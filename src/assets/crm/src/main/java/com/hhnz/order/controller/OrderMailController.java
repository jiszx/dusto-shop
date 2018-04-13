package com.hhnz.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.order.dto.DeliveryOrderDTO;
import com.hhnz.order.service.OrderMailService;
import com.hhnz.rmi.dto.base.ResultDTO;
import com.hhnz.rmi.util.token.TokenManager;
import com.hhnz.rmi.util.token.jwt.JWTToken;

/**
 * @author: chaoyang.ren
 * @date:Mar 28, 2017
 * @time:2:19:20 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Controller
@RequestMapping("/order/mail")
public class OrderMailController {

	@Autowired
	private OrderMailService orderMailService;
	
	@RequestMapping(value = "/delivery/print", method = RequestMethod.GET)
	public ModelAndView viewDeliveryOrder(String t){
		ModelAndView mv = new ModelAndView("order/deliveryOrder");
		TokenManager tokenManager = new JWTToken(OrderMailService.LOGSTICS_PRINT_TOKEN_KEY);
		ResultDTO<Map<String, Object>> info = tokenManager.verify(t);
		if(info.isSuccess()){
			Map<String, Object> data = info.getData();
			Long orderId = Long.parseLong(data.get("orderId").toString());
			DeliveryOrderDTO deliveryOrder = orderMailService.searchDeliveryOrder(orderId);
			mv.addObject("order", deliveryOrder);
		}
		return mv;
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public int sendTest(Long orderId){
		orderMailService.logisticsNotice(orderId);
		return 1;
	}
}
