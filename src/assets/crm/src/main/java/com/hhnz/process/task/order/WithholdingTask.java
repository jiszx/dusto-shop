package com.hhnz.process.task.order;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yang on 2016-8-19.
 * 销售政策预扣款处理
 */
@Service("withholdingTask")
@Transactional(isolation = Isolation.SERIALIZABLE)
public class WithholdingTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
//        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//订单号
        delegateExecution.setVariable("WITHHOLD_FLAG",1); //1 成功 0 失败
    }
}
