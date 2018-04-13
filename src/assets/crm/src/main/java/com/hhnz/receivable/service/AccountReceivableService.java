package com.hhnz.receivable.service;

import java.util.Map;

import com.hhnz.util.AjaxDTO;

public interface AccountReceivableService {

	AjaxDTO getArBalancesList(Map<String, Object> params);

	AjaxDTO getARbalanceDetailslist(AjaxDTO bean, Long id);

	String getMaxPeriod();

	Map<String, Object> updateByMerchId(Long merchId);

	String updateByPeriod();

}
