package com.hhnz.report.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.report.model.OrderDetailReport;
import com.hhnz.report.model.RetailOrderInvoice;
import com.hhnz.report.model.TransferOrderDetailReport;

public interface OrderDetailMapper {
  
  List<OrderDetailReport> findOrderDetails(Map<String, Object> param);
  
  int countOrderDetails(Map<String, Object> param);
  
  /**
   * 零售订单及发票信息
   * @param param
   * @return
   */
  List<RetailOrderInvoice> findRetailOrderInvoices(Map<String, Object> param);
  
  int countRetailOrderInvoices(Map<String, Object> param);
  
  /**
   * 调拨单详情
   * @param param
   * @return
   */
  List<TransferOrderDetailReport> findTransferOrderDetail(Map<String, Object> param);
  
  int countTransferOrderDetail(Map<String, Object> param);

}
