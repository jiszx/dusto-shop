package com.hhnz.account.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.AccountUtilMapper;
import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.mapper.CMerchUpaccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAccountLogExample;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.api.cache.CacheService;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.util.BigDecimalASME;

/**
 * 配送商资金分配推送SAP结果处理
 * @author hhnz-skevin
 *
 */
@Component("CapitalAllocationCallBack")
@Transactional
public class CapitalAllocationCallBack implements RFCCallback {
	private static Logger logger = Logger.getLogger(CapitalAllocationCallBack.class);
	@Resource
	private CMerchCustAccountLogMapper accountLogMapper;

	@Resource
	private CMerchCustBalancesMapper balancemapper;

	@Resource
	private CMerchCustAccountMapper accountmapper;
	
	@Resource
	private CMerchUpaccountMapper upAccountMapper;
	
	@Resource
	private  OrderUtilMapper  ordermapper;
	
	@Resource
	private AccountUtilMapper  accountUtilmapper;
	
	@Resource
	private TaskService taskService;
	
	@Resource
    private RuntimeService runtimeService;
	
	@Resource
	private CacheService cacheService;
	
	@Resource
	private CMerchCustBaseMapper  merchmapper;
	
	@Autowired
    private ProcessEngine processEngine;

	@Override
	public void errorCallBack(CallbackParam result) {
		CMerchCustAccountLog accountlog = accountLogMapper.selectByPrimaryKey(result.getId());
		CMerchUpaccount upaccount = upAccountMapper.selectByPrimaryKey(accountlog.getOrderId());
		upaccount.setStates("3");
		upaccount.setAttribute2(result.getResult().getMESSAGE());
		this.upAccountMapper.updateByPrimaryKeySelective(upaccount);
		accountlog.setStates("E");
		this.accountLogMapper.updateByPrimaryKeySelective(accountlog);
	}

	@Override
	public void successCallBack(CallbackParam result) {
		// 推送SAP成功
		// 获取当前期间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String period = sdf.format(new Date());// 当前年月
		CMerchCustAccountLog accountlog = accountLogMapper.selectByPrimaryKey(result.getId());
		CMerchUpaccount upaccount = upAccountMapper.selectByPrimaryKey(accountlog.getOrderId());
		// 修改零售商资金余额
		// 调整客户资金
		CMerchCustAccount account = new CMerchCustAccount();
		CMerchCustAccountExample ex = new CMerchCustAccountExample();
		ex.createCriteria().andMerchCustIdEqualTo(accountlog.getMerchCustId());
		ex.createCriteria().andOrganizationIdEqualTo(
				accountlog.getOrganizationId());
		List<CMerchCustAccount> list = new ArrayList<CMerchCustAccount>();
		list = this.accountmapper.selectByExample(ex);
		account = list.get(0);
		account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
		account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
		account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
		account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()).add(accountlog.getdAmt()));
		this.accountmapper.updateByPrimaryKeySelective(account);

		// 期间余额表插入数据
		CMerchCustBalancesExample bex = new CMerchCustBalancesExample();
		CMerchCustBalancesExample.Criteria bext = bex.createCriteria();
		bext.andAccountTypeEqualTo("1");
		bext.andMerchCustIdEqualTo(accountlog.getMerchCustId());
		bext.andOrganizationIdEqualTo(accountlog.getOrganizationId());
		bext.andPeriodEqualTo(period);
		List<CMerchCustBalances> balances = this.balancemapper.selectByExample(bex);
		CMerchCustBalances balance = balances.get(0);
		balance.setdAmt(accountlog.getdAmt());
		this.balancemapper.updateByPrimaryKeySelective(balance);
		
		//更新日志表期间
		accountlog.setPeriod(period);
		accountlog.setStates("S");
		this.accountLogMapper.updateByPrimaryKeySelective(accountlog);
		//修改上账记录表状态
		upaccount.setStates("5");
		this.upAccountMapper.updateByPrimaryKeySelective(upaccount);
		CMerchCustBase  merch = this.merchmapper.selectByPrimaryKey(upaccount.getMerchCusId());
		CMerchCustBase  cust = this.merchmapper.selectByPrimaryKey(merch.getPid());
		/*if( !"7".equals(cust.getCustType())){
			//零售商对应的上级客户不是仓储服务商触发流程
			Long flag = cacheService.decr(merch.getPid().toString()+"flag");
			if(flag == null){
				logger.info("获取当前零售商对应的订单匹配号并触发下一步流程");
				List<String> processIds = this.ordermapper.getOrderProcessIds(merch.getPid());
				if(processIds !=null && processIds.size()>0){
					doTask(processIds);
				}
			}else if(flag.longValue() == 0){
				logger.info("资金分配完成，获取当前零售商对应的订单匹配号并触发下一步流程");
				List<String> processIds = this.ordermapper.getOrderProcessIds(merch.getPid());
				if(processIds !=null && processIds.size()>0){
					doTask(processIds);
				}
			} 
		}*/
	}
	private Integer doTask(List<String> processIds){
		for(String processId:processIds){
			if(StringUtils.isBlank(processId)){
				continue;
			}
			//验证当前任务节点是否在匹配资金节点
			Execution e = processEngine.getRuntimeService().createExecutionQuery()
					.processInstanceId(processId).activityId("RECV_M2O")
					.singleResult();
			if(e == null){
				continue;
			}
			//触发流程下一步操作
			String id = e.getId();
			this.runtimeService.signal(id);
		}
		return 1;
	}
}
