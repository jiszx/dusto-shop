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
import com.hhnz.promotion.model.CrmPromotionPur;
import com.hhnz.promotion.service.PromotionPURService;
import com.hhnz.util.AjaxDTO;

/**
 * 促销品采购方维护Controller
 * 
 * @author skevin
 *
 */
@Controller
@RequestMapping("promotion")
public class PromotionPURController {
	@Resource
	private PromotionPURService service;

	// 促销品库存查询页面跳转
	@RequestMapping("promotionPUR.html")
	public String promotionPUR() throws Exception {
		return "promotion/promotionPUR";
	}
	
	/**
	 * 获取促销品采购方list
	 * @param bean
	 * @param states
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("Pur/list")
	public @ResponseBody AjaxDTO purchaserlistPage(@ModelAttribute AjaxDTO bean,
			String name,String orgid,HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		orgid = StringUtils.isEmpty(orgid) ? (user.getOrganizationId().length() > 5 ? user
				.getOrganizationId().substring(0, 5) : user.getOrganizationId())
				: orgid;
		AjaxDTO dto = this.service.findPromotionPurchaserList(name,orgid, bean);
		return dto;
	}
	
	/**
	 * 添加促销品库房信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="Pur/add",method=RequestMethod.POST)
	public @ResponseBody Integer addPromotionPurchaser(@ModelAttribute
			CrmPromotionPur model ,HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setCreateOid(user.getId());
		model.setCreateTs(new Date());
		return this.service.addPromotionPurchaser(model);
	}
	
	/**
	 * 修改促销品库房信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "Pur/edit", method = RequestMethod.POST)
	public @ResponseBody Integer editPromotionPurchaser(
			@ModelAttribute CrmPromotionPur model,
			HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setUpdateOid(user.getId());
		model.setUpdateTs(new Date());
		return this.service.editPromotionPurchaser(model);
	}
	
	/**
	 * 删除促销品库房信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="Pur/del",method=RequestMethod.POST)
	public @ResponseBody Integer delPurchaser(@RequestParam("id") Long id){
		return this.service.delPurchaser(id);
	}
}
