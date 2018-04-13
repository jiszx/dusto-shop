package com.hhnz.customer.model;

import java.util.Date;

import com.hhnz.customer.enu.CustomerBaseStateEnu;
import com.hhnz.rmi.db.model.customer.CustInvWarning;

public class CMerchCustBaseV {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.SAP_CUSTOMER_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String sapCustomerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.CUST_TYPE
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String custType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.CONTACT_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String contactName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.CONTACT_TEL
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String contactTel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.CUST_TYPE_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String custTypeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.SALES_ORG_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String salesOrgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.SALES_ORG_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String salesOrgName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.SALES_AREA_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String salesAreaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.SALES_AREA_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String salesAreaName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.SALES_PROV_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String salesProvId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.SALES_PROV_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String salesProvName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.POSITION_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private Long positionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.POSITION_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String positionName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.CREATE_TS
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private Date createTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.UPDATE_TS
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private Date updateTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.SALESMAN
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String salesman;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.STATES
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String states;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_BASE_V.STATES_DESC
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    private String statesDesc;
    
    private String pid;
    
    private String processId;
    
    private String businessLicense;
    
    private String organizationId;
    
    private String code;

    private String email;
    
    //Transient
    private CustInvWarning custWarning;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.ID
     *
     * @return the value of C_MERCH_CUST_BASE_V.ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.ID
     *
     * @param id the value for C_MERCH_CUST_BASE_V.ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.NAME
     *
     * @return the value of C_MERCH_CUST_BASE_V.NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.NAME
     *
     * @param name the value for C_MERCH_CUST_BASE_V.NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.SAP_CUSTOMER_ID
     *
     * @return the value of C_MERCH_CUST_BASE_V.SAP_CUSTOMER_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getSapCustomerId() {
        return sapCustomerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.SAP_CUSTOMER_ID
     *
     * @param sapCustomerId the value for C_MERCH_CUST_BASE_V.SAP_CUSTOMER_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setSapCustomerId(String sapCustomerId) {
        this.sapCustomerId = sapCustomerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.CUST_TYPE
     *
     * @return the value of C_MERCH_CUST_BASE_V.CUST_TYPE
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getCustType() {
        return custType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.CUST_TYPE
     *
     * @param custType the value for C_MERCH_CUST_BASE_V.CUST_TYPE
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setCustType(String custType) {
        this.custType = custType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.CONTACT_NAME
     *
     * @return the value of C_MERCH_CUST_BASE_V.CONTACT_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.CONTACT_NAME
     *
     * @param contactName the value for C_MERCH_CUST_BASE_V.CONTACT_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.CONTACT_TEL
     *
     * @return the value of C_MERCH_CUST_BASE_V.CONTACT_TEL
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getContactTel() {
        return contactTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.CONTACT_TEL
     *
     * @param contactTel the value for C_MERCH_CUST_BASE_V.CONTACT_TEL
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.CUST_TYPE_NAME
     *
     * @return the value of C_MERCH_CUST_BASE_V.CUST_TYPE_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getCustTypeName() {
        return custTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.CUST_TYPE_NAME
     *
     * @param custTypeName the value for C_MERCH_CUST_BASE_V.CUST_TYPE_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setCustTypeName(String custTypeName) {
        this.custTypeName = custTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.SALES_ORG_ID
     *
     * @return the value of C_MERCH_CUST_BASE_V.SALES_ORG_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getSalesOrgId() {
        return salesOrgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.SALES_ORG_ID
     *
     * @param salesOrgId the value for C_MERCH_CUST_BASE_V.SALES_ORG_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setSalesOrgId(String salesOrgId) {
        this.salesOrgId = salesOrgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.SALES_ORG_NAME
     *
     * @return the value of C_MERCH_CUST_BASE_V.SALES_ORG_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getSalesOrgName() {
        return salesOrgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.SALES_ORG_NAME
     *
     * @param salesOrgName the value for C_MERCH_CUST_BASE_V.SALES_ORG_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setSalesOrgName(String salesOrgName) {
        this.salesOrgName = salesOrgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.SALES_AREA_ID
     *
     * @return the value of C_MERCH_CUST_BASE_V.SALES_AREA_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getSalesAreaId() {
        return salesAreaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.SALES_AREA_ID
     *
     * @param salesAreaId the value for C_MERCH_CUST_BASE_V.SALES_AREA_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setSalesAreaId(String salesAreaId) {
        this.salesAreaId = salesAreaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.SALES_AREA_NAME
     *
     * @return the value of C_MERCH_CUST_BASE_V.SALES_AREA_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getSalesAreaName() {
        return salesAreaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.SALES_AREA_NAME
     *
     * @param salesAreaName the value for C_MERCH_CUST_BASE_V.SALES_AREA_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setSalesAreaName(String salesAreaName) {
        this.salesAreaName = salesAreaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.SALES_PROV_ID
     *
     * @return the value of C_MERCH_CUST_BASE_V.SALES_PROV_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getSalesProvId() {
        return salesProvId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.SALES_PROV_ID
     *
     * @param salesProvId the value for C_MERCH_CUST_BASE_V.SALES_PROV_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setSalesProvId(String salesProvId) {
        this.salesProvId = salesProvId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.SALES_PROV_NAME
     *
     * @return the value of C_MERCH_CUST_BASE_V.SALES_PROV_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getSalesProvName() {
        return salesProvName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.SALES_PROV_NAME
     *
     * @param salesProvName the value for C_MERCH_CUST_BASE_V.SALES_PROV_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setSalesProvName(String salesProvName) {
        this.salesProvName = salesProvName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.POSITION_ID
     *
     * @return the value of C_MERCH_CUST_BASE_V.POSITION_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public Long getPositionId() {
        return positionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.POSITION_ID
     *
     * @param positionId the value for C_MERCH_CUST_BASE_V.POSITION_ID
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.POSITION_NAME
     *
     * @return the value of C_MERCH_CUST_BASE_V.POSITION_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.POSITION_NAME
     *
     * @param positionName the value for C_MERCH_CUST_BASE_V.POSITION_NAME
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.CREATE_TS
     *
     * @return the value of C_MERCH_CUST_BASE_V.CREATE_TS
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.CREATE_TS
     *
     * @param createTs the value for C_MERCH_CUST_BASE_V.CREATE_TS
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.UPDATE_TS
     *
     * @return the value of C_MERCH_CUST_BASE_V.UPDATE_TS
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.UPDATE_TS
     *
     * @param updateTs the value for C_MERCH_CUST_BASE_V.UPDATE_TS
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.SALESMAN
     *
     * @return the value of C_MERCH_CUST_BASE_V.SALESMAN
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getSalesman() {
        return salesman;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.SALESMAN
     *
     * @param salesman the value for C_MERCH_CUST_BASE_V.SALESMAN
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.STATES
     *
     * @return the value of C_MERCH_CUST_BASE_V.STATES
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getStates() {
        return states;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.STATES
     *
     * @param states the value for C_MERCH_CUST_BASE_V.STATES
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setStates(String states) {
      this.states = states;
      this.statesDesc = CustomerBaseStateEnu.toEnum(states).getDesc();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_BASE_V.STATES_DESC
     *
     * @return the value of C_MERCH_CUST_BASE_V.STATES_DESC
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public String getStatesDesc() {
        return statesDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_BASE_V.STATES_DESC
     *
     * @param statesDesc the value for C_MERCH_CUST_BASE_V.STATES_DESC
     *
     * @mbggenerated Fri Aug 19 09:33:45 CST 2016
     */
    public void setStatesDesc(String statesDesc) {
    }

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}


	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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

	public CustInvWarning getCustWarning() {
		return custWarning;
	}

	public void setCustWarning(CustInvWarning custWarning) {
		this.custWarning = custWarning;
	}

    
}