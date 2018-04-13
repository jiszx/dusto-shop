package com.hhnz.task.controller;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderHeadersAllExample;
import com.hhnz.process.service.IProcessService;

@Service
public class InvoiceVerify {
  @Resource
  private OmOrderHeadersAllMapper orderMapper;
  @Resource
  private OrderUtilMapper orderutilMapper;
  @Resource
  private TaskService taskService;
  @Autowired
  private IProcessService processService;
  
//  @Scheduled(cron = "0 0/1 * * * ?")
  public void invoiceVerify() {
    List<OmOrderHeadersAll> orders = orders();
    for(OmOrderHeadersAll order:orders){
      Object sendNum = taskService.getVariable(order.getAttribute1(), "sendCount");
      if(sendNum==null){
        continue;
      }
      boolean isStart = isStartProcess(order.getSapOrderId(), (int)sendNum);
      if(isStart){
        // TODO: 开启流程
      }
    }
    
    
  }
  
  private boolean isStartProcess(String sapOrderid, int count){
    int invoiceNumTotal = orderutilMapper.orderInvoiceNumTotal(sapOrderid);
    if(invoiceNumTotal==count){
      return true;
    }
    return false;
  }
  
  private List<OmOrderHeadersAll> orders(){
    OmOrderHeadersAllExample ex = new OmOrderHeadersAllExample();
    ex.createCriteria().andStatesGreaterThanOrEqualTo("3");
    return orderMapper.selectByExample(ex);
  }
}
