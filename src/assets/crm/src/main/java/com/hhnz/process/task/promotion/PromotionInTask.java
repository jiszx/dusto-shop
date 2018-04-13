package com.hhnz.process.task.promotion;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.promotion.service.PromotionInvService;

@Service("promotionInServie")
@Transactional
public class PromotionInTask implements JavaDelegate {
	
	/*@Resource
	private  CrmPromotionLogMapper  logmapper;*/
	
	@Resource
	private PromotionInvService  service;
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//入库编号
		int flag = (int) delegateExecution.getVariable("FLAG");//0 驳回 1通过
		//CrmPromotionLog  promotionIn = this.logmapper.selectByPrimaryKey(id);
		if(flag==0){
			this.service.updateStates(id, "4");
		}else{
			this.service.updateStates(id, "3");
		}
	}

}
