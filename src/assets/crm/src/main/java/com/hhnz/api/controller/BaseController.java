package com.hhnz.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hhnz.api.cache.CacheService;
import com.hhnz.api.cache.model.LoginCacheModel;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IHomeService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.objutil.StringUtil;

@Controller
public class BaseController {
  @Autowired
  private CacheService cacheService;
  @Autowired
  private IHomeService homeService;

  protected static AjaxDTO limitVerify(AjaxDTO page) {
    if (page.getLimit() < 1) {
      page.setLimit(10);
    }
    if (page.getLimit() > 100) {
      page.setLimit(100);
    }
    return page;
  }

  protected String getToken(HttpServletRequest request) {
    return request.getHeader("accesstoken");
  }

  protected TEmployee loginApiUser(HttpServletRequest request) {
    String token = getToken(request);
    if(StringUtils.isEmpty(token)){
      return null;
    }
    LoginCacheModel cache = cacheService.get(token,LoginCacheModel.class);
    return cache == null ? null : cache.getT();
  }
  
  protected Map<String, Object> userInfo(HttpServletRequest request) throws Exception{
    TEmployee user = loginApiUser(request);
    if(user==null){
      return null;
    }
    return homeService.findUserSessionInfo(user.getLoginName());
  }
  
  protected List<UserJobs> userJobs(Map<String, Object> userInfo) throws Exception {
    return  (List<UserJobs>) userInfo.get(SessionKey.JOBS);// 用户职位信息
  }
  
  protected UserStations userStation(Map<String, Object> userInfo, Long stationId) throws Exception {
    List<UserStations> userstations = (List<UserStations>) userInfo.get(SessionKey.STATIONS);// 用户岗位信息
    for(UserStations station:userstations){
      if(station.getStationid().equals(stationId)){
        return station;
      }
    }
    return new UserStations();
  }

  @SuppressWarnings("unchecked")
  protected List<CrmSalesOrganization> userOrgs(String loginName) throws Exception {
    Map<String, Object> userinfo = homeService.findUserSessionInfo(loginName);
    return (List<CrmSalesOrganization>) (userinfo != null ? userinfo.get(SessionKey.ORG_INFO)
        : null);
  }

}
