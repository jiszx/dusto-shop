package com.hhnz.jco.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.hhnz.jco.enu.RfcExeStatus;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.util.ApplicationContextUtil;

public class TRfcExecute {
    private String serialNo;

    private String executeType;

    private String parameters;

    private String status;

    private Integer failureTimes;

    private String result;

    private Date createDate;

    private Date updateDate;

    private String callbackName;
    
    private String clobParameters;
    
    public static TRfcExecute transferFromDto(RfcRedoDto rfcDto){
    	TRfcExecute re = new TRfcExecute();
    	re.setCreateDate(new Date());
    	re.setExecuteType(rfcDto.getRfcExeType().name());
    	re.setFailureTimes(rfcDto.getFailureTimes());
    	String jsonParam = rfcDto.getJsonParam();
    	if(StringUtils.isNotBlank(jsonParam) && jsonParam.length()>3800){
    		re.setClobParameters(jsonParam);
    	}else{
    		re.setParameters(jsonParam);
    	}
    	re.setSerialNo(rfcDto.getSerialNo());
    	re.setStatus(RfcExeStatus.INPROGRESS.name());
    	re.setUpdateDate(re.getCreateDate());
    	if(rfcDto.getRfcCallback() != null){
    		re.setCallbackName(ApplicationContextUtil.getSimpleName(rfcDto.getRfcCallback().getClass()));
    	}
    	return re;
    }
    public void setParametersWithLength(String parameters) {
    	if(StringUtils.isNotBlank(parameters) && parameters.length()>3800){
    		this.setClobParameters(parameters);
    	}else{
    		this.setParameters(parameters);
    	}
    }
    
    public String getParametersWithLength() {
    	if(StringUtils.isNotBlank(parameters)){
    		return parameters;
    	}else{
    		return clobParameters;
    	}
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getExecuteType() {
        return executeType;
    }

    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFailureTimes() {
        return failureTimes;
    }

    public void setFailureTimes(Integer failureTimes) {
        this.failureTimes = failureTimes;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCallbackName() {
        return callbackName;
    }

    public void setCallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

	public String getClobParameters() {
		return clobParameters;
	}

	public void setClobParameters(String clobParameters) {
		this.clobParameters = clobParameters;
	}
}