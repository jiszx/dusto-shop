package com.hhnz.virtualwarehouse.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:下午3:58:36
 * @email:chaoyang.ren@foxmail.com
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-config.xml","classpath:/applicationContext-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class VirtualWarehouseServiceTest {
	@Autowired
	private IVirtualWarehouseService service;
	
	@Test
	public void testAddInventory(){
		CrmVirtualWarehouse vw = new CrmVirtualWarehouse();
		vw.setFactoryCode("1000");
		vw.setMaterialId("20200500");
		vw.setCustType("2");
		vw.setAmt(BigDecimal.TEN);
		System.out.println(service.addInventory(vw));
	}
	
	@Test
	public void testMinusInventory(){
		CrmVirtualWarehouse vw = new CrmVirtualWarehouse();
		vw.setFactoryCode("1000");
		vw.setMaterialId("20200500");
		vw.setCustType("2");
		vw.setAmt(BigDecimal.valueOf(9l));
		System.out.println(service.minusInventory(vw));
	}
	
	@Test
	public void testAddInventoryWithId(){
		CrmVirtualWarehouse vw = new CrmVirtualWarehouse();
		vw.setId(3l);
		vw.setAmt(BigDecimal.TEN);
		System.out.println(service.addInventory(vw));
	}
	
	@Test
	public void testMinusInventoryWithId(){
		CrmVirtualWarehouse vw = new CrmVirtualWarehouse();
		vw.setId(3l);
		vw.setAmt(BigDecimal.valueOf(9l));
		System.out.println(service.minusInventory(vw));
	}
}
