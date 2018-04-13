package com.hhnz.salepolicy.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.order.model.OrderMaterial;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.salepolicy.model.PolicyCustArea;
import com.hhnz.salepolicy.model.PolicyMaterialModel;
//import com.hhnz.salepolicy.model.PolicyOrgModel;
import com.hhnz.salepolicy.model.PolicySearchModel;

public interface PolicyUtilMapper {

	/*List<PolicyOrgModel> getPolicyOrg(Long userid);*/

	List<PolicyMaterialModel> getPolicyMaterial(String orgid);

	List<PolicySearchModel> getPolicyList(Map<String, Object> params);
	
	List<OrderMaterial> getPolicyMaterials(Map<String, Object> params);

	List<PolicyCustArea> getAreaCust(Map<String, Object> params);

	List<CrmSalesOrganization> getPolicyArea(Long id);

	List<CMerchCustBase> getPolicyCustArea(Long id);

}
