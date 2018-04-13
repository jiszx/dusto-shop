package com.hhnz.monitor.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hhnz.monitor.mapper.CrmTaskLogMapper;
import com.hhnz.monitor.mapper.CrmTaskMainMapper;
import com.hhnz.monitor.mapper.TaskLogRestartMapper;
import com.hhnz.monitor.model.CrmTaskLog;
import com.hhnz.monitor.model.CrmTaskLogExample;
import com.hhnz.monitor.model.CrmTaskMain;
import com.hhnz.monitor.model.CrmTaskMainExample;
import com.hhnz.monitor.service.TaskDetailService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.DateUtil;
import com.hhnz.util.db.Page;

@Service
public class TaskDetailServiceImpl implements TaskDetailService {
  
  @Resource
  private CrmTaskMainMapper taskMapper;
  @Resource
  private CrmTaskLogMapper taskLogMapper; 
  @Resource
  private TaskLogRestartMapper logRestartMapper;
  
  @Override
  public AjaxDTO list(AjaxDTO page){
    Page p = new Page();
    p.setLimit(page.getLimit());
    p.setOffset(page.getOffset());
    CrmTaskMainExample example = new CrmTaskMainExample();
    example.setPage(p);
    List<CrmTaskMain> list = taskMapper.selectByExample(example);
    int count = taskMapper.countByExample(example);
    page.setTotal(count);
    page.setRows(list);
    return page;
  }
  
  @Override
  public AjaxDTO taskLogs(AjaxDTO page, Long taskid){
    Page p = new Page();
    p.setLimit(page.getLimit());
    p.setOffset(page.getOffset());
    CrmTaskLogExample example = new CrmTaskLogExample();
    example.setPage(p);
    example.createCriteria().andTaskMainIdEqualTo(taskid);
    example.setOrderByClause("edate desc");
    List<CrmTaskLog> list = taskLogMapper.selectByExample(example);
    int count = taskLogMapper.countByExample(example);
    page.setTotal(count);
    page.setRows(list);
    return page;
  }
  
  public int canManual(CrmTaskLog log){
    Date date = log.getEdate();
    Date today = DateUtil.parse(DateUtil.format(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
    if(date.getTime()>today.getTime() && "2".equals(log.getStates())){
      return 1;
    }
    return 0;
  }
  
  @Override
  public int manualRun(Long taskid){
    AjaxDTO page = new AjaxDTO();
    page.setLimit(10);
    AjaxDTO dto = taskLogs(page, taskid);
    List<CrmTaskLog> list = dto.getRows();
    if(list==null || list.isEmpty()){
      return 0;
    }
    CrmTaskLog log = list.get(0);
    if(canManual(log)==0){
      return 0;
    }
    // TODO: 通过mapper执行
//    logRestartMapper.crmjobs();
    return 1;
  }
  
}
