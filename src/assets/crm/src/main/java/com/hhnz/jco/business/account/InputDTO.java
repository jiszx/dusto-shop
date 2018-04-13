package com.hhnz.jco.business.account;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.SerializedName;

/**
 * 上账及账户调整接口输入参数
 * @author: chaoyang.ren
 * @date:2016年9月21日
 * @time:上午11:47:31
 * @email:chaoyang.ren@foxmail.com
 */
public class InputDTO {
	@SerializedName("IN_BKPF")
	private Header header;
	
	@SerializedName("IN_BSEG")
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

	/**
	 * <h1>账户调整接口行项目信息</h1>
	 * <h3>ex:</h3>
	 * <table>
	 * <th>行项目号</th><th>记账码</th><th>科目</th><th>金额</th><th>文本</th><th>客户</th><th>名称（配送商模式下不要票的零售商必须）</th><th>城市</th>
	 * <tbody>
	 * <tr><td>1</td><td>40</td><td>1002001000</td><td>200</td><td>日期+XX配送商+“代收货款”/日期+“收”+客户名称+“货款”	</td><td></td><td></td><td></td></tr>
	 * <tr><td>2</td><td>11</td><td></td><td>300</td><td>日期+“收”+零售商名称+“货款”/日期+“收”+客户名称+“货款”	</td><td>A001</td><td>123张三</td><td>成都</td></tr>
	 * </tbody>
	 * </table>
	 * @author: chaoyang.ren
	 * @date:2016年8月19日
	 * @time:上午10:18:22
	 * @email:chaoyang.ren@foxmail.com
	 */
	public class Item{
		/**
		 * 行项目号
		 * default 10
		 * required
		 */
		@SerializedName("BUZEI")
		private String itemNo;
		/**
		 * <h1>记账码</h1>
		 * 现金：
		 * 40和11<br/>
		 * 调账：<br/>
		 * 01-客户可用资金余额减少 <br/>
		 * 11-客户可用资金余额增加 <br/>
		 * 票据：
		 * 09和11<br/>
		 * required
		 */
		@SerializedName("BSCHL")
		private String accountCode;
		/**
		 * 科目
		 * <p>
		 * 只在现金科目对应的行项目上才必须</br>
		 * 默认：'100'
		 * </p>
		 * required
		 */
		@SerializedName("NEWKO")
		private String subject;
		/**
		 * 特别总账标识
		 */
		@SerializedName("UMSKZ")
		private String flag;
		/**
		 * 事务类型
		 * 未知信息
		 */
		@SerializedName("NEWBW")
		private String type;
		/**
		 * 金额
		 * required
		 */
		@SerializedName("WRBTR")
		private String amt;
		/**
		 * 金额
		 * RMB币种时，amt与foreignAmt相同
		 */
		@SerializedName("DMBTR")
		private String foreignAmt;
		/**
		 * 文本
		 * 日期+XX配送商+“代收货款”/日期+“收”+客户名称+“货款”
		 * 日期+“收”+零售商名称+“货款”/日期+“收”+客户名称+“货款”
		 */
		@SerializedName("SGTXT")
		private String text;
		/**
		 * 供应商
		 */
		@SerializedName("LIFNR")
		private String supplier;
		/**
		 * 客户<br/>
		 * 现金：
		 * 记账码为11时，必需<br/>
		 * 调账：
		 * 记账码为01，11时必需<br/>
		 * 票据：
		 * 记账码为11时，必需<br/>
		 * required
		 */
		@SerializedName("KUNNR")
		private String customerId;
		/**
		 * 成本中心
		 */
		@SerializedName("KOSTL")
		private String costCenter;
		/**
		 * 内部订单
		 */
		@SerializedName("AUFNR")
		private String internalOrder;
		/**
		 * 参考码1
		 */
		@SerializedName("XREF1")
		private String referenceCode1;
		/**
		 * 参考码2
		 */
		@SerializedName("XREF2")
		private String referenceCode2;
		/**
		 * 固定资产
		 */
		@SerializedName("ANLN1")
		private String permanentAssets;
		/**
		 * 到票日期（票据必须）
		 * pattern: yyyyMMdd
		 */
		@SerializedName("ZFBDT")
		private String billReceivedDate;
		/**
		 * 业务范围（CRM不需要）
		 */
		@SerializedName("GSBER")
		private String businessScope;
		/**
		 * 原因代码
		 * <p>
		 * 只在现金科目对应的行项目且记账码为40时，必需</br>
		 * 默认：
		 * 保证金：'112'
		 * 贷款：'100'
		 * </p>
		 * required
		 */
		@SerializedName("RSTGR")
		private String resonCode;
		/**
		 * 名称：
		 * 配送商模式的不要票类的零售客户必输，
		 * 建议值为零售商编码+名称<br/>
		 * 调账和现金时如果客户为Y000-Y999，必需
		 */
		@SerializedName("NAME1_GP")
		private String name;
		/**
		 * 城市：
		 * 配送商模式的不要票类的零售客户必输，
		 * 建议值为零售商编码+名称<br/>
		 * 调账和现金时如果客户为Y000-Y999，必需
		 */
		@SerializedName("ORT01_GP")
		private String city;
		/**
		 * 票号（票据必须）
		 */
		@SerializedName("BILL_CRM")
		private String billNo;
		/**
		 * 出票日期（票据必须）
		 */
		@SerializedName("WDATE")
		private String billDate;
		/**
		 * 出票银行（票据必须）
		 */
		@SerializedName("WBANK")
		private String billBank;
		/**
		 * 出票人（票据必须）
		 */
		@SerializedName("WBZOG")
		private String billPerson;
		/**
		 * 收票人（票据必须）
		 */
		@SerializedName("WNAME")
		private String receivePerson;
		public String getItemNo() {
			return itemNo;
		}
		public void setItemNo(String itemNo) {
			this.itemNo = itemNo;
		}
		public String getAccountCode() {
			return accountCode;
		}
		public void setAccountCode(String accountCode) {
			this.accountCode = accountCode;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getAmt() {
			return amt;
		}
		public void setAmt(String amt) {
			this.amt = amt;
		}
		public String getForeignAmt() {
			return foreignAmt;
		}
		public void setForeignAmt(String foreignAmt) {
			this.foreignAmt = foreignAmt;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getSupplier() {
			return supplier;
		}
		public void setSupplier(String supplier) {
			this.supplier = supplier;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public String getCostCenter() {
			return costCenter;
		}
		public void setCostCenter(String costCenter) {
			this.costCenter = costCenter;
		}
		public String getInternalOrder() {
			return internalOrder;
		}
		public void setInternalOrder(String internalOrder) {
			this.internalOrder = internalOrder;
		}
		public String getReferenceCode1() {
			return referenceCode1;
		}
		public void setReferenceCode1(String referenceCode1) {
			this.referenceCode1 = referenceCode1;
		}
		public String getReferenceCode2() {
			return referenceCode2;
		}
		public void setReferenceCode2(String referenceCode2) {
			this.referenceCode2 = referenceCode2;
		}
		public String getPermanentAssets() {
			return permanentAssets;
		}
		public void setPermanentAssets(String permanentAssets) {
			this.permanentAssets = permanentAssets;
		}
		public String getBillReceivedDate() {
			return billReceivedDate;
		}
		public void setBillReceivedDate(String billReceivedDate) {
			this.billReceivedDate = billReceivedDate;
		}
		public String getBusinessScope() {
			return businessScope;
		}
		public void setBusinessScope(String businessScope) {
			this.businessScope = businessScope;
		}
		public String getResonCode() {
			return resonCode;
		}
		public void setResonCode(String resonCode) {
			this.resonCode = resonCode;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getBillNo() {
			return billNo;
		}
		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}
		public String getBillDate() {
			return billDate;
		}
		public void setBillDate(String billDate) {
			this.billDate = billDate;
		}
		public String getBillBank() {
			return billBank;
		}
		public void setBillBank(String billBank) {
			this.billBank = billBank;
		}
		public String getBillPerson() {
			return billPerson;
		}
		public void setBillPerson(String billPerson) {
			this.billPerson = billPerson;
		}
		public String getReceivePerson() {
			return receivePerson;
		}
		public void setReceivePerson(String receivePerson) {
			this.receivePerson = receivePerson;
		}
	}

	/**
	 * 传入参数主信息
	 * @author: chaoyang.ren
	 * @date:2016年9月21日
	 * @time:上午11:49:18
	 * @email:chaoyang.ren@foxmail.com
	 */
	public class Header{
		/**
		 * 收款类型（现金D/票据B/调账）
		 * required
		 */
		@SerializedName("OPERATION")
		private String moneyType;
		/**
		 * CRM凭证编号
		 * required
		 */
		@SerializedName("BELNR_CRM")
		private String crmRelatedId;
		/**
		 * 公司代码
		 * required
		 */
		@SerializedName("BUKRS")
		private String companyCode;
		/**
		 * 凭证日期（收款日期）
		 * required
		 */
		@SerializedName("BLDAT")
		@DateTimeFormat(pattern="yyyyMMdd")
		private String receiveDate;
		/**
		 * 过账日期（填写过账日期）
		 * required
		 */
		@SerializedName("BUDAT")
		@DateTimeFormat(pattern="yyyyMMdd")
		private String accountDate;
		/**
		 * 操作人
		 * required
		 */
		@SerializedName("XREF1_HD")
		private String operationUser;
		/**
		 * 抬头文本
		 * required
		 */
		@SerializedName("BKTXT")
		private String text;
		/**
		 * 货币类型
		 * 非必须
		 */
		@SerializedName("WAERS")
		@Length(max=3)
		private String currency;
		/**
		 * 凭证类型
		 * default 'SA'
		 * 非必须
		 */
		@SerializedName("BLART")
		private String voucherType = "SA";
		/**
		 * 参照 
		 * default null;
		 * 非必须
		 */
		@SerializedName("XBLNR")
		private String referTo;
		public String getMoneyType() {
			return moneyType;
		}
		public void setMoneyType(String moneyType) {
			this.moneyType = moneyType;
		}
		public String getCrmRelatedId() {
			return crmRelatedId;
		}
		public void setCrmRelatedId(String crmRelatedId) {
			this.crmRelatedId = crmRelatedId;
		}
		public String getCompanyCode() {
			return companyCode;
		}
		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}
		public String getOperationUser() {
			return operationUser;
		}
		public void setOperationUser(String operationUser) {
			this.operationUser = operationUser;
		}
		public String getReceiveDate() {
			return receiveDate;
		}
		public void setReceiveDate(String receiveDate) {
			this.receiveDate = receiveDate;
		}
		public String getAccountDate() {
			return accountDate;
		}
		public void setAccountDate(String accountDate) {
			this.accountDate = accountDate;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getVoucherType() {
			return voucherType;
		}
		public void setVoucherType(String voucherType) {
			this.voucherType = voucherType;
		}
		public String getReferTo() {
			return referTo;
		}
		public void setReferTo(String referTo) {
			this.referTo = referTo;
		}
		
	}
	
}
