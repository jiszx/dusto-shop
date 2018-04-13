package com.hhnz.report.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.report.mapper.AccountdetailsreportMapper;
import com.hhnz.report.service.IAccountDetailsReportService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

@Service
@Transactional
public class AccountDetailsReportServiceImpl implements
		IAccountDetailsReportService {
	
	@Resource
	private AccountdetailsreportMapper mapper;
	@Override
	public AjaxDTO accountDetailsList(AjaxDTO bean) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		return null;
	}

}
