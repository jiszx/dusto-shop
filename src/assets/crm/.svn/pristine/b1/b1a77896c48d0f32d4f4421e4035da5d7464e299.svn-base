package com.hhnz.invoiceSecurity.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.invoiceSecurity.dto.AisionInvoicesDTO;
import com.hhnz.invoiceSecurity.dto.HeadersDTO;
import com.hhnz.invoiceSecurity.dto.InvoiceSecurityListDTO;
import com.hhnz.invoiceSecurity.dto.ItemsDTO;

public interface InvoiceSecurityMapper {

	List<InvoiceSecurityListDTO> getInvoiceListByAr(Map<String, Object> params);

	int countInvoiceByAr(Map<String, Object> params);

	List<InvoiceSecurityListDTO> getInvoiceListByOrder(
			Map<String, Object> params);

	int countInvoiceByOrder(Map<String, Object> params);
	
	
	List<HeadersDTO> getInvoiceHeaderByAr(Map<String, Object> params);

	List<ItemsDTO> getOrdersItemsByAr(Long invoiceId);

	List<ItemsDTO> getOrdersItemsByArAll(Map<String, Object> params);

	List<HeadersDTO> getInvoiceHeaderByOrder(Map<String, Object> params);

	List<ItemsDTO> getOrdersItemsByOrder(long orderId);

	List<ItemsDTO> getOrdersItemsByOrderAll(Map<String, Object> params);

	List<AisionInvoicesDTO> selectAisionInvoicesListByOrder(
			Map<String, Object> params);

	int countAisionInvoicesListByOrder(Map<String, Object> params);

}
