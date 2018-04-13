package com.hhnz.promotion.mapper;

import java.util.List;

import com.hhnz.promotion.model.CrmPromotionStoresV;
import com.hhnz.promotion.model.CrmPromotionStoresVExample;

public interface CrmPromotionStoresVMapper {
    int countByExample(CrmPromotionStoresVExample example);
    List<CrmPromotionStoresV> selectByExample(CrmPromotionStoresVExample example);
}