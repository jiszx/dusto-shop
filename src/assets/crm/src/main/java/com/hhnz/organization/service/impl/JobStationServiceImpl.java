package com.hhnz.organization.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.mapper.TEmployeeMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.organization.mapper.TJobStationMapper;
import com.hhnz.organization.mapper.TJobStationVMapper;
import com.hhnz.organization.mapper.TJobUserMapper;
import com.hhnz.organization.mapper.TJobUserVMapper;
import com.hhnz.organization.model.TJobStation;
import com.hhnz.organization.model.TJobStationV;
import com.hhnz.organization.model.TJobStationVExample;
import com.hhnz.organization.model.TJobUserExample;
import com.hhnz.organization.model.TJobUserKey;
import com.hhnz.organization.model.TJobUserV;
import com.hhnz.organization.model.TJobUserVExample;
import com.hhnz.organization.model.TJobUserVExample.Criteria;
import com.hhnz.organization.service.IJobStationService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Orgs;
import com.hhnz.util.db.Page;

@Service
@Transactional
public class JobStationServiceImpl implements IJobStationService {

  @Resource
  private TJobStationMapper stationMapper;
  @Resource
  private TJobStationVMapper stationVMapper;
  @Resource
  private TEmployeeMapper empMapper;
  @Resource
  private TJobUserMapper jobUserMapper;
  @Resource
  private TJobUserVMapper jobUserVMapper;
  
  @Override
  public AjaxDTO findJobUsers(AjaxDTO bean, String orgId, String search, TJobUserV jobUser){
    TJobUserVExample ex = new TJobUserVExample();
    Page page = new Page();
    page.setLimit(bean.getLimit());
    page.setOffset(bean.getOffset());
    ex.setPage(page);
    ex.setOrderByClause("JOB_ID");
    Criteria param = ex.createCriteria();
    if(StringUtils.isNotEmpty(jobUser.getName())){
      param.andNameLike("%"+jobUser.getName()+"%");
    }
    if(StringUtils.isNotEmpty(orgId)){
      param.andOrganizationIdLike(orgId+"%");
    }
    if(StringUtils.isNotEmpty(search)){
      param.andStationnameLike("%"+search+"%");
    }
    if(StringUtils.isNotEmpty(jobUser.getJobId())){
      param.andJobIdEqualTo(jobUser.getJobId());
    }
    List<TJobUserV> rows = this.jobUserVMapper.selectByExample(ex);
    int count = this.jobUserVMapper.countByExample(ex);
    bean.setRows(rows);
    bean.setTotal(count);
    return bean;
  }
  
  @Override
  public int jobset(TJobUserKey jobuser){
    TJobUserExample ex = new TJobUserExample();
    ex.createCriteria().andJobIdEqualTo(jobuser.getJobId()).andEmpIdEqualTo(jobuser.getEmpId());
    List<TJobUserKey> list = this.jobUserMapper.selectByExample(ex);
    if(!list.isEmpty()){
      this.jobUserMapper.deleteByPrimaryKey(list.get(0));
    }
    return this.jobUserMapper.insert(jobuser);
  }

  @Override
  public AjaxDTO findJobsByOrg(AjaxDTO bean, String orgId) {
    TJobStationVExample ex = new TJobStationVExample();
    Page page = new Page();
    page.setLimit(bean.getLimit());
    page.setOffset(bean.getOffset());
    ex.setPage(page);
    ex.setOrderByClause("org_id");
    TJobStationVExample.Criteria exc = ex.createCriteria();
    if (StringUtils.isNotEmpty(orgId)) {
      exc.andOrgIdLike(orgId+"%");
    }
    if(StringUtils.isNoneEmpty(bean.getSearch())){
      exc.andNameLike("%"+bean.getSearch()+"%");
    }
    List<TJobStationV> rows = this.stationVMapper.selectByExample(ex);
    int count = this.stationVMapper.countByExample(ex);
    for(TJobStationV jobuser:rows){
      String name = jobuser.getName();
      int index = name.lastIndexOf("-");
      jobuser.setName(Orgs.orgFullName(jobuser.getOrgId())+(index>=0?name.substring(index, name.length()):name));
    }
    bean.setRows(rows);
    bean.setTotal(count);
    return bean;
  }

  @Override
  public List<TEmployee> findJobUser(String jid) throws Exception {
    return this.empMapper.selectByJob(jid);
  }

  @Override
  public int addJob(TJobStation job) {
    job.setId(UUID.randomUUID().toString());
    if("1".equals(job.getJobType())){
    	job.setDataView("11111111");
    }else{
    	job.setDataView("00000000");
    }
    return this.stationMapper.insert(job);
  }

  @Override
  public int editJob(TJobStation job) {
    if("1".equals(job.getJobType())){
    	job.setDataView("11111111");
    }else{
    	job.setDataView("00000000");
    }
    return this.stationMapper.updateByPrimaryKeySelective(job);
  }

  @Override
  public int deleteJob(String id) {
    return this.stationMapper.deleteByPrimaryKey(id);
  }
  
  @Override
  public int cancelUserJob(String jobid, Long userid){
    TJobUserKey jobUser = new TJobUserKey();
    jobUser.setEmpId(userid);
    jobUser.setJobId(jobid);
    return jobUserMapper.deleteByPrimaryKey(jobUser);
  }
  
  public AjaxDTO getUsers(String jobId, AjaxDTO page){
    
    return null;
  }

}
