package com.hhnz.promotion.mapper;

import java.util.List;

import com.hhnz.promotion.model.CrmPromotionInvV;
import com.hhnz.promotion.model.CrmPromotionInvVExample;

public interface CrmPromotionInvVMapper {
    int countByExample(CrmPromotionInvVExample example);

    List<CrmPromotionInvV> selectByExample(CrmPromotionInvVExample example);

}