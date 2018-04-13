package com.hhnz.invoiceSecurity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.invoiceSecurity.dto.BillingInvoiceDTO;
import com.hhnz.invoiceSecurity.model.CrmTaxInvoice;
import com.hhnz.invoiceSecurity.service.InvoiceSecurityService;
import com.hhnz.util.AjaxDTO;

/**
 * 金税发票开票
 * 
 * @author hhnz-skevin 2016-11-10
 *
 */
@RequestMapping("invoiceSecurity")
@Controller
public class InvoiceSecurityController {
	@Resource
	private InvoiceSecurityService service;

	@RequestMapping("invoice.html")
	public String invoice() {
		return "invoiceSecurity/invoice";
	}
	
	@RequestMapping("aisionInvoices.html")
	public String  aisionInvoices(){
		return "invoiceSecurity/aisionInvoices";
	}
	/**
	 * 获取发票列表
	 * 
	 * @param dto
	 * @param custname
	 * @param sapCustomerId
	 * @param type
	 * @return
	 */
	@RequestMapping("list")
	public @ResponseBody AjaxDTO getList(@ModelAttribute AjaxDTO dto,
			String custname, String sapCustomerId, String type,
			String sourcesType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("begin", dto.getOffset());
		params.put("edn", dto.getLimit() + dto.getOffset());
		params.put("custname", custname);
		params.put("sapCustomerId", sapCustomerId);
		params.put("type", type);
		params.put("sourcesType", sourcesType);
		return this.service.getinvoiceList(params);
	}

	/**
	 * 开票
	 * 
	 * @param ids
	 * @param invoiceType
	 * @param request
	 * @return
	 */
	@RequestMapping("billingInvoice.html")
	@ResponseBody
	public ModelAndView billingInvoice(String ids, String invoiceType,
			String sourcesType, String IsMerge, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("invoiceSecurity/billingInvoice");
		//ModelAndView mv = new ModelAndView("invoiceSecurity/test");
		List<BillingInvoiceDTO> list = new ArrayList<BillingInvoiceDTO>();
		list = this.service.getBillInvoicesItem(ids, sourcesType,IsMerge);
		JSONArray data = JSONArray.fromObject(list);
		mv.addObject("list", list);// 展示数据
		mv.addObject("data", data);// 开票数据
		mv.addObject("invoiceType", invoiceType);// 发票类型
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		mv.addObject("InfoInvoicer", user.getName());// 开票人
		mv.addObject("ids", ids);
		mv.addObject("IsMerge", IsMerge);// 是否合并开票
		mv.addObject("sourcesType", sourcesType);//订单or应收发票
		return mv;
	}


	/**
	 * 回写金穗发票编号
	 * 
	 * @param invoiceNo
	 * @param infoNumber
	 * @param infoTypeCode
	 * @param infoMonth
	 * @param infoDate
	 * @param infoAmount
	 * @param infoTaxAmount
	 * @return
	 */
	@RequestMapping("callBackInvoiceNo")
	@ResponseBody
	public String callBackInvoiceNo(CrmTaxInvoice invoices,
		String ids,HttpServletRequest request) {
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		invoices.setCreateOid(user.getId());
		invoices.setCreateTs(new Date());
		return this.service.callBackInvoiceNo(invoices, ids) == 1 ? "S":"E";
	}
	
	@RequestMapping("/aisionInvoicesList")
	@ResponseBody
	public AjaxDTO aisionInvoicesList(AjaxDTO dto,String custname, String sapCustomerId, String invoicesType,
			String sourcesType){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("custname", custname);
		params.put("sapCustomerId", sapCustomerId);
		params.put("invoicesType", invoicesType);
		params.put("sourcesType", sourcesType);
		params.put("begin", dto.getOffset());
		params.put("edn", dto.getLimit() + dto.getOffset());
		return this.service.selectAisionInvociesList(params);
	}
	
}
