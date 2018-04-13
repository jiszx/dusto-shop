package com.hhnz.customerInv.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customerInv.model.CMerchCustProductAdjust;
import com.hhnz.customerInv.service.CustomerInvAdjustService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;

/**
 * 客户库存调整Controller
 * @author hhnz-skevin 2016-12-09
 *
 */
@RequestMapping("customerInvAdjust")
@Controller
public class CustomerInvAdjustController {
	
	@Resource
	private  CustomerInvAdjustService  adjustservice;
	
	@Resource
	private IPowerOrgService  powerService;
	
	@RequestMapping("customerInvAdjust.html")
	public String customerInvAdjust(){
		return "customerInv/customerInvAdjust";
	}
	
	@RequestMapping("audit.html")
	@ResponseBody
	public  ModelAndView adjustAuditView(Long id) throws Exception{
		ModelAndView mv = new ModelAndView("customerInv/adjustAudit");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("begin", 0);
		params.put("end", 10);
		params.put("adjustId", id);
		AjaxDTO dto = this.adjustservice.selectAdjustList(params);
		mv.addObject("adjust", dto.getRows().get(0));
    	return mv;
	}
	/**
	 * 库存调整LIST
	 * @param bean
	 * @param custname
	 * @param smaterialId
	 * @param ssku
	 * @param sorgid
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("adjustList")
	@ResponseBody
	public  AjaxDTO  getadjustList(@ModelAttribute AjaxDTO bean,String custname,String smaterialId,String ssku,String sorgid,HttpServletRequest request) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		params.put("materialId", smaterialId);
		params.put("sku", ssku);
		params.put("orgid", sorgid);
		params.put("custname", custname);
		params.put("merchid", "1".equals(user.getUserType())?user.getMerchId():null);
		params.put("stationids", stationids);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit());
		return this.adjustservice.selectAdjustList(params);
	}
	
	

	/**
	 * 获取调整客户
	 * @param orgid 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("customer")
	@ResponseBody
	public AjaxDTO  getCustomer(String orgid,String invValidate) throws Exception{
		return this.adjustservice.getCustomer(orgid,invValidate);
	}
	
	
	/**
	 * 获取客户对应的物料
	 * @param orgid
	 * @param merchId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getmaterials")
	@ResponseBody
	public  AjaxDTO getmaterials(String orgid,String merchId,String type) throws Exception{
		return this.adjustservice.getmaterials(orgid,merchId,type);
	}
	
	/**
	 * 调整添加
	 * @param adjust 添加的model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	@ResponseBody
	public Integer doAdd(@ModelAttribute CMerchCustProductAdjust  adjust,HttpServletRequest request) throws Exception{
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		adjust.setCreateTs(new Date());
		adjust.setCreateOid(user.getId());
		adjust.setUpdateOid(user.getId());
		adjust.setUpdateTs(new Date());
		adjust.setStates("1");
		return this.adjustservice.doAdd(adjust);
	}
	
	/**
	 * 库存调整修改
	 * @param adjust 修改model
	 * @param request 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@ResponseBody
	public Integer doEdit(@ModelAttribute CMerchCustProductAdjust  adjust,HttpServletRequest request) throws Exception{
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		adjust.setUpdateOid(user.getId());
		adjust.setUpdateTs(new Date());
		return this.adjustservice.doEdit(adjust);
	}
	
	/**
	 * 库存调整删除
	 * @param id 调整ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("del")
	@ResponseBody
	public String doDel(Long id) throws Exception{
		return this.adjustservice.doDel(id);
	}
	/**
	 * 获取库存数量
	 * @param merchId 客户ID
	 * @param materialId 物料ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("invNum")
	@ResponseBody
	public String  getInvNum(String merchId,String materialId) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("merchId", merchId);
		params.put("materialId", materialId);
		return this.adjustservice.selectInvNum(params);
	}
	
	/**
	 * 库存调整提交审批
	 * @param id 库存调整ID
	 * @param states 状态
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("audit")
	@ResponseBody
	public  String doAudit(Long id,String states,HttpServletRequest request) throws Exception{
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		return this.adjustservice.doAudit(id,user,states);
	}
	
}
