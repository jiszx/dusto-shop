package com.hhnz.process.task.order;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.order.service.OrderMailService;

/**
 * 订单物流邮件通知
 * @author: chaoyang.ren
 * @date:Apr 6, 2017
 * @time:9:58:23 AM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Service("orderNoticeMailService")
@Transactional
public class OrderNoticeMailService implements JavaDelegate {
	@Autowired
	private OrderMailService orderMailService;
	
	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//客户编号
        orderMailService.logisticsNotice(id);
	}
}
