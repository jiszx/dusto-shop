package com.hhnz.jco.business.inventory;

import java.util.List;

import com.hhnz.jco.business.base.BaseResultDTO;
import com.hhnz.jco.business.base.CommonResult;

/**
 * @author: chaoyang.ren
 * @date:2016年8月19日
 * @time:下午3:17:07
 * @email:chaoyang.ren@foxmail.com
 */
public class ResultDTO extends BaseResultDTO{
	
	public CommonResult getResult() {
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
	
	@Override
	public Object getData(){
		return null;
	}
	@Override
	public void setData(Object data) {
	}
}
