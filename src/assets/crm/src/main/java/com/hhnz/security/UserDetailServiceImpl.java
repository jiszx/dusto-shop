package com.hhnz.security;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private IEmployeeService service;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
		TEmployee user = null;
		try {
			user= this.service.findByPK(username);
			if(user == null){
				user = new TEmployee();
				user.setLoginName("123");
				user.setPasswd("");
			}else{
				auths.add(new GrantedAuthorityImpl(user.getRoleId() + ""));
			}
		} catch (Exception e) {
			user = new TEmployee();
			user.setLoginName("123");
			user.setPasswd("");
		}
		String password=user.getPasswd();
		User userDetail = new User(user.getLoginName(),password,"1".equals(user.getStates()),true,true,true,auths);
		return userDetail;
	}
}
