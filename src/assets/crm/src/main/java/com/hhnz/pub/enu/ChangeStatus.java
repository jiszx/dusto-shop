package com.hhnz.pub.enu;

import java.util.HashMap;
import java.util.Map;

/**
 * 变更状态说明
 * 1草稿，2,审批中,3生效，4删除
 * @author: chaoyang.ren
 * @date:Jan 5, 2017
 * @time:10:27:56 AM
 * @email:chaoyang.ren@foxmail.com
 */
public enum ChangeStatus {
	/**
	 * 草稿
	 */
	DRAFT("1","草稿"),
	/**
	 * 审批中
	 */
	SUBMITED("2","审批中"),
	/**
	 * 生效
	 */
	VALID("3","生效"),
	/**
	 * 删除
	 */
	DELETED("4","删除");
	
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * @param code
	 * @param desc
	 */
	private ChangeStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	private static Map<String, ChangeStatus> CACHE = new HashMap<String, ChangeStatus>() {
		private static final long serialVersionUID = 1L;
		{
            for (ChangeStatus enu : ChangeStatus.values()) {
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
	public static ChangeStatus toEnum(String code){
		return CACHE.get(code);
	}
}