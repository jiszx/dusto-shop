package com.hhnz.organization.service;

import java.util.List;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserStations;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.util.AjaxDTO;

public interface IStationService {

  int edit(CrmStation model);

  int save(CrmStation model);

  int delete(Long id);

  AjaxDTO findStations(AjaxDTO bean, CrmStation station, String orgid);

  List<CrmStation> findStationsByorgid(String orgid);
  
  List<UserStations> findUserStations(Long userid);

  int relateCustomers(Long customerId, List<Long> stationIds, TEmployee user);

  AjaxDTO relatedCustomers(Long stationId);

}
