package com.hhnz.monitor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.monitor.mapper.TLogMapper;
import com.hhnz.monitor.model.TLog;
import com.hhnz.monitor.service.ILogService;


/**
 * @author: chaoyang.ren
 * @date:2016年9月1日
 * @time:下午4:53:20
 * @email:chaoyang.ren@foxmail.com
 */
@Service
@Transactional
public class LogServiceImpl implements ILogService{
	@Autowired
	private TLogMapper logMapper;
	
	@Override
	public void save(TLog log){
		logMapper.insertSelective(log);
	}
}
