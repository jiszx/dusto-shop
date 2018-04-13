package com.hhnz.crm.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hhnz.crm.mapper.TAttachmentMapper;
import com.hhnz.crm.mapper.TFactoryMapper;
import com.hhnz.crm.mapper.TFactoryVMapper;
import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.mapper.TMaterialBaseVMapper;
import com.hhnz.crm.model.FactoryAndOrg;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TAttachmentExample;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TFactory;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.model.TMaterialBaseV;
import com.hhnz.crm.model.TMaterialBaseVExample;
import com.hhnz.crm.model.TMaterialBaseVExample.Criteria;
import com.hhnz.crm.service.IProductService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.db.Page;

@Service
public class ProductServiceImpl implements IProductService {
	@Resource
	private TMaterialBaseVMapper materialBaseVMapper;
	@Resource
	private TMaterialBaseMapper materialBaseMapper;
	@Resource
	private TAttachmentMapper attachmentMapper;
	@Resource
	private TFactoryVMapper factoryVMapper;
	@Resource
	private TFactoryMapper factoryMapper;
	@Resource
	private IAttachmentService attachmentService;
	
	@Override
	public AjaxDTO findProducts(AjaxDTO bean, TMaterialBaseV model,
			String isFilter) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		TMaterialBaseVExample ex = new TMaterialBaseVExample();
		Criteria param = ex.createCriteria();
		if (StringUtils.isNotEmpty(model.getCategory())) {
		  param.andCategoryidEqualTo(model.getCategory());
		}
		if (StringUtils.isNotEmpty(model.getBrand())) {
		  param.andBrandidEqualTo(model.getBrand());
		}
		if (StringUtils.isNotEmpty(model.getSeries())) {
		  param.andSeriesidEqualTo(model.getSeries());
		}
		if(StringUtils.isNotEmpty(model.getiPackage())){
		  param.andIPackageEqualTo(model.getiPackage());
		}
		if(StringUtils.isNotEmpty(model.getAttribute1())){
		  param.andAttribute1EqualTo(model.getAttribute1());
		}
		if (StringUtils.isNotEmpty(model.getSku())) {
		  param.andSkuLike("%" + model.getSku() + "%");
		}
		if (StringUtils.isNotBlank(model.getSapId())) {
		  param.andSapIdLike("%"+model.getSapId()+"%");
		}
		if(StringUtils.isNotEmpty(model.getFactoryid())){
		  param.addCriterion("factoryid=", model.getFactoryid(), "factoryid");
		}
		if (StringUtils.isEmpty(model.getCategory())
				&& StringUtils.isNotEmpty(isFilter)) {
		  param.andCategoryIsNull();
		}
		ex.setPage(page);
		List<TMaterialBaseV> list = this.materialBaseVMapper
				.selectByExample(ex);
		int total = this.materialBaseVMapper.countByExample(ex);
		for(TMaterialBaseV material:list){
		  setFactoryName(material);
		}
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}
	
	private void setFactoryName(TMaterialBaseV material){
	  if(StringUtils.isEmpty(material.getFactoryid())){
	    return;
	  }
	  TFactory factory = factoryMapper.selectByPrimaryKey(material.getFactoryid());
	  material.setFactoryName(factory.getName());
	}

	@Override
	public AjaxDTO apiFindProducts(AjaxDTO bean, TMaterialBaseV model,
			String isFilter) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		TMaterialBaseVExample ex = new TMaterialBaseVExample();
		Criteria param = ex.createCriteria();
		if (StringUtils.isNotEmpty(model.getCategory())) {
		  param.andCategoryidEqualTo(model.getCategory());
		}
		if (StringUtils.isNotEmpty(model.getBrand())) {
		  param.andBrandidEqualTo(model.getBrand());
		}
		if (StringUtils.isNotEmpty(model.getSeries())) {
			String[] tmpSeries = model.getSeries().split(",");
			param.andSeriesidIn(Arrays.asList(tmpSeries));
		}
		if (StringUtils.isNotEmpty(model.getSku())) {
		  param.andSkuLike("%" + model.getSku() + "%");
		}
		if (StringUtils.isNotBlank(model.getSapId())) {
		  param.andSapIdEqualTo(model.getSapId());
		}
		if (StringUtils.isEmpty(model.getCategory())
				&& StringUtils.isNotEmpty(isFilter)) {
		  param.andCategoryIsNull();
		}
		ex.setPage(page);
		List<TMaterialBaseV> list = this.materialBaseVMapper
				.apiSelectByExample(ex);
		for (TMaterialBaseV mat : list) {
          mat.setBasePrice(mat.getBasePrice() != null ? mat.getBasePrice().divide(new BigDecimal("100"))
              : BigDecimal.ZERO);
          mat.setAmounts(StringUtils.isEmpty(mat.getAttribute6())?"1":mat.getAttribute6());
		}
		bean.setRows(list);
		return bean;
	}

	@Override
	public void fillOrgAndFactory(TMaterialBaseV material) {
		if (material == null || material.getSapId().isEmpty()) {
			return;
		}
		List<FactoryAndOrg> dict = this.materialBaseVMapper
				.selectFactoryAndOrg(material.getSapId());
		material.setOrgname(getOrg(dict, material.getSapId()));
		material.setFactoryName(getFactory(dict, material.getSapId()));
	}

	private String getOrg(List<FactoryAndOrg> dict, String materialId) {
		StringBuilder builder = new StringBuilder();
		for (FactoryAndOrg fa : dict) {
			if (StringUtils.equals(fa.getMaterial_id(), materialId)) {
				builder.append(fa.getOrgname()).append(",");
			}
		}
		return builder.length() > 0 ? builder
				.substring(0, builder.length() - 1) : builder.toString();
	}

	private String getFactory(List<FactoryAndOrg> dict, String materialId) {
		StringBuilder builder = new StringBuilder();
		for (FactoryAndOrg fa : dict) {
			if (StringUtils.equals(fa.getMaterial_id(), materialId)) {
				builder.append(fa.getFactoryname()).append(",");
			}
		}
		return builder.length() > 0 ? builder
				.substring(0, builder.length() - 1) : builder.toString();
	}

	@Override
	public void fillAttachmentUrl(List<TAttachment> atts) {
		String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
		if (atts == null || atts.isEmpty()) {
			return;
		}
		for (TAttachment att : atts) {
			att.setFileName(baseUrl +att.getObjectName()+ att.getFileName());
		}
	}

	@Override
	public AjaxDTO findSeries() {
		List<TMaterialBaseV> series = this.materialBaseVMapper.selectSeries();
		AjaxDTO bean = new AjaxDTO();
		bean.setRows(series);
		bean.setTotal(series.size());
		return bean;
	}

	@Override
	public int editMaterial(TMaterialBase material) {
		TMaterialBase record = this.materialBaseMapper
				.selectByPrimaryKey(material.getSapId());
		record.setCategory(material.getCategory());
		record.setBrand(material.getBrand());
		record.setSeries(material.getSeries());
		return this.materialBaseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int saveAttachment(TAttachment record, TEmployee user) {
		record.setObjectKey("product");
		record.setUploadTs(new Date());
		record.setUploadOid(String.valueOf(user.getId()));
		return attachmentMapper.insert(record);
	}

	@Override
	public AjaxDTO findAttachment(String id) throws Exception {
		AjaxDTO bean = new AjaxDTO();
		Page page = new Page();
		page.setLimit(100);
		page.setOffset(0);
		List<TAttachment> atts = attachmentService.findAttachment(TMaterialBase.class.getSimpleName(), id, bean);
		bean.setRows(atts);
		bean.setTotal(atts.size());
		return bean;
	}

	@Override
	public int deleteAttachment(Long id) {
		return this.attachmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AjaxDTO findBrand() {
		List<TMaterialBaseV> brands = this.materialBaseVMapper.selectBrands();
		AjaxDTO bean = new AjaxDTO();
		bean.setRows(brands);
		bean.setTotal(brands.size());
		return bean;
	}

	@Override
	public AjaxDTO findiPackage() {
		List<TMaterialBaseV> iPackages = this.materialBaseVMapper.selectiPackages();
		AjaxDTO bean = new AjaxDTO();
		bean.setRows(iPackages);
		bean.setTotal(iPackages.size());
		return bean;
	}
	
	@Override
	public AjaxDTO findViceBrand() {
      List<TMaterialBaseV> brands = this.materialBaseVMapper.selectVicebrand();
      AjaxDTO bean = new AjaxDTO();
      bean.setRows(brands);
      bean.setTotal(brands.size());
      return bean;
  }

}
