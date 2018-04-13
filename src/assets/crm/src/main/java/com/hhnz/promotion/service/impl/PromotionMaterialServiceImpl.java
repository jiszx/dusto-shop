package com.hhnz.promotion.service.impl;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.promotion.mapper.CrmPromotionInvVMapper;
import com.hhnz.promotion.mapper.CrmPromotionMaterialMapper;
import com.hhnz.promotion.mapper.CrmPromotionMaterialVMapper;
import com.hhnz.promotion.model.CrmPromotionInvV;
import com.hhnz.promotion.model.CrmPromotionInvVExample;
import com.hhnz.promotion.model.CrmPromotionMaterial;
import com.hhnz.promotion.model.CrmPromotionMaterialV;
import com.hhnz.promotion.model.CrmPromotionMaterialVExample;
import com.hhnz.promotion.service.PromotionMaterialService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

/**
 * 
 * @author skevin
 *
 */
@Service
@Transactional
public class PromotionMaterialServiceImpl implements PromotionMaterialService {

	@Resource
	private CrmPromotionInvVMapper numMapper;
	
	@Resource
	private CrmPromotionMaterialVMapper materialvMapper;
	
	@Resource
	private CrmPromotionMaterialMapper mterialMapper;
	@Override
	public AjaxDTO findPromotionNumList(String name,String orgid,Long storesid, AjaxDTO bean) throws Exception {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		CrmPromotionInvVExample ex = new CrmPromotionInvVExample();
		CrmPromotionInvVExample.Criteria ext = ex.createCriteria();
		if(!StringUtils.isEmpty(name) &&!"null".equals(name)){
			ext.andMaterialnameLike(URLDecoder.decode(name,"utf-8")+"%");
		}
		if(storesid !=null){
			ext.andStoresIdEqualTo(storesid);
		}
		if(!StringUtils.isEmpty(orgid)&& !"null".equals(orgid)){			
			ext.andOrganizationIdLike(orgid+"%");
		}
		List<CrmPromotionInvV> list = this.numMapper.selectByExample(ex);
		int total = this.numMapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public AjaxDTO findmaterialList(String name,String orgid, AjaxDTO bean)  throws Exception{
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		CrmPromotionMaterialVExample ex = new CrmPromotionMaterialVExample();
		CrmPromotionMaterialVExample.Criteria ext = ex.createCriteria();
		if(!StringUtils.isEmpty(name) &&!"null".equals(name)){
			ext.andNameLike(URLDecoder.decode(name,"utf-8")+"%");
		}
		ext.andOrganizationIdLike(orgid+"%");
		List<CrmPromotionMaterialV> list = this.materialvMapper.selectByExample(ex);
		int total = this.materialvMapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public Integer addPromotionMaterial(CrmPromotionMaterial model) {
		// TODO Auto-generated method stub
		return this.mterialMapper.insert(model);
	}

	@Override
	public Integer editPromotionMaterial(CrmPromotionMaterial model) {
		// TODO Auto-generated method stub
		return this.mterialMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public Integer delMaterial(Long id) {
		// TODO Auto-generated method stub
		return this.mterialMapper.deleteByPrimaryKey(id);
	}

}
