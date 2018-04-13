package com.hhnz.organization.service;

import java.util.List;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.organization.model.TJobStation;
import com.hhnz.organization.model.TJobUserKey;
import com.hhnz.organization.model.TJobUserV;
import com.hhnz.util.AjaxDTO;

public interface IJobStationService {

  int jobset(TJobUserKey jobuser);

  AjaxDTO findJobsByOrg(AjaxDTO bean, String orgId);

  List<TEmployee> findJobUser(String jid) throws Exception;

  int addJob(TJobStation job);

  int editJob(TJobStation job);

  int deleteJob(String id);

  AjaxDTO findJobUsers(AjaxDTO bean, String orgId, String search, TJobUserV jobUser);

  int cancelUserJob(String jobid, Long userid);

}
