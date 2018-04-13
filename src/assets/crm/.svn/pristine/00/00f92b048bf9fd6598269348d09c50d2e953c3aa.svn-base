package com.hhnz.account.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAdjustMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAdjust;
import com.hhnz.account.service.IAdjustAccountService;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;

/**
 * @author: chaoyang.ren
 * @date:2016年11月16日
 * @time:上午11:31:42
 * @email:chaoyang.ren@foxmail.com
 */
@Component("AdjustAccountCallback")
@Transactional
public class AdjustAccountCallback implements RFCCallback{
	@Resource
	private CMerchCustAdjustMapper custAdjustMapper;
	@Resource
    private CMerchCustAccountLogMapper accountLogMapper;
    @Resource
    private IAdjustAccountService adjustAccountService;
	
    @Autowired
    private CMerchCustBaseMapper baseMapper;
	@Override
	public void errorCallBack(CallbackParam result) {
		CMerchCustAccountLog cal = accountLogMapper.selectByPrimaryKey(result.getId());
		CMerchCustAdjust adjust = this.custAdjustMapper.selectByPrimaryKey(cal.getOrderId());
		adjust.setAttribute2(result.getResult().getMESSAGE());
		adjust.setStates("5");
		cal.setStates("E");
		custAdjustMapper.updateByPrimaryKey(adjust);
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
		adjustAccountService.adjustAccountAmt(account, adjust.getAccountType(), amt);
		adjustAccountService.changeBalanceAmt(adjust.getAccountType(), adjust.getMerchCustId(), adjust.getOrganizationId(), amt);
		CMerchCustBase base = this.baseMapper.selectByPrimaryKey(adjust.getMerchCustId());
		if("4".equals(adjust.getAccountType()) && "7".equals(base.getCustType())){
			//仓储服务商调整保证金同时需要调整授信额度（可配货金额）
			adjustAccountService.changeBalanceAmt("3", adjust.getMerchCustId(), adjust.getOrganizationId(), amt);
		}
		adjustAccountService.customerToCustomer(adjust);
        custAdjustMapper.updateByPrimaryKeySelective(adjust);
        cal.setStates("S");
        accountLogMapper.updateByPrimaryKeySelective(cal);
    }

}
