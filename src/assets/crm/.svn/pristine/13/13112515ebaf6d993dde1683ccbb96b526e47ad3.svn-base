package com.hhnz.order.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderCancelMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.service.IOrderCancelService;
import com.hhnz.util.BigDecimalASME;

/**
 * @author: chaoyang.ren
 * @date:2016年11月16日
 * @time:上午11:31:42
 * @email:chaoyang.ren@foxmail.com
 */
@Component("OrderCancelCallback")
@Transactional
public class OrderCancelCallback implements RFCCallback{
	@Resource
	private OrderCancelMapper cancelmapper;
	
	@Resource
	private OmOrderHeadersAllMapper  headermapper;
	
	@Resource
	private OmOrderLinesAllMapper  linemapper;
	
	@Resource
	private OmOrderSpiltsMapper  spiltmapper;
	
	@Resource
	private CMerchCustAccountMapper  accountmapper;
	
	@Resource
	private CMerchCustBalancesMapper  balancesmapper;
	
	@Resource
	private IOrderCancelService orderCancelService;
	
	@Override
	public void errorCallBack(CallbackParam result) {
		Long id = result.getId();
		//获取订单头
		OmOrderHeadersAll order = this.headermapper.selectByPrimaryKey(id);
		order.setAttribute10(result.getResult().getMESSAGE());
		this.headermapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public void successCallBack(CallbackParam result) {
		Long id = result.getId();
		//获取订单头
		OmOrderHeadersAll  order = this.headermapper.selectByPrimaryKey(id);
		BigDecimal  cashAmt = BigDecimal.ZERO;
		BigDecimal  hbAmt= BigDecimal.ZERO;
		//BigDecimal  plicyAmt = BigDecimal.ZERO;
		OmOrderLinesAllExample  lineex = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineext = lineex.createCriteria();
		lineext.andHeaderIdEqualTo(id);
		//订单行
		List<OmOrderLinesAll> lines = this.linemapper.selectByExample(lineex);
		for(OmOrderLinesAll line :lines){
			//拆分订单信息
			OmOrderSpiltsExample spiltex = new OmOrderSpiltsExample();
			OmOrderSpiltsExample.Criteria spiltext = spiltex.createCriteria();
			spiltext.andHeaderIdEqualTo(id);
			spiltext.andLineIdEqualTo(line.getId());
			List<OmOrderSpilts> spilts = this.spiltmapper.selectByExample(spiltex);
			BigDecimal  cashNum = BigDecimal.ZERO;
			BigDecimal  hbNum = BigDecimal.ZERO;
			BigDecimal  plicyNum = BigDecimal.ZERO;
			for(OmOrderSpilts spilt:spilts){
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("orderid", order.getSapOrderId());
				params.put("lineid", line.getId());
				params.put("itemNo", spilt.getOrderitemSapNo());
				params.put("materialId", spilt.getMaterialId());
				//应收发票数量
				BigDecimal  invouceNum = this.cancelmapper.getInvouceNum(params);
				//处理差异数量
				if(StringUtils.equals("2", spilt.getType())){
					//货补
					hbNum = hbNum.add(spilt.getNum().subtract(invouceNum));
					hbAmt = hbAmt.add(line.getOrderPrice().multiply(spilt.getNum().subtract(invouceNum)));
				}else if(StringUtils.equals("3", spilt.getType())){
					//销售政策
					plicyNum=plicyNum.add(spilt.getNum().subtract(invouceNum));
				}else{
					//标准订单
					cashNum=cashNum.add(spilt.getNum().subtract(invouceNum));
					cashAmt = cashAmt.add(line.getOrderPrice().multiply(spilt.getNum().subtract(invouceNum)));
				}
			}
			line.setReturnNum((line.getReturnNum()==null?BigDecimal.ZERO:line.getReturnNum()).add(cashNum));
			line.setRetrunHbNum((line.getRetrunHbNum()==null?BigDecimal.ZERO:line.getRetrunHbNum()).add(hbNum));
			line.setReturnPolicyNum((line.getReturnPolicyNum()==null?BigDecimal.ZERO:line.getReturnPolicyNum()).add(plicyNum));
			this.linemapper.updateByPrimaryKeySelective(line);
		}
		//获取客户资金余额
		CMerchCustAccountExample  accountex = new CMerchCustAccountExample();
		CMerchCustAccountExample.Criteria accountext = accountex.createCriteria();
		accountext.andMerchCustIdEqualTo(order.getMerchCustId());
		accountext.andOrganizationIdEqualTo(order.getOrganizationId());
		List<CMerchCustAccount> accounts = this.accountmapper.selectByExample(accountex);
		if(accounts !=null && accounts.size()==1){
			CMerchCustAccount account = accounts.get(0);
			account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
			account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
			account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
			account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
			//货补
			if(hbAmt.compareTo(BigDecimal.ZERO)==1){
				account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()).add(hbAmt));
				orderCancelService.InsertLog("2",hbAmt,order);
			}
			//现金
			if(cashAmt.compareTo(BigDecimal.ZERO)==1){
				if(BigDecimalASME.multiply(account.getCreditAmt()).compareTo(account.getContractCreditAmt()) ==-1){
					BigDecimal  amt = account.getContractCreditAmt().subtract(BigDecimalASME.multiply(account.getCreditAmt()));
					if(cashAmt.compareTo(amt)>=0){
						//取消金额大于授信额度和可用授信额度差额
						orderCancelService.InsertLog("3",amt,order);
						account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()).add(amt));
						
						orderCancelService.InsertLog("1",cashAmt.subtract(amt),order);
						account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()).add(cashAmt.subtract(amt)));
					}else{
						//已使用授信金额大于退货金额，全部退换授信
						orderCancelService.InsertLog("3",cashAmt,order);
						account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()).add(cashAmt));
					}
				}else{
					//授信额度等于可用授信，退货金额全部退回现金账户
					orderCancelService.InsertLog("1",cashAmt,order);
					account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()).add(cashAmt));
				}
			}
			this.accountmapper.updateByPrimaryKeySelective(account);
		}
		
		order.setStates("9");
		this.headermapper.updateByPrimaryKeySelective(order);
	}

}
