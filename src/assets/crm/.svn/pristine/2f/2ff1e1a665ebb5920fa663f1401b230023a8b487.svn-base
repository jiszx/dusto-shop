package com.hhnz.order.dto;

import java.util.ArrayList;
import java.util.List;

import com.hhnz.rmi.db.model.customer.Customer;
import com.hhnz.rmi.db.model.order.Order;

/**
 * @author: chaoyang.ren  
 * @date:Mar 28, 2017  
 * @time:3:54:04 PM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
public class DeliveryOrderDTO {
	private Order order;
	
	private Customer customer;

	private List<DeliveryOrderItemDTO> orderItems = new ArrayList<DeliveryOrderItemDTO>();
	
	private String token;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<DeliveryOrderItemDTO> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<DeliveryOrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}

