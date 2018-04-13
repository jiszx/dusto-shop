package com.hhnz.process.task.price;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import com.hhnz.customer.service.ICustomerPriceService;

@Service("pricebatchmaintainTask")
public class PricebatchmaintainTask implements JavaDelegate{
	
	@Resource
	private ICustomerPriceService priceService;
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//申请ID
		int flag = (int) delegateExecution.getVariable("FLAG");// 0 驳回 1通过
		String states =flag ==1?"3":"4";
		this.priceService.updateStates(id,states);
		if(flag ==1) {
			//审批通过，处理客户产品信息
			this.priceService.updateMerchProduct(id);
		}
	}

}
