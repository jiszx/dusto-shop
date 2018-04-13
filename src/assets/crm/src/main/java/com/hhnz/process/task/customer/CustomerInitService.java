package com.hhnz.process.task.customer;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.enu.CustomerBaseStateEnu;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;

/**
 * 客户流程初始化
 * @author: chaoyang.ren
 * @date:Jan 10, 2017
 * @time:11:13:36 AM
 * @email:chaoyang.ren@foxmail.com
 */
@Service("customerInitService")
@Transactional
public class CustomerInitService implements JavaDelegate {
	@Autowired
	private ICustomerService customerService;
	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//客户编号
        CMerchCustBase existedOne = customerService.findCustBaseById(id);
        existedOne.setProcessId(delegateExecution.getProcessInstanceId());
        existedOne.setStates(CustomerBaseStateEnu.SUBMITED.getCode());
        customerService.save(existedOne);
	}
}
