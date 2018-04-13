package com.hhnz.organization.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.organization.service.IStationService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("/station")
public class StationController {
  @Resource
  private IStationService stationService;


  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public AjaxDTO list(AjaxDTO bean, CrmStation station, String orgid) {
    return this.stationService.findStations(bean, station, orgid);
  }

  @ResponseBody
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public int editStation(CrmStation station) {
    return this.stationService.edit(station);
  }

  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public int deleteStation(Long id) {
    return this.stationService.delete(id);
  }

  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public int saveStation(CrmStation station, HttpServletRequest request) {
    TEmployee user =(TEmployee) request.getSession().getAttribute("user");
    station.setCreateOid(user.getId());
    station.setCreateTs(new Date());
    station.setUpdateOid(user.getId());
    station.setUpdateTs(new Date());
    station.setStates("1");
    return this.stationService.save(station);
  }
  
  @ResponseBody
  @RequestMapping(value = "/relate", method = RequestMethod.POST)
  public int relateCustomer(Long stationId,
      @RequestParam(required = false, value = "customerId") List<Long> customerIds,
      HttpServletRequest request) {
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    return stationService.relateCustomers(stationId, customerIds, user);
  }

  @ResponseBody
  @RequestMapping(value = "/related/customer", method = RequestMethod.GET)
  public AjaxDTO relatedCustomers(Long stationId, HttpServletRequest request){
    return stationService.relatedCustomers(stationId);
  }
}
