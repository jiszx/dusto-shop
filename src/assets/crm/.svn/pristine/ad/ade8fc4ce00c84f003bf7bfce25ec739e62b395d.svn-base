package com.hhnz.account.service;

import java.math.BigDecimal;
import java.util.Map;

import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAdjust;
import com.hhnz.account.model.CMerchCustAdjustV;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.util.AjaxDTO;

public interface IAdjustAccountService {

	AjaxDTO findAccountAdjustList(CMerchCustAdjustV adjust, AjaxDTO bean);

	Integer addAccountAdjust(CMerchCustAdjust model);

	Integer updateAdjust(CMerchCustAdjust model);

	Integer delAdjust(Long id);

	Integer audit(Long id, String states) throws Exception;

	CMerchCustAdjust findAdjustById(Long id);

	Integer startprocess(Long id, Map<String, Object> param,TEmployee user);

	CMerchCustAccount getAccount(CMerchCustAdjust adjust);

	void adjustAccountAmt(CMerchCustAccount account, String accountType, BigDecimal amt);

	void changeBalanceAmt(String accountType, Long merchid, String orgid, BigDecimal amt);

	int customerToCustomer(CMerchCustAdjust adjust);

}
