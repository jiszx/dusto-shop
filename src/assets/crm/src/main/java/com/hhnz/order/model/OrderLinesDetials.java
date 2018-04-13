package com.hhnz.order.model;

import java.math.BigDecimal;
import java.util.Date;

import com.hhnz.util.BigDecimalASME;

public class OrderLinesDetials {
	private Long spliteid;
	private Long id;
	private Long headerId;
	private String materialId;
	private BigDecimal price;
	private BigDecimal num;
	private BigDecimal deliverynum;
	private Long policyHeaderId;
	private BigDecimal hbNum;
	private BigDecimal amt;
	private BigDecimal discountAmt;
	private BigDecimal orderAmt;
	private String states;
	private Long policyLineId;
	private BigDecimal hbAmt;
	private BigDecimal highPrice;
	private BigDecimal transportPrice;
	private BigDecimal deliveredNum;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String attribute1;
	private Date createTs;
	private Long createOid;
	private Date updateTs;
	private Long updateOid;
	private String policyDiscount;
	private String policyDiscountIntensity;
	private String policyVerfication;
	private BigDecimal orderPrice;
	private String unit;
	private String sku;
	private String policydiscountname;
	private String policyname;
	private OrderPolicy policy;
	private OrderMaterial material;
	private String policyprimary;
	private String type;
	private String factoryname;
	private String factoryid;
	private String amounts;
	private String specifications;
	private String materialType;

	public String getPolicyprimary() {
		return policyprimary;
	}

	public void setPolicyprimary(String policyprimary) {
		this.policyprimary = policyprimary;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getPolicydiscountname() {
		return policydiscountname;
	}

	public void setPolicydiscountname(String policydiscountname) {
		this.policydiscountname = policydiscountname;
	}

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

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
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

	public Long getPolicyHeaderId() {
		return policyHeaderId;
	}

	public void setPolicyHeaderId(Long policyHeaderId) {
		this.policyHeaderId = policyHeaderId;
	}

	public BigDecimal getHbNum() {
		return hbNum;
	}

	public void setHbNum(BigDecimal hbNum) {
		this.hbNum = hbNum;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = BigDecimalASME.divide(amt);
	}

	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = BigDecimalASME.divide(discountAmt);
	}

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = BigDecimalASME.divide(orderAmt);
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public Long getPolicyLineId() {
		return policyLineId;
	}

	public void setPolicyLineId(Long policyLineId) {
		this.policyLineId = policyLineId;
	}

	public BigDecimal getHbAmt() {
		return hbAmt;
	}

	public void setHbAmt(BigDecimal hbAmt) {
		this.hbAmt = BigDecimalASME.divide(hbAmt);
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getTransportPrice() {
		return transportPrice;
	}

	public void setTransportPrice(BigDecimal transportPrice) {
		this.transportPrice = transportPrice;
	}

	public BigDecimal getDeliveredNum() {
		return deliveredNum;
	}

	public void setDeliveredNum(BigDecimal deliveredNum) {
		this.deliveredNum = deliveredNum;
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

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
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

	public Date getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}

	public Long getUpdateOid() {
		return updateOid;
	}

	public void setUpdateOid(Long updateOid) {
		this.updateOid = updateOid;
	}

	public String getPolicyDiscount() {
		return policyDiscount;
	}

	public void setPolicyDiscount(String policyDiscount) {
		this.policyDiscount = policyDiscount;
	}

	public String getPolicyDiscountIntensity() {
		return policyDiscountIntensity;
	}

	public void setPolicyDiscountIntensity(String policyDiscountIntensity) {
		this.policyDiscountIntensity = policyDiscountIntensity;
	}

	public String getPolicyVerfication() {
		return policyVerfication;
	}

	public void setPolicyVerfication(String policyVerfication) {
		this.policyVerfication = policyVerfication;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = BigDecimalASME.divide(orderPrice);
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPolicyname() {
		return policyname;
	}

	public void setPolicyname(String policyname) {
		this.policyname = policyname;
	}

	public OrderPolicy getPolicy() {
		return policy;
	}

	public void setPolicy(OrderPolicy policy) {
		this.policy = policy;
	}

	public OrderMaterial getMaterial() {
		return material;
	}

	public void setMaterial(OrderMaterial material) {
		this.material = material;
	}

	public BigDecimal getDeliverynum() {
		return deliverynum;
	}

	public void setDeliverynum(BigDecimal deliverynum) {
		this.deliverynum = deliverynum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSpliteid() {
		return spliteid;
	}

	public void setSpliteid(Long spliteid) {
		this.spliteid = spliteid;
	}

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public String getFactoryid() {
		return factoryid;
	}

	public void setFactoryid(String factoryid) {
		this.factoryid = factoryid;
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
}