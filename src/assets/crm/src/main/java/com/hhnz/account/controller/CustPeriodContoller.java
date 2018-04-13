package com.hhnz.account.controller;

import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.account.model.CMerchBalancesV;
import com.hhnz.account.service.ICustPeriodService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;

/**
 * 客户资金对账Controller
 * @author hhnz-skevin
 *
 */
@Controller
@RequestMapping("account/period")
public class CustPeriodContoller {
	@Resource
	private ICustPeriodService custPeriodService;
	
	@Resource
	private IPowerOrgService  powerService;
	@RequestMapping("/custPeriod.html")
    public ModelAndView custperiod(Long custid, String orgid, String period,String accountType,Long id)throws Exception{
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("account/custPeriod");
    	mv.addObject("custid", custid);
    	mv.addObject("orgid", orgid);
    	mv.addObject("period", period);
    	mv.addObject("accountType", accountType);
    	CMerchBalancesV balance  = this.custPeriodService.getBalance(id);
    	mv.addObject("balance", balance);
    	return mv;
    }
	// 获取对账管理list
	@SuppressWarnings("unchecked")
	@RequestMapping("list")
	public @ResponseBody AjaxDTO listPage(@ModelAttribute AjaxDTO bean,String custname,String orgid,String accountType,String period,
			String custType,String sorgid,HttpServletRequest request)
			throws Exception {
		if(!StringUtils.isEmpty(custname)&&!custname.equals("null")){    		
    		custname = URLDecoder.decode(custname,"utf-8");
    	}
		Map<String,Object> params = new HashMap<String, Object>();
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		params.put("custname", custname);
		params.put("orgid", orgid);
		params.put("stationids", stationids);
		params.put("accountType", accountType);
		params.put("period", period);
		params.put("usertype", user.getUserType());
		params.put("merchid", user.getMerchId());
		params.put("sorgid", sorgid);
		params.put("custType", custType);
		AjaxDTO dto = this.custPeriodService.findCustPeriod(bean,params);
		return dto;
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public @ResponseBody AjaxDTO listDetail(
			@RequestParam(required = false, defaultValue = "10") int limit,
			@RequestParam(required = false, defaultValue = "0") int offset,
			Long custid, String orgid, String period,String accountType) {
		AjaxDTO dto = this.custPeriodService.findPeriodDetail(custid, orgid, period, limit, offset,accountType);
		return dto;
	}
	
	@RequestMapping("periodData")
	@ResponseBody
	public AjaxDTO periodData() throws Exception{
		AjaxDTO dto = new AjaxDTO();
		dto = this.custPeriodService.periodData();
		return dto;
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
		String period = this.custPeriodService.getMaxPeriod();
		return  StringUtils.equals(newperiod, period)?1:2;
	}
	
	/**
	 * 更新期间数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="upBalance",method=RequestMethod.POST)
	public @ResponseBody String upBalance() throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		String newperiod =sf.format(new Date());
		return this.custPeriodService.upBalance(newperiod);
	}
}
