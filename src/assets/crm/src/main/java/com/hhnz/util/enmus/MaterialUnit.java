package com.hhnz.util.enmus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 物料单位枚举
 * @author: chaoyang.ren
 * @date:2016年11月16日
 * @time:下午5:29:30
 * @email:chaoyang.ren@foxmail.com
 */
public enum MaterialUnit {
	TO(1000,"吨"),
	KG(1,"千克"),
	G(0.001,"克")/*,
	BAG(0,"袋")*/;
	MaterialUnit(double standardWeight, String desc){
		this.standardWeight = standardWeight;
		this.desc = desc;
	}
	
	/**
	 * 由名称转换为枚举的静态方法
	 * @author: chaoyang.ren 
	 * @date:2016年11月17日  上午9:32:40
	 * @param name
	 * @return
	 */
	public static MaterialUnit toEnum(String name){
		return CACHE.get(name);
	}
	
	public static boolean contains(String name){
		return CACHE.containsKey(name);
	}
	
	/**
	 * 转换为1千克标准重量的系数
	 */
	private double standardWeight;
	/**
	 * 单位描述
	 */
	private String desc;
	public double getStandardWeight() {
		return standardWeight;
	}
	public void setStandardWeight(double standardWeight) {
		this.standardWeight = standardWeight;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	private static final Map<String,MaterialUnit> CACHE = Collections.unmodifiableMap(
			new HashMap<String,MaterialUnit>(){
				private static final long serialVersionUID = 1L;
				{
					for (MaterialUnit mu : MaterialUnit.values()) {
						put(mu.name(), mu);
					}
				}
			});
}
