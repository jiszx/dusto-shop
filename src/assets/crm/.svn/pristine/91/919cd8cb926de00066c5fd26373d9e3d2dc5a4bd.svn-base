package com.hhnz.promotion.service.impl;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.promotion.mapper.CrmPromotionStoresMapper;
import com.hhnz.promotion.mapper.CrmPromotionStoresVMapper;
import com.hhnz.promotion.model.CrmPromotionStores;
import com.hhnz.promotion.model.CrmPromotionStoresV;
import com.hhnz.promotion.model.CrmPromotionStoresVExample;
import com.hhnz.promotion.service.PromotionStoresService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

/**
 * 促销品库房维护service
 * @author skevin
 *
 */
@Service
@Transactional
public class PromotionStoresServiceImpl implements PromotionStoresService {
	
	@Resource
	private CrmPromotionStoresMapper storemapper;
	
	@Resource
	private CrmPromotionStoresVMapper storevmapper;
	
	@Override
	public AjaxDTO findPromotionStoresList(String name,String orgid, AjaxDTO bean) throws Exception {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		CrmPromotionStoresVExample ex = new CrmPromotionStoresVExample();
		CrmPromotionStoresVExample.Criteria ext = ex.createCriteria();
		if(!StringUtils.isEmpty(name) && !"null".equals(name)){
			ext.andNameLike(URLDecoder.decode(name,"utf-8")+"%");
		}
		ext.andOrganizationIdLike(orgid+"%");
		List<CrmPromotionStoresV> list = this.storevmapper.selectByExample(ex);
		int total = this.storevmapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public Integer addPromotionStores(CrmPromotionStores model) {
		// TODO Auto-generated method stub
		return this.storemapper.insert(model);
	}

	@Override
	public Integer editPromotionStores(CrmPromotionStores model) {
		// TODO Auto-generated method stub
		return this.storemapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public Integer delstores(Long id) {
		// TODO Auto-generated method stub
		return this.storemapper.deleteByPrimaryKey(id);
	}

}
