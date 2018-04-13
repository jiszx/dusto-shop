package com.hhnz.order.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.order.service.IOrderCancelService;
import com.hhnz.util.AjaxDTO;

/**
 * 订单取消Controller
 * 
 * @author hhnz-skevin 2016-10-24
 *
 */
@Controller
@RequestMapping("/order/cancel")
public class OrderCancelController {

	@Resource
	private IOrderCancelService cancelservice;

	@RequestMapping("/orderCancel.html")
	public String orderCancel() throws Exception {
		return "order/orderCancel";
	}

	@RequestMapping("cancelList")
	@ResponseBody
	public AjaxDTO cancelList(@ModelAttribute AjaxDTO bean, String custname,
			String bdate, String edate, Long crmorderid, String saporderid,
			String orderType, String salesman, HttpServletRequest request) throws Exception {
        TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		Map<String, Object> params = new HashMap<String, Object>();
		if(user!=null && user.getMerchId()!=null){
		  params.put("pid", user.getMerchId());
		}
		params.put("custname", custname);
		params.put("bdate", bdate);
		params.put("edate", edate);
		params.put("crmorderid", crmorderid);
		params.put("saporderid", saporderid);
		params.put("orderType", orderType);
		params.put("salesman", salesman);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit());
		return this.cancelservice.cancelList(params);
	}

	@RequestMapping("doCancel")
	public @ResponseBody Map<String, Object> doCancel(Long id,HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.cancelservice.doCancel(id,user);
		return map;
	}
}
