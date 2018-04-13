package com.hhnz.jco.business.customer;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.BaseResultDTO;

/**
 * @author: chaoyang.ren
 * @date:2016年8月19日
 * @time:下午3:17:07
 * @email:chaoyang.ren@foxmail.com
 */
public class ResultDTO extends BaseResultDTO{
	/**
	 * SAP客户编号
	 */
	@SerializedName("OUT_KUNNR")
	private String sapCustomerId;
	/**
	 * CRM客户编号
	 */
	@SerializedName("OUT_CUSTOMER")
	private String customerId;
	public String getSapCustomerId() {
		return sapCustomerId;
	}
	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public Object getData() {
		return this.getSapCustomerId();
	}
	@Override
	public void setData(Object data) {
		super.data = this.getSapCustomerId();
	}
}
