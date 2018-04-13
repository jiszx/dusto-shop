package com.hhnz.account.service;

import java.util.Map;

import com.hhnz.account.model.CMerchBalancesV;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.util.AjaxDTO;

public interface ICustPeriodService {

	AjaxDTO findPeriodDetail(Long custid, String orgid, String period,
			int limit, int offset, String accountType);

	AjaxDTO findCustPeriod(AjaxDTO bean, Map<String, Object> params);

	CMerchBalancesV getBalance(Long id);

	AjaxDTO periodData();

	String getMaxPeriod();

	String upBalance(String period);

	CMerchCustBalances getMarginBalance(Long merchCustId, String orgId, String period);

}
