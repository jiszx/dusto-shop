package com.hhnz.account.mapper;

import java.util.List;

import com.hhnz.account.model.CMerchBalancesV;
import com.hhnz.account.model.CMerchBalancesVExample;

public interface CMerchBalancesVMapper {
    int countByExample(CMerchBalancesVExample example);

    List<CMerchBalancesV> selectByExample(CMerchBalancesVExample example);

}