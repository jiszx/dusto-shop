package com.hhnz.jco;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhnz.api.cache.CacheService;
import com.hhnz.jco.business.writeoff.InputDTO;
import com.hhnz.jco.business.writeoff.WriteOffRFC;
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
public class WriteOffRFCTest {
	@Autowired
	private WriteOffRFC writeOffRFC;
	@Autowired
	private CacheService cacheService;
	
	@Test
	public void testExecute(){
		Long id = 13l;
		InputDTO input = writeOffRFC.constructInputParam(id);
		writeOffRFC.execute(new RfcRedoDto(JsonUtil.toJSON(input), RfcExeType.WRITEOFF, null));
//		writeOffRFC.executeInThread(input, null);
	}
}
