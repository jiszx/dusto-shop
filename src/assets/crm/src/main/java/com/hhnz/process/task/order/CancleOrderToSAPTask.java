package com.hhnz.process.task.order;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.jco.business.order.InputDTO;
import com.hhnz.jco.business.order.OrderRFC;
import com.hhnz.order.service.OrderService;

/**
 * Created by yang on 2016-8-19.
 * 取消订单
 */
@Service("cancleOrderToSAPTask")
@Transactional(isolation = Isolation.SERIALIZABLE)
public class CancleOrderToSAPTask implements JavaDelegate {
	@Autowired
	private OrderRFC orderRFC;
	@Autowired
	private OrderService orderService;
	
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//订单号
        
        InputDTO input = orderRFC.constructInputParamForDel(id);
		orderRFC.executeInThread(input, null);
    }
}
