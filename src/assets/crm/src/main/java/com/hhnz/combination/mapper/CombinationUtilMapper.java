package com.hhnz.combination.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.combination.dto.CombinationApplyListDTO;
import com.hhnz.combination.dto.CombinationLinesDTO;
import com.hhnz.combination.dto.CombinationMerchDTO;
import com.hhnz.combination.dto.CombinationProductDTO;
import com.hhnz.combination.model.CrmMaterialPackageHeader;
import com.hhnz.organization.model.CrmSalesOrganization;

public interface CombinationUtilMapper {

	List<CombinationProductDTO> selectProduct(Map<String, Object> params);

	int countProduct(Map<String, Object> params);

	List<CombinationLinesDTO> selectLinesByHeaderId(Long headerId);

	int countLinesByHeaderId(Long headerId);

	List<CombinationApplyListDTO> selectApplyList(Map<String, Object> params);

	int countApplyList(Map<String, Object> params);

	List<CrmSalesOrganization> selectRangeArea(Long applyId);

	void updateMerchProduct(Long id);

	List<CombinationMerchDTO> selectCombinationMerch(Map<String, Object> params);

	int countCombinationMerch(Map<String, Object> params);

	List<CombinationMerchDTO> selectMerchs(Map<String, Object> params);

	int countMerchs(Map<String, Object> params);

	void insertIntoProduct(Map<String, Object> params);

	void updateProduct(Map<String, Object> params);

	List<CrmMaterialPackageHeader> selectPackage(Map<String, Object> params);

}
