package com.hhnz.customer.model;

import java.math.BigDecimal;
import java.util.Date;

import com.hhnz.util.BigDecimalASME;

public class CMerchCustContractV {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.ORGNAME
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String orgname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.CUSTNAME
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String custname;
    private String custType;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.ID
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.MERCH_CUST_ID
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private Long merchCustId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.ORGANIZATION_ID
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String organizationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.MERCH_LEVELS
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String merchLevels;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.DELIVERY_ADDRESS
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String deliveryAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.CONTRACT_BDATE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String contractBdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.CONTRACT_EDATE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String contractEdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.SETTLE_TYPE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String settleType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.A_PERIOD
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String aPeriod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.YEAR_AMT
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String yearAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.REBATE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String rebate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE1
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String attribute1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE2
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String attribute2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE3
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String attribute3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE4
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String attribute4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE5
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String attribute5;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.STATES
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private String states;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_CONTRACT_V.CREDIT_AMT
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    private BigDecimal creditAmt;
    
    private Date createTs;
    
    
    private String factoryName;
    private String virtualWarehouse;
    private Long stationId;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.ORGNAME
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.ORGNAME
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getOrgname() {
        return orgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.ORGNAME
     *
     * @param orgname the value for C_MERCH_CUST_CONTRACT_V.ORGNAME
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.CUSTNAME
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.CUSTNAME
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getCustname() {
        return custname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.CUSTNAME
     *
     * @param custname the value for C_MERCH_CUST_CONTRACT_V.CUSTNAME
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setCustname(String custname) {
        this.custname = custname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.ID
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.ID
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.ID
     *
     * @param id the value for C_MERCH_CUST_CONTRACT_V.ID
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.MERCH_CUST_ID
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.MERCH_CUST_ID
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public Long getMerchCustId() {
        return merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.MERCH_CUST_ID
     *
     * @param merchCustId the value for C_MERCH_CUST_CONTRACT_V.MERCH_CUST_ID
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setMerchCustId(Long merchCustId) {
        this.merchCustId = merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.ORGANIZATION_ID
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.ORGANIZATION_ID
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.ORGANIZATION_ID
     *
     * @param organizationId the value for C_MERCH_CUST_CONTRACT_V.ORGANIZATION_ID
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.MERCH_LEVELS
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.MERCH_LEVELS
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getMerchLevels() {
        return merchLevels;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.MERCH_LEVELS
     *
     * @param merchLevels the value for C_MERCH_CUST_CONTRACT_V.MERCH_LEVELS
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setMerchLevels(String merchLevels) {
        this.merchLevels = merchLevels;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.DELIVERY_ADDRESS
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.DELIVERY_ADDRESS
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.DELIVERY_ADDRESS
     *
     * @param deliveryAddress the value for C_MERCH_CUST_CONTRACT_V.DELIVERY_ADDRESS
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.CONTRACT_BDATE
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.CONTRACT_BDATE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getContractBdate() {
        return contractBdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.CONTRACT_BDATE
     *
     * @param contractBdate the value for C_MERCH_CUST_CONTRACT_V.CONTRACT_BDATE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setContractBdate(String contractBdate) {
        this.contractBdate = contractBdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.CONTRACT_EDATE
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.CONTRACT_EDATE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getContractEdate() {
        return contractEdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.CONTRACT_EDATE
     *
     * @param contractEdate the value for C_MERCH_CUST_CONTRACT_V.CONTRACT_EDATE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setContractEdate(String contractEdate) {
        this.contractEdate = contractEdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.SETTLE_TYPE
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.SETTLE_TYPE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getSettleType() {
        return settleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.SETTLE_TYPE
     *
     * @param settleType the value for C_MERCH_CUST_CONTRACT_V.SETTLE_TYPE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.A_PERIOD
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.A_PERIOD
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getaPeriod() {
        return aPeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.A_PERIOD
     *
     * @param aPeriod the value for C_MERCH_CUST_CONTRACT_V.A_PERIOD
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setaPeriod(String aPeriod) {
        this.aPeriod = aPeriod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.YEAR_AMT
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.YEAR_AMT
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getYearAmt() {
        return yearAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.YEAR_AMT
     *
     * @param yearAmt the value for C_MERCH_CUST_CONTRACT_V.YEAR_AMT
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setYearAmt(String yearAmt) {
        this.yearAmt = yearAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.REBATE
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.REBATE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getRebate() {
        return rebate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.REBATE
     *
     * @param rebate the value for C_MERCH_CUST_CONTRACT_V.REBATE
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE1
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.ATTRIBUTE1
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getAttribute1() {
        return attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE1
     *
     * @param attribute1 the value for C_MERCH_CUST_CONTRACT_V.ATTRIBUTE1
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE2
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.ATTRIBUTE2
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getAttribute2() {
        return attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE2
     *
     * @param attribute2 the value for C_MERCH_CUST_CONTRACT_V.ATTRIBUTE2
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE3
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.ATTRIBUTE3
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getAttribute3() {
        return attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE3
     *
     * @param attribute3 the value for C_MERCH_CUST_CONTRACT_V.ATTRIBUTE3
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE4
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.ATTRIBUTE4
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getAttribute4() {
        return attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE4
     *
     * @param attribute4 the value for C_MERCH_CUST_CONTRACT_V.ATTRIBUTE4
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE5
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.ATTRIBUTE5
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getAttribute5() {
        return attribute5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.ATTRIBUTE5
     *
     * @param attribute5 the value for C_MERCH_CUST_CONTRACT_V.ATTRIBUTE5
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.STATES
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.STATES
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public String getStates() {
        return states;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.STATES
     *
     * @param states the value for C_MERCH_CUST_CONTRACT_V.STATES
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setStates(String states) {
        this.states = states;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_CONTRACT_V.CREDIT_AMT
     *
     * @return the value of C_MERCH_CUST_CONTRACT_V.CREDIT_AMT
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_CONTRACT_V.CREDIT_AMT
     *
     * @param creditAmt the value for C_MERCH_CUST_CONTRACT_V.CREDIT_AMT
     *
     * @mbggenerated Wed Aug 31 16:46:56 CST 2016
     */
    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = BigDecimalASME.divide(creditAmt);
    }

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getVirtualWarehouse() {
		return virtualWarehouse;
	}

	public void setVirtualWarehouse(String virtualWarehouse) {
		this.virtualWarehouse = virtualWarehouse;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

  public Date getCreateTs() {
    return createTs;
  }

  public void setCreateTs(Date createTs) {
    this.createTs = createTs;
  }

public Long getStationId() {
	return stationId;
}

public void setStationId(Long stationId) {
	this.stationId = stationId;
}
  
}