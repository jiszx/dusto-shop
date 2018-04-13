package com.hhnz.account.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.account.model.CMerchReceiveVerifie;
import com.hhnz.account.model.OmInvoiceV;
import com.hhnz.account.service.IAccountReceiveService;
import com.hhnz.util.AjaxDTO;

/**
 * 应收发票核销
 * @author hhnz-skevin
 *
 */
@Controller
@RequestMapping("account/receive")
public class AccountReceiveController {
	
	@Resource
	private  IAccountReceiveService service;
	
	/**
	 *页面跳转
	 * @return
	 */
	@RequestMapping("receiveVerifle.html")
	public String receiveVerifle(){
		return "account/receiveVerifle";
	}
	
	/**
	 * 应收发票列表
	 * @param bean
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("List")
	@ResponseBody
	public AjaxDTO  verifieList(@ModelAttribute AjaxDTO bean,OmInvoiceV model)throws Exception{
		bean = this.service.verifieList(bean,model);
		return bean;
	}
	
	/**
	 * 对应客户上账确认的记录
	 * @param sapCustomerId
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("upAccountList")
	@ResponseBody
	public AjaxDTO  upAccountList(String sapCustomerId,@ModelAttribute AjaxDTO bean) throws Exception {
		bean = this.service.upAccountList(bean,sapCustomerId);
		return bean;
	}
	
	/**
	 * 核销动作
	 * @param invoiceId
	 * @param accountIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doReceive")
	@ResponseBody
	public Map<String,Object> doReceive(Long invoiceId,String accountIds) throws Exception{
		return this.service.doReceive(invoiceId,accountIds);
	}
	
	/**
	 * 应收发票明显
	 * @param bean
	 * @param invoiceNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("invoiceItemList")
	@ResponseBody
	public AjaxDTO  invoiceItemList(@ModelAttribute AjaxDTO bean,String invoiceNo)throws Exception{
		bean = this.service.invoiceItemList(bean,invoiceNo);
		return bean;
	}
	
	/**
	 * 负数发票核销
	 * @param invoiceNo
	 * @param invoiceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("verifieByneinvoice")
	@ResponseBody
	public Map<String,Object> verifieByneinvoice(String invoiceNo,Long invoiceId)throws Exception{
		return this.service.verifieByneinvoice(invoiceNo,invoiceId);
	}
	
	/**
	 * 核销明细
	 * @param bean
	 * @param invoiceId
	 * @param invoiceNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("verifieList")
	@ResponseBody
	public AjaxDTO  verifieList(@ModelAttribute AjaxDTO bean,Long invoiceId,String invoiceNo) throws Exception{
		return this.service.verifieList(bean,invoiceId,invoiceNo);
	}
	
	/**
	 * 红冲
	 * @param invoiceId
	 * @param invoiceNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("writeOff")
	@ResponseBody
	public Map<String, Object> writeOff(Long invoiceId,String invoiceNo) throws Exception{
		return this.service.writeOff(invoiceId,invoiceNo);
	}
	
	@RequestMapping("verifieAll")
	@ResponseBody
	public String verifieAll(String p_org_id){
		return this.service.verifieAll(p_org_id);
	}
	
	@RequestMapping("sendSap")
	@ResponseBody
	public Map<String,Object> sendSap(CMerchReceiveVerifie verifie)throws Exception {
		return this.service.SendToSap(verifie);
	}
}
