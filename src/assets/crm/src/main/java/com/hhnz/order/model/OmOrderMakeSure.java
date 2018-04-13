package com.hhnz.order.model;

import java.math.BigDecimal;

import com.hhnz.util.io.excel.util.excel.ExcelField;

public class OmOrderMakeSure {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.MERCH_CUST_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    private Long merchCustId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.NAME
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    @ExcelField(order = 1, header = "客户名称")
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    @ExcelField(order = 2, header = "CRM调拨单编码")
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.SAP_ORDER_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    private String sapOrderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.ORDER_TYPE
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    @ExcelField(order = 3, header = "订单类型")
    private String orderType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.CUST_TYPE
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    @ExcelField(order = 4, header = "客户类型")
    private String custType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.TASKINST_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    private String taskinstId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.PROCESS_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    private String processId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.ORDER_AMT
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    @ExcelField(order = 5, header = "订单金额")
    private BigDecimal orderAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.ORDER_NUM
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    @ExcelField(order = 6, header = "订单数量")
    private BigDecimal orderNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.ORDER_WEIGTH
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    @ExcelField(order = 7, header = "订单重量")
    private BigDecimal orderWeigth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.SEND_NUM
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    @ExcelField(order = 8, header = "发货数量")
    private BigDecimal sendNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_ORDER_MAKE_SURE.SEND_WEIGTH
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    @ExcelField(order = 9, header = "发货重量")
    private BigDecimal sendWeigth;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.MERCH_CUST_ID
     *
     * @return the value of OM_ORDER_MAKE_SURE.MERCH_CUST_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public Long getMerchCustId() {
        return merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.MERCH_CUST_ID
     *
     * @param merchCustId the value for OM_ORDER_MAKE_SURE.MERCH_CUST_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setMerchCustId(Long merchCustId) {
        this.merchCustId = merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.NAME
     *
     * @return the value of OM_ORDER_MAKE_SURE.NAME
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.NAME
     *
     * @param name the value for OM_ORDER_MAKE_SURE.NAME
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.ID
     *
     * @return the value of OM_ORDER_MAKE_SURE.ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.ID
     *
     * @param id the value for OM_ORDER_MAKE_SURE.ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.SAP_ORDER_ID
     *
     * @return the value of OM_ORDER_MAKE_SURE.SAP_ORDER_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public String getSapOrderId() {
        return sapOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.SAP_ORDER_ID
     *
     * @param sapOrderId the value for OM_ORDER_MAKE_SURE.SAP_ORDER_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setSapOrderId(String sapOrderId) {
        this.sapOrderId = sapOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.ORDER_TYPE
     *
     * @return the value of OM_ORDER_MAKE_SURE.ORDER_TYPE
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.ORDER_TYPE
     *
     * @param orderType the value for OM_ORDER_MAKE_SURE.ORDER_TYPE
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.CUST_TYPE
     *
     * @return the value of OM_ORDER_MAKE_SURE.CUST_TYPE
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public String getCustType() {
        return custType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.CUST_TYPE
     *
     * @param custType the value for OM_ORDER_MAKE_SURE.CUST_TYPE
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setCustType(String custType) {
        this.custType = custType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.TASKINST_ID
     *
     * @return the value of OM_ORDER_MAKE_SURE.TASKINST_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public String getTaskinstId() {
        return taskinstId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.TASKINST_ID
     *
     * @param taskinstId the value for OM_ORDER_MAKE_SURE.TASKINST_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setTaskinstId(String taskinstId) {
        this.taskinstId = taskinstId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.PROCESS_ID
     *
     * @return the value of OM_ORDER_MAKE_SURE.PROCESS_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.PROCESS_ID
     *
     * @param processId the value for OM_ORDER_MAKE_SURE.PROCESS_ID
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setProcessId(String processId) {
        this.processId = processId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.ORDER_AMT
     *
     * @return the value of OM_ORDER_MAKE_SURE.ORDER_AMT
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.ORDER_AMT
     *
     * @param orderAmt the value for OM_ORDER_MAKE_SURE.ORDER_AMT
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.ORDER_NUM
     *
     * @return the value of OM_ORDER_MAKE_SURE.ORDER_NUM
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public BigDecimal getOrderNum() {
        return orderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.ORDER_NUM
     *
     * @param orderNum the value for OM_ORDER_MAKE_SURE.ORDER_NUM
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setOrderNum(BigDecimal orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.ORDER_WEIGTH
     *
     * @return the value of OM_ORDER_MAKE_SURE.ORDER_WEIGTH
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public BigDecimal getOrderWeigth() {
        return orderWeigth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.ORDER_WEIGTH
     *
     * @param orderWeigth the value for OM_ORDER_MAKE_SURE.ORDER_WEIGTH
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setOrderWeigth(BigDecimal orderWeigth) {
        this.orderWeigth = orderWeigth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.SEND_NUM
     *
     * @return the value of OM_ORDER_MAKE_SURE.SEND_NUM
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public BigDecimal getSendNum() {
        return sendNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.SEND_NUM
     *
     * @param sendNum the value for OM_ORDER_MAKE_SURE.SEND_NUM
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setSendNum(BigDecimal sendNum) {
        this.sendNum = sendNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_ORDER_MAKE_SURE.SEND_WEIGTH
     *
     * @return the value of OM_ORDER_MAKE_SURE.SEND_WEIGTH
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public BigDecimal getSendWeigth() {
        return sendWeigth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_ORDER_MAKE_SURE.SEND_WEIGTH
     *
     * @param sendWeigth the value for OM_ORDER_MAKE_SURE.SEND_WEIGTH
     *
     * @mbggenerated Wed Mar 01 17:27:59 CST 2017
     */
    public void setSendWeigth(BigDecimal sendWeigth) {
        this.sendWeigth = sendWeigth;
    }
}