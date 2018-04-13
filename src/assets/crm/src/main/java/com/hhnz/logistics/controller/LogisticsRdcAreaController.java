package com.hhnz.logistics.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.logistics.dto.LogisticsRdcAreaDTO;
import com.hhnz.logistics.service.ILogisticsRdcAreaService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ExcelExport;
import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;

/**
 * 物流模块--权限配置
 * 
 * @author hhnz-skevin 2017-09-08
 *
 */
@RequestMapping("logisticsRdcArea")
@Controller
public class LogisticsRdcAreaController {

	@Resource
	private ILogisticsRdcAreaService logisticsservice;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/logisticsRdcArea.html")
	public String InvAllocationList() {
		return "logistics/logisticsRdcArea";
	}

	@RequestMapping("list")
	@ResponseBody
	public AjaxDTO selectUserRdcList(@ModelAttribute AjaxDTO bean,
			String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit());
		params.put("username", username);
		return this.logisticsservice.selectUserRdcList(params);
	}

	@RequestMapping("getRdcAreaByUserId")
	@ResponseBody
	public List<String> getRdcAreaByUserId(@RequestParam("id") Long id)
			throws Exception {
		List<String> ids = this.logisticsservice.getRdcAreaByUserId(id);
		return ids;
	}

	@RequestMapping("addUserRdc")
	@ResponseBody
	public int addUserRdc(Long userId, String rdcCode) {
		return this.logisticsservice.addUserRdc(userId, rdcCode);
	}

	@RequestMapping(value = "saveRdcArea", method = RequestMethod.POST)
	public @ResponseBody Integer saveRdcArea(
			@RequestParam("logisticsRdcId") Long id,
			@RequestParam("areaId") List<String> areas) throws Exception {
		return this.logisticsservice.saveRdcArea(id, areas);
	}
	@RequestMapping(value ="del",method = RequestMethod.POST)
	public @ResponseBody Integer  del(@RequestParam("id") Long id){
		return this.logisticsservice.del(id);
	}
	
	@RequestMapping("exportExl")
	public void exportExl(@ModelAttribute AjaxDTO bean,String username,
			HttpServletRequest request,HttpServletResponse response) throws IOException, IllegalArgumentException, IllegalAccessException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit());
		params.put("username", username);
		List<LogisticsRdcAreaDTO> list = this.logisticsservice.getexportExl(params);
		String excelName = "物流RDC设置明细.xlsx";
		ExcelResult excelResult = ExcelExport.export(excelName, list, ExcelType.XLSX, 0, response, LogisticsRdcAreaDTO.class);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("file", excelName);
		result.put("totalNum", excelResult.getTotalNum());
		result.put("successNum", excelResult.getSuccessNum());
		result.put("failureNum", excelResult.getFailureNum());
		request.setAttribute("result", result);
		
	}
}
