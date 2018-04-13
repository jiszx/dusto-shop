package com.hhnz.promotion.mapper;

import java.util.List;

import com.hhnz.promotion.model.CrmPromotionPurV;
import com.hhnz.promotion.model.CrmPromotionPurVExample;

public interface CrmPromotionPurVMapper {
    int countByExample(CrmPromotionPurVExample example);
    List<CrmPromotionPurV> selectByExample(CrmPromotionPurVExample example);

}