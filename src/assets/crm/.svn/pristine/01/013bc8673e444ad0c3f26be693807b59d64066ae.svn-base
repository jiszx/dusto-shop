package com.hhnz.logistics.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhnz.logistics.dto.LogisticsRdcAreaDTO;
import com.hhnz.logistics.mapper.LogisticsRdcAreaMapper;
import com.hhnz.logistics.mapper.TLogisticsAreaMapper;
import com.hhnz.logistics.mapper.TLogisticsRdcMapper;
import com.hhnz.logistics.model.TLogisticsArea;
import com.hhnz.logistics.model.TLogisticsAreaExample;
import com.hhnz.logistics.model.TLogisticsRdc;
import com.hhnz.logistics.service.ILogisticsRdcAreaService;
import com.hhnz.util.AjaxDTO;

@Service
public class LogisticsRdcAreaServiceImpl implements ILogisticsRdcAreaService {
	
	@Autowired
	private LogisticsRdcAreaMapper  mapper;
	
	@Autowired
	private TLogisticsRdcMapper  rdcmapper;
	
	@Autowired
	private TLogisticsAreaMapper  areamapper;
	@Override
	public AjaxDTO selectUserRdcList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<LogisticsRdcAreaDTO>  list = this.mapper.selectUserRdcList(params);
		int total =this.mapper.countUserRdcList(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	@Override
	public List<String> getRdcAreaByUserId(Long id) {
		TLogisticsAreaExample ex = new TLogisticsAreaExample();
		TLogisticsAreaExample.Criteria ext = ex.createCriteria();
		ext.andLogisticsRdcIdEqualTo(id);
		List<TLogisticsArea> TLogisticsAreas = this.areamapper.selectByExample(ex);
		List<String> list= new ArrayList<String>();
		if(TLogisticsAreas !=null && TLogisticsAreas.size() >0){
			for(TLogisticsArea area :TLogisticsAreas){
				list.add(area.getAreaId());
			}
		}
		return list;
	}
	@Override
	public int addUserRdc(Long userId, String rdcCode) {
		TLogisticsRdc rdc = new TLogisticsRdc();
		rdc.setUserId(userId);
		rdc.setRdcCode(rdcCode);
		return this.rdcmapper.insert(rdc);
	}
	@Override
	public Integer saveRdcArea(Long id, List<String> areas) {
		if(areas !=null && areas.size() >0){
			for(String areaId :areas){
				TLogisticsAreaExample ex = new TLogisticsAreaExample();
				TLogisticsAreaExample.Criteria ext = ex.createCriteria();
				ext.andLogisticsRdcIdEqualTo(id);
				ext.andAreaIdEqualTo(areaId);
				List<TLogisticsArea>  a = this.areamapper.selectByExample(ex);
				if(a ==null || a.size() ==0){
					TLogisticsArea larea = new TLogisticsArea();
					larea.setAreaId(areaId);
					larea.setLogisticsRdcId(id);
					this.areamapper.insert(larea);
				}
			}
		}
		return 1;
	}
	@Override
	public Integer del(Long id) {
		this.rdcmapper.deleteByPrimaryKey(id);
		TLogisticsAreaExample ex = new TLogisticsAreaExample();
		TLogisticsAreaExample.Criteria ext = ex.createCriteria();
		ext.andLogisticsRdcIdEqualTo(id);
		return this.areamapper.deleteByExample(ex);
	}
	@Override
	public List<LogisticsRdcAreaDTO> getexportExl(Map<String, Object> params) {
		return  this.mapper.getexportExl(params);
	}

}
