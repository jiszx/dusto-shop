package com.hhnz.api.cache.model;

import java.util.Date;

/**
 * 缓存model
 * @author: chaoyang.ren
 * @date:2016年8月8日
 * @time:下午2:48:02
 * @email:chaoyang.ren@foxmail.com
 * @param <T>
 */
public class CacheModel<T> {
	/**
	 * 缓存对象
	 */
	private T t;
	/**
	 * 缓存时间
	 */
	private Date cacheTime;
	
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public Date getCacheTime() {
		return cacheTime;
	}
	public void setCacheTime(Date cacheTime) {
		this.cacheTime = cacheTime;
	}
	
}
