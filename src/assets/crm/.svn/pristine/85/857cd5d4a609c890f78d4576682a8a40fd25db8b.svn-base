package com.hhnz.jco.business.writeoff;

/**
 * 核销类型枚举
 * @author: chaoyang.ren
 * @date:2016年11月18日
 * @time:上午10:36:18
 * @email:chaoyang.ren@foxmail.com
 */
public enum OperationType {
	/**
	 * 全清
	 */
	A("全清"),
	/**
	 * 剩余清账-有余额
	 */
	L("清账剩余");
	
	public static OperationType of(String type){
		if("2".equals(type)){
			return A;
		}else if("1".equals(type)){
			return L;
		}else{
			throw new RuntimeException("异常的核销操作类型！");
		}
	}
	
	/**
	 * @param code
	 * @param desc
	 * @param voucherType
	 */
	private OperationType(String desc) {
		this.desc = desc;
	}
	private String desc;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
