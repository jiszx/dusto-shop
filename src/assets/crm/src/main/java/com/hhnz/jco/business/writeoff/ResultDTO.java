package com.hhnz.jco.business.writeoff;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.BaseResultDTO;

/**
 * 核销结果信息
 * @author: chaoyang.ren
 * @date:2016年11月18日
 * @time:上午10:31:13
 * @email:chaoyang.ren@foxmail.com
 */
public class ResultDTO extends BaseResultDTO{
	/**
	 * SAP生成的核销财务凭证
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
