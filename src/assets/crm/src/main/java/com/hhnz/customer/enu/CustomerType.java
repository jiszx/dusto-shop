package com.hhnz.customer.enu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户类别
 * @author: chaoyang.ren
 * @date:2016年11月28日
 * @time:下午3:17:03
 * @email:chaoyang.ren@foxmail.com
 */
public enum CustomerType {
	PARTNERS("1","合作盐业公司","G"),
	DELIVERY("2","配送商","P"),
	KA("3","KA","K"),
	SALT_COMPANY("4","盐业公司","Y"),
	RETAILER("5","零售商","L"),
	RECEIVER("6","送达方","S"),
	/**
	 * 供应商/仓储服务商
	 */
	VENDOR("7","仓储服务商","C"),
	/**
	 * 合作仓储服务商（一种特殊的送达方）
	 */
	COWAREHOUSING("70","合作仓储服务商","H"),
	LOGISTICS("8","物流商","W"),
	SPECIAL("9","特通渠道","T"),
	NKA("10","NKA","N")
	;
	private CustomerType(String code, String desc, String prefix){
		this.code = code;
		this.desc = desc;
		this.prefix = prefix;
	}
	
	private static final Map<String,CustomerType> CACHE = Collections.unmodifiableMap(new HashMap<String,CustomerType>(){
		private static final long serialVersionUID = 1L;
		{
			for (CustomerType rs : CustomerType.values()) {
				put(rs.getCode(), rs);
			}
		}
	});
	
	public static CustomerType toEnum(String code){
		return CACHE.get(code);
	}
	
	private String code;
	private String desc;
	private String prefix;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}
