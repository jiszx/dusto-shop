package com.hhnz.config.service;

import com.hhnz.crm.model.TDict;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by 杨 on 2016/12/20.
 */
public interface IModelAndBrandService  {
    public List<TDict> findDictByName(String virtual_warehouse_code) throws Exception;

    List<String> findBrand()throws Exception;

    List<String> findModelBrand(String model)throws Exception;

    Integer modelGrand(String id, List<String> auths)throws Exception;
}
