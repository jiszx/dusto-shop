package com.hhnz.crm.controller;

import java.util.List;

import com.hhnz.crm.model.TTimeLine;

public interface ITimelineService {

	List<TTimeLine> findTopline(String loginName)throws Exception;

}
