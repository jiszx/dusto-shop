package com.hhnz.salepolicy.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.salepolicy.model.OmPolicyType;
import com.hhnz.salepolicy.service.SalePolicyService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("/salepolicy/policyType")
public class SalePolicyTypeController {
	@Resource
	private SalePolicyService service;

	@RequestMapping("/policytype.html")
	public String policytype() throws Exception {
		return "salepolicy/policytype";
	}

	@RequestMapping("applyTypeList")
	public @ResponseBody AjaxDTO listPage(@ModelAttribute AjaxDTO bean,
			String states) throws Exception {
		AjaxDTO dto = this.service.findPolicyTypeList(states, bean);
		return dto;
	}

	@RequestMapping(value = "addPolicyType", method = RequestMethod.POST)
	public @ResponseBody Integer addPolicyType(
			@ModelAttribute OmPolicyType model, HttpServletRequest request)
			throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setCreateOid(user.getId());
		model.setCreateTs(new Date());
		//model.setOrganizationId(user.getOrganizationId());
		model.setStates("1");
		return this.service.addPolicyType(model);
	}
	@RequestMapping(value = "editPolicyType",method = RequestMethod.POST)
    public @ResponseBody Integer  editPolicyType(@ModelAttribute OmPolicyType model,HttpServletRequest request) throws Exception{
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setUpdateOid(user.getId());
		model.setUpdateTs(new Date());
		return this.service.editPolicyType(model);
    }
	@RequestMapping(value = "delPolicyType", method = RequestMethod.POST)
	public @ResponseBody Integer delPolicyType(@RequestParam("id") Long id)
			throws Exception {
		return this.service.delPolicyType(id);
	}
}
