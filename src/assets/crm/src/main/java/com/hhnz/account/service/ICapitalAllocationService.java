package com.hhnz.account.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.util.AjaxDTO;

public interface ICapitalAllocationService {

	CMerchCustBase getmerchByID(Long merchId);

	List<CMerchCustBase> getMerchs(List<Long> stationids,String custType);

	AjaxDTO getReceives(Long merchCustId);

	AjaxDTO getRetailer(Long merchCustId);

	AjaxDTO getRetailerAccount(String ids);

	Map<String, Object> doAllocation(String data, Long receiveId, TEmployee user, HttpServletRequest request);

	AjaxDTO getOrders(Long merchCustId);

	AjaxDTO getRetailerOrders(String ids, String type);

	AjaxDTO allocationDetail(Long upaccountId, AjaxDTO bean);

	AjaxDTO getRecord(Long merchCustId);

}
