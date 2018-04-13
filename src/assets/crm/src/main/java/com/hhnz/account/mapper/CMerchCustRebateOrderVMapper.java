package com.hhnz.account.mapper;

import java.util.List;

import com.hhnz.account.model.CMerchCustRebateOrderV;
import com.hhnz.account.model.CMerchCustRebateOrderVExample;

public interface CMerchCustRebateOrderVMapper {
    int countByExample(CMerchCustRebateOrderVExample example);

    List<CMerchCustRebateOrderV> selectByExample(CMerchCustRebateOrderVExample example);

}