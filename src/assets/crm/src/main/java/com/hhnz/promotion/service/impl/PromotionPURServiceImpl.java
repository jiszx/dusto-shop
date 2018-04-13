package com.hhnz.promotion.service.impl;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.promotion.mapper.CrmPromotionPurMapper;
import com.hhnz.promotion.mapper.CrmPromotionPurVMapper;
import com.hhnz.promotion.model.CrmPromotionPur;
import com.hhnz.promotion.model.CrmPromotionPurV;
import com.hhnz.promotion.model.CrmPromotionPurVExample;
import com.hhnz.promotion.model.CrmPromotionStores;
import com.hhnz.promotion.model.CrmPromotionStoresV;
import com.hhnz.promotion.model.CrmPromotionStoresVExample;
import com.hhnz.promotion.service.PromotionPURService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

/**
 * 促销品采购方维护service
 * @author skevin
 *
 */
@Service
@Transactional
public class PromotionPURServiceImpl implements PromotionPURService {
	
	@Resource
	private  CrmPromotionPurVMapper  purvmapper;
	
	@Resource
	private CrmPromotionPurMapper purmapper;
	@Override
	public AjaxDTO findPromotionPurchaserList(String name,String orgid, AjaxDTO bean) throws Exception {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		CrmPromotionPurVExample ex = new CrmPromotionPurVExample();
		CrmPromotionPurVExample.Criteria ext = ex.createCriteria();
		if(StringUtils.isNotEmpty(name)&&!"null".equals(name)){
			ext.andNameLike(URLDecoder.decode(name,"utf-8")+"%");
		}
		ext.andOrganizationIdLike(orgid+"%");
		List<CrmPromotionPurV> list = this.purvmapper.selectByExample(ex);
		int total = this.purvmapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public Integer addPromotionPurchaser(CrmPromotionPur model) {
		// TODO Auto-generated method stub
		return this.purmapper.insert(model);
	}

	@Override
	public Integer editPromotionPurchaser(CrmPromotionPur model) {
		// TODO Auto-generated method stub
		return this.purmapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public Integer delPurchaser(Long id) {
		// TODO Auto-generated method stub
		return this.purmapper.deleteByPrimaryKey(id);
	}
	
}
