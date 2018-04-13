package com.hhnz.config.service;

import com.hhnz.crm.model.TDict;

import java.util.List;

/**
 * Created by 杨 on 2016/12/16.
 */
public interface IAreaRDCConfigService {
    List<TDict> findDictByName(String virtual_warehouse_code)throws Exception;

    List<String> findRdcArea(String rdc)throws Exception;

    Integer rdcGrantArea(String id, List<String> auths)throws Exception;
}
