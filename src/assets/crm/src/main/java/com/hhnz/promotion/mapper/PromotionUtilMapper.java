package com.hhnz.promotion.mapper;

import java.util.List;

import com.hhnz.promotion.model.CrmPromotionMaterial;
import com.hhnz.promotion.model.CrmPromotionPur;
import com.hhnz.promotion.model.CrmPromotionStores;
public interface PromotionUtilMapper {

	//List<CrmSalesOrganization> findUserOrgInfo(String organizationId);

	List<CrmPromotionStores> findUserStoresInfo(String orgid);

	List<CrmPromotionPur> findUserPurInfo(String orgid);

	List<CrmPromotionMaterial> findUserPromotionMaterialInfo(String orgid);

	String findpromotionPrice(String promotionid);

}
