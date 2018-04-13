package com.hhnz.api.cache;

import java.util.Set;

public interface CacheService {
	
	/**
	 * 新增或修改信息
	 * @author: chaoyang.ren 
	 * @date:2016年8月8日  下午1:47:28
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value);
	
	/**
	 * 删除信息
	 * @author: chaoyang.ren 
	 * @date:2016年8月8日  下午1:47:45
	 * @param key
	 */
	public void delete(String key);
	
	/**
	 * 获取信息
	 * @author: chaoyang.ren 
	 * @date:2016年8月8日  下午1:47:53
	 * @param key
	 * @return
	 */
	public String get(String key);
	
	/**
	 * 获取信息
	 * @author: chaoyang.ren 
	 * @date:2016年8月8日  下午1:47:53
	 * @param key
	 * @return
	 */
	public <T> T get(String key, Class<T> clazz);
	
	/**
	 * 获取信息并重置延时
	 * @author: chaoyang.ren 
	 * @date:2016年8月8日  下午3:00:47
	 * @param key
	 * @return
	 */
	public String getAndExpire(String key);
	
	/**
	 * 获取信息并重置延时
	 * @author: chaoyang.ren 
	 * @date:2016年8月8日  下午3:00:47
	 * @param key
	 * @return
	 */
	public <T> T getAndExpire(String key, Class<T> clazz);
	
	/**
	 * 获取信息并删除
	 * @author: chaoyang.ren 
	 * @date:2016年8月8日  下午3:00:47
	 * @param key
	 * @return
	 */
	public String getAndRemove(String key);
	
	/**
	 * 获取信息并删除
	 * @author: chaoyang.ren 
	 * @date:2016年8月8日  下午3:00:47
	 * @param key
	 * @return
	 */
	public <T> T getAndRemove(String key, Class<T> clazz);
	
	/**
	 * 查找存在的key
	 * Returns all the keys matching the glob-style pattern 
	 * @author: chaoyang.ren 
	 * @date:2016年8月10日  下午3:24:52
	 * @param keyExp
	 * @return
	 */
	public Set<String> getKeys(String keyExp);

	/**
	 * 
	 * @author: chaoyang.ren 
	 * @date:2016年11月23日  下午1:20:34
	 * @param string
	 * @return
	 */
	public Long decr(String string);

	/**
	 * @author: chaoyang.ren 
	 * @date:2016年11月23日  下午2:11:43
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void put(String key, Object value, int seconds);

    boolean putIfAbsent(String key, Object value);
}
