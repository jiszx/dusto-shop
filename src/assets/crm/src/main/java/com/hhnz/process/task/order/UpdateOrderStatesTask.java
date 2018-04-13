package com.hhnz.process.task.order;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("{updateStateTask}")
public class UpdateOrderStatesTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// TODO Auto-generated method stub
		//如果有发货信息，则修改订单状态为已发货
		//如果无，则标记订单为取消订单
		
	}

}
