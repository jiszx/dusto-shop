package com.hhnz.process.task.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBalancesExample.Criteria;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;

/**
 * Created by yang on 恢复物流商的授信
 */
@Transactional
@Service("restoreCredit")
public class LKARestoreCredit implements JavaDelegate {
  @Resource
  private OmOrderHeadersAllMapper orderHeaderMapper;
  @Resource
  private OmOrderSpiltsMapper spliteMapper;
  @Resource
  private CMerchCustBaseMapper custbaseMapper;
  @Resource
  private OrderUtilService utilService;
  @Resource
  private OrderService orderservice;
  @Resource
  private CMerchCustAccountLogMapper accountLogMapper;
  @Resource
  private CMerchCustBalancesMapper balanceMapper;
  @Resource
  private CMerchCustAccountMapper accountMapper;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Long id = Long.parseLong(execution.getVariable("key").toString());// 订单号

    OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(id);
    Assert.isTrue("10".equals(order.getOrderType()));
    CMerchCustBase ka = custbaseMapper.selectByPrimaryKey(order.getMerchCustId());

    // 获取订单拆分数据
    OmOrderSpiltsExample ex = new OmOrderSpiltsExample();
    OmOrderSpiltsExample.Criteria ext = ex.createCriteria();
    ext.andHeaderIdEqualTo(order.getId()).andTypeEqualTo("1"); // 取出现金及授信订单拆分行
    List<OmOrderSpilts> lines = spliteMapper.selectByExample(ex);

    CMerchCustAccount account = (CMerchCustAccount) utilService
        .getCustomerAccount(ka.getPid(), order.getOrganizationId()).getRows().get(0);
    changeToDbAccountAmt(account); // 将账户金额乘100

    BigDecimal totalAmt = BigDecimal.ZERO;
    for (OmOrderSpilts line : lines) {
      BigDecimal amt = BigDecimalASME.multiply(StringUtils.isEmpty(line.getDeliveredNum()) ? BigDecimal.ZERO
          : new BigDecimal(line.getDeliveredNum()), line.getPrice());
      totalAmt = totalAmt.add(amt);
    }
    totalAmt = totalAmt.setScale(2, RoundingMode.HALF_EVEN);

    account.setCreditAmt(account.getCreditAmt().add(totalAmt));
    accountMapper.updateByPrimaryKey(account);

    CMerchCustAccountLog creditAccountLog = new CMerchCustAccountLog();
    creditAccountLog.setAccountType("3");
    creditAccountLog.setdAmt(totalAmt);
    creditAccountLog.setCreateTs(new Date());
    creditAccountLog.setMerchCustId(ka.getPid());
    creditAccountLog.setOrderId(id);
    creditAccountLog.setOrganizationId(order.getOrganizationId());
    creditAccountLog.setType("4"); // 调整
    creditAccountLog.setPeriod(DateUtil.periodNow());
    creditAccountLog.setStates("S");
    accountLogMapper.insert(creditAccountLog);

    changeDamt("3", ka.getPid(), order.getOrganizationId(), totalAmt);

  }

  private void changeDamt(String accountType, Long merchid, String orgid, BigDecimal cAmt) {
    CMerchCustBalancesExample balanceEx = new CMerchCustBalancesExample();
    Criteria param = balanceEx.createCriteria();
    param.andMerchCustIdEqualTo(merchid);
    param.andOrganizationIdEqualTo(orgid);
    param.andAccountTypeEqualTo(accountType);
    param.andPeriodEqualTo(DateUtil.periodNow());
    List<CMerchCustBalances> balances = balanceMapper.selectByExample(balanceEx);
    if (!balances.isEmpty()) {
      CMerchCustBalances balance = balances.get(0);
      balance.setdAmt(balance.getdAmt().add(cAmt));
      balanceMapper.updateByPrimaryKey(balance);
    }
  }

  private void changeToDbAccountAmt(CMerchCustAccount account) {
    account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
    account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
    account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
    account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
  }
}
