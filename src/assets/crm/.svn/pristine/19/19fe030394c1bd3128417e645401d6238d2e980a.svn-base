package com.hhnz.pub.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;

/**
 * 用户可查看的销售组织Controller
 * @author hhnz-skevin
 *
 */
@Controller
@RequestMapping("org")
public class OrgPowerController {
	@Resource
	private IPowerOrgService service;
	@SuppressWarnings("unchecked")
	@RequestMapping("powerOrg")
	public @ResponseBody AjaxDTO powerOrg(HttpServletRequest request) throws Exception{
		List<UserJobs> jobs =(List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);
		AjaxDTO dto = new AjaxDTO();
		dto = this.service.powerOrg(jobs);
		return dto;
	}
}	
