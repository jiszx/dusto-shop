package com.hhnz.account.service.impl;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.MoreObjects;
import com.hhnz.account.mapper.AccountUtilMapper;
import com.hhnz.account.model.CMerchCustAccountV;
import com.hhnz.account.service.IAccountService;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.service.IDictService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Excels;
import com.hhnz.util.Files;

/**
 * 账户余额管理Service
 * @author hhnz-skevin 
 *
 */
@Service
@Transactional
public class AccountServiceImpl implements IAccountService {
	@Resource
	private AccountUtilMapper accountutilmapper;
	@Value("${upload.file.path}")
	private String basePath;
	@Resource
	private IDictService dictService;
	
	/**
	 * 获取指定客户的账户余额(不包含零售商)
	 */
	@Override
	public CMerchCustAccountV getAccountByMerchId(Long merchCustId) {
		return this.accountutilmapper.getAccountByMerchId(merchCustId);
	}
	
	/**
	 * 获取零售商资金明细
	 */
	@Override
	public AjaxDTO getAccountDetailsList(AjaxDTO bean, Long pid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("merchid", pid);//零食商对应的配送商ID
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit()+bean.getOffset());
		List<CMerchCustAccountV> list = this.accountutilmapper.getaccountByPid(params);
		bean.setRows(list);
		bean.setTotal(list.size());
		return bean;
	}
	
	/**
	 * 账户余额list(不包含零售商)
	 */
	@Override
	public AjaxDTO getMerchAccount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		List<CMerchCustAccountV> list = new ArrayList<CMerchCustAccountV>();
		int count =0;
		TEmployee user =(TEmployee) params.get("user");
		if(StringUtils.equals("1",user.getUserType())){
			params.put("merchid", user.getMerchId());
			list = this.accountutilmapper.getRetailMerchaccount(params);
			count = accountutilmapper.countRetailMerchaccount(params);
		}else{
			list = this.accountutilmapper.getmerchaccount(params);
			count = accountutilmapper.countMerchaccount(params);
		}
		dto.setRows(list);
		dto.setTotal(count);
		return dto;
	}
	
	@Override
	public String generateAccountExcel(Map<String, Object> params) throws IOException{
	  List<CMerchCustAccountV> accounts = accountutilmapper.getmerchaccount(params);
	  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	  String day = sf.format(new Date());
	// 创建文件
      String fileName = "客户账户余额"+day + ".xlsx";
      String filePath = Files.standardFolderPath(basePath) + fileName;
      File file = new File(filePath);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      String title[] = {"客户编码", "SAP客户编码", "客户名称", "客户类型", "销售组织", "可用现金", "可用货补", "可用授信",
          "可用合计", "授信额度", "冻结现金授信合计", "冻结货补", "保证金"};
      
      Workbook wb = null;
      OutputStream stream = null;
      try{
        wb = new XSSFWorkbook();
        stream = new FileOutputStream(file);
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        // 写入头数据
        Row header = (Row) sheet1.createRow(0);
        // 循环写入头列数据
        for (int j = 0; j < title.length; j++) {
          Cell cell = header.createCell(j);
          cell.setCellValue(title[j]);
        }
        // 内容
        List<TDict> custTypes = dictService.findByName("CUST_MERCH_TYPE");
        int rowIndex = 1;
        for (CMerchCustAccountV acc : accounts) {
          fillRow(sheet1, rowIndex++, acc, custTypes);
        }
        wb.write(stream);
        Files.deleteFileDelay(file, 3, TimeUnit.MINUTES);
      }finally{
        IOUtils.closeQuietly(stream);
        IOUtils.closeQuietly((Closeable)wb);
      }
      
      return fileName;
	  
	}
	
	private void fillRow(Sheet sheet, int rowIndex, CMerchCustAccountV acc, List<TDict> custTypes){
	  int cellIndex = 0;
      Row row = (Row) sheet.createRow(rowIndex);
      // 写入列数据
      Excels.fillCell(row, cellIndex++, String.valueOf(acc.getMerchCustId()));
      Excels.fillCell(row, cellIndex++, acc.getSapCustomerId());
      Excels.fillCell(row, cellIndex++, acc.getCustname());
      Excels.fillCell(row, cellIndex++, dictService.code2Str(acc.getCustType(), custTypes));
      Excels.fillCell(row, cellIndex++, acc.getOrgname());
      Excels.fillCell(row, cellIndex++, MoreObjects.firstNonNull(acc.getCashAmt(), BigDecimal.ZERO).toString());
      Excels.fillCell(row, cellIndex++, MoreObjects.firstNonNull(acc.getSubsidyAmt(), BigDecimal.ZERO).toString());
      Excels.fillCell(row, cellIndex++, MoreObjects.firstNonNull(acc.getCreditAmt(), BigDecimal.ZERO).toString());
      Excels.fillCell(row, cellIndex++, MoreObjects.firstNonNull(acc.getAllamt(), BigDecimal.ZERO).toString());
      Excels.fillCell(row, cellIndex++, MoreObjects.firstNonNull(acc.getCreditLines(), BigDecimal.ZERO).toString());
      Excels.fillCell(row, cellIndex++, MoreObjects.firstNonNull(acc.getFrozenAmt(), BigDecimal.ZERO).toString());
      Excels.fillCell(row, cellIndex++, MoreObjects.firstNonNull(acc.getFrozenSubsidy(), BigDecimal.ZERO).toString());
      Excels.fillCell(row, cellIndex++, MoreObjects.firstNonNull(acc.getBondAmt(), BigDecimal.ZERO).toString());
	}

}
