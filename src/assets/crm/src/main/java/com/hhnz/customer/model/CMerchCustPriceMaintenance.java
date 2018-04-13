package com.hhnz.customer.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CMerchCustPriceMaintenance {
    private Long id;

    private String orgId;

    private String areaId;

    private String reginId;

    private Long merchCustId;

    private String brand;

    private Date createTs;

    private Long createOid;

    private Date updateTs;

    private Long updateOid;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eDate;

    private String attribute1;

    private String attribute2;

    private String materialId;

    private String type;

    private BigDecimal price;

    private String states;

    private String adjustDirection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getReginId() {
        return reginId;
    }

    public void setReginId(String reginId) {
        this.reginId = reginId;
    }

    public Long getMerchCustId() {
        return merchCustId;
    }

    public void setMerchCustId(Long merchCustId) {
        this.merchCustId = merchCustId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getAdjustDirection() {
        return adjustDirection;
    }

    public void setAdjustDirection(String adjustDirection) {
        this.adjustDirection = adjustDirection;
    }
}