package com.hhnz.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hhnz.customer.model.CMerchCustPriceMaintenance;
import com.hhnz.customer.model.CMerchCustPriceMaintenanceExample;

public interface CMerchCustPriceMaintenanceMapper {
    int countByExample(CMerchCustPriceMaintenanceExample example);

    int deleteByExample(CMerchCustPriceMaintenanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CMerchCustPriceMaintenance record);

    int insertSelective(CMerchCustPriceMaintenance record);

    List<CMerchCustPriceMaintenance> selectByExample(CMerchCustPriceMaintenanceExample example);

    CMerchCustPriceMaintenance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CMerchCustPriceMaintenance record, @Param("example") CMerchCustPriceMaintenanceExample example);

    int updateByExample(@Param("record") CMerchCustPriceMaintenance record, @Param("example") CMerchCustPriceMaintenanceExample example);

    int updateByPrimaryKeySelective(CMerchCustPriceMaintenance record);

    int updateByPrimaryKey(CMerchCustPriceMaintenance record);

}