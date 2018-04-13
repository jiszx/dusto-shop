package com.hhnz.crm.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.crm.model.TFactoryV;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.organization.model.CrmSalesOrganization;

public interface UtilMapper {

	List<CrmSalesOrganization> getuserorgs(Map<String, Object> params);

	List<CMerchCustBase> getorgCustomer(Map<String, Object> params);

	List<UserStations> getUserStations(Map<String, Object> params);

	List<UserJobs> getUserJobs(Long id);

	List<CrmSalesOrganization> getOrderOrg(Map<String, Object> params);

	Long getCustNumByStationId(Long stationid);

	Long getauditOrder(Long stationid);

	Long mytaskNum(String loginName);

	String getUserOrgInfo(String stationorgid);

	List<TFactoryV> getfactorysByOrgid(String orgid);

	int insertPolicyCust(Map<String, Object> params);

}
