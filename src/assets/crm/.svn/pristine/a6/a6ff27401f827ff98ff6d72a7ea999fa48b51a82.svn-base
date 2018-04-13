package com.hhnz.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.mapper.ApiMaterialBaseMapper;
import com.hhnz.crm.model.TMaterialBaseV;
import com.hhnz.order.mapper.ApiOrderMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OrderSearchModel;
import com.hhnz.order.service.ApiOrderService;
import com.hhnz.util.AjaxDTO;

@Service
@Transactional
public class ApiOrderServiceImpl implements ApiOrderService {

	@Resource
	private ApiOrderMapper apiOrderMapper;
	@Resource
	private ApiMaterialBaseMapper apiMetarialMapper;
	
	@Override
	public List<OrderSearchModel> findCustOrderList(Map<String, Object> params) {
		return  apiOrderMapper.findCustOrderList(params);
	}


	@Override
	public List<Map> findRetailersOrderList(Map<String, Object> params) {
		List<OmOrderHeadersAll> orderHeaderList = apiOrderMapper.findRetailersOrderList(params);//第一步获取所有的订单这里需要用到分页
	
		
		List<Map> orderInfo = new ArrayList<Map>();
		
		for(OmOrderHeadersAll orderHeader : orderHeaderList){
			
			orderHeader.setAmt(orderHeader.getAmt().divide(new BigDecimal(100)));//100倍
			
			Map<String,Object> orderHeaderInfo = new HashMap<String,Object>();
			orderHeaderInfo.put("headerId", orderHeader.getId());
			//根据订单头，获取这个订单的订单行明细信息 
			List<OmOrderLinesAll> orderLinesList = apiOrderMapper.findOrderLineList(orderHeaderInfo);
			List<Map> orderLinesInfo = new ArrayList<Map>();
			for(OmOrderLinesAll orderLine : orderLinesList){
				Map<String,Object> temp = new HashMap<String,Object>();
				
				orderLine.setPrice(orderLine.getPrice().divide(new BigDecimal(100)));//100倍
				
				TMaterialBaseV  material = apiMetarialMapper.findMaterialById(orderLine.getMaterialId());
				
				temp.put("material", material);
				temp.put("orderLine", orderLine);
				orderLinesInfo.add(temp);
			}
			Map<String,Object> order  = new HashMap<String,Object>();
			//存放订单头信息
			order.put("orderHeader", orderHeader);
			//存放订单行详细信息
			order.put("orderLinesInfo", orderLinesInfo);
            //把这个订单全部信息存放
			orderInfo.add(order);
		}
		
		return orderInfo;
	}


	@Override
	public Map<String, Object> issueInvoice(Map<String, Object> params) {
		Map<String,Object> result  = new HashMap<String,Object>();
		int row = apiOrderMapper.issueInvoice(params);		
		result.put("row",row);
		return result;
	}

}
