package com.hhnz.jco.business.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhnz.api.cache.CacheService;
import com.hhnz.jco.business.RfcCaller;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.jco.message.RFCMessage;

/**
 * @author: chaoyang.ren
 * @date:2016年8月11日
 * @time:上午9:16:06
 * @email:chaoyang.ren@foxmail.com
 */
@Component
public abstract class AbstractRfcCaller implements RfcCaller{
	private static final Log LOG = LogFactory.getLog(AbstractRfcCaller.class);
	@Autowired
	private CacheService cacheService;
	
	/**
	 * 失败后重新处理
	 * @author: chaoyang.ren 
	 * @date:2016年9月2日  上午11:07:42
	 * @param redo
	 */
	public void failureRedo(RfcRedoDto rfcDto){
		LOG.info("RFC 执行失败,加入缓存队列，param:"+rfcDto.getJsonParam());
		//定时执行失败任务已由缓存改为从数据库获取
//		rfcDto.setFailureTimes(rfcDto.getFailureTimes()+1);
//		storeInCache(rfcDto);
	}
	
	/**
	 * 缓存待执行的RFC参数
	 * @see com.hhnz.jco.job.RfcRedoDto.RfcExeType
	 * @author: chaoyang.ren 
	 * @date:2016年8月11日  上午10:00:33
	 * @param {@link RfcRedoDto} 失败重试参数
	 */
	public void storeInCache(RfcRedoDto redo){
		cacheService.put(redo.getSerialNo(), redo);
		LOG.info("缓存待执行RFC参数！param:"+redo.getJsonParam());
	}
	
	/**
	 * 异步消息处理
	 * @author: chaoyang.ren 
	 * @date:2016年11月1日  下午3:58:43
	 * @param dto
	 */
	public void executeInThread(RfcRedoDto dto){
		try {
			RFCMessage.put(dto);
		} catch (InterruptedException e) {
			LOG.error("异步处理添加消息队列失败，改为添加至缓存重处理！", e);
			failureRedo(dto);
		}
	}
}
