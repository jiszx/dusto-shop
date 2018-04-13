package com.hhnz.pub.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.util.AjaxDTO;

public interface IPowerOrgService {

	AjaxDTO powerOrg(List<UserJobs> jobs);
	
	List<Long> getUserStations(TEmployee user,List<UserJobs> jobs ,UserStations station );

  List<String> powerProv(TEmployee user, List<UserJobs> jobs, UserStations station);

  List<Long> stations(HttpServletRequest request);
}
