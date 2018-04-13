package com.hhnz.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.controller.ITimelineService;
import com.hhnz.crm.mapper.TTimeLineMapper;
import com.hhnz.crm.model.TTimeLine;
import com.hhnz.crm.model.TTimeLineExample;
import com.hhnz.util.db.Page;
@Service
@Transactional
public class TimeLineServiceImpl implements ITimelineService {
	
	@Autowired
	private TTimeLineMapper mapper;

	@Override
	public List<TTimeLine> findTopline(String loginName) throws Exception {
		
		TTimeLineExample ex = new TTimeLineExample();
		ex.createCriteria().andUserIdEqualTo(loginName);
		ex.setOrderByClause("create_ts desc");
		
		Page page = new Page();
		page.setLimit(5);
		page.setOffset(0);
		
		ex.setPage(page);
		
		return this.mapper.selectByExample(ex);
		
	}

}
