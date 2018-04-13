package com.hhnz.security;

import com.hhnz.crm.model.TAuthority;
import com.hhnz.crm.model.TAuthorityExt;
import com.hhnz.crm.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


@Component("securityMetadataSource")
@Transactional(propagation=Propagation.REQUIRED)
public class SecurityMetadataSourceServiceImpl implements
		FilterInvocationSecurityMetadataSource {
	@Autowired
	protected IAuthorityService service;
	public static AtomicBoolean flag= new AtomicBoolean(true);
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	// 用来保存从数据库中读取的资源
	private Map<String, Collection<ConfigAttribute>> sresourceMap = null;

	public boolean supports(Class<?> clazz) {
		return true;
	}

	// 加载从数据库中读取的资源
	public Map<String, Collection<ConfigAttribute>> load() {
		Map<String, Collection<ConfigAttribute>> resourceMap = new java.util.Hashtable<String, Collection<ConfigAttribute>>();
		try {
			List<TAuthorityExt> roles=this.service.findRoleAuth();
			List<ConfigAttribute> admin = new ArrayList<ConfigAttribute>();
			List<ConfigAttribute> allUser = new ArrayList<ConfigAttribute>();
			admin.add(new SecurityConfig(1+""));
			resourceMap.put("**", admin);
			for(TAuthorityExt role:roles){
				if(allUser.contains(new SecurityConfig(role.getRoleId()+""))){
					
				}else{
					allUser.add(new SecurityConfig(role.getRoleId()+""));
				}
				if(role.getRoleId() == 1){continue;}
				if(!StringUtils.hasLength(role.getResUrl())){continue;}
				if(resourceMap.containsKey(role.getResUrl())){//处理一级
					resourceMap.get(role.getResUrl()).add(new SecurityConfig(role.getRoleId()+""));
				}else{
					resourceMap.put(role.getResUrl(),new ArrayList<ConfigAttribute>());
					resourceMap.get(role.getResUrl()).add(new SecurityConfig(role.getRoleId()+""));
				}
				
				if(StringUtils.hasLength(role.getOtherRes())){
					String[] others = role.getOtherRes().split(",");
					for (String st : others) {
						if(resourceMap.containsKey(st)){//处理一级
							resourceMap.get(st).add(new SecurityConfig(role.getRoleId()+""));
						}else{
							resourceMap.put(st,new ArrayList<ConfigAttribute>());
							resourceMap.get(st).add(new SecurityConfig(role.getRoleId()+""));
						}
					}
				}
			}
			List<TAuthority> pubicAuth = this.service.findPubAuth();
			for (TAuthority ta : pubicAuth) {
				if(StringUtils.hasLength(ta.getResUrl())){
					resourceMap.put(ta.getResUrl(), allUser);
					if(StringUtils.hasLength(ta.getOtherRes())){
						String[] others = ta.getOtherRes().split(",");
						for (String st : others) {
							resourceMap.put(st, allUser);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourceMap;
	}

	// 返回请求资源所需要的权限 ，这个方法是类的核心
	public Collection<ConfigAttribute> getAttributes(Object obj)throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) obj;
		String requestUrl = ((FilterInvocation) obj).getRequestUrl();
		if(requestUrl.startsWith("/")){
			requestUrl = requestUrl.substring(1);
		}
		Map<String, Collection<ConfigAttribute>> map = null;
		if(flag.get()){
			flag.set(false);
			map = this.load();
			this.sresourceMap = map;
		}else{
			map = this.sresourceMap;
		}
		if(map == null){
			return null;
		}
		Iterator<String> iterator = map.keySet().iterator();
		Collection<ConfigAttribute> maxConfig=new HashSet<ConfigAttribute>();
		while (iterator.hasNext()) {
			String requestURL = iterator.next();
//			System.err.println(requestURL+"----->"+requestUrl);
			if(urlMatcher.pathMatchesUrl(requestURL, requestUrl)){
				maxConfig.addAll(map.get(requestURL));
			}
		}
		if(maxConfig!=null&&maxConfig.size()>0){
			return maxConfig;
		}else{
			maxConfig.add(new SecurityConfig("need_auth"));
			return maxConfig;
//			return null;
		}
		
	}
	// 获取所有权限的配置属性
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

}
