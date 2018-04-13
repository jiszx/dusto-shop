package com.hhnz.customer.service;

import java.util.List;
import java.util.Map;

import com.hhnz.customer.model.CMerchCustBase;

public interface ApiCustomerService {

	List<CMerchCustBase> findCustBaseByPID(Map<String, Object> params);


}
