package com.hhnz.customer.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.model.CMerchPayer;
import com.hhnz.customer.model.CustomerPayerDetail;
import com.hhnz.customer.service.impl.CustomerPayerService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("/customer/payer")
public class CustomerPayerController {
  @Resource
  private CustomerPayerService payerService;
  @Resource
  private IPowerOrgService  powerService;

  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public AjaxDTO list(AjaxDTO page, CustomerPayerDetail model, HttpServletRequest request) {
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);//用户信息
    @SuppressWarnings("unchecked")
    List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);//用户职位信息
    UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);//用户当前岗位
    List<Long> stationids =  this.powerService.getUserStations(user, jobs, station);//获取用户可以查看的岗位
    return payerService.list(page, model, stationids);
  }

  @ResponseBody
  @RequestMapping(value = "/relate", method = RequestMethod.POST)
  public int relatePayer(CMerchPayer model) {
    return payerService.addOrUpdatePayer(model) ? 1 : 0;
  }
  
  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public int deletePayer(CMerchPayer model) {
    return payerService.deletePayer(model) ? 1 : 0;
  }

}
