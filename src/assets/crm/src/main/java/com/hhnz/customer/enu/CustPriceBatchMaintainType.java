package com.hhnz.customer.enu;

public enum CustPriceBatchMaintainType {
	CIFPRICE("1","到岸价调整"),
	MERCHPRICE("2","客户价格调整"),
	IMPORT("3","导入");
	private String code;

	private String desc;
	private CustPriceBatchMaintainType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
