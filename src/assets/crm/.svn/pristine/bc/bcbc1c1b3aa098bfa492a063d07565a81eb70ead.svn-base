package com.hhnz.process.task.orderback;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
@Service("backOrderReject")
public class BackOrderReject implements JavaDelegate {
	@Resource
	private OmOrderHeadersAllMapper orderHeaderMapper;
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//订单号
		OmOrderHeadersAll  header = this.orderHeaderMapper.selectByPrimaryKey(id);
		int flag = (int) delegateExecution.getVariable("FLAG");// 0 驳回 1通过
		if (flag == 0) {
			header.setStates("3");
		}
		this.orderHeaderMapper.updateByPrimaryKeySelective(header);
	}

}
