package com.hhnz.cost.mapper;

import java.util.List;

import com.hhnz.cost.model.CrmCostLogV;
import com.hhnz.cost.model.CrmCostLogVExample;

public interface CrmCostLogVMapper {
    int countByExample(CrmCostLogVExample example);
    List<CrmCostLogV> selectByExample(CrmCostLogVExample example);

}