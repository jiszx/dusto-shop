package com.hhnz.crm.model;

import java.math.BigDecimal;
import java.util.Date;

public class TMaterialPrice {
    
    private String materialId;
    
    private String states;

    private Date createTs;

    private String organizationId;
    
    private Long price;

    private String unit;
    
    private Date bdate;
    
    private Date edate;

    private String channel;
    
    private String sapRecordNo;
    
    private BigDecimal adjustPrice;

    
    public BigDecimal getAdjustPrice() {
		return adjustPrice;
	}

	public void setAdjustPrice(BigDecimal adjustPrice) {
		this.adjustPrice = adjustPrice;
	}


	public String getMaterialId() {
        return materialId;
    }

    
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    
    public String getStates() {
        return states;
    }

    
    public void setStates(String states) {
        this.states = states;
    }

    
    public Date getCreateTs() {
        return createTs;
    }

    
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    
    public String getOrganizationId() {
        return organizationId;
    }

    
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    
    public Long getPrice() {
        return price;
    }

    
    public void setPrice(Long price) {
        this.price = price;
    }

    
    public String getUnit() {
        return unit;
    }

    
    public void setUnit(String unit) {
        this.unit = unit;
    }

    
    public Date getBdate() {
        return bdate;
    }

    
    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    
    public Date getEdate() {
        return edate;
    }

    
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    
    public String getChannel() {
        return channel;
    }

    
    public void setChannel(String channel) {
        this.channel = channel;
    }

	public String getSapRecordNo() {
		return sapRecordNo;
	}

	public void setSapRecordNo(String sapRecordNo) {
		this.sapRecordNo = sapRecordNo;
	}
}