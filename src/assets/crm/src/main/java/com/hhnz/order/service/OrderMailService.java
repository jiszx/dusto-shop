package com.hhnz.order.service;

import com.hhnz.order.dto.DeliveryOrderDTO;

/**
 * @author: chaoyang.ren  
 * @date:Mar 28, 2017  
 * @time:3:51:27 PM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
public interface OrderMailService {
	public final String LOGSTICS_PRINT_TOKEN_KEY = "logis_o_print";
	
	public DeliveryOrderDTO searchDeliveryOrder(Long orderId);

	public boolean logisticsNotice(Long orderId);
	
}

