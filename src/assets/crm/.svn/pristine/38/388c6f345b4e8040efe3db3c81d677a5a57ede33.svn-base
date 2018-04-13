package com.hhnz.process.listerner.task.user;

import com.hhnz.organization.mapper.TJobStationMapper;
import com.hhnz.organization.mapper.TJobStationVMapper;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 用户节点处理
 * Created by 杨 on 2016/12/8.
 */
@Component("userTaskListerner")
public class UserTaskListerner implements TaskListener {

    @Autowired
    private TJobStationMapper mapper;

    @Override
    public void notify(DelegateTask delegateTask) {
        // 获取默认的节点处理人
        //根据组织ID，从下往上寻找处理人
        //没有找到，不变动
        //找到，设置新的处理人
        String assignee = delegateTask.getAssignee();
        String orgId = delegateTask.getVariable("orgId",String.class);
        if(orgId !=null && StringUtils.hasLength(orgId)){
            List<String> names = this.mapper.findJobNamesById(orgId,assignee);
            if(names !=null && names.size()>0){
                delegateTask.setAssignee(names.get(0));
            }
        }
    }
}
