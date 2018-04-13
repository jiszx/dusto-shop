package com.hhnz.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.util.AjaxDTO;

public interface OrderBackService {

	AjaxDTO selectBackOrderHeaderList();

	String addBackOrder(Long headerId, String linesData,String backReason,String remark, TEmployee user);

	AjaxDTO selectBackOrderList(Map<String, Object> params);

	AjaxDTO selectEditLineData(Long headerid);

	void editNum(Long spiltId, BigDecimal num);

	void startProcess(TEmployee user, Long id, String states) throws Exception;

	void spiltline(List<OmOrderLinesAll> storgageBacklines,
			OmOrderHeadersAll newBackStorgageOrder);

	String editAllocatePrice(Long id, BigDecimal price);

	AjaxDTO selectAuditLineData(Long id);

	String validateBackOrder(Long id);

	AjaxDTO selectOldOrderLine(Long id);

	void updateOldOrderReturnNum(Long id);

	void addStorageOrder(Long id);

}
