package com.hhnz.salepolicy.service;

import java.util.List;
import java.util.Map;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.salepolicy.model.OmPolicyHeaders;
import com.hhnz.salepolicy.model.OmPolicyLines;
import com.hhnz.salepolicy.model.OmPolicyType;
import com.hhnz.salepolicy.model.PolicyCustArea;
import com.hhnz.salepolicy.model.PolicySearchModel;
import com.hhnz.util.AjaxDTO;

public interface SalePolicyService {

	AjaxDTO findPolicyTypeList(String states, AjaxDTO bean);

	Integer addPolicyType(OmPolicyType model);

	Integer delPolicyType(Long id);

	Integer editPolicyType(OmPolicyType model);

	AjaxDTO getpolicyType();

	//AjaxDTO getpolicyorg(Long id);

	AjaxDTO getPolicyMaterial(String orgid);

	Integer addHeader(OmPolicyHeaders policyheader);

	// Integer addLine(OmPolicyLines line);

	AjaxDTO getPolicyList(Map<String, Object> params);

	String submit(String states, Long headerid);

	String delpolicy(Long headerid);

	Map<String, Object> getpolicyBYid(Long id);

	AjaxDTO getPolicyLines(Long id);

	int delPolicyLine(Long id);

	int editPolicyLine(OmPolicyLines line);

	PolicySearchModel getPolicyDetail(Map<String, Object> params);

	List<CrmSalesOrganization> getSalepolicyArea(String orgid);

	List<PolicyCustArea> getAreacCust(String orgids);

	String addLine(Long headerid, String lines, String orgids, String custs);

	OmPolicyHeaders getpolicyById(Long headerid);

	int updatePolicyHeader(OmPolicyHeaders policy);

	List<CrmSalesOrganization> getPolicyArea(Long id);

	List<CMerchCustBase> getPolicyCustArea(Long id);

	AjaxDTO writeofflist(Long policyid, AjaxDTO dto);

}
