package com.hhnz.order.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OrderSearchModel;

public interface ApiOrderMapper {

	List<OrderSearchModel> findCustOrderList(Map<String, Object> params);

	List<OmOrderHeadersAll> findRetailersOrderList(Map<String, Object> params);

	List<OmOrderLinesAll> findOrderLineList(Map<String, Object> params);

	int issueInvoice(Map<String, Object> params);


}
