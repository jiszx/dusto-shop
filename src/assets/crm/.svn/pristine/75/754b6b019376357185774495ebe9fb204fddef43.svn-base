package com.hhnz.promotion.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.promotion.service.PromotionUtilService;
import com.hhnz.util.AjaxDTO;

/**
 * 促销品管理公用方法
 * @author skevin
 *
 */
@Controller
@RequestMapping("promotion/util")
public class PromotionUtilController {
	@Resource
	private PromotionUtilService  service;
	
	/**
	 * 获取登陆用户销售组织
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value="org")
	public @ResponseBody AjaxDTO findorg(HttpServletRequest request)throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		String orgid ="";
		if(user.getOrganizationId().length()>5){			
			orgid =user.getOrganizationId().substring(0, 5);
		}else{
			orgid =user.getOrganizationId();
		}
		AjaxDTO dto  = this.service.findUserOrgInfo(orgid);
		return dto;
	}*/
	
	/**
	 * 获取登录用户的可查看库房信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("stores")
	public @ResponseBody AjaxDTO findstore(String orgid) throws Exception{
		AjaxDTO dto  = this.service.findUserStoresInfo(orgid);
		return dto;
	}
	
	/**
	 * 采购方信息
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("pur")
	public @ResponseBody AjaxDTO findpur(String orgid) throws Exception{
		AjaxDTO dto  = this.service.findUserPurInfo(orgid);
		return dto;
	}
	
	/**
	 * 获取促销品信息
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("promotion")
	public @ResponseBody AjaxDTO findpromotion(String orgid) throws Exception{
		AjaxDTO dto  = this.service.findUserpromotionInfo(orgid);
		return dto;
	}
	/**
	 * 出库，根据库房获取促销品
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("OutPromotion")
	public @ResponseBody AjaxDTO OutPromotion(Long storesId) throws Exception{
		AjaxDTO dto  = this.service.OutPromotion(storesId);
		return dto;
	}
	/**
	 * 促销品价格信息
	 * @param promotionid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("promotionPrice")
	public @ResponseBody String promotionPrice(Long promotionid) throws Exception{
		return  this.service.promotionPrice(promotionid);
	}
	
	/*@RequestMapping("promotionInv")
	public @ResponseBody String promotionInv(Long promotionId,Long storesId) throws Exception{
		return   this.service
	}*/
	/**
	 * 获取销售组织对应的有效库房
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("invstores")
	public @ResponseBody AjaxDTO getInvStoresInfo(String orgid) throws Exception{
		AjaxDTO dto = new AjaxDTO();
		dto = this.service.getInvStoresInfo(orgid);
		return dto;
	}
	
}
