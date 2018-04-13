package com.hhnz.order.dto;

import java.math.BigDecimal;

import com.hhnz.util.BigDecimalASME;

public class DistributeOrderDetailsDTO {
	private Long orderid;//订单编号
	private String saporderid;//sap订单编号
	private String merchname;//终端客户名称
	private Long merchid;//终端客户ID
	private String materialId;//物料编码
	private String sku;//物料描述
	private String unit;//单位
	private BigDecimal price;//单价
	private BigDecimal num;//数量
	private BigDecimal amt;//金额
	private BigDecimal sendAmt;
	private BigDecimal depo;
	private BigDecimal delivered;
	private String amounts;
	
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public String getSaporderid() {
		return saporderid;
	}
	public void setSaporderid(String saporderid) {
		this.saporderid = saporderid;
	}
	public String getMerchname() {
		return merchname;
	}
	public void setMerchname(String merchname) {
		this.merchname = merchname;
	}
	public Long getMerchid() {
		return merchid;
	}
	public void setMerchid(Long merchid) {
		this.merchid = merchid;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = BigDecimalASME.divide(price);
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
		this.amt = BigDecimalASME.divide(amt);
	}
	public BigDecimal getSendAmt() {
		return sendAmt;
	}
	public void setSendAmt(BigDecimal sendAmt) {
		this.sendAmt = sendAmt;
	}
  public BigDecimal getDepo() {
    return depo;
  }
  public void setDepo(BigDecimal depo) {
    this.depo = depo;
  }
  public BigDecimal getDelivered() {
    return delivered;
  }
  public void setDelivered(BigDecimal delivered) {
    this.delivered = delivered;
  }
  public String getAmounts() {
    return amounts;
  }
  public void setAmounts(String amounts) {
    this.amounts = amounts;
  }
	
	
}
