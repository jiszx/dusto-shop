package com.hhnz.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hhnz.api.cache.CacheService;
import com.hhnz.message.mail.MailSender;
import com.hhnz.rmi.db.model.customer.CustInvWarning;
import com.hhnz.rmi.db.model.customer.CustStation;
import com.hhnz.rmi.db.model.customer.Customer;
import com.hhnz.rmi.db.model.customer.enu.InvWarningType;
import com.hhnz.rmi.db.model.inventory.CustInventory;
import com.hhnz.rmi.db.repository.customer.CustInvWarningItemRepository;
import com.hhnz.rmi.db.repository.customer.CustInvWarningRepository;
import com.hhnz.rmi.db.repository.customer.CustStationRepository;
import com.hhnz.rmi.db.repository.customer.CustomerRepository;
import com.hhnz.rmi.db.repository.inventory.CustInventoryRepository;
import com.hhnz.rmi.db.util.DynamicSpecifications;
import com.hhnz.rmi.db.util.SearchFilter;

@Service
public class InvWarningTask {
  private static Logger logger = LoggerFactory.getLogger(InvWarningTask.class);

  private static String lockKey = "lockTag-checkInv";

  @Resource
  private CustInventoryRepository custInventoryRepository;
  @Resource
  private CustInvWarningRepository custInvWarningRepository;
  @Resource
  private CustInvWarningItemRepository custInvWarningItemRepository;
  @Resource
  private CustomerRepository customerRepository;
  @Resource
  private CustStationRepository custStationRepository;
  @Resource
  private MailSender mailSender;
  @Resource
  private CacheService cacheService;

  @Scheduled(cron = "0 0 5 * * ?")
  public void checkInv() {
    boolean result = cacheService.putIfAbsent(lockKey, "1");
    if (result == false) {
      return;
    }
    cacheService.getAndExpire(lockKey);
    List<CustInvWarning> list = custInvWarningRepository.findByIsWarning(true);
    for (CustInvWarning warn : list) {
      Map<String, Object> searchParams = new HashMap<>();
      searchParams.put("EQ_merchId", warn.getCustomerId());
      Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
      Specification<CustInventory> spec =
          DynamicSpecifications.bySearchFilter(filters.values(), CustInventory.class);
      List<CustInventory> invs = custInventoryRepository.findAll(spec);
      String content = content(invs, warn.getCustomer());

      sendMail(warn, content);

    }
    cacheService.delete(lockKey);
  }

  private void sendMail(CustInvWarning invWarn, String content) {
    if (content == null) {
      return;
    }
    Customer customer = customerRepository.findOne(invWarn.getCustomerId());
    if (StringUtils.isBlank(customer.getEmail())) {
      logger.error("发送缺货邮件失败，邮件地址为空！");
      return;
    }
    String recipient = null;
    CustStation custStation = custStationRepository.findByMerchCustId(customer.getId());
    if (custStation == null || custStation.getStation() == null
        || custStation.getStation().getSalesrep() == null
        || StringUtils.isBlank(custStation.getStation().getSalesrep().getEmail())) {
      logger.error("抄送对应销售人员的邮件失败，无法获取对应销售的邮件地址！");
    } else {
      recipient = custStation.getStation().getSalesrep().getEmail();
    }
    mailSender.send(content, "物料缺货提醒", customer.getEmail(), recipient);
  }

  private String content(List<CustInventory> invs, Customer customer) {
    StringBuilder builder = new StringBuilder();
    builder.append("客户").append(customer.getName()).append('(').append(customer.getSapCustomerId())
        .append(')').append("的物料：");
    boolean hasMaterial = false;
    for (CustInventory inv : invs) {
      if (hasMaterial == false && isNeedNotice(inv)) {
        builder.append(inv.getMaterialBase().getMaterialName()).append('(')
            .append(inv.getMaterialBase().getSapId()).append(')');
        hasMaterial = true;
      } else if (isNeedNotice(inv)) {
        builder.append(',').append(inv.getMaterialBase().getMaterialName()).append('(')
            .append(inv.getMaterialBase().getSapId()).append(')');
      }
    }
    builder.append(" 库存告警.");
    return hasMaterial ? builder.toString() : null;
  }

  private boolean isNeedNotice(CustInventory inv) {
    return inv.getCustInvWarningItem() != null && inv.getMaterialBase() != null
        && InvWarningType.QUANT == inv.getCustInvWarningItem().getWarningType()
        && inv.getInvNum().compareTo(inv.getCustInvWarningItem().getTarget()) < 0;
  }

}
