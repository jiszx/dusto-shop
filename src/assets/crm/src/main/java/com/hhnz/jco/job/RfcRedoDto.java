package com.hhnz.jco.job;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.model.TRfcExecute;
import com.hhnz.util.ApplicationContextUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:上午11:20:51
 * @email:chaoyang.ren@foxmail.com
 */
public class RfcRedoDto{
	public RfcRedoDto(){}
	
	public RfcRedoDto(String jsonParam, RfcExeType rfcExeType){
		this.jsonParam = jsonParam;
		this.serialNo = serialGenerator();
		this.rfcExeType = rfcExeType;
	}
	
	public RfcRedoDto(String jsonParam, RfcExeType rfcExeType, RFCCallback callback){
		this.jsonParam = jsonParam;
		this.serialNo = serialGenerator();
		this.rfcExeType = rfcExeType;
		this.rfcCallback = callback;
	}
	
	public RfcRedoDto(String jsonParam, String serialNo, RfcExeType rfcExeType){
		this.jsonParam = jsonParam;
		this.serialNo = serialNo;
		this.rfcExeType = rfcExeType;
	}
	
	public RfcRedoDto(String jsonParam, String serialNo, RfcExeType rfcExeType, RFCCallback callback){
		this.jsonParam = jsonParam;
		this.serialNo = serialNo;
		this.rfcExeType = rfcExeType;
		this.rfcCallback = callback;
	}
	
	public RfcRedoDto(TRfcExecute re){
		this.jsonParam = re.getParametersWithLength();
		this.serialNo = re.getSerialNo();
		this.rfcExeType = RfcExeType.toEnum(re.getExecuteType());
		if(StringUtils.isNotBlank(re.getCallbackName())){
			this.rfcCallback = ApplicationContextUtil.getBean(re.getCallbackName());
		}
		if(re.getFailureTimes() != null){
			this.failureTimes = re.getFailureTimes();
		}
	}
	
	/**
	 * serialized json input param.
	 */
	private String jsonParam;
	
	/**
	 * 执行失败时的随机序列号
	 */
	private String serialNo;
	
	/**
	 * rfc执行类别
	 */
	private RfcExeType rfcExeType;
	
	/**
	 * 失败次数
	 */
	private int failureTimes = 0;
	
	/**
	 * 最高失败尝试次数
	 * default to 5.
	 */
	private int maxAttempt = 5;
	
	/**
	 * 回调
	 */
	private RFCCallback rfcCallback;
	/**
	 * rfc执行序列号生成
	 * @author: chaoyang.ren 
	 * @date:2016年8月10日  上午11:51:00
	 * @return
	 */
	public String serialGenerator(){
		return RFCConstants.CACHE_KEY+RandomStringUtils.random(5, 48, 123, true, true)+System.currentTimeMillis()+RandomStringUtils.random(5, 48, 58, false, true);
	}
	
	@Override
	public String toString() {
		return "RfcRedoDto [jsonParam=" + jsonParam + ", serialNo=" + serialNo
				+ ", rfcExeType=" + rfcExeType + ", failureTimes="
				+ failureTimes + ", maxAttempt=" + maxAttempt + "]";
	}

	public String getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public RfcExeType getRfcExeType() {
		return rfcExeType;
	}

	public void setRfcExeType(RfcExeType rfcExeType) {
		this.rfcExeType = rfcExeType;
	}

	public String getJsonParam() {
		return jsonParam;
	}

	public void setJsonParam(String jsonParam) {
		this.jsonParam = jsonParam;
	}

	public int getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(int failureTimes) {
		this.failureTimes = failureTimes;
	}

	public int getMaxAttempt() {
		return maxAttempt;
	}

	public void setMaxAttempt(int maxAttempt) {
		this.maxAttempt = maxAttempt;
	}

	public RFCCallback getRfcCallback() {
		return rfcCallback;
	}

	public void setRfcCallback(RFCCallback rfcCallback) {
		this.rfcCallback = rfcCallback;
	}
	
}
