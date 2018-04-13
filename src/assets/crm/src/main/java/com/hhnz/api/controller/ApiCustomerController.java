package com.hhnz.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IProductService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.enu.CustomerBaseStateEnu;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustStation;
import com.hhnz.customer.model.ContractDetail;
import com.hhnz.customer.service.ApiCustomerService;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.customer.validator.CustBaseValidator;
import com.hhnz.dto.ResponseResult;
import com.hhnz.process.service.IProcessService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.util.Constants;
import com.hhnz.util.ResponseMessage;

@Controller
@RequestMapping("/api/customer")
public class ApiCustomerController extends BaseController {

  @Autowired
  private ICustomerService customerService;
  @Autowired
  private IAttachmentService attaService;
  @Autowired
  private CustomerContractService contractService;
  @Autowired
  private ApiCustomerService apiCustomerService;
  @Autowired
  private IProcessService processService;
  @Autowired
  private IProductService productService;

  // 保存客户
  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ResponseResult saveCustomer(CMerchCustBase custBase, Long stationId, BindingResult result,
      HttpServletRequest request,
      @RequestParam(required = false, value = "files") MultipartFile[] files) throws Exception {
//    CustBaseValidator validator = new CustBaseValidator();
//    validator.validate(custBase, result);
//    if (result.hasErrors()) {
//      return ResponseMessage.customerInfoMiss();
//    }
    // 用户信息
    boolean applyCustomer = false;
    if("2".equals(custBase.getStates())){
      applyCustomer = true;
    }
    Map<String, Object> userInfo = userInfo(request);
    if (userInfo == null) {
      return ResponseMessage.tokenExpired();
    }
    TEmployee user = loginApiUser(request);
    UserStations stations = userStation(userInfo, stationId);
    if(custBase.getId()!=null && !custBase.getId().equals(0L)){// 更新
      CMerchCustBase existMerch = customerService.findCustBaseById(custBase.getId());
      if(existMerch==null || !"1".equals(existMerch.getStates())){
        return ResponseResult.builder().code(-1).data("客户不可编辑").build();
      }
      custBase.setUpdateOid(user.getId());
      custBase.setUpdateTs(new Date());
      customerService.update(custBase, "");
    }else{
      // 新增客户
      custBase.setCreateOid(user.getId());
      custBase.setCreateTs(new Date());
      custBase.setOrganizationId(stations.getStationorgid().substring(0, 5));
      custBase.setIsInvoice(StringUtils.isEmpty(custBase.getIsInvoice())?"1" : custBase.getIsInvoice());
      CMerchCustStation custStation = new CMerchCustStation();
      custStation.setUpdateOid(user.getId());
      custStation.setUpdateTs(new Date());
      custStation.setCreateOid(user.getId());
      custStation.setCreateTs(new Date());
      custStation.setStates("1");
      custStation.setStationId(stations.getStationid());
      custBase.setCustStation(custStation);
      custBase.setIsAttachment((files != null && files.length > 0) ? "1" : "0");
      custBase.setStates(CustomerBaseStateEnu.POTENTIAL.getCode());
      custBase = customerService.add(custBase);
      
    }
    
    // 删除附件
    List<TAttachment> attachments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(),
        String.valueOf(custBase.getId()), null);
    if(attachments != null){
      for(TAttachment temp : attachments){
          attaService.delAttachent(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), temp.getId());
      }
    }
    // 插入附件信息
    if (files != null && files.length > 0) {
      attaService.addAttachment(CMerchCustBase.class.getSimpleName(),
          String.valueOf(custBase.getId()), String.valueOf(custBase.getCreateOid()), files);
    }
    int resp = 1;
    if(applyCustomer){
      String apply = applyCustomer(custBase.getId(), user);
      if(!Constants.SUCCESS_CODE_STR.equals(apply)){
        resp = 0;
      }
    }
    return ResponseResult.builder().data(resp).build();
  }
  
 //提交客户送审
 @ResponseBody
 @RequestMapping(value = "/censor", method = RequestMethod.POST)
 public ResponseResult censor(Long id, HttpServletRequest request) throws Exception{
   TEmployee user = loginApiUser(request);
   if(user==null){
     return ResponseMessage.tokenExpired();
   }
   String result = applyCustomer(id, user);
   if(Constants.SUCCESS_CODE_STR.equals(result)){
     return ResponseResult.builder().data(1).build();
   }
   return ResponseResult.builder().data(0).build();
 }
  
  // 送审
  private String applyCustomer(Long id, TEmployee user) throws Exception{
    CMerchCustBase existedOne = customerService.findCustBaseById(id);
    if(!CustomerBaseStateEnu.POTENTIAL.getCode().equals(existedOne.getStates())){
        return "客户已申请过审批！";
    }
    if(!"5".equals(existedOne.getCustType()) && (existedOne.getCustStation() == null || (existedOne.getCustStation().getStationId() == null))){
        return "客户还未指定岗位！";
    }
    Map<String,Object> params = new HashMap<>();
    String processName = "costomer_apply";
    String viewPage = "customer/view"+id+".html";
    if("5".equals(existedOne.getCustType())){
      viewPage = "customer/viewRetail/"+id+".html";
      processName = "retail_apply";
      params.put("TICKET", "3".equals(existedOne.getIsInvoice()) ? 1 : 0);
    }
    params.put("key",id);
    params.put("name", existedOne.getName());
    params.put("groupType","CW");
    params.put("level","01");
    params.put("userId",user.getId());
    params.put("viewPage",viewPage);
    //key 对象主键
    String processId = processService.startProcess(params, processName, user.getLoginName());
    existedOne.setProcessId(processId);
    existedOne.setStates(CustomerBaseStateEnu.SUBMITED.getCode());
    customerService.save(existedOne);
    return Constants.SUCCESS_CODE_STR;
}

  // 详情
  @ResponseBody
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public CMerchCustBase detail(Long id) throws Exception {
//	  根据id找客户基本信息
    CMerchCustBase custBase = customerService.findCustBaseById(id);
    
    List<TAttachment> attachments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(),
        String.valueOf(custBase.getId()), null);
    productService.fillAttachmentUrl(attachments);
    custBase.setAttachments(attachments);
    
    ContractDetail contract = contractService.merchContractdetail(id);
    custBase.setContract(contract);
    Long custPID = custBase.getPid();
    if(custPID != null){
    	CMerchCustBase parent = detail(custPID);
        custBase.setParent(parent);
    }else{
    	custBase.setParent(customerService.findCustBaseById(id));
    }
    return custBase;
  }
  //crm 2期    返回配送商下面的零售商列表，客户选择部分
  @ResponseBody
  @RequestMapping(value = "/retailers", method = RequestMethod.GET)
  public List<CMerchCustBase> retailers(String custPID,String search) throws Exception {
    
	String name =  null;
	String phone = null;
	String consignee = null;
	if(search != null){
		name =  search.trim();
		phone = search.trim();
		consignee = search.trim();
	}
	
	
	Map<String,Object> params = new HashMap<String,Object>();
	params.put("custPID", custPID);
	if((name!=null)&&(!"".equals(name))){
		params.put("name", "'%"+name+"%'");
	}
	if((phone!=null)&&(!"".equals(phone))){
		params.put("phone","'%"+phone+"%'");
	}
	if((consignee!=null)&&(!"".equals(consignee))){
		params.put("consignee","'%"+consignee+"%'");
	}
    List<CMerchCustBase>  list = apiCustomerService.findCustBaseByPID(params);
    return list;
  }
  
}
