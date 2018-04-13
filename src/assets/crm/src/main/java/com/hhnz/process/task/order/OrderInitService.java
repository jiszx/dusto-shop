package com.hhnz.process.task.order;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.model.OmOrderHeadersAll;

/**
 * 订单流程初始化
 * @author: chaoyang.ren
 * @date:Apr 6, 2017
 * @time:9:58:23 AM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Service("orderInitService")
@Transactional
public class OrderInitService implements JavaDelegate {
	@Resource
	private OmOrderHeadersAllMapper orderHeadMapper;
	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());
        OmOrderHeadersAll updateStatus = new OmOrderHeadersAll();
        updateStatus.setId(id);
        updateStatus.setStates("2");
        updateStatus.setAttribute1(delegateExecution.getProcessInstanceId());
        orderHeadMapper.updateByPrimaryKeySelective(updateStatus);
	}
}
