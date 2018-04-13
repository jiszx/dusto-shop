package com.hhnz.report.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.report.service.IAccountDetailsReportService;
import com.hhnz.util.AjaxDTO;
/**
 * 客户资金流水表Controller
 * @author hhnz-skevin 2016-11-17
 *
 */

@Controller
@RequestMapping("report/account")
public class AccountDetailsReportController {
	
	@Resource
	private  IAccountDetailsReportService service;
	
	@RequestMapping("custAccountDetails.html")
	public  String custAccountDetails(){
		return "report/custAccountDetails";
	}
	
	@RequestMapping("accountDetailsList")
	@ResponseBody
	public AjaxDTO accountDetailsList(AjaxDTO bean){
		return this.service.accountDetailsList(bean);
	}
}
