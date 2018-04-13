package com.hhnz.combination.model;

import java.math.BigDecimal;

public class CrmMaterialPackageRebate {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_REBATE.ID
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_REBATE.HEADER_ID
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    private Long headerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_REBATE.MATERIAL_ID
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    private String materialId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_REBATE.RATIO
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    private String ratio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_REBATE.PRICE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CRM_MATERIAL_PACKAGE_REBATE.LIMIT_NUM
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    private BigDecimal limitNum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_REBATE.ID
     *
     * @return the value of CRM_MATERIAL_PACKAGE_REBATE.ID
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_REBATE.ID
     *
     * @param id the value for CRM_MATERIAL_PACKAGE_REBATE.ID
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_REBATE.HEADER_ID
     *
     * @return the value of CRM_MATERIAL_PACKAGE_REBATE.HEADER_ID
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public Long getHeaderId() {
        return headerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_REBATE.HEADER_ID
     *
     * @param headerId the value for CRM_MATERIAL_PACKAGE_REBATE.HEADER_ID
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_REBATE.MATERIAL_ID
     *
     * @return the value of CRM_MATERIAL_PACKAGE_REBATE.MATERIAL_ID
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_REBATE.MATERIAL_ID
     *
     * @param materialId the value for CRM_MATERIAL_PACKAGE_REBATE.MATERIAL_ID
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_REBATE.RATIO
     *
     * @return the value of CRM_MATERIAL_PACKAGE_REBATE.RATIO
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public String getRatio() {
        return ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_REBATE.RATIO
     *
     * @param ratio the value for CRM_MATERIAL_PACKAGE_REBATE.RATIO
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_REBATE.PRICE
     *
     * @return the value of CRM_MATERIAL_PACKAGE_REBATE.PRICE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_REBATE.PRICE
     *
     * @param price the value for CRM_MATERIAL_PACKAGE_REBATE.PRICE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CRM_MATERIAL_PACKAGE_REBATE.LIMIT_NUM
     *
     * @return the value of CRM_MATERIAL_PACKAGE_REBATE.LIMIT_NUM
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public BigDecimal getLimitNum() {
        return limitNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CRM_MATERIAL_PACKAGE_REBATE.LIMIT_NUM
     *
     * @param limitNum the value for CRM_MATERIAL_PACKAGE_REBATE.LIMIT_NUM
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    public void setLimitNum(BigDecimal limitNum) {
        this.limitNum = limitNum;
    }
}