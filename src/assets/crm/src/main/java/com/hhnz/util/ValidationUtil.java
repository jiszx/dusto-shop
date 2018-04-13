package com.hhnz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.commons.collections.CollectionUtils;

/**
 * 注解属性校验工具类
 * 
 * @author: chaoyang.ren
 * @date:2016年9月14日
 * @time:上午10:46:46
 * @email:chaoyang.ren@foxmail.com
 */
public class ValidationUtil {
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * 校验对象返回校验结果信息
	 * @author: chaoyang.ren 
	 * @date:2016年9月14日  上午11:06:07
	 * @param obj
	 * @return
	 */
	public static <T> ValidationResult validateEntity(T obj) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		if (CollectionUtils.isNotEmpty(set)) {
			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}
	
	/**
	 * 校验对象返回校验结果，校验通过为true,否则false
	 * @author: chaoyang.ren 
	 * @date:2016年9月14日  上午11:07:13
	 * @param obj
	 * @return
	 */
	public static <T> boolean validate(T obj) {
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		if (CollectionUtils.isEmpty(set)) {
			return true;
		}
		return false;
	}

	/**
	 * 校验对象属性返回校验结果信息
	 * @author: chaoyang.ren 
	 * @date:2016年9月14日  上午11:06:32
	 * @param obj
	 * @param propertyName
	 * @return
	 */
	public static <T> ValidationResult validateProperty(T obj, String propertyName) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
		if (CollectionUtils.isNotEmpty(set)) {
			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(propertyName, cv.getMessage());
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}

	/**
	 * 校验对象属性返回校验结果，校验通过为true,否则false
	 * @author: chaoyang.ren 
	 * @date:2016年9月14日  上午11:06:32
	 * @param obj
	 * @param propertyName
	 * @return
	 */
	public static <T> boolean validate(T obj, String propertyName) {
		Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
		if (CollectionUtils.isEmpty(set)) {
			return true;
		}
		return false;
	}
}
