package com.hhnz.account.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.account.service.IAccountUtilService;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("account/util")
public class AccountUtilController {
	
	
	@Resource
	private IAccountUtilService  utilservice;
	
	@Resource
	private IorganizationService orgservice;
	/**
	 * 获取登录用户可进行账户调整的客户清单
	 * @param request
	 * @return
	 */
	@RequestMapping("customer")
	@ResponseBody
	public AjaxDTO getadjustCustomer(HttpServletRequest request,String orgid,Long amt){
		//通过销售组织下的岗位查询客户
		AjaxDTO dto = new AjaxDTO();
		dto = this.utilservice.getAdjustCutomer(orgid,!StringUtils.isEmpty(amt)?amt:0);
		return dto;
	}

	@RequestMapping("customerByType")
	@ResponseBody
	public AjaxDTO getadjustCustomerByType(String orgid,String type){
		//通过销售组织下的岗位查询客户
		AjaxDTO dto = new AjaxDTO();
		dto = this.utilservice.getAdjustCutomerByType(orgid,type);
		return dto;
	}
	
	/**
	 * 获取登录能调整的销售组织
	 * @param request
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	@RequestMapping("adjustorg")
	@ResponseBody
	public AjaxDTO getAdjustOrg(HttpServletRequest request){
		AjaxDTO dto =new AjaxDTO();
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);
		List<CrmSalesOrganization> list = new ArrayList<CrmSalesOrganization>();
		for(UserJobs job:userjobs){
			List<CrmSalesOrganization> orglist =this.orgservice.getcompanybyid(job.getOrgid().length()>5?job.getOrgid().substring(0, 5):job.getOrgid());
			for(CrmSalesOrganization org:orglist){
				list.add(org);
			}
		}
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}*/
	
}
