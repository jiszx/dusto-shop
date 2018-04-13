package com.hhnz.order.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hhnz.order.model.OrderSearchModel;

public interface OrderCloseMapper {

	List<OrderSearchModel> closeList(Map<String, Object> params);

	int countCloseList(Map<String, Object> params);

	BigDecimal getInvouceNum(Map<String, Object> params);

	BigDecimal getDeliveredNum(Map<String, Object> params);

	void closeTransferOrder(Map<String, Object> map);

	String judgeDelivered(String sapOrderId);

}
