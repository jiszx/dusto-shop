package com.hhnz.logistics.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.logistics.dto.DeploymentDTO;
import com.hhnz.logistics.service.IDeploymentService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ExcelExport;
import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;

/**
 * 物流模块--调拨单查询
 * @author hhnz-skevin  2017-08-30
 *
 */
@RequestMapping("deployment")
@Controller
public class DeploymentController {
	
	@Autowired
	private IDeploymentService service;
	
	@Resource
	private IPowerOrgService  powerService;
	
	
	/**
	 * 调拨单查询页面跳转
	 * @return
	 */
	@RequestMapping("/logisticsInvAllocationList.html")
	public String InvAllocationList(){
		return "logistics/logisticsInvAllocationList";
	}
	/**
	 * 调拨单查询
	 * @param bean
	 * @param custname
	 * @param id
	 * @param sapId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@ResponseBody
	public AjaxDTO getList(@ModelAttribute AjaxDTO bean,String custname,Long id,String sapId,
			String startTime, String endTime, String custType, String addr,String orderStates, HttpServletRequest request) throws Exception{
		Map<String, Object> params = constructParam(bean, custname, id, sapId, startTime, endTime, custType, addr,orderStates, request);
		return this.service.SelectDeploymentList(params);
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> constructParam(AjaxDTO bean, String custname, Long id, String sapId,
			String startTime, String endTime, String custType, String addr,String orderStates,
			HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		if (bean != null) {
			params.put("begin", bean.getOffset());
			params.put("end", bean.getOffset() + bean.getLimit());
		}
		params.put("id", id);
		params.put("sapId", sapId);
		params.put("custname", custname);
		params.put("merchid", "1".equals(user.getUserType())?user.getMerchId():null);
		params.put("stationids", stationids);
		if(StringUtils.isNotEmpty(startTime)) {
			params.put("startTime", startTime);
		}
		if(StringUtils.isNotEmpty(endTime)) {
			params.put("endTime", endTime);
		}
		if(StringUtils.isNotEmpty(custType)) {
			params.put("custType", custType);
		}
		if(StringUtils.isNotEmpty(addr)) {
			params.put("addr", addr);
		}
		params.put("userid", user.getId());
		params.put("orderStates", orderStates);
		return params;
	}
	/**
	 * 导出
	 * 
	 * @param bean
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping("exportOrder")
	public void exportOrder(@ModelAttribute AjaxDTO bean,String custname,Long id,String sapId,
			String startTime, String endTime, String custType, String addr,String orderStates,
			HttpServletRequest request,HttpServletResponse response) throws IOException, IllegalArgumentException, IllegalAccessException
	{
		Map<String, Object> params = constructParam(bean, custname, id, sapId, startTime, endTime, custType, addr,orderStates, request);
		List<DeploymentDTO> orderList = service.exportListAll(params);
		String excelName = "调拨单明细.xlsx";
		ExcelResult excelResult = ExcelExport.export(excelName, orderList, ExcelType.XLSX, 0, response, DeploymentDTO.class);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("file", excelName);
		result.put("totalNum", excelResult.getTotalNum());
		result.put("successNum", excelResult.getSuccessNum());
		result.put("failureNum", excelResult.getFailureNum());
		request.setAttribute("result", result);
		
	}
}
