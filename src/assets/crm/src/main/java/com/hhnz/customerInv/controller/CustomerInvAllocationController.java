package com.hhnz.customerInv.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customerInv.dto.CustomerInvAllocationDTO;
import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OrderDetail;
import com.hhnz.order.model.OrderSearchModel;
import com.hhnz.order.service.OrderService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ExcelExport;
import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;


/**
 * 客户库存管理--调拨controller
 * @author hhnz-skevin  2016-12-06
 *
 */
@RequestMapping("customerInvAllocation")
@Controller
public class CustomerInvAllocationController {
	
	@Resource
	private  CustomerStockService  service;
	
	@Resource
	private IPowerOrgService  powerService;
	
	@Resource
	private OrderService orderservice;
	
	/**
	 * 调拨单录入
	 * @return
	 */
	@RequestMapping("/customerInvAllocation.html")
	public String  customerInvAllocation(){
		return "customerInv/customerInvAllocation";
	}
	
	
	/**
	 * 特殊调拨单录入
	 * @return
	 */
	@RequestMapping("/specialAllocation.html")
	public String  specialAllocation(String type, Model model){
        model.addAttribute("type", "2".equals(type) ? "2" : "1");
		return "customerInv/specialAllocation";
	}
	/**
	 * 调拨单查询页面跳转
	 * @return
	 */
	@RequestMapping("/InvAllocationList.html")
	public String InvAllocationList(){
		return "customerInv/invAllocationList";
	}
	
	@RequestMapping("/makesure.html")
    public ModelAndView makesure(Long id){
	  ModelAndView mv = new ModelAndView();
      OrderDetail detail = orderservice.orderDelivery(id);
      mv.addObject("order", detail);
      mv.setViewName("customerInv/makeSure");
      return mv;
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
			String startTime, String endTime, String custType, String addr, HttpServletRequest request) throws Exception{
		Map<String, Object> params = constructParam(bean, custname, id, sapId, startTime, endTime, custType, addr, request);
		return this.service.getList(params);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> constructParam(AjaxDTO bean, String custname, Long id, String sapId,
			String startTime, String endTime, String custType, String addr,
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
	public void exportOrder(@ModelAttribute AjaxDTO bean,String custname,Long id,String sapId,
			String startTime, String endTime, String custType, String addr,
			HttpServletRequest request,HttpServletResponse response) throws IOException, IllegalArgumentException, IllegalAccessException
	{
		Map<String, Object> params = constructParam(bean, custname, id, sapId, startTime, endTime, custType, addr, request);
		List<CustomerInvAllocationDTO> orderList = service.getListAll(params);
		String excelName = "调拨单明细.xlsx";
		ExcelResult excelResult = ExcelExport.export(excelName, orderList, ExcelType.XLSX, 0, response, CustomerInvAllocationDTO.class);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("file", excelName);
		result.put("totalNum", excelResult.getTotalNum());
		result.put("successNum", excelResult.getSuccessNum());
		result.put("failureNum", excelResult.getFailureNum());
		request.setAttribute("result", result);
		
	}
	
	/**
	 * 提交审批
	 * @param id
	 * @param states
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doAudit")
	@ResponseBody
	public  String doAudit(Long id,String states,HttpServletRequest request) throws Exception{
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		orderservice.startProcess(user,id,"5");
		return null;
	}
	
	//验证库存数量和金额
	@RequestMapping("ValidateAmtAndNum")
	@ResponseBody
	public  Map<String,Object> ValidateAmtAndNum(Long id) throws Exception{
		return this.service.ValidateAmtAndNum(id);
	}
	
	@RequestMapping("orderLineDetails")
	@ResponseBody
	public AjaxDTO  orderLineDetails(Long id) throws Exception{
		return this.service.getOrderLineDetails(id);
	}
	
	@RequestMapping("orderAudit")
	@ResponseBody
	public String orderAudit(Long id,HttpServletRequest request)throws Exception{
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		OmOrderHeadersAll header = this.service.selectByPrimaryKey(id);
		this.orderservice.orderAudit(Long.parseLong(header.getAttribute13()),"2", "7");
	    orderservice.startProcess(user,Long.parseLong(header.getAttribute13()),"7");
	    return "";
	}
	
	/**
	 * 调拨单确认收货
	 * @param makesureDto
	 * @param orderId
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping("submitMakeSure.html")
    public String submitmakeSure(OrderMakesureDto makesureDto, Long orderId)throws Exception{
        orderservice.updateMakesure(makesureDto.getOrders());
        orderservice.orderAudit(orderId, "8", "5");
        service.addMerchInv(orderId);
        return "redirect:InvAllocationList.html";
    }*/
	
	@ResponseBody
	@RequestMapping("/export")
	public String exportTransferDetail(String custname,Long id,String sapId,
			String startTime, String endTime, String custType, String addr, HttpServletRequest request) throws IOException{
//	  Map<String, Object> params = new HashMap<String, Object>();
//      List<Long> stationids =  powerService.stations(request);
//      params.put("stationids", stationids);
      Map<String, Object> params = constructParam(null, custname, id, sapId, startTime, endTime, custType, addr, request);
      return service.generateTransferDetails(params);
	}
	
	@RequestMapping("closeOrder")
	@ResponseBody
	public String closeOrder(Long id) throws Exception{
		return this.service.closeOrder(id);
	}
}
