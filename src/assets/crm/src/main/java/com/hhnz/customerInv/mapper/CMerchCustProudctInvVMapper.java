package com.hhnz.customerInv.mapper;

import java.util.List;

import com.hhnz.customerInv.model.CMerchCustProudctInvV;
import com.hhnz.customerInv.model.CMerchCustProudctInvVExample;

public interface CMerchCustProudctInvVMapper {
    int countByExample(CMerchCustProudctInvVExample example);
    
    List<CMerchCustProudctInvV> selectByExample(CMerchCustProudctInvVExample example);
    
    Integer updateInv();
}