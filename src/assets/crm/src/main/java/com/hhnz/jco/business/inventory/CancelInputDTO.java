package com.hhnz.jco.business.inventory;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.BaseInput;

/**
 * 库存调拨取消传入参数
 * @author: chaoyang.ren
 * @date:Mar 8, 2017
 * @time:5:15:10 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
public class CancelInputDTO extends BaseInput{
	
	@SerializedName("IN_ZAPHNUM")
	private Long crmRelatedId;

	@Override
	public Serializable getCrmRelatedId() {
		return crmRelatedId;
	}

	public void setCrmRelatedId(Long crmRelatedId) {
		this.crmRelatedId = crmRelatedId;
	}
	
}
