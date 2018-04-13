package com.hhnz.aop.log.advice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.monitor.model.TLog;
import com.hhnz.monitor.service.ILogService;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.JsonUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年9月1日
 * @time:上午11:55:02
 * @email:chaoyang.ren@foxmail.com
 */
@Component
@Aspect
public class ControllerLogAdvice {
	private static final Log LOG  = LogFactory.getLog(ControllerLogAdvice.class);
	private static final String LINE = " --------------------------------------------------";
	private static final String LINE_BREAK = "\r\n";
	private static final String VERTICAL = "丨";
	private static final String WHITE_SPACE = " ";
	
	@Autowired
	private ILogService logService;
	
	/**
	 * 执行带有controller注解类里的方法时执行此方法的内容
	 * @author: chaoyang.ren 
	 * @date:2016年9月1日  下午4:12:54
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("within(@org.springframework.stereotype.Controller *)")
	public Object arroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		HttpServletRequest request = ApplicationContextUtil.getRequest();
		Map<String,String> logInfo = new HashMap<String,String>();
		TLog log = new TLog();
		TEmployee o = ApplicationContextUtil.getCurrentUser();
		logInfo.put("operation user", o==null?"未登录！":o.getLoginName());
		if(o != null){
			log.setOperId(o.getId().toString());
		}
		log.setOpTs(new Date());
		String ip = getRemoteHost(request);
		logInfo.put("request from ip", ip);
		log.setAccessIp(ip);
        Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();  
        String className = targetClass.getSimpleName();
        logInfo.put("className", className);
        log.setClassName(className);
        String methodName = proceedingJoinPoint.getSignature().getName();
        logInfo.put("methodName", methodName);
        log.setMethodName(methodName);
        Object[] args = proceedingJoinPoint.getArgs();
        int i = 0;
        StringBuilder paramsSb = new StringBuilder();
		for (Object obj : args) {
			i++;
			if(i > 1){
				paramsSb.append(",");
			}
			//转换项目中的类
			if(obj == null){
				paramsSb.append("");
				continue;
			}
			try {
				if(obj.getClass().getPackage().getName().indexOf("com.hhnz") != -1){
					paramsSb.append(JsonUtil.toJSON(obj));
				}else{
					paramsSb.append(obj.toString());
				}
			} catch (Exception e1) {
				paramsSb.append("error occurred while serialize the parameter with json format!");
			}
		}
        String params = paramsSb.toString();
		logInfo.put("params",params);
        log.setOpParams(params);
        try {
            Object result = proceedingJoinPoint.proceed();
            return result;
        } catch (Exception e) {
        	LOG.error(e.getMessage(), e);
        	log.setExceptionInfo(e.getClass().getSimpleName()+","+e.getMessage());
        	logInfo.put("exception",e.getClass().getSimpleName()+",msg:"+e.getMessage());
            throw e;
        } finally {
        	try {
				logService.save(log);
			} catch (Exception e) {
				LOG.error("database日志记录失败",e);
			}
        	try {
				LOG.info(mapToTableInfo(logInfo));
			} catch (Exception e) {
				LOG.error("log4j日志记录失败",e);
			}
        }
	}
	
	/**
	 * 把key-value集构造成table样式
	 * @author: chaoyang.ren 
	 * @date:2016年9月1日  下午4:16:42
	 * @param params
	 * @return
	 */
	private String mapToTableInfo(Map<String,String> params){
		int wide_column1 = 20;
		int wide_column2 = 26;
		StringBuffer table = new StringBuffer();
		table.append(LINE_BREAK).append(LINE).append(LINE_BREAK);
		for (Entry<String, String> entry : params.entrySet()) {
			table.append(VERTICAL).append(WHITE_SPACE);
			String key = entry.getKey();
			table.append(key);
			for (int i = 0; i< wide_column1-key.length(); i++) {
				table.append(WHITE_SPACE);
			}
			table.append(VERTICAL);
			String value = StringUtils.trimToEmpty(entry.getValue());
			table.append(value);
			for (int i = 0; i< wide_column2-value.length(); i++) {
				table.append(WHITE_SPACE);
			}
			table.append(VERTICAL).append(LINE_BREAK).append(LINE).append(LINE_BREAK);
		}
		return table.toString();
	}
	
	private String getRemoteHost(HttpServletRequest request){
		if(request == null){
			return null;
		}
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
}
