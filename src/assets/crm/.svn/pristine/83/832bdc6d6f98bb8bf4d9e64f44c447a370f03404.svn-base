package com.hhnz.process.task.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.base.MoreObjects;
import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.mapper.CMerchCustAdjustMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAdjust;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBalancesExample.Criteria;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderHeadersAllExample;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;

/**
 * Created by yang on 2016-8-19. 扣减客户金额处理
 */
@Service("reduceMerchAccountTask")
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ReduceMerchAccountTask implements JavaDelegate {

  @Resource
  private OmOrderHeadersAllMapper orderHeaderMapper;
  @Resource
  private OmOrderLinesAllMapper orderLineMapper;
  @Resource
  private CMerchCustAccountMapper accountMapper;
  @Resource
  private CMerchCustAdjustMapper adjustMapper;
  @Resource
  private CMerchCustAccountLogMapper accountLogMapper;
  @Resource
  private OrderUtilService utilService;
  @Resource
  private CMerchCustBalancesMapper balanceMapper;

  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {

    Long id = Long.parseLong(delegateExecution.getVariable("key").toString());// 订单号或批次号
    String orderType = (String) delegateExecution.getVariable("type");
    String ids = (String) delegateExecution.getVariable("ids");
    Long userid = 0L; // 默认使用0
    // 处理逻辑
    // 扣减客户金额，现金、货补，授信
    // 扣款记录 --》
    int result = 1;
    if ("1".equals(orderType)) {
      for (String orderid : ids.split(",")) {
        reduceByLine(Long.parseLong(orderid), userid);
      }
    } else {
      result = reduceByLine(id, userid);
      // fillProcessId(id, delegateExecution);
    }
    delegateExecution.setVariable("REDUCE_FLAG", result); // 1 成功 0 失败
  }

  public int reduceByLine(Long orderid, Long userid) {
    int result = 1;
    OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(orderid);

    // 查找订单行
    OmOrderLinesAllExample example = new OmOrderLinesAllExample();
    example.createCriteria().andHeaderIdEqualTo(order.getId());
    List<OmOrderLinesAll> lines = orderLineMapper.selectByExample(example);
    CMerchCustAccount account = getAccount(order);
    // 将账户金额乘100
    changeToDbAccountAmt(account);
    BigDecimal accountCash = account.getCashAmt();

    BigDecimal disSubsidy = BigDecimal.ZERO;
    BigDecimal disMaterialCashAndCredit = BigDecimal.ZERO;
    BigDecimal disCash = BigDecimal.ZERO;
    BigDecimal disCredit = BigDecimal.ZERO;
    BigDecimal disFreight = BigDecimal.ZERO;
    
    for (OmOrderLinesAll line : lines) {
      BigDecimal lineAmt = MoreObjects.firstNonNull(line.getNum(), BigDecimal.ZERO).multiply(line.getOrderPrice()).setScale(0, RoundingMode.HALF_UP);
      BigDecimal lineSubsidy = MoreObjects.firstNonNull(line.getHbNum(), BigDecimal.ZERO).multiply(line.getOrderPrice()).setScale(0, RoundingMode.HALF_UP);
      disMaterialCashAndCredit = disMaterialCashAndCredit.add(lineAmt);
      disSubsidy = disSubsidy.add(lineSubsidy);
      disFreight = disFreight.add(MoreObjects.firstNonNull(line.getFreight(), BigDecimal.ZERO));
    }

    // 验证金额是否足够
    int isEnough = validateOrderMoney(account, disMaterialCashAndCredit, disSubsidy, disFreight, order);
    if (isEnough == 0) {
      return isEnough;
    }

    // 扣除物料现金及授信
    if (isRetailOrder(order)) {
      account.setCashAmt(accountCash.subtract(disMaterialCashAndCredit));
      disCash = disMaterialCashAndCredit;
    } else if (accountCash.compareTo(disMaterialCashAndCredit) >= 0) {
      disCash = disMaterialCashAndCredit;
      account.setCashAmt(accountCash.subtract(disMaterialCashAndCredit));
    } else {
      disCash = accountCash;
      disCredit = disMaterialCashAndCredit.subtract(accountCash);
      account.setCashAmt(BigDecimal.ZERO);
      account.setCreditAmt(account.getCreditAmt().subtract(disCredit));
    }
    // 扣除运费
	if (isRetailOrder(order)) {
		account.setCashAmt(account.getCashAmt().subtract(disFreight));
		freightLog(order, disFreight, "1");
	} else if (account.getCashAmt().compareTo(disFreight) >= 0) {
		account.setCashAmt(account.getCashAmt().subtract(disFreight));
		freightLog(order, disFreight, "1");
	} else {
		BigDecimal disFreightCash = account.getCashAmt();
		BigDecimal disFreightCredit = disFreight.subtract(disFreightCash);
		account.setCreditAmt(account.getCreditAmt().subtract(disFreightCredit));
		account.setCashAmt(BigDecimal.ZERO);
		freightLog(order, disFreightCash, "1");
	    freightLog(order, disFreightCredit, "3");
	}
    // 扣除货补
    if (disSubsidy.compareTo(BigDecimal.ZERO) == 1) {
      account.setSubsidyAmt(account.getSubsidyAmt().subtract(disSubsidy));
    }
    accountMapper.updateByPrimaryKey(account);

    // 记录订单现金扣减
    if(disCash.compareTo(BigDecimal.ZERO)==1) {
	    CMerchCustAdjust adjust = new CMerchCustAdjust();
	    adjust.setOrderId(orderid);
	    adjust.setMerchCustId(order.getMerchCustId());
	    adjust.setOrganizationId(order.getOrganizationId());
	    adjust.setCreateTs(new Date());
	    adjust.setCreateOid(userid);
	    adjust.setOrderId(orderid);
	    adjust.setAmt(disCash);
	    adjust.setType("2");// 订单调整
	    adjust.setAccountType("1");
	    adjust.setReason("1");
	    adjust.setStates("1");
	    adjustMapper.insert(adjust);
	    CMerchCustAccountLog accountLog = new CMerchCustAccountLog();
	    accountLog.setAccountType("1");
	    accountLog.setcAmt(disCash);
	    accountLog.setCreateTs(new Date());
	    accountLog.setMerchCustId(order.getMerchCustId());
	    accountLog.setOrderId(orderid);
	    accountLog.setOrganizationId(order.getOrganizationId());
	    accountLog.setType("4"); // 调整
	    accountLog.setPeriod(DateUtil.periodNow());
	    accountLog.setStates("S");
	    // accountLog.setSapVoucherId(order.getSapOrderId());
	    accountLogMapper.insert(accountLog);
	
	    changeCamt("1", order.getMerchCustId(), order.getOrganizationId(), disCash);
    }
    // 记录授信扣减
    if (disCredit.compareTo(BigDecimal.ZERO) == 1) {
      CMerchCustAdjust creditAdjust = new CMerchCustAdjust();
      creditAdjust.setMerchCustId(order.getMerchCustId());
      creditAdjust.setOrganizationId(order.getOrganizationId());
      creditAdjust.setCreateTs(new Date());
      creditAdjust.setCreateOid(userid);
      creditAdjust.setOrderId(orderid);
      creditAdjust.setAmt(disCredit);
      creditAdjust.setType("2");// 订单调整
      creditAdjust.setAccountType("3");
      creditAdjust.setReason("1");
      creditAdjust.setStates("1");
      adjustMapper.insert(creditAdjust);
      CMerchCustAccountLog creditAccountLog = new CMerchCustAccountLog();
      creditAccountLog.setAccountType("3");
      creditAccountLog.setcAmt(disCredit);
      creditAccountLog.setCreateTs(new Date());
      creditAccountLog.setMerchCustId(order.getMerchCustId());
      creditAccountLog.setOrderId(orderid);
      creditAccountLog.setOrganizationId(order.getOrganizationId());
      creditAccountLog.setType("4"); // 调整
      creditAccountLog.setPeriod(DateUtil.periodNow());
      creditAccountLog.setStates("S");
      // creditAccountLog.setSapVoucherId(order.getSapOrderId());
      accountLogMapper.insert(creditAccountLog);

      changeCamt("3", order.getMerchCustId(), order.getOrganizationId(), disCredit);
    }
    // 记录货补扣减
    if (disSubsidy.compareTo(BigDecimal.ZERO) == 1) {
      CMerchCustAdjust subsidyAdjust = new CMerchCustAdjust();
      subsidyAdjust.setMerchCustId(order.getMerchCustId());
      subsidyAdjust.setOrganizationId(order.getOrganizationId());
      subsidyAdjust.setCreateTs(new Date());
      subsidyAdjust.setCreateOid(userid);
      subsidyAdjust.setOrderId(orderid);
      subsidyAdjust.setAmt(disSubsidy);
      subsidyAdjust.setType("2");// 订单调整
      subsidyAdjust.setAccountType("2");
      subsidyAdjust.setReason("1");
      subsidyAdjust.setStates("1");
      adjustMapper.insert(subsidyAdjust);
      CMerchCustAccountLog subsidyAccountLog = new CMerchCustAccountLog();
      subsidyAccountLog.setAccountType("2");
      subsidyAccountLog.setcAmt(disSubsidy);
      subsidyAccountLog.setCreateTs(new Date());
      subsidyAccountLog.setMerchCustId(order.getMerchCustId());
      subsidyAccountLog.setOrderId(orderid);
      subsidyAccountLog.setOrganizationId(order.getOrganizationId());
      subsidyAccountLog.setType("4"); // 调整
      subsidyAccountLog.setPeriod(DateUtil.periodNow());
      subsidyAccountLog.setStates("S");
      // subsidyAccountLog.setSapVoucherId(order.getSapOrderId());
      accountLogMapper.insert(subsidyAccountLog);

      changeCamt("2", order.getMerchCustId(), order.getOrganizationId(), disSubsidy);
    }
    return result;
  }

	/**
	 * 
	 * @param order
	 * @param amt
	 * @param type
	 *            1现金，2货补，3授信
	 */
	private void freightLog(OmOrderHeadersAll order, BigDecimal amt, String type) {
		if (amt.compareTo(BigDecimal.ZERO) == 1) {
			CMerchCustAccountLog accountLog = new CMerchCustAccountLog();
			accountLog.setAccountType(type);
			accountLog.setcAmt(amt);
			accountLog.setCreateTs(new Date());
			accountLog.setMerchCustId(order.getMerchCustId());
			accountLog.setOrderId(order.getId());
			accountLog.setOrganizationId(order.getOrganizationId());
			accountLog.setType("13"); // 运费
			accountLog.setPeriod(DateUtil.periodNow());
			accountLog.setStates("S");
			accountLogMapper.insert(accountLog);
			changeCamt(type, order.getMerchCustId(), order.getOrganizationId(), amt);
		}
	}

  /**
   * 
   * @param id
   * @param userid
   * @return
   * @deprecated 扣减资金由行表计算  {@link #reduceByLine(Long, Long)}
   */
  @Deprecated
  public int reduceByOrderId(Long id, Long userid) {
    int result = 1;
    OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(id);

    CMerchCustAccount account = getAccount(order);
    // 查找订单行
    OmOrderLinesAllExample example = new OmOrderLinesAllExample();
    example.createCriteria().andHeaderIdEqualTo(order.getId());
    List<OmOrderLinesAll> lines = orderLineMapper.selectByExample(example);

    // 将账户金额乘100
    changeToDbAccountAmt(account);

    BigDecimal cash = account.getCashAmt();

    BigDecimal disSubsidy = BigDecimal.ZERO;
    for (OmOrderLinesAll line : lines) {
      // disSubsidy += StringUtils.isEmpty(line.getHbAmt())? 0L : Long.parseLong(line.getHbAmt());
      disSubsidy = disSubsidy.add(line.getHbAmt() == null ? BigDecimal.ZERO : line.getHbAmt());
    }
    // 验证金额是否足够
    int isEnough = validateOrderMoney(cash, disSubsidy, order, account);
    if (isEnough == 0) {
      return isEnough;
    }

    // 扣除现金及授信
    BigDecimal disCash = order.getOrderAmt();
    BigDecimal disCredit = BigDecimal.ZERO;

    if (isRetailOrder(order)) {
      account.setCashAmt(cash.subtract(disCash));
    } else if (cash.compareTo(order.getOrderAmt()) >= 0) {
      account.setCashAmt(cash.subtract(disCash));
    } else {
      disCash = cash;
      disCredit = order.getOrderAmt().subtract(cash);
      account.setCashAmt(BigDecimal.ZERO);
      account.setCreditAmt(account.getCreditAmt().subtract(disCredit));
    }
    // 扣除货补

    if (disSubsidy.compareTo(BigDecimal.ZERO) == 1) {
      account.setSubsidyAmt(account.getSubsidyAmt().subtract(disSubsidy));
    }
    accountMapper.updateByPrimaryKey(account);

    // 记录现金扣减
    CMerchCustAdjust adjust = new CMerchCustAdjust();
    adjust.setOrderId(id);
    adjust.setMerchCustId(order.getMerchCustId());
    adjust.setOrganizationId(order.getOrganizationId());
    adjust.setCreateTs(new Date());
    adjust.setCreateOid(userid);
    adjust.setOrderId(id);
    adjust.setAmt(disCash);
    adjust.setType("2");// 订单调整
    adjust.setAccountType("1");
    adjust.setReason("1");
    adjust.setStates("1");
    adjustMapper.insert(adjust);
    CMerchCustAccountLog accountLog = new CMerchCustAccountLog();
    accountLog.setAccountType("1");
    accountLog.setcAmt(disCash);
    accountLog.setCreateTs(new Date());
    accountLog.setMerchCustId(order.getMerchCustId());
    accountLog.setOrderId(id);
    accountLog.setOrganizationId(order.getOrganizationId());
    accountLog.setType("4"); // 调整
    accountLog.setPeriod(DateUtil.periodNow());
    accountLog.setStates("S");
    // accountLog.setSapVoucherId(order.getSapOrderId());
    accountLogMapper.insert(accountLog);

    changeCamt("1", order.getMerchCustId(), order.getOrganizationId(), disCash);
    // 记录授信扣减
    if (disCredit.compareTo(BigDecimal.ZERO) == 1) {
      CMerchCustAdjust creditAdjust = new CMerchCustAdjust();
      creditAdjust.setMerchCustId(order.getMerchCustId());
      creditAdjust.setOrganizationId(order.getOrganizationId());
      creditAdjust.setCreateTs(new Date());
      creditAdjust.setCreateOid(userid);
      creditAdjust.setOrderId(id);
      creditAdjust.setAmt(disCredit);
      creditAdjust.setType("2");// 订单调整
      creditAdjust.setAccountType("3");
      creditAdjust.setReason("1");
      creditAdjust.setStates("1");
      adjustMapper.insert(creditAdjust);
      CMerchCustAccountLog creditAccountLog = new CMerchCustAccountLog();
      creditAccountLog.setAccountType("3");
      creditAccountLog.setcAmt(disCredit);
      creditAccountLog.setCreateTs(new Date());
      creditAccountLog.setMerchCustId(order.getMerchCustId());
      creditAccountLog.setOrderId(id);
      creditAccountLog.setOrganizationId(order.getOrganizationId());
      creditAccountLog.setType("4"); // 调整
      creditAccountLog.setPeriod(DateUtil.periodNow());
      creditAccountLog.setStates("S");
      // creditAccountLog.setSapVoucherId(order.getSapOrderId());
      accountLogMapper.insert(creditAccountLog);

      changeCamt("3", order.getMerchCustId(), order.getOrganizationId(), disCredit);
    }
    // 记录货补扣减
    if (disSubsidy.compareTo(BigDecimal.ZERO) == 1) {
      CMerchCustAdjust subsidyAdjust = new CMerchCustAdjust();
      subsidyAdjust.setMerchCustId(order.getMerchCustId());
      subsidyAdjust.setOrganizationId(order.getOrganizationId());
      subsidyAdjust.setCreateTs(new Date());
      subsidyAdjust.setCreateOid(userid);
      subsidyAdjust.setOrderId(id);
      subsidyAdjust.setAmt(disSubsidy);
      subsidyAdjust.setType("2");// 订单调整
      subsidyAdjust.setAccountType("2");
      subsidyAdjust.setReason("1");
      subsidyAdjust.setStates("1");
      adjustMapper.insert(subsidyAdjust);
      CMerchCustAccountLog subsidyAccountLog = new CMerchCustAccountLog();
      subsidyAccountLog.setAccountType("2");
      subsidyAccountLog.setcAmt(disSubsidy);
      subsidyAccountLog.setCreateTs(new Date());
      subsidyAccountLog.setMerchCustId(order.getMerchCustId());
      subsidyAccountLog.setOrderId(id);
      subsidyAccountLog.setOrganizationId(order.getOrganizationId());
      subsidyAccountLog.setType("4"); // 调整
      subsidyAccountLog.setPeriod(DateUtil.periodNow());
      subsidyAccountLog.setStates("S");
      // subsidyAccountLog.setSapVoucherId(order.getSapOrderId());
      accountLogMapper.insert(subsidyAccountLog);

      changeCamt("2", order.getMerchCustId(), order.getOrganizationId(), disSubsidy);
    }
    // 更新订单状态
    // order.setStates("11");
    orderHeaderMapper.updateByPrimaryKey(order);
    return result;
  }

  private boolean isRetailOrder(OmOrderHeadersAll order) {
    return "1".equals(order.getOrderType()) || "6".equals(order.getOrderType());
  }

  private int validateOrderMoney(CMerchCustAccount account, BigDecimal disCashAndCredit,
      BigDecimal disSubsidy, BigDecimal disFreight, OmOrderHeadersAll order) {
    if (isRetailOrder(order)) {
      return 1;
    }
    if (account.getCashAmt().add(account.getCreditAmt()).compareTo(disCashAndCredit.add(disFreight)) < 0) {
      return 0;
    }
    if (account.getSubsidyAmt().compareTo(disSubsidy) < 0) {
      return 0;
    }
    return 1;
  }

  public int validateOrderMoney(BigDecimal accountCash, BigDecimal disSubsidy,
      OmOrderHeadersAll order, CMerchCustAccount account) {
    if (isRetailOrder(order)) {
      return 1;
    }
    if (accountCash.add(account.getCreditAmt()).compareTo(order.getOrderAmt()) < 0) {
      return 0;
    }
    if (disSubsidy.compareTo(account.getSubsidyAmt()) > 0) {
      return 0;
    }
    return 1;
  }

  CMerchCustAccount getAccount(OmOrderHeadersAll order) {
    Long merchid = null;
    if ("10".equals(order.getOrderType())) {
      Assert.notNull(order.getBillTo());
      merchid = order.getBillTo();
    } else {
      merchid = order.getMerchCustId();
    }
    AjaxDTO accountsDto = utilService.getCustomerAccount(merchid, order.getOrganizationId());
    return (CMerchCustAccount) accountsDto.getRows().get(0);
  }

  public int validateMoney(String ids) {
    List<Long> orderids = new ArrayList<>();
    for (String id : ids.split(",")) {
      orderids.add(Long.parseLong(id));
    }
    OmOrderHeadersAllExample selectExa = new OmOrderHeadersAllExample();
    selectExa.createCriteria().andIdIn(orderids);
    List<OmOrderHeadersAll> orders = orderHeaderMapper.selectByExample(selectExa);
    Map<String, BigDecimal> moneys = new HashMap<>();
    for (OmOrderHeadersAll order : orders) {
      String merchid = String.valueOf(order.getMerchCustId());
      BigDecimal money = moneys.get(merchid);
      money = money == null ? BigDecimal.ZERO : money;
      moneys.put(merchid, money.add(order.getOrderAmt()));
    }
    for (String merch : moneys.keySet()) {
      CMerchCustAccountExample ex = new CMerchCustAccountExample();
      ex.createCriteria().andMerchCustIdEqualTo(Long.parseLong(merch))
          .andOrganizationIdEqualTo(orders.get(0).getOrganizationId());
      List<CMerchCustAccount> accounts = accountMapper.selectByExample(ex);
      if (accounts.isEmpty()) {
        return 0;
      }
      CMerchCustAccount account = accounts.get(0);
      if (BigDecimalASME.multiply(account.getCashAmt()).compareTo(moneys.get(merch)) < 0) {
        return 0;
      }
    }
    return 1;
  }

  private void changeCamt(String accountType, Long merchid, String orgid, BigDecimal cAmt) {
    CMerchCustBalancesExample balanceEx = new CMerchCustBalancesExample();
    Criteria param = balanceEx.createCriteria();
    param.andMerchCustIdEqualTo(merchid);
    param.andOrganizationIdEqualTo(orgid);
    param.andAccountTypeEqualTo(accountType);
    param.andPeriodEqualTo(DateUtil.periodNow());
    List<CMerchCustBalances> balances = balanceMapper.selectByExample(balanceEx);
    if (!balances.isEmpty()) {
      CMerchCustBalances balance = balances.get(0);
      balance.setcAmt(balance.getcAmt().add(cAmt));
      balanceMapper.updateByPrimaryKey(balance);
    }
  }

  private void changeToDbAccountAmt(CMerchCustAccount account) {
    account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
    account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
    account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
    account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
  }

  boolean fillProcessId(Long orderid, DelegateExecution execution) {
    String processId = execution.getProcessInstanceId();
    OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(orderid);
    String pid = order.getAttribute1();
    if (pid == null || pid.length() < 2) {
      OmOrderHeadersAll updateOrder = new OmOrderHeadersAll();
      updateOrder.setId(orderid);
      updateOrder.setAttribute1(processId);
      orderHeaderMapper.updateByPrimaryKeySelective(updateOrder);
      return true;
    }
    return false;
  }
}
