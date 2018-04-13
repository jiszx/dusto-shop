package com.hhnz.customerInv.service;

import java.util.Map;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.customerInv.model.CMerchCustProductAdjust;
import com.hhnz.util.AjaxDTO;

public interface CustomerInvAdjustService {

	AjaxDTO getCustomer(String orgid,String invValidate);

	AjaxDTO getmaterials(String orgid, String merchId, String type);

	Integer doAdd(CMerchCustProductAdjust adjust);

	AjaxDTO selectAdjustList(Map<String, Object> params);

	String selectInvNum(Map<String, Object> params);

	String doDel(Long id);

	String doAudit(Long id,TEmployee user,String states) throws Exception;

	Integer updateStates(CMerchCustProductAdjust adjust) throws Exception;

	Integer doEdit(CMerchCustProductAdjust adjust);

}
