package com.hhnz.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;

/**
 * @author: chaoyang.ren  
 * @date:Jul 7, 2017  
 * @time:11:46:38 AM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
public class ExcelExport {
	public static <T> ExcelResult export(String excelName, Collection<T> objects, ExcelType excelType,
			int beginRowNum, HttpServletResponse response, Class<T> type) throws IllegalArgumentException, IllegalAccessException, IOException{
		String fileName = URLEncoder.encode(excelName, "UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msexcel");
		response.setHeader("Content-type", "application/x-msexcel");
		response.setHeader("content-disposition", "attachment;filename=" + fileName);
		OutputStream outputStream = response.getOutputStream();
		ExcelResult excelResult = null;
		try {
			excelResult = ExcelUtil.exportExcelWithHeader(objects, excelType, beginRowNum, outputStream, type);
			outputStream.flush();
			return excelResult;
		} catch (Exception e) {
			throw e;
		}
	}
}

