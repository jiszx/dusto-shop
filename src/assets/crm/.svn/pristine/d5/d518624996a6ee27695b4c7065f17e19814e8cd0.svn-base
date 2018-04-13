package com.hhnz.order.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;

@Controller
@RequestMapping("/order/transfer")
public class TransferOrderController {
  @Resource
  private OrderUtilService utilService;

  @RequestMapping(value = "transfer.html", method = RequestMethod.GET)
  public String addOrderPage() {
    return "order/addTransfer";
  }
  
  @RequestMapping(value = "transferOrder.html", method = RequestMethod.GET)
  public String transferOrder() {
    return "order/transfer";
  }
  
  @ResponseBody
  @RequestMapping(value = "/cancel", method = RequestMethod.POST)
  public String cancelOrder(long headerid){
    return utilService.cancelTransferOrder(headerid);
  }

}
