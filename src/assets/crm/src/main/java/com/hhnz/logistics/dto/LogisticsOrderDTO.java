package com.hhnz.logistics.dto;

import java.math.BigDecimal;

import com.hhnz.util.io.excel.util.excel.ExcelField;

public class LogisticsOrderDTO {
	private String orgid;
	private Long merchCustId;
	
	@ExcelField(order = 0, header = "下单时间")
	private String createTs;
	
	@ExcelField(order = 1, header = "SAP订单编号")
	private String saporderid;// sap订单编号
	
	@ExcelField(order = 2, header = "创建人")
	private String salesman;
	
	@ExcelField(order = 3, header = "默认RDC")
	private String defaultRdc;
	
	@ExcelField(order = 4, header = "发货RDC")
	private String sendRdc;
	
	@ExcelField(order = 5, header = "sku")
	private String sku;
	
	@ExcelField(order = 6, header = "重量")
	private BigDecimal weight;
	
	@ExcelField(order = 7, header = "箱内数量")
	private String amounts;
	
	@ExcelField(order = 8, header = "件数")
	private BigDecimal piece;
	
	@ExcelField(order = 9, header = "省")
	private String dporvName;
	
	@ExcelField(order = 10, header = "市")
	private String cityName;
	
	@ExcelField(order = 11, header = "区县")
	private String countyName;
	
	@ExcelField(order = 12, header = "收货地址")
	private String address ;
	
	@ExcelField(order = 13, header = "联系人")
	private String contact;//联系人
	
	@ExcelField(order = 14, header = "联系电话")
	private String contactModel;//联系电话
	
	@ExcelField(order = 15, header = "SAP客户编码")
	private String sapCustomerId;
	
	@ExcelField(order = 16, header = "客户名称")
	private String merchname;
	
	@ExcelField(order = 17, header = "送达方名称")
	private String shipname;
	
	@ExcelField(order = 18, header = "销售组织")
	private String orgname;
	
	@ExcelField(order = 19, header = "大区")
	private String regionname;// 大区
	
	@ExcelField(order = 20, header = "业务省")
	private String provname;// 省区
	
	@ExcelField(order = 21, header = "行政省")
	private String prov;
	
	@ExcelField(order = 22, header = "销售人员")
	private String salesPerson;//销售人员
	
	@ExcelField(order = 23, header = "销售人员电话")
	private String salesPersonModel;//销售人员电话
	
	@ExcelField(order = 24, header = "订单类型")
	private String orderType;
	
	@ExcelField(order = 25, header = "CRM订单编号")
	private String orderHeaderId;
	
	@ExcelField(order = 26, header = "调拨单单号")
	private String transferOrderId;
	
	@ExcelField(order = 27, header = "物料编码")
	private String materialId;
	
	@ExcelField(order = 28, header = "单位")
	private String unit;
	
	@ExcelField(order = 29, header = "数量")
	private BigDecimal num;
	
	@ExcelField(order = 30, header = "行类型")
	private String lineType;
	
	@ExcelField(order = 31, header = "订单状态")
	private String states;

	
	public String getDporvName() {
		return dporvName;
	}

	public void setDporvName(String dporvName) {
		this.dporvName = dporvName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
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

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
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

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
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

	public String getMerchname() {
		return merchname;
	}

	public void setMerchname(String merchname) {
		this.merchname = merchname;
	}

	public String getShipname() {
		return shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSapCustomerId() {
		return sapCustomerId;
	}

	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactModel() {
		return contactModel;
	}

	public void setContactModel(String contactModel) {
		this.contactModel = contactModel;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderHeaderId() {
		return orderHeaderId;
	}

	public void setOrderHeaderId(String orderHeaderId) {
		this.orderHeaderId = orderHeaderId;
	}

	public String getSaporderid() {
		return saporderid;
	}

	public void setSaporderid(String saporderid) {
		this.saporderid = saporderid;
	}

	public String getTransferOrderId() {
		return transferOrderId;
	}

	public void setTransferOrderId(String transferOrderId) {
		this.transferOrderId = transferOrderId;
	}

	public String getCreateTs() {
		return createTs;
	}

	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getSalesPersonModel() {
		return salesPersonModel;
	}

	public void setSalesPersonModel(String salesPersonModel) {
		this.salesPersonModel = salesPersonModel;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	public String getAmounts() {
		return amounts;
	}

	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}

	public BigDecimal getPiece() {
		return piece;
	}

	public void setPiece(BigDecimal piece) {
		this.piece = piece;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}
	
	
}
