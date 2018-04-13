package com.hhnz.crm.service;

import com.hhnz.crm.model.TDict;
import com.hhnz.util.AjaxDTO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yang on 2016/6/24.
 */
public interface IDictService {
    /**
     * 分页查询
     * @param stringObjectHashMap
     * @param page
     * @return
     * @throws Exception
     */
    AjaxDTO findDict(HashMap<String, Object> stringObjectHashMap, AjaxDTO page)throws Exception;

    int addDict(TDict model)throws Exception;

    Integer updateDict(TDict model)throws Exception;

    Integer delDict(Long id)throws Exception;

    TDict findById(Long id)throws Exception;

    List<TDict> findAll()throws Exception;

	/**
	 * @author: chaoyang.ren 
	 * @date:2016年12月16日  下午2:18:08
	 * @param cityId
	 * @return
	 */
	TDict findByCityId(Long cityId);

  List<TDict> findByName(String name);

  String code2Str(String code, List<TDict> dict);
}
