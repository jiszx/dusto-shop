package com.hhnz.salepolicy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.process.service.IProcessService;
import com.hhnz.pub.model.PowerOrg;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.salepolicy.model.OmPolicyHeaders;
import com.hhnz.salepolicy.model.OmPolicyLines;
import com.hhnz.salepolicy.model.PolicyCustArea;
//import com.hhnz.salepolicy.model.PolicyOrgModel;
import com.hhnz.salepolicy.service.SalePolicyService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

/**
 * Created by yang on 2016-7-6.
 */
@Controller
@RequestMapping("/salePolicy")
public class SalePolicyController {
	@Resource
	private SalePolicyService service;
	
	@Resource
	private IPowerOrgService  orgservice;
	@Resource
	private IProcessService processService;
	@RequestMapping("/index.html")
	public String indexPage() throws Exception {
		return "salepolicy/index";
	}

	@RequestMapping("/addPolicy.html")
	public String addPolicy() throws Exception {
		return "salepolicy/addPolicy";
	}

	@RequestMapping("/policyDetails.html")
	public ModelAndView policyDetails(Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		//mv= this.service.getpolicyBYid(id);
		Map<String,Object> map = this.service.getpolicyBYid(id);
		mv.setViewName("salepolicy/policyDetails");
		mv.addObject("policy", map.get("policy"));
		mv.addObject("bdate", map.get("bdate"));
		mv.addObject("eDate", map.get("eDate"));
		mv.addObject("policyType", map.get("policyType"));
		mv.addObject("dept", map.get("dept"));
		return mv;
	}
	@RequestMapping("policyEdit.html")
	public ModelAndView policyEdit(Long id)throws Exception{
		ModelAndView mv = new ModelAndView();
		//mv= this.service.getpolicyBYid(id);
		Map<String,Object> map = this.service.getpolicyBYid(id);
		mv.setViewName("salepolicy/policyEdit");
		mv.addObject("policy", map.get("policy"));
		mv.addObject("bdate", map.get("bdate"));
		mv.addObject("eDate", map.get("eDate"));
		mv.addObject("policyType", map.get("policyType"));
		mv.addObject("dept", map.get("dept"));
		return mv;
	}
	
	@RequestMapping("policyWriteOff.html")
	public ModelAndView policyWriteOff(Long id)throws Exception{
		ModelAndView mv = new ModelAndView("salepolicy/policyWriteOffDetails");
		Map<String,Object> map = this.service.getpolicyBYid(id);
		mv.addObject("policy", map.get("policy"));
		mv.addObject("bdate", map.get("bdate"));
		mv.addObject("eDate", map.get("eDate"));
		mv.addObject("policyType", map.get("policyType"));
		mv.addObject("dept", map.get("dept"));
		return mv;
	}
	@RequestMapping("policyLines")
	@ResponseBody
	public AjaxDTO getpolicyLines(Long id) throws Exception{
		AjaxDTO dto = new AjaxDTO();
		dto = this.service.getPolicyLines(id);
		return dto;
	}
	/**
	 * 获取销售政策list
	 * @param bean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("policylist")
	public @ResponseBody AjaxDTO listPage(@ModelAttribute AjaxDTO bean,
			HttpServletRequest request) throws Exception {
		AjaxDTO dto = new AjaxDTO();
		//TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<UserJobs> jobs =(List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);
		AjaxDTO orgdto = this.orgservice.powerOrg(jobs);
		List<PowerOrg> orgs= orgdto.getRows();
		List<String> orgids = new ArrayList<String>();
		if(orgs !=null && orgs.size()>0){
			for(PowerOrg org:orgs){
				orgids.add(org.getOrgid());
			}
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit()+bean.getOffset());
		params.put("orgid", orgids);
		dto = this.service.getPolicyList(params);
		return dto;
		
	}
	@RequestMapping("writeofflist")
	@ResponseBody
	public AjaxDTO  writeofflist(@ModelAttribute AjaxDTO dto,Long policyid)throws Exception{
		//AjaxDTO dto = new AjaxDTO();
		dto = this.service.writeofflist(policyid,dto);
		return dto;
	}
	/**
	 * 获取销售政策类型
	 * 
	 * @return
	 */
	@RequestMapping("policytype")
	@ResponseBody
	public AjaxDTO getPolicyType() {
		return this.service.getpolicyType();
	}

	/**
	 * 获取登录用户职位对应的销售组织
	 * 
	 * @param request
	 * @return
	 */
	/*@RequestMapping("policyorg")
	@ResponseBody
	public AjaxDTO getPolicyOrg(HttpServletRequest request) {
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		return this.service.getpolicyorg(user.getId());
	}*/

	/*
	 * 获取销售组织下的物料清单
	 */
	@RequestMapping("policymaterial")
	@ResponseBody
	public AjaxDTO getPolicyMaterial(String orgid) {
		return this.service.getPolicyMaterial(orgid);
	}
	/**
	 * 添加销售政策头信息
	 * @param policyheader
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "header/add", method = RequestMethod.POST)
	public @ResponseBody Long addPolicyHeader(
			@ModelAttribute OmPolicyHeaders policyheader,
			HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		policyheader.setCreateTs(new Date());
		policyheader.setCreateOid(user.getId());
		policyheader.setStates("1");
		policyheader.setDept(policyheader.getOrganizationId());
		policyheader.setAmt(BigDecimalASME.multiply(policyheader.getAmt()));
	    this.service.addHeader(policyheader);
	    return policyheader.getId();
	}
	/**
	 * 添加政策行信息
	 * @param headerid
	 * @param lines
	 * @return
	 */
	@RequestMapping(value="lines/add",method=RequestMethod.POST)
	public @ResponseBody String addPolicyLines(Long headerid,String lines,String orgids,String custs) throws Exception{
		return this.service.addLine(headerid,lines,orgids,custs);
	}
	/**
	 * 添加政策执行范围
	 * @param headerid
	 * @param areas
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="area/add",method=RequestMethod.POST)
	public @ResponseBody String addPolicyArea(Long headerid,String areas) throws Exception{
		return "500";
	}
	
	@RequestMapping("submit")
	public @ResponseBody String submit(String states,Long headerid,HttpServletRequest request) throws Exception{
		if(StringUtils.equals("2", states)){
			TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("key", headerid);
			param.put("startUser",user.getLoginName());
			param.put("groupType", "admin");
			param.put("name", "销售政策编号："+headerid);
			param.put("level", "1");
			param.put("viewPage","salePolicy/policyDetails.html?id="+headerid);
			String processId = this.processService.startProcess(param, "om_salepolicy_apply",
					user.getLoginName());
			OmPolicyHeaders  policy = this.service.getpolicyById(headerid);
			policy.setProcessId(processId);
			this.service.updatePolicyHeader(policy);
		}
		return this.service.submit(states,headerid);
	}
	
	@RequestMapping("delpolicy")
	public  @ResponseBody String delpolicy(Long headerid) throws Exception{
		return this.service.delpolicy(headerid);
	}
	
	@RequestMapping("delPolicyLine")
	@ResponseBody
	public String delPolicyLine(Long id) throws Exception{
		int i = this.service.delPolicyLine(id);
		return i==1?"200":"500";
	}
	
	@RequestMapping("editPolicyLine")
	@ResponseBody
	public String editPolicyLine(@ModelAttribute OmPolicyLines line) throws Exception{
		return this.service.editPolicyLine(line)==1?"200":"500";
	}
	
	@RequestMapping("areaList")
	@ResponseBody
	public List<CrmSalesOrganization> getSalesArea(String orgid) throws Exception{
		return this.service.getSalepolicyArea(orgid);
	}
	
	@RequestMapping("areaCust")
	@ResponseBody
	public List<PolicyCustArea> getAreaCust(String orgids) throws Exception{
		return this.service.getAreacCust(orgids);
	}
	
	@RequestMapping("edit/policyarea")
	@ResponseBody
	public AjaxDTO getpolicyArea(Long id) throws Exception{
		AjaxDTO dto  = new AjaxDTO();
		List<CrmSalesOrganization> list= this.service.getPolicyArea(id);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	
	@RequestMapping("edit/policycustarea")
	@ResponseBody
	public AjaxDTO getPolicyCustArea(Long id) throws Exception{
		AjaxDTO dto  = new AjaxDTO();
		List<CMerchCustBase> list= this.service.getPolicyCustArea(id);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
}
