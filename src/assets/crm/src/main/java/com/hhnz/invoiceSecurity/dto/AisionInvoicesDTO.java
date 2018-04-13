package com.hhnz.invoiceSecurity.dto;

import java.math.BigDecimal;

public class AisionInvoicesDTO {
	private Long id;
	private String orgname;
	private String custname;
	private Long merchCustId;
	private String organizationId;
	private String sapCustomerId;
	private String infoDate;
	private String creater;
	private String sourcesNo;
	private String sourcesId;
	private String sourcesType;
	private String invoicesType;
	private	String infoNumber;
	private String infoTypeCode;
	private String infoMonth;
	private BigDecimal infoAmount;
	private BigDecimal infoTaxAmount;
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
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
	public String getSapCustomerId() {
		return sapCustomerId;
	}
	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}
	public String getInfoDate() {
		return infoDate;
	}
	public void setInfoDate(String infoDate) {
		this.infoDate = infoDate;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getSourcesNo() {
		return sourcesNo;
	}
	public void setSourcesNo(String sourcesNo) {
		this.sourcesNo = sourcesNo;
	}
	public String getSourcesId() {
		return sourcesId;
	}
	public void setSourcesId(String sourcesId) {
		this.sourcesId = sourcesId;
	}
	public String getSourcesType() {
		return sourcesType;
	}
	public void setSourcesType(String sourcesType) {
		this.sourcesType = sourcesType;
	}
	public String getInvoicesType() {
		return invoicesType;
	}
	public void setInvoicesType(String invoicesType) {
		this.invoicesType = invoicesType;
	}
	public String getInfoNumber() {
		return infoNumber;
	}
	public void setInfoNumber(String infoNumber) {
		this.infoNumber = infoNumber;
	}
	public String getInfoTypeCode() {
		return infoTypeCode;
	}
	public void setInfoTypeCode(String infoTypeCode) {
		this.infoTypeCode = infoTypeCode;
	}
	public String getInfoMonth() {
		return infoMonth;
	}
	public void setInfoMonth(String infoMonth) {
		this.infoMonth = infoMonth;
	}
	public BigDecimal getInfoAmount() {
		return infoAmount;
	}
	public void setInfoAmount(BigDecimal infoAmount) {
		this.infoAmount = infoAmount;
	}
	public BigDecimal getInfoTaxAmount() {
		return infoTaxAmount;
	}
	public void setInfoTaxAmount(BigDecimal infoTaxAmount) {
		this.infoTaxAmount = infoTaxAmount;
	}
	
}
