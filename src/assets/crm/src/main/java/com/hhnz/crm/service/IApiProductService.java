package com.hhnz.crm.service;

import java.util.List;
import java.util.Map;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialBaseV;
import com.hhnz.util.AjaxDTO;

public interface IApiProductService {



	List<TMaterialBaseV> findCategoryProducts(AjaxDTO bean);


	List<TMaterialBaseV> findTypeProduct(Map<String, Object> params);

	List<TMaterialBaseV> searchProduct(Map<String, Object> params);

	List<TMaterialBaseV> findProductById(Map<String,Object> params);	

}
