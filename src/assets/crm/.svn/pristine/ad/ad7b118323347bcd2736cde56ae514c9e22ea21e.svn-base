package com.hhnz.promotion.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.process.service.IProcessService;
import com.hhnz.promotion.model.CrmPromotionLog;
import com.hhnz.promotion.model.CrmPromotionLogV;
import com.hhnz.promotion.service.PromotionInvService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

/**
 * 促销品出入库Controller
 * 
 * @author skevin
 *
 */
@Controller
@RequestMapping("promotion")
public class PromotionInvController {

	@Resource
	private PromotionInvService service;
	
	@Resource
	private IProcessService processService;
	/**
	 * 入库页面跳转
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("promotionIN.html")
	public String promotionIN() throws Exception {
		return "promotion/promotionIn";
	}
	
	/**
	 * 出库页面跳转
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("promotionOUT.html")
	public String promotionOUT() throws Exception {
		return "promotion/promotionOut";
	}
    @RequestMapping("detail{id}.html")
    public ModelAndView policyDetial(@PathVariable(value="id")Long id) throws Exception{
    	ModelAndView mv = new ModelAndView("promotion/detail");
    	CrmPromotionLogV log = this.service.findLogVByid(id);
    	mv.addObject("log", log);
    	return mv;
    }
	/**
	 * 促销品出入库查询
	 * 
	 * @param bean
	 * @param type
	 *            1：入库，2出库
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("inv/list")
	public @ResponseBody AjaxDTO applylistPage(@ModelAttribute AjaxDTO bean,
			String type, String orgid, String name, String states,
			String purid, String stores, HttpServletRequest request)
			throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		orgid = StringUtils.isEmpty(orgid) ? (user.getOrganizationId().length() > 5 ? user
				.getOrganizationId().substring(0, 5) : user.getOrganizationId())
				: orgid;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("orgid", orgid);
		params.put("type", type);
		if(!StringUtils.isEmpty(name) && !"null".equals(name)){
			name=URLDecoder.decode(name,"utf-8");
		}
		params.put("name", name);
		params.put("states", states);
		params.put("stores", stores);
		params.put("purid", purid);
		AjaxDTO dto = this.service.findApplylist(params, bean);
		return dto;
	}

	/**
	 * 促销品入库添加
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "inv/addApply", method = RequestMethod.POST)
	public @ResponseBody Integer addPromotionApply(
			@ModelAttribute CrmPromotionLog model, HttpServletRequest request)
			throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		// model.setOrganizationId(user.getOrganizationId());
		model.setCreateOid(user.getId());
		model.setCreateTs(new Date());
		model.setPrice(BigDecimalASME.multiply(model.getPrice()));
		//model.setType("1");
		model.setStates("1");
		return this.service.addPromotionApply(model);
	}

	@RequestMapping(value = "inv/editApply", method = RequestMethod.POST)
	public @ResponseBody Integer editPromotionApply(
			@ModelAttribute CrmPromotionLog model, HttpServletRequest request)
			throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setUpdateOid(user.getId());
		model.setUpdateTs(new Date());
		model.setPrice(BigDecimalASME.multiply(model.getPrice()));
		return this.service.editPromotionApply(model);
	}

	@RequestMapping(value = "inv/delApply")
	public @ResponseBody Integer delapply(@RequestParam("id") Long id) {
		return this.service.delApply(id);
	}
	
	@RequestMapping("inv/updateStates")
	public @ResponseBody String updateStates(Long id,String states,HttpServletRequest request) throws Exception{
		if("2".equals(states)){
			TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("key", id);
			param.put("startUser",user.getLoginName());
			param.put("groupType", "admin");
			param.put("level", "1");
			param.put("name", "促销品入库："+id);
			param.put("viewPage","promotion/detail"+id+".html");
			String processId = this.processService.startProcess(param, "crm_promotion_in",
					user.getLoginName());
			CrmPromotionLog log = this.service.findLogByid(id);
			log.setProcessId(processId);
			this.service.editPromotionApply(log);
		}
		return this.service.updateStates(id,states)==1?"200":"500";
	}
	
	@RequestMapping("inv/OutupdateStates")
	public @ResponseBody String OutupdateStates(Long id,String states,HttpServletRequest request) throws Exception{
		if("2".equals(states)){
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", id);
		param.put("startUser",user.getLoginName());
		param.put("groupType", "admin");
		param.put("name", "促销品出库："+id);
		param.put("level", "1");
		param.put("viewPage","promotion/detail"+id+".html");
		String processId = this.processService.startProcess(param, "crm_promotion_out",
				"admin");
		CrmPromotionLog log = this.service.findLogByid(id);
		log.setProcessId(processId);
		this.service.editPromotionApply(log);
		}
		return this.service.OutupdateStates(id,states)==1?"200":"500";
	}
}
