package com.hhnz.account.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hhnz.account.model.CDistributorsUpaccount;
import com.hhnz.account.service.IUpAccountService;
import com.hhnz.account.validator.DistributorsUpAccountValidator;
import com.hhnz.util.AjaxDTO;

/**
 * 配送商资金Controller
 * 
 * @author hhnz-skevin 2016-09-08
 *
 */
@Controller
@RequestMapping("account/distributors")
public class DistributorsAccountController {

	@Resource
	private IUpAccountService service;
	
	@Resource
	private DistributorsUpAccountValidator validator;

	@RequestMapping("distributorsaccount.html")
	public String distributorsaccount() throws Exception {
		return "account/distributorsaccount";
	}

	@RequestMapping("list")
	public @ResponseBody AjaxDTO list(@ModelAttribute AjaxDTO bean,
			String orgid, String custname, String states,String bankSerial) {
		bean = this.service.getdistributorsList(bean, orgid, custname, states,bankSerial);
		return bean;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("add")
	@ResponseBody
	public String distributorAdd(String orgid, Long custid, MultipartFile file)
			throws FileNotFoundException, IOException {
		InputStream is;
		HSSFWorkbook workbook = null;
		is = file.getInputStream();
		POIFSFileSystem fs = new POIFSFileSystem(is);
		workbook = new HSSFWorkbook(fs);
		// 获取工作表
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 获取有效行数
		int lrnum = sheet.getLastRowNum();
		if (lrnum > 0) {
			for (int i = 0; i < lrnum; i++) {
				HSSFRow row = sheet.getRow(i);
				CDistributorsUpaccount distributor = new CDistributorsUpaccount();
				if (row == null) {
					continue;
				}
				distributor.setMerchCustId(custid);
				distributor.setOrganizationId(orgid);
				String retailer_name="";
				Long   retailer_id = null;
				BigDecimal  amt = new BigDecimal(0);
				for (short j = 0; j <= row.getLastCellNum(); j++) {
					HSSFCell cell = null;
					cell = row.getCell(j);
					if (cell != null) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							retailer_name = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							amt = new BigDecimal(cell.getNumericCellValue());
							break;
						}
					}
				}
				distributor.setAmt(amt);
				distributor.setRetailerId(retailer_id);
				distributor.setRetailerName(retailer_name);
				//验证传入数据
				String validator = this.validator.validateAdd(distributor);
				if(StringUtils.equals("validated", validator)){
					distributor.setStates("1");
				}else{
					distributor.setStates("0");
					distributor.setAttribute1(validator);
				}
				this.service.addDistributorAdd(distributor);
			}
		}
		return "";
	}
	
}
