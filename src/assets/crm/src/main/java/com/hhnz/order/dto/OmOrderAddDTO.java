package com.hhnz.order.dto;

import java.math.BigDecimal;

public class OmOrderAddDTO {
	private String states;
	private Long merchCustId;
	private Long shipId;
	private Long billto;
	private Long stationid;
	private String rdcCode;
	private String orgid;
	private BigDecimal xjamt;
	private BigDecimal sxamt;
	private BigDecimal hbamt;
	private String orderType;
	private String endTime;
	private String remark;
	private String lines;
	private String othersOrderId;
	private String receiveRDC;
	private BigDecimal freight;
	private String deliveryType;
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public Long getMerchCustId() {
		return merchCustId;
	}
	public void setMerchCustId(Long merchCustId) {
		this.merchCustId = merchCustId;
	}
	public Long getShipId() {
		return shipId;
	}
	public void setShipId(Long shipId) {
		this.shipId = shipId;
	}
	public Long getBillto() {
		return billto;
	}
	public void setBillto(Long billto) {
		this.billto = billto;
	}
	public Long getStationid() {
		return stationid;
	}
	public void setStationid(Long stationid) {
		this.stationid = stationid;
	}
	public String getRdcCode() {
		return rdcCode;
	}
	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public BigDecimal getXjamt() {
		return xjamt;
	}
	public void setXjamt(BigDecimal xjamt) {
		this.xjamt = xjamt;
	}
	public BigDecimal getSxamt() {
		return sxamt;
	}
	public void setSxamt(BigDecimal sxamt) {
		this.sxamt = sxamt;
	}
	public BigDecimal getHbamt() {
		return hbamt;
	}
	public void setHbamt(BigDecimal hbamt) {
		this.hbamt = hbamt;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLines() {
		return lines;
	}
	public void setLines(String lines) {
		this.lines = lines;
	}
	public String getOthersOrderId() {
		return othersOrderId;
	}
	public void setOthersOrderId(String othersOrderId) {
		this.othersOrderId = othersOrderId;
	}
	public String getReceiveRDC() {
		return receiveRDC;
	}
	public void setReceiveRDC(String receiveRDC) {
		this.receiveRDC = receiveRDC;
	}
	public BigDecimal getFreight() {
		return freight;
	}
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
}
