package com.hhnz.report.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.report.service.IinvoiceReportService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("invoice/report")
public class InvoiceReportController {
	
	@Resource
	private IinvoiceReportService service;
	
	
	@RequestMapping("invoiceItem.html")
	public String invoiceItem(){
		return "report/invoiceItem";
	}
	
	@RequestMapping("invoice.html")
	public String invoice(){
		return "report/invoice";
	}
	@RequestMapping("invoiceItemlist")
	@ResponseBody
	public AjaxDTO  invoiceItemList(AjaxDTO bean,String custname,String invoiceNo,String type,String startTime,String endTime) {
		Map<String, Object> param = new HashMap<>();
		if(StringUtils.isNotEmpty(custname)){
			param.put("custname", custname);
		}
		if(StringUtils.isNotEmpty(invoiceNo)){
			param.put("invoiceNo", invoiceNo);
		}
		if(StringUtils.isNotEmpty(type)){
			param.put("type", type);
		}
		if(StringUtils.isNotEmpty(startTime)){
			param.put("startTime", startTime);
		}
		if(StringUtils.isNotEmpty(endTime)){
			param.put("endTime", endTime);
		}
		return this.service.invoiceItemList(bean,param);
	}
	
	@RequestMapping("invoicelist")
	@ResponseBody
	public AjaxDTO  invoiceList(AjaxDTO bean,String custname,String invoiceNo,String type,String startTime,String endTime) {
		Map<String, Object> param = new HashMap<>();
		if(StringUtils.isNotEmpty(custname)){
			param.put("custname", custname);
		}
		if(StringUtils.isNotEmpty(invoiceNo)){
			param.put("invoiceNo", invoiceNo);
		}
		if(StringUtils.isNotEmpty(type)){
			param.put("type", type);
		}
		if(StringUtils.isNotEmpty(startTime)){
			param.put("startTime", startTime);
		}
		if(StringUtils.isNotEmpty(endTime)){
			param.put("endTime", endTime);
		}
		return this.service.invoiceList(bean,param);
	}
}
