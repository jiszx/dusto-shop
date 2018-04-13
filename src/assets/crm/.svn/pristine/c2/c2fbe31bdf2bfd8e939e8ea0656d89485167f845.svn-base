package com.hhnz.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.impl.OrganizationServiceImpl;

public class Orgs {
  private static Logger logger = LoggerFactory.getLogger(Orgs.class);

  private static Map<String, String> orgs = orgs();

  private Orgs() {}

  private static Map<String, String> orgs() {
    Builder<String, String> builder = ImmutableMap.builder();
    OrganizationServiceImpl orgService =
        ApplicationContextUtil.getBean(OrganizationServiceImpl.class);
    try {
      List<CrmSalesOrganization> organizations = orgService.findAll();
      for (CrmSalesOrganization org : organizations) {
        builder.put(org.getId(), org.getName());
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return builder.build();
  }

  public static synchronized void refresh() {
    orgs = orgs();
  }

  public static String orgFullName(String orgid) {
    return orgFullName(orgid, 10);
  }

  /**
   * 通过销售组织id获取自顶向下的组织，如：调味品事业部-食用盐-销售部-应城益盐堂
   * 
   * @param orgid 销售组织id
   * @param count 前缀个数
   * @return
   */
  public static String orgFullName(String orgid, int count) {
    Preconditions.checkNotNull(orgid);
    Preconditions.checkArgument(count >= 0);
    StringBuilder builder = new StringBuilder();
    for (int i = 1; i <= orgid.length() && count > 0; i++) {
      if (orgs.containsKey(orgid.substring(0, i))) {
        builder.append(orgs.get(orgid.substring(0, i))).append("-");
        count--;
      }
    }
    return builder.toString();
  }

}
