package com.hhnz.crm.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class TMaterialPriceAdjustRecord {
    private Long id;

    @NotBlank
    private String materialId;

    private Date createTs;

    @NotBlank
    private String organizationId;

    @NotNull
    private BigDecimal price;

    private String opt;

    @NotNull
    @DateTimeFormat(iso=ISO.DATE)
    private Date bdate;

    @NotNull
    @DateTimeFormat(iso=ISO.DATE)
    private Date edate;

    @NotBlank
    private String channel;

    private Long adjustId;
    
    private Long oprice;

    private BigDecimal oadjustPrice;

    private Date obdate;

    private Date oedate; 
    
    //Transient properties below.
    private Long cprice;
    
    private BigDecimal cadjustPrice;
    
    private Date cbdate;
    
    private Date cedate; 
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
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

    public Long getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(Long adjustId) {
        this.adjustId = adjustId;
    }

	public Long getOprice() {
		return oprice;
	}

	public void setOprice(Long oprice) {
		this.oprice = oprice;
	}

	public BigDecimal getOadjustPrice() {
		return oadjustPrice;
	}

	public void setOadjustPrice(BigDecimal oadjustPrice) {
		this.oadjustPrice = oadjustPrice;
	}

	public Date getObdate() {
		return obdate;
	}

	public void setObdate(Date obdate) {
		this.obdate = obdate;
	}

	public Date getOedate() {
		return oedate;
	}

	public void setOedate(Date oedate) {
		this.oedate = oedate;
	}

	public Long getCprice() {
		return cprice;
	}

	public void setCprice(Long cprice) {
		this.cprice = cprice;
	}

	public BigDecimal getCadjustPrice() {
		return cadjustPrice;
	}

	public void setCadjustPrice(BigDecimal cadjustPrice) {
		this.cadjustPrice = cadjustPrice;
	}

	public Date getCbdate() {
		return cbdate;
	}

	public void setCbdate(Date cbdate) {
		this.cbdate = cbdate;
	}

	public Date getCedate() {
		return cedate;
	}

	public void setCedate(Date cedate) {
		this.cedate = cedate;
	}
}