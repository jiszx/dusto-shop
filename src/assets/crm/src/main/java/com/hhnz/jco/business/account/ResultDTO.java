package com.hhnz.jco.business.account;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.BaseResultDTO;

/**
 * 账户调整结果信息
 * @author: chaoyang.ren
 * @date:2016年9月21日
 * @time:下午4:47:31
 * @email:chaoyang.ren@foxmail.com
 */
public class ResultDTO extends BaseResultDTO{

	/**
	 * sap财务凭证号
	 */
	@SerializedName("OUT_BELNR")
	private String voucher;
	
	
	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	@Override
	public Object getData() {
		return getVoucher();
	}

	@Override
	public void setData(Object data) {
		super.data = getVoucher();
	}
	
}
