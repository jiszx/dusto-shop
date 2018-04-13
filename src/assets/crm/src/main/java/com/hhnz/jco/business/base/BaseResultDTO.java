package com.hhnz.jco.business.base;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.business.base.CommonResult;

/**
 * @author: chaoyang.ren
 * @date:2016年8月19日
 * @time:下午3:17:07
 * @email:chaoyang.ren@foxmail.com
 */
public abstract class BaseResultDTO {
	/**
	 * 可能需要返回的参数
	 */
	protected Object data;
	/**
	 * SAP返回消息类型 CHAR1 S 成功,E 错误
	 */
	protected CommonResult result;
	/**
	 * SAP返回消息TABLE类型
	 */
	@SerializedName("ET_RETURN")
	protected List<CommonResult> results;
	public CommonResult getResult() {
		//table ET_RETURN just contains one row.
		if(results != null && results.size()>0){
			this.result = results.get(0);
		}
		return result;
	}
	public void setResults(List<CommonResult> results) {
		this.results = results;
	}
	public List<CommonResult> getResults() {
		return results;
	}
	public void setResult(CommonResult result) {
		this.result = result;
	}
	public abstract Object getData();
	public abstract void setData(Object data);
}
