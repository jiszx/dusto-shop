package com.hhnz.customer.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

public class CustomerInvDTO {
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 盘点月份
	 */
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date date;

	private List<Long> stationIds;

	private Long merchid;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		if (StringUtils.isBlank(customerName)) {
			return;
		}
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		if (StringUtils.isBlank(productName)) {
			return;
		}
		this.productName = productName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getMerchid() {
		return merchid;
	}

	public void setMerchid(Long merchid) {
		this.merchid = merchid;
	}

	public List<Long> getStationIds() {
		return stationIds;
	}

	public void setStationIds(List<Long> stationIds) {
		this.stationIds = stationIds;
	}

}
