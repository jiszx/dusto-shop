package com.hhnz.jco.job;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hhnz.jco.enu.RfcExeStatus;
import com.hhnz.jco.message.RFCMessage;
import com.hhnz.jco.model.TRfcExecute;
import com.hhnz.jco.service.IRFCExecuteService;

/**
 * @author: chaoyang.ren
 * @date:2016年8月10日
 * @time:上午11:20:27
 * @email:chaoyang.ren@foxmail.com
 */
@Component
public class RfcRedoJob {
	private static final Log LOG = LogFactory.getLog(RfcRedoJob.class);
	
	@Resource
	private IRFCExecuteService rfcExecuteService;
	
//	@Scheduled(cron="0 0/3 * * * ?")
	public void autoRedo(){
		//失败的任务
		TRfcExecute re = new TRfcExecute();
		re.setStatus(RfcExeStatus.FAILURE.name());
		List<TRfcExecute> res = rfcExecuteService.searchCurrentExecutions(re);
		if(res != null){
			LOG.info("自动执行rfc任务");
			for (TRfcExecute e : res) {
				try {
					RFCMessage.put(new RfcRedoDto(e));
				} catch (InterruptedException e1) {
					LOG.error("自动执行加入执行队列失败！", e1);
				}
			}
		}
		//接口通讯导致挂起的任务
		re.setStatus(RfcExeStatus.INPROGRESS.name());
		final int failure = 5;
		re.setFailureTimes(failure);
		List<TRfcExecute> resi = rfcExecuteService.searchCurrentExecutions(re);
		if(resi != null){
			final int time = 180000;//3分钟
			for (TRfcExecute e : resi) {
				Long updateDate = e.getUpdateDate().getTime();
				if(System.currentTimeMillis()-updateDate>time){
					LOG.info("自动执行搁置的rfc任务");
					try {
						RFCMessage.put(new RfcRedoDto(e));
					} catch (InterruptedException e1) {
						LOG.error("自动执行加入执行队列失败！", e1);
					}
				}
			}
		}
	}
	
	
}
