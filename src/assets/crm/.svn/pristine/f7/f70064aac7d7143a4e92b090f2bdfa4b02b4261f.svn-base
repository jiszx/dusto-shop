package com.hhnz.jco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhnz.api.cache.CacheService;
import com.hhnz.jco.business.account.AccountAdjustRFC;
import com.hhnz.jco.business.account.InputDTO;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:下午3:58:36
 * @email:chaoyang.ren@foxmail.com
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountAdjustRFCTest {
	@Autowired
	private AccountAdjustRFC accountAdjustRFC;
	@Autowired
	private CacheService cacheService;
	
	@Test
	public void testExecute(){
		Long id = 342l;
		InputDTO input = accountAdjustRFC.constructInputParam(id);
		accountAdjustRFC.execute(input);
//		accountAdjustRFC.storeInCache(new RfcRedoDto(JsonUtil.toJSON(input), RfcExeType.ACCOUNT));
//		Set<String> keys = cacheService.getKeys(RFCConstants.CACHE_KEY+"*");
//		System.out.println(keys);
	}
}
