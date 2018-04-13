package com.hhnz.crm.service;

import java.math.BigDecimal;
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
import com.hhnz.util.UnitConverter;
import com.hhnz.util.enmus.MaterialUnit;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:下午3:58:36
 * @email:chaoyang.ren@foxmail.com
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UnitConvertTest {
	private static final Log LOG = LogFactory.getLog(UnitConvertTest.class);
	
	@Test
	public void testCreate(){
		BigDecimal b = UnitConverter.convert(BigDecimal.valueOf(200), "20200939", MaterialUnit.KG);
		LOG.error("==========================");
		System.out.println(b);
		LOG.error("==========================");
	}
	
}
