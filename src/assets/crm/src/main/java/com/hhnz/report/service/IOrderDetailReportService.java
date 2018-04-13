package com.hhnz.report.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.hhnz.report.model.OrderDetailReport;
import com.hhnz.report.model.TransferOrderDetailReport;
import com.hhnz.util.AjaxDTO;

public interface IOrderDetailReportService {

  AjaxDTO orderDetails(AjaxDTO dto, OrderDetailReport order, List<Long> stations);

  AjaxDTO retailOrderInvoice(AjaxDTO dto);

  String generateRetailOrderInvoiceExcel(Map<String, Object> param) throws IOException;

  AjaxDTO transferOrderDetail(AjaxDTO page,TransferOrderDetailReport detail, List<Long> stations);

  String generateTransferOrderExcel(Map<String, Object> param) throws IOException;

  String generateOrderDetail(AjaxDTO dto, OrderDetailReport order, List<Long> stations)
      throws IOException;

}
