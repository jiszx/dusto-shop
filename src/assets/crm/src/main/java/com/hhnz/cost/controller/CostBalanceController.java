package com.hhnz.cost.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.cost.service.ICostBalanceService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("costBalance")
public class CostBalanceController {
	@Resource
	private ICostBalanceService service;

	@RequestMapping("list")
	public @ResponseBody AjaxDTO listPage(@ModelAttribute AjaxDTO bean,
			String regionId, String organizationId, String costTypeid)
			throws Exception {
		
		AjaxDTO dto = this.service.balancelist(bean,regionId,organizationId,costTypeid);
		return dto;
	}

	@RequestMapping("DetailList")
	@ResponseBody
	public AjaxDTO DetailList(@ModelAttribute AjaxDTO bean, Long id)
			throws Exception {
		bean = this.service.getDetailList(bean, id);
		return bean;
	}

	@RequestMapping("balanceValidate")
	public @ResponseBody Integer balanceValidate() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		String newperiod = sf.format(new Date());
		// 获取当前期间
		String period = this.service.getMaxPeriod();
		return StringUtils.equals(newperiod, period) ? 1 : 2;
	}

	/**
	 * 更新期间数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "upBalance", method = RequestMethod.POST)
	public @ResponseBody String upBalance(String period) throws Exception {
		return this.service.upBalance(period);
	}
}
