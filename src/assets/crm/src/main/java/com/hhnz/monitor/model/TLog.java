package com.hhnz.monitor.model;

import java.math.BigDecimal;
import java.util.Date;

public class TLog {
    private BigDecimal id;

    private String operId;

    private String accessIp;

    private String className;

    private String methodName;

    private String opParams;

    private String exceptionInfo;

    private Date opTs;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getOpParams() {
        return opParams;
    }

    public void setOpParams(String opParams) {
        this.opParams = opParams;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public Date getOpTs() {
        return opTs;
    }

    public void setOpTs(Date opTs) {
        this.opTs = opTs;
    }
}