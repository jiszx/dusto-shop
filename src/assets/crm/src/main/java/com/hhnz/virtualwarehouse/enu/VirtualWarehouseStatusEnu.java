package com.hhnz.virtualwarehouse.enu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 虚拟仓入库状态说明
 * 1编辑，2,待审批,3审批通过，4审批驳回
 * @author: chaoyang.ren
 * @date:2016年8月18日
 * @time:上午9:40:31
 * @email:chaoyang.ren@foxmail.com
 */
public enum VirtualWarehouseStatusEnu {
	/**
	 * 编辑
	 */
	DRAFT("1","编辑"),
	/**
	 * 待审批
	 */
	SUBMITED("2","待审批"),
	/**
	 * 审批通过
	 */
	APPROVED("3","审批通过"),
	/**
	 * 审批驳回
	 */
	REJECT("4","审批驳回");
	
	/**
	 * 状态编码
	 */
	private String code;
	/**
	 * 状态描述
	 */
	private String desc;
	/**
	 * @param code
	 * @param desc
	 */
	private VirtualWarehouseStatusEnu(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	private static Map<String, VirtualWarehouseStatusEnu> CACHE = Collections.unmodifiableMap(new HashMap<String, VirtualWarehouseStatusEnu>() {
		private static final long serialVersionUID = 1L;
		{
            for (VirtualWarehouseStatusEnu enu : VirtualWarehouseStatusEnu.values()) {
            	put(enu.getCode(), enu);
			}
        }
    });
    
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
	public static VirtualWarehouseStatusEnu toEnum(String code){
		return CACHE.get(code);
	}
}