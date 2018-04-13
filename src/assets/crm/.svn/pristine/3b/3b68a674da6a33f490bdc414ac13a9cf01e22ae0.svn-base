package com.hhnz.process.task.change.customer;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.pub.enu.ChangeStatus;
import com.hhnz.pub.model.Change;
import com.hhnz.pub.service.IChangeService;


/**
 * 默认客户rfc处理参数
 */
@Component("CustomerChangeRFCCallback")
@Transactional
public class CustomerChangeRFCCallback implements RFCCallback{
	private static final Log LOG = LogFactory.getLog(CustomerChangeRFCCallback.class);
	@Resource
    private RuntimeService runtimeService;
	@Resource
	private IChangeService changeService;
	
	@Override
	public void errorCallBack(CallbackParam result) {
		LOG.warn("CustomerSendSAP RFC execute failed!");
	}

	@Override
	public void successCallBack(CallbackParam result) {
		Change latestChange = changeService.getLatestChange(CMerchCustBase.class.getSimpleName(), result.getId().toString());
		if(ChangeStatus.SUBMITED.equals(latestChange.getStatEnu())){
			String processId = latestChange.getProcessId();
			processKeepOn(processId, true, result.getResult().getMESSAGE(), result.getSapCode());
			LOG.info("CustomerSendSAP RFC execute success!");
		}else{
			throw new RuntimeException("异常的客户状态！");
		}
	}
	
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
				LOG.warn("客户推送SAP后未触发'RECV_SAP'流程");
			}
		} catch (Exception e) {
			LOG.error("客户推送SAP后未触发'RECV_SAP'流程",e);
		}
	}
}
