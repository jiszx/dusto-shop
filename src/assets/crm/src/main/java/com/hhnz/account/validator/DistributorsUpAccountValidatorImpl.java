package com.hhnz.account.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hhnz.account.model.CDistributorsUpaccount;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;

@Component
public class DistributorsUpAccountValidatorImpl implements
		DistributorsUpAccountValidator {
	private final String VALID = "validated";
	
	@Resource
	private CMerchCustBaseMapper  custmapper;
	@Override
	public String validateAdd(CDistributorsUpaccount distributor) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(this.validateMerchCustId(distributor));
		sb.append(this.validateRetailer(distributor));
		if (sb.length() < 2) {
			return VALID;
		} else {
			return sb.toString();
		}
	}
	private Object validateRetailer(CDistributorsUpaccount distributor) {
		// TODO Auto-generated method stub
		return null;
	}
	private Object validateMerchCustId(CDistributorsUpaccount distributor) {
		if(distributor.getMerchCustId() ==null || StringUtils.isEmpty(distributor.getMerchCustId())){
			return "配送商客户编号不能为空";
		}
		return null;
	}

}
