package com.hhnz.jco.business.product;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.hhnz.crm.model.TMaterialFactory;
import com.hhnz.jco.business.base.BaseResultDTO;
import com.hhnz.jco.business.base.CommonResult;

/**
 * 调味品成品库存
 * 
 * @author: chaoyang.ren
 * @date:2016年8月9日
 * @time:下午2:17:06
 * @email:chaoyang.ren@foxmail.com
 */
@SuppressWarnings("unused")
public class ResultDTO extends BaseResultDTO{

	/**
	 * SAP返回消息类型 CHAR1 S 成功,E 错误
	 */
	@SerializedName("OUTPUT_LABST")
	private List<Item> items;

	class Item {
		@SerializedName("WERKS")
		private String factoryId;
		@SerializedName("MATNR")
		private String materialId;
		@SerializedName("MAKTX")
		private String materialName;
		@SerializedName("LABST")
		private String invNum;
		@SerializedName("GG_LABST")
		private String GG_LABST;
		@SerializedName("ZJ_LABST")
		private String ZJ_LABST;
		@SerializedName("ZY_LABST")
		private String ZY_LABST;
		@SerializedName("SY_LABST")
		private String SY_LABST;
		@SerializedName("DJ_LABST")
		private String DJ_LABST;
		@SerializedName("FP_LABST")
		private String FP_LABST;
		@SerializedName("CP_LABST")
		private String CP_LABST;
		@SerializedName("KY_LABST")
		private String KY_LABST;
		@SerializedName("MEINS")
		private String unit;
		
		/**
		 * map with local model.
		 * @author: chaoyang.ren 
		 * @date:2016年8月11日  上午10:34:16
		 * @return
		 */
		public TMaterialFactory toMaterialFactory(){
			TMaterialFactory mf = new TMaterialFactory();
			mf.setFacotryId(this.factoryId);
			mf.setMaterialId(materialId);
			mf.setInvNum(this.invNum);
			return mf;
		}

		public String getFactoryId() {
			return factoryId;
		}

		public void setFactoryId(String factoryId) {
			this.factoryId = factoryId;
		}

		public String getMaterialId() {
			return materialId;
		}

		public void setMaterialId(String materialId) {
			this.materialId = materialId;
		}

		public String getMaterialName() {
			return materialName;
		}

		public void setMaterialName(String materialName) {
			this.materialName = materialName;
		}

		public String getInvNum() {
			return invNum;
		}

		public void setInvNum(String invNum) {
			this.invNum = invNum;
		}

		public String getGG_LABST() {
			return GG_LABST;
		}

		public void setGG_LABST(String gG_LABST) {
			GG_LABST = gG_LABST;
		}

		public String getZJ_LABST() {
			return ZJ_LABST;
		}

		public void setZJ_LABST(String zJ_LABST) {
			ZJ_LABST = zJ_LABST;
		}

		public String getZY_LABST() {
			return ZY_LABST;
		}

		public void setZY_LABST(String zY_LABST) {
			ZY_LABST = zY_LABST;
		}

		public String getSY_LABST() {
			return SY_LABST;
		}

		public void setSY_LABST(String sY_LABST) {
			SY_LABST = sY_LABST;
		}

		public String getDJ_LABST() {
			return DJ_LABST;
		}

		public void setDJ_LABST(String dJ_LABST) {
			DJ_LABST = dJ_LABST;
		}

		public String getFP_LABST() {
			return FP_LABST;
		}

		public void setFP_LABST(String fP_LABST) {
			FP_LABST = fP_LABST;
		}

		public String getCP_LABST() {
			return CP_LABST;
		}

		public void setCP_LABST(String cP_LABST) {
			CP_LABST = cP_LABST;
		}

		public String getKY_LABST() {
			return KY_LABST;
		}

		public void setKY_LABST(String kY_LABST) {
			KY_LABST = kY_LABST;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}
		
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public Object getData() {
		return super.data;
	}

	@Override
	public void setData(Object data) {
		super.data = data;
	}
	
}
