package com.hhnz.virtualwarehouse.model;

import java.math.BigDecimal;
import java.util.Date;

public class CrmVirtualWarehouseEntry {
    private Long id;

    private String factoryCode;

    private String custType;

    private String materialId;

    private BigDecimal amt;

    private String status;

    private Date createTs;

    private Long createOid;

    private Date updateTs;

    private Long updateOid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}