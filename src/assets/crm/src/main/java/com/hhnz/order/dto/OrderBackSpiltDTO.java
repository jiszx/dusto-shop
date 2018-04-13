package com.hhnz.order.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderBackSpiltDTO {
    private Long id;
    private Long headerId;
    private Long lineId;
    private BigDecimal price;
    private BigDecimal orderPrice;
    private BigDecimal num;
    private BigDecimal amt;
    private Long merchCustId;
    private String materialId;
    private String states;
    private String sapHeaderId;
    private Date sapCreateTs;
    private String organizationId;
    private String type;
    private Long shipTo;
    private Date createTs;
    private Long createOid;
    private String reginId;
    private String provId;
    private Long stationId;
    private Long salesrepId;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String deliveredNum;
    private String orderitemSapNo;
    private BigDecimal oldNum;
    private String sku;
    private BigDecimal amounts;
    private String unit;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHeaderId() {
		return headerId;
	}
	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}
	public Long getLineId() {
		return lineId;
	}
	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getNum() {
		return num;
	}
	public void setNum(BigDecimal num) {
		this.num = num;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public Long getMerchCustId() {
		return merchCustId;
	}
	public void setMerchCustId(Long merchCustId) {
		this.merchCustId = merchCustId;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getSapHeaderId() {
		return sapHeaderId;
	}
	public void setSapHeaderId(String sapHeaderId) {
		this.sapHeaderId = sapHeaderId;
	}
	public Date getSapCreateTs() {
		return sapCreateTs;
	}
	public void setSapCreateTs(Date sapCreateTs) {
		this.sapCreateTs = sapCreateTs;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getShipTo() {
		return shipTo;
	}
	public void setShipTo(Long shipTo) {
		this.shipTo = shipTo;
	}
	public Date getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}
	public Long getCreateOid() {
		return createOid;
	}
	public void setCreateOid(Long createOid) {
		this.createOid = createOid;
	}
	public String getReginId() {
		return reginId;
	}
	public void setReginId(String reginId) {
		this.reginId = reginId;
	}
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public Long getSalesrepId() {
		return salesrepId;
	}
	public void setSalesrepId(Long salesrepId) {
		this.salesrepId = salesrepId;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	public String getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	public String getDeliveredNum() {
		return deliveredNum;
	}
	public void setDeliveredNum(String deliveredNum) {
		this.deliveredNum = deliveredNum;
	}
	public String getOrderitemSapNo() {
		return orderitemSapNo;
	}
	public void setOrderitemSapNo(String orderitemSapNo) {
		this.orderitemSapNo = orderitemSapNo;
	}
	public BigDecimal getOldNum() {
		return oldNum;
	}
	public void setOldNum(BigDecimal oldNum) {
		this.oldNum = oldNum;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public BigDecimal getAmounts() {
		return amounts;
	}
	public void setAmounts(BigDecimal amounts) {
		this.amounts = amounts;
	}
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
