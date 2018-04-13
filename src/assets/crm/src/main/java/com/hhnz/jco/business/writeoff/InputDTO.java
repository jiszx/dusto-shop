package com.hhnz.jco.business.writeoff;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.BaseInput;

/**
 * 核销输入信息
 * @author: chaoyang.ren
 * @date:2016年11月18日
 * @time:上午10:38:08
 * @email:chaoyang.ren@foxmail.com
 */
public class InputDTO extends BaseInput{
	@SerializedName("IN_HEADER")
	private Header header;
	
	@SerializedName("IN_ITEM")
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
	 * 核销参数行项目
	 * @author: chaoyang.ren
	 * @date:2016年11月18日
	 * @time:上午10:50:40
	 * @email:chaoyang.ren@foxmail.com
	 */
	public class Item{
		/**
		 * 行号
		 * 规则为1，2，3，4，5
		 * required
		 */
		@SerializedName("BUZEI")
		private String itemNo;
		/**
		 * 清帐凭证类别
		 * @see VoucherType
		 */
		@SerializedName("BTYPE")
		private VoucherType voucherType;
		/**
		 * 清账凭证
		 * SAP发票号或者收款上账凭证（CRM编号）
		 */
		@SerializedName("BELNR")
		private String voucher;
		/**
		 * 清帐凭证的年度
		 * 发票号或者收款凭证的年度（例如2016）
		 */
		@SerializedName("GJAHR")
		private String year;
		/**
		 * 金额
		 * 每一行凭证对应的金额（不带符号）,2位小数
		 */
		@SerializedName("WRBTR")
		private BigDecimal amount;
		public String getItemNo() {
			return itemNo;
		}
		public void setItemNo(String itemNo) {
			this.itemNo = itemNo;
		}
		public VoucherType getVoucherType() {
			return voucherType;
		}
		public void setVoucherType(VoucherType voucherType) {
			this.voucherType = voucherType;
		}
		public String getVoucher() {
			return voucher;
		}
		public void setVoucher(String voucher) {
			this.voucher = voucher;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
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
		 * 核销类型（A是全清/L是剩余清账-有余额，且必需告知余额再哪一行）
		 * required
		 * @see OperationType
		 */
		@SerializedName("OPERATION")
		private OperationType operation;
		/**
		 * CRM核销凭证编号
		 * required
		 */
		@SerializedName("BELNR_CRM")
		private Long crmRelatedId;
		/**
		 * 清账客户
		 * required
		 */
		@SerializedName("KUNNR")
		private String sapCustomerId;
		/**
		 * 公司代码
		 * required
		 */
		@SerializedName("BUKRS")
		private String companyCode;
		/**
		 * 核销日期
		 * required
		 */
		@SerializedName("BUDAT")
		@DateTimeFormat(pattern="yyyyMMdd")
		private String writeOffDate;
		/**
		 * 剩余金额
		 * 余额带符号（正负）：发票行剩余则为发票上的正负金额，收款凭证行的金额剩余，应为负数。
		 * required
		 */
		@SerializedName("DMBTR")
		private String balance;
		/**
		 * 剩余金额行文本
		 * required
		 */
		@SerializedName("SGTXT")
		private String text;
		/**
		 * 剩余金额填入的清账凭证
		 * 如果是发票剩余，则为SAP发票号；如果是收款剩余，则为CRM收款凭证号。
		 */
		@SerializedName("ZUONR")
		private String voucher;
		public OperationType getOperation() {
			return operation;
		}
		public void setOperation(OperationType operation) {
			this.operation = operation;
		}
		public Long getCrmRelatedId() {
			return crmRelatedId;
		}
		public void setCrmRelatedId(Long crmRelatedId) {
			this.crmRelatedId = crmRelatedId;
		}
		public String getSapCustomerId() {
			return sapCustomerId;
		}
		public void setSapCustomerId(String sapCustomerId) {
			this.sapCustomerId = sapCustomerId;
		}
		public String getCompanyCode() {
			return companyCode;
		}
		public void setCompanyCode(String companyCode) {
			this.companyCode = companyCode;
		}
		public String getWriteOffDate() {
			return writeOffDate;
		}
		public void setWriteOffDate(String writeOffDate) {
			this.writeOffDate = writeOffDate;
		}
		public String getBalance() {
			return balance;
		}
		public void setBalance(String balance) {
			this.balance = balance;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getVoucher() {
			return voucher;
		}
		public void setVoucher(String voucher) {
			this.voucher = voucher;
		}
				
	}

	@Override
	public Serializable getCrmRelatedId() {
		return header.getCrmRelatedId();
	}
	
}
