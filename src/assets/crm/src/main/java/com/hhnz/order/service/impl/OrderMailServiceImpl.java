package com.hhnz.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.order.dto.DeliveryOrderDTO;
import com.hhnz.order.dto.DeliveryOrderItemDTO;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.service.OrderMailService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.rmi.db.model.customer.CustStation;
import com.hhnz.rmi.db.model.customer.Customer;
import com.hhnz.rmi.db.model.material.MaterialBase;
import com.hhnz.rmi.db.model.order.Order;
import com.hhnz.rmi.db.model.order.OrderSplits;
import com.hhnz.rmi.db.repository.customer.CustStationRepository;
import com.hhnz.rmi.db.repository.customer.CustomerRepository;
import com.hhnz.rmi.db.repository.material.MaterialBaseRepository;
import com.hhnz.rmi.db.repository.order.OrderRepository;
import com.hhnz.rmi.db.repository.order.OrderSplitsRepository;
import com.hhnz.rmi.util.BigDecimalUtil;
import com.hhnz.rmi.util.token.TokenManager;
import com.hhnz.rmi.util.token.jwt.JWTToken;
import com.hhnz.util.SendMail;
import com.hhnz.util.UnitConverter;
import com.hhnz.util.enmus.MaterialUnit;
import com.hhnz.util.exception.HHNZException;

/**
 * @author: chaoyang.ren
 * @date:Mar 28, 2017
 * @time:4:04:04 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Service
@Transactional
public class OrderMailServiceImpl implements OrderMailService {
	protected static final Log LOG = LogFactory.getLog(OrderMailServiceImpl.class);
	@Resource
	private OrderRepository orderRepository;
	@Resource
	private OrderSplitsRepository orderSplitsRepository;
	@Resource
	private MaterialBaseRepository materialBaseRepository;
	@Resource
	private CustomerRepository customerRepository;
	@Resource
	private CustStationRepository custStationRepository;
	@Resource
	private SendMail sendMail;
	@Autowired
    private IAttachmentService attaService;
	@Override
	public DeliveryOrderDTO searchDeliveryOrder(Long orderId) {
		DeliveryOrderDTO dod = new DeliveryOrderDTO();
		Order order = orderRepository.findOne(orderId);
		dod.setOrder(order);
		Customer customer = customerRepository.findOne(order.getMerchCustId());
		dod.setCustomer(customer);
		List<OrderSplits> orderSplits = orderSplitsRepository.findByOrderHeaderId(orderId);
		List<DeliveryOrderItemDTO> itemList = new ArrayList<>(orderSplits.size());
		for (OrderSplits orderSplit : orderSplits) {
			DeliveryOrderItemDTO doid = new DeliveryOrderItemDTO();
			doid.setOrderSplits(orderSplit);
			String materialId = orderSplit.getMaterialId();
			BigDecimal bagNum = orderSplit.getNum();
			MaterialBase materialBase = materialBaseRepository.findOne(materialId);
			BigDecimal bagNumPerBox = BigDecimalUtil.of(materialBase.getAttribute6());
			BigDecimal boxWeight = UnitConverter.convert(bagNumPerBox, materialBase.getSapId(), MaterialUnit.TO);
			BigDecimal totalWeight = UnitConverter.convert(bagNum, materialBase.getSapId(), MaterialUnit.TO);
			doid.setBoxWeight(boxWeight);
			doid.setTotalWeight(totalWeight);
			doid.setMaterialBase(materialBase);
			itemList.add(doid);
		}
		dod.setOrderItems(itemList);
		return dod;
	}

	@Override
	public boolean logisticsNotice(Long orderId) {
		TokenManager tokenManager = new JWTToken(LOGSTICS_PRINT_TOKEN_KEY);
		Map<String, Object> payload = new HashMap<>();
		payload.put("orderId", orderId);//邮件里的物流单打印连接，不设置超时
		String token = tokenManager.sign(payload);
		
		Order order = orderRepository.findOne(orderId);
		Long merchCustId = order.getMerchCustId();
		Customer customer = customerRepository.findOne(merchCustId);
		String[] files = null;
		List<TAttachment> attachments = null;
		try {
			attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), orderId.toString(), null);
		} catch (Exception e) {
			throw HHNZException.getMyException(e);
		}
		if(attachments != null && !attachments.isEmpty()){
			files = new String[attachments.size()];
			int i=0;
			for (TAttachment tAttachment : attachments) {
				files[i] = tAttachment.getFilePath();
				i++;
			}
		}
		DeliveryOrderDTO dod = searchDeliveryOrder(orderId);
		dod.setToken(token);
		Customer logisticsCust = customerRepository.findOne(customer.getPid());
		if(StringUtils.isBlank(logisticsCust.getEmail())){
			LOG.error("发送物流商邮件失败，物流商邮件地址为空！");
			return false;
		}
		String recipient = null;
		CustStation custStation = custStationRepository.findByMerchCustId(logisticsCust.getId());
		if(custStation == null || custStation.getStation() == null || custStation.getStation().getSalesrep() == null || StringUtils.isBlank(custStation.getStation().getSalesrep().getEmail())){
			LOG.error("抄送物流商对应销售人员的邮件失败，无法获取该物流商对应销售的邮件地址！");
		}else{
			recipient = custStation.getStation().getSalesrep().getEmail();
		}
		return sendMail.noticeDelivery(customer.getName(), files, dod, logisticsCust.getEmail(), recipient);
	}
}
