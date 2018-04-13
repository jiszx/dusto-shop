package com.hhnz.crm.service;

import java.util.List;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.model.TMaterialBaseV;
import com.hhnz.util.AjaxDTO;

public interface IProductService {

	int saveAttachment(TAttachment record, TEmployee user);

	AjaxDTO findAttachment(String id) throws Exception;

	int editMaterial(TMaterialBase material);

	AjaxDTO findSeries();

	AjaxDTO findProducts(AjaxDTO bean, TMaterialBaseV model, String isFilter);

	int deleteAttachment(Long id);

	AjaxDTO apiFindProducts(AjaxDTO bean, TMaterialBaseV model, String isFilter);

	void fillOrgAndFactory(TMaterialBaseV material);

	void fillAttachmentUrl(List<TAttachment> atts);

	AjaxDTO findBrand();

	AjaxDTO findiPackage();

  AjaxDTO findViceBrand();

}
