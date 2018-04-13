package com.hhnz.api.cache.model;

import java.util.Date;

import com.hhnz.crm.model.TEmployee;

public class LoginCacheModel extends CacheModel<TEmployee>{
	/**
	 * 登录imei号
	 */
	private String imei;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public LoginCacheModel(TEmployee em, String imei) {
		super();
		this.imei = imei;
		super.setT(em);
		super.setCacheTime(new Date());
	}
	
}
