package com.hhnz.virtualwarehouse.mapper;

import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmVirtualWarehouseMapper {
    int countByExample(CrmVirtualWarehouseExample example);

    int deleteByExample(CrmVirtualWarehouseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrmVirtualWarehouse record);

    int insertSelective(CrmVirtualWarehouse record);

    List<CrmVirtualWarehouse> selectByExample(CrmVirtualWarehouseExample example);

    CrmVirtualWarehouse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrmVirtualWarehouse record, @Param("example") CrmVirtualWarehouseExample example);

    int updateByExample(@Param("record") CrmVirtualWarehouse record, @Param("example") CrmVirtualWarehouseExample example);

    int updateByPrimaryKeySelective(CrmVirtualWarehouse record);

    int updateByPrimaryKey(CrmVirtualWarehouse record);

	/**
	 * @author: chaoyang.ren 
	 * @date:2016年10月17日  上午9:40:17
	 * @param ex
	 * @return
	 */
	List<CrmVirtualWarehouse> selectByExampleWithMaterial(CrmVirtualWarehouseExample ex);

	/**
	 * @author: chaoyang.ren 
	 * @date:2016年10月17日  上午10:42:45
	 * @param ex
	 * @return
	 */
	int countByExampleWithMaterial(CrmVirtualWarehouseExample ex);
}