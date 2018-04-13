package com.hhnz.jco.business.account;

/**
 * RFC业务执行函数枚举
 * @author: chaoyang.ren
 * @date:2016年8月11日
 * @time:上午10:03:42
 * @email:chaoyang.ren@foxmail.com
 */
public enum OperationType {
	/**
	 * 现金
	 */
	CASH("D","现金","DZ"),
	/**
	 * 调账
	 */
	ADJUST("C","调账","SA"),
	/**
	 * 票据
	 */
	BILL("B","票据","DZ"),
	/**
	 * 保证金
	 */
	MARGINS("A","保证金","SA");
	
	private String code;
	private String desc;
	private String voucherType;
	public String getCode() {
		return code;
	}
	/**
	 * @param code
	 * @param desc
	 * @param voucherType
	 */
	private OperationType(String code, String desc, String voucherType) {
		this.code = code;
		this.desc = desc;
		this.voucherType = voucherType;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
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
