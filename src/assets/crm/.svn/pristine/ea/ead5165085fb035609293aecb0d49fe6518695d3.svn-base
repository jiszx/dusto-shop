package com.hhnz.receivable.service.impl;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.receivable.dto.ArOverdueDTO;
import com.hhnz.receivable.dto.ArOverdueDetailsDTO;
import com.hhnz.receivable.mapper.ArOverdueMapper;
import com.hhnz.receivable.service.ArOverdueService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Files;

/**
 * 应收账款逾期service
 * @author hhnz-skevin 2017-03-29
 *
 */
@Service
@Transactional
public class IArOverdueServiceImpl implements ArOverdueService {
	
	@Resource
	private ArOverdueMapper mapper;
	@Resource
	private CMerchCustBaseMapper merchmapper;
	
	@Value("${upload.file.path}")
	private String basePath;
	@Override
	public AjaxDTO selectArOverdueList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<ArOverdueDTO> list = this.mapper.selectArOverdueList(params);
		int total = this.mapper.countArOverdue(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	@Override
	public AjaxDTO selectArOverdueDetails(AjaxDTO bean, Long merchId,Long arPeriod) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("begin", bean.getOffset());
		params.put("end", bean.getLimit()+bean.getOffset());
		params.put("merchId", merchId);
		params.put("arPeriod", arPeriod);
		List<ArOverdueDetailsDTO> list = this.mapper.selectArOverdueDetials(params);
		int total = this.mapper.countArOverdueDetails(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	
	@Override
	public String exportDetails(Long merchId, Long arPeriod) throws IOException {
		Map<String,Object> params = new HashMap<String, Object>();;
		params.put("merchId", merchId);
		params.put("arPeriod", arPeriod);
		int total = this.mapper.countArOverdueDetails(params);
		params.put("begin", 0);
		params.put("end", total);
		List<ArOverdueDetailsDTO> list = this.mapper.selectArOverdueDetials(params);
		
		CMerchCustBase merch = this.merchmapper.selectByPrimaryKey(merchId);
		  // 创建文件
	      String fileName = "应收账龄明细表-"+merch.getName() + ".xlsx";
	      String filePath = Files.standardFolderPath(basePath) + fileName;
	      File file = new File(filePath);
	      if (!file.getParentFile().exists()) {
	        file.getParentFile().mkdirs();
	      }
	      String title[] = {"应收发票编号", "开票日期", "物料编码", "物料描述","单价", "数量", "金额","税额", "重量", "逾期天数"};
	      
	      Workbook wb = null;
	      OutputStream stream = null;
	      try{
	        wb = new XSSFWorkbook();
	        stream = new FileOutputStream(file);
	        Sheet sheet1 = (Sheet) wb.createSheet("应收账龄明细");
	        //写入表头数据
	        
	        Row name= (Row) sheet1.createRow(0);
	        Cell nametitle = name.createCell(0);
	        nametitle.setCellValue("客户名称");
	        Cell namevalue = name.createCell(1);
	        namevalue.setCellValue(merch.getName());
	        
	        Row code= (Row) sheet1.createRow(1);
	        Cell codetitle = code.createCell(0);
	        codetitle.setCellValue("SAP编码");
	        Cell codevalue = code.createCell(1);
	        codevalue.setCellValue(merch.getSapCustomerId());
	        
	        Row period= (Row) sheet1.createRow(2);
	        Cell periodtitle = period.createCell(0);
	        periodtitle.setCellValue("账期(天)");
	        Cell periodvalue = period.createCell(1);
	        periodvalue.setCellValue(arPeriod.toString());
	        
	        
	        // 写明细头数据
	        Row header = (Row) sheet1.createRow(3);
	        // 循环写入头列数据
	        for (int j = 0; j < title.length; j++) {
	          Cell cell = header.createCell(j);
	          cell.setCellValue(title[j]);
	        }
	        // 内容
	        int rowIndex = 4;
	        for (ArOverdueDetailsDTO overdueDetails : list) {
	          fillRow(sheet1, rowIndex++, overdueDetails);
	        }
	        wb.write(stream);
	      }finally{
	        IOUtils.closeQuietly(stream);
	        if(wb instanceof Closeable){
				IOUtils.closeQuietly((Closeable)wb);
			}
	      }
	      
	      return fileName;
	  }
	 void fillRow(Sheet sheet, int rowIndex,ArOverdueDetailsDTO overdueDetails) {
		 int cellIndex = 0;
	     Row row = (Row) sheet.createRow(rowIndex);
	      // 写入列数据
	     Cell id = row.createCell(cellIndex++);
	     id.setCellValue(overdueDetails.getInvoiceNo());
	     
	     Cell drawdate = row.createCell(cellIndex++);
	     drawdate.setCellValue(overdueDetails.getDrawDate());
	     
	     Cell materialId = row.createCell(cellIndex++);
	     materialId.setCellValue(overdueDetails.getMaterialId());
	     
	     Cell sku = row.createCell(cellIndex++);
	     sku.setCellValue(overdueDetails.getSku());
	     
	     Cell price = row.createCell(cellIndex++);
	     price.setCellValue(overdueDetails.getPrice().toString());
	     
	     Cell num = row.createCell(cellIndex++);
	     num.setCellValue(overdueDetails.getNum().toString());
	     
	     Cell amt = row.createCell(cellIndex++);
	     amt.setCellValue(overdueDetails.getAmt().toString());
	     
	     Cell tax = row.createCell(cellIndex++);
	     tax.setCellValue(overdueDetails.getTax().toString());
	     
	     Cell weight = row.createCell(cellIndex++);
	     weight.setCellValue(overdueDetails.getWeight().toString());
	     
	     Cell overnum = row.createCell(cellIndex++);
	     overnum.setCellValue(overdueDetails.getOverdueNum().toString());
     }
	@Override
	public String exportAll(Map<String, Object> params) throws IOException {
		int total = this.mapper.countArOverdue(params);
		params.put("begin", 0);
		params.put("end", total);
		List<ArOverdueDTO> list = this.mapper.selectArOverdueList(params);
		// 创建文件
		String fileName = "应收账龄表-" + ".xlsx";
		String filePath = Files.standardFolderPath(basePath) + fileName;
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		String title[] = { "客户ID", "客户名称", "客户SAP编码", "销售组织名称", "账期", "应收账款金额",
				"未逾期金额", "逾期1-30天", "逾期31-60天", "逾期61-90天", "逾期91-120天",
				"逾期121-180天", "逾期超过180天" };

		Workbook wb = null;
		OutputStream stream = null;
		try {
			wb = new XSSFWorkbook();
			stream = new FileOutputStream(file);
			Sheet sheet1 = (Sheet) wb.createSheet("应收账龄明细");

			Row header = (Row) sheet1.createRow(0);
			// 循环写入头列数据
			for (int j = 0; j < title.length; j++) {
				Cell cell = header.createCell(j);
				cell.setCellValue(title[j]);
			}
			// 内容
	        int rowIndex = 1;
	        for (ArOverdueDTO aroverdue : list) {
	          fillRow(sheet1, rowIndex++, aroverdue);
	        }
			wb.write(stream);
		} finally {
			IOUtils.closeQuietly(stream);
			if (wb instanceof Closeable) {
				IOUtils.closeQuietly((Closeable) wb);
			}
		}
		return fileName;
	}
	 void fillRow(Sheet sheet, int rowIndex,ArOverdueDTO aroverdue) {
		 int cellIndex = 0;
	     Row row = (Row) sheet.createRow(rowIndex);
	      // 写入列数据
	     Cell id = row.createCell(cellIndex++);
	     id.setCellValue(aroverdue.getMerchId());
	     
	     Cell custname = row.createCell(cellIndex++);
	     custname.setCellValue(aroverdue.getCustname());
	     
	     Cell sapcode = row.createCell(cellIndex++);
	     sapcode.setCellValue(aroverdue.getSapCustomerId());
	     
	     Cell orgname = row.createCell(cellIndex++);
	     orgname.setCellValue(aroverdue.getOrgname());
	     
	     Cell arperiod = row.createCell(cellIndex++);
	     arperiod.setCellValue(aroverdue.getArPeriod());
	     
	     Cell aramt = row.createCell(cellIndex++);
	     aramt.setCellValue(aroverdue.getAramt().toString());
	     
	     Cell overdue1 = row.createCell(cellIndex++);
	     overdue1.setCellValue(aroverdue.getOverdue1().toString());
	     
	     Cell overdue2 = row.createCell(cellIndex++);
	     overdue2.setCellValue(aroverdue.getOverdue2().toString());
	     
	     Cell overdue3 = row.createCell(cellIndex++);
	     overdue3.setCellValue(aroverdue.getOverdue3().toString());
	     
	     Cell overdue4 = row.createCell(cellIndex++);
	     overdue4.setCellValue(aroverdue.getOverdue4().toString());
	     
	     Cell overdue5 = row.createCell(cellIndex++);
	     overdue5.setCellValue(aroverdue.getOverdue5().toString());
	     
	     Cell overdue6 = row.createCell(cellIndex++);
	     overdue6.setCellValue(aroverdue.getOverdue6().toString());
	     
	     Cell overdue7 = row.createCell(cellIndex++);
	     overdue7.setCellValue(aroverdue.getOverdue7().toString());
     }
}
