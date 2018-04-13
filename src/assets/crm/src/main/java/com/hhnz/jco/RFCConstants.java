package com.hhnz.jco;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * rfc接口常量列表
 * @author: chaoyang.ren
 * @date:2016年8月9日
 * @time:下午2:11:45
 * @email:chaoyang.ren@foxmail.com
 */
public class RFCConstants {
	/**
	 * 缓存任务参数key
	 */
	public static final String CACHE_KEY = "RFC_";
	/**
	 * sap常用输入参数前缀
	 */
	public static final String IN_PREFIX = "IN_";
	/**
	 * sap常用输出参数前缀
	 */
	public static final String OUT_PREFIX = "OUT_";
	/**
	 * sap常用布尔值占位符
	 */
	public static final String X_FLAG = "X";
	/**
	 * sap常用返回常量S
	 */
	public static final String SUCCESS_FLAG = "S";
	/**
	 * sap常用返回常量E
	 */
	public static final String ERROR_FLAG = "E";
	
	
	/**
	 * sap产品组：盐
	 */
	public static final String SALT = "00";
	/**
	 * sap产品组：调味品
	 */
	public static final String CONDIMENT = "40";
	/**
	 * sap产品组：其他
	 */
	public static final String OTHER = "90";
	/**
	 * sap售达方Z001
	 */
	public static final String SALE_TO = "Z001";
	/**
	 * sap送达方Z002
	 */
	public static final String SEND_TO = "Z002";
	/**
	 * sap-crm产品组对应关系 
	 */
	public static final Map<String,String> GROUP_MAPPING = Collections.unmodifiableMap(
		new HashMap<String,String>(){
			private static final long serialVersionUID = 1L;
			{
				put("T01",SALT);
				put("T02",SALT);
				put("T03",SALT);
				put("T04",SALT);
				put("T05",SALT);
				put("T06",SALT);
				put("T07",SALT);
				put("T08",SALT);
				put("T09",SALT);
				put("T10",SALT);
			}
		}
	);
	/**
	 * sap销售凭证类型-crm产品组+订单类型的对应关系 
	 */
	public static final Map<String,String> DOCUMENT_TYPE_MAPPING = Collections.unmodifiableMap(
		new HashMap<String,String>(){
			private static final long serialVersionUID = 1L;
			{
				//盐订单
				put("T010","ZS01");
				//盐退货订单
				put("T011","ZR01");
				//默认盐订单
				put("0","ZS01");
				//默认盐退货订单
				put("1","ZR01");
				
				put("T020","ZS01");
				put("T021","ZR01");
				put("T030","ZS01");
				put("T031","ZR01");
				put("T040","ZS01");
				put("T041","ZR01");
				put("T050","ZS01");
				put("T051","ZR01");
				put("T060","ZS01");
				put("T061","ZR01");
				put("T070","ZS01");
				put("T071","ZR01");
				put("T080","ZS01");
				put("T081","ZR01");
				put("T090","ZS01");
				put("T091","ZR01");
				put("T100","ZS01");
				put("T101","ZR01");
			}
		}
	);
	
	/**
	 * sap操作类型常量-新增
	 */
	public static final String OPERATION_TYPE_ADD = "I";
	/**
	 * sap操作类型常量-修改
	 */
	public static final String OPERATION_TYPE_EDIT = "X";
	/**
	 * sap操作类型常量-删除
	 */
	public static final String OPERATION_TYPE_DEL = "D";
	
	/**
	 * sap常用日期类型
	 */
	public static final String SDF_PATTERN = "yyyyMMdd";
}
