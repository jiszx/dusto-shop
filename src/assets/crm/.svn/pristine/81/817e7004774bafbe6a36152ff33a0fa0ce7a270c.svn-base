package com.hhnz.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hhnz.api.cache.CacheService;
import com.hhnz.api.cache.model.LoginCacheModel;
import com.hhnz.crm.util.SessionKey;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private CacheService cacheService;

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	  return true;
//		if("/api/login".indexOf(request.getRequestURI()) == 0){
//			return true;
//		}
//		// 从header中得到token
//		String authorization = request.getHeader("accesstoken");
//		// 验证token
//		LoginCacheModel lcm = cacheService.getAndExpire(authorization,LoginCacheModel.class);
//		if (lcm != null) {
//			//stores user in request
//			request.setAttribute(SessionKey.API_USER_INFO, lcm.getT());
//			// token验证成功
//			return true;
//		} else {
//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			return false;
//		}
	}
}
