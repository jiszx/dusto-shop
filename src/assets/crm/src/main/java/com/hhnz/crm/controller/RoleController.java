package com.hhnz.crm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TRole;
import com.hhnz.crm.service.IRoleService;
import com.hhnz.security.SecurityMetadataSourceServiceImpl;
import com.hhnz.util.AjaxDTO;

/**
 * Created by yang on 2016-6-28.
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {

	@Autowired
	private IRoleService service;

	/**
	 * 角色首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index.jhtml", method = RequestMethod.GET)
	public String indexPage() throws Exception {
		return "system/role";
	}

	@RequestMapping("list")
	@ResponseBody
	public AjaxDTO listPage(@ModelAttribute AjaxDTO bean, String orgId) throws Exception {
		HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
		stringObjectHashMap.put("orgId", orgId);
		AjaxDTO dto = this.service.findRole(stringObjectHashMap, bean);
		return dto;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody Integer addRole(@ModelAttribute TRole model) throws Exception {
		return this.service.addRole(model);
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody Integer edit(@ModelAttribute TRole model) throws Exception {
		if(model.getId().longValue() == 1L){
			return 0;
		}else{
			model.setUpdateTs(new Date());
			return this.service.updateRole(model);
		}
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody Integer del(@RequestParam("id") Long id) throws Exception {
		if(id.longValue() == 1L){
			return 0;
		}else{
			SecurityMetadataSourceServiceImpl.flag.set(true);
			return this.service.delRole(id);
		}
	}

	@RequestMapping(value = "getAuth")
	@ResponseBody
	public List<Long> findRoleAuth(@RequestParam("id") Long id) throws Exception {
		List<Long> ids = this.service.findRoleAuths(id);
		return ids;
	}

	@RequestMapping(value = "grant", method = RequestMethod.POST)
	public @ResponseBody Integer grant(@RequestParam("roleId") Long id, @RequestParam("auths") List<Integer> auths)
			throws Exception {
		SecurityMetadataSourceServiceImpl.flag.set(true);
		return this.service.grantRole(id, auths);
	}
}
