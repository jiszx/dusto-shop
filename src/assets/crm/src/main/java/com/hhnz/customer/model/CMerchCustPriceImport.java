package com.hhnz.customer.model;

import java.math.BigDecimal;

public class CMerchCustPriceImport {
    private Long custMerchId;

    private String custName;

    private Long maintenanceId;

    private String materialId;

    private BigDecimal price;

    private String remark;

    public Long getCustMerchId() {
        return custMerchId;
    }

    public void setCustMerchId(Long custMerchId) {
        this.custMerchId = custMerchId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}