package com.hhnz.crm.enu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 调整价调整操作
 * @author: chaoyang.ren  
 * @date:Sep 14, 2017  
 * @time:5:21:30 PM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
public enum PriceAdjustOperation {
	ADD("1","加上"),
	MULTIPLY("2","乘以"),
	OVERRIDE("3","覆盖")
	;
	private PriceAdjustOperation(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	private static final Map<String,PriceAdjustOperation> CACHE = Collections.unmodifiableMap(new HashMap<String,PriceAdjustOperation>(){
		private static final long serialVersionUID = 1L;
		{
			for (PriceAdjustOperation rs : PriceAdjustOperation.values()) {
				put(rs.getCode(), rs);
			}
		}
	});
	
	public static PriceAdjustOperation toEnum(String code){
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

