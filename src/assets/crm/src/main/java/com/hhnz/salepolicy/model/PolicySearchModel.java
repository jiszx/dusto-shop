package com.hhnz.salepolicy.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.hhnz.order.model.OrderMaterial;

public class PolicySearchModel {
	private Long id;
	private String orgname;
	private String typename;
	private String creater;
	private Long policyType;
	private String description;
	private Date bDate;
	private Date eDate;
	private BigDecimal amt;
	private BigDecimal balanceAmt;
	private String states;
	private String costTypeid;
	private String costSubjectid;
	private String custname;
	private String verification;
	private String effect;
	private List<OrderMaterial> materials;
	
	
	public String getCustname() {
    return custname;
  }
  public void setCustname(String custname) {
    this.custname = custname;
  }
  public String getVerification() {
    return verification;
  }
  public void setVerification(String verification) {
    this.verification = verification;
  }
  public String getEffect() {
    return effect;
  }
  public void setEffect(String effect) {
    this.effect = effect;
  }
  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Long getPolicyType() {
		return policyType;
	}
	public void setPolicyType(Long policyType) {
		this.policyType = policyType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	
	public BigDecimal getAmt() {
    return amt;
  }
  public void setAmt(BigDecimal amt) {
    this.amt = amt;
  }
  public BigDecimal getBalanceAmt() {
    return balanceAmt;
  }
  public void setBalanceAmt(BigDecimal balanceAmt) {
    this.balanceAmt = balanceAmt;
  }
  public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getCostTypeid() {
		return costTypeid;
	}
	public void setCostTypeid(String costTypeid) {
		this.costTypeid = costTypeid;
	}
	public String getCostSubjectid() {
		return costSubjectid;
	}
	public void setCostSubjectid(String costSubjectid) {
		this.costSubjectid = costSubjectid;
	}
  public List<OrderMaterial> getMaterials() {
    return materials;
  }
  public void setMaterials(List<OrderMaterial> materials) {
    this.materials = materials;
  }
	
	
}
