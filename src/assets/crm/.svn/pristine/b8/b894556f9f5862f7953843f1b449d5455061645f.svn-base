package com.hhnz.account.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.account.model.CMerchCustRebateV;
import com.hhnz.account.service.IRebateService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
/**
 * 客户合同返利
 * @author skevin
 *
 */
@Controller
@RequestMapping("account/rebate")
public class RebateController {
	@Resource
	private IRebateService rebateService;
	
	@Resource
	private IPowerOrgService  powerService;
	
	@RequestMapping("rebateDetails.html")
	public ModelAndView rebateDetails(Long id){
		ModelAndView mv = new ModelAndView("account/custRebateDetails");
		mv.addObject("id", id);
		CMerchCustRebateV rebate = this.rebateService.getRebateById(id);
		mv.addObject("rebate", rebate);
		/*String osName = System.getProperty("os.name", "");
		if (osName.startsWith("Windows")) {
		     Runtime.getRuntime().exec(new String [] {"cmd.exe", "/C", "start firefox.exe",url});
		}*/
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("list")
    public @ResponseBody AjaxDTO listPage(@ModelAttribute AjaxDTO bean,HttpServletRequest request) throws Exception {
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		AjaxDTO dto = this.rebateService.findRebate(stationids,bean);
        return dto;
    }
	
	@RequestMapping(value="orders", method = RequestMethod.GET)
	public @ResponseBody AjaxDTO listRebateOrders(
			@ModelAttribute AjaxDTO bean,
			Long id){
		return rebateService.findRebateOrderDetail(id,bean);
	}
}
