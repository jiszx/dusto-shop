package com.hhnz.pub.service;

import java.util.List;

import com.hhnz.pub.model.Change;
import com.hhnz.pub.model.ChangeVar;


/**
 * 
 * 商户变更信息service
 * 
 */
public interface IChangeService {

	/**
	 * 商户信息变更, 添加商户变更信息
	 * 
	 * @param pojo
	 *            Change对象
	 * @return 添加标志
	 *             异常信息
	 */
	public String add(Change pojo);

	/**
	 * 商户信息变更, 删除商户变更信息
	 * 
	 * @param clazz
	 *            Change对象
	 * @param id
	 *            主键
	 * @return 删除标志
	 *             异常信息
	 */
	public Integer del(Class<Change> clazz, String id);

	/**
	 * 根据变更ID查询变更信息
	 * 
	 * @param entry
	 *            Change对象
	 * @param id
	 *            主键
	 * @return 变更信息
	 *             异常信息
	 */
	public Change findById(Long id);

	/**
	 * 商户信息变更, 更新商户变更信息
	 * 
	 * @param vo
	 *            Change对象
	 *            
	 * @return 更新标志
	 *             异常信息
	 */
	public Integer updateChange(Change vo);

	/**
	 * 获取商户变更编号
	 * 
	 * @return 变更编号
	 * @throws Exception
	 *             异常信息
	 */
	public Long getChangeId();

	/**
	 * 获取变更源信息
	 * 
	 * @param dto
	 *            变更编号
	 * @param obj
	 * @return
	 * @throws Exception
	 *             异常信息
	 */
	public List<ChangeVar> findChangeVars(Long changeId, String obj);


	public Integer deleteChangeVar(String changeId);
	/**
	 * 
	 * @param clazz
	 * @param changeId
	 * @return
	 * @throws Exception
	 */
	public <T> T getChangeBean(Class<T> clazz, Long changeId);

	/**
	 * @author: chaoyang.ren 
	 * @date:Jan 5, 2017  9:22:17 AM
	 * @param clazz
	 * @param id
	 */
	public <T> T getLatestChangeBean(Class<T> clazz, String id);

	/**
	 * @author: chaoyang.ren 
	 * @date:Jan 5, 2017  10:16:09 AM
	 * @param objName
	 * @param objKey
	 */
	public Change getLatestChange(String objName, String objKey);

	/**
	 * @author: chaoyang.ren 
	 * @date:Jan 5, 2017  4:47:54 PM
	 * @param change
	 */
	public void save(Change change);
}
