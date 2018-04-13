package com.hhnz.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.mapper.ApiMaterialBaseMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialBaseV;
import com.hhnz.crm.service.IApiProductService;
import com.hhnz.util.AjaxDTO;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApiProductServiceImpl implements IApiProductService {

	@Resource
	private ApiMaterialBaseMapper materialBaseMapper;
	  


	@Override
	public List<TMaterialBaseV> findCategoryProducts(AjaxDTO bean) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("begin", bean.getOffset());
		params.put("end",(bean.getLimit()+bean.getOffset()));
		return materialBaseMapper.findCategoryProducts(params);
	}

	@Override
	public List<TMaterialBaseV> findTypeProduct(Map<String,Object> params) {
		//user中存放    用户数据    ，用于之后   的推荐部分
		//bean中存放    分页数据    
		AjaxDTO bean = (AjaxDTO) params.get("AjaxDTO");
		params.put("begin", bean.getOffset());
		params.put("end",(bean.getLimit()+bean.getOffset()));
		return materialBaseMapper.findTypeProduct(params);
	}

	@Override
	public List<TMaterialBaseV> searchProduct(Map<String, Object> params) {
		return materialBaseMapper.findTypeProduct(params);
	}

	@Override
	public List<TMaterialBaseV> findProductById(Map<String,Object> params) {
				
		return materialBaseMapper.indexFindProduct(params);
	}

}
