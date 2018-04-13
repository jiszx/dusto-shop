package com.hhnz.customer.model;

import java.math.BigDecimal;
import java.util.Date;

public class CMerchCustProduct {
    private Long id;

    private Long merchCustId;

    private String materialId;

    private String organizationId;

    private Long contractId;

    private Long contractLineid;

    private BigDecimal price;

    private Date createTs;

    private Long createOid;

    private Date updateTs;

    private Long updateOid;

    private BigDecimal hPrice;

    private String states;

    private Date bDate;

    private Date eDate;

    private Long basePriceId;

    private Long oldProductId;

    private BigDecimal cifPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchCustId() {
        return merchCustId;
    }

    public void setMerchCustId(Long merchCustId) {
        this.merchCustId = merchCustId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getContractLineid() {
        return contractLineid;
    }

    public void setContractLineid(Long contractLineid) {
        this.contractLineid = contractLineid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public Long getCreateOid() {
        return createOid;
    }

    public void setCreateOid(Long createOid) {
        this.createOid = createOid;
    }

    public Date getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    public Long getUpdateOid() {
        return updateOid;
    }

    public void setUpdateOid(Long updateOid) {
        this.updateOid = updateOid;
    }

    public BigDecimal gethPrice() {
        return hPrice;
    }

    public void sethPrice(BigDecimal hPrice) {
        this.hPrice = hPrice;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public Long getBasePriceId() {
        return basePriceId;
    }

    public void setBasePriceId(Long basePriceId) {
        this.basePriceId = basePriceId;
    }

    public Long getOldProductId() {
        return oldProductId;
    }

    public void setOldProductId(Long oldProductId) {
        this.oldProductId = oldProductId;
    }

    public BigDecimal getCifPrice() {
        return cifPrice;
    }

    public void setCifPrice(BigDecimal cifPrice) {
        this.cifPrice = cifPrice;
    }
}