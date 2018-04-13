package com.hhnz.organization.service;

import java.util.List;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.organization.model.CrmOrgArea;
import com.hhnz.organization.model.CrmOrganizationInvoices;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.pub.model.TArea;
import com.hhnz.util.AjaxDTO;

/**
 * Created by yang on 2016-7-6.
 */
public interface IorganizationService {
    List<CrmSalesOrganization> findAll()throws Exception;

    List<CrmSalesOrganization> findPid(String pid);

    Integer addOrg(CrmSalesOrganization model)throws Exception;

    Integer editOrg(CrmSalesOrganization model)throws Exception;

    Integer orgGrantArea(String id, List<String> auths)throws Exception;

    List<String> findArea(String id)throws Exception;

    List<CrmOrgArea> findOrgArea()throws Exception;

    List<CrmSalesOrganization> findByLevel(String leve)throws Exception;

    List<TArea> findAreas(String id, String filter, TEmployee user) throws RuntimeException;

	//List<CrmSalesOrganization> getcompanybyid(String orgid);

	/**
	 * 根据id获取有效销售组织
	 * @author: chaoyang.ren 
	 * @date:2016年8月23日  上午11:21:15
	 * @param id
	 * @return
	 */
	public CrmSalesOrganization findById(String id);
	
	/**
	 * 根据sapid获取有效销售组织
	 * @author: chaoyang.ren 
	 * @date:2016年11月16日  上午9:25:59
	 * @param id
	 * @return
	 */
	public CrmSalesOrganization findBySapId(String sapId);

	List<CrmSalesOrganization> findLessLevel(String level)throws Exception;

	AjaxDTO getInfoInvoices(AjaxDTO bean);

	Integer maintainInfoInvoices(CrmOrganizationInvoices model);
}
