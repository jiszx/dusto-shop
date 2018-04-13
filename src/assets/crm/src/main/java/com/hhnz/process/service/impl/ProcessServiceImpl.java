package com.hhnz.process.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.organization.mapper.TJobUserVMapper;
import com.hhnz.organization.model.TJobUserV;
import com.hhnz.organization.model.TJobUserVExample;
import com.hhnz.process.mapper.VProcessMapper;
import com.hhnz.process.mapper.VTaskMapper;
import com.hhnz.process.model.VProcess;
import com.hhnz.process.model.VProcessExample;
import com.hhnz.process.model.VTask;
import com.hhnz.process.model.VTaskExample;
import com.hhnz.process.service.IProcessService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

/**
 * Created by yang on 2016-7-11.
 */
@Service
@Transactional
public class ProcessServiceImpl implements IProcessService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private VProcessMapper processMapper;

    @Autowired
    private VTaskMapper taskMapper;
    
    @Autowired
    private TJobUserVMapper jobMapper;

    @Override
    public AjaxDTO findMyProcess(Map<String, Object> map, AjaxDTO bean) throws Exception {
        Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        VProcessExample ex =new VProcessExample();
        ex.setPage(page);
        ex.setOrderByClause("start_time_ desc");
        VProcessExample.Criteria exc = ex.createCriteria();
        exc.andStartUserIdEqualTo(map.get("user"));
        if(map.containsKey("his")){
            exc.andEndTimeIsNotNull();
        }else{
        	exc.andEndTimeIsNull();
        }
        if(StringUtils.hasLength(bean.getSearch())){
            ex.createCriteria().andDefNameEqualTo(bean.getSearch());
        }
        List<VProcess> list = this.processMapper.selectByExample(ex);
        int total = this.processMapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public AjaxDTO findMyTask(Map<String,Object> param, AjaxDTO bean) throws Exception {
        Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        VTaskExample ex =new VTaskExample();
        ex.setPage(page);
        List users = new ArrayList<String>();
        TEmployee emp = (TEmployee) param.get("oper");
        TJobUserVExample eex = new TJobUserVExample();
        eex.createCriteria().andIdEqualTo(emp.getId());
        List<TJobUserV> jobs = this.jobMapper.selectByExample(eex);
        for (TJobUserV tJobUserV : jobs) {
        	if(StringUtils.hasLength(tJobUserV.getStationname())){
        		users.add(tJobUserV.getStationname());
                users.add(tJobUserV.getOrganizationId()+tJobUserV.getStationname());
        	}
		}
        users.add(emp.getLoginName());
        ex.createCriteria().andEndTimeIsNull().andAssigneeIn(users);
        ex.setOrderByClause("start_time_ desc");
        List<VTask> list = this.taskMapper.selectByExample(ex);
        int total = this.taskMapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public String startProcess(Map<String, Object> params, String key, String startUser) throws Exception {
        this.identityService.setAuthenticatedUserId(startUser);
        ProcessInstance instance = this.runtimeService.startProcessInstanceByKey(key,params);
        return instance.getId();
    }

    @Override
    public String findViewUrl(String urlKey, String processInstanceId) throws Exception {
        String url = this.processMapper.findParams(urlKey,processInstanceId);
        if(StringUtils.hasLength(url)){
            return url;
        }else{
            return null;
        }
    }

    @Override
    public String findTaskBtnInfo(String key, String processInstanceId) throws Exception {
        String url = this.processMapper.findParams(key,processInstanceId);
        if(StringUtils.hasLength(url)){
            return url;
        }else{
            return null;
        }
    }
}
