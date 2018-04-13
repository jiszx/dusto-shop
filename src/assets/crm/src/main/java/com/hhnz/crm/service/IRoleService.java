package com.hhnz.crm.service;

import com.hhnz.crm.model.TRole;
import com.hhnz.util.AjaxDTO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yang on 2016-6-27.
 */
public interface IRoleService {
    AjaxDTO findRole(HashMap<String, Object> stringObjectHashMap, AjaxDTO bean)throws Exception;

    Integer addRole(TRole model)throws Exception;

    Integer updateRole(TRole model)throws Exception;

    Integer delRole(Long id)throws Exception;

    List<Long> findRoleAuths(Long id)throws Exception;

    Integer grantRole(Long id, List<Integer> auths)throws Exception;
}
