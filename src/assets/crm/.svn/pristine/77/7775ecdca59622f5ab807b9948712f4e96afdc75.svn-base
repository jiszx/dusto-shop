package com.hhnz.jco;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhnz.jco.business.base.BaseResultDTO;
import com.hhnz.jco.business.product.InputDTO;
import com.hhnz.jco.business.product.ProductInvRFC;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:下午3:58:36
 * @email:chaoyang.ren@foxmail.com
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductInvRFCTest {
	private static final Log LOG = LogFactory.getLog(ProductInvRFCTest.class);
	@Autowired
	private ProductInvRFC productInvRFC;
	
	@Test
	public void testExecute(){
		InputDTO input = new InputDTO("20500116", null, "", "");
		BaseResultDTO r = productInvRFC.execute(input);
		LOG.info(r.getResult().getMESSAGE());
	}
}
