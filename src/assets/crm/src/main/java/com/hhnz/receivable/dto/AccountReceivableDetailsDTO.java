package com.hhnz.receivable.dto;

import java.math.BigDecimal;

public class AccountReceivableDetailsDTO {
	private String type;
	private String invoiceNo;
	private String sapid;
	private String crmid;
	private BigDecimal amt;
	private String drawDate;
	private String remark;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getSapid() {
		return sapid;
	}
	public void setSapid(String sapid) {
		this.sapid = sapid;
	}
	public String getCrmid() {
		return crmid;
	}
	public void setCrmid(String crmid) {
		this.crmid = crmid;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getDrawDate() {
		return drawDate;
	}
	public void setDrawDate(String drawDate) {
		this.drawDate = drawDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
