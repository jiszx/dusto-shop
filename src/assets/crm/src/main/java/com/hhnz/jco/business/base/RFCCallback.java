package com.hhnz.jco.business.base;



/**
 * rfc处理参数接口
 */
public interface RFCCallback{
	/**
	 * 返回错误结果之后的回调
	 * @author: chaoyang.ren 
	 * @date:2016年11月11日  下午5:00:26
	 * @param result
	 */
	public void errorCallBack(CallbackParam result);
	/**
	 * 返回正确结果之后的回调
	 * @author: chaoyang.ren 
	 * @date:2016年11月11日  下午4:57:30
	 */
	public void successCallBack(CallbackParam result);
}
