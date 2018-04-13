package com.hhnz.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:下午1:55:35
 * @email:chaoyang.ren@foxmail.com
 */
public class ApplicationContextUtil implements ApplicationContextAware{
	private static final Log LOG  = LogFactory.getLog(ApplicationContextUtil.class);
	private static ApplicationContext applicationContext;
	static class ApplicationContextUtilHolder{
        static final ApplicationContextUtil INSTANCE = new ApplicationContextUtil();
    }
    public static ApplicationContextUtil getInstance(){
        return ApplicationContextUtilHolder.INSTANCE;
    }
    private ApplicationContextUtil(){}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextUtil.applicationContext = applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		return (T)applicationContext.getBean(name);
	}
	
	public static <T> T getBean(Class<T> requiredType){
		return (T)applicationContext.getBean(requiredType);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String name){
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		return (T) webApplicationContext.getServletContext().getAttribute(name);
	}
	
	/**
	 * 获取Class的simpleName
	 * @author: chaoyang.ren 
	 * @date:2016年11月18日  下午1:32:15
	 * @param cglibbean
	 * @return
	 */
	public static String getSimpleName(Class<?> cglibbean){
		String simpleName = cglibbean.getSimpleName();
		if(simpleName.contains("$$EnhancerBySpringCGLIB$$")){
			return cglibbean.getSuperclass().getSimpleName();
		}else{
			return simpleName;
		}
	}
	
	/**
	 * 获取当前登录用户</br>
	 * {@code return null} if exception occur.
	 * @author: chaoyang.ren 
	 * @date:2016年9月13日  上午9:45:13
	 * @return
	 */
	public static TEmployee getCurrentUser(){
		TEmployee emp = null;
		try {
			emp = (TEmployee) getSessionInfo(SessionKey.USER_INFO);
		} catch (Exception e) {
			LOG.error("获取当前用户失败！", e);
		}
		return emp;
	}
	
	/**
	 * 获取当前请求上下文的session信息</br>
	 * {@code return null} if exception occur.
	 * @author: chaoyang.ren 
	 * @date:2016年9月13日  上午9:41:35
	 * @param key session的key值
	 * @return
	 */
	public static Object getSessionInfo(String key){
		try {
			return getRequest().getSession().getAttribute(key);
		} catch (Exception e) {
			LOG.error("获取session信息失败！", e);
			return null;
		}
	}
	
	/**
	 * 获取当前请求</br>
	 * {@code return null} if exception occur.
	 * @author: chaoyang.ren 
	 * @date:2016年9月13日  上午11:10:09
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		HttpServletRequest request;
		try {
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			request = null;
			LOG.error("获取当前请求失败！", e);
		}
		return request;
	}
}
