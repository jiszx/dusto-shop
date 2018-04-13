package com.hhnz.process.task.order;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.MoreObjects;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.util.ApplicationContextUtil;

/**
 * Created by yang on 2016-8-19. 订单状态处理
 */
@Service("orderStateTask")
@Transactional
public class OrderStateTask implements JavaDelegate {
  @Resource
  private OmOrderHeadersAllMapper orderHeaderMapper;
  @Resource
  private OrderUtilService utilService;
  @Resource
  private  CustomerStockService  customerStockService;
  @Resource
  private OrderService orderservice;

  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {
    Long id = Long.parseLong(delegateExecution.getVariable("key").toString());// 订单号或批次号
    String orderType = (String) delegateExecution.getVariable("type");
    String ids = (String) delegateExecution.getVariable("ids");
    BigDecimal flag = (BigDecimal) delegateExecution.getVariable("recvCount");
    Integer confirmFlag = (Integer) delegateExecution.getVariable("FLAG");
    flag = MoreObjects.firstNonNull(flag, BigDecimal.ONE);
    
    String state = "8";
    if(flag.compareTo(BigDecimal.ZERO) == 0 && "1".equals(orderType)){
      state = "4"; // 驳回
    }else if(flag.compareTo(BigDecimal.ZERO) == 0){
      state = "9"; // 取消订单
    }else if(flag.compareTo(BigDecimal.ZERO) > 0){
      state = "8";
    }else{
      
    }
    
    if("1".equals(orderType)){
      for(String orderid:ids.split(",")){
        OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(Long.parseLong(orderid));
        order.setStates(state);
        orderHeaderMapper.updateByPrimaryKey(order);
      }
    }else{
      OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(id);
      if("5".equals(order.getOrderType())||"8".equals(order.getOrderType())){//调拨单流程完结生成转化订单
    	  if(confirmFlag != null && confirmFlag == 1){
    		  TEmployee user = ApplicationContextUtil.getCurrentUser();
    		  customerStockService.updateStates(id, null, user);
    		  
    		  OmOrderHeadersAll header = this.customerStockService.selectByPrimaryKey(id);
    		  this.orderservice.orderAudit(Long.parseLong(header.getAttribute13()),"2", "7");
    		  orderservice.startProcess(user,Long.parseLong(header.getAttribute13()),"7");
    	  }else{
    		  OmOrderHeadersAll o = orderHeaderMapper.selectByPrimaryKey(id);
    	      o.setStates("1");
    	      orderHeaderMapper.updateByPrimaryKey(o);
    	  }
      }else{
    	  if(confirmFlag != null && confirmFlag == 0){
    		  order.setStates("1");
    	  }else{
    		  order.setStates(state);
    	  }
    	  orderHeaderMapper.updateByPrimaryKey(order);
      }
    }
    
  }
}
