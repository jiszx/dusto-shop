package com.hhnz.jco.business.inventory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.BaseInput;

/**
 * 库存调拨输入参数
 * @author: chaoyang.ren
 * @date:2016年12月13日
 * @time:上午10:28:17
 * @email:chaoyang.ren@foxmail.com
 */
public class InputDTO extends BaseInput{
	@SerializedName("IN_H_ZAPSTO")
	private Header header;
	
	@SerializedName("IN_I_ZAPSTO")
	private List<Item> items;
	
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public class Header{
		/**
		 * 申请编号
		 */
		@SerializedName("ZAPHNUM")
		private String crmRelatedId;
		/**
		 * 会计年度
		 */
		@SerializedName("GJAHR")
		private String fiscalYear;
		/**
		 * 工厂
		 */
		@SerializedName("WERKS")
		private String factory;
		/**
		 * 收货库存地点
		 */
		@SerializedName("UMLGO")
		private String vwNo;
		/**
		 * 配送商
		 */
		@SerializedName("VENDOR")
		private String vendor;
		/**
		 * 电话
		 */
		@SerializedName("TELF1")
		private String tel;
		/**
		 * 地址
		 */
		@SerializedName("STRAS")
		private String address;
		/**
		 * 联系人
		 */
		@SerializedName("ERNAM")
		private String contacts;
		/**
		 * 关闭标识
		 */
		@SerializedName("ZCLOSE")
		private String closeFlag;
		public String getVendor() {
			return vendor;
		}
		public void setVendor(String vendor) {
			this.vendor = vendor;
		}
		public String getCrmRelatedId() {
			return crmRelatedId;
		}
		public void setCrmRelatedId(String crmRelatedId) {
			this.crmRelatedId = crmRelatedId;
		}
		public String getFiscalYear() {
			return fiscalYear;
		}
		public void setFiscalYear(String fiscalYear) {
			this.fiscalYear = fiscalYear;
		}
		public String getFactory() {
			return factory;
		}
		public void setFactory(String factory) {
			this.factory = factory;
		}
		public String getVwNo() {
			return vwNo;
		}
		public void setVwNo(String vwNo) {
			this.vwNo = vwNo;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getContacts() {
			return contacts;
		}
		public void setContacts(String contacts) {
			this.contacts = contacts;
		}
		public String getCloseFlag() {
			return closeFlag;
		}
		public void setCloseFlag(String closeFlag) {
			this.closeFlag = closeFlag;
		}
	}
	
	public class Item{
		/**
		 * 项目
		 */
		@SerializedName("ZAPLNUM")
		private String itemNo;
		/**
		 * 发货库存地
		 */
		@SerializedName("LGORT")
		private String vwNo;
		/**
		 * 物料
		 */
		@SerializedName("MATNR")
		private String materialId;
		/**
		 * 数量
		 */
		@SerializedName("MENGE")
		private BigDecimal quantity;
		/**
		 * 单位
		 */
		@SerializedName("MEINS")
		private String unit;
		public String getItemNo() {
			return itemNo;
		}
		public void setItemNo(String itemNo) {
			this.itemNo = itemNo;
		}
		public String getVwNo() {
			return vwNo;
		}
		public void setVwNo(String vwNo) {
			this.vwNo = vwNo;
		}
		public String getMaterialId() {
			return materialId;
		}
		public void setMaterialId(String materialId) {
			this.materialId = materialId;
		}
		public BigDecimal getQuantity() {
			return quantity;
		}
		public void setQuantity(BigDecimal quantity) {
			this.quantity = quantity;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
	}

	@Override
	public Serializable getCrmRelatedId() {
		return header.getCrmRelatedId();
	}
}
