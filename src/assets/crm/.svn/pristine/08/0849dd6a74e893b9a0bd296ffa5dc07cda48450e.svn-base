package com.hhnz.process.task.costpool;

import com.hhnz.cost.mapper.CrmCostAdjustMapper;
import com.hhnz.cost.model.CrmCostAdjust;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by yang on 2016-8-23.
 */
@Service("costStateTask")
@Transactional
public class CostAdjustStateTask implements JavaDelegate {

    @Autowired
    private CrmCostAdjustMapper mapper;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());
        int flag = (int) delegateExecution.getVariable("FLAG");//0 驳回 1通过
        String status = "5";
        String processId = delegateExecution.getProcessInstanceId();
        Long checko = Long.parseLong(String.valueOf(delegateExecution.getVariable("userId")));
        Date check_ts = new Date();
        if(flag==0){
            status = "3";// 退回
        }
        CrmCostAdjust adjust = new CrmCostAdjust();
        adjust.setId(id);
        adjust.setStates(status);
        adjust.setAuditTs(check_ts);
        adjust.setAuditOid(checko);
        int res = this.mapper.updateByPrimaryKeySelective(adjust);
    }
}
