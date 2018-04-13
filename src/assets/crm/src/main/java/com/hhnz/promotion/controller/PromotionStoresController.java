package com.hhnz.promotion.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.promotion.model.CrmPromotionStores;
import com.hhnz.promotion.service.PromotionStoresService;
import com.hhnz.util.AjaxDTO;

/**
 * 促销品库房维护controller
 * @author skevin
 *
 */
@Controller
@RequestMapping("promotion")
public class PromotionStoresController {
	
	@Resource
	private PromotionStoresService service;
	
	/**
	 * 促销品库房管理页面跳转
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("promotionStores.html")
	public String promotionNum() throws Exception {
		return "promotion/promotionStores";
	}
	/**
	 * 获取促销品库房list
	 * @param bean
	 * @param states
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("stores/list")
	public @ResponseBody AjaxDTO storeslistPage(@ModelAttribute AjaxDTO bean,
			String name,String orgid,HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		orgid = StringUtils.isEmpty(orgid) ? (user.getOrganizationId().length() > 5 ? user
				.getOrganizationId().substring(0, 5) : user.getOrganizationId())
				: orgid;
		AjaxDTO dto = this.service.findPromotionStoresList(name,orgid, bean);
		return dto;
	}
	
	/**
	 * 添加促销品库房信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="stores/add",method=RequestMethod.POST)
	public @ResponseBody Integer addPromotionStores(@ModelAttribute
			CrmPromotionStores model ,HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setCreateOid(user.getId());
		model.setCreateTs(new Date());
		return this.service.addPromotionStores(model);
	}
	
	/**
	 * 修改促销品库房信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "stores/edit", method = RequestMethod.POST)
	public @ResponseBody Integer editPromotionStores(
			@ModelAttribute CrmPromotionStores model,
			HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setUpdateOid(user.getId());
		model.setUpdateTs(new Date());
		return this.service.editPromotionStores(model);
	}
	
	/**
	 * 删除促销品库房信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="stores/del",method=RequestMethod.POST)
	public @ResponseBody Integer delstores(@RequestParam("id") Long id){
		return this.service.delstores(id);
	}
	
}
