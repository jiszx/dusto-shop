package com.hhnz.account.mapper;

import java.util.List;

import com.hhnz.account.model.CMerchCustAdjustV;
import com.hhnz.account.model.CMerchCustAdjustVExample;

public interface CMerchCustAdjustVMapper {
    int countByExample(CMerchCustAdjustVExample example);
    List<CMerchCustAdjustV> selectByExample(CMerchCustAdjustVExample example);
}