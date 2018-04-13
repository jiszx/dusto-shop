package com.hhnz.promotion.service;

import java.util.HashMap;

import com.hhnz.promotion.model.CrmPromotionLog;
import com.hhnz.promotion.model.CrmPromotionLogV;
import com.hhnz.util.AjaxDTO;

public interface PromotionInvService {

	AjaxDTO findApplylist(HashMap<String, Object> params, AjaxDTO bean);

	Integer addPromotionApply(CrmPromotionLog model);

	Integer editPromotionApply(CrmPromotionLog model);

	Integer delApply(Long id);

	int updateStates(Long id, String states);

	int OutupdateStates(Long id, String states);

	CrmPromotionLog findLogByid(Long id);

	CrmPromotionLogV findLogVByid(Long id);

}
