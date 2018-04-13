package com.hhnz.order.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hhnz.order.dto.OrderBackDTO;
import com.hhnz.order.dto.OrderBackSpiltDTO;
import com.hhnz.order.model.OrderLinesDetials;

public interface OrderBackMapper {

	List<OrderBackDTO> selectBackOrderHeaderList();

	List<OrderBackDTO> selectBackOrderList(Map<String, Object> params);

	int countBackOrderList(Map<String, Object> params);

	List<OrderBackSpiltDTO> selectEditSpilts(Long headerid);

	BigDecimal selectStoragePrice(Map<String, Object> params);

	List<OrderLinesDetials> selectAuditLineData(Long id);

	List<OrderLinesDetials> selectOldOrderLines(Long id);

}
