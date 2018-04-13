package com.hhnz.account.validator.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.account.validator.UpAccountValidator;

/**
 * 资金上账验证
 * @author dell
 *
 */
@Component
public class UpAccountValidatorImpl implements UpAccountValidator {
	private final String VALID = "validated";
	@Override
	public String validateAdd(CMerchUpaccount model) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.validatePayType(model.getPayType()));
		//承兑票据
		if(StringUtils.equals("2", model.getPayType())){
			sb.append(this.validateBillNo(model.getBillNo()));
			sb.append(this.validateBillOutDate(model.getBillOutDate()));
			sb.append(this.validateBillInDate(model.getBillInDate()));
			sb.append(this.validateBillBank(model.getBillBank()));
			sb.append(this.validateBillOutName(model.getBillOutName()));
			sb.append(this.validateBillInName(model.getBillInName()));
		}else{
			sb.append(this.validateBankAccount(model.getBankAccount()));
//			sb.append(this.validatePayBankNo(model.getPayBankNo()));
//			sb.append(this.validatePayName(model.getPayName()));
		}
		if (sb.length() < 2) {
			return VALID;
		} else {
			return sb.toString();
		}
	}
	
	@Override
	public String validateEdit(CMerchUpaccount model) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(this.validatePayType(model.getPayType()));
		//承兑票据
		if(StringUtils.equals("2", model.getPayType())){
			sb.append(this.validateBillNo(model.getBillNo()));
			sb.append(this.validateBillOutDate(model.getBillOutDate()));
			sb.append(this.validateBillInDate(model.getBillInDate()));
			sb.append(this.validateBillBank(model.getBillBank()));
			sb.append(this.validateBillOutName(model.getBillOutName()));
			sb.append(this.validateBillInName(model.getBillInName()));
		}else{
			sb.append(this.validateBankAccount(model.getBankAccount()));
//			sb.append(this.validatePayBankNo(model.getPayBankNo()));
//			sb.append(this.validatePayName(model.getPayName()));
		}
		if (sb.length() < 2) {
			return VALID;
		} else {
			return sb.toString();
		}
	}
	/**
	 * 打款人
	 * @param payName
	 * @return
	 */
	private Object validatePayName(String payName) {
		if(StringUtils.isEmpty(payName)){
			return "打款人为空";
		}
		return "";
	}
	
	/**
	 * 打款卡号
	 * @param payBankNo
	 * @return
	 */
	private Object validatePayBankNo(String payBankNo) {
		if(StringUtils.isEmpty(payBankNo)){
			return "打款卡号为空";
		}
		return "";
	}

	/**
	 * 收款账户验证
	 * @param bankAccount
	 * @return
	 */
	private Object validateBankAccount(String bankAccount) {
		if(StringUtils.isEmpty(bankAccount)){
			return "收款类型为空";
		}
		return "";
	}
	/**
	 * 收票人验证
	 * @param billInName
	 * @return
	 */
	private Object validateBillInName(String billInName) {
		if(StringUtils.isEmpty(billInName)){
			return "收票人为空";
		}
		return "";	}
	
	/**
	 * 出票人验证
	 * @param billOutName
	 * @return
	 */
	private Object validateBillOutName(String billOutName) {
		if(StringUtils.isEmpty(billOutName)){
			return "出票人为空";
		}
		return "";
	}
	/**
	 * 出票银行验证
	 * @param billBank
	 * @return
	 */
	private Object validateBillBank(String billBank) {
		if(StringUtils.isEmpty(billBank)){
			return "出票银行为空";
		}
		return "";
	}
	/**
	 * 到票时间验证
	 * @param billInDate
	 * @return
	 */
	private Object validateBillInDate(Date billInDate) {
		if(billInDate==null){
			return "到票时间为空";
		}
		return "";
	}
	
	/**
	 * 出票时间验证
	 * @param billOutDate
	 * @return
	 */
	private Object validateBillOutDate(Date billOutDate) {
		if(billOutDate==null){
			return "出票时间为空";
		}
		return "";
	}
	/**
	 * 承兑票据，票号验证
	 * @param billNo
	 * @return
	 */
	private Object validateBillNo(String billNo) {
		if(StringUtils.isEmpty(billNo)){
			return "承兑票据，票号为空";
		}
		return "";
	}
	private String validatePayType(String payType) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(payType)){
			return "资金类型不能为空";
		}
		if(!StringUtils.equals("1", payType) && !StringUtils.equals("2", payType)){
			return "资金类型错误，请选择正确的资金类型";
		}
		return "";
	}

	

}
