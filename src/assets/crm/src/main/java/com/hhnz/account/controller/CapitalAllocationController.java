package com.hhnz.account.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.account.service.ICapitalAllocationService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;

/**
 * 配送商资金匹配
 * @author hhnz-skevin 2016-011-08
 *
 */
@Controller
@RequestMapping("account/allocation")
public class CapitalAllocationController {
	
	@Resource
	private IPowerOrgService  powerService;
	
	@Resource
	private ICapitalAllocationService  service;
	
	@RequestMapping("capotalAllocation.html")
	public String capotalAllocation(){
		return "account/capotalAllocation";
	}
	
	/**
	 * 获取配送商基本信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("customer")
	@ResponseBody
	public  AjaxDTO customer(HttpServletRequest request){
		AjaxDTO dto = new AjaxDTO();
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		List<CMerchCustBase> merchs = new ArrayList<CMerchCustBase>();
		int total =0;
		if("1".equals(user.getUserType())){
			//配送商用户
			CMerchCustBase merch =this.service.getmerchByID(user.getMerchId());
			merchs.add(merch);
			total =1;
		}else{
			//非配送商用户，通过职位获取数据
			merchs = this.service.getMerchs(stationids,"2");
			total = merchs.size();
		}
		dto.setRows(merchs);
		dto.setTotal(total);
		return dto;
	}
	
	/**
	 * 查询配送商收款记录
	 * @param merchCustId
	 * @return
	 */
	@RequestMapping("receives")
	@ResponseBody
	public AjaxDTO getReceives(Long merchCustId){
		return this.service.getReceives(merchCustId);
	}
	
	/**
	 * 获取配送商对应的零售商信息
	 * @param merchCustId
	 * @return
	 */
	@RequestMapping("retailer")
	@ResponseBody
	public AjaxDTO getRetailer(Long merchCustId){
		return this.service.getRetailer(merchCustId);
	}
	
	/**
	 * 获取选择到的零售商资金情况
	 * @param ids
	 * @return
	 */
	@RequestMapping("retailerAccount")
	@ResponseBody
	public AjaxDTO getRetailerAccount(String ids){
		return this.service.getRetailerAccount(ids);
	}
	
	/**
	 * 获取订单和客户余额信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("retailerOrders")
	@ResponseBody
	public AjaxDTO getRetailerOrders(String ids,String type){
		return this.service.getRetailerOrders(ids,type);
	}
	
	/**
	 * 获取批次订单
	 * @param merchCustId
	 * @return
	 */
	@RequestMapping("records")
	@ResponseBody
	public AjaxDTO getRecord(Long merchCustId){
		return this.service.getRecord(merchCustId);
	}
	/**
	 * 获取订单
	 * @param merchCustId
	 * @return
	 */
	@RequestMapping("orders")
	@ResponseBody
	public AjaxDTO getOrders(Long merchCustId){
		return this.service.getOrders(merchCustId);
	}
	/**
	 * 分配资金
	 * @param data
	 * @param receiveId
	 * @param request
	 * @return
	 */
	@RequestMapping("doAllocation")
	@ResponseBody
	public Map<String,Object> doAllocation(String data,Long receiveId,HttpServletRequest request){
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		return this.service.doAllocation(data,receiveId,user,request);
	}
	
	/**
	 * 资金分配明细
	 * @param bean
	 * @param upaccountId
	 * @return
	 */
	@RequestMapping("allocationDetail")
	@ResponseBody
	public AjaxDTO  allocationDetail(@ModelAttribute AjaxDTO bean,Long upaccountId){
		return this.service.allocationDetail(upaccountId,bean);
	}
}
