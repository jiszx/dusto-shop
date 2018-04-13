package com.hhnz.process.listerner;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.mapper.TTimeLineMapper;
import com.hhnz.crm.model.TTimeLine;

/**
 * Created by yang on 2016-8-23.
 */
@Service("processStartListerner")
@Transactional
public class ProcessStartListener implements ExecutionListener {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private TTimeLineMapper mapper;
	
	
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        //System.out.println(delegateExecution.getVariable("key"));
    	SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat tsf =new SimpleDateFormat("HH:mm");
        String name=this.repositoryService.createProcessDefinitionQuery().processDefinitionId(delegateExecution.getProcessDefinitionId()).singleResult().getName();
        TTimeLine line = new TTimeLine();
        line.setCategory(name);
        line.setCategoryIcon("icon icon-eye-open");
        /*line.setContentDesc("描述信息");
        line.setContentUrl("ddddd");*/
        if(delegateExecution.getVariable("name") !=null){
        	line.setTitle((String)delegateExecution.getVariable("name"));
        }
        line.setUserId((String)delegateExecution.getVariable("startUser"));
        line.setCreateTs(sf.format(new Date()));
        line.setTimeStr(tsf.format(new Date()));
        this.mapper.insert(line);
    }
}
