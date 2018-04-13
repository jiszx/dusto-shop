package com.hhnz.customerInv.model;

import java.math.BigDecimal;
import java.util.Date;

public class CMerchInvLogV {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.CREATER
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String creater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.MATERIAL_ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String materialId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.D_NUM
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private BigDecimal dNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.INV_NUM
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private BigDecimal invNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.CREATE_TS
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private Date createTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.CREATE_OID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private Long createOid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.VOUCHER_ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String voucherId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.MERCH_CUST_ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private Long merchCustId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.PERIOD
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String period;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.ATTRIBUTE1
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String attribute1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.ATTRIBUTE2
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String attribute2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.ATTRIBUTE3
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String attribute3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.ATTRIBUTE4
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String attribute4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.REMARK
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.TYPE
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.SOURCE
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_INV_LOG_V.C_NUM
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    private BigDecimal cNum;
    private String amounts;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.CREATER
     *
     * @return the value of C_MERCH_INV_LOG_V.CREATER
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getCreater() {
        return creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.CREATER
     *
     * @param creater the value for C_MERCH_INV_LOG_V.CREATER
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.ID
     *
     * @return the value of C_MERCH_INV_LOG_V.ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.ID
     *
     * @param id the value for C_MERCH_INV_LOG_V.ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.MATERIAL_ID
     *
     * @return the value of C_MERCH_INV_LOG_V.MATERIAL_ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.MATERIAL_ID
     *
     * @param materialId the value for C_MERCH_INV_LOG_V.MATERIAL_ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.D_NUM
     *
     * @return the value of C_MERCH_INV_LOG_V.D_NUM
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public BigDecimal getdNum() {
        return dNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.D_NUM
     *
     * @param dNum the value for C_MERCH_INV_LOG_V.D_NUM
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setdNum(BigDecimal dNum) {
        this.dNum = dNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.INV_NUM
     *
     * @return the value of C_MERCH_INV_LOG_V.INV_NUM
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public BigDecimal getInvNum() {
        return invNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.INV_NUM
     *
     * @param invNum the value for C_MERCH_INV_LOG_V.INV_NUM
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setInvNum(BigDecimal invNum) {
        this.invNum = invNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.CREATE_TS
     *
     * @return the value of C_MERCH_INV_LOG_V.CREATE_TS
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.CREATE_TS
     *
     * @param createTs the value for C_MERCH_INV_LOG_V.CREATE_TS
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.CREATE_OID
     *
     * @return the value of C_MERCH_INV_LOG_V.CREATE_OID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public Long getCreateOid() {
        return createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.CREATE_OID
     *
     * @param createOid the value for C_MERCH_INV_LOG_V.CREATE_OID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setCreateOid(Long createOid) {
        this.createOid = createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.VOUCHER_ID
     *
     * @return the value of C_MERCH_INV_LOG_V.VOUCHER_ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getVoucherId() {
        return voucherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.VOUCHER_ID
     *
     * @param voucherId the value for C_MERCH_INV_LOG_V.VOUCHER_ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.MERCH_CUST_ID
     *
     * @return the value of C_MERCH_INV_LOG_V.MERCH_CUST_ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public Long getMerchCustId() {
        return merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.MERCH_CUST_ID
     *
     * @param merchCustId the value for C_MERCH_INV_LOG_V.MERCH_CUST_ID
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setMerchCustId(Long merchCustId) {
        this.merchCustId = merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.PERIOD
     *
     * @return the value of C_MERCH_INV_LOG_V.PERIOD
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getPeriod() {
        return period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.PERIOD
     *
     * @param period the value for C_MERCH_INV_LOG_V.PERIOD
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.ATTRIBUTE1
     *
     * @return the value of C_MERCH_INV_LOG_V.ATTRIBUTE1
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getAttribute1() {
        return attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.ATTRIBUTE1
     *
     * @param attribute1 the value for C_MERCH_INV_LOG_V.ATTRIBUTE1
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.ATTRIBUTE2
     *
     * @return the value of C_MERCH_INV_LOG_V.ATTRIBUTE2
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getAttribute2() {
        return attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.ATTRIBUTE2
     *
     * @param attribute2 the value for C_MERCH_INV_LOG_V.ATTRIBUTE2
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.ATTRIBUTE3
     *
     * @return the value of C_MERCH_INV_LOG_V.ATTRIBUTE3
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getAttribute3() {
        return attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.ATTRIBUTE3
     *
     * @param attribute3 the value for C_MERCH_INV_LOG_V.ATTRIBUTE3
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.ATTRIBUTE4
     *
     * @return the value of C_MERCH_INV_LOG_V.ATTRIBUTE4
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getAttribute4() {
        return attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.ATTRIBUTE4
     *
     * @param attribute4 the value for C_MERCH_INV_LOG_V.ATTRIBUTE4
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.REMARK
     *
     * @return the value of C_MERCH_INV_LOG_V.REMARK
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.REMARK
     *
     * @param remark the value for C_MERCH_INV_LOG_V.REMARK
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.TYPE
     *
     * @return the value of C_MERCH_INV_LOG_V.TYPE
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.TYPE
     *
     * @param type the value for C_MERCH_INV_LOG_V.TYPE
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.SOURCE
     *
     * @return the value of C_MERCH_INV_LOG_V.SOURCE
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.SOURCE
     *
     * @param source the value for C_MERCH_INV_LOG_V.SOURCE
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_INV_LOG_V.C_NUM
     *
     * @return the value of C_MERCH_INV_LOG_V.C_NUM
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public BigDecimal getcNum() {
        return cNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_INV_LOG_V.C_NUM
     *
     * @param cNum the value for C_MERCH_INV_LOG_V.C_NUM
     *
     * @mbggenerated Wed Dec 07 14:46:53 CST 2016
     */
    public void setcNum(BigDecimal cNum) {
        this.cNum = cNum;
    }

	public String getAmounts() {
		return amounts;
	}

	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}
    
    
}