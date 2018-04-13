package com.hhnz.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IDictService;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractLines;
import com.hhnz.customer.model.CMerchCustContractV;
import com.hhnz.customer.model.ContractDetail;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.dto.ResponseResult;
import com.hhnz.pub.model.TArea;
import com.hhnz.pub.service.IAreaService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.ResponseMessage;

@Controller
@RequestMapping("/api/contract")
public class ApiContractController extends BaseController {

  @Resource
  private CustomerContractService contractService;
  @Resource
  private ICustomerService customerService;
  @Resource
  private IDictService dictService;
  @Resource
  private IAreaService areaService;
  @Resource
  private IPowerOrgService  powerService;
  
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ResponseResult list(AjaxDTO page, CMerchCustContractV contract, Long stationId, HttpServletRequest request) throws Exception {
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    limitVerify(page);
    Map<String, Object> userInfo = userInfo(request);
    UserStations userstations = userStation(userInfo, stationId);//用户岗位
    List<UserJobs> jobs = userJobs(userInfo);
    List<Long> stationids =  this.powerService.getUserStations(user, jobs, userstations);//获取用户可以查看的岗位
    AjaxDTO list = contractService.list(page, contract, stationids);
    return ResponseResult.builder().data(list.getRows()).build();
  }

//  @ResponseBody
//  @RequestMapping(value = "/save", method = RequestMethod.POST)
//  public ResponseResult saveContranct(CMerchCustContract model, HttpServletRequest request)
//      throws Exception {
//    TEmployee user = loginApiUser(request);
//    if (user == null) {
//      return ResponseMessage.tokenExpired();
//    }
//    CMerchCustBase merch = customerService.findCustBaseById(model.getMerchCustId());
//    String warehouse = contractService.getvirtualWarehouse(model.getMerchCustId());
//    model.setFactoryId("1420");
//    model.setOrganizationId(merch.getOrganizationId());
//    model.setVirtualWarehouse(StringUtils.isNotEmpty(warehouse) ? warehouse : "Z101");
//    model.setCreateOid(user.getId());
//    model.setCreateTs(new Date());
//    model.setCreditAmt(BigDecimalASME.multiply(model.getCreditAmt()));
//    Long result = contractService.addPaper(model);
////    Long result = 0L;
//    return ResponseResult.builder().data(result==null?0:result.equals(0L)?0:1).build();
//  }

  @ResponseBody
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public ResponseResult detail(Long id, String type) throws Exception {
    ContractDetail detail = new ContractDetail();
    CMerchCustContract contract = contractService.findById(id);
    CMerchCustBase merch = customerService.findCustBaseById(contract.getMerchCustId());
    List<CMerchCustContractLines> lines = contractService.getLines(id);
    contractService.fillAgentName(contract);
    CMerchCustBase rMerch = new CMerchCustBase();
    rMerch.setName(merch.getName());
    rMerch.setSapCustomerId(merch.getSapCustomerId());
    rMerch.setId(merch.getId());
    detail.setContract(contract);
    detail.setLines(lines);
    detail.setMerch(rMerch);
    return ResponseResult.builder().data(detail).build();
  }
  
  @ResponseBody
  @RequestMapping(value = "/area", method = RequestMethod.GET)
  public ResponseResult area() throws Exception {
    List<TArea> areas = areaService.findAll();
    return ResponseResult.builder().data(areas).build();
  }

}
