package com.hhnz.combination.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.combination.dto.CombinationApplyListDTO;
import com.hhnz.combination.dto.CombinationMerchDTO;
import com.hhnz.combination.mapper.CombinationUtilMapper;
import com.hhnz.combination.mapper.CrmMaterialPackageApplyMapper;
import com.hhnz.combination.mapper.CrmMaterialPackageHeaderMapper;
import com.hhnz.combination.model.CrmMaterialPackageApply;
import com.hhnz.combination.model.CrmMaterialPackageHeader;
import com.hhnz.combination.service.CombinationApplyService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.customer.mapper.CMerchCustProductMapper;
import com.hhnz.customer.model.CMerchCustProduct;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.process.service.IProcessService;
import com.hhnz.util.AjaxDTO;

@Service
@Transactional
public class CombinationApplyServiceImpl implements CombinationApplyService {
	
	@Resource
	private CombinationUtilMapper utilmapper;
	@Resource
	private CrmMaterialPackageHeaderMapper  headmapper;
	
	@Resource
	private CrmMaterialPackageApplyMapper  applymapper;
	
	@Resource
	private IProcessService processService;
	
	@Resource
	private CMerchCustProductMapper productmapper;
	@Override
	public AjaxDTO getApplyList(AjaxDTO bean) {
		AjaxDTO dto = new AjaxDTO();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit()+bean.getOffset());
		List<CombinationApplyListDTO> list = this.utilmapper.selectApplyList(params);
		int total = this.utilmapper.countApplyList(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO getPachage(String modelType,String orgid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("modelType", modelType);
		params.put("orgid", orgid);
		List<CrmMaterialPackageHeader> list = this.utilmapper.selectPackage(params);
		AjaxDTO dto = new AjaxDTO();
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	@Override
	public String addOrUpdateApply(CrmMaterialPackageApply apply, TEmployee user) throws Exception{
		if(apply.getId() !=null){
			apply.setUpdateOid(user.getId());
			apply.setUpdateTs(new Date());
			this.applymapper.updateByPrimaryKeySelective(apply);
		}else{
			apply.setCreateTs(new Date());
			apply.setCreateOid(user.getId());
			apply.setUpdateOid(user.getId());
			apply.setUpdateTs(new Date());
			apply.setStates("1");
			this.applymapper.insert(apply);
		}
		return "S";
	}

	@Override
	public String delApply(Long id) {
		return this.applymapper.deleteByPrimaryKey(id)==1?"S":"E";
	}

	@Override
	public AjaxDTO getRangeArea(Long applyId) {
		AjaxDTO dto = new AjaxDTO();
		List<CrmSalesOrganization> list= this.utilmapper.selectRangeArea(applyId);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	@Override
	public String startProcess(Long applyId, String states, TEmployee user)
			throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", applyId);
		param.put("startUser", user.getLoginName());
		param.put("groupType", "admin");
		param.put("name", "套餐申请" + applyId);
		param.put("level", "1");
		param.put("viewPage", "combinationApply/Auditview.html?id=" + applyId);
		String processId = this.processService.startProcess(param,
				"combination_apply", user.getLoginName());
		CrmMaterialPackageApply apply = this.applymapper.selectByPrimaryKey(applyId);
		apply.setProcessId(processId);
		apply.setStates("2");
		this.applymapper.updateByPrimaryKeySelective(apply);
		return "S";
	}
	public  void updateApplyStates(Long id,String states) throws Exception{
		CrmMaterialPackageApply apply = this.applymapper.selectByPrimaryKey(id);
		apply.setStates(states);
		this.applymapper.updateByPrimaryKeySelective(apply);
		if("3".equals(states)){
			//审批通过，则把产品添加到对应的客户下
			this.utilmapper.updateMerchProduct(id);
		}
	}

	@Override
	public CrmMaterialPackageApply getApplyById(Long id) {
		return this.applymapper.selectByPrimaryKey(id);
	}

	@Override
	public AjaxDTO getCombinationMerchs(AjaxDTO bean, Long id) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit()+bean.getOffset());
		List<CombinationMerchDTO> list = this.utilmapper.selectCombinationMerch(params);
		int total = this.utilmapper.countCombinationMerch(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public String delCombinationMerch(Long lineId,Long merchCustId, Long id) throws Exception{
		/*CMerchCustProductExample ex = new CMerchCustProductExample();
		CMerchCustProductExample.Criteria ext = ex.createCriteria();
		ext.andIdEqualTo(id);
		ext.andMerchCustIdEqualTo(merchCustId);
		ext.andContractLineidEqualTo(lineId);*/
		CMerchCustProduct product = this.productmapper.selectByPrimaryKey(id);
		product.setStates("5");
		return this.productmapper.updateByPrimaryKeySelective(product)==1?"S":"E";
	}

	@Override
	public AjaxDTO selectMerchs(AjaxDTO bean,Long id, String type) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit()+bean.getOffset());
		params.put("type", type);
		List<CombinationMerchDTO> list = this.utilmapper.selectMerchs(params);
		int total = this.utilmapper.countMerchs(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public String addCombinationMerch(String merchids, Long applyId) throws Exception {
		List<Long> ids = new ArrayList<Long>();
		if(StringUtils.isEmpty(merchids)){
			return "E";
		}
		for(String id:merchids.split(",")){
			ids.add(Long.parseLong(id));
		}
		if(ids.size()==0){
			ids.add(0L);
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		params.put("applyId", applyId);
		this.utilmapper.insertIntoProduct(params);
		this.utilmapper.updateProduct(params);
		return "S";
	}

}
