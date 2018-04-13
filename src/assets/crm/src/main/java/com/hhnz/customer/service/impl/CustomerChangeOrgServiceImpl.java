package com.hhnz.customer.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.CMerchCustBaseVMapper;
import com.hhnz.customer.service.ICustomerChangeOrgService;

@Service
@Transactional
public class CustomerChangeOrgServiceImpl implements ICustomerChangeOrgService {

	@Resource
	private CMerchCustBaseVMapper merchCustBaseVMapper;

	public String changeMerchOrg(Long merchId, String newOrgId, String oldOrgId,String changeType,String states,Long stationId,Long salesrepId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchId", merchId);
		map.put("newOrgId", newOrgId);
		map.put("oldOrgId", oldOrgId);
		map.put("changeType", changeType);
		map.put("stationId", stationId);
		map.put("salesrepId", salesrepId);
		this.merchCustBaseVMapper.changeOrg(map);
		if ("4".equals(states)){
			//失效客户
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("states", states);
			params.put("merchId", merchId);
			this.merchCustBaseVMapper.changeStates(params);
		}
		return map.get("p_result").toString();
	}

}
