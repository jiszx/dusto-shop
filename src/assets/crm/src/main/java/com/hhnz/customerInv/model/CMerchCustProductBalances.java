package com.hhnz.customerInv.model;

import java.math.BigDecimal;

public class CMerchCustProductBalances {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.MERCH_CUST_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private Long merchCustId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.PERIOD
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private String period;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.YTD
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private BigDecimal ytd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.D_NUM
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private BigDecimal dNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.C_NUM
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private BigDecimal cNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.PTD
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private BigDecimal ptd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.ORGANIZATION_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private String organizationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.STATES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private String states;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.CONTRACT_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private Long contractId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PRODUCT_BALANCES.MATERIAL_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    private String materialId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.ID
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.ID
     *
     * @param id the value for C_MERCH_CUST_PRODUCT_BALANCES.ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.MERCH_CUST_ID
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.MERCH_CUST_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public Long getMerchCustId() {
        return merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.MERCH_CUST_ID
     *
     * @param merchCustId the value for C_MERCH_CUST_PRODUCT_BALANCES.MERCH_CUST_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setMerchCustId(Long merchCustId) {
        this.merchCustId = merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.PERIOD
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.PERIOD
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public String getPeriod() {
        return period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.PERIOD
     *
     * @param period the value for C_MERCH_CUST_PRODUCT_BALANCES.PERIOD
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.YTD
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.YTD
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public BigDecimal getYtd() {
        return ytd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.YTD
     *
     * @param ytd the value for C_MERCH_CUST_PRODUCT_BALANCES.YTD
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setYtd(BigDecimal ytd) {
        this.ytd = ytd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.D_NUM
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.D_NUM
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public BigDecimal getdNum() {
        return dNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.D_NUM
     *
     * @param dNum the value for C_MERCH_CUST_PRODUCT_BALANCES.D_NUM
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setdNum(BigDecimal dNum) {
        this.dNum = dNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.C_NUM
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.C_NUM
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public BigDecimal getcNum() {
        return cNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.C_NUM
     *
     * @param cNum the value for C_MERCH_CUST_PRODUCT_BALANCES.C_NUM
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setcNum(BigDecimal cNum) {
        this.cNum = cNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.PTD
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.PTD
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public BigDecimal getPtd() {
        return ptd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.PTD
     *
     * @param ptd the value for C_MERCH_CUST_PRODUCT_BALANCES.PTD
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setPtd(BigDecimal ptd) {
        this.ptd = ptd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.ORGANIZATION_ID
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.ORGANIZATION_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.ORGANIZATION_ID
     *
     * @param organizationId the value for C_MERCH_CUST_PRODUCT_BALANCES.ORGANIZATION_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.STATES
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.STATES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public String getStates() {
        return states;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.STATES
     *
     * @param states the value for C_MERCH_CUST_PRODUCT_BALANCES.STATES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setStates(String states) {
        this.states = states;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.CONTRACT_ID
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.CONTRACT_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.CONTRACT_ID
     *
     * @param contractId the value for C_MERCH_CUST_PRODUCT_BALANCES.CONTRACT_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.MATERIAL_ID
     *
     * @return the value of C_MERCH_CUST_PRODUCT_BALANCES.MATERIAL_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PRODUCT_BALANCES.MATERIAL_ID
     *
     * @param materialId the value for C_MERCH_CUST_PRODUCT_BALANCES.MATERIAL_ID
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
}