package com.hhnz.crm.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhnz.crm.dto.ProductPriceAdjustDTO;
import com.hhnz.crm.dto.ProductPriceAdjustEditDTO;
import com.hhnz.crm.dto.ProductPriceDTO;
import com.hhnz.crm.model.TMaterialPrice;
import com.hhnz.crm.model.TMaterialPriceAdjust;
import com.hhnz.crm.model.TMaterialPriceAdjustRecord;
import com.hhnz.crm.model.TPriceAdjustApproved;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.io.excel.util.excel.ExcelResult;

public interface IProductPriceService {

	ExcelResult importPrice(CommonsMultipartFile file);

	AjaxDTO findPriceAdjusts(AjaxDTO bean, TMaterialPriceAdjust model);

	AjaxDTO findPriceAdjustDetails(AjaxDTO bean, Long adjustId);

	void editPrice(ProductPriceDTO model);

	void editAdjustPrice(ProductPriceAdjustEditDTO model);

	void editAdjustBatchPrice(ProductPriceAdjustDTO model);

	TMaterialPriceAdjust findAdjustById(Long adjustId);

	void deleteAdjustPrice(Long adjustId);

	TMaterialPrice findActiveByMaterialIdAndOrgIdAndChannel(String materialId, String orgId, String channel);
	
	List<TMaterialPrice> findAllActive();
	
	List<TMaterialPrice> findLaterByMaterialIdAndOrgIdAndChannel(String materialId, String orgId, String channel);

	void updateAdjustPrice(TMaterialPriceAdjust priceAdjust);

	List<TMaterialPriceAdjustRecord> findPriceAdjustDetails(Long adjustId);

	void insert(TMaterialPrice materialPrice);

	void update(TMaterialPrice materialPrice);

	void insert(List<TMaterialPrice> materialPrices);

	/**
	 * 查询某一日期时生效的调整价
	 * @author: chaoyang.ren 
	 * @date:Sep 25, 2017  2:24:43 PM
	 * @param date 日期，如果查询当前有效则传入当前日期
	 * @param materialId
	 * @param orgId
	 * @param channel
	 * @return
	 */
	TPriceAdjustApproved findActiveAdjustByMaterialIdAndOrgIdAndChannel(Date date, String materialId, String orgId, String channel);

	void insertApprovedAdjustBatch(List<TPriceAdjustApproved> adjustApprovedList);

	void update(List<TMaterialPrice> materialPrices);
	
}
