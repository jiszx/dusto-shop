package com.hhnz.cost.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.cost.model.CrmCostAdjust;
import com.hhnz.cost.model.CrmCostAdjustV;
import com.hhnz.cost.model.CrmCostMainV;
import com.hhnz.cost.service.ICostAdjustService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.process.service.IProcessService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

/**
 * 费用池调整Controller
 * 
 * @author skevin
 *
 */
@Controller
@RequestMapping("cost/adjust")
public class CostAdjustController {
  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(CostAdjustController.class);

  @Autowired
  private ICostAdjustService service;

  @Autowired
  private IProcessService processService;
  @Resource
  private IPowerOrgService  powerService;

  @RequestMapping("/adjust.html")
  public String indexPage() {
    return "cost/costAdjust";
  }

  @RequestMapping("/detail")
  public ModelAndView detailPage(Long id)throws Exception{
    ModelAndView mv = new ModelAndView();
    CrmCostAdjustV vo = this.service.costAdjustDetail(id);
    mv.setViewName("cost/detail");
    mv.addObject("vo",vo);
    return mv;
  }

  @SuppressWarnings("unchecked")
@ResponseBody
  @RequestMapping(value = "main/list", method = RequestMethod.GET)
  public AjaxDTO balancelistPage(AjaxDTO bean, CrmCostMainV cost, HttpServletRequest request){
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);//用户职位信息
    UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);//用户当前岗位
    List<String> provs = powerService.powerProv(user, jobs, station);
    AjaxDTO dto = this.service.findBalanceList(bean, cost, provs);
    return dto;
  }

  @ResponseBody
  @RequestMapping("adjustlist")
  public AjaxDTO adjustlistPage(@ModelAttribute AjaxDTO bean, CrmCostAdjustV adjust) {
    AjaxDTO dto = this.service.findAdjustList(adjust, bean);
    return dto;
  }

  @ResponseBody
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public int addAdjust(CrmCostAdjust adjust, HttpServletRequest request) {
    TEmployee user = (TEmployee) request.getSession().getAttribute("user");
    adjust.setCreateOid(user.getId());
    return service.saveAdjust(adjust);
  }

  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public int deleteAdjust(Long id) {
    return service.deleteAdjust(id);
  }

  @ResponseBody
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public int editAdjust(CrmCostAdjust adjust) {
    return service.editAdjust(adjust);
  }

  /**
   * 提交审批
   * @param id
   * @return
     */
  @ResponseBody
  @RequestMapping(value = "/censor", method = RequestMethod.POST)
  public int censor(Long id,HttpServletRequest request)throws Exception {
    TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    Map<String,Object> params = new HashMap<String,Object>();
    params.put("key",id);
    params.put("groupType","CW");
    params.put("level","01");
    params.put("userId",emp.getId());
    params.put("name", "费用池手工调整："+id);
    params.put("viewPage","cost/adjust/detail?id="+id);
    //TODO 参数列表
    //查看参数详情列表
    //key 对象主键
    String processId = this.processService.startProcess(params,"cost_adjust",emp.getLoginName());
    //this.processService.updateStartUser(processId,emp.getLoginName());
    //将流程ID写入对象信息，方便查看
    service.updateProcessId(id, processId);
    return service.censor(id);
  }
  
  @RequestMapping("updateCost")
  public Integer updateCost(){
  	return this.service.updateCost();
  }
  
}
