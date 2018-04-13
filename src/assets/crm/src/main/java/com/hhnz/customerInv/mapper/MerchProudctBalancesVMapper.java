package com.hhnz.customerInv.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.customerInv.model.MerchProudctBalancesV;
import com.hhnz.customerInv.model.MerchProudctBalancesVExample;

public interface MerchProudctBalancesVMapper {

	int countByExample(MerchProudctBalancesVExample example);
   
    List<MerchProudctBalancesV> selectByExample(MerchProudctBalancesVExample example);

	void updateBalance(Map<String, Object> params);
}