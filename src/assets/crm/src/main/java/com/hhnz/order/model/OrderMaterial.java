package com.hhnz.order.model;

import java.math.BigDecimal;

public class OrderMaterial {
	private Long merchCustId;
	private String organizationId;
	private String materialId;
	private String sku;
	private String materialName;
	private BigDecimal price;
	private BigDecimal hPrice;
	private BigDecimal basePrice;
	private BigDecimal unitprice;
	private BigDecimal cifPrice;
	private String unit;
	private String category;
	private String brand;
	private String series;
	private String sapId;
	private String invlimit;
	private Integer policynum;
	private String invnum;
	private String iPackage;
	private String amounts;
	private String specifications;
	private String materialType;

	public Integer getPolicynum() {
		return policynum;
	}

	public void setPolicynum(Integer policynum) {
		this.policynum = policynum;
	}

	public String getInvlimit() {
		return invlimit;
	}

	public void setInvlimit(String invlimit) {
		this.invlimit = invlimit;
	}

	public String getSapId() {
		return sapId;
	}

	public void setSapId(String sapId) {
		this.sapId = sapId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getMerchCustId() {
		return merchCustId;
	}

	public void setMerchCustId(Long merchCustId) {
		this.merchCustId = merchCustId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal gethPrice() {
		return hPrice;
	}

	public void sethPrice(BigDecimal hPrice) {
		this.hPrice = hPrice;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public String getInvnum() {
		return invnum;
	}

	public void setInvnum(String invnum) {
		this.invnum = invnum;
	}

	public String getiPackage() {
		return iPackage;
	}

	public void setiPackage(String iPackage) {
		this.iPackage = iPackage;
	}

	public String getAmounts() {
		return amounts;
	}

	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public BigDecimal getCifPrice() {
		return cifPrice;
	}

	public void setCifPrice(BigDecimal cifPrice) {
		this.cifPrice = cifPrice;
	}
}
