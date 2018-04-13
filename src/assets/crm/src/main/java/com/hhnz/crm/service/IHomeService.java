package com.hhnz.crm.service;

import com.hhnz.crm.model.TAuthority;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.dto.TreeDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2016/6/24.
 */
public interface IHomeService {

    List<TreeDTO<TAuthority>> findAllAuthority(Integer rid)throws Exception;

    Map<String,Object> findUserSessionInfo(String name)throws Exception;

	Long getCustNumByStationId(Long stationid);

	Long getauditOrder(Long stationid);

	Integer mytaskNum(TEmployee user);

	String getUserOrgInfo(String stationorgid);
}
