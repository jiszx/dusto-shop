package com.hhnz.jco.message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.jco.model.TRfcExecute;
import com.hhnz.jco.service.IRFCExecuteService;
import com.hhnz.util.ApplicationContextUtil;

/**
 * RFC 消息队列处理类.
 * @author: chaoyang.ren
 * @date:2016年10月31日
 * @time:下午4:55:21
 * @email:chaoyang.ren@foxmail.com
 */
public class RFCMessage {
	private static final Log LOG = LogFactory.getLog(RFCMessage.class);
	/**
	 * rfc执行线程池的最大线程数
	 */
	private static final int MAX_POOL_SIZE = 4; 
	static class RFCMessageHolder{
		static final RFCMessage INSTANCE = new RFCMessage();
	}
	public static RFCMessage getInstance(){
        return RFCMessageHolder.INSTANCE;
    }
	/**
	 * rfc执行队列
	 */
	private BlockingQueue<RfcRedoDto> messages = new LinkedBlockingQueue<>();
	private ExecutorService executorService = Executors.newFixedThreadPool(MAX_POOL_SIZE);
	private RFCMessage(){}
	
	/**
	 * 添加待处理数据队列
	 * @author: chaoyang.ren 
	 * @date:2016年11月1日  上午10:31:45
	 * @param dto
	 * @throws InterruptedException
	 */
	public static void put(RfcRedoDto dto) throws InterruptedException{
		Assert.notNull(dto);
		LOG.info("添加队列数据:"+dto.getJsonParam());
		RFCMessage.getInstance().putMsg(dto);
	}
	
	/**
	 * 阻塞获取数据并移除队列数据
	 * @author: chaoyang.ren 
	 * @date:2016年11月1日  上午10:32:02
	 * @return
	 * @throws InterruptedException
	 */
	public static RfcRedoDto take() throws InterruptedException{
		return RFCMessage.getInstance().takeMsg();
	}
	
	/**
	 * 非阻塞获取并移除队列数据
	 * @author: chaoyang.ren 
	 * @date:2016年11月1日  上午10:32:26
	 * @return
	 * @throws InterruptedException
	 */
	public static RfcRedoDto poll() throws InterruptedException{
		return RFCMessage.getInstance().pollMsg();
	}
	
	/**
	 * 获取RFC远程执行线程池
	 * @author: chaoyang.ren 
	 * @date:2016年11月1日  上午10:33:04
	 * @return
	 */
	public static ExecutorService getExecutor(){
		return RFCMessage.getInstance().getExecutorService();
	}
	
	/**
	 * 启动处理消息线程,如果已达线程池最大值则停止生成
	 * @author: chaoyang.ren 
	 * @date:2016年11月1日  上午11:25:53
	 */
	public synchronized void startExecutor(){
		RFCMessage.getExecutor().execute(new RfcExecuteThread());
		int currentPoolSize = ((ThreadPoolExecutor)RFCMessage.getExecutor()).getPoolSize();
		for (int i = currentPoolSize; i < MAX_POOL_SIZE-1; i++) {
			RFCMessage.getExecutor().execute(new RfcExecuteThread());
		}
		LOG.info("当前RFC线程池中线程数："+((ThreadPoolExecutor)RFCMessage.getExecutor()).getPoolSize());
	}
	
	private ExecutorService getExecutorService() {
		return executorService;
	}
	private synchronized void putMsg(RfcRedoDto dto) throws InterruptedException{
		IRFCExecuteService rfcService = ApplicationContextUtil.getBean(IRFCExecuteService.class);
		rfcService.save(TRfcExecute.transferFromDto(dto));
		messages.put(dto);
		this.startExecutor();
	}
	private RfcRedoDto takeMsg() throws InterruptedException{
		return messages.take();
	}
	private RfcRedoDto pollMsg() throws InterruptedException{
		return messages.poll();
	}
}
