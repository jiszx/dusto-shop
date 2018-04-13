package com.hhnz.report.model;

import java.math.BigDecimal;

public class TransferOrderDetailReport {
  private String merch;
  private String transferAddr;
  private long orderId;
  private String merchSapId;
  private String createTime;
  private String creator;
  private String orgname;
  private String region;
  private String prov;
  private String rdc;
  private String custType;
  private String materialId;
  private String sku;
  private BigDecimal unitPrice;
  private BigDecimal num;
  private BigDecimal amt;
  private String specifications;
  private String unit;
  private String weight;
  private BigDecimal deliveryNum;
  private BigDecimal deliveryWeight;
  private BigDecimal deliveryAmt;
  private String states;
  
  // for search
  private String bdate;
  private String edate;

  public String getMerch() {
    return merch;
  }

  public void setMerch(String merch) {
    this.merch = merch;
  }

  public String getTransferAddr() {
    return transferAddr;
  }

  public void setTransferAddr(String transferAddr) {
    this.transferAddr = transferAddr;
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getOrgname() {
    return orgname;
  }

  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getProv() {
    return prov;
  }

  public void setProv(String prov) {
    this.prov = prov;
  }

  public String getRdc() {
    return rdc;
  }

  public void setRdc(String rdc) {
    this.rdc = rdc;
  }

  public String getCustType() {
    return custType;
  }

  public void setCustType(String custType) {
    this.custType = custType;
  }

  public String getMaterialId() {
    return materialId;
  }

  public void setMaterialId(String materialId) {
    this.materialId = materialId;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
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

  public String getSpecifications() {
    return specifications;
  }

  public void setSpecifications(String specifications) {
    this.specifications = specifications;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public BigDecimal getDeliveryNum() {
    return deliveryNum;
  }

  public void setDeliveryNum(BigDecimal deliveryNum) {
    this.deliveryNum = deliveryNum;
  }

  public BigDecimal getDeliveryWeight() {
    return deliveryWeight;
  }

  public void setDeliveryWeight(BigDecimal deliveryWeight) {
    this.deliveryWeight = deliveryWeight;
  }

  public BigDecimal getDeliveryAmt() {
    return deliveryAmt;
  }

  public void setDeliveryAmt(BigDecimal deliveryAmt) {
    this.deliveryAmt = deliveryAmt;
  }

  public String getStates() {
    return states;
  }

  public void setStates(String states) {
    this.states = states;
  }

  public String getMerchSapId() {
    return merchSapId;
  }

  public void setMerchSapId(String merchSapId) {
    this.merchSapId = merchSapId;
  }

  public String getBdate() {
    return bdate;
  }

  public void setBdate(String bdate) {
    this.bdate = bdate;
  }

  public String getEdate() {
    return edate;
  }

  public void setEdate(String edate) {
    this.edate = edate;
  }

}
