package com.hhnz.organization.service;

import java.util.List;

/**
 * Created by 杨 on 2016/12/27.
 */
public interface ICompanySetService {
    List<String> findCompanyOrg(String id)throws Exception;

    Integer grantOrg(String id, List<String> auths)throws Exception;

    String findCompanyCode(String id)throws Exception;
}
