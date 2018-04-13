package com.hhnz.jco.business.inventory;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.BaseInput;

/**
 * 库存调拨查询传入参数
 * @author: chaoyang.ren
 * @date:Apr 25, 2017
 * @time:2:41:04 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
public class QueryInputDTO extends BaseInput{
	
	@SerializedName("IN_MATNR")
	private String materialId;
	@SerializedName("IN_WERKS")
	private String factoryCode;
	@SerializedName("IN_LGORT")
	private String rdcCode;
	@SerializedName("IN_RULE")
	private String rule;

	
	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getRdcCode() {
		return rdcCode;
	}

	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Override
	public Serializable getCrmRelatedId() {
		return null;
	}

	public void setCrmRelatedId(Long crmRelatedId) {
	}
	
}
