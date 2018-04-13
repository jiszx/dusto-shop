package com.hhnz.combination.service;

import java.math.BigDecimal;

import com.hhnz.combination.dto.CombinationDTO;
import com.hhnz.combination.model.CrmMaterialPackageHeader;
import com.hhnz.combination.model.CrmMaterialPackageLines;
import com.hhnz.combination.model.CrmMaterialPackageRebate;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.util.AjaxDTO;

public interface CombinationService {

	String addOrUpdateCombination(CombinationDTO model, TEmployee user) throws Exception;

	AjaxDTO getProduct(AjaxDTO bean, String modelType, String materialId) throws Exception;

	AjaxDTO getRebateMaterial() throws Exception;

	AjaxDTO getCombinationList(AjaxDTO bean) throws Exception;

	CrmMaterialPackageHeader getHeaderById(Long id) throws Exception;

	AjaxDTO getRebateData(Long headerId)throws Exception;

	AjaxDTO getLinesData(Long headerId) throws Exception;

	String delById(Long id) throws Exception;

	String addProductLine(CrmMaterialPackageLines line) throws Exception;

	String delProductLine(Long id) throws Exception;

	String delRebateById(Long id);

	String audit(Long id);

	String validateCode(String code);

	String addRebateLine(CrmMaterialPackageRebate line);

	String updatePrice(Long id, BigDecimal amt);

	String editProductLine(CrmMaterialPackageLines line);

}
