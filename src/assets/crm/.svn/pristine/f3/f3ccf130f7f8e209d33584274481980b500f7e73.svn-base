package com.hhnz.order.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.hhnz.util.io.excel.util.excel.ExcelField;

/**
 * @author: chaoyang.ren  
 * @date:May 22, 2017  
 * @time:4:48:34 PM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
public class RetailOrderImportDTO {
	@ExcelField(order = 0, header = "日期")
	private Date orderDate;
	@ExcelField(order = 1, header = "仓储服务商ID")
	@NotNull
	private Long warehousingId;
	/*@NotBlank
	@ExcelField(order = 3, header = "所属销售组织")
	private String orgId;*/
	/*@ExcelField(order = 4, header = "销售团队")
	private String salesTeam;
	@ExcelField(order = 5, header = "大区")
	private String salesRegion;
	@ExcelField(order = 6, header = "省区")
	private String province;*/
	@NotNull
	@ExcelField(order = 7, header = "客户ID")
	private Long merchCustId;
	
	//增票需要字段
	/*@ExcelField(order = 8, header = "税号")
	private String taxNo;
	@ExcelField(order = 9, header = "地址")
	private String address;
	@ExcelField(order = 10, header = "电话")
	private String tel;
	@ExcelField(order = 11, header = "开户行")
	private String bank;
	@ExcelField(order = 12, header = "银行账号")
	private String bankAccount;
	
	@ExcelField(order = 13, header = "规格型号")
	private String specification;*/
	@NotBlank
	@ExcelField(order = 15, header = "物料编码")
	private String materialNo;
	/*@ExcelField(order = 15, header = "物料描述")
	private String materialDesc;
	@NotBlank
	@ExcelField(order = 16, header = "单位")
	private String unit;*/
	@NotNull
	@Min(0)
	@ExcelField(order = 18, header = "数量")
	private Double num;
	@NotNull
	@Min(0)
	@ExcelField(order = 19, header = "未税单价")
	private Double price;
	@NotNull
	@Min(0)
	@ExcelField(order = 20, header = "未税金额")
	private Double amount;
	/*@NotNull
	@Min(0)
	@ExcelField(order = 20, header = "税率")
	private Double taxRate;
	@NotNull
	@Min(0)
	@ExcelField(order = 21, header = "税额")
	private Double tax;
	@NotNull
	@Min(0)
	@ExcelField(order = 22, header = "价税合计")
	private Double toalAmount;
	@NotBlank
	@ExcelField(order = 23, header = "发票类型")
	private String invoiceType;*/
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Long getWarehousingId() {
		return warehousingId;
	}
	public void setWarehousingId(Long warehousingId) {
		this.warehousingId = warehousingId;
	}
	public Long getMerchCustId() {
		return merchCustId;
	}
	public void setMerchCustId(Long merchCustId) {
		this.merchCustId = merchCustId;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}

