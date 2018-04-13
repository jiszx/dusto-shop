package com.hhnz.account.service;

import java.util.Map;

import com.hhnz.account.model.CMerchReceiveVerifie;
import com.hhnz.account.model.OmInvoiceV;
import com.hhnz.util.AjaxDTO;

public interface IAccountReceiveService {

	AjaxDTO verifieList(AjaxDTO bean, OmInvoiceV model);

	AjaxDTO upAccountList(AjaxDTO bean, String sapcustomerid);

	Map<String, Object> doReceive(Long invoiceId, String accountIds);

	AjaxDTO invoiceItemList(AjaxDTO bean, String invoiceNo);

	Map<String, Object> verifieByneinvoice(String invoiceNo,Long invoiceId);

	AjaxDTO verifieList(AjaxDTO bean, Long invoiceId, String invoiceNo);

	Map<String, Object> writeOff(Long invoiceId, String invoiceNo) throws Exception;

	String verifieAll(String p_org_id);

	Map<String, Object> SendToSap(CMerchReceiveVerifie verifie);

}
