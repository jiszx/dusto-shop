package com.hhnz.customerInv.mapper;

import java.util.List;

import com.hhnz.customerInv.model.CMerchInvLogV;
import com.hhnz.customerInv.model.CMerchInvLogVExample;

public interface CMerchInvLogVMapper {
    int countByExample(CMerchInvLogVExample example);

    List<CMerchInvLogV> selectByExample(CMerchInvLogVExample example);

}