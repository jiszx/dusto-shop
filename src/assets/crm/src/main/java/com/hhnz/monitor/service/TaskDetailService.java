package com.hhnz.monitor.service;

import com.hhnz.util.AjaxDTO;

public interface TaskDetailService {

  AjaxDTO list(AjaxDTO page);

  AjaxDTO taskLogs(AjaxDTO page, Long taskid);

  int manualRun(Long taskid);

}
