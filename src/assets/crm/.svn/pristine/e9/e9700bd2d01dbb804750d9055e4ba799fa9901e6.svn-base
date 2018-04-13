package com.hhnz.jco.enu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * RFC业务执行状态枚举
 * @author: chaoyang.ren
 * @date:2016年11月10日
 * @time:上午11:32:07
 * @email:chaoyang.ren@foxmail.com
 */
public enum RfcExeStatus {
	/**
	 * 执行中
	 */
	INPROGRESS("执行中"),
	/**
	 * 执行成功
	 */
	SUCCESS("执行成功"),
	/**
	 * 执行失败
	 */
	FAILURE("执行失败"),
	/**
	 * 执行完成
	 */
	FINISHED("执行完成"),
	/**
	 * 参数更新
	 */
	REFRESHED("参数更新"),
	/**
	 * 不再执行
	 */
	NEVER("不再执行");
	
	/**
	 * 状态描述
	 */
	private String desc;
	/**
	 * @param beanName
	 * @param beanDesc
	 */
	private RfcExeStatus(String desc) {
		this.desc = desc;
	}
	private static final Map<String,RfcExeStatus> CACHE = Collections.unmodifiableMap(new HashMap<String,RfcExeStatus>(){
		private static final long serialVersionUID = 1L;
		{
			for (RfcExeStatus ret: RfcExeStatus.values()) {
				put(ret.toString(), ret);	
			}
		}
	});
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static RfcExeStatus toEnum(String name){
		return CACHE.get(name);
	}

}
