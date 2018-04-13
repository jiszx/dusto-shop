package com.hhnz.order.enu;

public enum OrderDeliveryType {
	FREEDELIVERY("1","包邮"),
	NOMAIL("2","不包邮"),
	PICKUP("3","自提");
	private String code;

	private String desc;
	private OrderDeliveryType(String code, String desc) {
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
