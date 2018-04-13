package com.hhnz.combination.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CrmMaterialPackageHeader {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.ID
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.NAME
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.CODE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.B_DATE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date bDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.E_DATE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date eDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.CREATE_TS
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private Date createTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.CREATE_OID
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private Long createOid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.UPDATE_TS
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private Date updateTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.UPDATE_OID
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private Long updateOid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.MODEL_TYPE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String modelType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.STATES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String states;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE1
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String attribute1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE2
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String attribute2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE3
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String attribute3;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE4
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String attribute4;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE5
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String attribute5;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.PRICE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_HEADER.WEIGHT
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    private String weight;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ID
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.ID
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ID
     *
     * @param id the value for CRM_MATERIAL_PACKAGE_HEADER.ID
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.NAME
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.NAME
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.NAME
     *
     * @param name the value for CRM_MATERIAL_PACKAGE_HEADER.NAME
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.CODE
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.CODE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.CODE
     *
     * @param code the value for CRM_MATERIAL_PACKAGE_HEADER.CODE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.B_DATE
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.B_DATE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public Date getbDate() {
        return bDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.B_DATE
     *
     * @param bDate the value for CRM_MATERIAL_PACKAGE_HEADER.B_DATE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.E_DATE
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.E_DATE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public Date geteDate() {
        return eDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.E_DATE
     *
     * @param eDate the value for CRM_MATERIAL_PACKAGE_HEADER.E_DATE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.CREATE_TS
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.CREATE_TS
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.CREATE_TS
     *
     * @param createTs the value for CRM_MATERIAL_PACKAGE_HEADER.CREATE_TS
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.CREATE_OID
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.CREATE_OID
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public Long getCreateOid() {
        return createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.CREATE_OID
     *
     * @param createOid the value for CRM_MATERIAL_PACKAGE_HEADER.CREATE_OID
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setCreateOid(Long createOid) {
        this.createOid = createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.UPDATE_TS
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.UPDATE_TS
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public Date getUpdateTs() {
        return updateTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.UPDATE_TS
     *
     * @param updateTs the value for CRM_MATERIAL_PACKAGE_HEADER.UPDATE_TS
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.UPDATE_OID
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.UPDATE_OID
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public Long getUpdateOid() {
        return updateOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.UPDATE_OID
     *
     * @param updateOid the value for CRM_MATERIAL_PACKAGE_HEADER.UPDATE_OID
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setUpdateOid(Long updateOid) {
        this.updateOid = updateOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.MODEL_TYPE
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.MODEL_TYPE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getModelType() {
        return modelType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.MODEL_TYPE
     *
     * @param modelType the value for CRM_MATERIAL_PACKAGE_HEADER.MODEL_TYPE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.STATES
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.STATES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getStates() {
        return states;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.STATES
     *
     * @param states the value for CRM_MATERIAL_PACKAGE_HEADER.STATES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setStates(String states) {
        this.states = states;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE1
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE1
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getAttribute1() {
        return attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE1
     *
     * @param attribute1 the value for CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE1
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE2
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE2
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getAttribute2() {
        return attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE2
     *
     * @param attribute2 the value for CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE2
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE3
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE3
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getAttribute3() {
        return attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE3
     *
     * @param attribute3 the value for CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE3
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE4
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE4
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getAttribute4() {
        return attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE4
     *
     * @param attribute4 the value for CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE4
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE5
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE5
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getAttribute5() {
        return attribute5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE5
     *
     * @param attribute5 the value for CRM_MATERIAL_PACKAGE_HEADER.ATTRIBUTE5
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.PRICE
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.PRICE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.PRICE
     *
     * @param price the value for CRM_MATERIAL_PACKAGE_HEADER.PRICE
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_HEADER.WEIGHT
     *
     * @return the value of CRM_MATERIAL_PACKAGE_HEADER.WEIGHT
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public String getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_HEADER.WEIGHT
     *
     * @param weight the value for CRM_MATERIAL_PACKAGE_HEADER.WEIGHT
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }
}