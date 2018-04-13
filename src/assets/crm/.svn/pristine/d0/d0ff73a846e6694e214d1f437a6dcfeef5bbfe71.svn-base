package com.hhnz.jco.business.vendor;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.BaseInput;

/**
 * 客户主数据接口输入参数
 * @author: chaoyang.ren
 * @date:2016年8月18日
 * @time:下午1:35:38
 * @email:chaoyang.ren@foxmail.com
 */
public class InputDTO extends BaseInput{
	@SerializedName("IN_VENDOR_CRM")
	private Vendor vendor;
	
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public class Vendor{
		/**
		 * 公司
		 */
		@SerializedName("BUKRS")
		private String company;
		
		/**
		 * 供应商名称
		 */
		@SerializedName("NAME1")
		private String name;
		
		/**
		 * crm编号 name4
		 */
		@SerializedName("CRM_NO")
		private String crmRelatedId;
		
		/**
		 * 街道
		 */
		@SerializedName("STREET")
		private String street;
		
		/**
		 * 增值税登记号
		 */
		@SerializedName("STCEG")
		private String taxNo;
		
		/**
		 * 排序
		 */
		@SerializedName("SORT1")
		private String sort;
		
		/**
		 * 创建人名称
		 */
		@SerializedName("ERNAM")
		private String createName;
		
		/**
		 * sap编号
		 */
		@SerializedName("LIFNR")
		private String sapCustomerId;

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCrmRelatedId() {
			return crmRelatedId;
		}

		public void setCrmRelatedId(String crmRelatedId) {
			this.crmRelatedId = crmRelatedId;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getTaxNo() {
			return taxNo;
		}

		public void setTaxNo(String taxNo) {
			this.taxNo = taxNo;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}

		public String getCreateName() {
			return createName;
		}

		public void setCreateName(String createName) {
			this.createName = createName;
		}

		public String getSapCustomerId() {
			return sapCustomerId;
		}

		public void setSapCustomerId(String sapCustomerId) {
			this.sapCustomerId = sapCustomerId;
		}
	}

	@Override
	public Serializable getCrmRelatedId() {
		return vendor.getCrmRelatedId();
	}
}
