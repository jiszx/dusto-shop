package com.hhnz.report.model;

import java.math.BigDecimal;

public class RetailOrderInvoice {
  private String createTime;
  private String merchName;
  private String taxNum; // 税号
  private String address;
  private String phone;
  private String bankName;
  private String bankAccount;
  private String sku;
  private String specifications; // 规格
  private String unit;
  private int amounts;
  private BigDecimal num;
  private BigDecimal price;
  private BigDecimal amt;
  private String invoiceType;
  private String invoice; // 发票金额
  private String tax; // 税额
  private String taxRate;
  private String orgName;
  private long headerId;

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getMerchName() {
    return merchName;
  }

  public void setMerchName(String merchName) {
    this.merchName = merchName;
  }

  public String getTaxNum() {
    return taxNum;
  }

  public void setTaxNum(String taxNum) {
    this.taxNum = taxNum;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getSpecifications() {
    return specifications;
  }

  public void setSpecifications(String specifications) {
    this.specifications = specifications;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public int getAmounts() {
    return amounts;
  }

  public void setAmounts(int amounts) {
    this.amounts = amounts;
  }

  public BigDecimal getNum() {
    return num;
  }

  public void setNum(BigDecimal num) {
    this.num = num;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getAmt() {
    return amt;
  }

  public void setAmt(BigDecimal amt) {
    this.amt = amt;
  }

  public String getInvoiceType() {
    return invoiceType;
  }

  public void setInvoiceType(String invoiceType) {
    this.invoiceType = invoiceType;
  }

  public String getInvoice() {
    return invoice;
  }

  public void setInvoice(String invoice) {
    this.invoice = invoice;
  }

  public String getTax() {
    return tax;
  }

  public void setTax(String tax) {
    this.tax = tax;
  }

  public String getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(String taxRate) {
    this.taxRate = taxRate;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public long getHeaderId() {
    return headerId;
  }

  public void setHeaderId(long headerId) {
    this.headerId = headerId;
  }

}
