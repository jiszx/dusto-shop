package com.hhnz.crm.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.hhnz.crm.enu.PriceAdjustOperation;


public class ProductPriceAdjustEditDTO {

	@NotBlank
	private String materialId;
	
	@NotBlank
	private String organizationId;
	
	@NotBlank
	private String channel = "10";

    @NotNull
    private BigDecimal adjustVal;

    @NotBlank
    private String adjustOpt;

    @NotNull
    @DateTimeFormat(iso=ISO.DATE)
    private Date bdate;

    @NotNull
    @DateTimeFormat(iso=ISO.DATE)
    private Date edate;

    public BigDecimal getAdjustVal() {
        return adjustVal;
    }

    public void setAdjustVal(BigDecimal adjustVal) {
        this.adjustVal = adjustVal;
    }

    public String getAdjustOpt() {
        return adjustOpt;
    }
    
    public PriceAdjustOperation getAdjustOptEnu() {
    	return StringUtils.isBlank(adjustOpt)?null:PriceAdjustOperation.toEnum(adjustOpt);
    }

    public void setAdjustOpt(String adjustOpt) {
        this.adjustOpt = adjustOpt;
    }
    
    public void setAdjustOpt(PriceAdjustOperation adjustOpt) {
    	this.adjustOpt = adjustOpt==null?null:adjustOpt.getCode();
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
    
}