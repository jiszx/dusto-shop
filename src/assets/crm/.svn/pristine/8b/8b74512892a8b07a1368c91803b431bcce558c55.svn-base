package com.hhnz.monitor.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.monitor.service.TaskDetailService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("/monitor/task")
public class TaskDetailController {

  @Resource
  private TaskDetailService taskDetailService;

  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public AjaxDTO list(AjaxDTO page) {
    return taskDetailService.list(page);
  }
  
  @ResponseBody
  @RequestMapping(value = "/log", method = RequestMethod.GET)
  public AjaxDTO taskLog(AjaxDTO page, Long taskid) {
    return taskDetailService.taskLogs(page, taskid);
  }
  
  @ResponseBody
  @RequestMapping(value = "/manual", method = RequestMethod.GET)
  public int manualRun(Long taskid){
    return taskDetailService.manualRun(taskid);
  }

}
