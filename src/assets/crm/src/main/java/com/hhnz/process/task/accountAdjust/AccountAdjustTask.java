package com.hhnz.process.task.accountAdjust;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.service.IAdjustAccountService;

@Service("accountadjustTask")
@Transactional
public class AccountAdjustTask  implements JavaDelegate {
	@Resource
	private IAdjustAccountService service;
	
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//入库编号
		int flag = (int) delegateExecution.getVariable("FLAG");//0 驳回 1通过
		//CrmPromotionLog  promotionIn = this.logmapper.selectByPrimaryKey(id);
		if(flag==0){
			this.service.audit(id, "4");
		}else{
			this.service.audit(id, "3");
		}
		
	}

}
