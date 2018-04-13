package com.hhnz.combination.service;

import com.hhnz.combination.model.CrmMaterialPackageApply;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.util.AjaxDTO;

public interface CombinationApplyService {

	AjaxDTO getApplyList(AjaxDTO bean);

	AjaxDTO getPachage(String modelType,String orgid);

	String addOrUpdateApply(CrmMaterialPackageApply apply, TEmployee user) throws Exception;

	String delApply(Long id) throws Exception;

	AjaxDTO getRangeArea(Long applyId);

	String startProcess(Long applyId, String states, TEmployee user) throws Exception;
	
	void updateApplyStates(Long id,String states) throws Exception;

	CrmMaterialPackageApply getApplyById(Long id);

	AjaxDTO getCombinationMerchs(AjaxDTO bean, Long id);

	String delCombinationMerch(Long lineId,Long merchCustId, Long id) throws Exception;

	AjaxDTO selectMerchs(AjaxDTO bean,Long id, String type);

	String addCombinationMerch(String merchids, Long applyId) throws Exception;

}
