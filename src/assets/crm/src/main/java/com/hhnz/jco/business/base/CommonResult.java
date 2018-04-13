package com.hhnz.jco.business.base;

import com.hhnz.jco.RFCConstants;

/**
 * RFC接口返回主信息
 * S 成功,E 错误
 * @author: chaoyang.ren
 * @date:2016年8月9日
 * @time:下午2:39:23
 * @email:chaoyang.ren@foxmail.com
 */
public class CommonResult{
	/**
	 * SAP返回消息类型
	 * CHAR1
	 * S 成功,E 错误
	 */
	private String TYPE;
	/**
	 * SAP返回消息文本
	 * CHAR220
	 */
	private String MESSAGE;
	
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getMESSAGE() {
		return MESSAGE;
	}
	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}
	/**
	 * @param tYPE
	 * @param mESSAGE
	 */
	public CommonResult(String tYPE, String mESSAGE) {
		TYPE = tYPE;
		MESSAGE = mESSAGE;
	}
	public CommonResult() {}
	
	public static CommonResult error(String msg){
		return new CommonResult(RFCConstants.ERROR_FLAG, msg);
	}
	public static CommonResult error(Throwable t){
		return new CommonResult(RFCConstants.ERROR_FLAG, t.getMessage());
	}
	public static CommonResult success(String msg){
		return new CommonResult(RFCConstants.SUCCESS_FLAG, msg);
	}
	public static CommonResult success(){
		return new CommonResult(RFCConstants.SUCCESS_FLAG, "执行成功！");
	}
}
