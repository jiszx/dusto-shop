package com.hhnz.crm.dto;

import java.util.List;

import com.hhnz.crm.model.BusinessModelMoq;

/**
 * @author: chaoyang.ren  
 * @date:Mar 17, 2017  
 * @time:10:21:20 AM   
 * @email:chaoyang.ren@foxmail.com  
 * @version: 1.0
 */
public class MoqList {
	private List<BusinessModelMoq> moqs;

	public List<BusinessModelMoq> getMoqs() {
		return moqs;
	}

	public void setMoqs(List<BusinessModelMoq> moqs) {
		this.moqs = moqs;
	}
	
}

