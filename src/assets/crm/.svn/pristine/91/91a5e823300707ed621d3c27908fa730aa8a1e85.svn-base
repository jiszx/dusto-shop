package com.hhnz.logistics.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hhnz.logistics.dto.LogisticsOrderDTO;
import com.hhnz.logistics.mapper.LogisticsOrderMapper;
import com.hhnz.logistics.service.ILogisticsOrderService;
import com.hhnz.util.AjaxDTO;

@Service
public class LogisticsOrderServiceImpl implements ILogisticsOrderService {
	
	@Resource
	private LogisticsOrderMapper mapper;
	@Override
	public AjaxDTO selectOrderList(Map<String, Object> params) {
		List<LogisticsOrderDTO> list = new ArrayList<LogisticsOrderDTO>();
		list = this.mapper.selectOrderList(params);
		int total = this.mapper.countOrderList(params);
		AjaxDTO dto = new AjaxDTO();
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	@Override
	public List<LogisticsOrderDTO> exportOrderDetailListAll(Map<String, Object> params) {
		return this.mapper.exportOrderDetailListAll(params);
	}

}
