package com.hhnz.customer.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.customer.dto.CustomerBaseExport;
import com.hhnz.customer.model.CMerchCustContractV;
import com.hhnz.customer.model.CMerchCustContractVExample;

public interface CMerchCustContractVMapper {
    int countByExample(CMerchCustContractVExample example);

    List<CMerchCustContractV> selectByExample(CMerchCustContractVExample example);

	String getvirtualWarehouse(Long merchId);
	
	List<CustomerBaseExport> customerDetail4Export(Map<String, Object> param);

}