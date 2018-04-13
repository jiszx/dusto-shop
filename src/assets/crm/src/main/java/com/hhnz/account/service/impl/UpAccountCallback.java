package com.hhnz.account.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.AccountUtilMapper;
import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.mapper.CMerchUpaccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.util.BigDecimalASME;

/**
 * @author: chaoyang.ren
 * @date:2016年11月16日
 * @time:上午11:31:42
 * @email:chaoyang.ren@foxmail.com
 */
@Component("UpAccountCallback")
@Transactional
public class UpAccountCallback implements RFCCallback{
	@Resource
    private CMerchCustAccountLogMapper accountLogMapper;
    @Resource
	private CMerchUpaccountMapper upAccountMapper;
    @Resource
    private CMerchCustBalancesMapper balancemapper;
    @Resource
	private CMerchCustAccountMapper accountmapper;
    @Resource
	private AccountUtilMapper accountutilmapper;
	
	@Override
	public void errorCallBack(CallbackParam result) {
		CMerchCustAccountLog accountlog = accountLogMapper.selectByPrimaryKey(result.getId());
		CMerchUpaccount upaccount = upAccountMapper.selectByPrimaryKey(accountlog.getOrderId());
		upaccount.setAttribute2(result.getResult().getMESSAGE());
		this.upAccountMapper.updateByPrimaryKeySelective(upaccount);
		accountlog.setStates("E");
		this.accountLogMapper.updateByPrimaryKeySelective(accountlog);
	}

	@Override
	public void successCallBack(CallbackParam result) {
		CMerchCustAccountLog accountlog = accountLogMapper.selectByPrimaryKey(result.getId());
		CMerchUpaccount upaccount = upAccountMapper.selectByPrimaryKey(accountlog.getOrderId());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String period = sdf.format(new Date());// 当前年月
		// 调整客户资金
		CMerchCustAccount merchaccount = new CMerchCustAccount();
		CMerchCustAccountExample ex = new CMerchCustAccountExample();
		ex.createCriteria().andMerchCustIdEqualTo(accountlog.getMerchCustId());
		ex.createCriteria().andOrganizationIdEqualTo(accountlog.getOrganizationId());
		List<CMerchCustAccount> list = new ArrayList<CMerchCustAccount>();
		list = this.accountmapper.selectByExample(ex);
		merchaccount = list.get(0);
		// 货补和保证金余额不变化
		merchaccount.setSubsidyAmt(BigDecimalASME.multiply(merchaccount.getSubsidyAmt()));
		merchaccount.setBondAmt(BigDecimalASME.multiply(merchaccount.getBondAmt()));
		merchaccount.setCreditAmt(BigDecimalASME.multiply(merchaccount.getCreditAmt()));
		merchaccount.setCashAmt(BigDecimalASME.multiply(merchaccount.getCashAmt()));
		if (merchaccount.getContractCreditAmt().compareTo(
				merchaccount.getCreditAmt()) > 0 ) {
			// 可用授信和授信额度不相等
			BigDecimal amt = merchaccount.getContractCreditAmt().subtract(
					merchaccount.getCreditAmt());
			if (upaccount.getPayAmt().compareTo(amt) > 0) {
				// 上账金额大于已用授信额度--补全授信额度，余额保存到现金账户
				insertAccountLog(period, upaccount, "3", amt,accountlog.getId());
				updateBalance(period, upaccount, "3", amt);
				merchaccount.setCreditAmt(merchaccount.getContractCreditAmt());

				// 剩余金额添加到现金账户中
				insertAccountLog(period, upaccount, "1",upaccount.getPayAmt().subtract(amt),accountlog.getId());
				updateBalance(period, upaccount, "1",upaccount.getPayAmt().subtract(amt));
				merchaccount.setCashAmt(merchaccount.getCashAmt().add(upaccount.getPayAmt().subtract(amt)));
				accountlog.setAttribute1("1");
			} else {
				// 上账金额小于已用授信额度，则补全到授信额度中
				//insertAccountLog(period, upaccount, "3",upaccount.getPayAmt());
				accountlog.setAccountType("3");
				accountlog.setStates("S");
				accountlog.setAttribute5(accountlog.getId().toString());
				updateBalance(period, upaccount, "3",upaccount.getPayAmt());
				merchaccount.setCreditAmt(merchaccount.getCreditAmt().add(upaccount.getPayAmt()));
			}
		} else {
			// 如果可用授信和授信额度相同全额资金添加到现金账户
			//insertAccountLog(period, upaccount, "1",upaccount.getPayAmt());
			accountlog.setAttribute5(accountlog.getId().toString());
			accountlog.setStates("S");
			updateBalance(period, upaccount, "1",upaccount.getPayAmt());
			merchaccount.setCashAmt(merchaccount.getCashAmt().add(upaccount.getPayAmt()));
		}
		this.accountutilmapper.upSapVoucherId(upaccount.getId());
		this.accountmapper.updateByPrimaryKeySelective(merchaccount);
		upaccount.setAttribute2("");
		this.upAccountMapper.updateByPrimaryKeySelective(upaccount);
		this.accountLogMapper.updateByPrimaryKeySelective(accountlog);
	}

	/**
	 * 更新期间表和科目余额表
	 * 
	 * @param period
	 * @param upaccount
	 * @param accountType
	 * @param amt
	 * @return
	 */
	private void updateBalance(String period,
			CMerchUpaccount upaccount, String accountType, BigDecimal amt){
		// Map<String, Object> result = new HashMap<String, Object>();
		// 获取客户的期间资金表
		CMerchCustBalancesExample balanceex = new CMerchCustBalancesExample();
		CMerchCustBalancesExample.Criteria bext = balanceex.createCriteria();
		bext.andMerchCustIdEqualTo(upaccount.getMerchCusId());
		bext.andOrganizationIdEqualTo(upaccount.getOrganizationId());
		bext.andPeriodEqualTo(period);
		bext.andAccountTypeEqualTo(accountType);// 现金账户
		List<CMerchCustBalances> custbalance = this.balancemapper
				.selectByExample(balanceex);
		if (custbalance != null && custbalance.size() == 1) {
			CMerchCustBalances balance = custbalance.get(0);
			balance.setdAmt(balance.getdAmt().add(amt));
			this.balancemapper.updateByPrimaryKeySelective(balance);
		}
	}
	
	private CMerchCustAccountLog insertAccountLog(String period,
			CMerchUpaccount upaccount, String accountType, BigDecimal amt,Long logid) {
		// 添加到客户资金日志表
		CMerchCustAccountLog accountlog = new CMerchCustAccountLog();
		accountlog.setAccountType(accountType);
		accountlog.setdAmt(amt);
		accountlog.setMerchCustId(upaccount.getMerchCusId());
		accountlog.setOrganizationId(upaccount.getOrganizationId());
		accountlog.setType("1");
		// accountlog.setAttribute1("0");
		accountlog.setOrderId(upaccount.getId());
		accountlog.setPeriod(period);
		accountlog.setCreateTs(new Date());
		accountlog.setAttribute5(logid.toString());
		accountlog.setStates("S");
		this.accountLogMapper.insert(accountlog);
		return accountlog;
	}
}
