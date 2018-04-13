package com.hhnz.virtualwarehouse.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;
import com.hhnz.virtualwarehouse.dto.VirtualWarehouseQueryDTO;
import com.hhnz.virtualwarehouse.mapper.CrmVirtualWarehouseEntryMapper;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseEntry;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseEntryExample;
import com.hhnz.virtualwarehouse.service.IVirtualWarehouseEntryService;

@Service
@Transactional
public class VirtualWarehouseEntryServiceImpl implements IVirtualWarehouseEntryService{
	@Resource
	private CrmVirtualWarehouseEntryMapper vwEntryMapper;
	
	@Override
	public AjaxDTO find(AjaxDTO bean, VirtualWarehouseQueryDTO queryDto) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		CrmVirtualWarehouseEntryExample ex = new CrmVirtualWarehouseEntryExample();
		CrmVirtualWarehouseEntryExample.Criteria ext = ex.createCriteria();
		
		if(StringUtils.isNotBlank(queryDto.getMaterialName())){
			//TODO
		}
		//状态
		if(StringUtils.isNotBlank(queryDto.getStatus())){
			ext.andStatusEqualTo(queryDto.getStatus());
		}
		//客户类型
		if(StringUtils.isNotBlank(queryDto.getCustType())){
			ext.andCustTypeEqualTo(queryDto.getCustType());
		}
		List<CrmVirtualWarehouseEntry> list = this.vwEntryMapper.selectByExample(ex);
		int total = this.vwEntryMapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public void save(CrmVirtualWarehouseEntry vmEntry) {
		if(vmEntry.getId() == null){
			vwEntryMapper.insertSelective(vmEntry);
		}else{
			vwEntryMapper.updateByPrimaryKeySelective(vmEntry);
		}
	}

	@Override
	public CrmVirtualWarehouseEntry findById(Long id) {
		return vwEntryMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(Long id) {
		vwEntryMapper.deleteByPrimaryKey(id);
	}
	
}
