package com.hhnz.customerInv.dto;

import java.math.BigDecimal;

public class AdjustMaterialDTO {
	private String materialId;
	private String sku;
	private BigDecimal invNum;
	private BigDecimal price;
	private BigDecimal amounts;
	private String unit;
	private String specifications;
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
	public BigDecimal getInvNum() {
		return invNum;
	}
	public void setInvNum(BigDecimal invNum) {
		this.invNum = invNum;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmounts() {
		return amounts;
	}
	public void setAmounts(BigDecimal amounts) {
		this.amounts = amounts;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	
}
