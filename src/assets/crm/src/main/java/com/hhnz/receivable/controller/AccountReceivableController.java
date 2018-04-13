package com.hhnz.receivable.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.receivable.service.AccountReceivableService;
import com.hhnz.receivable.service.ArOverdueService;
import com.hhnz.util.AjaxDTO;

/**
 * 应收账款Controller
 * @author hhnz-skevin 2016-11-02
 *
 */
@RequestMapping("account/ARbalance")
@Controller
public class AccountReceivableController {

	@Resource
	private AccountReceivableService service;
	
	@Resource
	private ArOverdueService overdueservice;
	
	@Resource
	private IPowerOrgService powerService;
	/**
	 * 应收账款页面
	 * @return
	 */
	@RequestMapping("ARbalance.html")
	public String ARbalance() {
		return "ar/ARbalance";
	}
	
	/**
	 * 应收账款逾期页面跳转
	 * @return
	 */
	@RequestMapping("arOverdue.html")
	public String arOverdue(){
		return "ar/arOverdue";
	}
	/**
	 * 应收账款List
	 * @param bean
	 * @param custname
	 * @param sapCustomerId
	 * @param period
	 * @param organizationId
	 * @param custType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ARbalancelist")
	@ResponseBody
	public AjaxDTO getArBalancesList(@ModelAttribute AjaxDTO bean,
			String custname, String sapCustomerId, String period,String organizationId,String custType)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custname", custname);
		params.put("sapCustomerId", sapCustomerId);
		params.put("period", period);
		params.put("organizationId", organizationId);
		params.put("custType", custType);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getLimit()+bean.getOffset());
		return this.service.getArBalancesList(params);
	}
	
	/**
	 * 应收账龄list
	 * @param bean
	 * @param custname
	 * @param sapCustomerId
	 * @param orgid
	 * @param custType
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("arOverdueList")
	@ResponseBody
	public AjaxDTO getArOverdueList(@ModelAttribute AjaxDTO bean,String custname,
			String sapCustomerId,String orgid,String custType,HttpServletRequest request)
	   throws Exception{
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids = this.powerService.getUserStations(user,	userjobs, station);// 获取用户可以查看的岗位
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custname", custname);
		params.put("sapCustomerId", sapCustomerId);
		params.put("orgid", orgid);
		params.put("custType", custType);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getLimit()+bean.getOffset());
		params.put("stationids", stationids);
		return this.overdueservice.selectArOverdueList(params);
	}
	/**
	 * 应收账款本期发生明细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ARbalanceDetailslist")
	@ResponseBody
	public AjaxDTO  getARbalanceDetailslist(@ModelAttribute AjaxDTO bean,Long id) throws Exception{
		return this.service.getARbalanceDetailslist(bean,id);
	}
	
	/**
	 * 应收账款逾期明细
	 * @param merchId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("arOverdueDetailslist")
	@ResponseBody
	public AjaxDTO getArOverdueDetails(@ModelAttribute AjaxDTO bean,Long merchId,Long arPeriod) throws Exception{
		return this.overdueservice.selectArOverdueDetails(bean,merchId,arPeriod);
	}
	/**
	 * 判断当期时间所属月份是否时最新期间
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="balanceValidate",method=RequestMethod.POST)
	public @ResponseBody Integer balanceValidate() throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		String newperiod =sf.format(new Date());
		//获取当前期间
		String period = this.service.getMaxPeriod();
		return  StringUtils.equals(newperiod, period)?1:2;
	}
	
	/**
	 * 更新期间数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateByPeriod")
	@ResponseBody
	public String updateByPeriod() throws Exception{
		return this.service.updateByPeriod();
	}
	
	/**
	 * 更新指定客户应收账款数据
	 * @param merchCustId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateByMerchId")
	public @ResponseBody Map<String,Object> updateByMerchId(Long merchCustId) throws Exception{
		return this.service.updateByMerchId(merchCustId);
	}
	
	@RequestMapping("exportDetails")
	@ResponseBody
	public String exportDetails(Long merchId,Long arPeriod) throws Exception{
		
		return this.overdueservice.exportDetails(merchId,arPeriod);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("exportAll")
	@ResponseBody
	public String exportAll( HttpServletRequest request) throws Exception{
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids = this.powerService.getUserStations(user,	userjobs, station);// 获取用户可以查看的岗位
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("stationids", stationids);
		return this.overdueservice.exportAll(params);
	}
}
