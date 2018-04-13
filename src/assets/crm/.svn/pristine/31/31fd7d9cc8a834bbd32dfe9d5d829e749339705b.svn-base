package com.hhnz.crm.enu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: chaoyang.ren  
 * @date:Sep 14, 2017  
 * @time:5:21:30 PM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
public enum PriceAdjustType {
	STANDARD("1","标准价"),
	ADJUSTED("2","调整价")
	;
	private PriceAdjustType(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	private static final Map<String,PriceAdjustType> CACHE = Collections.unmodifiableMap(new HashMap<String,PriceAdjustType>(){
		private static final long serialVersionUID = 1L;
		{
			for (PriceAdjustType rs : PriceAdjustType.values()) {
				put(rs.getCode(), rs);
			}
		}
	});
	
	public static PriceAdjustType toEnum(String code){
		return CACHE.get(code);
	}
	
	public static boolean contains(String code) {
		return CACHE.containsKey(code);
	}
	
	private String code;
	private String desc;
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
	
}

