package com.hhnz.order.mapper;

import java.util.List;

import com.hhnz.order.model.OmOrderMakeSure;
import com.hhnz.order.model.OmOrderMakeSureExample;

public interface OmOrderMakeSureMapper {
    int countByExample(OmOrderMakeSureExample example);

    List<OmOrderMakeSure> selectByExample(OmOrderMakeSureExample example);

}