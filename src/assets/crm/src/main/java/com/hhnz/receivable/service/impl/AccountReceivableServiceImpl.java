package com.hhnz.receivable.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.receivable.dto.AccountReceivableDTO;
import com.hhnz.receivable.dto.AccountReceivableDetailsDTO;
import com.hhnz.receivable.mapper.AccountReceivableMapper;
import com.hhnz.receivable.service.AccountReceivableService;
import com.hhnz.util.AjaxDTO;

@Service
@Transactional
public class AccountReceivableServiceImpl implements AccountReceivableService {
	
	@Resource
	private AccountReceivableMapper mapper;
	
	@Override
	public AjaxDTO getArBalancesList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<AccountReceivableDTO> list = this.mapper.selectArBalancesList(params);
		int count = this.mapper.countArBalancesList(params);
		dto.setRows(list);
		dto.setTotal(count);
		return dto;
	}

	@Override
	public AjaxDTO getARbalanceDetailslist(AjaxDTO bean,Long id) {
		AjaxDTO dto = new AjaxDTO();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getLimit()+bean.getOffset());
		List<AccountReceivableDetailsDTO> list = this.mapper.selectArDetailsList(params);
		int total = this.mapper.countArDetials(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public String getMaxPeriod() {
		return this.mapper.selectMaxPeriod();
	}

	@Override
	public Map<String, Object> updateByMerchId(Long merchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_merch_cust_id", merchId);
		this.mapper.updateByMerchId(map);
		return map;
	}

	@Override
	public String updateByPeriod() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("p_period", sf.format(new Date()));
		this.mapper.updateByPeriod(map);
		return map.get("p_result").toString();
	}
	
}
