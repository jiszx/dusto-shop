package com.hhnz.security;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hhnz.aop.token.annotation.Token;

/**
 * 拦截重复的请求
 * @author: chaoyang.ren
 * @date:2016年10月18日
 * @time:下午6:23:03
 * @email:chaoyang.ren@foxmail.com
 */
public class TokenInterceptor extends HandlerInterceptorAdapter{
	private final String TOKEN = "_token";
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                if (isRepeatSubmit(request)) {
                    String msg = "操作已处理，请不要频繁操作！";
                    writeMessageUtf8(response, msg);
                    return false; 
                }
                request.getSession().removeAttribute(TOKEN);
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

	/**
	 * 根据token判断是否是重复提交
	 * @author: chaoyang.ren 
	 * @date:2016年10月19日  上午10:24:10
	 * @param request
	 * @return
	 */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute(TOKEN);
        if (StringUtils.isBlank(serverToken)) {
            return true;
        }
        String clinetToken = request.getParameter(TOKEN);
        if (StringUtils.isBlank(clinetToken)) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
    
    /**
     * 回写信息
     * @author: chaoyang.ren 
     * @date:2016年10月19日  上午10:24:27
     * @param response
     * @param msg
     * @throws IOException
     */
    private void writeMessageUtf8(HttpServletResponse response, String msg) throws IOException  
    {  
        try  
        {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(msg);
        }
        finally{  
            response.getWriter().close();  
        }  
    } 
}