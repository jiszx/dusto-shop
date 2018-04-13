package com.hhnz.util;

import java.math.BigDecimal;

/**
 * 加减乘除
 * @author hhnz-skevin 2016-09-02
 *
 */
public class BigDecimalASME {
	private static BigDecimal  HUNDRED = new BigDecimal("100");
	private BigDecimalASME(){}
	
	/**
	 * 乘
	 * @param value
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal value){
		return value==null ? zero() : value.multiply(HUNDRED);
	}
	
	public static BigDecimal multiply(BigDecimal multiplicand,BigDecimal multiplier ){
		return multiplicand==null ? zero() : multiplicand.multiply(multiplier);
	}
	/**
	 * 加
	 * @param value
	 * @return
	 */
	
	public static BigDecimal add(BigDecimal value,BigDecimal value1){
		return value==null ? zero() : value.add(value1);
	}
	/**
	 * 减
	 * @param value
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal value,BigDecimal value1){
		return value==null ? zero() : value.subtract(value1);
	}
	/**
	 * 除
	 * @param value
	 * @return
	 */
	public static BigDecimal divide(BigDecimal dividend){
		return dividend==null ? zero() : dividend.divide(HUNDRED);
	}
	
	public static BigDecimal divide(BigDecimal dividend,BigDecimal divisor ){
		return dividend==null ? zero() : dividend.divide(divisor);
	}
	
	private static BigDecimal zero(){
	  return BigDecimal.ZERO;
	}
}
