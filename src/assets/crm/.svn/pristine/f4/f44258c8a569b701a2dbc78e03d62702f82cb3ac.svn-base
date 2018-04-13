package com.hhnz.monitor.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor")
public class MonitorPageController {
  
  @RequestMapping("/exception.html")
  public String exceptionPage(){
    return "monitor/exception";
  }
  
  @RequestMapping("/config.html")
  public String configPage(){
    return "monitor/config";
  }
  
  @RequestMapping("/task.html")
  public String taskPage(){
    return "monitor/task";
  }
  
  @RequestMapping("/app.html")
  public String app(String version, Model view){
    view.addAttribute("version", StringUtils.isEmpty(version)?"":version);
    return "monitor/app";
  }
  
  @RequestMapping("/export.html")
  public String exportPage(){
    return "monitor/export";
  }
}
