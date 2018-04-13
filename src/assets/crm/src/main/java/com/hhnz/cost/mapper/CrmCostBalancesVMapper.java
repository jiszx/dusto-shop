package com.hhnz.cost.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.cost.model.CrmCostBalancesV;
import com.hhnz.cost.model.CrmCostBalancesVExample;

public interface CrmCostBalancesVMapper {
    int countByExample(CrmCostBalancesVExample example);

    List<CrmCostBalancesV> selectByExample(CrmCostBalancesVExample example);

	String getMaxPeriod();

	void upBalance(Map<String, Object> map);

}