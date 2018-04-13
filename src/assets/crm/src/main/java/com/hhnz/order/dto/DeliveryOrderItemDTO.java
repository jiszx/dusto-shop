package com.hhnz.order.dto;

import java.math.BigDecimal;

import com.hhnz.rmi.db.model.material.MaterialBase;
import com.hhnz.rmi.db.model.order.OrderSplits;

/**
 * @author: chaoyang.ren  
 * @date:Mar 28, 2017  
 * @time:3:54:04 PM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
public class DeliveryOrderItemDTO {
	private OrderSplits orderSplits;
	private MaterialBase materialBase;
	private BigDecimal boxWeight;
	private BigDecimal totalWeight;
	
	public OrderSplits getOrderSplits() {
		return orderSplits;
	}
	public void setOrderSplits(OrderSplits orderSplits) {
		this.orderSplits = orderSplits;
	}
	public MaterialBase getMaterialBase() {
		return materialBase;
	}
	public void setMaterialBase(MaterialBase materialBase) {
		this.materialBase = materialBase;
	}
	public BigDecimal getBoxWeight() {
		return boxWeight;
	}
	public void setBoxWeight(BigDecimal boxWeight) {
		this.boxWeight = boxWeight;
	}
	public BigDecimal getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}
	
}

