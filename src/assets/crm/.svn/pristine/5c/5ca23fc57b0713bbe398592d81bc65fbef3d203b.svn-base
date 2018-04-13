package com.hhnz.jco.business.order;

import java.io.Serializable;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.BaseInput;

/**
 * 订单处理输入参数
 * @author: chaoyang.ren
 * @date:2016年8月24日
 * @time:上午9:50:31
 * @email:chaoyang.ren@foxmail.com
 */
public class InputDTO extends BaseInput{
	@SerializedName("IN_VBAK")
	private OrderHeader orderHeader;
	
	@SerializedName("IN_VBAP")
	private List<Item> items;
	
	@SerializedName("IN_KONP")
	private List<ItemPriceCondition> itemConditions;
	
	/**
	 * 暂不起用
	 */
	@SerializedName("IN_TEXT")
	private List<OrderText> text;
	
	public class OrderHeader{
		/**
		 * 操作类型
		 * required
		 */
		@SerializedName("OPERATION")
		private String operationType;
		/**
		 * 销售凭证
		 * CRM提供的销售订单编号(必填，唯一编号)
		 * required
		 */
		@SerializedName("VBELN_CRM")
		private String salesDocumentCRM;
		/**
		 * 销售凭证 
		 * SAP订单号(修改时可用)
		 * required
		 */
		@SerializedName("VBELN_SAP")
		private String salesDocumentSAP;
		/**
		 * 销售凭证类型
		 * required
		 */
		@SerializedName("AUART")
		private String salesDocumentType;
		/**
		 * 销售组织
		 * required
		 */
		@SerializedName("VKORG")
		private String salesOrg;
		/**
		 * 分销渠道
		 * required
		 */
		@SerializedName("VTWEG")
		private String channel;
		/**
		 * 产品组
		 * required
		 */
		@SerializedName("SPART")
		private String productGroup;
		/**
		 * 售达方 
		 * required
		 */
		@SerializedName("KUNAG")
		private String saleTo;
		/**
		 * 售达方名称 ,售达方为Y004时必须
		 * required
		 */
		@SerializedName("NAME1")
		private String saleToName;
		/**
		 * 送达方
		 * required
		 */
		@SerializedName("KUNWE")
		private String sendTo;
		/**
		 * 送达方名称,送达方为Y004时必须
		 * required
		 */
		@SerializedName("NAME2")
		private String sendToName;
		/**
		 * 定价时间
		 */
		@SerializedName("PRSDT")
		@DateTimeFormat(pattern="yyyyMMdd")
		private String priceDate;
		/**
		 * 卸货点
		 * required
		 */
		@SerializedName("ABLAD")
		private String unloadingPosition;
		/**
		 * 装运类型
		 * required
		 */
		@SerializedName("VSART")
		private String shippingType;
		/**
		 * 销售部门
		 */
		@SerializedName("VKBUR")
		private String salesDep;
		/**
		 * 销售组
		 */
		@SerializedName("VKGRP")
		private String salesGroup;
		/**
		 * 销售地区  
		 */
		@SerializedName("BZIRK")
		private String salesArea;
		/**
		 * 开票方名称
		 */
		@SerializedName("KP_NAME")
		private String drawerName;
		/**
		 * 价格组(客户)
		 */
		@SerializedName("KONDA")
		private String priceGroup;
		/**
		 * 订购原因( 业务原因 )
		 */
		@SerializedName("AUGRU")
		private String busiReason;
		/**
		 * 你的参考(销售岗位ID)
		 */
		@SerializedName("IHREZ")
		private String positionId;
		/**
		 * 抬头文本001
		 */
		@SerializedName("TEXT01")
		private String text1;
		/**
		 * 抬头文本002
		 */
		@SerializedName("TEXT02")
		private String text2;
		/**
		 * 抬头文本003
		 * 仓储服务商名称
		 */
		@SerializedName("TEXT03")
		private String text3;
		/**
		 * 区别打印
		 */
		@SerializedName("TEXT04")
		private String printFlag;
		/**
		 * 联系人
		 */
		@SerializedName("NAME_CO")
		private String contact;
		/**
		 * 联系电话
		 */
		@SerializedName("TEL_NUMBER")
		private String contactTel;
		
		/**
		 * 付款条件	说明<br/>
			ZN01	货到付款（无账期、需收到发票）<br/>
			ZN02	购销15天/货到15天/货到15天（需收到发票）<br/>
			ZN03	仓储服务商代收代结<br/>
			ZN04	货到30天/月结30天<br/>
			ZN05	货到45天/票到30天<br/>
			ZN06	票到45天/月结30天（仓储商代收代结）/月结45天<br/>
			ZN07	票到60天/月结60天<br/>
		 * NKA订单时使用字段，当前暂未使用
		 */
		@SerializedName("ZTERM")
		private String payCondition;
		
		public String getPayCondition() {
			return payCondition;
		}
		public void setPayCondition(String payCondition) {
			this.payCondition = payCondition;
		}
		public String getPrintFlag() {
			return printFlag;
		}
		public void setPrintFlag(String printFlag) {
			this.printFlag = printFlag;
		}
		public String getSalesGroup() {
			return salesGroup;
		}
		public void setSalesGroup(String salesGroup) {
			this.salesGroup = salesGroup;
		}
		public String getDrawerName() {
			return drawerName;
		}
		public void setDrawerName(String drawerName) {
			this.drawerName = drawerName;
		}
		public String getText1() {
			return text1;
		}
		public void setText1(String text1) {
			this.text1 = text1;
		}
		public String getText2() {
			return text2;
		}
		public void setText2(String text2) {
			this.text2 = text2;
		}
		public String getText3() {
			return text3;
		}
		public void setText3(String text3) {
			this.text3 = text3;
		}
		public String getProductGroup() {
			return productGroup;
		}
		public void setProductGroup(String productGroup) {
			this.productGroup = productGroup;
		}
		public String getOperationType() {
			return operationType;
		}
		public void setOperationType(String operationType) {
			this.operationType = operationType;
		}
		public String getSalesDocumentCRM() {
			return salesDocumentCRM;
		}
		public void setSalesDocumentCRM(String salesDocumentCRM) {
			this.salesDocumentCRM = salesDocumentCRM;
		}
		public String getSalesDocumentSAP() {
			return salesDocumentSAP;
		}
		public void setSalesDocumentSAP(String salesDocumentSAP) {
			this.salesDocumentSAP = salesDocumentSAP;
		}
		public String getSalesDocumentType() {
			return salesDocumentType;
		}
		public void setSalesDocumentType(String salesDocumentType) {
			this.salesDocumentType = salesDocumentType;
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
		public String getSaleTo() {
			return saleTo;
		}
		public void setSaleTo(String saleTo) {
			this.saleTo = saleTo;
		}
		public String getSendTo() {
			return sendTo;
		}
		public void setSendTo(String sendTo) {
			this.sendTo = sendTo;
		}
		public String getPriceDate() {
			return priceDate;
		}
		public void setPriceDate(String priceDate) {
			this.priceDate = priceDate;
		}
		public String getUnloadingPosition() {
			return unloadingPosition;
		}
		public void setUnloadingPosition(String unloadingPosition) {
			this.unloadingPosition = unloadingPosition;
		}
		public String getShippingType() {
			return shippingType;
		}
		public void setShippingType(String shippingType) {
			this.shippingType = shippingType;
		}
		public String getSalesDep() {
			return salesDep;
		}
		public void setSalesDep(String salesDep) {
			this.salesDep = salesDep;
		}
		public String getSalesArea() {
			return salesArea;
		}
		public void setSalesArea(String salesArea) {
			this.salesArea = salesArea;
		}
		public String getPriceGroup() {
			return priceGroup;
		}
		public void setPriceGroup(String priceGroup) {
			this.priceGroup = priceGroup;
		}
		public String getBusiReason() {
			return busiReason;
		}
		public void setBusiReason(String busiReason) {
			this.busiReason = busiReason;
		}
		public String getPositionId() {
			return positionId;
		}
		public void setPositionId(String positionId) {
			this.positionId = positionId;
		}
		public String getSaleToName() {
			return saleToName;
		}
		public void setSaleToName(String saleToName) {
			this.saleToName = saleToName;
		}
		public String getSendToName() {
			return sendToName;
		}
		public void setSendToName(String sendToName) {
			this.sendToName = sendToName;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public String getContactTel() {
			return contactTel;
		}
		public void setContactTel(String contactTel) {
			this.contactTel = contactTel;
		}
	}
	
	public class Item{
		/**
		 * 销售和分销凭证的项目号
		 * required
		 */
		@SerializedName("POSNR")
		private String salesItemNo;
		/**
		 * 物料号
		 * required
		 */
		@SerializedName("MATNR")
		private String materialNo;
		/**
		 * 以销售单位表示的累计订单数量
		 * required
		 */
		@SerializedName("KWMENG")
		private String orderNum;
		/**
		 * 销售单位
		 * required
		 */
		@SerializedName("VRKME")
		private String orderUnit;
		/**
		 * 工厂
		 * required
		 */
		@SerializedName("WERKS")
		private String factory;
		/**
		 * 装运点/接收点
		 * required
		 */
		@SerializedName("VSTEL")
		private String shippingPosition;
		/**
		 * 库存地点
		 * required
		 */
		@SerializedName("LGORT")
		private String vwLocation;
		/**
		 * 赠品标记
		 * 正常商品：ZTAN
		 * 退货正常商品：REN
		 * 赠品：ZTN1
		 * 退货赠品：ZRN1
		 */
		@SerializedName("PSTYV")
		private String giftFlag;
		/**
		 * 行项目备注
		 */
		@SerializedName("TEXT")
		private String memo;
		
		
		public String getGiftFlag() {
			return giftFlag;
		}
		public void setGiftFlag(String giftFlag) {
			this.giftFlag = giftFlag;
		}
		public String getVwLocation() {
			return vwLocation;
		}
		public void setVwLocation(String vwLocation) {
			this.vwLocation = vwLocation;
		}
		public String getSalesItemNo() {
			return salesItemNo;
		}
		public void setSalesItemNo(String salesItemNo) {
			this.salesItemNo = salesItemNo;
		}
		public String getMaterialNo() {
			return materialNo;
		}
		public void setMaterialNo(String materialNo) {
			this.materialNo = materialNo;
		}
		public String getOrderNum() {
			return orderNum;
		}
		public void setOrderNum(String orderNum) {
			this.orderNum = orderNum;
		}
		public String getOrderUnit() {
			return orderUnit;
		}
		public void setOrderUnit(String orderUnit) {
			this.orderUnit = orderUnit;
		}
		public String getFactory() {
			return factory;
		}
		public void setFactory(String factory) {
			this.factory = factory;
		}
		public String getShippingPosition() {
			return shippingPosition;
		}
		public void setShippingPosition(String shippingPosition) {
			this.shippingPosition = shippingPosition;
		}
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
		}
	}
	
	public class ItemPriceCondition{
		/**
		 * 销售和分销凭证的项目号
		 * required
		 */
		@SerializedName("POSNR")
		private String salesItemNo;
		/**
		 * 条件类型
		 * required
		 */
		@SerializedName("KSCHL")
		private String conditionType;
		/**
		 * 价格( 条件金额或百分数 )
		 * required
		 */
		@SerializedName("KBETR")
		private String price;
		/**
		 * 货币码
		 * required
		 */
		@SerializedName("KOEIN")
		private String currency;
		/**
		 * 条件单位 
		 * required
		 */
		@SerializedName("KMEIN")
		private String unit;
		/**
		 * 条件定价单位
		 * required
		 */
		@SerializedName("KPEIN")
		private String priceUnit;
		
		public String getSalesItemNo() {
			return salesItemNo;
		}
		public void setSalesItemNo(String salesItemNo) {
			this.salesItemNo = salesItemNo;
		}
		public String getConditionType() {
			return conditionType;
		}
		public void setConditionType(String conditionType) {
			this.conditionType = conditionType;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public String getPriceUnit() {
			return priceUnit;
		}
		public void setPriceUnit(String priceUnit) {
			this.priceUnit = priceUnit;
		}
	}
	
	public class OrderText{
		/**
		 * 销售和分销凭证的项目号
		 */
		@SerializedName("POSNR")
		private String salesItemNo;
		/**
		 * Text ID
		 */
		@SerializedName("TEXT_ID")
		private String textId;
		/**
		 * 文本行 
		 */
		@SerializedName("TEXT_LINE")
		private String textLine;
		
		public String getSalesItemNo() {
			return salesItemNo;
		}
		public void setSalesItemNo(String salesItemNo) {
			this.salesItemNo = salesItemNo;
		}
		public String getTextId() {
			return textId;
		}
		public void setTextId(String textId) {
			this.textId = textId;
		}
		public String getTextLine() {
			return textLine;
		}
		public void setTextLine(String textLine) {
			this.textLine = textLine;
		}
	}

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<ItemPriceCondition> getItemConditions() {
		return itemConditions;
	}

	public void setItemConditions(List<ItemPriceCondition> itemConditions) {
		this.itemConditions = itemConditions;
	}

	public List<OrderText> getText() {
		return text;
	}

	public void setText(List<OrderText> text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see com.hhnz.jco.business.base.BaseInput#getCrmRelatedId()
	 */
	@Override
	public Serializable getCrmRelatedId() {
		return orderHeader.getSalesDocumentCRM();
	}
	
}
