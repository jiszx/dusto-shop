package com.hhnz.process.task.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;

/**
 * Created by yang on 2016-8-19.
 */
@Service("orderWithAccountTask")
@Transactional
public class OrderWithAccountTask implements JavaDelegate {

  @Resource
  private OrderUtilMapper orderUtilMapper;
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
    Long id = Long.parseLong(delegateExecution.getVariable("key").toString());// 订单号

    Map<String, Object> param = new HashMap<>();
    param.put("id", id);
    OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(id);
    List<OmOrderSpilts> deliverys = orderUtilMapper.findDeliverys(param);

    OmOrderLinesAllExample ex = new OmOrderLinesAllExample();
    ex.createCriteria().andHeaderIdEqualTo(id);
    List<OmOrderLinesAll> lines = orderLineMapper.selectByExample(ex);

    Long crashAmt = 0L;
    Long hbAmt = 0L;
    for (OmOrderLinesAll line : lines) {
      List<OmOrderSpilts> lineDeliberys = findSplite(deliverys, line);
      int lineCrashNum = line.getNum() != null ? line.getNum().intValue() : 0;
      int lineHbNum = line.getHbNum() != null ? line.getHbNum().intValue() : 0;
      int linePolicyNum = 0;
      if(line.getPolicyDiscount()!=null && line.getPolicyDiscount().length()>2){
        linePolicyNum = Integer.parseInt(line.getPolicyDiscountIntensity());
      }
      int crashDeliveryNum = amtNum(lineDeliberys, "1");
      int hbDeliveryNum = amtNum(lineDeliberys, "2");
      int policyDeliveryNum = amtNum(lineDeliberys, "3");
      line.setRetrunHbNum(new BigDecimal(lineHbNum - hbDeliveryNum));
      line.setReturnNum(new BigDecimal(lineCrashNum - crashDeliveryNum));
      line.setReturnPolicyNum(new BigDecimal(linePolicyNum - policyDeliveryNum));

      crashAmt += (line.getOrderPrice().multiply(new BigDecimal(lineCrashNum - crashDeliveryNum))
          .longValue());
      hbAmt +=
          (line.getOrderPrice().multiply(new BigDecimal(lineHbNum - hbDeliveryNum)).longValue());
      orderLineMapper.updateByPrimaryKey(line);
    }
    order.setReturnCashAmt(new BigDecimal(crashAmt));
    order.setReturnHbAmt(new BigDecimal(hbAmt));
    orderHeaderMapper.updateByPrimaryKeySelective(order);

    // 查找账户
    AjaxDTO accountsDto =
        utilService.getCustomerAccount(order.getMerchCustId(), order.getOrganizationId());
    CMerchCustAccount account = (CMerchCustAccount) accountsDto.getRows().get(0);
    CMerchCustAccount updateAcc = new CMerchCustAccount();
    updateAcc.setId(account.getId());
    updateAcc.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()).add(new BigDecimal(crashAmt)));
    updateAcc.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()).add(new BigDecimal(hbAmt)));
    int result = accountMapper.updateByPrimaryKeySelective(updateAcc);

    // 记录
    if (crashAmt > 0) {
      CMerchCustAdjust adjust = new CMerchCustAdjust();
      adjust.setOrderId(id);
      adjust.setMerchCustId(account.getMerchCustId());
      adjust.setOrganizationId(account.getOrganizationId());
      adjust.setCreateTs(new Date());
      adjust.setCreateOid(0L);
      adjust.setAmt(new BigDecimal(crashAmt));
      adjust.setType("2");// 订单调整
      adjust.setAccountType("1");
      adjust.setReason("1");
      adjust.setStates("1");
      adjustMapper.insert(adjust);
      CMerchCustAccountLog accountLog = new CMerchCustAccountLog();
      accountLog.setAccountType("1");
      accountLog.setdAmt(new BigDecimal(crashAmt));
      accountLog.setCreateTs(new Date());
      accountLog.setMerchCustId(order.getMerchCustId());
      accountLog.setOrderId(id);
      accountLog.setOrganizationId(order.getOrganizationId());
      accountLog.setType("4"); // 订单调整
      accountLog.setPeriod(DateUtil.periodNow());
      accountLogMapper.insert(accountLog);

      changeCamt("1", order.getMerchCustId(), order.getOrganizationId(), new BigDecimal(crashAmt));
    }
    if (hbAmt > 0) {
      CMerchCustAdjust adjust = new CMerchCustAdjust();
      adjust.setOrderId(id);
      adjust.setMerchCustId(account.getMerchCustId());
      adjust.setOrganizationId(account.getOrganizationId());
      adjust.setCreateTs(new Date());
      adjust.setCreateOid(0L);
      adjust.setAmt(new BigDecimal(hbAmt));
      adjust.setType("2");// 订单调整
      adjust.setAccountType("2");
      adjust.setReason("1");
      adjust.setStates("1");
      adjustMapper.insert(adjust);
      CMerchCustAccountLog accountLog = new CMerchCustAccountLog();
      accountLog.setAccountType("2");
      accountLog.setdAmt(new BigDecimal(hbAmt));
      accountLog.setCreateTs(new Date());
      accountLog.setMerchCustId(order.getMerchCustId());
      accountLog.setOrderId(id);
      accountLog.setOrganizationId(order.getOrganizationId());
      accountLog.setType("4"); // 调整
      accountLog.setPeriod(DateUtil.periodNow());
      accountLogMapper.insert(accountLog);

      changeCamt("2", order.getMerchCustId(), order.getOrganizationId(), new BigDecimal(hbAmt));
    }

  }

  public int amtNum(List<OmOrderSpilts> splites, String type) {
    if (splites == null) {
      return 0;
    }
    int result = 0;
    for (OmOrderSpilts splite : splites) {
      if (type.equals(splite.getType())) {
        result += splite.getNum().intValue();
      }
    }
    return result;
  }

  public List<OmOrderSpilts> findSplite(List<OmOrderSpilts> splites, OmOrderLinesAll line) {
    List<OmOrderSpilts> result = new ArrayList<>();
    for (OmOrderSpilts splite : splites) {
      if (line.getId().equals(splite.getLineId())
          && line.getHeaderId().equals(splite.getHeaderId())) {
        result.add(splite);
      }
    }
    return result;
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



}
