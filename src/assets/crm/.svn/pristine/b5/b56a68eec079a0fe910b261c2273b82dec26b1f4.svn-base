package com.hhnz.process.task.invAdjust;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customerInv.mapper.CMerchCustProductAdjustMapper;
import com.hhnz.customerInv.model.CMerchCustProductAdjust;
import com.hhnz.customerInv.service.CustomerInvAdjustService;

@Service("invAdjustAuditServie")
@Transactional
public class InvAdjustAuditServie implements JavaDelegate{
	@Resource
	private  CustomerInvAdjustService  adjustservice;
	
	@Resource
	private CMerchCustProductAdjustMapper  mapper;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long id = (Long) execution.getVariable("key");//调整编号
		int flag = (int) execution.getVariable("FLAG");//0 驳回 1通过
		CMerchCustProductAdjust adjust = this.mapper.selectByPrimaryKey(id);
		if(flag==0){
			adjust.setStates("4");
			this.adjustservice.updateStates(adjust);
		}else{
			adjust.setStates("3");
			this.adjustservice.updateStates(adjust);
		}
		
	}

}
