package com.hhnz.account.model;

import java.math.BigDecimal;
import java.util.Date;

import com.hhnz.util.BigDecimalASME;

public class CMerchCustRebateOrderV {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.HEADID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private Long headid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.REBATE_ID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private Long rebateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.MATERIAL_ID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private String materialId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.SKU
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private String sku;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.LINEID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private Long lineid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.PRICE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.NUM
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private Long num;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.HB_NUM
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private Long hbNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.ORDER_AMT
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private BigDecimal orderAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.HB_AMT
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private BigDecimal hbAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.TYPE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.BDATE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private Date bdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.EDATE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private Date edate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_REBATE_ORDER_V.SALEREP
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    private String salerep;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.HEADID
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.HEADID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public Long getHeadid() {
        return headid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.HEADID
     *
     * @param headid the value for C_MERCH_CUST_REBATE_ORDER_V.HEADID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setHeadid(Long headid) {
        this.headid = headid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.REBATE_ID
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.REBATE_ID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public Long getRebateId() {
        return rebateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.REBATE_ID
     *
     * @param rebateId the value for C_MERCH_CUST_REBATE_ORDER_V.REBATE_ID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setRebateId(Long rebateId) {
        this.rebateId = rebateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.MATERIAL_ID
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.MATERIAL_ID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.MATERIAL_ID
     *
     * @param materialId the value for C_MERCH_CUST_REBATE_ORDER_V.MATERIAL_ID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.SKU
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.SKU
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public String getSku() {
        return sku;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.SKU
     *
     * @param sku the value for C_MERCH_CUST_REBATE_ORDER_V.SKU
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.LINEID
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.LINEID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public Long getLineid() {
        return lineid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.LINEID
     *
     * @param lineid the value for C_MERCH_CUST_REBATE_ORDER_V.LINEID
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setLineid(Long lineid) {
        this.lineid = lineid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.PRICE
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.PRICE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.PRICE
     *
     * @param price the value for C_MERCH_CUST_REBATE_ORDER_V.PRICE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setPrice(BigDecimal price) {
        this.price = BigDecimalASME.divide(price);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.NUM
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.NUM
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public Long getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.NUM
     *
     * @param num the value for C_MERCH_CUST_REBATE_ORDER_V.NUM
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setNum(Long num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.HB_NUM
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.HB_NUM
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public Long getHbNum() {
        return hbNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.HB_NUM
     *
     * @param hbNum the value for C_MERCH_CUST_REBATE_ORDER_V.HB_NUM
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setHbNum(Long hbNum) {
        this.hbNum = hbNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.ORDER_AMT
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.ORDER_AMT
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.ORDER_AMT
     *
     * @param orderAmt the value for C_MERCH_CUST_REBATE_ORDER_V.ORDER_AMT
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = BigDecimalASME.divide(orderAmt);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.HB_AMT
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.HB_AMT
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public BigDecimal getHbAmt() {
        return hbAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.HB_AMT
     *
     * @param hbAmt the value for C_MERCH_CUST_REBATE_ORDER_V.HB_AMT
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setHbAmt(BigDecimal hbAmt) {
        this.hbAmt = BigDecimalASME.divide(hbAmt);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.TYPE
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.TYPE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.TYPE
     *
     * @param type the value for C_MERCH_CUST_REBATE_ORDER_V.TYPE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.BDATE
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.BDATE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public Date getBdate() {
        return bdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.BDATE
     *
     * @param bdate the value for C_MERCH_CUST_REBATE_ORDER_V.BDATE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.EDATE
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.EDATE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public Date getEdate() {
        return edate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.EDATE
     *
     * @param edate the value for C_MERCH_CUST_REBATE_ORDER_V.EDATE
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_REBATE_ORDER_V.SALEREP
     *
     * @return the value of C_MERCH_CUST_REBATE_ORDER_V.SALEREP
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public String getSalerep() {
        return salerep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_REBATE_ORDER_V.SALEREP
     *
     * @param salerep the value for C_MERCH_CUST_REBATE_ORDER_V.SALEREP
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setSalerep(String salerep) {
        this.salerep = salerep;
    }
}