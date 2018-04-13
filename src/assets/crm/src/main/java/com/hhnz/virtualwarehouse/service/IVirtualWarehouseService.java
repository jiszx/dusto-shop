package com.hhnz.virtualwarehouse.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.hhnz.util.AjaxDTO;
import com.hhnz.virtualwarehouse.dto.VirtualWarehouseQueryDTO;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;

public interface IVirtualWarehouseService{

	/**
	 * 添加库存
	 * <p>如果没有对应库存数据则会新增一条</p>
	 * @author: chaoyang.ren 
	 * @date:2016年9月13日  上午9:22:05
	 * @param vw 虚拟仓库存实例
	 * @return
	 * <b>以下情况返回-1,否则返回添加后的新库存数</b>
	 * <p>
	 * 1.数据中无法确认唯一的库存信息</br>
	 * 2.数据中无法找到已存在的库存信息且工厂，物料，客户类型三者信息不齐全无法插入新数据时
	 * </p>
	 */
	public BigDecimal addInventory(CrmVirtualWarehouse vw);
	
	/**
	 * 减少库存
	 * @author: chaoyang.ren 
	 * @date:2016年9月14日  上午9:51:43
	 * @param vw 虚拟仓库存实例
	 * @return
	 * <b>以下情况返回-1,否则返回减少后的新库存数</b>
	 * <p>
	 * 1.数据中无法确认唯一的库存信息</br>
	 * 2.数据中无法找到已存在的库存信息</br>
	 * 3.已存在库存值小于要减少的库存值
	 * </p>
	 */
	public BigDecimal minusInventory(CrmVirtualWarehouse vw);

	/**
	 * 根据 id 或 物料 或 客户类型获取虚拟仓库存列表</br>
	 * 工厂-物料-客户类型三者可唯一对应一条数据
	 * @author: chaoyang.ren 
	 * @date:2016年9月13日  上午10:42:07
	 * @param vw
	 * @return
	 */
	public List<CrmVirtualWarehouse> find(CrmVirtualWarehouse vw);

	/**
	 * 分页查询库存信息
	 * @author: chaoyang.ren 
	 * @date:2016年9月14日  下午1:18:44
	 * @param bean
	 * @param queryDto
	 * @return
	 */
	public AjaxDTO find(AjaxDTO bean, VirtualWarehouseQueryDTO queryDto);

	/**
	 * 根据ID获取虚拟仓库信息
	 * @author: chaoyang.ren 
	 * @date:2016年9月14日  下午1:31:05
	 * @param id
	 * @return
	 */
	public CrmVirtualWarehouse findById(Long id);

	/**
	 * 校验库存是否充足
	 * @author: chaoyang.ren 
	 * @date:2016年9月14日  下午2:54:05
	 * @param vw
	 * @return
	 */
	public boolean isInventoryEnough(CrmVirtualWarehouse vw);

  String generateExcel(AjaxDTO bean, VirtualWarehouseQueryDTO queryDto) throws IOException;
}
