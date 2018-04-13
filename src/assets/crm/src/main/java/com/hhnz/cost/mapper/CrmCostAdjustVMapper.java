package com.hhnz.cost.mapper;

import java.util.List;

import com.hhnz.cost.model.CrmCostAdjustV;
import com.hhnz.cost.model.CrmCostAdjustVExample;

public interface CrmCostAdjustVMapper {
    int countByExample(CrmCostAdjustVExample example);

    List<CrmCostAdjustV> selectByExample(CrmCostAdjustVExample example);

	Integer updateCostMain();

}