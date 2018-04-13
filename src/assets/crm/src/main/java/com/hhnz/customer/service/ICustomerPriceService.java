package com.hhnz.customer.service;

import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.customer.model.CMerchCustPriceMaintenance;
import com.hhnz.util.AjaxDTO;

public interface ICustomerPriceService {

	AjaxDTO selectCustomerAll(String orgid);

	AjaxDTO selectMaterials();

	int addProductBatchMaintain(CMerchCustPriceMaintenance model);

	AjaxDTO selectBatchMaintainList(Map<String, Object> params);

	Integer del(Long id) throws Exception;

	int editProductBatchMaintain(CMerchCustPriceMaintenance model);

	void updateStates(Long id, String states);

	void updateMerchProduct(Long id) throws Exception;

	Integer startprocess(Long id, Map<String, Object> param, TEmployee user);

	AjaxDTO batchmaintainDetails(AjaxDTO dto,Long id) throws Exception;

	void importCustPrice(CommonsMultipartFile file, CMerchCustPriceMaintenance model) throws Exception;

}
