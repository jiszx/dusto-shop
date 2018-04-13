package com.hhnz.account.service;

import java.io.IOException;
import java.util.Map;

import com.hhnz.account.model.CMerchCustAccountV;
import com.hhnz.util.AjaxDTO;

public interface IAccountService {

	CMerchCustAccountV getAccountByMerchId(Long merchCustId);

	AjaxDTO getAccountDetailsList(AjaxDTO bean, Long pid);

	AjaxDTO getMerchAccount(Map<String, Object> params);

  String generateAccountExcel(Map<String, Object> params) throws IOException;

}
