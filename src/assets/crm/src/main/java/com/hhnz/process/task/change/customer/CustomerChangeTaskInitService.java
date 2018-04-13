package com.hhnz.process.task.change.customer;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.pub.enu.ChangeStatus;
import com.hhnz.pub.model.Change;
import com.hhnz.pub.service.IChangeService;

/**
 * Created by yang on 2016-8-18.
 */
@Service("customerChangeTaskInit")
@Transactional
public class CustomerChangeTaskInitService implements JavaDelegate {
	@Autowired
	private IChangeService changeService;
	
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long changeId = Long.valueOf(delegateExecution.getVariable("changeId").toString());//变更编号
        String processInstanceId = delegateExecution.getProcessInstanceId();
        Change change = changeService.findById(changeId);
        change.setStat(ChangeStatus.SUBMITED);
        change.setProcessId(processInstanceId);
        changeService.updateChange(change);
    }
}
