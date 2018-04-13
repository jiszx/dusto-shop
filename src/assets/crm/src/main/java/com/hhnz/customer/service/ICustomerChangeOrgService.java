package com.hhnz.customer.service;

public interface ICustomerChangeOrgService {

	String changeMerchOrg(Long merchId, String newOrgId, String oldOrgId,String changeType, String states,Long stationId, Long salesrepId);

}
