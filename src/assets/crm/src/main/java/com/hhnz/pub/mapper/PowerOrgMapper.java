package com.hhnz.pub.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.crm.model.UserStations;
import com.hhnz.pub.model.PowerOrg;

public interface PowerOrgMapper {

	List<PowerOrg> powerOrg(Map<String, Object> params);
	
	@Deprecated
	List<UserStations> getUserStations(String orgid);
	
	List<UserStations> getUserStations(Map<String, Object> params);
	

	List<UserStations> getPowerStations(Map<String, Object> params);
	
	List<String> subOrg(String orgid);

}
