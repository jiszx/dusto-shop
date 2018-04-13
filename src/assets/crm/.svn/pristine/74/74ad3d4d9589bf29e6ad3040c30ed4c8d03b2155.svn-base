package com.hhnz.crm.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.hhnz.util.io.excel.util.excel.ExcelField;

/**
 * @author: chaoyang.ren  
 * @date:May 22, 2017  
 * @time:4:48:34 PM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
@ScriptAssert(lang = "javascript", script = "_this.bdate.before(_this.edate)", message="结束日期不能小于开始日期")
public class ProductPriceDTO {
	@NotBlank
	@ExcelField(order = 0, header = "物料编号")
	private String materialId;
	@ExcelField(order = 1, header = "销售组织")
	@NotBlank
	private String organizationId;
	@NotBlank
	@ExcelField(order = 2, header = "渠道")
	private String channel = "10";
	@NotNull
	@ExcelField(order = 3, header = "起始日期")
	@DateTimeFormat(iso=ISO.DATE)
	private Date bdate;
	@NotNull
	@ExcelField(order = 4, header = "结束日期")
	@DateTimeFormat(iso=ISO.DATE)
	private Date edate;
	@NotNull
	@Min(0)
	@ExcelField(order = 5, header = "价格")
	@Digits(fraction=4, integer = 10, message="价格最多4位小数")
	private Double price;
	@ExcelField(order = 6, header = "单位")
	private String unit;
	
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}

