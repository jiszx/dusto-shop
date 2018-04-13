package com.hhnz.customer.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.customer.dto.CustomerBaseDTO;
import com.hhnz.customer.model.CMerchCustBase;

public interface CMerchCustRetailMapper {

	List<CustomerBaseDTO> selectRetailCustomers(Map<String, Object> params);

	int countRetailCustomers(Map<String, Object> params);
	
	List<CMerchCustBase> selectRetail4Export(Map<String, Object> params);

}
