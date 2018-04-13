package com.hhnz.pub.service.impl;

import com.hhnz.pub.mapper.TAreaMapper;
import com.hhnz.pub.model.TArea;
import com.hhnz.pub.model.TAreaExample;
import com.hhnz.pub.service.IAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yang on 2016-7-6.
 */
@Service
@Transactional
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private TAreaMapper mapper;

    @Override
    public List<TArea> findAll() throws Exception {
        TAreaExample ex = new TAreaExample();
        ex.setOrderByClause("id asc");
        ex.createCriteria().andAreaLevelLessThan("2");
        return this.mapper.selectByExample(ex);
    }

	@Override
	public List<TArea> findAll(String pid) throws Exception {
		TAreaExample ex = new TAreaExample();
        ex.setOrderByClause("id asc");
        ex.createCriteria().andPidEqualTo(pid);
        return this.mapper.selectByExample(ex);
	}
	
	@Override
	public String findRDCCodeByCity(String cityId){
		return this.mapper.findRDCCodeByCity(cityId);
	}
	
	@Override
	public TArea findById(String id){
		return this.mapper.selectByPrimaryKey(id);
	}

    @Override
    public List<TArea> findallByLevel(String level) throws Exception {
        TAreaExample ex = new TAreaExample();
        ex.setOrderByClause("id asc");
        ex.createCriteria().andAreaLevelLessThan(level);
        return this.mapper.selectByExample(ex);
    }

    @Override
    public int updateArea(TArea bean) throws Exception {
        return this.mapper.updateByPrimaryKeySelective(bean);
    }
}
