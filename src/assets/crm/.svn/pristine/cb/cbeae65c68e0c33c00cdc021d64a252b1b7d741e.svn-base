package com.hhnz.customerInv.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customerInv.model.CMerchCustProudctInvV;
import com.hhnz.customerInv.model.MerchProudctBalancesV;
import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;

/**
 * 客户库存管理--库存查询
 * @author hhnz-skevin 2016-12-06
 *
 */
@RequestMapping("customerInvSearch")
@Controller
public class CustomerInvController {
	
	@Resource
	private CustomerStockService service;
	
	
	@Resource
	private IPowerOrgService  powerService;
	/**
	 * 客户库存查询页面跳转
	 * @return
	 */
	@RequestMapping("customerInvSearch.html")
	public String  customerInvSearch(){
		return "customerInv/customerInvSearch";
	}
	
	/**
	 * 客户库存期间数据页面跳转
	 * @return
	 */
	@RequestMapping("customerInvBalances.html")
	public String  customerInvBalances(){
		return "customerInv/customerInvBalances";
	}
	/**
	 * 客户库存查询List
	 * @param bean
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("invList")
	public @ResponseBody AjaxDTO  getList(@ModelAttribute AjaxDTO bean, CMerchCustProudctInvV  inv,HttpServletRequest request)  throws Exception{
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		return this.service.getInvList(bean,inv,stationids,user);
	}
	
	/**
	 * 库存期间数据
	 * @param bean
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("balancesList")
	public @ResponseBody  AjaxDTO getBalanceList(@ModelAttribute AjaxDTO bean, MerchProudctBalancesV  inv,HttpServletRequest request)  throws Exception{
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		return this.service.getBalancesList(bean,inv,stationids,user);
	}
	
	/**
	 * 库存期间数据详情list
	 * @param bean
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("balanceDetailsList")
	public @ResponseBody  AjaxDTO  getbalanceDetailsList(@ModelAttribute AjaxDTO bean,Long id) throws Exception{
		return this.service.getbalanceDetailsList(bean,id);
	}
	
	@RequestMapping("updateInv")
	@ResponseBody
	public String updateInv() throws Exception{
		return this.service.updateInv()==1?"S":"E";
	}
	
	@RequestMapping("updateBalance")
	@ResponseBody
	public  String  updateBalance(Long merchId,String materialId) throws Exception{
		return this.service.updateBalance(merchId,materialId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String export(AjaxDTO bean, CMerchCustProudctInvV  inv,HttpServletRequest request) throws IOException{
	  List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
      UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
      TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
      List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
	  return service.generateInvDetail(bean, inv, stationids, user);
	}
}
