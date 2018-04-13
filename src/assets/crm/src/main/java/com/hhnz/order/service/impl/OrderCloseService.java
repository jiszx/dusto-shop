package com.hhnz.order.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.jco.business.order.OrderRFC;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderCloseMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.model.OrderSearchModel;
import com.hhnz.order.service.IOrderCloseService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.DateUtil;

@Service
@Transactional
public class OrderCloseService implements IOrderCloseService {
	private static Logger logger = Logger
			.getLogger(OrderCloseService.class);
	@Resource
	private OrderCloseMapper closemapper;

	@Resource
	private OmOrderHeadersAllMapper headermapper;

	@Resource
	private OmOrderLinesAllMapper linemapper;

	@Resource
	private OmOrderSpiltsMapper spiltmapper;

	@Resource
	private CMerchCustAccountLogMapper accountlogmapper;

	@Resource
	private CMerchCustAccountMapper accountmapper;

	@Resource
	private CMerchCustBalancesMapper balancesmapper;
	
	@Resource
	private  OrderRFC  orderRfc;
	@Override
	public AjaxDTO closeList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		List<OrderSearchModel> list = new ArrayList<OrderSearchModel>();
		list = this.closemapper.closeList(params);
		int total = this.closemapper.countCloseList(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public Map<String,Object>  doClose(Long id,TEmployee user) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> result = new HashMap<String,Object> ();
		//获取订单头
		OmOrderHeadersAll  order = this.headermapper.selectByPrimaryKey(id);
		order.setAttribute11(d.format(new Date()));
		order.setAttribute12(user.getName());
		this.headermapper.updateByPrimaryKeySelective(order);
		OmOrderLinesAllExample  lineex = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineext = lineex.createCriteria();
		lineext.andHeaderIdEqualTo(id);
		//订单行
		List<OmOrderLinesAll> lines = this.linemapper.selectByExample(lineex);
		for(OmOrderLinesAll line :lines){
			//拆分订单信息
			OmOrderSpiltsExample spiltex = new OmOrderSpiltsExample();
			OmOrderSpiltsExample.Criteria spiltext = spiltex.createCriteria();
			spiltext.andHeaderIdEqualTo(id);
			spiltext.andLineIdEqualTo(line.getId());
			List<OmOrderSpilts> spilts = this.spiltmapper.selectByExample(spiltex);
			for(OmOrderSpilts spilt:spilts){
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("orderid", order.getSapOrderId());
				params.put("lineid", line.getId());
				params.put("itemNo", spilt.getOrderitemSapNo());
				params.put("materialId", spilt.getMaterialId());
				//应收发票数量
				BigDecimal  invouceNum = this.closemapper.getInvouceNum(params);
				//交货单数量
				BigDecimal  deliveredNum = this.closemapper.getDeliveredNum(params);
				if(invouceNum.compareTo(deliveredNum)!=0){
					result.put("type", "500");
					result.put("msg", spilt.getMaterialId()+"对应的应收发票数量和交货单数量不一致，不允许关闭订单");
					return result;
				}
			}
		}
		logger.info("提交订单关闭异步处理");
		orderRfc.executeInThread(this.orderRfc.constructInputParamForDel(id), OrderCloseCallback.class.getSimpleName());
		result.put("type", "200");
		result.put("msg", "订单关闭处理中");
		return result;
	}
	
	public int InsertLog(String accountType,BigDecimal amt,OmOrderHeadersAll order){
		//获取期间余额表
		CMerchCustBalancesExample balancesex =new CMerchCustBalancesExample();
		CMerchCustBalancesExample.Criteria balancesext= balancesex.createCriteria();
		balancesext.andMerchCustIdEqualTo(order.getMerchCustId());
		balancesext.andOrganizationIdEqualTo(order.getOrganizationId());
		balancesext.andAccountTypeEqualTo(accountType);
		balancesext.andPeriodEqualTo(DateUtil.periodNow());
		List<CMerchCustBalances> balances = this.balancesmapper.selectByExample(balancesex);
		if(balances !=null && balances.size()==1){
			CMerchCustBalances balance = balances.get(0);
			balance.setdAmt(balance.getdAmt().add(amt));
			this.balancesmapper.updateByPrimaryKeySelective(balance);
		}
		CMerchCustAccountLog  log = new CMerchCustAccountLog();
		log.setAccountType(accountType);
		log.setdAmt(amt);
		log.setCreateTs(new Date());
		log.setMerchCustId(order.getMerchCustId());
		log.setOrganizationId(order.getOrganizationId());
		log.setOrderId(order.getId());
		log.setType("6");
		log.setPeriod(DateUtil.periodNow());
		log.setStates("S");
		return this.accountlogmapper.insert(log);
	}

	@Override
	public void closeTransferOrder(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_order_id",id); 
		this.closemapper.closeTransferOrder(map);
	}

}
