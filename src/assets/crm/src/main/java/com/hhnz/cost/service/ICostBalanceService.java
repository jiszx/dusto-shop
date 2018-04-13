package com.hhnz.cost.service;

import com.hhnz.cost.model.CrmCostBalancesV;
import com.hhnz.util.AjaxDTO;

public interface ICostBalanceService {

	AjaxDTO balancelist(AjaxDTO bean, String regionId, String organizationId, String costTypeid);

	CrmCostBalancesV getCostBalanceById(Long id);

	AjaxDTO getDetailList(AjaxDTO bean, Long id);

	String getMaxPeriod();

	String upBalance(String period);

}
