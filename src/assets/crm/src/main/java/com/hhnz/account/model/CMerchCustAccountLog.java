package com.hhnz.account.model;

import java.math.BigDecimal;
import java.util.Date;

public class CMerchCustAccountLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.MERCH_CUST_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private Long merchCustId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String organizationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.TYPE
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.D_AMT
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private BigDecimal dAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.C_AMT
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private BigDecimal cAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.ACCOUNT_TYPE
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String accountType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.ORDER_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private Long orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.PERIOD
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String period;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.CREATE_TS
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private Date createTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.CREATER
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String creater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String attribute1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String attribute2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String attribute3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String attribute4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String attribute5;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ACCOUNT_LOG.SAP_VOUCHER_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    private String sapVoucherId;
    
    private String states;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ID
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ID
     *
     * @param id the value for C_MERCH_CUST_ACCOUNT_LOG.ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.MERCH_CUST_ID
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.MERCH_CUST_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public Long getMerchCustId() {
        return merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.MERCH_CUST_ID
     *
     * @param merchCustId the value for C_MERCH_CUST_ACCOUNT_LOG.MERCH_CUST_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setMerchCustId(Long merchCustId) {
        this.merchCustId = merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @param organizationId the value for C_MERCH_CUST_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.TYPE
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.TYPE
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.TYPE
     *
     * @param type the value for C_MERCH_CUST_ACCOUNT_LOG.TYPE
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setType(String type) {
        this.type = type;
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

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ACCOUNT_TYPE
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.ACCOUNT_TYPE
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ACCOUNT_TYPE
     *
     * @param accountType the value for C_MERCH_CUST_ACCOUNT_LOG.ACCOUNT_TYPE
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ORDER_ID
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.ORDER_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ORDER_ID
     *
     * @param orderId the value for C_MERCH_CUST_ACCOUNT_LOG.ORDER_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.PERIOD
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.PERIOD
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getPeriod() {
        return period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.PERIOD
     *
     * @param period the value for C_MERCH_CUST_ACCOUNT_LOG.PERIOD
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.CREATE_TS
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.CREATE_TS
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.CREATE_TS
     *
     * @param createTs the value for C_MERCH_CUST_ACCOUNT_LOG.CREATE_TS
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.CREATER
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.CREATER
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getCreater() {
        return creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.CREATER
     *
     * @param creater the value for C_MERCH_CUST_ACCOUNT_LOG.CREATER
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getAttribute1() {
        return attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @param attribute1 the value for C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getAttribute2() {
        return attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @param attribute2 the value for C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getAttribute3() {
        return attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @param attribute3 the value for C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getAttribute4() {
        return attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @param attribute4 the value for C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getAttribute5() {
        return attribute5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @param attribute5 the value for C_MERCH_CUST_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ACCOUNT_LOG.SAP_VOUCHER_ID
     *
     * @return the value of C_MERCH_CUST_ACCOUNT_LOG.SAP_VOUCHER_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public String getSapVoucherId() {
        return sapVoucherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ACCOUNT_LOG.SAP_VOUCHER_ID
     *
     * @param sapVoucherId the value for C_MERCH_CUST_ACCOUNT_LOG.SAP_VOUCHER_ID
     *
     * @mbggenerated Thu Sep 29 13:26:38 CST 2016
     */
    public void setSapVoucherId(String sapVoucherId) {
        this.sapVoucherId = sapVoucherId;
    }

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}
    
}