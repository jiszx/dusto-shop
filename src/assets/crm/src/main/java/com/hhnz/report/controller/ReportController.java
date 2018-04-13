package com.hhnz.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {

  // 销售订单明细表
  @RequestMapping("/salesdetails.html")
  public String indexPage() throws Exception {
    return "report/orderdetails";
  }

  // 客户资金流水表
  @RequestMapping("/custdetails.html")
  public String custdetails() throws Exception {
    return "report/custAcccountDetails";
  }

  // 费用池流水表
  @RequestMapping("/costdetails.html")
  public String costdetails() throws Exception {
    return "report/costDetails";
  }

  // 销售政策流水表
  @RequestMapping("/policydetails.html")
  public String policydetails() throws Exception {
    return "report/policyDetails";
  }

  // 调拨单明细
  @RequestMapping("/transferdetails.html")
  public String transferOderDetail() throws Exception {
    return "report/transferOrderDetail";
  }
}
