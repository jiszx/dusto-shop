package com.hhnz.process.task.customer;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.jco.business.customer.CustomerRFC;
import com.hhnz.jco.business.customer.InputDTO;
import com.hhnz.jco.business.customer.TempCustomerRFCCallback;
import com.hhnz.jco.business.vendor.DefaultVendorRFCCallback;
import com.hhnz.jco.business.vendor.VendorRFC;

/**
 * Created by yang on 2016-8-18.
 */
@Service("customerSendSAPTaskService")
@Transactional
public class CustomerSendSAPTaskService implements JavaDelegate {
	@Autowired
	private CustomerRFC customerRFC;
	@Autowired
	private VendorRFC vendorRFC;
	@Autowired
	private ICustomerService customerService;
	
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//客户编号
        CMerchCustBase custBase = customerService.findSimpleCustBaseById(id);
        if(StringUtils.isBlank(custBase.getProcessId())){
        	custBase.setProcessId(delegateExecution.getProcessInstanceId());
        }
        //仓储服务商审批流程不推送SAP
        if("7".equals(custBase.getCustType()) || "70".equals(custBase.getCustType())  || "8".equals(custBase.getCustType())){
        	//处理SAP传入参数
        	com.hhnz.jco.business.vendor.InputDTO vendor = vendorRFC.constructInputParam(id);
        	//执行客户接口
        	vendorRFC.executeInThread(vendor, DefaultVendorRFCCallback.class.getSimpleName());
        }else{
        	//处理SAP传入参数
        	InputDTO input = customerRFC.constructInputParam(id);
        	//执行客户接口
        	customerRFC.executeInThread(input, TempCustomerRFCCallback.class.getSimpleName());
        }
    }
}
