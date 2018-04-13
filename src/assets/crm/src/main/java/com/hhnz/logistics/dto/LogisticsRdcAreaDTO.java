package com.hhnz.logistics.dto;

import com.hhnz.util.io.excel.util.excel.ExcelField;

public class LogisticsRdcAreaDTO {
	@ExcelField(order = 0, header = "用户名称")
	private  String username;
	private Long userId;
	@ExcelField(order = 1, header = "RDC")
	private String rdcCode;
	@ExcelField(order = 2, header = "RDC描述")
	private String rdcName;
	private String areaId;
	@ExcelField(order = 3, header = "地区名称")
	private String areaName;
	private Long logisticsRdcId;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRdcCode() {
		return rdcCode;
	}
	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getLogisticsRdcId() {
		return logisticsRdcId;
	}
	public void setLogisticsRdcId(Long logisticsRdcId) {
		this.logisticsRdcId = logisticsRdcId;
	}
	public String getRdcName() {
		return rdcName;
	}
	public void setRdcName(String rdcName) {
		this.rdcName = rdcName;
	}
	
	
}
