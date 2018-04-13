package com.hhnz.customerInv.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customerInv.dto.CustomerInvAllocationDTO;
import com.hhnz.customerInv.model.CMerchCustProudctInvV;
import com.hhnz.customerInv.model.MerchProudctBalancesV;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.util.AjaxDTO;

public interface CustomerStockService {

	AjaxDTO getList(Map<String, Object> params);

	AjaxDTO getInvList(AjaxDTO bean, CMerchCustProudctInvV inv, List<Long> stationids, TEmployee user);

	AjaxDTO getBalancesList(AjaxDTO bean, MerchProudctBalancesV inv, List<Long> stationids, TEmployee user);

	AjaxDTO getbalanceDetailsList(AjaxDTO bean, Long id);

	Map<String,Object> updateStates(Long id, String states, TEmployee user) throws Exception;

	Map<String,Object> ValidateAmtAndNum(Long id);

	Integer updateInv();

	String  updateBalance(Long merchId,String materialId);

	void doAddProductInv(OmOrderHeadersAll header, OmOrderSpilts line,
			 String format,TEmployee user);

	void doUpdateAccount(OmOrderHeadersAll header, String format);

	AjaxDTO getOrderLineDetails(Long id);
	
	//void updateCustInv(Long id);

	OmOrderHeadersAll selectByPrimaryKey(Long id);

  //int addMerchInv(Long transferOrderId);

  String generateTransferDetails(Map<String, Object> param) throws IOException;

  int reduceProductInvByOrder(OmOrderHeadersAll header, OmOrderSpilts line, CMerchCustBase merch,  String period);

  String generateInvDetail(AjaxDTO bean, CMerchCustProudctInvV inv, List<Long> stationids,
      TEmployee user) throws IOException;

  String closeOrder(Long id);

List<CustomerInvAllocationDTO> getListAll(Map<String, Object> params);

}
