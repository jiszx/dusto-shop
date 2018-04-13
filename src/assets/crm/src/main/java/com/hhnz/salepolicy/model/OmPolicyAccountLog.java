package com.hhnz.salepolicy.model;

import java.math.BigDecimal;
import java.util.Date;

public class OmPolicyAccountLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.POLICY_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private Long policyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private String organizationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ORDER_HEADER_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private Long orderHeaderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ORDER_LINE_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private Long orderLineId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ORDER_SPILT_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private Long orderSpiltId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.POLICY_LINE_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private Long policyLineId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.CREATE_TS
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private Date createTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.CREATE_OID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private Long createOid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.AMT
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private BigDecimal amt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.REMARK
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private String attribute1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private String attribute2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private String attribute3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private String attribute4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    private String attribute5;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ID
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ID
     *
     * @param id the value for OM_POLICY_ACCOUNT_LOG.ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.POLICY_ID
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.POLICY_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public Long getPolicyId() {
        return policyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.POLICY_ID
     *
     * @param policyId the value for OM_POLICY_ACCOUNT_LOG.POLICY_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @param organizationId the value for OM_POLICY_ACCOUNT_LOG.ORGANIZATION_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ORDER_HEADER_ID
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ORDER_HEADER_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public Long getOrderHeaderId() {
        return orderHeaderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ORDER_HEADER_ID
     *
     * @param orderHeaderId the value for OM_POLICY_ACCOUNT_LOG.ORDER_HEADER_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setOrderHeaderId(Long orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ORDER_LINE_ID
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ORDER_LINE_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public Long getOrderLineId() {
        return orderLineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ORDER_LINE_ID
     *
     * @param orderLineId the value for OM_POLICY_ACCOUNT_LOG.ORDER_LINE_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setOrderLineId(Long orderLineId) {
        this.orderLineId = orderLineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ORDER_SPILT_ID
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ORDER_SPILT_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public Long getOrderSpiltId() {
        return orderSpiltId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ORDER_SPILT_ID
     *
     * @param orderSpiltId the value for OM_POLICY_ACCOUNT_LOG.ORDER_SPILT_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setOrderSpiltId(Long orderSpiltId) {
        this.orderSpiltId = orderSpiltId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.POLICY_LINE_ID
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.POLICY_LINE_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public Long getPolicyLineId() {
        return policyLineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.POLICY_LINE_ID
     *
     * @param policyLineId the value for OM_POLICY_ACCOUNT_LOG.POLICY_LINE_ID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setPolicyLineId(Long policyLineId) {
        this.policyLineId = policyLineId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.CREATE_TS
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.CREATE_TS
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.CREATE_TS
     *
     * @param createTs the value for OM_POLICY_ACCOUNT_LOG.CREATE_TS
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.CREATE_OID
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.CREATE_OID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public Long getCreateOid() {
        return createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.CREATE_OID
     *
     * @param createOid the value for OM_POLICY_ACCOUNT_LOG.CREATE_OID
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setCreateOid(Long createOid) {
        this.createOid = createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.AMT
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.AMT
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.AMT
     *
     * @param amt the value for OM_POLICY_ACCOUNT_LOG.AMT
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.REMARK
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.REMARK
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.REMARK
     *
     * @param remark the value for OM_POLICY_ACCOUNT_LOG.REMARK
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public String getAttribute1() {
        return attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @param attribute1 the value for OM_POLICY_ACCOUNT_LOG.ATTRIBUTE1
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public String getAttribute2() {
        return attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @param attribute2 the value for OM_POLICY_ACCOUNT_LOG.ATTRIBUTE2
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public String getAttribute3() {
        return attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @param attribute3 the value for OM_POLICY_ACCOUNT_LOG.ATTRIBUTE3
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public String getAttribute4() {
        return attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @param attribute4 the value for OM_POLICY_ACCOUNT_LOG.ATTRIBUTE4
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @return the value of OM_POLICY_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public String getAttribute5() {
        return attribute5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_POLICY_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @param attribute5 the value for OM_POLICY_ACCOUNT_LOG.ATTRIBUTE5
     *
     * @mbggenerated Thu Sep 08 13:29:25 CST 2016
     */
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }
}