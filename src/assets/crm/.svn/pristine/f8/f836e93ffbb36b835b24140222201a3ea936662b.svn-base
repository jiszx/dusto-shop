package com.hhnz.account.service;

import java.util.List;
import java.util.Map;

import com.hhnz.account.model.CDistributorsUpaccount;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.util.AjaxDTO;

public interface IUpAccountService {

	Integer addupaccount(CMerchUpaccount model);

	Integer updateupaccount(CMerchUpaccount model);

	Integer delUpheader(Long id);

	AjaxDTO findInputAccountList(CMerchUpaccount upaccount, AjaxDTO bean);

	Map<String, Object> censor(Long id, String states);

	AjaxDTO getaccountCustomer(String orgid, List<Long> userid);

	AjaxDTO getSalesAccountList(Map<String, Object> params, AjaxDTO bean);

	int updateUpaccoutMerch(CMerchUpaccount model);

	AjaxDTO getdistributorsList(AjaxDTO bean, String orgid, String custname,
			String states, String bankSerial);

	int addDistributorAdd(CDistributorsUpaccount distributor);

	AjaxDTO findUpAccountList(CMerchUpaccount upaccount, AjaxDTO bean);

	AjaxDTO findAllList(CMerchUpaccount upaccount, AjaxDTO bean);

	String getSapMsg(Long id);

	void updateErrorMsg(Long id, String msg);

	AjaxDTO bankCodeToName(AjaxDTO page);

	Map<String,Object>  validateStorage(Long id, Long merchCustId);

}
