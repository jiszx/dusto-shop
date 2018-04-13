package com.hhnz.jco;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhnz.jco.business.base.BaseResultDTO;
import com.hhnz.jco.business.order.InputDTO;
import com.hhnz.jco.business.order.InputDTO.ItemPriceCondition;
import com.hhnz.jco.business.order.InputDTO.OrderHeader;
import com.hhnz.jco.business.order.InputDTO.Item;
import com.hhnz.jco.business.order.InputDTO.OrderText;
import com.hhnz.jco.business.order.OrderRFC;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:下午3:58:36
 * @email:chaoyang.ren@foxmail.com
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRFCTest {
	private static final Log LOG = LogFactory.getLog(OrderRFCTest.class);
	@Autowired
	private OrderRFC orderRFC;
	
	@Ignore
	@Test
	public void testCreate(){
		InputDTO input = new InputDTO();
		
		OrderHeader header = input.new OrderHeader();
		header.setOperationType(RFCConstants.OPERATION_TYPE_ADD);
		header.setSalesDocumentCRM("12");
		header.setSalesDocumentSAP(null);
		/*
		 * 调味品ZT01;品种盐ZS01
		 */
		header.setSalesDocumentType("ZS01");
		header.setSalesOrg("1100");
		/*
		 * 10分销,30跨公司
		 */
		header.setChannel("10");
		header.setSaleTo("1100524");//SAP的客户编号
		header.setSendTo("1100524");
		header.setPriceDate("20160703");
		header.setUnloadingPosition("四川成都");
		/*
		 * TODO 装运类型为必输字段，当前没有暂定为自提
		 * 传编码 :                                  
		 * 00 自提
		 * 01 汽车运输
		 * 02 集装箱运输
	 	 * 03 火车运输
		 * 04 船运
		 */
		header.setShippingType("01");
		header.setSalesDep(null);
		header.setSalesArea(null);
		header.setPriceGroup(null);
		header.setBusiReason(null);
		header.setPositionId("1");

		//crm 的order-split表对应sap行项目
		List<Item> items = new ArrayList<Item>();
		Item item = input.new Item();
		item.setSalesItemNo("10");
		item.setMaterialNo("20200037");
		item.setOrderNum("100");
		item.setOrderUnit("TO");
		item.setFactory("1100");
		item.setShippingPosition(null);
		item.setMemo("备注");
		items.add(item);
		
		List<ItemPriceCondition> itemConditions = new ArrayList<InputDTO.ItemPriceCondition>();
		ItemPriceCondition priceCondition = input.new ItemPriceCondition();
		priceCondition.setSalesItemNo(item.getSalesItemNo());
		/*
		 * TODO 判断是否为搭赠条目，搭赠条目需传入价格信息
		 * ZPR1：(物料标准销售价，SAP自动带出，暂不传)
		 * ZTW4: 特批折扣
		 * ZTW7: 普通高卖
		 */
		priceCondition.setConditionType(null);
		priceCondition.setPrice(null);
		priceCondition.setCurrency(null);
		priceCondition.setUnit(null);
		priceCondition.setPriceUnit(null);
		itemConditions.add(priceCondition);
		
		List<OrderText> text = new ArrayList<InputDTO.OrderText>();
		OrderText o = input.new OrderText();
		o.setSalesItemNo(item.getSalesItemNo());
		o.setTextId(null);
		o.setTextLine(null);
		text.add(o);
		
		input.setOrderHeader(header);
		input.setItems(items);
		input.setItemConditions(itemConditions);
		input.setText(text);
		
		BaseResultDTO r = orderRFC.execute(input);
		LOG.info(r.getResult().getMESSAGE());
	}
	
	@Ignore
	@Test
	public void testCancel(){
        InputDTO input = new InputDTO();
		OrderHeader header = input.new OrderHeader();
		header.setOperationType(RFCConstants.OPERATION_TYPE_DEL);
		header.setSalesDocumentCRM("12");
		input.setOrderHeader(header);
		orderRFC.execute(input);
	}
	
	@Test
	public void testExecute(){
		Long id = 20161230000027L;
		InputDTO input = orderRFC.constructInputParam(id);
		orderRFC.execute(input);
	}
}
