package com.hhnz.process.task.change.customer;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.jco.business.customer.CustomerRFC;
import com.hhnz.jco.business.customer.InputDTO;
import com.hhnz.jco.business.vendor.VendorRFC;
import com.hhnz.pub.service.IChangeService;
import com.hhnz.util.BeanUtils;

/**
 * Created by yang on 2016-8-18.
 */
@Service("customerChangeSendSAPService")
@Transactional
public class CustomerChangeSendSAPService implements JavaDelegate {
	@Autowired
	private CustomerRFC customerRFC;
	@Autowired
	private VendorRFC vendorRFC;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IChangeService changeService;
	
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.valueOf(delegateExecution.getVariable("key").toString());//客户编号
        Long changeId = Long.valueOf(delegateExecution.getVariable("changeId").toString());//变更编号
        CMerchCustBase custBase = customerService.findCustBaseById(id);
        CMerchCustBase changeVo = changeService.getChangeBean(CMerchCustBase.class, changeId);
        
        BeanUtils.copyPropertiesIgnoreNil(changeVo, custBase);
        //仓储服务商审批流程不推送SAP
        if("7".equals(custBase.getCustType()) || "8".equals(custBase.getCustType())){
        	//处理SAP传入参数
        	com.hhnz.jco.business.vendor.InputDTO vendor = vendorRFC.constructInputParam(custBase);
        	//执行客户接口
        	vendorRFC.executeInThread(vendor, CustomerChangeRFCCallback.class.getSimpleName());
        }else{
        	//处理SAP传入参数
        	InputDTO input = customerRFC.constructInputParam(custBase);
        	//执行客户接口
        	customerRFC.executeInThread(input, CustomerChangeRFCCallback.class.getSimpleName());
        }
    }
}
