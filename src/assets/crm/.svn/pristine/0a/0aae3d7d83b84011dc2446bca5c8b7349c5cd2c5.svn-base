package com.hhnz.pub.service;

import com.hhnz.pub.model.TArea;

import java.util.List;

/**
 * Created by yang on 2016-7-6.
 */
public interface IAreaService {

	/**
	 * 查询所有省市列表
	 * @return
	 * @throws Exception
	 */
    List<TArea> findAll()throws Exception;

    /**
     * 根据父ID查询区域列表
     * @param pid
     * @return
     * @throws Exception
     */
	List<TArea> findAll(String pid) throws Exception;

	/**
	 * @author: chaoyang.ren 
	 * @date:2016年12月13日  下午2:31:36
	 * @param cityId
	 * @return
	 */
	String findRDCCodeByCity(String cityId);

	List<TArea> findallByLevel(String level)throws Exception;

    int updateArea(TArea bean)throws Exception;

	/**
	 * @author: chaoyang.ren 
	 * @date:Jan 4, 2017  4:56:10 PM
	 * @param id
	 * @return
	 */
	TArea findById(String id);
}
