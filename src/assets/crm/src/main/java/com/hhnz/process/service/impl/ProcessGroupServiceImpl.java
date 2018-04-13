package com.hhnz.process.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.process.service.IProcessGroupService;

/**
 * Created by yang on 2016-8-4.
 */
@Service("groupService")
@Transactional
public class ProcessGroupServiceImpl implements IProcessGroupService {
	
    
	@Override
	public String findAssignee(String orgId, String name) {
		if(orgId !=null){
			return orgId+name;
		}else{
			return name;
		}
		
	}
}
