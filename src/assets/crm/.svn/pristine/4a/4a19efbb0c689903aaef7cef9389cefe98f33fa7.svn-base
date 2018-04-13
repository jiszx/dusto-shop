package com.hhnz.customer.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.ApiCMerchCustMapper;
import com.hhnz.customer.mapper.CMerchCustInvMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ApiCustomerService;
@Service
@Transactional
public class ApiCustomerServiceImpl implements ApiCustomerService {

	@Resource
    private ApiCMerchCustMapper apiCustomerMapper;
	
	@Override
	public List<CMerchCustBase> findCustBaseByPID(Map<String,Object> params) {
		return apiCustomerMapper.findCustBaseByPID(params);
	}

}
