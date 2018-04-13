package com.hhnz.order.model;

import java.math.BigDecimal;
import java.util.Date;

import com.hhnz.rmi.db.model.order.enu.OrderStatus;
import com.hhnz.rmi.db.model.order.enu.OrderType;
import com.hhnz.rmi.util.BigDecimalUtil;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;
import com.hhnz.util.io.excel.util.excel.ExcelField;

public class OrderSearchModel {
	@ExcelField(order = 0, header = "销售组织")
	private String orgname;
	private String orgid;
	private Long merchCustId;
	@ExcelField(order = 4, header = "客户名称")
	private String merchname;
	@ExcelField(order = 6, header = "SAP客户编码")
	private String sapCustomerId;
	@ExcelField(order = 8, header = "CRM订单编号")
	private String orderHeaderId;
	private Date createTs;
	@ExcelField(order = 11, header = "销售人员")
	private String salesman;
	private Long salesrepId;
	private String states;
	@ExcelField(order = 12, header = "订单金额")
	private BigDecimal orderAmt;
	private String stationId;
	private String stationname;// 销售岗位名称
	@ExcelField(order = 9, header = "SAP订单编号")
	private String saporderid;// sap订单编号
	@ExcelField(order = 1, header = "大区")
	private String regionname;// 大区
	private String regionid;
	@ExcelField(order = 2, header = "业务省")
	private String provname;// 省区
	private String provid;
	private String shipto;
	@ExcelField(order = 15, header = "已发货金额")
	private BigDecimal sendAmt;
	private String orderType;
	private String msg;
	@ExcelField(order = 5, header = "送达方名称")
	private String shipname;
	private String batchnum;
	@ExcelField(order = 3, header = "行政省")
	private String prov;
	@ExcelField(order = 13, header = "重量")
	private BigDecimal weight;
	private String shipType;

	@ExcelField(order = 14, header = "吨均价")
	private BigDecimal averagePrice;
	@ExcelField(order = 7, header = "订单类型")
	private String orderTypeDesc;
	@ExcelField(order = 16, header = "订单状态")
	private String statesDesc;
	@ExcelField(order = 10, header = "下单时间")
	private String createTsDesc;
	@ExcelField(order = 17, header = "默认RDC")
	private String defaultRdc;
	@ExcelField(order = 18, header = "发货RDC")
	private String sendRdc;
	private String transferOrderId;
	private String deliveryType;
	private BigDecimal freight;
	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Long getMerchCustId() {
		return merchCustId;
	}

	public void setMerchCustId(Long merchCustId) {
		this.merchCustId = merchCustId;
	}

	public String getMerchname() {
		return merchname;
	}

	public void setMerchname(String merchname) {
		this.merchname = merchname;
	}

	public String getSapCustomerId() {
		return sapCustomerId;
	}

	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}

	public String getOrderHeaderId() {
		return orderHeaderId;
	}

	public void setOrderHeaderId(String orderHeaderId) {
		this.orderHeaderId = orderHeaderId;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = BigDecimalASME.divide(orderAmt);
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getSaporderid() {
		return saporderid;
	}

	public void setSaporderid(String saporderid) {
		this.saporderid = saporderid;
	}

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	public String getProvid() {
		return provid;
	}

	public void setProvid(String provid) {
		this.provid = provid;
	}

	public String getShipto() {
		return shipto;
	}

	public void setShipto(String shipto) {
		this.shipto = shipto;
	}

	public BigDecimal getSendAmt() {
		return sendAmt;
	}

	public void setSendAmt(BigDecimal sendAmt) {
		this.sendAmt = BigDecimalASME.divide(sendAmt);
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getSalesrepId() {
		return salesrepId;
	}

	public void setSalesrepId(Long salesrepId) {
		this.salesrepId = salesrepId;
	}

	public String getShipname() {
		return shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public String getBatchnum() {
		return batchnum;
	}

	public void setBatchnum(String batchnum) {
		this.batchnum = batchnum;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	
	public String getDefaultRdc() {
		return defaultRdc;
	}

	public void setDefaultRdc(String defaultRdc) {
		this.defaultRdc = defaultRdc;
	}

	public String getSendRdc() {
		return sendRdc;
	}

	public void setSendRdc(String sendRdc) {
		this.sendRdc = sendRdc;
	}

	public String getTransferOrderId() {
		return transferOrderId;
	}

	public void setTransferOrderId(String transferOrderId) {
		this.transferOrderId = transferOrderId;
	}

	public BigDecimal getAveragePrice() {
		if (orderAmt == null || orderAmt == null || weight.compareTo(BigDecimal.ZERO) <= 0) {
			return null;
		}
		return BigDecimalUtil.divide(orderAmt, weight);
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public String getOrderTypeDesc() {
		if(orderType == null || OrderType.toEnum(orderType) == null){
			return null;
		}
		return OrderType.toEnum(orderType).getDesc();
	}

	public void setOrderTypeDesc(String orderTypeDesc) {
		this.orderTypeDesc = orderTypeDesc;
	}

	public String getStatesDesc() {
		if(states == null || OrderStatus.toEnum(states) == null){
			return null;
		}
		return OrderStatus.toEnum(states).getDesc();
	}

	public void setStatesDesc(String statesDesc) {
		this.statesDesc = statesDesc;
	}

	public String getCreateTsDesc() {
		if(createTs == null){
			return null;
		}
		return DateUtil.format(createTs, DateUtil.DATE_TIME_FORMAT_STR_PLAIN);
	}

	public void setCreateTsDesc(String createTsDesc) {
		this.createTsDesc = createTsDesc;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	
}
