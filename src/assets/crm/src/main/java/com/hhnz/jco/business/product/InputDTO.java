package com.hhnz.jco.business.product;

import org.springframework.util.Assert;

import com.google.gson.annotations.SerializedName;
import com.hhnz.jco.RFCConstants;

/**
 * 调味品成品库存RFC输入参数
 * @author: chaoyang.ren
 * @date:2016年8月9日
 * @time:下午3:52:51
 * @email:chaoyang.ren@foxmail.com
 */
public class InputDTO {
	/**
	 * 物料编码
	 */
	@SerializedName("IN_MATNR")
	private String materialId;
	/**
	 * 物料名称
	 */
	@SerializedName("IN_DESC")
	private Long materialName;
	/**
	 * 工厂
	 */
	@SerializedName("IN_WERKS")
	private String factoryId;
	/**
	 * 是否时实(X是，空为否)
	 * if X, factoryId is required
	 */
	@SerializedName("IN_MARK")
	private String isOnTime;
	
	/**
	 * @param materialId
	 * @param materialName
	 * @param factoryId
	 * @param isOnTime
	 */
	public InputDTO(String materialId, Long materialName, String factoryId,
			String isOnTime) {
		Assert.hasLength(materialId,"materialId 不能为空!");
		if(RFCConstants.X_FLAG.equals(isOnTime)){
			Assert.hasLength(factoryId,"isOnTime 为'X'时，factoryId 不能为空!");
		}
		this.materialId = materialId;
		this.materialName = materialName;
		this.factoryId = factoryId;
		this.isOnTime = isOnTime;
	}
	
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public Long getMaterialName() {
		return materialName;
	}
	public void setMaterialName(Long materialName) {
		this.materialName = materialName;
	}
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getIsOnTime() {
		return isOnTime;
	}
	public void setIsOnTime(String isOnTime) {
		this.isOnTime = isOnTime;
	}
	
}
