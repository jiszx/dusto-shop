package com.hhnz.util;

import java.beans.PropertyDescriptor;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

/**
 * extends of {@link org.springframework.beans.BeanUtils}
 * @author: chaoyang.ren
 * @date:Jan 6, 2017
 * @time:2:35:02 PM
 * @email:chaoyang.ren@foxmail.com
 */
public class BeanUtils extends org.springframework.beans.BeanUtils{
	/**
	 * copy none null properties from source to avoid a none-null value erased.
	 * @author: chaoyang.ren 
	 * @date:Jan 6, 2017  2:51:38 PM
	 * @param source
	 * @param target
	 * @param ignoreProperties
	 * @throws BeansException
	 */
	public static void copyPropertiesIgnoreNil(Object source, Object target, String... ignoreProperties) throws BeansException {
		final BeanWrapper src = new BeanWrapperImpl(source);
    	PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();
    	Set<String> emptyNames = new HashSet<String>();
    	for (PropertyDescriptor pd : propertyDescriptors) {
    		String propertyName = pd.getName();
    		Object srcObj = src.getPropertyValue(propertyName);
    		if(srcObj == null){
    			emptyNames.add(propertyName);
    		}
		}
    	if(ignoreProperties.length > 0){
    		Collections.addAll(emptyNames, ignoreProperties);
    	}
    	String[] nips = new String[emptyNames.size()];
    	org.springframework.beans.BeanUtils.copyProperties(source, target, emptyNames.toArray(nips));
	}
}
