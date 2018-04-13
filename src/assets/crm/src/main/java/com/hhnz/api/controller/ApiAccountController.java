package com.hhnz.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.account.service.IAccountService;
import com.hhnz.account.service.IUpAccountService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IHomeService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.dto.CustomerBaseDTO;
import com.hhnz.customer.dto.CustomerInvDTO;
import com.hhnz.customer.model.CMerchCustBaseV;
import com.hhnz.customer.model.CMerchCustInv;
import com.hhnz.customer.service.ICustomerInvService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.dto.ResponseResult;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;
import com.hhnz.util.ResponseMessage;

/**
 * 客户资金上账controller
 * 
 * @author skevin
 *
 */
@Controller
@RequestMapping("/api/account")
public class ApiAccountController extends BaseController {

  @Resource
  private IUpAccountService upaccountService;
  @Resource
  private ICustomerInvService customerInvService;
  @Resource
  private IHomeService homeService;
  @Resource
  private IorganizationService orgservice;
  @Resource
  private ICustomerService customerService;
  @Resource
  private IAccountService accountService;
  @Resource
  private IPowerOrgService  powerService;


  /**
   * 客户打款信息录入修改
   * 
   * @param model
   * @param request
   * @return
   * @throws Exception
   */
  @ResponseBody
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public ResponseResult editaccount(CMerchUpaccount model, HttpServletRequest request)
      throws Exception {
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    model.setUpdateOid(user.getId());
    model.setUpdateTs(new Date());
    Integer result = this.upaccountService.updateupaccount(model);
    return ResponseResult.builder().data(result).build();
  }


  /**
   * 打款资金
   * 
   * @param page
   * @return
   * @throws Exception
   */
  @ResponseBody
  @RequestMapping(value = "/upaccount", method = RequestMethod.GET)
  public List<?> listPage(AjaxDTO page, CMerchUpaccount upaccount, Long stationId, HttpServletRequest request) throws Exception {
    limitVerify(page);
    List<Long> stationids = null;
    TEmployee user = loginApiUser(request);
    if(user!=null && stationId!=null && !stationId.equals(0)){
      Map<String, Object> userInfo = userInfo(request);
      UserStations userstations = userStation(userInfo, stationId);//用户岗位
      List<UserJobs> jobs = userJobs(userInfo);
      stationids =  this.powerService.getUserStations(user, jobs, userstations);//获取用户可以查看的岗位
    }
    Map<String, Object> param = new HashMap<>();
    if(StringUtils.isNotEmpty(upaccount.getStates())){
      param.put("states", Arrays.asList(upaccount.getStates().split(",")));
    }
    if(upaccount.getStates()!=null && !upaccount.getStates().contains("2") && !upaccount.getStates().contains("4")){
      param.put("merchstation", stationids);
    }
    AjaxDTO dto = this.upaccountService.getSalesAccountList(param, page);
    dto = upaccountService.bankCodeToName(dto);
    return dto.getRows();
  }

  // 送审(资金确认)
  @ResponseBody
  @RequestMapping(value = "/upaccount/censor", method = RequestMethod.POST)
  public ResponseResult censor(Long id, Long merchid, HttpServletRequest request) {
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    CMerchUpaccount upa = new CMerchUpaccount();
    upa.setId(id);
    upa.setMerchCusId(merchid);
    upa.setUpdateOid(user.getId());
    upa.setUpdateTs(new Date());
    upa.setSalesrepId(user.getId());
    upa.setSalesrepDate(new Date());
    upaccountService.updateupaccount(upa);
    int result =Integer.parseInt((String) this.upaccountService.censor(id, "3").get("type"));
    return ResponseResult.builder().data(result==200 ? 1 : 0).build();
  }

  /*
   * 
   * 获取销售人员对应的客户
   */
  @ResponseBody
  @RequestMapping(value = "/accountcustomer", method = RequestMethod.GET)
  public ResponseResult getaccountCustomer(String orgid, HttpServletRequest request)
      throws RuntimeException {
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    AjaxDTO dto = new AjaxDTO();
    List<Long> stationid = new ArrayList<Long>();
    stationid.add(user.getId());
    dto = this.upaccountService.getaccountCustomer(orgid,stationid);
    return ResponseResult.builder().data(dto.getRows()).build();
  }

  // 余额
  @ResponseBody
  @RequestMapping(value = "/balance", method = RequestMethod.GET)
  public ResponseResult balance(AjaxDTO page, String custname, String orgid, Long merchid,
		  String custType,String status,String name,String areaId, Long stationId,
      HttpServletRequest request) throws Exception {
    limitVerify(page);
    
//  这里是地区列表
  //没办法，参数的格式提前定死了1,2,7.所以现在我只能用String接受参数然后转换成Long数组
   String[] areaList = null;
   if((areaId != null) && (! "".equals(areaId))){
  	 areaList = areaId.split(",");
   }
//  顾客类型列表
   String[] custTypeList = null;
   if((custType != null) && (! "".equals(custType))){
  	  custTypeList = custType.split(",");
   }
//  客户姓名
   if((name != null) && (! "".equals(name))){
  	 name = "'%"+name+"%'" ;
   }else if((name != null) && ( "".equals(name))){
  	 name = null;
   }
//  这个是新添加的status也必须是转换成list
    String[] statusList = null;
    if((status != null) && (! "".equals(status))){
  	  statusList = status.split(",");
    } 
    
   
    
    HashMap<String, Object> params = new HashMap<String, Object>();
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    Map<String, Object> userInfo = userInfo(request);
    UserStations userstations = userStation(userInfo, stationId);//用户岗位
    List<UserJobs> jobs = userJobs(userInfo);
    List<Long> stationids =  this.powerService.getUserStations(user, jobs, userstations);//获取用户可以查看的岗位
    params.put("custname", custname);
    params.put("merchid", merchid);
    //新加筛选的条件字段信息
    params.put("areaList", areaList);
    params.put("custTypeList", custTypeList);
    params.put("name", name);
    params.put("statusList", statusList);
    
   /* List<UserJobs> jobs = (List<UserJobs>) userInfo.get(SessionKey.JOBS);// 用户职位信息
    if (!StringUtils.isEmpty(orgid) && !"null".equals(orgid)) {
      params.put("type", "1");
      params.put("orgid", orgid);
    } else {
      params.put("type", "2");
      List<String> orgs = new ArrayList<String>();
      if(jobs==null || jobs.isEmpty()){
        orgs.add("0");
        params.put("orgid", orgs);
      }else{
        for (UserJobs job : jobs) {
          if (job.getLevels() == "2" && "2".equals(job.getLevels())) {
            orgs.add(job.getOrgid());
          } else if (job.getLevels().equals("1") || job.getLevels().equals("0")) {
            List<CrmSalesOrganization> list = this.orgservice.getcompanybyid(job.getOrgid());
            if (list != null && list.size() > 0) {
              for (CrmSalesOrganization org : list) {
                orgs.add(org.getId());
              }
            }
          } else {
            orgs.add(job.getOrgid().substring(0, 5));
          }
        }
        params.put("orgid", orgs);
      }
      
    }*/
    params.put("orgid", orgid);
    params.put("stationid", stationids);
    params.put("bpage", page.getOffset());
    params.put("epage", page.getLimit() + page.getOffset());
    AjaxDTO dto = accountService.getMerchAccount(params);
    return ResponseResult.builder().data(dto.getRows()).build();
  }

  // 客户库存查询
  @ResponseBody
  @RequestMapping(value = "/inventory", method = RequestMethod.GET)
  public List<?> custinvList(CustomerInvDTO invDTO, String period, AjaxDTO page) throws Exception {
    limitVerify(page);
    if(StringUtils.isNotEmpty(period)){
      invDTO.setDate(DateUtil.parse(period, "yyyy-MM"));
    }
    return customerInvService.find(invDTO, page).getRows();
  }

  // 客户列表
  @ResponseBody
  @RequestMapping(value = "/customer", method = RequestMethod.GET)
  public List<?> list(CustomerBaseDTO customerBasedto, AjaxDTO page, String name,
      String areaId, Long stationId, HttpServletRequest request) throws Exception {
    TEmployee user = loginApiUser(request);
    List<Long> stationids = null;
    if(user!=null && stationId!=null && !stationId.equals(0)){
      Map<String, Object> userInfo = userInfo(request);
      UserStations userstations = userStation(userInfo, stationId);//用户岗位
      List<UserJobs> jobs = userJobs(userInfo);
      stationids =  this.powerService.getUserStations(user, jobs, userstations);//获取用户可以查看的岗位
    }
    limitVerify(page);
    
//    这里是地区列表
    //没办法，参数的格式提前定死了1,2,7.所以现在我只能用String接受参数然后转换成Long数组
    String[] areaList = null;
    if((areaId != null) && (! "".equals(areaId))){
    	areaList = areaId.split(",");
    }
//    顾客类型列表
    String custType = customerBasedto.getCustType();
    String[] custTypeList = null;
    if((custType != null) && (! "".equals(custType))){
    	 custTypeList = custType.split(",");
    }
//    客户姓名
    if((name != null) && (! "".equals(name))){
    	name = "'%"+name+"%'" ;
    }else if((name != null) && ( "".equals(name))){
    	name = null;
    }
//    这个是新添加的status也必须是转换成list
     String status = customerBasedto.getStatus();
     String[] statusList = null;
     if((status != null) && (! "".equals(status))){
    	 statusList = status.split(",");
     } 
     
//    return customerService.findCustBase(customerBasedto, page).getRows();//原代码
    return customerService.findCustBaseVague(customerBasedto, page,areaList,name,custTypeList,statusList, stationids).getRows();
  }

  // 客户库存添加
  @ResponseBody
  @RequestMapping(value = "/inventory/add", method = RequestMethod.POST)
  public ResponseResult custinvAdd(CMerchCustInv inventory, String period, HttpServletRequest request)
      throws Exception {
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    inventory.setOrganizationId(user.getOrganizationId());
    inventory.setSalesrepId(user.getId());
    inventory.setSalesrepName(user.getName());
    inventory.setCreateTs(DateUtil.parse(period, "yyyy-MM"));
    int result = customerInvService.add(inventory);
    return ResponseResult.builder().data(result).build();
  }

  @ResponseBody
  @RequestMapping(value = "/overview", method = RequestMethod.GET)
  public ResponseResult overview(HttpServletRequest request){
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    return ResponseMessage.customerInfoMiss();
  }
  
  @ResponseBody
  @RequestMapping(value = "/addCash", method = RequestMethod.GET)
  public ResponseResult addCash(String addCash,HttpServletRequest request){
	  
	  return null;
  }
  
}
