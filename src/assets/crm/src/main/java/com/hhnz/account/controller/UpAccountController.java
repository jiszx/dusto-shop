package com.hhnz.account.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.account.service.IUpAccountService;
import com.hhnz.account.validator.UpAccountValidator;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

/**
 * 客户资金上账controller
 * 
 * @author skevin
 *
 */
@Controller
@RequestMapping("account/upaccount")
public class UpAccountController {
	@Autowired
	private IUpAccountService service;

	@Resource
	private UpAccountValidator validate;
	
	@Resource
	private IPowerOrgService  powerService;
	
	/**
	 * 银行到款录入页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/upAccount.html")
	public String upAccount() throws Exception {
		return "account/upAccount";
	}

	/**
	 * 资金确认页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/salesUpList.html")
	public String saleUp() throws Exception {
		return "account/salesUpList";
	}

	/**
	 * 资金上账页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accountConfirm.html")
	public String accountConfirm() throws Exception {
		return "account/accountConfirm";
	}

	@RequestMapping("/accountSearch.html")
	public String accountSearch() throws Exception {
		return "account/accountSearch";
	}

	/**
	 * 银行到款录入添加
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> addaccount(@ModelAttribute CMerchUpaccount model,
			HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setCreateOid(user.getId());
		model.setCreateTs(new Date());
		model.setUpdateTs(new Date());
		model.setUpdateOid(user.getId());
		model.setPayAmt(BigDecimalASME.multiply(model.getPayAmt()));
		model.setStates("1");
		model.setAllocationAmt(BigDecimal.ZERO);
		model.setReceviceAmt(BigDecimal.ZERO);
		model.setUnreceviceAmt(model.getPayAmt());
		model.setMerchCusId(StringUtils.isEmpty(model.getMerchCusId())?null:(model.getMerchCusId().equals(Long.parseLong("0"))?null:model.getMerchCusId()));
		//数据验证
		Map<String,Object>  result = new  HashMap<String,Object>();
		String validator = this.validate.validateAdd(model);
		if(!("validated").equals(validator)){
			result.put("type","500");
			result.put("code", validator);
			return result;
		}
		this.service.addupaccount(model);
		result.put("type","200");
		result.put("code", model.getId());
		return result;
	}

	/**
	 * 银行到款录入修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> editaccount(
			@ModelAttribute CMerchUpaccount model, HttpServletRequest request)
			throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setUpdateOid(user.getId());
		model.setUpdateTs(new Date());
		model.setPayAmt(BigDecimalASME.multiply(model.getPayAmt()));
		model.setMerchCusId(StringUtils.isEmpty(model.getMerchCusId())?null:(model.getMerchCusId().equals(Long.parseLong("0"))?null:model.getMerchCusId()));
		Map<String,Object>  result = new HashMap<String,Object>();
		String validator = this.validate.validateEdit(model);
		if(!("validated").equals(validator)){
			result.put("type","500");
			result.put("code", validate);
			return result;
		}
	    this.service.updateupaccount(model);
	    result.put("type","200");
		result.put("code", model.getId());
		return result;
	}

	/**
	 * 销售资金确认
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "salesConfirm", method = RequestMethod.POST)
	public @ResponseBody Integer salesconfirm(
			@ModelAttribute CMerchUpaccount model, HttpServletRequest request)
			throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setUpdateOid(user.getId());
		model.setUpdateTs(new Date());
		model.setSalesrepId(user.getId());
		model.setSalesrepDate(new Date());
		model.setPayAmt(BigDecimalASME.multiply(model.getPayAmt()));
		model.setMerchCusId(StringUtils.isEmpty(model.getMerchCusId())?null:(model.getMerchCusId().equals(Long.parseLong("0"))?null:model.getMerchCusId()));
		return this.service.updateupaccount(model);
	}

	/**
	 * 银行到款录入list
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("inputList")
	public @ResponseBody AjaxDTO inputList(@ModelAttribute AjaxDTO bean,
			CMerchUpaccount upaccount)
			throws Exception {
		AjaxDTO dto = this.service.findInputAccountList(upaccount, bean);
		return dto;
	}

	/**
	 * 资金确认list
	 */
	@RequestMapping("salesAccountList")
	public @ResponseBody AjaxDTO salesList(@ModelAttribute AjaxDTO bean,
			String payName, String payBankNo, String orgid,
			HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		AjaxDTO dto = new AjaxDTO();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", user.getId());
		params.put("payName", StringUtils.isEmpty(payName) ? null : payName);
		params.put("payBankNo", StringUtils.isEmpty(payBankNo) ? null
				: payBankNo);
		params.put("orgid", StringUtils.isEmpty(orgid) ? null : orgid);
		dto = this.service.getSalesAccountList(params, bean);
		return dto;
	}

	/**
	 * 资金上账List
	 * 
	 */
	@RequestMapping("upAccountList")
	public @ResponseBody AjaxDTO upAccountList(@ModelAttribute AjaxDTO bean,
			CMerchUpaccount upaccount) throws Exception {
		AjaxDTO dto = this.service.findUpAccountList(upaccount, bean);
		return dto;
	}

	/**
	 * 资金上账查询
	 * 
	 * @param bean
	 * @param upaccount
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("upAccountsearchList")
	public @ResponseBody AjaxDTO Listpage(@ModelAttribute AjaxDTO bean,
			CMerchUpaccount upaccount) throws Exception {
		AjaxDTO dto = this.service.findAllList(upaccount, bean);
		return dto;
	}

	/**
	 * 银行到款录入删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody Integer del(@RequestParam("id") Long id)
			throws Exception {
		return this.service.delUpheader(id);
	}

	// 提交
	@ResponseBody
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	public Map<String, Object> censor(Long id, String states){
		Map<String, Object> result = this.service.censor(id, states);
		if("500".equals(result.get("type"))){
			//记录错误信息
			this.service.updateErrorMsg(id,(String)result.get("msg"));

		}
		return result;
	}

	/*
	 * 
	 * 获取销售人员对应的客户
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("accountcustomer")
	@ResponseBody
	public AjaxDTO getaccountCustomer(String orgid, HttpServletRequest request) {
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		AjaxDTO dto = new AjaxDTO();
		dto = this.service.getaccountCustomer(orgid, stationids);
		return dto;
	}
	
	@RequestMapping("getSapMsg")
	@ResponseBody
	public String getSapMsg(Long id){
		return this.service.getSapMsg(id);
	}
	
	/**
	 * 仓储服务商打款验证
	 * @param id
	 * @param merchCustId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("validateStorage")
	public @ResponseBody Map<String,Object>  validateStorage(Long id,Long merchCustId) throws Exception{
		return this.service.validateStorage(id,merchCustId);
	}
}
