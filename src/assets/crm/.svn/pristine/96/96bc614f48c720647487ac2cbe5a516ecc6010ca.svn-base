package com.hhnz.jco.business.vendor;

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
	@SerializedName("EXPORT_LIFNR")
	private String sapCustomerId;
	public String getSapCustomerId() {
		return sapCustomerId;
	}
	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
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
