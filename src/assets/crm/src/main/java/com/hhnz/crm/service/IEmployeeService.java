package com.hhnz.crm.service;

import java.util.Map;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.util.AjaxDTO;

/**
 * Created by yang on 2016-6-27.
 */
public interface IEmployeeService {
    TEmployee findByPK(String username)throws Exception;

    AjaxDTO findUserByCondation(Map<String,Object> map, AjaxDTO bean)throws Exception;

    Integer addEmployee(TEmployee model)throws Exception;

    Integer updateEmployee(TEmployee model)throws Exception;

    Integer delEmployee(Long id)throws Exception;

    TEmployee findById(Long id)throws Exception;

	int checkUser(String userName)throws Exception;

    int generateUser(CMerchCustBase merch, String loginName) throws Exception;
}
