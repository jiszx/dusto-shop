package com.hhnz.api.cache.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hhnz.api.cache.CacheService;
import com.hhnz.util.JsonUtil;

@Deprecated
@Component("mapCacheService")
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MapCacheServiceImpl implements CacheService{
	private Map<String,Object> cache = Collections.synchronizedMap(new LinkedHashMap<String, Object>());
	
	@Override
	public void put(String key, Object value) {
		cache.put(key, JsonUtil.toJSON(value));
	}

	@Override
	public void delete(String key) {
		cache.remove(key);
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		String s = (String) cache.get(key);
		if(StringUtils.isBlank(s)){
			return null;
		}
		return JsonUtil.fromJSON(s, clazz);
	}

	@Override
	public <T> T getAndExpire(String key, Class<T> clazz) {
		return this.get(key, clazz);
	}

	@Override
	public String get(String key) {
		return get(key, String.class);
	}

	@Override
	public String getAndExpire(String key) {
		return get(key);
	}

	@Override
	public Set<String> getKeys(String keyExp) {
		//make * stands for any char and repeat any times.
		if(keyExp.indexOf(".*") == -1 && keyExp.indexOf("*") != -1){
			keyExp = keyExp.replace("*", ".*");
		}
		Set<String> allKeys = cache.keySet();
		Set<String> result = new HashSet<String>();
		for (String key : allKeys) {
			Pattern pattern = Pattern.compile(keyExp);
			Matcher matcher = pattern.matcher(key);
			if(matcher.matches()){
				result.add(key);
			};
		}
		return result;
	}

	@Override
	public String getAndRemove(String key) {
		String result = this.get(key);
		this.delete(key);
		return result;
	}

	@Override
	public <T> T getAndRemove(String key, Class<T> clazz) {
		T result = this.get(key,clazz);
		this.delete(key);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hhnz.api.cache.CacheService#decr(java.lang.String)
	 */
	@Override
	public Long decr(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hhnz.api.cache.CacheService#put(java.lang.String, java.lang.Object, int)
	 */
	@Override
	public void put(String key, Object value, int seconds) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean putIfAbsent(String key, Object value) {
  	  throw new UnsupportedOperationException();
	}
	
}
