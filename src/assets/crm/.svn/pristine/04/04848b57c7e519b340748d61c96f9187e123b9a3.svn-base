package com.hhnz.logistics.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hhnz.logistics.dto.DeploymentDTO;
import com.hhnz.logistics.mapper.DeploymentMapper;
import com.hhnz.logistics.service.IDeploymentService;
import com.hhnz.util.AjaxDTO;

@Service
public class DeploymentServiceImpl implements IDeploymentService {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DeploymentServiceImpl.class);
	
	@Resource
	private  DeploymentMapper mapper;
	@Override
	public AjaxDTO SelectDeploymentList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<DeploymentDTO> list = this.mapper.selectDeploymentList(params);
		int total = this.mapper.countDeploymentList(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	@Override
	public List<DeploymentDTO> exportListAll(
			Map<String, Object> params) {
		return this.mapper.exportListAll(params);
	}
}
