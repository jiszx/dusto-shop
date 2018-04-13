package com.hhnz.promotion.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.promotion.mapper.CrmPromotionInvMapper;
import com.hhnz.promotion.mapper.CrmPromotionLogMapper;
import com.hhnz.promotion.mapper.CrmPromotionLogVMapper;
import com.hhnz.promotion.model.CrmPromotionInv;
import com.hhnz.promotion.model.CrmPromotionInvExample;
import com.hhnz.promotion.model.CrmPromotionLog;
import com.hhnz.promotion.model.CrmPromotionLogV;
import com.hhnz.promotion.model.CrmPromotionLogVExample;
import com.hhnz.promotion.service.PromotionInvService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.db.Page;

@Service
@Transactional
public class PromotionInvServiceImpl implements PromotionInvService {
	@Resource
	private CrmPromotionLogVMapper vmapper;
	
	@Resource
	private CrmPromotionLogMapper mapper;
	
	@Resource
	private CrmPromotionInvMapper invmapper;
	@Override
	public AjaxDTO findApplylist(HashMap<String, Object> params, AjaxDTO bean) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		CrmPromotionLogVExample ex = new CrmPromotionLogVExample();
		CrmPromotionLogVExample.Criteria ext = ex.createCriteria();
		//类型
		ext.andTypeEqualTo((String)params.get("type"));
		//销售组织
		ext.andOrganizationIdLike((String)params.get("orgid")+"%");
		//促销品名称
		if(!StringUtils.isEmpty((String) params.get("name"))){
			ext.andMaterialnameLike((String) params.get("name")+"%");
		}
		//状态
		if(!StringUtils.isEmpty((String)params.get("states"))){
			ext.andStatesEqualTo((String)params.get("states"));
		}
		//采购方
		if(!StringUtils.isEmpty((String)params.get("purid"))&&!"null".equals((String)params.get("purid"))){
			ext.andPurIdEqualTo(Long.parseLong((String) params.get("purid")));
		}
		//采购方
		if(!StringUtils.isEmpty((String)params.get("stores"))&&!"null".equals((String)params.get("stores"))){
			ext.andStoresIdEqualTo(Long.parseLong((String) params.get("stores")));
		}
		List<CrmPromotionLogV> list = this.vmapper.selectByExample(ex);
		int total = this.vmapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}
	@Override
	public Integer addPromotionApply(CrmPromotionLog model) {
		// TODO Auto-generated method stub
		return this.mapper.insert(model);
	}
	@Override
	public Integer editPromotionApply(CrmPromotionLog model) {
		// TODO Auto-generated method stub
		return this.mapper.updateByPrimaryKeySelective(model);
	}
	@Override
	public Integer delApply(Long id) {
		// TODO Auto-generated method stub
		return this.mapper.deleteByPrimaryKey(id);
	}
	@Override
	public int updateStates(Long id, String states) {
		CrmPromotionLog  log = this.mapper.selectByPrimaryKey(id);
		log.setStates(states);
		this.mapper.updateByPrimaryKeySelective(log);
		if("3".equals(states)){
			//添加到客戶库存
			CrmPromotionInvExample ex = new CrmPromotionInvExample();
			CrmPromotionInvExample.Criteria ext = ex.createCriteria();
			ext.andOrganizationIdEqualTo(log.getOrganizationId());
			ext.andPromotionIdEqualTo(log.getPromotionId());
			ext.andStoresIdEqualTo(log.getStoresId());
			List<CrmPromotionInv>  list = new ArrayList<CrmPromotionInv>();
			list = this.invmapper.selectByExample(ex);
			CrmPromotionInv inv = new CrmPromotionInv();
			if(list !=null && list.size()==1){
				inv = list.get(0);
				inv.setNum(inv.getNum().add(log.getNum()));
				inv.setAmt(inv.getAmt().add(BigDecimalASME.multiply(log.getNum(), log.getPrice())));
				this.invmapper.updateByPrimaryKeySelective(inv);
			}else{
				inv.setNum(log.getNum());
				inv.setAmt(BigDecimalASME.multiply(log.getNum(), log.getPrice()));
				inv.setOrganizationId(log.getOrganizationId());
				inv.setPromotionId(log.getPromotionId());
				inv.setStoresId(log.getStoresId());
				this.invmapper.insert(inv);
			}
		}
		return 1;
	}
	@Override
	public int OutupdateStates(Long id, String states) {
		CrmPromotionLog  log = this.mapper.selectByPrimaryKey(id);
		log.setStates(states);
		this.mapper.updateByPrimaryKeySelective(log);
		if("3".equals(states)){
			//减少库存
			CrmPromotionInvExample ex = new CrmPromotionInvExample();
			CrmPromotionInvExample.Criteria ext = ex.createCriteria();
			ext.andOrganizationIdEqualTo(log.getOrganizationId());
			ext.andPromotionIdEqualTo(log.getPromotionId());
			ext.andStoresIdEqualTo(log.getStoresId());
			List<CrmPromotionInv>  list = new ArrayList<CrmPromotionInv>();
			list = this.invmapper.selectByExample(ex);
			CrmPromotionInv inv = new CrmPromotionInv();
			if(list !=null && list.size()==1){
				inv = list.get(0);
				inv.setNum(inv.getNum().subtract(log.getNum()));
				inv.setAmt(inv.getAmt().subtract(BigDecimalASME.multiply(log.getNum(), log.getPrice())));
				this.invmapper.updateByPrimaryKeySelective(inv);
			}
		}
		return 1;
	}
	@Override
	public CrmPromotionLog findLogByid(Long id) {
		// TODO Auto-generated method stub
		return this.mapper.selectByPrimaryKey(id);
	}
	@Override
	public CrmPromotionLogV findLogVByid(Long id) {
		// TODO Auto-generated method stub
		CrmPromotionLogVExample ex = new CrmPromotionLogVExample();
		CrmPromotionLogVExample.Criteria ext = ex.createCriteria();
		ext.andIdEqualTo(id);
		return this.vmapper.selectByExample(ex).get(0);
	}

}
