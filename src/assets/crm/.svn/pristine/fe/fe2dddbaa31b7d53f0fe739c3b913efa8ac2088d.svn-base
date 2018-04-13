package com.hhnz.customer.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.hhnz.util.io.excel.util.excel.ExcelField;

public class ImportCustPrice {
	@NotBlank
	@ExcelField(order = 0, header = "CRM客户编号")
	private String crmId;
	@NotBlank
	@ExcelField(order = 1, header = "客户名称")
	private String custname;
	@NotBlank
	@ExcelField(order = 2, header = "物料编码")
	private String materialId;
	@NotNull
	@Min(0)
	@ExcelField(order = 3, header = "客户价格")
	@Digits(fraction=4, integer = 10, message="价格最多4位小数")
	private Double price;
	
	@ExcelField(order = 4, header = "备注")
	private String remark;
	public String getCrmId() {
		return crmId;
	}
	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
