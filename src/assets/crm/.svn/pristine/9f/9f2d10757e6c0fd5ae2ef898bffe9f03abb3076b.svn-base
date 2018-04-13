package com.hhnz.process.task.orderback;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.h2.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderBackMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.service.OrderBackService;
import com.hhnz.util.BigDecimalASME;

@Component("SendBackOrderSapCallBack")
@Transactional
public class SendBackOrderSapCallBack implements RFCCallback {
	@Resource
	private OmOrderSpiltsMapper spiltMapper;
	@Resource
	private OmOrderHeadersAllMapper orderHeaderMapper;
	@Resource
	private OmOrderLinesAllMapper orderLineMapper;
	@Resource
	private CMerchCustBaseMapper merchMapper;
	@Resource
	private CMerchCustAccountLogMapper accountLogMapper;
	@Resource
	private CMerchCustBalancesMapper balancesMapper;
	@Resource
	private CMerchCustAccountMapper accountMapper;
	@Resource
	private OrderBackMapper  orderBackMapper;
	@Resource
	private OrderBackService  backservice;
	@Override
	public void errorCallBack(CallbackParam result) {
		
	}

	@Override
	public void successCallBack(CallbackParam r) {
		Long id = r.getId();//订单号
		OmOrderHeadersAll  header = this.orderHeaderMapper.selectByPrimaryKey(id);//退货订单
		CMerchCustBase merch = this.merchMapper.selectByPrimaryKey(header.getMerchCustId());//客户
		updateAmt(header);
		//更新退货订单状态
		header.setStates("4");
		this.orderHeaderMapper.updateByPrimaryKeySelective(header);
		//更新原订单已退货数量
		this.backservice.updateOldOrderReturnNum(id);
		
		if("1".equals(merch.getCustType()) && !StringUtils.isNullOrEmpty(header.getAttribute2())) {
			//合作仓储服务商退货调拨单，处理对应合作盐业公司金额
			OmOrderHeadersAll order = this.orderHeaderMapper.selectByPrimaryKey(Long.parseLong(header.getAttribute2()));
			if(order !=null) {
				updateAmt(order);
				order.setStates("4");
				order.setSapOrderId(header.getSapOrderId());
				this.orderHeaderMapper.updateByPrimaryKeySelective(order);
				this.backservice.updateOldOrderReturnNum(order.getId());
			}
		}
	}
	private void updateAmt(OmOrderHeadersAll header) {
		BigDecimal orderamt= BigDecimal.ZERO;
		BigDecimal hbamt = BigDecimal.ZERO;
		//获取行数据
		OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
		lineExt.andHeaderIdEqualTo(header.getId());
		
		List<OmOrderLinesAll> lines = this.orderLineMapper.selectByExample(lineEx);
		if(lines !=null && lines.size() >0){
			for(OmOrderLinesAll l :lines){
				//计算退货订单的金额
				orderamt =orderamt.add(l.getOrderAmt());
				hbamt = hbamt.add(l.getHbAmt());
			}
		}
		if(hbamt.compareTo(BigDecimal.ZERO)>0){
			//货补金额大于0
			updateAccountAmt(hbamt,"2",header);
		}
		if(orderamt.compareTo(BigDecimal.ZERO)>0){
			updateAccountAmt(orderamt,"3",header);
		}
	}
	private void updateAccountAmt(BigDecimal amt,String accountType,OmOrderHeadersAll order){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		String period = sf.format(new Date());
		
		//更新账户数据
		CMerchCustAccountExample accountEx = new CMerchCustAccountExample();
		CMerchCustAccountExample.Criteria accountExt= accountEx.createCriteria();
		accountExt.andMerchCustIdEqualTo(order.getMerchCustId());
		List<CMerchCustAccount> accounts = this.accountMapper.selectByExample(accountEx);
		CMerchCustAccount account = accounts.get(0);
		account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
		account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
		account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
		account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
		if("2".equals(accountType)){
			//货补
			account.setSubsidyAmt(account.getSubsidyAmt().add(amt));
			addAccountLog("2",order.getMerchCustId(),amt,period,order.getId(),order.getCreateOid().toString());
			updateBalanceAmt(order.getMerchCustId(),"2",period,amt);
		}
		if("3".equals(accountType)){
			BigDecimal creditLimit = account.getContractCreditAmt();//限制金额等于授信额度
			if(account.getBondAmt().compareTo(BigDecimal.ZERO) >0){
				//客户存在保证金
				creditLimit = account.getBondAmt();
			}
			if(creditLimit.compareTo(BigDecimal.ZERO)<=0){
				//透支金额小于等于0
				account.setCashAmt(account.getCashAmt().add(amt));
				addAccountLog("1",order.getMerchCustId(),amt,period,order.getId(),order.getCreateOid().toString());
				updateBalanceAmt(order.getMerchCustId(),"1",period,amt);
			}else{
				if((account.getCreditAmt().add(amt)).compareTo(creditLimit) >0){
					//退货金额加上授信金额大于授信额度
					BigDecimal difference = creditLimit.subtract(account.getCreditAmt());
					account.setCashAmt(amt.subtract(difference));
					addAccountLog("1",order.getMerchCustId(),amt.subtract(difference),period,order.getId(),order.getCreateOid().toString());
					updateBalanceAmt(order.getMerchCustId(),"1",period,amt.subtract(difference));
					
					account.setCreditAmt(account.getCreditAmt().add(difference));
					addAccountLog("3",order.getMerchCustId(),difference,period,order.getId(),order.getCreateOid().toString());
					updateBalanceAmt(order.getMerchCustId(),"3",period,difference);
				}else{
					//退货金额加上授信金额小于授信额度,全部退货到授信
					account.setCreditAmt(account.getCreditAmt().add(amt));
					addAccountLog("3",order.getMerchCustId(),amt,period,order.getId(),order.getCreateOid().toString());
					updateBalanceAmt(order.getMerchCustId(),"3",period,amt);
				}
			}
		}
		this.accountMapper.updateByPrimaryKeySelective(account);
	}
	private void addAccountLog(String accountType,Long merchCustId,BigDecimal amt,String period,Long orderId,String creater){
		CMerchCustBase merch= this.merchMapper.selectByPrimaryKey(merchCustId);
		//处理日志表
		CMerchCustAccountLog accountlog = new CMerchCustAccountLog();
		accountlog.setAccountType(accountType);
		accountlog.setcAmt(BigDecimal.ZERO);
		accountlog.setdAmt(amt);
		accountlog.setCreateTs(new Date());
		accountlog.setPeriod(period);
		accountlog.setMerchCustId(merchCustId);
		accountlog.setOrderId(orderId);
		accountlog.setOrganizationId(merch.getOrganizationId());
		accountlog.setStates("S");
		accountlog.setType("9");
		accountlog.setCreater(creater);
		this.accountLogMapper.insert(accountlog);
	}
	private void updateBalanceAmt(Long merchCustId, String accountType,
			String period, BigDecimal amt) {
		// 更新期间数据
		CMerchCustBalancesExample balanceEx = new CMerchCustBalancesExample();
		CMerchCustBalancesExample.Criteria balanceExt = balanceEx
				.createCriteria();
		balanceExt.andMerchCustIdEqualTo(merchCustId);
		balanceExt.andAccountTypeEqualTo(accountType);
		balanceExt.andPeriodEqualTo(period);
		List<CMerchCustBalances> balances = this.balancesMapper
				.selectByExample(balanceEx);
		CMerchCustBalances accountBalance = new CMerchCustBalances();
		if (balances != null && balances.size() > 0) {
			accountBalance = balances.get(0);
		}
		accountBalance.setdAmt(accountBalance.getdAmt().add(amt));
		this.balancesMapper.updateByPrimaryKeySelective(accountBalance);
	}
}
