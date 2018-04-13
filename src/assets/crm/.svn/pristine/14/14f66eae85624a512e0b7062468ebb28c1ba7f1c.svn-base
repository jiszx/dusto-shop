package com.hhnz.order.service;

import java.math.BigDecimal;
import java.util.Map;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.util.AjaxDTO;

public interface IOrderCloseService {

	AjaxDTO closeList(Map<String, Object> params);

	Map<String,Object>  doClose(Long id,TEmployee user);

	public int InsertLog(String string, BigDecimal hbAmt, OmOrderHeadersAll order);

	void closeTransferOrder(Long id);

}
