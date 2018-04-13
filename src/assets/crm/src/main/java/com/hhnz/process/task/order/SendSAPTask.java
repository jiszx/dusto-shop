package com.hhnz.process.task.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.business.order.InputDTO;
import com.hhnz.jco.business.order.OrderRFC;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;

/**
 * Created by yang on 2016-8-19.
 */
@Service("sendSAPTask")
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class SendSAPTask implements JavaDelegate {
    private static Log log = LogFactory.getLog(OrderRFC.class);
	@Autowired
	private OrderRFC orderRFC;
	@Resource
	private OrderService orderservice;
	@Resource
	private OrderUtilService utilService;
	
	@Resource
	private CMerchCustBaseMapper merchmapper;
	
	@Resource
	private OmOrderHeadersAllMapper  ordermapper;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//订单号或批次号
        String orderType = (String) delegateExecution.getVariable("type");
        String ids = (String) delegateExecution.getVariable("ids");
        
        if("1".equals(orderType)){
          List<InputDTO> temp = new ArrayList<>();
          for(String orderid:ids.split(",")){
            OmOrderHeadersAll order = orderservice.getOrderBYid(Long.parseLong(orderid));
            log.info("order info:{}"+order.getAttribute9());
            if(StringUtils.isBlank(order.getAttribute1())){
              OmOrderHeadersAll updateOrder = new OmOrderHeadersAll();
              updateOrder.setId(order.getId());
              updateOrder.setAttribute1(delegateExecution.getProcessInstanceId());
              orderservice.updateOrderHeader(updateOrder);
            }
            InputDTO input = orderRFC.constructInputParam(order.getId());
            temp.add(input);
          }
          delegateExecution.setVariable("recvCount", BigDecimal.ONE);
          for (InputDTO input : temp) {
        	  orderRFC.executeInThread(input,null);
		  }
        }else{
          OmOrderHeadersAll order = orderservice.getOrderBYid(id);
          CMerchCustBase  merch = this.merchmapper.selectByPrimaryKey(order.getMerchCustId());
          CMerchCustBase  pmerch = new CMerchCustBase();
          if("6".equals(order.getOrderType())){
        	    pmerch = this.merchmapper.selectByPrimaryKey(merch.getPid());
          }
          if(StringUtils.isBlank(order.getAttribute1())){
            OmOrderHeadersAll updateOrder = new OmOrderHeadersAll();
            updateOrder.setId(order.getId());
            updateOrder.setAttribute1(delegateExecution.getProcessInstanceId());
            orderservice.updateOrderHeader(updateOrder);
          }
          
          if(isSpecialRetailOrder(order, pmerch)){
            InputDTO input = orderRFC.constructInputParam(id);
            orderRFC.executeInThread(input,null);
          }else if("6".equals(order.getOrderType()) && "70".equals(pmerch.getCustType())){        	  
        	  order.setStates("8");
        	  this.ordermapper.updateByPrimaryKeySelective(order);
          }else{
        	  InputDTO input = orderRFC.constructInputParam(id);
        	  orderRFC.executeInThread(input,null);
          }
        }
    }
    
    /**
     * 合作仓储服务商零售订单，当对应的合作盐业公司sap编码为1421时，推送sap
     * @param order
     * @param pmerch
     * @return
     */
    boolean isSpecialRetailOrder(OmOrderHeadersAll order, CMerchCustBase pmerch){
      if(pmerch.getPid()!=null && "6".equals(order.getOrderType()) && "70".equals(pmerch.getCustType())){
        CMerchCustBase  shipP = this.merchmapper.selectByPrimaryKey(pmerch.getPid());
        if(shipP != null && "1421".equals(shipP.getSapCustomerId())){
          return true;
        }
      }
      return false;
    }
    
}
