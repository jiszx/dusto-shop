package com.hhnz.crm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hhnz.crm.mapper.TFactoryMapper;
import com.hhnz.crm.mapper.TFactoryVMapper;
import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.mapper.TMaterialFactoryMapper;
import com.hhnz.crm.model.TFactory;
import com.hhnz.crm.model.TFactoryV;
import com.hhnz.crm.model.TFactoryVExample;
import com.hhnz.crm.model.TFactoryVExample.Criteria;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.model.TMaterialBaseExample;
import com.hhnz.crm.model.TMaterialFactory;
import com.hhnz.crm.model.TMaterialFactoryExample;
import com.hhnz.crm.service.IFactoryService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

@Service
public class FactoryServiceImpl implements IFactoryService {
  @Resource
  private TFactoryMapper factoryMapper;
  @Resource
  private TFactoryVMapper factoryVMapper;
  @Resource
  private TMaterialFactoryMapper materialFactoryMapper;
  @Resource
  private TMaterialBaseMapper materialMapper;

  @Override
  public AjaxDTO findFactorys(AjaxDTO bean, TFactoryV factory) {
    Page page = new Page();
    page.setLimit(bean.getLimit());
    page.setOffset(bean.getOffset());
    TFactoryVExample ex = new TFactoryVExample();
    ex.setPage(page);
    Criteria param = ex.createCriteria();
    if(StringUtils.isNotEmpty(factory.getCity())){
      param.andCityLike("%"+factory.getCity()+"%");
    }
    if(StringUtils.isNotEmpty(factory.getName())){
      param.andNameLike("%"+factory.getName()+"%");
    }
    if(StringUtils.isNotEmpty(factory.getOrganizationId())){
      param.andOrganizationIdLike(factory.getOrganizationId()+"%");
    }
    List<TFactoryV> list = this.factoryVMapper.selectByExample(ex);
    int total = this.factoryVMapper.countByExample(ex);
    bean.setRows(list);
    bean.setTotal(total);
    return bean;
  }
  
  @Override
  public List<TFactoryV> getFactorys(int limit){
    AjaxDTO page = new AjaxDTO();
    page.setLimit(limit);
    TFactoryV fac = new TFactoryV();
    return findFactorys(page, fac).getRows();
  }

  @Override
  public int delete(String id) {
    return factoryMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int editOrganization(TFactory model) {
    TFactory factory = factoryMapper.selectByPrimaryKey(model.getId());
    factory.setOrganizationId(model.getOrganizationId());
    return factoryMapper.updateByPrimaryKey(factory);
  }
  
  @Override
  public AjaxDTO findMaterials(AjaxDTO bean, String id){
    Page page = new Page();
    if(bean.getLimit()==0){
      bean.setLimit(20);
    }
    page.setLimit(bean.getLimit());
    page.setOffset(bean.getOffset());
    TMaterialFactoryExample ex = new TMaterialFactoryExample();
    ex.setPage(page);
    ex.createCriteria().andFacotryIdEqualTo(id);
    List<TMaterialFactory> list = this.materialFactoryMapper.selectByExample(ex);
    int total = this.materialFactoryMapper.countByExample(ex);
    bean.setTotal(total);
    if(list.isEmpty()){
      bean.setRows(Collections.EMPTY_LIST);
      return bean;
    }
    List<String> materialIds = new ArrayList<>();
    for(TMaterialFactory mf:list){
      materialIds.add(String.valueOf(mf.getMaterialId()));
    }
    TMaterialBaseExample baseEx = new TMaterialBaseExample();
    baseEx.createCriteria().andSapIdIn(materialIds);
    List<TMaterialBase> result = this.materialMapper.selectByExample(baseEx);
    bean.setRows(result);
    return bean;
  }

	@Override
	public List<TFactory> findAll() {
		return factoryMapper.selectByExample(null);
	}

	@Override
	public TFactory getFactoryById(String factoryId) {
		return this.factoryMapper.selectByPrimaryKey(factoryId);
	}
	
}
