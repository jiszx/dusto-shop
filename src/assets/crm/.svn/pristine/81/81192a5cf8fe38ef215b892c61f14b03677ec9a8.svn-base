package com.hhnz.jco.business.vendor;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;


/**
 * 默认客户rfc处理参数
 */
@Component("DefaultVendorRFCCallback")
public class DefaultVendorRFCCallback implements RFCCallback{
	private static final Log LOG = LogFactory.getLog(DefaultVendorRFCCallback.class);
	@Autowired
	private ICustomerService customerService;
	@Resource
    private RuntimeService runtimeService;
	
	
	@Override
	public void errorCallBack(CallbackParam result) {
		CMerchCustBase custBase = customerService.findSimpleCustBaseById(result.getId());
		processKeepOn(custBase.getProcessId(), false, "接口传输失败！"+result.getResult().getMESSAGE());
		LOG.warn("CustomerSendSAP RFC execute failed!");
	}

	@Override
	public void successCallBack(CallbackParam result) {
		CMerchCustBase custBase = customerService.findSimpleCustBaseById(result.getId());
		custBase.setSapCustomerId(result.getSapCode());
		customerService.save(custBase);
		processKeepOn(custBase.getProcessId(), true, StringUtils.EMPTY, result.getSapCode());
		LOG.info("CustomerSendSAP RFC execute success!");
	}
	
	/**
	 * 下一步流程
	 * @author: chaoyang.ren 
	 * @date:2016年9月28日  上午11:07:21
	 * @param processId
	 * @param isSuccess
	 * @param sapId
	 */
	private void processKeepOn(String processId, boolean isSuccess, String msg, String... sapId){
		try {
			Execution execution = runtimeService.createExecutionQuery().processInstanceId(processId).activityId("RECV_SAP").singleResult();
			if(execution != null){
				this.runtimeService.setVariable(processId,"MSG",msg);
				this.runtimeService.setVariable(processId,"FLAG",isSuccess?1:0);
				if(isSuccess){
					String id = execution.getId();
					this.runtimeService.setVariable(processId,"sapId",sapId[0]);
					this.runtimeService.signal(id);
				}
			}else{
				LOG.warn("仓储服务商推送SAP后未触发客户创建的'RECV_SAP'流程");
			}
		} catch (Exception e) {
			LOG.error("仓储服务商推送SAP后未触发客户创建的'RECV_SAP'流程",e);
		}
	}
}
