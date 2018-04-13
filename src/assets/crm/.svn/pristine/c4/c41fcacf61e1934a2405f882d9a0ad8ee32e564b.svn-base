package com.hhnz.crm.service;

import com.hhnz.crm.model.TAuthority;
import com.hhnz.crm.model.TAuthorityExt;

import java.util.List;

/**
 * Created by yang on 2016-6-26.
 */
public interface IAuthorityService {
    List<TAuthority> findAll()throws Exception;

    List<TAuthorityExt> findRoleAuth()throws Exception;

    List<TAuthority> findPubAuth()throws Exception;

	int update(TAuthority bean)throws Exception;

    int add(TAuthority bean)throws Exception;

    int delete(Long resId)throws Exception;

    int updateAll(List<TAuthority> auths)throws Exception;
}
