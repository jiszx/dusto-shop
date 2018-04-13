package com.hhnz.customer.model;

public class CustomerPayerDetail {
  private Long merchId;
  private String merchSap;
  private String merchName;
  private Long payerId;
  private String payerSap;
  private String payerName;
  private String statesDescri;

  public Long getMerchId() {
    return merchId;
  }

  public void setMerchId(Long merchId) {
    this.merchId = merchId;
  }

  public String getMerchSap() {
    return merchSap;
  }

  public void setMerchSap(String merchSap) {
    this.merchSap = merchSap;
  }

  public String getMerchName() {
    return merchName;
  }

  public void setMerchName(String merchName) {
    this.merchName = merchName;
  }

  public Long getPayerId() {
    return payerId;
  }

  public void setPayerId(Long payerId) {
    this.payerId = payerId;
  }

  public String getPayerSap() {
    return payerSap;
  }

  public void setPayerSap(String payerSap) {
    this.payerSap = payerSap;
  }

  public String getPayerName() {
    return payerName;
  }

  public void setPayerName(String payerName) {
    this.payerName = payerName;
  }

  public String getStatesDescri() {
    return statesDescri;
  }

  public void setStatesDescri(String statesDescri) {
    this.statesDescri = statesDescri;
  }

}
