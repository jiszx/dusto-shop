package com.hhnz.customerInv.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customerInv.dto.AdjustMaterialDTO;
import com.hhnz.customerInv.dto.CMerchCustInvAdjustDTO;

public interface CustomerInvAdjustMapper {

	List<CMerchCustBase> selectAdjustMerch(Map<String, Object> params);

	List<AdjustMaterialDTO> selectAdjustMaterials(Map<String, Object> params);

	String selectInvNum(Map<String, Object> params);

	List<CMerchCustInvAdjustDTO> selectAdjustList(Map<String, Object> params);

	int countAdjustList(Map<String, Object> params);

}
