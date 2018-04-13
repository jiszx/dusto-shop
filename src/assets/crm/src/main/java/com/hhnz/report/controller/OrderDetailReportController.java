package com.hhnz.report.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.report.model.OrderDetailReport;
import com.hhnz.report.model.TransferOrderDetailReport;
import com.hhnz.report.service.IOrderDetailReportService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Params;
import com.hhnz.util.Params.ParamMapBuilder;

@Controller
@RequestMapping("/report/order")
public class OrderDetailReportController {
  
  @Resource
  private IOrderDetailReportService orderDetailReportService;
  @Resource
  private IPowerOrgService  powerService;

  @ResponseBody
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public AjaxDTO orderDetails(AjaxDTO page, OrderDetailReport model, HttpServletRequest request){
    List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
    UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
    if("admin".equals(user.getLoginName())){
      stationids = null; // 不限制岗位
    }
    return orderDetailReportService.orderDetails(page, model, stationids);
  }
  
  @ResponseBody
  @RequestMapping(value = "/retail/invoice", method = RequestMethod.GET)
  public AjaxDTO retailInvoice(AjaxDTO page){
    return orderDetailReportService.retailOrderInvoice(page);
  }
  
  @ResponseBody
  @RequestMapping(value = "/retail/generate", method = RequestMethod.GET)
  public String generateExcel(HttpServletRequest request) throws IOException{
    List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
    UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
    if("admin".equals(user.getLoginName())){
      stationids = null; // 不限制岗位
    }
    Map<String, Object> param = Params.builder().add("stations", stationids).buid();
    return orderDetailReportService.generateRetailOrderInvoiceExcel(param);
  }
  
  @ResponseBody
  @RequestMapping(value = "/transfer/detail", method = RequestMethod.GET)
  public AjaxDTO transferOrderDetail(AjaxDTO page, TransferOrderDetailReport detail, HttpServletRequest request){
    List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
    UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
    if("admin".equals(user.getLoginName())){
      stationids = null; // 不限制岗位
    }
    return orderDetailReportService.transferOrderDetail(page, detail, stationids);
  }
  
  @ResponseBody
  @RequestMapping(value = "/transfer/generate", method = RequestMethod.GET)
  public String generateTransferOrderExcel(HttpServletRequest request) throws IOException{
    List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
    UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
    if("admin".equals(user.getLoginName())){
      stationids = null; // 不限制岗位
    }
    Map<String, Object> param = Params.builder().add("stations", stationids).buid();
    return orderDetailReportService.generateTransferOrderExcel(param);
  }
  
  @ResponseBody
  @RequestMapping(value = "/orderdetail/generate", method = RequestMethod.GET)
  public String generateOrderDetailExcel(AjaxDTO page, OrderDetailReport model, HttpServletRequest request) throws IOException{
    @SuppressWarnings("unchecked")
    List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
    UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
    if("admin".equals(user.getLoginName())){
      stationids = null; // 不限制岗位
    }
    return orderDetailReportService.generateOrderDetail(page, model, stationids);
  }
}
