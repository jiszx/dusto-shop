package com.hhnz.customer.dto;

import java.util.List;

/**
 * @author: chaoyang.ren
 * @date:2016年8月15日
 * @time:下午5:26:57
 * @email:chaoyang.ren@foxmail.com
 */
public class CustomerBaseDTO {
	private String salesOrg;
	private String salesArea;
	private String bizProvId;
	private String position;
	private String customerName;
	private String sales;
	private String status;
	private String custType;
	private String search;
	private String pid;
	private String hasContract;
	private List<Long> pids;
	private String sapCustomerId;
	private Long id;
	private String businessLicense;
	private String code;
	private String email;
	
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getSalesArea() {
		return salesArea;
	}
	public void setSalesArea(String salesArea) {
		this.salesArea = salesArea;
	}
	public String getBizProvId() {
		return bizProvId;
	}
	public void setBizProvId(String bizProvId) {
		this.bizProvId = bizProvId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
    public String getSearch() {
      return search;
    }
    public void setSearch(String search) {
      this.search = search;
    }
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<Long> getPids() {
		return pids;
	}
	public void setPids(List<Long> pids) {
		this.pids = pids;
	}
	public String getSapCustomerId() {
		return sapCustomerId;
	}
	public void setSapCustomerId(String sapCustomerId) {
		this.sapCustomerId = sapCustomerId;
	}

	public String getHasContract() {
		return hasContract;
	}

	public void setHasContract(String hasContract) {
		this.hasContract = hasContract;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
  
    public String getCode() {
      return code;
    }
  
    public void setCode(String code) {
      this.code = code;
    }
  
    public String getEmail() {
      return email;
    }
  
    public void setEmail(String email) {
      this.email = email;
    }
	
}
