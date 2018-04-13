package com.hhnz.receivable.dto;

import java.math.BigDecimal;

public class ArOverdueDTO {
	private Long merchId;//客户ID
	private String custname;//客户名称
	private String sapCustomerId;//客户sap编码
	private String orgid;//销售组织ID
	private String orgname;//销售组织名称
	private String custType;//客户类型
	private String arPeriod;//账期
	private BigDecimal aramt;//应收账款金额
	private BigDecimal overdue1;//未逾期金额
	private BigDecimal overdue2;//逾期1-30天
	private BigDecimal overdue3;//逾期31-60天
	private BigDecimal overdue4;//逾期61-90天
	private BigDecimal overdue5;//逾期91-120天
	private BigDecimal overdue6;//逾期121-180天
	private BigDecimal overdue7;//逾期超过180天
	public Long getMerchId() {
		return merchId;
	}
	public void setMerchId(Long merchId) {
		this.merchId = merchId;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getSapCustomerId() {
		return sapCustomerId;
	}
	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getArPeriod() {
		return arPeriod;
	}
	public void setArPeriod(String arPeriod) {
		this.arPeriod = arPeriod;
	}
	public BigDecimal getAramt() {
		return aramt;
	}
	public void setAramt(BigDecimal aramt) {
		this.aramt = aramt;
	}
	public BigDecimal getOverdue1() {
		return overdue1;
	}
	public void setOverdue1(BigDecimal overdue1) {
		this.overdue1 = overdue1;
	}
	public BigDecimal getOverdue2() {
		return overdue2;
	}
	public void setOverdue2(BigDecimal overdue2) {
		this.overdue2 = overdue2;
	}
	public BigDecimal getOverdue3() {
		return overdue3;
	}
	public void setOverdue3(BigDecimal overdue3) {
		this.overdue3 = overdue3;
	}
	public BigDecimal getOverdue4() {
		return overdue4;
	}
	public void setOverdue4(BigDecimal overdue4) {
		this.overdue4 = overdue4;
	}
	public BigDecimal getOverdue5() {
		return overdue5;
	}
	public void setOverdue5(BigDecimal overdue5) {
		this.overdue5 = overdue5;
	}
	public BigDecimal getOverdue6() {
		return overdue6;
	}
	public void setOverdue6(BigDecimal overdue6) {
		this.overdue6 = overdue6;
	}
	public BigDecimal getOverdue7() {
		return overdue7;
	}
	public void setOverdue7(BigDecimal overdue7) {
		this.overdue7 = overdue7;
	}
	
	
}
