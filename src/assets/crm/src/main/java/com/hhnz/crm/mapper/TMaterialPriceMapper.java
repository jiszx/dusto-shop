package com.hhnz.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hhnz.crm.dto.ProductPriceQueryDTO;
import com.hhnz.crm.model.TMaterialPrice;

public interface TMaterialPriceMapper {

    List<TMaterialPrice> selectByMaterialBase(ProductPriceQueryDTO material);
    /**
     * 正常为单个值，为预防可能出现的情况，请判断size后get(0)
     * @author: chaoyang.ren 
     * @date:Sep 21, 2017  2:38:03 PM
     * @param material
     * @return
     */
    List<TMaterialPrice> selectActiveByMaterialIdAndOrgIdAndChannel(@Param("materialId")String materialId, @Param("organizationId")String orgId, @Param("channel")String channel);
    /**
     * 获取后续才会生效的价格信息
     * @author: chaoyang.ren
     * @date:Sep 22, 2017  5:19:00 PM
     * @param materialId
     * @param orgId
     * @param channel
     * @return
     */
    List<TMaterialPrice> selectLaterByMaterialIdAndOrgIdAndChannel(@Param("materialId")String materialId, @Param("organizationId")String orgId, @Param("channel")String channel);
    
    void insertSelective(TMaterialPrice record);
    
    void updateByPrimaryKeySelective(TMaterialPrice record);
    
	void insertBatch(@Param("materialPrices") List<TMaterialPrice> materialPrices);
	
	void updateBatch(@Param("materialPrices") List<TMaterialPrice> materialPrices);
}