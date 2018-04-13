package com.hhnz.crm.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.crm.model.TMaterialBaseV;

public interface ApiMaterialBaseMapper {

	List<TMaterialBaseV> indexFindProduct(Map<String, Object> params);

	List<TMaterialBaseV> findCategoryProducts(Map<String, Object> params);

	List<TMaterialBaseV> findTypeProduct(Map<String, Object> params);

	TMaterialBaseV findMaterialById(String materialId);

	List<TMaterialBaseV> searchProduct(Map<String, Object> params);


}
