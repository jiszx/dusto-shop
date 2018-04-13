package com.hhnz.process.task.salepolicy;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.salepolicy.mapper.OmPolicyHeadersMapper;
import com.hhnz.salepolicy.model.OmPolicyHeaders;

@Service("salePolicyServie")
@Transactional
public class SalePolicyApplyTask implements JavaDelegate {
	@Resource
	private OmPolicyHeadersMapper headmapper;
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//入库编号
		int flag = (int) delegateExecution.getVariable("FLAG");//0 驳回 1通过
		//CrmPromotionLog  promotionIn = this.logmapper.selectByPrimaryKey(id);
		OmPolicyHeaders  policy = this.headmapper.selectByPrimaryKey(id);
		if(flag==0){
			policy.setStates("4");
		}else{
			policy.setBalanceAmt(policy.getAmt());
			policy.setStates("3");
		}
		this.headmapper.updateByPrimaryKeySelective(policy);
	}
}
