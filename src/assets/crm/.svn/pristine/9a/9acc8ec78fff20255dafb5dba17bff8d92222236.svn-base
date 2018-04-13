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
import com.hhnz.promotion.model.CrmPromotionMaterial;
import com.hhnz.promotion.service.PromotionMaterialService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

/**
 * 促销品主档维护Controller
 * 
 * @author skevin
 *
 */
@Controller
@RequestMapping("promotion")
public class PromotionMaterialController {
	@Resource
	private PromotionMaterialService service;

	// 促销品库存查询页面跳转
	@RequestMapping("promotionNum.html")
	public String promotionNum() throws Exception {
		return "promotion/promotionNum";
	}

	// 促销品物料维护页面跳转
	@RequestMapping("promotionMaterial.html")
	public String promotionMaterial() throws Exception {
		return "promotion/promotionMaterial";
	}

	/**
	 * 促销品库存查询list
	 * 
	 * @param bean
	 * @param states
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("material/invlist")
	public @ResponseBody AjaxDTO NumlistPage(@ModelAttribute AjaxDTO bean,
			String name,String orgid,Long storesid, HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		orgid = StringUtils.isEmpty(orgid) ? (user.getOrganizationId().length() > 5 ? user
				.getOrganizationId().substring(0, 5) : user.getOrganizationId())
				: orgid;
		AjaxDTO dto = this.service.findPromotionNumList(name,orgid,storesid, bean);
		return dto;
	}

	/**
	 * 促销品物料list
	 * 
	 * @param bean
	 * @param states
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("material/list")
	public @ResponseBody AjaxDTO materialListPage(@ModelAttribute AjaxDTO bean,
			String name, String orgid, HttpServletRequest request)
			throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		// if(user.getOrganizationId().length()>5){
		// orgid
		// =StringUtils.isEmpty(orgid)?user.getOrganizationId().substring(0,
		// 5):orgid;
		// }else{
		// orgid =StringUtils.isEmpty(orgid)?user.getOrganizationId():orgid;
		// }
		orgid = StringUtils.isEmpty(orgid) ? (user.getOrganizationId().length() > 5 ? user
				.getOrganizationId().substring(0, 5) : user.getOrganizationId())
				: orgid;
		AjaxDTO dto = this.service.findmaterialList(name, orgid, bean);
		return dto;
	}

	/**
	 * 新增促销品物料
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "material/add", method = RequestMethod.POST)
	public @ResponseBody Integer addPromotionMaterial(
			@ModelAttribute CrmPromotionMaterial model,
			HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setCreateOid(user.getId());
		model.setCreateTs(new Date());
		model.setPrice(BigDecimalASME.multiply(model.getPrice()));
		return this.service.addPromotionMaterial(model);
	}

	/**
	 * 修改促销品物料
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "material/edit", method = RequestMethod.POST)
	public @ResponseBody Integer editPromotionMaterial(
			@ModelAttribute CrmPromotionMaterial model,
			HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setUpdateOid(user.getId());
		model.setUpdateTs(new Date());
		return this.service.editPromotionMaterial(model);
	}

	@RequestMapping(value = "material/del")
	public @ResponseBody Integer delmaterial(@RequestParam("id") Long id) {
		return this.service.delMaterial(id);
	}

}
