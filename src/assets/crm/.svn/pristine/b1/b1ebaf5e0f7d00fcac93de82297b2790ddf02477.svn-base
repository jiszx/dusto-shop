package com.hhnz.logistics.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.logistics.dto.LogisticsOrderDTO;
import com.hhnz.logistics.service.ILogisticsOrderService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ExcelExport;
import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;

/**
 * 物流订单查询
 * @author skevin 2017-08-30
 *
 */
@RequestMapping("logisticsOrder")
@Controller
public class LogisticsOrderController {
	@Resource
	private IPowerOrgService  powerService;
	
	@Resource
	private ILogisticsOrderService orderservice;
	/**
	 * 订单查询页面跳转
	 * @return
	 */
	@RequestMapping("/logisticsOrder.html")
	public String InvAllocationList(){
		return "logistics/logisticsOrder";
	}
	/**
	 * 订单查询
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderlist")
	public @ResponseBody AjaxDTO listPage(@ModelAttribute AjaxDTO bean, String states, String custname, String bdate,
			String edate, String orderType, Long crmorderid, String saporderid, String shipname, String saler,
			String transferOrderId, HttpServletRequest request) {
		Map<String, Object> params = constructParam(bean, states, custname, bdate, edate, orderType, crmorderid,
				saporderid, shipname, saler, transferOrderId, request);
		AjaxDTO dto = this.orderservice.selectOrderList(params);
		return dto;
	}
	private Map<String, Object> constructParam(AjaxDTO bean, String states, String custname, String bdate, String edate,
			String orderType, Long crmorderid, String saporderid, String shipname, String saler, String transferOrderId,
			HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession()
				.getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		List<Long> stationids = this.powerService.getUserStations(user,
				userjobs, station);// 获取用户可以查看的岗位
		params.put("states", Strings.emptyToNull(states));
		if (StringUtils.isNotEmpty(orderType)) {
			if (orderType.contains(",")) {
				params.put("ordertypes", orderType.split(","));
			} else {
				params.put("ordertype", orderType);
			}
		}
		if (user.getMerchId() != null) {
			params.put("shipid", user.getMerchId());
		}
		params.put("userId", user.getId());
		if (StringUtils.isNotEmpty(shipname)) {
			params.put("shipname", shipname);
		}
		if(StringUtils.isNotEmpty(transferOrderId)) {
			params.put("transferOrderId", transferOrderId);
		}
		params.put("stations", stationids);
		params.put("custname", Strings.emptyToNull(custname));
		params.put("bdate", Strings.emptyToNull(bdate));
		params.put("edate", Strings.emptyToNull(edate));
		params.put("crmorderid", crmorderid);
		params.put("saporderid", Strings.emptyToNull(saporderid));
		params.put("saler", Strings.emptyToNull(saler));
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit());
		return params;
	}
	/**
	 * 订单导出
	 * 
	 * @param bean
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping("exportOrder")
	public void exportOrder(@ModelAttribute AjaxDTO bean,
			String states, String custname, String bdate, String edate,
			String orderType, Long crmorderid, String saporderid,
			String shipname, String saler, String transferOrderId, HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalArgumentException, IllegalAccessException
	{
		Map<String, Object> params = constructParam(bean, states, custname, bdate, edate, orderType, crmorderid,
				saporderid, shipname, saler, transferOrderId, request);
		List<LogisticsOrderDTO> orderList = orderservice.exportOrderDetailListAll(params);
		String excelName = "订单明细.xlsx";
		ExcelResult excelResult = ExcelExport.export(excelName, orderList, ExcelType.XLSX, 0, response, LogisticsOrderDTO.class);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("file", excelName);
		result.put("totalNum", excelResult.getTotalNum());
		result.put("successNum", excelResult.getSuccessNum());
		result.put("failureNum", excelResult.getFailureNum());
		request.setAttribute("result", result);
		
	}
}
