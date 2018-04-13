package com.hhnz.jco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhnz.api.cache.CacheService;
import com.hhnz.jco.business.inventory.InputDTO;
import com.hhnz.jco.business.inventory.InventoryRFC;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.util.JsonUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:下午3:58:36
 * @email:chaoyang.ren@foxmail.com
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class InventoryRFCTest {
	@Autowired
	private InventoryRFC inventoryRFC;
	@Autowired
	private CacheService cacheService;
	
	@Test
	public void testExecute(){
		Long id = 20161213000041l;
//		Long id = 20161209000008l;
		InputDTO input = inventoryRFC.constructInputParam(id);
		inventoryRFC.execute(new RfcRedoDto(JsonUtil.toJSON(input), RfcExeType.INVENTORY, null));
	}
}
