package com.hhnz.jco.business.writeoff;

/**
 * 清帐凭证类别
 * V为发票/D为收款凭证
 * @author: chaoyang.ren
 * @date:2016年11月18日
 * @time:上午11:05:58
 * @email:chaoyang.ren@foxmail.com
 */
public enum VoucherType {
	/**
	 * 发票
	 */
	V,
	/**
	 * 收款凭证
	 */
	D;
	
	public static VoucherType of(String type){
		if("1".equals(type)){
			return D;
		}else if("2".equals(type)){
			return V;
		}else{
			throw new RuntimeException("异常的核销类型！");
		}
	}

}
