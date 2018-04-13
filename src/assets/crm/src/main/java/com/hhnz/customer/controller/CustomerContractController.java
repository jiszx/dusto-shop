package com.hhnz.customer.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TFactory;
import com.hhnz.crm.model.TFactoryV;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IFactoryService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractLines;
import com.hhnz.customer.model.CMerchCustContractV;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

@Controller
@RequestMapping("/customer/contract")
public class CustomerContractController {

  @Resource
  private CustomerContractService contractService;
  @Autowired
  private IProcessService processService;
  @Resource
  private IPowerOrgService  powerService;
  @Resource
  private IFactoryService factoryService;
  @Resource
  private ICustomerService customerService;

  // 客户合同列表
  @SuppressWarnings("unchecked")
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public AjaxDTO list(AjaxDTO page, CMerchCustContractV contract, HttpServletRequest request) {
    List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
	UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
	TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
	List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
    return this.contractService.list(page, contract, stationids);
  }
  
  /**
   * 配送商合同录入
   * @return
   */
  @RequestMapping("distributionContract.html")
  public String  distributionContract(String custType, Model model)throws Exception{
	  model.addAttribute("custType", custType);
	  return "customer/distributionContract";
  }
  
  /**
   * 配送商合同修改
   * @param id
   * @return
   * @throws Exception
   */
  @RequestMapping("distributionPaperEdit")
  @ResponseBody
  public ModelAndView distributionPaperEdit(Long id) throws Exception {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("customer/distributionContractEdit");
	    CMerchCustContract vo = this.contractService.findById(id);
	    vo.setCreditAmt(BigDecimalASME.divide(vo.getCreditAmt()));
	    mv.addObject("vo", vo);
	    TFactory factory = this.factoryService.getFactoryById(vo.getFactoryId());
	    mv.addObject("factory", factory);

	    CMerchCustBase merch = customerService.findCustBaseById(vo.getMerchCustId());
	    mv.addObject("merch", merch);
	    return mv;
  }
  /**
   * KA合同录入
   * @param custType
   * @param model
   * @return
   * @throws Exception
   */
  @RequestMapping("/kaContract.html")
  public String kaContract(String custType,Model model) throws Exception{
	 model.addAttribute("custType", custType);
	 return "customer/kaContract";
  }
  
  @RequestMapping("/kaContractEdit")
  public ModelAndView kaContractEdit(Long id) throws Exception{
	  ModelAndView mv = new ModelAndView();
	  mv.setViewName("customer/kaContractEdit");
	  CMerchCustContract vo = this.contractService.findById(id);
	  vo.setCreditAmt(BigDecimalASME.divide(vo.getCreditAmt()));
	  mv.addObject("vo", vo);
	  TFactory factory = this.factoryService.getFactoryById(vo.getFactoryId());
	  mv.addObject("factory", factory);

	  CMerchCustBase merch = customerService.findCustBaseById(vo.getMerchCustId());
	  mv.addObject("merch", merch);
	  return mv;
  }
  
  
  @RequestMapping("preEdit")
  public ModelAndView preEdit(Long id) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("customer/editPaper");
    CMerchCustContract vo = this.contractService.findById(id);
    vo.setCreditAmt(BigDecimalASME.divide(vo.getCreditAmt()));
    mv.addObject("vo", vo);
   // TFactory factory = this.factoryService.getFactoryById(vo.getFactoryId());
   // mv.addObject("factory", factory);

    CMerchCustBase merch = customerService.findCustBaseById(vo.getMerchCustId());
    mv.addObject("merch", merch);
    return mv;
  }

  @RequestMapping("/contractDetails.html")
  public ModelAndView contractDetails(Long id, String type) throws Exception {
    String page = StringUtils.isEmpty(type) ? "customer/contractDetails" : "customer/contractView";
    ModelAndView mv = new ModelAndView(page);
    CMerchCustContract contract = new CMerchCustContract();
    contract = this.contractService.findById(id);
    mv.addObject("contract", contract);
    //TFactory factory = this.factoryService.getFactoryById(contract.getFactoryId());
    //mv.addObject("factory", factory);
    CMerchCustBase merch = customerService.findCustBaseById(contract.getMerchCustId());
    mv.addObject("merch", merch);
    mv.addObject("type", StringUtils.isEmpty(type) ? 1 : 2);
    return mv;
  }

  /**
   * 添加合同主档
   * 
   * @param model
   * @param request
   * @return
   * @throws Exception
   */

  @RequestMapping("/addCustPaper")
  @ResponseBody
  public Long addCustPaper(CMerchCustContract model,String lineData, HttpServletRequest request) throws Exception {
    TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    /*
     * List<UserJobs> list = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);
     * if(list !=null && list.size()>0){ UserJobs job = list.get(0);
     * model.setOrganizationId(job.getOrgid()); }else{
     * model.setOrganizationId(emp.getOrganizationId()); }
     */
    model.setCreateOid(emp.getId());
    model.setCreateTs(new Date());
    model.setCreditAmt(BigDecimalASME.multiply(model.getCreditAmt()));
    Long res = this.contractService.addPaper(model,lineData);
    return res;
  }

  @RequestMapping("/addPaperLine")
  @ResponseBody
  public int addPaperLine(CMerchCustContractLines model) throws Exception {
    model.setyAmt(BigDecimalASME.multiply(model.getyAmt()));
    model.setqAmt(BigDecimalASME.multiply(model.getqAmt()));
    return this.contractService.addPaperLines(model);

  }
  
  @RequestMapping("/addLineByagentType")
  @ResponseBody
  public int addLineByagentType(Long contractId,String agentType) throws Exception{
	  CMerchCustContract contract = this.contractService.findById(contractId);
	  contract.setAgentType(agentType);
	  return this.contractService.addLineByagentType(contract);
  }
  @RequestMapping("/delPaperLines")
  @ResponseBody
  public int delPaperLines(Long id) throws Exception {

    return this.contractService.delLines(id);

  }

  @RequestMapping("/updateLines")
  @ResponseBody
  public int updateLines(CMerchCustContractLines model) throws Exception {
    return this.contractService.updateLines(model);
  }

  @ResponseBody
  @RequestMapping(value = "/lines", method = RequestMethod.GET)
  public AjaxDTO list(Long id, AjaxDTO page) throws Exception {
    if (id != null && id > 0) {
      return this.contractService.listLines(id);
    } else {
      return new AjaxDTO();
    }
  }

  /**
   * 删除编辑状态的合同信息
   * 
   */
  @RequestMapping("delContract")
  @ResponseBody
  public String delContract(Long contractid) throws Exception {
    return this.contractService.delContract(contractid);
  }

  @RequestMapping("updateContractStates")
  @ResponseBody
  public String updateContractStates(Long contractid, String states, HttpServletRequest request)
      throws Exception {
    if ("2".equals(states)) {
      String processName = "customer_paper_apply";
      CMerchCustContract contract = contractService.findById(contractid);
      CMerchCustBase merch = customerService.findCustBaseById(contract.getMerchCustId());
      if("3".equals(merch.getCustType())){
        processName = "customer_KA_paper_apply";
      }
      TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("key", contractid);
      params.put("groupType", "CW");
      params.put("level", "01");
      params.put("userId", emp.getId());
      params.put("name", "合同编号：" + contractid);
      params.put("viewPage", "customer/contract/contractDetails.html?id=" + contractid + "&type=2");// 合同详情页面
      // key 对象主键
      String processId =
          this.processService.startProcess(params, processName, emp.getLoginName());
      // 更新流程id，状态
      // return processId;
      this.contractService.updateContractProcess(contractid, processId);
    }
    return this.contractService.updateContractStates(contractid, states);
  }


  /*
   * @RequestMapping("/view{id}.html") public ModelAndView contractView(@PathVariable("id")Long id)
   * throws Exception{ ModelAndView mv = new ModelAndView("customer/contractView");
   * CMerchCustContract contract = new CMerchCustContract(); contract=
   * this.contractService.findById(id); mv.addObject("contract",contract); TFactory factory =
   * this.factoryService.getFactoryById(contract.getFactoryId()); mv.addObject("factory",factory);
   * return mv; }
   */

  @RequestMapping("factorys")
  @ResponseBody
  public AjaxDTO getfactorys(String orgid) throws Exception {
    AjaxDTO dto = new AjaxDTO();
    List<TFactoryV> list = this.contractService.getfactorys(orgid);
    dto.setRows(list);
    dto.setTotal(list.size());
    return dto;
  }

  @RequestMapping("getAgents")
  @ResponseBody
  public AjaxDTO  getAgents(String agentType) throws Exception{
	  return  this.contractService.getAgents(agentType);
  }
  
  @RequestMapping("getvirtualWarehouse")
  @ResponseBody
  public String getvirtualWarehouse(Long merchId) throws Exception{
	  return this.contractService.getvirtualWarehouse(merchId);
  }
  
  @RequestMapping("bondsByModel")
  @ResponseBody
  public  AjaxDTO  getBondsByModel(String model) throws Exception{
	  return this.contractService.selectBondsByModel(model);
  }
}
