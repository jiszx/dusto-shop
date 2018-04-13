package com.hhnz.virtualwarehouse.dto;

/**
 * @author: chaoyang.ren
 * @date:2016年9月12日
 * @time:下午2:41:33
 * @email:chaoyang.ren@foxmail.com
 */
public class VirtualWarehouseQueryDTO {
	private String factoryCode;
	private String materialName;
	private String materialCode;
	private String custType;
	private String status;
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
}
