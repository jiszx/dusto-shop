package com.hhnz.jco.business.customer;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
	@SerializedName("IN_KNA1_KNB1_KNVV")
	private Customer customer;
	
	@SerializedName("IN_ZX_KNBK")
	private List<CustomerBank> banks;
	
	@SerializedName("IN_ZX_KNVA")
	private List<UnloadingPort> ports;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<CustomerBank> getBanks() {
		return banks;
	}
	public void setBanks(List<CustomerBank> banks) {
		this.banks = banks;
	}
	public List<UnloadingPort> getPorts() {
		return ports;
	}
	public void setPorts(List<UnloadingPort> ports) {
		this.ports = ports;
	}

	/**
	 * 卸货点
	 * @author: chaoyang.ren
	 * @date:2016年8月19日
	 * @time:上午10:18:22
	 * @email:chaoyang.ren@foxmail.com
	 */
	public class UnloadingPort{
		@SerializedName("ABLAD")
		private String name;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != getClass()) {
				return false;
			}
			UnloadingPort up = (UnloadingPort) obj;
			return new EqualsBuilder().append(name, up.name).isEquals();
		}
		
		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(name).toHashCode();
		}
	}
	
	/**
	 * 传入参数的客户银行数据
	 * @author: chaoyang.ren
	 * @date:2016年8月19日
	 * @time:上午10:13:11
	 * @email:chaoyang.ren@foxmail.com
	 */
	public class CustomerBank{
		/**
		 * 银行编号
		 */
		@SerializedName("BANKL")
		private String bankNo;
		/**
		 * 银行帐户号码
		 */
		@SerializedName("BANKN")
		private String accountNo;
		/**
		 * 帐户持有人姓名 
		 */
		@SerializedName("KOINH")
		private String accountName;
		/**
		 * 国家代码
		 */
		@SerializedName("BANKS")
		private String countryCode = "CN";
		public String getBankNo() {
			return bankNo;
		}
		public void setBankNo(String bankNo) {
			this.bankNo = bankNo;
		}
		public String getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
		public String getAccountName() {
			return accountName;
		}
		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
	}

	/**
	 * 传入参数的客户主数据
	 * @author: chaoyang.ren
	 * @date:2016年8月19日
	 * @time:上午10:13:23
	 * @email:chaoyang.ren@foxmail.com
	 */
	public class Customer{
		/**
		 * 客户ID
		 */
		@SerializedName("CUSTOMER")
		private String id;
		/**
		 * 客户SAP编码
		 */
		@SerializedName("KUNNR")
		private String sapCustomerId;
		/**
		 * 客户帐户组
		 */
		@SerializedName("KTOKD")
		private String accountGroup;
		/**
		 * 名称
		 */
		@SerializedName("NAME1")
		private String name;
		/**
		 * 名称2
		 */
		@SerializedName("NAME2")
		private String name2;
		/**
		 * 简称
		 */
		@SerializedName("SORTL")
		private String abbrName;
		/**
		 * 增值税登记号
		 */
		@SerializedName("STCEG")
		private String addedTaxNo;
		/**
		 * 税号 5
		 */
//		@SerializedName("STCD5")
//		private String taxNo5;
		/**
		 * 公司代码
		 */
		@SerializedName("BUKRS")
		private String companyCode;
		/**
		 * 总帐中的统驭科目
		 */
		@SerializedName("AKONT")
		private String reconciliationAccounts;
		/**
		 * 销售组织
		 */
		@SerializedName("VKORG")
		private String salesOrg;
		/**
		 * 分销渠道
		 */
		@SerializedName("VTWEG")
		private String channel;
		/**
		 * 产品组
		 */
		@SerializedName("SPART")
		private String productGroup;
		/**
		 * 销售地区 (编号)
		 */
		@SerializedName("BZIRK")
		private String salesAreaCode;
		/**
		 * 销售部门 (编号)
		 */
		@SerializedName("VKBUR")
		private String salesDepartCode;
		/**
		 * 付款条件代码
		 */
		@SerializedName("ZTERM")
		private String conditionCode;
		/**
		 * 街道
		 */
		@SerializedName("STREET")
		private String address;
		/**
		 * 电话号码
		 */
		@SerializedName("TEL_NUMBER")
		private String tel;
		/**
		 * 联系人名称
		 */
		@SerializedName("PARNR_NAME")
		private String contacter;
		/**
		 * 传真
		 */
		@SerializedName("FAX_NUMBER")
		private String fax;
		/**
		 * 销售组
		 */
		@SerializedName("VKGRP")
		private String salesGroup;
		/**
		 * 交货工厂
		 */
		@SerializedName("VWERK")
		private String factory;
		/**
		 * 库存地点
		 * 虚拟库存标识
		 */
		@SerializedName("LGORT")
		private String vwLocation;
		
		public String getContacter() {
			return contacter;
		}
		public void setContacter(String contacter) {
			this.contacter = contacter;
		}
		public String getFactory() {
			return factory;
		}
		public void setFactory(String factory) {
			this.factory = factory;
		}
		public String getVwLocation() {
			return vwLocation;
		}
		public void setVwLocation(String vwLocation) {
			this.vwLocation = vwLocation;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAbbrName() {
			return abbrName;
		}
		public void setAbbrName(String abbrName) {
			this.abbrName = abbrName;
		}
		public String getAddedTaxNo() {
			return addedTaxNo;
		}
		public void setAddedTaxNo(String addedTaxNo) {
			this.addedTaxNo = addedTaxNo;
		}
//		public String getTaxNo5() {
//			return taxNo5;
//		}
//		public void setTaxNo5(String taxNo5) {
//			this.taxNo5 = taxNo5;
//		}
		public String getCompanyCode() {
			return companyCode;
		}
		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}
		public String getAccountGroup() {
			return accountGroup;
		}
		public void setAccountGroup(String accountGroup) {
			this.accountGroup = accountGroup;
		}
		public String getReconciliationAccounts() {
			return reconciliationAccounts;
		}
		public void setReconciliationAccounts(String reconciliationAccounts) {
			this.reconciliationAccounts = reconciliationAccounts;
		}
		public String getSalesOrg() {
			return salesOrg;
		}
		public void setSalesOrg(String salesOrg) {
			this.salesOrg = salesOrg;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
		public String getProductGroup() {
			return productGroup;
		}
		public void setProductGroup(String productGroup) {
			this.productGroup = productGroup;
		}
		public String getSalesAreaCode() {
			return salesAreaCode;
		}
		public void setSalesAreaCode(String salesAreaCode) {
			this.salesAreaCode = salesAreaCode;
		}
		public String getSalesDepartCode() {
			return salesDepartCode;
		}
		public void setSalesDepartCode(String salesDepartCode) {
			this.salesDepartCode = salesDepartCode;
		}
		public String getConditionCode() {
			return conditionCode;
		}
		public void setConditionCode(String conditionCode) {
			this.conditionCode = conditionCode;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getFax() {
			return fax;
		}
		public void setFax(String fax) {
			this.fax = fax;
		}
		public String getSalesGroup() {
			return salesGroup;
		}
		public void setSalesGroup(String salesGroup) {
			this.salesGroup = salesGroup;
		}
		public String getName2() {
			return name2;
		}
		public void setName2(String name2) {
			this.name2 = name2;
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
		return customer.getId();
	}
	
}
