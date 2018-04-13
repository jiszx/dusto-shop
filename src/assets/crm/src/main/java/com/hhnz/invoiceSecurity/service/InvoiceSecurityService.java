package com.hhnz.invoiceSecurity.service;

import java.util.List;
import java.util.Map;

import com.hhnz.invoiceSecurity.dto.BillingInvoiceDTO;
import com.hhnz.invoiceSecurity.model.CrmTaxInvoice;
import com.hhnz.util.AjaxDTO;

public interface InvoiceSecurityService {

	AjaxDTO getinvoiceList(Map<String,Object> params);

	List<BillingInvoiceDTO> getBillInvoicesItem(String ids, String sourcesType, String isMerge);

	int callBackInvoiceNo(CrmTaxInvoice invoices, String ids);

	AjaxDTO selectAisionInvociesList(Map<String, Object> params);

}
