package com.hhnz.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 参数过滤器（去掉空和特殊字符串）
 * @author hhnz-skevin
 *
 */
public class CrmPramasFilter  extends OncePerRequestFilter{
	@Override  
    protected void doFilterInternal(HttpServletRequest request,  
            HttpServletResponse response, FilterChain filterChain)  
            throws ServletException, IOException {  
            ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);  
            filterChain.doFilter(requestWrapper, response);  
    } 
}
