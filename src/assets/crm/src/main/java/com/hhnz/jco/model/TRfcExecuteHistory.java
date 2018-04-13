package com.hhnz.jco.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class TRfcExecuteHistory {
    private String serialNo;

    private String executeType;

    private String parameters;

    private String status;

    private Integer failureTimes;

    private String result;

    private Date createDate;

    private String callbackName;
    
    private String clobParameters;
    
    public void setParametersWithLength(String parameters) {
    	if(StringUtils.isNotBlank(parameters) && parameters.length()>3800){
    		this.setClobParameters(parameters);
    	}else{
    		this.setParameters(parameters);
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