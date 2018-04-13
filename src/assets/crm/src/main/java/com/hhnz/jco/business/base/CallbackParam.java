package com.hhnz.jco.business.base;

import java.util.Map;

/**
 * @author: chaoyang.ren
 * @date:2016年11月16日
 * @time:上午11:51:47
 * @email:chaoyang.ren@foxmail.com
 */
public class CallbackParam {
	private Long id;
	private String sapCode;
	private CommonResult result;
	private Map<String,Object> data;
	
	
	public static CallbackParam of(Long id){
		return new CallbackParam(id);
	}
	
	public static CallbackParam of(CommonResult result){
		CallbackParam param = new CallbackParam();
		param.setResult(result);
		return param;
	}
	
	public static CallbackParam of(Long id, CommonResult result){
		CallbackParam param = new CallbackParam();
		param.setId(id);
		param.setResult(result);
		return param;
	}
	
	public static CallbackParam of(Long id, String sapCode, CommonResult result){
		CallbackParam param = new CallbackParam();
		param.setId(id);
		param.setResult(result);
		param.setSapCode(sapCode);
		return param;
	}
	
	public static CallbackParam of(Long id, Map<String,Object> data, CommonResult result){
		CallbackParam param = new CallbackParam();
		param.setId(id);
		param.setResult(result);
		param.setData(data);
		return param;
	}
	public CallbackParam(){}
	public CallbackParam(Long id){
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSapCode() {
		return sapCode;
	}
	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}
	public CommonResult getResult() {
		return result;
	}
	public void setResult(CommonResult result) {
		this.result = result;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
