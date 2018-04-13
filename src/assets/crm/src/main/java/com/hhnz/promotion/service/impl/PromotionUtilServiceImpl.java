package com.hhnz.promotion.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.promotion.mapper.CrmPromotionInvVMapper;
import com.hhnz.promotion.mapper.CrmPromotionMaterialMapper;
import com.hhnz.promotion.mapper.CrmPromotionStoresVMapper;
import com.hhnz.promotion.mapper.PromotionUtilMapper;
import com.hhnz.promotion.model.CrmPromotionInvV;
import com.hhnz.promotion.model.CrmPromotionInvVExample;
import com.hhnz.promotion.model.CrmPromotionMaterial;
import com.hhnz.promotion.model.CrmPromotionPur;
import com.hhnz.promotion.model.CrmPromotionStores;
import com.hhnz.promotion.service.PromotionUtilService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

/**
 * 促销品管理工具接口Service
 * @author skevin
 *
 */
@Service
@Transactional
public class PromotionUtilServiceImpl implements PromotionUtilService {
	@Resource
	private PromotionUtilMapper mapper;
	
	@Resource
	private CrmPromotionStoresVMapper  storesmapper;
	
	@Resource
	private CrmPromotionMaterialMapper materialmapper;
	
	@Resource
	private CrmPromotionInvVMapper invVmapper;
	/**
	 * 用户销售组织信息
	 */
	/*@Override
	public AjaxDTO findUserOrgInfo(String organizationId) {
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		List<CrmSalesOrganization> list = this.mapper.findUserOrgInfo(organizationId);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}*/
	/**
	 * 用户可查看库房信息
	 */
	@Override
	public AjaxDTO findUserStoresInfo(String orgid) {
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		List<CrmPromotionStores> list = this.mapper.findUserStoresInfo(orgid);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	/**
	 * 用户可查看采购方信息
	 */
	@Override
	public AjaxDTO findUserPurInfo(String orgid) {
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		List<CrmPromotionPur> list = this.mapper.findUserPurInfo(orgid);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	@Override
	public AjaxDTO findUserpromotionInfo(String orgid) {
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		List<CrmPromotionMaterial> list = this.mapper.findUserPromotionMaterialInfo(orgid);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	@Override
	public String promotionPrice(Long promotionid) {
		// TODO Auto-generated method stub
		CrmPromotionMaterial material = new CrmPromotionMaterial();
		material = this.materialmapper.selectByPrimaryKey(promotionid);
		if(material !=null){
			return BigDecimalASME.divide(material.getPrice()).toString();
		}else{
			return "0";
		}
		
	}
	@Override
	public AjaxDTO getInvStoresInfo(String orgid) {
		AjaxDTO dto = new AjaxDTO();
		CrmPromotionInvVExample ex = new CrmPromotionInvVExample();
		ex.createCriteria().andOrganizationIdEqualTo(orgid);
		List<CrmPromotionInvV> list = this.invVmapper.selectByExample(ex);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	@Override
	public AjaxDTO OutPromotion(Long storesId) {
		CrmPromotionInvVExample  ex = new CrmPromotionInvVExample();
		ex.createCriteria().andStoresIdEqualTo(storesId);
		List<CrmPromotionInvV> list = this.invVmapper.selectByExample(ex);
		AjaxDTO dto = new AjaxDTO();
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

}
