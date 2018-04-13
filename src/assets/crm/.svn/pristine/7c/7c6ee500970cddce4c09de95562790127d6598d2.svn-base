package com.hhnz.crm.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.hhnz.crm.enu.PriceAdjustCategory;
import com.hhnz.crm.enu.PriceAdjustOperation;


public class ProductPriceAdjustDTO {

	@NotBlank
    private String adjustParam;

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

    @NotBlank
    private String adjustCategory;

	public String getAdjustParam() {
        return adjustParam;
    }

    public void setAdjustParam(String adjustParam) {
        this.adjustParam = adjustParam;
    }

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


    public String getAdjustCategory() {
        return adjustCategory;
    }
    
    public PriceAdjustCategory getAdjustCategoryEnu() {
    	return StringUtils.isBlank(adjustCategory)?null:PriceAdjustCategory.toEnum(adjustCategory);
    }

    public void setAdjustCategory(String adjustCategory) {
        this.adjustCategory = adjustCategory;
    }
    
    public void setAdjustCategory(PriceAdjustCategory adjustCategory) {
    	this.adjustCategory = adjustCategory==null?null:adjustCategory.getCode();;
    }
}