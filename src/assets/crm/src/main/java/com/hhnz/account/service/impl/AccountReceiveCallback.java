package com.hhnz.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchReceiveVerifieMapper;
import com.hhnz.account.model.CMerchReceiveVerifie;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;

@Component("AccountReceiveCallback")
@Transactional
public class AccountReceiveCallback implements RFCCallback{
	@Resource
	private CMerchReceiveVerifieMapper  mapper;
	
	@Override
	public void errorCallBack(CallbackParam result) {
		CMerchReceiveVerifie  verifie = this.mapper.selectByPrimaryKey(result.getId());
		verifie.setStates("2");
		verifie.setMsg(result.getResult().getMESSAGE());
		this.mapper.updateByPrimaryKeySelective(verifie);
	}

	@Override
	public void successCallBack(CallbackParam result) {
		CMerchReceiveVerifie  verifie = this.mapper.selectByPrimaryKey(result.getId());
		verifie.setStates("1");
		verifie.setMsg("");
		this.mapper.updateByPrimaryKeySelective(verifie);
	}
}
