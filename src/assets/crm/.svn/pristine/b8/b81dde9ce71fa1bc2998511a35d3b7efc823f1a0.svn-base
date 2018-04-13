package com.hhnz.task.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.mapper.CMerchCustAdjustMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAdjust;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBalancesExample.Criteria;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.order.model.Invoice;
import com.hhnz.order.model.OmOrderDeliveredV;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderHeadersAllExample;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.task.service.ICrmTaskJobService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;

/**
 * 定时任务
 * 
 * @author hhnz-skevin
 *
 */
// @Component("crmTaskJobController")
@Service
public class CrmTaskJobController {
  private static Logger logger = LoggerFactory.getLogger(CrmTaskJobController.class);

  @Resource
  private ICrmTaskJobService service;
  @Resource
  private OmOrderHeadersAllMapper orderMapper;
  @Resource
  private OrderUtilMapper orderutilMapper;
  @Resource
  private CMerchCustAccountMapper accountMapper;
  @Resource
  private CMerchCustAdjustMapper adjustMapper;
  @Resource
  private CMerchCustAccountLogMapper accountLogMapper;
  @Resource
  private CMerchCustBalancesMapper balanceMapper;
  @Resource
  private OrderUtilService utilService;
  @Resource
  private RuntimeService runtimeService;
  @Resource
  private OrderService orderservice;

  /**
   * 定时任务主程序
   */
  // @Scheduled(cron = "0 * 14 * * ?")
  public void JobMain() {
    // System.out.println("sysdate");
    this.service.crmjobs();
  }

  /**
   * 触发销售订单追加物流信号
   */
  @Scheduled(cron = "0 0/30 * * * ?")
  public void addLogistics() {
    List<OmOrderHeadersAll> orders = waitSendOrders();
    for (OmOrderHeadersAll order : orders) {
      String pid = order.getAttribute1();
      if (StringUtils.isEmpty(pid)) {
        continue;
      }
      Execution execution = null;
      try {
        execution = runtimeService.createExecutionQuery().processInstanceId(pid)
            .activityId("RECV_WLFK").singleResult();
      } catch (Throwable t) {
        continue;
      }
      // 如果当前执行对象为空则表示流程已经处理
      if (execution == null) {
        continue;
      }
      // 检查SAP发货信息，调用信号
      List<OmOrderDeliveredV> allDeliveryItems =
          orderservice.getOrderDeliveryItemsByOrderId(order.getId());
      BigDecimal result = BigDecimal.ZERO;
      for (OmOrderDeliveredV di : allDeliveryItems) {
        String numString = di.getNum();
        BigDecimal num = new BigDecimal(numString);
        result = result.add(num);
      }
      // 检查发货数量
      if (result.compareTo(BigDecimal.ZERO) <= 0) {
        continue;
      }
      order.setStates("7"); // 设置状态为已发货
      orderservice.updateOrderHeader(order);

      // 更新调拨单状态
      if ("7".equals(order.getOrderType()) && StringUtils.isNotEmpty(order.getAttribute13())) {
        OmOrderHeadersAll transferOrder = new OmOrderHeadersAll();
        transferOrder.setId(Long.parseLong(order.getAttribute13()));
        transferOrder.setStates("7");
        orderservice.updateOrderHeader(transferOrder);
      }
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("recvCount", result);
      // 如果存在特殊订单的工厂发货流程，则说明当前为特殊订单的物流追加
      String id = execution.getId();
      runtimeService.signal(id, params);
    }
    orderutilMapper.updateTransferStatesWhenDelivery();

  }

  /**
   * 订单退货检查
   */
  // @Scheduled(cron = "0 0/1 * * * ?")
  public void checkReturn() {
    List<OmOrderHeadersAll> orders = orders();
    for (OmOrderHeadersAll order : orders) {
      List<Invoice> invoice = utilService.orderInvoices(Long.parseLong(order.getSapOrderId()));
      accountChange(invoice, order);
    }

  }

  private void accountChange(List<Invoice> invoices, OmOrderHeadersAll order) {
    BigDecimal sum = new BigDecimal("0");
    for (Invoice invoice : invoices) {
      if (invoice.getAmt().compareTo(new BigDecimal("0")) == -1) {
        sum = sum.add(invoice.getAmt());
      }
    }

    // 查找账户
    AjaxDTO accountsDto =
        utilService.getCustomerAccount(order.getMerchCustId(), order.getOrganizationId());
    CMerchCustAccount account = (CMerchCustAccount) accountsDto.getRows().get(0);
    CMerchCustAccount updateAcc = new CMerchCustAccount();
    updateAcc.setId(account.getId());
    updateAcc.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()).add(sum.abs()));

    adjust(order, sum, "1");
    accountLog(order, sum, "1");
    changeCamt("1", order.getMerchCustId(), order.getOrganizationId(), sum.abs());

    order.setStates("10");
    orderMapper.updateByPrimaryKey(order);
  }

  private void adjust(OmOrderHeadersAll order, BigDecimal sum, String accountType) {
    CMerchCustAdjust adjust = new CMerchCustAdjust();
    adjust.setOrderId(order.getId());
    adjust.setMerchCustId(order.getMerchCustId());
    adjust.setOrganizationId(order.getOrganizationId());
    adjust.setCreateTs(new Date());
    adjust.setCreateOid(0L);
    adjust.setAmt(sum);
    adjust.setType("2");// 订单调整
    adjust.setAccountType(accountType);
    adjust.setReason("1");
    adjust.setStates("1");
    adjustMapper.insert(adjust);
  }

  private void accountLog(OmOrderHeadersAll order, BigDecimal sum, String accountType) {
    CMerchCustAccountLog accountLog = new CMerchCustAccountLog();
    accountLog.setAccountType(accountType);
    accountLog.setdAmt(sum.abs());
    accountLog.setCreateTs(new Date());
    accountLog.setMerchCustId(order.getMerchCustId());
    accountLog.setOrderId(order.getId());
    accountLog.setOrganizationId(order.getOrganizationId());
    accountLog.setType("3"); // 调整
    accountLog.setPeriod(DateUtil.periodNow());
    accountLogMapper.insert(accountLog);
  }

  private void changeCamt(String accountType, Long merchid, String orgid, BigDecimal dAmt) {
    CMerchCustBalancesExample balanceEx = new CMerchCustBalancesExample();
    Criteria param = balanceEx.createCriteria();
    param.andMerchCustIdEqualTo(merchid);
    param.andOrganizationIdEqualTo(orgid);
    param.andAccountTypeEqualTo(accountType);
    param.andPeriodEqualTo(DateUtil.periodNow());
    List<CMerchCustBalances> balances = balanceMapper.selectByExample(balanceEx);
    if (!balances.isEmpty()) {
      CMerchCustBalances balance = balances.get(0);
      balance.setdAmt(balance.getdAmt().add(dAmt));
      balanceMapper.updateByPrimaryKey(balance);
    }
  }

  private List<OmOrderHeadersAll> orders() {
    OmOrderHeadersAllExample ex = new OmOrderHeadersAllExample();
    ex.createCriteria().andOrderTypeEqualTo("3").andStatesNotEqualTo("10");
    return orderMapper.selectByExample(ex);
  }

  private List<OmOrderHeadersAll> waitSendOrders() {
    OmOrderHeadersAllExample ex = new OmOrderHeadersAllExample();
    ex.createCriteria().andOrderTypeNotEqualTo("5").andStatesEqualTo("5")
        .andCreateTsGreaterThan(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(90)));
    return orderMapper.selectByExample(ex);
  }
}
