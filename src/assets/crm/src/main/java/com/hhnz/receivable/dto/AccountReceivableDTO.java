package com.hhnz.receivable.dto;

import java.math.BigDecimal;

public class AccountReceivableDTO {
	private String custname;
	private String period;
	private String sapCustomerId;
	private Long merchCustId;
	private BigDecimal ytd;
	private BigDecimal dAmt;
	private BigDecimal cAmt;
	private BigDecimal ptd;
	private String organizationId;
	private String orgname;
	private Long id;
	private BigDecimal sapAmt;
	private String custType;
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getSapCustomerId() {
		return sapCustomerId;
	}
	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}
	public Long getMerchCustId() {
		return merchCustId;
	}
	public void setMerchCustId(Long merchCustId) {
		this.merchCustId = merchCustId;
	}
	public BigDecimal getYtd() {
		return ytd;
	}
	public void setYtd(BigDecimal ytd) {
		this.ytd = ytd;
	}
	public BigDecimal getdAmt() {
		return dAmt;
	}
	public void setdAmt(BigDecimal dAmt) {
		this.dAmt = dAmt;
	}
	public BigDecimal getcAmt() {
		return cAmt;
	}
	public void setcAmt(BigDecimal cAmt) {
		this.cAmt = cAmt;
	}
	public BigDecimal getPtd() {
		return ptd;
	}
	public void setPtd(BigDecimal ptd) {
		this.ptd = ptd;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getSapAmt() {
		return sapAmt;
	}
	public void setSapAmt(BigDecimal sapAmt) {
		this.sapAmt = sapAmt;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	
}
