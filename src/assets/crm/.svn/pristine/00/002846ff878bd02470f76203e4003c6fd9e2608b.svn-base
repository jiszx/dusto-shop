package com.hhnz.virtualwarehouse.service;

import com.hhnz.util.AjaxDTO;
import com.hhnz.virtualwarehouse.dto.VirtualWarehouseQueryDTO;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseEntry;

public interface IVirtualWarehouseEntryService{

	/**
	 * 查询虚拟库存入库信息
	 * @param inv
	 * @param bean
	 * @param queryDto 
	 * @return
	 */
	public AjaxDTO find(AjaxDTO bean, VirtualWarehouseQueryDTO queryDto);

	/**
	 * 保存入库信息
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午3:51:34
	 * @param vmEntry
	 */
	public void save(CrmVirtualWarehouseEntry vmEntry);

	/**
	 * 根据ID获取入库信息
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午5:15:33
	 * @param id
	 * @return
	 */
	public CrmVirtualWarehouseEntry findById(Long id);

	/**
	 * 根据ID删除入库信息
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午5:34:43
	 * @param id
	 */
	public void delete(Long id);

	
}
