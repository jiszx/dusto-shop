package com.hhnz.order.dto;

import java.math.BigDecimal;

public class OrderBackDTO {
	private Long id;
	private String sapOrderId;
	private Long merchCustId;
	private String custname;
	private String rdcCode;
	private String orderType;
	private Long oldOrderId;
	private String orgname;
	private String organizationId;
	private String states;
	private BigDecimal backNum;
	private BigDecimal backAmt;
	private String backReason;
	private String sapCustomerId;
	private String custType;
	private String creater;
	private String createTs;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSapOrderId() {
		return sapOrderId;
	}
	public void setSapOrderId(String sapOrderId) {
		this.sapOrderId = sapOrderId;
	}
	public Long getMerchCustId() {
		return merchCustId;
	}
	public void setMerchCustId(Long merchCustId) {
		this.merchCustId = merchCustId;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getRdcCode() {
		return rdcCode;
	}
	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Long getOldOrderId() {
		return oldOrderId;
	}
	public void setOldOrderId(Long oldOrderId) {
		this.oldOrderId = oldOrderId;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public BigDecimal getBackNum() {
		return backNum;
	}
	public void setBackNum(BigDecimal backNum) {
		this.backNum = backNum;
	}
	public BigDecimal getBackAmt() {
		return backAmt;
	}
	public void setBackAmt(BigDecimal backAmt) {
		this.backAmt = backAmt;
	}
	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	public String getSapCustomerId() {
		return sapCustomerId;
	}
	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreateTs() {
		return createTs;
	}
	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}
	
}
