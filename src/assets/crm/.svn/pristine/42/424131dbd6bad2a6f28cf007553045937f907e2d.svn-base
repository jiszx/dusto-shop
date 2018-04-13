package com.hhnz.monitor.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.monitor.model.CrmExport;
import com.hhnz.monitor.service.CommonExportService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("/monitor/export")
public class CommonExportController {

  @Autowired
  private CommonExportService exportService;
  @Resource
  private IPowerOrgService  powerService;

  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public AjaxDTO list(AjaxDTO page) {
    return exportService.list(page);
  }

  @ResponseBody
  @RequestMapping(value = "/generate", method = RequestMethod.GET)
  public String generateExcel(String key, HttpServletRequest request) throws IOException {
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);// 用户信息
    @SuppressWarnings("unchecked")
    List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
    UserStations station =
        (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位
    List<Long> stationids = this.powerService.getUserStations(user, jobs, station);// 获取用户可以查看的岗位
    if("admin".equals(user.getLoginName())){
      stationids = null;
    }
    return exportService.generateExcel(key, stationids);
  }

  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public int saveOrUpdate(CrmExport bean) {
    return exportService.saveOrUpdate(bean);
  }

  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public int delete(CrmExport bean) {
    return exportService.deleteExportConfig(bean);
  }

}
