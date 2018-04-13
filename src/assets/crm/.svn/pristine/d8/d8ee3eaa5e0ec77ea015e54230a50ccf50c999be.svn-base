package com.hhnz.crm.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.mapper.MoqMapper;
import com.hhnz.crm.model.BusinessModelMoq;
import com.hhnz.crm.service.IMoqService;

/**
 * @author: chaoyang.ren
 * @date:Mar 17, 2017
 * @time:11:05:06 AM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Service
@Transactional
public class MoqServiceImpl implements IMoqService {

	@Resource
	private MoqMapper moqMapper;
	
	@Override
	public List<BusinessModelMoq> findAll() {
		return moqMapper.findAll();
	}

	@Override
	public void save(List<BusinessModelMoq> moqs) {
		for (BusinessModelMoq businessModelMoq : moqs) {
			//最小起订量不为空时才处理保存操作
			if(businessModelMoq.getMinOrderQuantity() != null){
				if(businessModelMoq.getRange() == null)
					businessModelMoq.setRange(BigDecimal.ZERO);
				if(businessModelMoq.getId() != null){
					//update
					moqMapper.update(businessModelMoq);
				}else{
					//add
					moqMapper.add(businessModelMoq);
				}
			}
		}
	}
	
}
