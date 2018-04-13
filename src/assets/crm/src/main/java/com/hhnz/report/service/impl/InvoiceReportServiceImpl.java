package com.hhnz.report.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.report.dto.OmInvoiceItemDTO;
import com.hhnz.report.mapper.InvoiceReportMapper;
import com.hhnz.report.service.IinvoiceReportService;
import com.hhnz.util.AjaxDTO;

@Service
@Transactional
public class InvoiceReportServiceImpl implements IinvoiceReportService {
	@Resource
	private  InvoiceReportMapper mapper;
	
	@Override
	public AjaxDTO invoiceItemList(AjaxDTO bean,Map<String, Object> param) {
	    param.put("begin", bean.getOffset());
	    param.put("end", bean.getOffset()+bean.getLimit());
	    List<OmInvoiceItemDTO> list = this.mapper.selectItemList(param);
	    int total = this.mapper.countItem(param);
	    bean.setRows(list);
	    bean.setTotal(total);
		return bean;
	}

	@Override
	public AjaxDTO invoiceList(AjaxDTO bean, Map<String, Object> param) {
		 param.put("begin", bean.getOffset());
		    param.put("end", bean.getOffset()+bean.getLimit());
		    List<OmInvoiceItemDTO> list = this.mapper.selectInvoiceList(param);
		    int total = this.mapper.countInvoice(param);
		    bean.setRows(list);
		    bean.setTotal(total);
			return bean;
	}

}
