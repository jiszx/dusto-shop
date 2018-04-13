package com.hhnz.jco.enu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * RFC业务执行函数枚举
 * @author: chaoyang.ren
 * @date:2016年8月11日
 * @time:上午10:03:42
 * @email:chaoyang.ren@foxmail.com
 */
public enum RfcExeType {
	/**
	 * 调味品成品库存
	 */
	PRODUCT_INV("ZRFC_ZERT_LABST_FUFEI","productInvRFC","调味品成品库存"),
	/**
	 * 客户主数据
	 */
	CUSTOMER("ZRFC_SPICE_CUSTOMER","customerRFC","客户主数据"),
	/**
	 * 供应商主数据
	 */
	VENDOR("Z_VENDOR_02","vendorRFC","供应商主数据"),
	/**
	 * 订单处理
	 */
	ORDER("ZRFC_SPICE_ORDER","orderRFC","订单处理"),
	/**
	 * 资金处理
	 */
	ACCOUNT("ZRFC_CREATE_DOCUMENT","accountAdjustRFC","资金处理"),
	/**
	 * 保证金
	 */
	MARGINS("ZRFC_CREATE_DOCUMENT_LIFNR","marginsRFC","保证金上账"),
	/**
	 * 发票核销
	 */
	WRITEOFF("ZRFC_POSTANDCLEARING","writeOffRFC","发票核销"),
	/**
	 * 库存调拨
	 */
	INVENTORY("ZRFC_INVENTORY_APY","inventoryRFC","库存调拨"),
	/**
	 * 库存调拨关闭
	 */
	INVENTORY_CANCEL("ZRFC_INVENTORY_APY_CANCLE","inventoryCancelRFC","库存调拨关闭"),
	/**
	 * 库存查询
	 */
	INVENTORY_QUERY("ZRFC_ATP_LABST","inventoryQueryRFC","库存查询");
	
	/**
	 * RFC业务处理的远程调用方法名
	 */
	private String funcName;
	/**
	 * RFC业务处理的bean name
	 */
	private String beanName;
	/**
	 * RFC业务处理的描述
	 */
	private String beanDesc;
	/**
	 * @param beanName
	 * @param beanDesc
	 */
	private RfcExeType(String funcName, String beanName, String beanDesc) {
		this.funcName = funcName;
		this.beanName = beanName;
		this.beanDesc = beanDesc;
	}
	private static final Map<String,RfcExeType> CACHE = Collections.unmodifiableMap(new HashMap<String,RfcExeType>(){
		private static final long serialVersionUID = 1L;
		{
			for (RfcExeType ret: RfcExeType.values()) {
				put(ret.toString(), ret);	
			}
		}
	});
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getBeanDesc() {
		return beanDesc;
	}
	public void setBeanDesc(String beanDesc) {
		this.beanDesc = beanDesc;
	}
	public static RfcExeType toEnum(String name){
		return CACHE.get(name);
	}

}
