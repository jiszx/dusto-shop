package com.hhnz.process.task.combination;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.combination.service.CombinationApplyService;

@Service("combinationApplyAuditServie")
@Transactional
public class CombinationApplyAduitService implements JavaDelegate {
	
	@Resource
	private  CombinationApplyService service;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long id = (Long) execution.getVariable("key");//调整编号
		int flag = (int) execution.getVariable("FLAG");//0 驳回 1通过
		if(flag==0){
			this.service.updateApplyStates(id,"4");
		}else{
			this.service.updateApplyStates(id,"3");
		}
	}

}
