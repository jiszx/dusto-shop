package com.hhnz.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.AccountUtilMapper;
import com.hhnz.account.model.AdjustCustomer;
import com.hhnz.account.service.IAccountUtilService;
import com.hhnz.util.AjaxDTO;

@Service
@Transactional
public class AccountUtilServiceImpl implements IAccountUtilService {
	@Resource
	private AccountUtilMapper  mapper;
	@Override
	public AjaxDTO getAdjustCutomer(String orgid,Long amt) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgid", orgid);
		params.put("amt", amt);
		List<AdjustCustomer> list = this.mapper.getAdjustCustomer(params);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	@Override
	public AjaxDTO getAdjustCutomerByType(String orgid, String type) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgid", orgid);
		if(type !=null){
			params.put("type", type);
			params.put("cust_type", type);
		}
		List<AdjustCustomer> list = this.mapper.getAdjustCustomer(params);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

}
