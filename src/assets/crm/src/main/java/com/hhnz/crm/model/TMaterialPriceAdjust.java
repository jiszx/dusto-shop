package com.hhnz.crm.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.hhnz.crm.enu.PriceAdjustCategory;
import com.hhnz.crm.enu.PriceAdjustOperation;
import com.hhnz.crm.enu.PriceAdjustStatus;
import com.hhnz.crm.enu.PriceAdjustType;


public class TMaterialPriceAdjust {
	public static final String STANDARD_IMPORT="标准价导入";
	public static final String STANDARD_EDIT="标准价修改";
	public static final String ADJUSTED_EDIT="调整价修改";
	public static final String ADJUSTED_BATCH="调整价批量处理";
	
    private Long id;

    private String adjustType;

    private String adjustParam;

    private BigDecimal adjustVal;

    private String adjustOpt;

    @DateTimeFormat(iso=ISO.DATE)
    private Date bdate;

    @DateTimeFormat(iso=ISO.DATE)
    private Date edate;

    private Date createTs;

    private String adjustDesc;

    private String status;

    private String adjustCategory;
    
    private String processId;
    
    //@Transient
    private String relatedMaterialId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdjustType() {
		return adjustType;
	}

	public PriceAdjustType getAdjustTypeEnu() {
    	return StringUtils.isBlank(adjustType)?null:PriceAdjustType.toEnum(adjustType);
    }

    public void setAdjustType(PriceAdjustType adjustType) {
        this.adjustType = adjustType==null?null:adjustType.getCode();
    }

    public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

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

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public String getAdjustDesc() {
        return adjustDesc;
    }

    public void setAdjustDesc(String adjustDesc) {
        this.adjustDesc = adjustDesc;
    }

    public PriceAdjustStatus getStatusEnu() {
    	return StringUtils.isBlank(status)?null:PriceAdjustStatus.toEnum(status);
    }

	public String getStatus() {
		return status;
	}
	
    public void setStatus(String status) {
		this.status = status;
	}

	public void setStatus(PriceAdjustStatus status) {
        this.status = status==null?null:status.getCode();
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

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getRelatedMaterialId() {
		return relatedMaterialId;
	}

	public void setRelatedMaterialId(String relatedMaterialId) {
		this.relatedMaterialId = relatedMaterialId;
	}
}