package com.hhnz.customerInv.model;

import java.math.BigDecimal;
import java.util.Date;

public class CMerchCustProudctInvV {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.CUSTNAME
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String custname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.SAP_CUSTOMER_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String sapCustomerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.ORGNAME
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String orgname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.SKU
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String sku;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.RDCNAME
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String rdcname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.RDC_CODE
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String rdcCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.STATION_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private Long stationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.SPECIFICATIONS
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String specifications;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.AMOUNTS
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String amounts;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.MERCH_CUST_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private Long merchCustId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.ORGANIZATION_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String organizationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.INV_NUM
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private BigDecimal invNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.FROZEN_NUM
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private BigDecimal frozenNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.CREATE_TS
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private Date createTs;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.CREATE_OID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private Long createOid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.CONTRACT_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private Long contractId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_PROUDCT_INV_V.MATERIAL_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    private String materialId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.CUSTNAME
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.CUSTNAME
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getCustname() {
        return custname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.CUSTNAME
     *
     * @param custname the value for C_MERCH_CUST_PROUDCT_INV_V.CUSTNAME
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setCustname(String custname) {
        this.custname = custname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.SAP_CUSTOMER_ID
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.SAP_CUSTOMER_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getSapCustomerId() {
        return sapCustomerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.SAP_CUSTOMER_ID
     *
     * @param sapCustomerId the value for C_MERCH_CUST_PROUDCT_INV_V.SAP_CUSTOMER_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setSapCustomerId(String sapCustomerId) {
        this.sapCustomerId = sapCustomerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.ORGNAME
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.ORGNAME
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getOrgname() {
        return orgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.ORGNAME
     *
     * @param orgname the value for C_MERCH_CUST_PROUDCT_INV_V.ORGNAME
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.SKU
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.SKU
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getSku() {
        return sku;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.SKU
     *
     * @param sku the value for C_MERCH_CUST_PROUDCT_INV_V.SKU
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.RDCNAME
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.RDCNAME
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getRdcname() {
        return rdcname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.RDCNAME
     *
     * @param rdcname the value for C_MERCH_CUST_PROUDCT_INV_V.RDCNAME
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setRdcname(String rdcname) {
        this.rdcname = rdcname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.RDC_CODE
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.RDC_CODE
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getRdcCode() {
        return rdcCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.RDC_CODE
     *
     * @param rdcCode the value for C_MERCH_CUST_PROUDCT_INV_V.RDC_CODE
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setRdcCode(String rdcCode) {
        this.rdcCode = rdcCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.STATION_ID
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.STATION_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public Long getStationId() {
        return stationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.STATION_ID
     *
     * @param stationId the value for C_MERCH_CUST_PROUDCT_INV_V.STATION_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.SPECIFICATIONS
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.SPECIFICATIONS
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.SPECIFICATIONS
     *
     * @param specifications the value for C_MERCH_CUST_PROUDCT_INV_V.SPECIFICATIONS
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.AMOUNTS
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.AMOUNTS
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getAmounts() {
        return amounts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.AMOUNTS
     *
     * @param amounts the value for C_MERCH_CUST_PROUDCT_INV_V.AMOUNTS
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.ID
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.ID
     *
     * @param id the value for C_MERCH_CUST_PROUDCT_INV_V.ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.MERCH_CUST_ID
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.MERCH_CUST_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public Long getMerchCustId() {
        return merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.MERCH_CUST_ID
     *
     * @param merchCustId the value for C_MERCH_CUST_PROUDCT_INV_V.MERCH_CUST_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setMerchCustId(Long merchCustId) {
        this.merchCustId = merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.ORGANIZATION_ID
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.ORGANIZATION_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.ORGANIZATION_ID
     *
     * @param organizationId the value for C_MERCH_CUST_PROUDCT_INV_V.ORGANIZATION_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.INV_NUM
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.INV_NUM
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public BigDecimal getInvNum() {
        return invNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.INV_NUM
     *
     * @param invNum the value for C_MERCH_CUST_PROUDCT_INV_V.INV_NUM
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setInvNum(BigDecimal invNum) {
        this.invNum = invNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.FROZEN_NUM
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.FROZEN_NUM
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public BigDecimal getFrozenNum() {
        return frozenNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.FROZEN_NUM
     *
     * @param frozenNum the value for C_MERCH_CUST_PROUDCT_INV_V.FROZEN_NUM
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setFrozenNum(BigDecimal frozenNum) {
        this.frozenNum = frozenNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.CREATE_TS
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.CREATE_TS
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.CREATE_TS
     *
     * @param createTs the value for C_MERCH_CUST_PROUDCT_INV_V.CREATE_TS
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.CREATE_OID
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.CREATE_OID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public Long getCreateOid() {
        return createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.CREATE_OID
     *
     * @param createOid the value for C_MERCH_CUST_PROUDCT_INV_V.CREATE_OID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setCreateOid(Long createOid) {
        this.createOid = createOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.CONTRACT_ID
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.CONTRACT_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.CONTRACT_ID
     *
     * @param contractId the value for C_MERCH_CUST_PROUDCT_INV_V.CONTRACT_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_PROUDCT_INV_V.MATERIAL_ID
     *
     * @return the value of C_MERCH_CUST_PROUDCT_INV_V.MATERIAL_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_PROUDCT_INV_V.MATERIAL_ID
     *
     * @param materialId the value for C_MERCH_CUST_PROUDCT_INV_V.MATERIAL_ID
     *
     * @mbggenerated Sat Dec 10 17:17:45 CST 2016
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
}