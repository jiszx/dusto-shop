package com.hhnz.organization.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.service.IRoleService;
import com.hhnz.organization.model.TJobStation;
import com.hhnz.organization.model.TJobUserKey;
import com.hhnz.organization.model.TJobUserV;
import com.hhnz.organization.service.IJobStationService;
import com.hhnz.util.AjaxDTO;

/**
 * Created by yang on 2016-7-26.
 */
@Controller
@RequestMapping("/jobstation")
public class JobStationController {

  @Resource
  private IJobStationService service;
  @Autowired
  private IRoleService roleService;

  @RequestMapping("/job.html")
  public String addOrgPage() {
    return "organization/job";
  }
  
  @RequestMapping("/jobset.html")
  public ModelAndView jobSet() throws Exception {
    ModelAndView mv = new ModelAndView();
    AjaxDTO dto = this.roleService.findRole(new HashMap<String, Object>(),new AjaxDTO());
    mv.addObject("roles",dto.getRows());
    mv.setViewName("organization/jobset");
    return mv;
  }
  
  @ResponseBody
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public AjaxDTO getUsers(String jobId, AjaxDTO page){
    
    return null;
  }

  @ResponseBody
  @RequestMapping(value = "/list")
  public AjaxDTO list(AjaxDTO bean, String orgId) throws Exception {
    return this.service.findJobsByOrg(bean, orgId);
  }
  
  @ResponseBody
  @RequestMapping(value = "/jobset/list", method= RequestMethod.GET)
  public AjaxDTO listJobuser(AjaxDTO bean, String orgId, String search, TJobUserV jobUser) {
    return this.service.findJobUsers(bean, orgId, search, jobUser);
  }
  
  @ResponseBody
  @RequestMapping(value = "/jobset/relation", method = RequestMethod.POST)
  public int jobset(TJobUserKey jobuser){
    return this.service.jobset(jobuser);
  }

  @ResponseBody
  @RequestMapping(value = "/jobList")
  public AjaxDTO jobList(String jid) throws Exception {
    AjaxDTO dto = new AjaxDTO();
    if (!StringUtils.hasLength(jid)) {
      jid = "X";
    }
    List<TEmployee> users = this.service.findJobUser(jid);
    dto.setRows(users);
    dto.setTotal(users.size());
    return dto;
  }
  
  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public int save(TJobStation job) throws Exception {
    return this.service.addJob(job);
  }

  @ResponseBody
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public int edit(TJobStation job) throws Exception {
    return this.service.editJob(job);
  }

  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public int delete(String id) throws Exception {
    return this.service.deleteJob(id);
  }
  
  @ResponseBody
  @RequestMapping(value = "/cancel", method = RequestMethod.POST)
  public int cancelRelate(String jobid, Long userid){
    return service.cancelUserJob(jobid, userid);
  }

}
