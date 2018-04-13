package com.hhnz.report.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.MoreObjects;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.service.IDictService;
import com.hhnz.report.mapper.OrderDetailMapper;
import com.hhnz.report.model.OrderDetailReport;
import com.hhnz.report.model.RetailOrderInvoice;
import com.hhnz.report.model.TransferOrderDetailReport;
import com.hhnz.report.service.IOrderDetailReportService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;
import com.hhnz.util.Files;
import com.hhnz.util.Params;

@Service
public class OrderDetailReportServiceImpl implements IOrderDetailReportService {
  
  @Value("${upload.file.path}")
  private String basePath;
  @Resource
  private OrderDetailMapper orderDetailMapper;
  @Resource
  private IDictService dictService;
  
  @Override
  public AjaxDTO orderDetails(AjaxDTO dto, OrderDetailReport order, List<Long> stations) {
    Map<String, Object> param = Params.builder().add("begin", dto.getOffset())
        .add("end", dto.getOffset() + dto.getLimit()).add("merchname", order.getMerchname())
        .add("bdate", order.getBdate()).add("edate", order.getEdate())
        .add("saler", order.getSalename()).add("materialid", order.getMaterialId())
        .add("orgid", order.getOrgid()).add("reginid", order.getReginid())
        .add("provid", order.getProvid()).add("stations", stations).buid();

    List<OrderDetailReport> details = orderDetailMapper.findOrderDetails(param);
    int count = orderDetailMapper.countOrderDetails(param);
    dto.setTotal(count);
    dto.setRows(details);
    return dto;
  }
  
  @Override
  public AjaxDTO transferOrderDetail(AjaxDTO page,TransferOrderDetailReport detail, List<Long> stations){
    Map<String, Object> param = Params.builder().add("begin", page.getOffset())
        .add("end", page.getOffset()+page.getLimit()).add("merch", detail.getMerch()).add("bdate", detail.getBdate())
        .add("edate", detail.getEdate()).add("custtype", detail.getCustType()).add("materialid", detail.getMaterialId())
        .add("stations", stations).buid();

    List<TransferOrderDetailReport> details = orderDetailMapper.findTransferOrderDetail(param);
    int count = orderDetailMapper.countTransferOrderDetail(param);
    page.setTotal(count);
    page.setRows(details);
    return page;
  }
  
  @Override
  public AjaxDTO retailOrderInvoice(AjaxDTO dto){
    Map<String, Object> param = new HashMap<>();
    param.put("begin", dto.getOffset());
    param.put("end", dto.getOffset()+dto.getLimit());
    List<RetailOrderInvoice> orders = orderDetailMapper.findRetailOrderInvoices(param);
    int count = orderDetailMapper.countRetailOrderInvoices(param);
    dto.setTotal(count);
    dto.setRows(orders);
    return dto;
  }
  
  @Override
  public String generateRetailOrderInvoiceExcel(Map<String, Object> param) throws IOException {
    List<RetailOrderInvoice> orders = orderDetailMapper.findRetailOrderInvoices(param);

    String fileName = UUID.randomUUID().toString() + ".xlsx";
    String filePath = Files.standardFolderPath(basePath) + fileName;
    File file = new File(filePath);
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }
    String[] headers = new String[] {"日期", "客户名", "税号", "地址", "电话", "开户行", "银行账号", "货物名称", "规格",
        "单位", "数量", "单价", "金额", "税率", "税额", "发票类型"};
    
    Workbook wb = null;
    OutputStream stream = null;
    try{
      wb = new XSSFWorkbook();
      stream = new FileOutputStream(file);
      Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
      // 写入头数据
      fillHeader(headers, sheet1);
      // 内容
      int rowIndex = 1;
      for(RetailOrderInvoice order:orders){
        fillRow(sheet1, rowIndex++, order);
      }
      wb.write(stream);
    }finally{
      IOUtils.closeQuietly(stream);
      IOUtils.closeQuietly(wb);
    }
    return fileName;
  }
  
  @Override
  public String generateTransferOrderExcel(Map<String, Object> param) throws IOException {
    List<TransferOrderDetailReport> details = orderDetailMapper.findTransferOrderDetail(param);

    String fileName = UUID.randomUUID().toString() + ".xlsx";
    String filePath = Files.standardFolderPath(basePath) + fileName;
    File file = new File(filePath);
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }
    String[] headers = new String[] {"创建日期", "销售组织", "大区", "省区", "客户名称", "客户sap编码", "订单编号", "创建人", "rdc",
        "客户类型", "物料编码", "sku", "单价", "数量", "规格", "单位", "重量", "发货数量", "发货重量", "发货金额", "订单状态"};
    
    Workbook wb = null;
    OutputStream stream = null;
    try{
      wb = new XSSFWorkbook();
      stream = new FileOutputStream(file);
      Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
      // 写入头数据
      fillHeader(headers, sheet1);
      // 内容
      int rowIndex = 1;
      for(TransferOrderDetailReport detail:details){
        fillRow(sheet1,rowIndex++,detail);
      }
      wb.write(stream);
    }finally{
      IOUtils.closeQuietly(stream);
      IOUtils.closeQuietly(wb);
    }
    return fileName;
  }
  
  @Override
  public String generateOrderDetail(AjaxDTO dto, OrderDetailReport order, List<Long> stations) throws IOException{
    Map<String, Object> param = Params.builder().add("begin", dto.getOffset())
        .add("end", 5_0000).add("bdate", order.getBdate()).add("edate", order.getEdate())
        .add("stations", stations).buid();

    List<OrderDetailReport> details = orderDetailMapper.findOrderDetails(param);
    String fileName = UUID.randomUUID().toString() + ".xlsx";
    String filePath = Files.standardFolderPath(basePath) + fileName;
    File file = new File(filePath);
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();
    }
    String[] headers = new String[] {"销售部门", "销售组织", "大区", "省区", "客户名称", "送达方名称", "客户sap编码", "sap订单编码", "crm订单编号", "订单类型",
        "sku", "品牌", "规格", "单位", "售卖单价", "数量", "重量", "已发货数量", "已发货重量", "折扣额", "实收金额", "创建时间", "销售人员"};
    
    Workbook wb = null;
    OutputStream stream = null;
    try{
      wb = new XSSFWorkbook();
      stream = new FileOutputStream(file);
      Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
      // 写入头数据
      fillHeader(headers, sheet1);
      List<TDict> orderTypes = dictService.findByName("ORDER_TYPE");
      // 内容
      int rowIndex = 1;
      for(OrderDetailReport detail:details){
        fillRow(sheet1, rowIndex++, detail, orderTypes);
      }
      wb.write(stream);
      Files.deleteFileDelay(file, 3, TimeUnit.MINUTES);
    }finally{
      IOUtils.closeQuietly(stream);
      IOUtils.closeQuietly(wb);
    }
    return fileName;
  }

  private void fillHeader(String[] headers, Sheet sheet1) {
    Row header = (Row) sheet1.createRow(0);
    for (int j = 0; j < headers.length; j++) {
      Cell cell = header.createCell(j);
      cell.setCellValue(headers[j]);
    }
  }
  
  private void fillRow(Sheet sheet, int rowIndex, TransferOrderDetailReport order) {
    int cellIndex = 0;
    Row row = (Row) sheet.createRow(rowIndex);
    // 写入列数据
    fillCell(row, cellIndex++, order.getCreateTime());
    fillCell(row, cellIndex++, order.getOrgname());
    fillCell(row, cellIndex++, order.getRegion());
    fillCell(row, cellIndex++, order.getProv());
    fillCell(row, cellIndex++, order.getMerch());
    fillCell(row, cellIndex++, order.getMerchSapId());
    fillCell(row, cellIndex++, String.valueOf(order.getOrderId()));
    fillCell(row, cellIndex++, order.getCreator());
    fillCell(row, cellIndex++, order.getRdc());
    fillCell(row, cellIndex++, order.getCustType());
    fillCell(row, cellIndex++, order.getMaterialId());
    fillCell(row, cellIndex++, order.getSku());
    fillCell(row, cellIndex++, order.getUnitPrice().toPlainString());
    fillCell(row, cellIndex++, order.getNum().toPlainString());
    fillCell(row, cellIndex++, order.getSpecifications());
    fillCell(row, cellIndex++, order.getUnit());
    fillCell(row, cellIndex++, order.getWeight());
    fillCell(row, cellIndex++, order.getDeliveryNum().toPlainString());
    fillCell(row, cellIndex++, order.getDeliveryWeight().toPlainString());
    fillCell(row, cellIndex++, order.getDeliveryAmt().toPlainString());
    fillCell(row, cellIndex++, order.getStates());
    
  }
  
  private void fillRow(Sheet sheet, int rowIndex, RetailOrderInvoice order) {
    int cellIndex = 0;
    Row row = (Row) sheet.createRow(rowIndex);
    // 写入列数据
    fillCell(row, cellIndex++, order.getCreateTime());
    fillCell(row, cellIndex++, order.getMerchName());
    fillCell(row, cellIndex++, order.getTaxNum());
    fillCell(row, cellIndex++, order.getAddress());
    fillCell(row, cellIndex++, order.getPhone());
    fillCell(row, cellIndex++, order.getBankName());
    fillCell(row, cellIndex++, order.getBankAccount());
    fillCell(row, cellIndex++, order.getSku());
    fillCell(row, cellIndex++, order.getSpecifications());
    fillCell(row, cellIndex++, order.getUnit());
    fillCell(row, cellIndex++, BigDecimalASME.divide(order.getNum(), new BigDecimal(order.getAmounts())).toPlainString());
    fillCell(row, cellIndex++, BigDecimalASME.multiply(order.getPrice(), new BigDecimal(order.getAmounts())).toPlainString());
    fillCell(row, cellIndex++, order.getAmt().toPlainString());
    fillCell(row, cellIndex++, order.getTaxRate());
    fillCell(row, cellIndex++, order.getTax());
    fillCell(row, cellIndex++, order.getInvoiceType());
    
  }
  
  private void fillRow(Sheet sheet, int rowIndex, OrderDetailReport detail, List<TDict> orderTypes){
    int cellIndex = 0;
    Row row = (Row) sheet.createRow(rowIndex);
    
    fillCell(row, rowIndex++, detail.getDepartment());
    fillCell(row, cellIndex++, detail.getOrgname());
    fillCell(row, cellIndex++, detail.getRegin());
    fillCell(row, cellIndex++, detail.getProv());
    fillCell(row, cellIndex++, detail.getMerchname());
    fillCell(row, cellIndex++, detail.getShipname());
    fillCell(row, cellIndex++, detail.getMerchsapid());
    fillCell(row, cellIndex++, detail.getOrdersapid());
    fillCell(row, cellIndex++, detail.getOrdercrmid());
    fillCell(row, cellIndex++, dictService.code2Str(detail.getOrderType(), orderTypes));
    fillCell(row, cellIndex++, detail.getSku());
    fillCell(row, cellIndex++, detail.getBrand());
    fillCell(row, cellIndex++, detail.getSpecifications());
    fillCell(row, cellIndex++, detail.getUnit());
    fillCell(row, cellIndex++, detail.getPrice());
    fillCell(row, cellIndex++, detail.getNum().toString());
    fillCell(row, cellIndex++, MoreObjects.firstNonNull(detail.getWeigth(), BigDecimal.ZERO).toString());
    fillCell(row, cellIndex++, MoreObjects.firstNonNull(detail.getSendNum(), BigDecimal.ZERO).toString());
    fillCell(row, cellIndex++, MoreObjects.firstNonNull(detail.getSendWeigth(), BigDecimal.ZERO).toString());
    fillCell(row, cellIndex++, detail.getDiscount());
    fillCell(row, cellIndex++, detail.getOrderamt());
    fillCell(row, cellIndex++, DateUtil.format(detail.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
    fillCell(row, cellIndex++, detail.getSalename());
    
  }
  
  private void fillCell(Row row, int cellIndex, String value){
    Cell cell = row.createCell(cellIndex);
    cell.setCellValue(value);
  }

}
