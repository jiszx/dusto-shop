package com.hhnz.crm.enu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 调整价调整条件类目
 * @author: chaoyang.ren  
 * @date:Sep 14, 2017  
 * @time:5:21:30 PM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
public enum PriceAdjustCategory {
	SUB_BRAND("1","副品牌","attribute1"),
	SERIES("2","系列","series"),
	MATERIAL_ID("3","物料编号","sapIds")
	;
	private PriceAdjustCategory(String code, String desc, String dbProperty){
		this.code = code;
		this.desc = desc;
		this.domainProperty = dbProperty;
	}
	
	private static final Map<String,PriceAdjustCategory> CACHE = Collections.unmodifiableMap(new HashMap<String,PriceAdjustCategory>(){
		private static final long serialVersionUID = 1L;
		{
			for (PriceAdjustCategory rs : PriceAdjustCategory.values()) {
				put(rs.getCode(), rs);
			}
		}
	});
	
	public static PriceAdjustCategory toEnum(String code){
		return CACHE.get(code);
	}
	
	public static boolean contains(String code) {
		return CACHE.containsKey(code);
	}
	
	private String code;
	private String desc;
	private String domainProperty;
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
	public String getDomainProperty() {
		return domainProperty;
	}
	public void setDomainProperty(String domainProperty) {
		this.domainProperty = domainProperty;
	}
}

