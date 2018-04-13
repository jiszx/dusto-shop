package com.hhnz.combination.controller;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.combination.model.CrmMaterialPackageApply;
import com.hhnz.combination.model.CrmMaterialPackageHeader;
import com.hhnz.combination.service.CombinationApplyService;
import com.hhnz.combination.service.CombinationService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.util.AjaxDTO;

/**
 * 套餐申请Controller
 * @author hhnz-skevin
 *
 */
@RequestMapping("combinationApply")
@Controller
public class CombinationApplyController {
	
	@Resource
	private  CombinationApplyService service;
	
	@Resource
	private  CombinationService  combinationservice;
	/**
	 * 列表页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("index.html")
    public String indexPage()throws Exception{
        return "combination/applyIndex";
    }
	
	/**
	 * 套餐审批页面
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("Auditview.html")
	public ModelAndView auditView(Long id) throws Exception{
		ModelAndView mv = new ModelAndView("combination/auditView");
		CrmMaterialPackageApply apply = this.service.getApplyById(id);
		mv.addObject("a", apply);
		CrmMaterialPackageHeader header = this.combinationservice.getHeaderById(apply.getPackageId());
		mv.addObject("h", header);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	mv.addObject("bdate",sf.format(header.getbDate()));
    	mv.addObject("edate",sf.format(header.geteDate()));
		return mv;
	}
	/**
	 * 申请列表数据
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("applyList")
	@ResponseBody
	public AjaxDTO getApplyList(@ModelAttribute AjaxDTO bean) throws Exception{
		return this.service.getApplyList(bean);
	}
	
	/**
	 * 根据生意模式获取套餐数据
	 * @param modelType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("package")
	@ResponseBody
	public AjaxDTO getPackage(String modelType,String orgid) throws Exception{
		return this.service.getPachage(modelType,orgid);
	}
	
	
	/**
	 * 添加或者修改申请
	 * @param apply
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addOrUpdateApply")
	@ResponseBody
	public String addOrUpdateApply(@ModelAttribute CrmMaterialPackageApply apply,HttpServletRequest request) throws Exception{
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		return this.service.addOrUpdateApply(apply,user);
	}
	
	/**
	 * 删除申请
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("del")
	@ResponseBody
	public String delApply(Long id) throws Exception{
		return this.service.delApply(id);
	}
	
	/**
	 * 获取执行范围数据
	 * @param applyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rangeArea")
	@ResponseBody
	public AjaxDTO getRangeArea(Long applyId) throws Exception{
		return this.service.getRangeArea(applyId);
	}
	
	/**
	 * 提交审批
	 * @param applyId
	 * @param states
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("audit")
	@ResponseBody
	public String doAudit(Long applyId,String states,HttpServletRequest request) throws Exception{
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		return this.service.startProcess(applyId,states,user);
	}
	
	/**
	 * 获取审批通过后套餐对应的客户数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("CombinationMerchs")
	@ResponseBody
	public AjaxDTO getCombinationMerchs(AjaxDTO bean,Long id){
		return this.service.getCombinationMerchs(bean,id);
	}
	
	/**
	 * 套餐申请，移除套餐对应的客户
	 * @param lineId
	 * @param merchCustId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delCombinationMerch")
	@ResponseBody
	public String  delCombinationMerch(Long lineId,Long merchCustId,Long id) throws Exception{
		return this.service.delCombinationMerch(lineId,merchCustId,id);
	}
	
	/**
	 * 获取套餐申请不存在的客户
	 * @param bean
	 * @param id
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selectMerchs")
	@ResponseBody
	public AjaxDTO selectMerchs(AjaxDTO bean,Long id,String type) throws Exception{
		return this.service.selectMerchs(bean,id,type);
	}
	
	/**
	 * 套餐申请，新增客户
	 * @param merchids
	 * @param applyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addCombinationMerch")
	@ResponseBody
	public String addCombinationMerch(String merchids,Long applyId) throws Exception{
		return this.service.addCombinationMerch(merchids,applyId);
	}
}
