package com.hhnz.account.model;

import java.math.BigDecimal;
import java.util.Date;

public class OmInvoiceV {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.ID
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.CUSTOMER_ID
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private Long customerId;
    
    private Long merchCustId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.INVOICE_NO
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private String invoiceNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.TOTAL_PRICE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private BigDecimal totalPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.TOTAL_TAX
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private BigDecimal totalTax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.PERIOD
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private String period;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.DRAW_DATE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private Date drawDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.MEMO
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.WRITEOFF_INVOICE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private String writeoffInvoice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.NAME
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.ORGNAME
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private String orgname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.VERIFIE_AMT
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private BigDecimal verifieAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OM_INVOICE_V.TYPE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    private String type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.ID
     *
     * @return the value of OM_INVOICE_V.ID
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.ID
     *
     * @param id the value for OM_INVOICE_V.ID
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.CUSTOMER_ID
     *
     * @return the value of OM_INVOICE_V.CUSTOMER_ID
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.CUSTOMER_ID
     *
     * @param customerId the value for OM_INVOICE_V.CUSTOMER_ID
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.INVOICE_NO
     *
     * @return the value of OM_INVOICE_V.INVOICE_NO
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.INVOICE_NO
     *
     * @param invoiceNo the value for OM_INVOICE_V.INVOICE_NO
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.TOTAL_PRICE
     *
     * @return the value of OM_INVOICE_V.TOTAL_PRICE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.TOTAL_PRICE
     *
     * @param totalPrice the value for OM_INVOICE_V.TOTAL_PRICE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.TOTAL_TAX
     *
     * @return the value of OM_INVOICE_V.TOTAL_TAX
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.TOTAL_TAX
     *
     * @param totalTax the value for OM_INVOICE_V.TOTAL_TAX
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.PERIOD
     *
     * @return the value of OM_INVOICE_V.PERIOD
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public String getPeriod() {
        return period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.PERIOD
     *
     * @param period the value for OM_INVOICE_V.PERIOD
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.DRAW_DATE
     *
     * @return the value of OM_INVOICE_V.DRAW_DATE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public Date getDrawDate() {
        return drawDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.DRAW_DATE
     *
     * @param drawDate the value for OM_INVOICE_V.DRAW_DATE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.MEMO
     *
     * @return the value of OM_INVOICE_V.MEMO
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.MEMO
     *
     * @param memo the value for OM_INVOICE_V.MEMO
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.WRITEOFF_INVOICE
     *
     * @return the value of OM_INVOICE_V.WRITEOFF_INVOICE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public String getWriteoffInvoice() {
        return writeoffInvoice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.WRITEOFF_INVOICE
     *
     * @param writeoffInvoice the value for OM_INVOICE_V.WRITEOFF_INVOICE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setWriteoffInvoice(String writeoffInvoice) {
        this.writeoffInvoice = writeoffInvoice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.NAME
     *
     * @return the value of OM_INVOICE_V.NAME
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.NAME
     *
     * @param name the value for OM_INVOICE_V.NAME
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.ORGNAME
     *
     * @return the value of OM_INVOICE_V.ORGNAME
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public String getOrgname() {
        return orgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.ORGNAME
     *
     * @param orgname the value for OM_INVOICE_V.ORGNAME
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.VERIFIE_AMT
     *
     * @return the value of OM_INVOICE_V.VERIFIE_AMT
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public BigDecimal getVerifieAmt() {
        return verifieAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.VERIFIE_AMT
     *
     * @param verifieAmt the value for OM_INVOICE_V.VERIFIE_AMT
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setVerifieAmt(BigDecimal verifieAmt) {
        this.verifieAmt = verifieAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OM_INVOICE_V.TYPE
     *
     * @return the value of OM_INVOICE_V.TYPE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OM_INVOICE_V.TYPE
     *
     * @param type the value for OM_INVOICE_V.TYPE
     *
     * @mbggenerated Mon Nov 07 16:21:51 CST 2016
     */
    public void setType(String type) {
        this.type = type;
    }

	public Long getMerchCustId() {
		return merchCustId;
	}

	public void setMerchCustId(Long merchCustId) {
		this.merchCustId = merchCustId;
	}
    
    
}