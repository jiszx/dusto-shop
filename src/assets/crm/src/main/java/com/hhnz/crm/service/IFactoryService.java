package com.hhnz.crm.service;

import java.util.List;

import com.hhnz.crm.model.TFactory;
import com.hhnz.crm.model.TFactoryV;
import com.hhnz.util.AjaxDTO;

public interface IFactoryService {

	int delete(String id);

	int editOrganization(TFactory model);

	AjaxDTO findMaterials(AjaxDTO bean, String id);

	AjaxDTO findFactorys(AjaxDTO bean, TFactoryV factory);

	public List<TFactory> findAll();

	TFactory getFactoryById(String factoryId);

	List<TFactoryV> getFactorys(int limit);
}
