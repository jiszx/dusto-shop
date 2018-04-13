package com.hhnz.organization.service;

import com.hhnz.customer.model.CMerchCustBaseV;

import java.util.List;

/**
 * Created by 杨 on 2016/12/28.
 */
public interface ICompanyAreaService {
    List<CMerchCustBaseV> findCompany()throws Exception;

    List<String> findCompanyArea(String id)throws Exception;

    Integer grantArea(String id, List<String> auths)throws Exception;
}
