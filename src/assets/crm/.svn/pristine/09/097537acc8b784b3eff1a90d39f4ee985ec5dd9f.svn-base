package com.hhnz.pub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.gson.JsonObject;
import com.hhnz.pub.mapper.ChangeMapper;
import com.hhnz.pub.mapper.ChangeVarMapper;
import com.hhnz.pub.model.Change;
import com.hhnz.pub.model.ChangeVar;
import com.hhnz.pub.service.IChangeService;
import com.hhnz.util.JsonUtil;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ChangeServiceImpl implements IChangeService {
	
	@Autowired
	private ChangeMapper mapper;
	
	@Autowired
	private ChangeVarMapper varMapper;
	
	@Override
	public Long getChangeId() {
		long m = this.mapper.findSEQ();
		return m;
	};
	
	@Override
	public List<ChangeVar> findChangeVars(Long changeId, String obj){
		List<ChangeVar> list = this.varMapper.findByChangeId(changeId,obj);
		return list;
	}
	
	@Override
	public Integer del(Class<Change> clazz, String id){
		return this.mapper.delete(id);
	}
	
	@Override
	public Integer deleteChangeVar(String changeId) {
		return  this.mapper.deleteChangeVar(changeId);
	}
	
	@Override
	public Integer updateChange(Change vo){
		return this.mapper.update(vo);
	}
	
	@Override
	public String add(Change pojo){
		this.mapper.add(pojo);
		return pojo.getId().toString();
	}
	
	@Override
	public <T> T getChangeBean(Class<T> clazz, Long changeId){
		List<ChangeVar> changeVarList = varMapper.findByChangeId(changeId, clazz.getSimpleName());
		JsonObject json = new JsonObject();
		if(changeVarList!=null && !changeVarList.isEmpty()){
			for (ChangeVar var : changeVarList) {
				String fieldName = var.getColumnName();
				json.addProperty(fieldName, var.getChangeValue());
			}
			return JsonUtil.fromJSON(json, clazz);
		}
		return null;
	}
	
	@Override
	public <T> T getLatestChangeBean(Class<T> clazz, String id) {
		Change c = getLatestChange(clazz.getSimpleName(), id);
		if(c == null){
			return null;
		}
		return getChangeBean(clazz, c.getId());
	}

	@Override
	public Change getLatestChange(String objectName, String id) {
		return mapper.findLatestChange(objectName, id);
	}

	@Override
	public void save(Change change) {
		Assert.notNull(change);
		Assert.notNull(change.getChangeVars());
		Change oc = findById(change.getId());
		if(oc == null){
			add(change);
			varMapper.batchInsert(change.getChangeVars());
		}else{
			change.setChangeTs(oc.getChangeTs());
			updateChange(change);
			varMapper.deleteByChangeId(change.getId(), change.getObjectKey());
			varMapper.batchInsert(change.getChangeVars());
		}
	}
	
	@Override
	public Change findById(Long id){
		return this.mapper.findById(id);
	}
}
