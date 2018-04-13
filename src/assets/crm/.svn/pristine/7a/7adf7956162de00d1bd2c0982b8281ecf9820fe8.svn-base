package com.hhnz.process.task.order;

import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.order.service.OrderUtilService;

@Transactional
@Service("checkTransportWeight")
public class CheckTransportWeight implements JavaDelegate {
  private static Logger logger = LoggerFactory.getLogger(CheckTransportWeight.class);

  @Autowired
  private OrderUtilService utilService;
  @Resource
  private  CustomerStockService  allocationservice;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Long id = Long.parseLong(execution.getVariable("key").toString()); // 订单号

    boolean result = utilService.canTransfer(id);
    if(result){
    	execution.setVariable("MODEL_LIMIT", 1);
    	execution.setVariable("FLAG", 1);
    }else{
    	execution.setVariable("MODEL_LIMIT", 0);
    	execution.setVariable("FLAG", 0);
    }
    //验证资金
    Map<String,Object> flag = this.allocationservice.ValidateAmtAndNum(id);
    if(StringUtils.equals((String)flag.get("type"), "S")){
    	execution.setVariable("MODEL_LIMIT", 1);
    	execution.setVariable("FLAG", 1);
    }else{
    	execution.setVariable("MODEL_LIMIT", 0);
    	execution.setVariable("FLAG", 0);
    }
  }


}
