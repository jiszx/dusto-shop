package com.hhnz.account.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAdjustMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAdjust;
import com.hhnz.account.service.IAdjustAccountService;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;

/**
 * 保证金上账Callback
 * @author hhnz-skevin 2016-12-13
 *
 */
@Component("MarginsAccountCallback")
@Transactional
public class MarginsAccountCallback implements RFCCallback{
	@Resource
	private CMerchCustAdjustMapper custAdjustMapper;
	@Resource
    private CMerchCustAccountLogMapper accountLogMapper;
    @Resource
    private IAdjustAccountService adjustAccountService;
    
	@Override
	public void errorCallBack(CallbackParam result) {
		CMerchCustAccountLog cal = accountLogMapper.selectByPrimaryKey(result.getId());
		CMerchCustAdjust adjust = this.custAdjustMapper.selectByPrimaryKey(cal.getOrderId());
		adjust.setAttribute2(result.getResult().getMESSAGE());
		adjust.setStates("5");
		custAdjustMapper.updateByPrimaryKey(adjust);
		cal.setStates("E");
        accountLogMapper.updateByPrimaryKeySelective(cal);
		
	}

	@Override
	public void successCallBack(CallbackParam result) {
		CMerchCustAccountLog cal = accountLogMapper.selectByPrimaryKey(result.getId());
		CMerchCustAdjust adjust = custAdjustMapper.selectByPrimaryKey(cal.getOrderId());
		adjust.setStates("3");
		adjust.setAttribute2("");
		CMerchCustAccount account = adjustAccountService.getAccount(adjust);
		BigDecimal amt = adjust.getAmt();
		//保证金添加
		adjustAccountService.adjustAccountAmt(account, adjust.getAccountType(), amt);
		adjustAccountService.changeBalanceAmt(adjust.getAccountType(), adjust.getMerchCustId(), adjust.getOrganizationId(), amt);
		//可配货金额添加(可用授信),授信额度
		adjustAccountService.changeBalanceAmt("3", adjust.getMerchCustId(), adjust.getOrganizationId(), amt);
	    custAdjustMapper.updateByPrimaryKeySelective(adjust);
	    cal.setStates("S");
        accountLogMapper.updateByPrimaryKeySelective(cal);
        //添加授信的日志表数据
        cal.setId(null);
        cal.setAccountType("3");
        cal.setAttribute5("保证金调整");
        accountLogMapper.insert(cal);
	}

}
