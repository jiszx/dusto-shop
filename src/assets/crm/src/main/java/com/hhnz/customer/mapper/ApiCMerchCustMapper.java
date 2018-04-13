package com.hhnz.customer.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.customer.model.CMerchCustBase;

public interface ApiCMerchCustMapper {

	List<CMerchCustBase> findCustBaseByPID(Map<String, Object> params);

}
