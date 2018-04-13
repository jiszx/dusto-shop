package com.hhnz.customer.enu;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户状态说明
 * 1潜在客户，2,待审批客户,3正式客户，4休眠客户，5失效客户
 * @author: chaoyang.ren
 * @date:2016年8月18日
 * @time:上午9:40:31
 * @email:chaoyang.ren@foxmail.com
 */
public enum CustomerBaseStateEnu {
	/**
	 * 潜在客户
	 */
	POTENTIAL("1","潜在客户"),
	/**
	 * 待审批客户
	 */
	SUBMITED("2","待审批客户"),
	/**
	 * 正式客户
	 */
	FORMAL("3","正式客户"),
	/**
	 * 休眠客户
	 */
	DORMANT("4","休眠客户"),
	/**
	 * 失效客户
	 */
	INEFFECTIVE("5","失效客户");
	
	/**
	 * 客户状态编码
	 */
	private String code;
	/**
	 * 客户状态描述
	 */
	private String desc;
	/**
	 * @param code
	 * @param desc
	 */
	private CustomerBaseStateEnu(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	private static Map<String, CustomerBaseStateEnu> CACHE = new HashMap<String, CustomerBaseStateEnu>() {
		private static final long serialVersionUID = 1L;
		{
            for (CustomerBaseStateEnu enu : CustomerBaseStateEnu.values()) {
            	put(enu.getCode(), enu);
			}
        }
    };
    
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
	public static CustomerBaseStateEnu toEnum(String code){
		return CACHE.get(code);
	}
}