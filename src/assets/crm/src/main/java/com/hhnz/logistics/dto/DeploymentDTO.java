package com.hhnz.logistics.dto;

import java.math.BigDecimal;

import com.hhnz.util.io.excel.util.excel.ExcelField;

public class DeploymentDTO {
	@ExcelField(order = 0, header = "下单时间")
	private String createTs;
	
	@ExcelField(order = 1, header = "默认RDC")
	private String defaultRdc;
	
	@ExcelField(order = 2, header = "发货RDC")
	private String RdcName;
	
	@ExcelField(order = 3, header = "SKU")
	private String sku;
	
	@ExcelField(order = 4, header = "重量")
	private BigDecimal weight;
	
	@ExcelField(order = 5, header = "箱内数量")
	private Long amounts;
	
	@ExcelField(order = 6, header = "件数")
	private BigDecimal piece;
	
	@ExcelField(order = 7, header = "省")
	private String dprovName;
	
	@ExcelField(order = 8, header = "市")
	private String cityName;
	
	@ExcelField(order = 9, header = "区县")
	private String countyName;
	
	@ExcelField(order = 10, header = "收货地址")
	private String address;
	
	@ExcelField(order = 11, header = "联系人")
	private String contact;//联系人
	
	@ExcelField(order = 12, header = "联系电话")
	private String contactModel;//联系电话
	
	@ExcelField(order = 13, header = "SAP客户编码")
	private String sapCustomerId;
	
	@ExcelField(order = 14, header = "送达方名称")
	private String custname;
	
	@ExcelField(order = 15, header = "销售组织")
	private String orgname;
	
	@ExcelField(order = 16, header = "业务省")
	private String provname;
	
	@ExcelField(order = 17, header = "行政省")
	private String prov; // 行政省
	
	@ExcelField(order = 18, header = "销售人员")
	private String salesPerson;//销售人员
	
	@ExcelField(order = 19, header = "销售人员电话")
	private String salesPersonModel;//销售人员电话
	
	@ExcelField(order = 20, header = "客户类型")
	private String custType;
	
	@ExcelField(order = 21, header = "调拨单编号")
	private Long id;
	
	@ExcelField(order = 22, header = "单位")
	private String unit;
	
	@ExcelField(order = 23, header = "数量")
	private BigDecimal num;
	
	@ExcelField(order = 24, header = "创建人")
	private String creater;
	
	@ExcelField(order = 25, header = "行类型")
	private String lineType;
	
	@ExcelField(order = 26, header = "状态")
	private String states;
	
	private String materialId;
	private BigDecimal allocationNum;
	private String  orderId;
	
	
	public String getDprovName() {
		return dprovName;
	}

	public void setDprovName(String dprovName) {
		this.dprovName = dprovName;
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

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public BigDecimal getAllocationNum() {
		return allocationNum;
	}

	public void setAllocationNum(BigDecimal allocationNum) {
		this.allocationNum = allocationNum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
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

	public String getRdcName() {
		return RdcName;
	}

	public void setRdcName(String rdcName) {
		RdcName = rdcName;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getSapCustomerId() {
		return sapCustomerId;
	}

	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateTs() {
		return createTs;
	}

	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
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

	public Long getAmounts() {
		return amounts;
	}

	public void setAmounts(Long amounts) {
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

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}
	
	
    
}
