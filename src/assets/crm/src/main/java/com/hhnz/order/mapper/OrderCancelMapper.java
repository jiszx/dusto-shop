package com.hhnz.order.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hhnz.order.model.OrderSearchModel;

public interface OrderCancelMapper {

	List<OrderSearchModel> cancelList(Map<String, Object> params);

	int countCancelList(Map<String, Object> params);

	BigDecimal getInvouceNum(Map<String, Object> params);

	BigDecimal getDeliveredNum(Map<String, Object> params);

}
