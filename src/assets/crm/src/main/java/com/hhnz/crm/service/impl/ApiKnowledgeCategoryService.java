package com.hhnz.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.mapper.ApiKnowledgeCategoryMapper;
import com.hhnz.crm.mapper.TKnowledgeCategoryMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TKnowledgeCategory;
import com.hhnz.crm.model.TMaterialBaseV;
import com.hhnz.crm.service.IApiKnowledgeCategoryService;
import com.hhnz.util.AjaxDTO;

@Service
@Transactional
public class ApiKnowledgeCategoryService implements IApiKnowledgeCategoryService {

    @Autowired
    private ApiKnowledgeCategoryMapper categoryMapper;
    
    
	@Override
	public List<TKnowledgeCategory> getTopCategory() {
		return categoryMapper.findTopCategory();
	}


	@Override
	public List<TKnowledgeCategory> searchCategory(Map<String, Object> params) {
		
		return categoryMapper.searchCategory(params);
	}


}
