package com.hhnz.invoiceSecurity.model;

import java.math.BigDecimal;
import java.util.Date;

public class CrmTaxInvoice {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.ID
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.SOURCES_NO
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private String sourcesNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.SOURCES_ID
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private Long sourcesId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.CREATE_TS
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private Date createTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.CREATE_OID
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private Long createOid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.MSG
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private String msg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.SOURCES_TYPE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private String sourcesType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.INVOICES_TYPE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private String invoicesType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.INFO_NUMBER
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private String infoNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.INFO_TYPE_CODE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private String infoTypeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.INFO_MONTH
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private String infoMonth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.INFO_DATE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private String infoDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.INFO_AMOUNT
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private BigDecimal infoAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.INFO_TAX_AMOUNT
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private BigDecimal infoTaxAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_TAX_INVOICE.IS_ISMERGE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    private String isIsmerge;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.ID
     *
     * @return the value of CRM_TAX_INVOICE.ID
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.ID
     *
     * @param id the value for CRM_TAX_INVOICE.ID
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.SOURCES_NO
     *
     * @return the value of CRM_TAX_INVOICE.SOURCES_NO
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public String getSourcesNo() {
        return sourcesNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.SOURCES_NO
     *
     * @param sourcesNo the value for CRM_TAX_INVOICE.SOURCES_NO
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setSourcesNo(String sourcesNo) {
        this.sourcesNo = sourcesNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.SOURCES_ID
     *
     * @return the value of CRM_TAX_INVOICE.SOURCES_ID
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public Long getSourcesId() {
        return sourcesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.SOURCES_ID
     *
     * @param sourcesId the value for CRM_TAX_INVOICE.SOURCES_ID
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setSourcesId(Long sourcesId) {
        this.sourcesId = sourcesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.CREATE_TS
     *
     * @return the value of CRM_TAX_INVOICE.CREATE_TS
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.CREATE_TS
     *
     * @param createTs the value for CRM_TAX_INVOICE.CREATE_TS
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.CREATE_OID
     *
     * @return the value of CRM_TAX_INVOICE.CREATE_OID
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public Long getCreateOid() {
        return createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.CREATE_OID
     *
     * @param createOid the value for CRM_TAX_INVOICE.CREATE_OID
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setCreateOid(Long createOid) {
        this.createOid = createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.MSG
     *
     * @return the value of CRM_TAX_INVOICE.MSG
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public String getMsg() {
        return msg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.MSG
     *
     * @param msg the value for CRM_TAX_INVOICE.MSG
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.SOURCES_TYPE
     *
     * @return the value of CRM_TAX_INVOICE.SOURCES_TYPE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public String getSourcesType() {
        return sourcesType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.SOURCES_TYPE
     *
     * @param sourcesType the value for CRM_TAX_INVOICE.SOURCES_TYPE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setSourcesType(String sourcesType) {
        this.sourcesType = sourcesType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.INVOICES_TYPE
     *
     * @return the value of CRM_TAX_INVOICE.INVOICES_TYPE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public String getInvoicesType() {
        return invoicesType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.INVOICES_TYPE
     *
     * @param invoicesType the value for CRM_TAX_INVOICE.INVOICES_TYPE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setInvoicesType(String invoicesType) {
        this.invoicesType = invoicesType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.INFO_NUMBER
     *
     * @return the value of CRM_TAX_INVOICE.INFO_NUMBER
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public String getInfoNumber() {
        return infoNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.INFO_NUMBER
     *
     * @param infoNumber the value for CRM_TAX_INVOICE.INFO_NUMBER
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setInfoNumber(String infoNumber) {
        this.infoNumber = infoNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.INFO_TYPE_CODE
     *
     * @return the value of CRM_TAX_INVOICE.INFO_TYPE_CODE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public String getInfoTypeCode() {
        return infoTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.INFO_TYPE_CODE
     *
     * @param infoTypeCode the value for CRM_TAX_INVOICE.INFO_TYPE_CODE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setInfoTypeCode(String infoTypeCode) {
        this.infoTypeCode = infoTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.INFO_MONTH
     *
     * @return the value of CRM_TAX_INVOICE.INFO_MONTH
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public String getInfoMonth() {
        return infoMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.INFO_MONTH
     *
     * @param infoMonth the value for CRM_TAX_INVOICE.INFO_MONTH
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setInfoMonth(String infoMonth) {
        this.infoMonth = infoMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.INFO_DATE
     *
     * @return the value of CRM_TAX_INVOICE.INFO_DATE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public String getInfoDate() {
        return infoDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.INFO_DATE
     *
     * @param infoDate the value for CRM_TAX_INVOICE.INFO_DATE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.INFO_AMOUNT
     *
     * @return the value of CRM_TAX_INVOICE.INFO_AMOUNT
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public BigDecimal getInfoAmount() {
        return infoAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.INFO_AMOUNT
     *
     * @param infoAmount the value for CRM_TAX_INVOICE.INFO_AMOUNT
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setInfoAmount(BigDecimal infoAmount) {
        this.infoAmount = infoAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.INFO_TAX_AMOUNT
     *
     * @return the value of CRM_TAX_INVOICE.INFO_TAX_AMOUNT
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public BigDecimal getInfoTaxAmount() {
        return infoTaxAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.INFO_TAX_AMOUNT
     *
     * @param infoTaxAmount the value for CRM_TAX_INVOICE.INFO_TAX_AMOUNT
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setInfoTaxAmount(BigDecimal infoTaxAmount) {
        this.infoTaxAmount = infoTaxAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_TAX_INVOICE.IS_ISMERGE
     *
     * @return the value of CRM_TAX_INVOICE.IS_ISMERGE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public String getIsIsmerge() {
        return isIsmerge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_TAX_INVOICE.IS_ISMERGE
     *
     * @param isIsmerge the value for CRM_TAX_INVOICE.IS_ISMERGE
     *
     * @mbggenerated Thu Mar 23 15:56:01 CST 2017
     */
    public void setIsIsmerge(String isIsmerge) {
        this.isIsmerge = isIsmerge;
    }
}