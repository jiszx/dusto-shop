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
import com.hhnz.order.service.IOrderCloseService;
import com.hhnz.util.AjaxDTO;

/**
 * 订单关闭Controller
 * 
 * @author hhnz-skevin
 *
 */
@Controller
@RequestMapping("/order/close")
public class OrderCloseController {

	@Resource
	private IOrderCloseService closeService;

	@RequestMapping("orderClose.html")
	public String orderClose() throws Exception {
		return "order/orderClose";
	}

	@RequestMapping("closeList")
	@ResponseBody
	public AjaxDTO closeList(@ModelAttribute AjaxDTO bean, String custname,
			String bdate, String edate, Long crmorderid, String saporderid)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custname", custname);
		params.put("bdate", bdate);
		params.put("edate", edate);
		params.put("crmorderid", crmorderid);
		params.put("saporderid", saporderid);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset()+bean.getLimit());
		return this.closeService.closeList(params);
	}

	@RequestMapping("doClose")
	@ResponseBody
	public Map<String, Object> doClose(Long id,HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		Map<String, Object> result = new HashMap<String, Object>();
		result = this.closeService.doClose(id,user);
		return result;
	}
}
