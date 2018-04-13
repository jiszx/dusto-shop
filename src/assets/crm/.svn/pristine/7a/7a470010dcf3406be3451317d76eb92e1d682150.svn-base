package com.hhnz.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.util.enmus.MaterialUnit;

/**
 * 物料单位转换
 * @see MaterialUnit
 * @author: chaoyang.ren
 * @date:2016年11月16日
 * @time:下午5:30:54
 * @email:chaoyang.ren@foxmail.com
 */
public class UnitConverter {
	private static final int SCALE = 3;
	private static final int DEFAULT_DIVIDE_SCALE = 12;
	
	/**
	 * 单位转换
	 * 返回初始值从初始单位转换为最终单位后的值
	 * 结果保留3位小数
	 * <pre>
	 * 例如：
	 * 1 千克 转为 吨
	 * UnitConverter.convert(BigDecimal.ONE, MaterialUnit.KG, MaterialUnit.TO)
	 * 返回值为 0.001
	 * 
	 * 1 克 转为 吨
	 * UnitConverter.convert(BigDecimal.ONE, MaterialUnit.G, MaterialUnit.TO)
	 * 返回值为 0.000
	 * </pre>
	 * 
	 * @see MaterialUnit
	 * @author: chaoyang.ren 
	 * @date:2016年11月16日  下午6:15:39
	 * @param value 初始值
	 * @param fromMU 初始单位
	 * @param toMU 最终单位
	 * @return
	 */
	public static BigDecimal convert(BigDecimal value, MaterialUnit fromMU, MaterialUnit toMU){
		if(fromMU.equals(toMU)){
			return value;
		}
		double fromCal = fromMU.getStandardWeight();
		double toCal = toMU.getStandardWeight();
		BigDecimal multiplicand = BigDecimal.valueOf(fromCal).divide(BigDecimal.valueOf(toCal), DEFAULT_DIVIDE_SCALE, RoundingMode.HALF_UP);
		BigDecimal result = value.multiply(multiplicand).setScale(SCALE, RoundingMode.HALF_UP);
		return result;
	}
	
	/**
	 * value数量的基本单位物料转为特定单位的物料数量,结果保留3位小数
	 * </br>
	 * <pre>
	 * 当前支持的单位：G/KG/TO及有数据库物料表中有转换关系的其他任意单位
	 * throws {@link IllegalArgumentException} - if unit not support
	 * 例如：
	 * 物料20201161的基本单位是BAG,规格是240，规格单位为G,即1BAG=240G
	 * 那么200 BAG 20201161 转为KG
	 * UnitConverter.convert(BigDecimal.valueOf(200), "20201161", MaterialUnit.KG)
	 * 返回值为48.000
	 * 
	 * 当物料单位是其他度量时，根据物料规格及规格单位处理,否则按{@link UnitConverter#convert(BigDecimal, MaterialUnit, MaterialUnit)}
	 * </pre>
	 * @author: chaoyang.ren
	 * @date:2016年12月7日  下午3:53:15
	 * @param value
	 * @param materialId
	 * @param toMU
	 * @return
	 */
	public static BigDecimal convert(BigDecimal value, String materialId, MaterialUnit toMU){
		TMaterialBaseMapper materialBaseMapper = ApplicationContextUtil.getBean(TMaterialBaseMapper.class);
		TMaterialBase mb = materialBaseMapper.selectByPrimaryKey(materialId);
		String materialUnit = mb.getUnit();
		if(!MaterialUnit.contains(materialUnit)){
			MaterialUnit mNnit = MaterialUnit.toEnum(mb.getOrderUnit());
			BigDecimal mNum = new BigDecimal(mb.getSpecifications());
			double fromCal = convert(mNum, mNnit, MaterialUnit.KG).doubleValue();//把包转为KG标准重量
			double toCal = toMU.getStandardWeight();
			//获取包转为目标单位的倍数
			BigDecimal multiplicand = BigDecimal.valueOf(fromCal).divide(BigDecimal.valueOf(toCal), DEFAULT_DIVIDE_SCALE, RoundingMode.HALF_UP);
			BigDecimal result = value.multiply(multiplicand).setScale(SCALE, RoundingMode.HALF_UP);
			return result;
		}else{
			return convert(value, MaterialUnit.toEnum(materialUnit), toMU);
		}
	}
	
}
