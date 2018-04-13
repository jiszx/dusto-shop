package com.hhnz.cost.mapper;

import java.util.List;

import com.hhnz.cost.model.CrmCostMainV;
import com.hhnz.cost.model.CrmCostMainVExample;

public interface CrmCostMainVMapper {

    int countByExample(CrmCostMainVExample example);

    List<CrmCostMainV> selectByExample(CrmCostMainVExample example);

}