package com.hhnz.jco.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.business.RfcCaller;
import com.hhnz.jco.business.base.BaseResultDTO;
import com.hhnz.jco.business.base.CommonResult;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.jco.enu.RfcExeStatus;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.jco.model.TRfcExecute;
import com.hhnz.jco.service.IRFCExecuteService;
import com.hhnz.util.ApplicationContextUtil;

/**
 * 处理rfc消息的线程
 * @author: chaoyang.ren
 * @date:2016年11月1日
 * @time:上午11:27:25
 * @email:chaoyang.ren@foxmail.com
 */
public class RfcExecuteThread implements Runnable{
	private static final Log LOG = LogFactory.getLog(RfcExecuteThread.class);
	@Override
	public void run() {
		//阻塞处理获取队列，当队列为空时让线程处于等待状态
//		while(true) {
			RfcRedoDto dto = null;
			try {
				dto = RFCMessage.take();
			} catch (InterruptedException e) {
				LOG.error("获取rfc队列中断！", e);
				return;
//				continue;
			}
			if(null == dto){
				return;
//				continue;
			}
			LOG.info("RFC消息线程:"+dto.getSerialNo()+"开始，处理内容："+dto.getJsonParam());
			RfcCaller rfcCaller = ApplicationContextUtil.getBean(dto.getRfcExeType().getBeanName());
			CommonResult cr = null;
			try {
				BaseResultDTO result = rfcCaller.execute(dto);
				cr = result.getResult();
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				RFCCallback rfcCallback = dto.getRfcCallback();
				String callbackClass = rfcCallback==null?"":rfcCallback.getClass().getName();
				LOG.error("--CALLBACK CLASS:"+callbackClass);
				cr = CommonResult.error(e);
			}
			IRFCExecuteService rfcService = ApplicationContextUtil.getBean(IRFCExecuteService.class);
			TRfcExecute rfcExecute = TRfcExecute.transferFromDto(dto);
			TRfcExecute existedExecute = rfcService.findBySerialNo(dto.getSerialNo());
			if(existedExecute != null){
				rfcExecute.setCreateDate(existedExecute.getCreateDate());
				rfcExecute.setFailureTimes(existedExecute.getFailureTimes());
			}
			rfcExecute.setResult(cr.getMESSAGE());
			if(RFCConstants.SUCCESS_FLAG.equals(cr.getTYPE())){
				rfcExecute.setStatus(RfcExeStatus.SUCCESS.name());
				rfcService.addToHistory(rfcExecute);
			}else if(RFCConstants.ERROR_FLAG.equals(cr.getTYPE())){
				rfcExecute.setStatus(RfcExeStatus.FAILURE.name());
				rfcExecute.setFailureTimes(rfcExecute.getFailureTimes()+1);
				rfcService.save(rfcExecute);
			}else{
				rfcExecute.setStatus(RfcExeStatus.FAILURE.name());
				rfcExecute.setFailureTimes(rfcExecute.getFailureTimes()+1);
				rfcService.save(rfcExecute);
			}
			LOG.info("RFC消息线程:"+dto.getSerialNo()+"处理完成,结果："+cr.getTYPE()+",信息："+cr.getMESSAGE());
//		}
	}
}
