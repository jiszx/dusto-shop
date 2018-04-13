package com.hhnz.process.task.change.customer;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.pub.enu.ChangeStatus;
import com.hhnz.pub.model.Change;
import com.hhnz.pub.service.IChangeService;
import com.hhnz.util.BeanUtils;

/**
 * @author: chaoyang.ren
 * @date:Jan 6, 2017
 * @time:10:49:37 AM
 * @email:chaoyang.ren@foxmail.com
 */
@Service("customerChangeTaskService")
@Transactional
public class CustomerChangeTaskService implements JavaDelegate {
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IChangeService changeService;
	
	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
		Long id = Long.valueOf(delegateExecution.getVariable("key").toString());//客户编号
        Long changeId = Long.valueOf(delegateExecution.getVariable("changeId").toString());//变更编号
        CMerchCustBase custBase = customerService.findSimpleCustBaseById(id);
        CMerchCustBase changeVo = changeService.getChangeBean(CMerchCustBase.class, changeId);
        BeanUtils.copyPropertiesIgnoreNil(changeVo, custBase);
        Change change = changeService.findById(changeId);
        int flag = (int) delegateExecution.getVariable("FLAG");//0 驳回 1通过
        if(flag == 1){
	        change.setStat(ChangeStatus.VALID);
	        customerService.save(custBase);
        }else{
        	change.setStat(ChangeStatus.DRAFT);
        }
        changeService.updateChange(change);
    }
	
}
