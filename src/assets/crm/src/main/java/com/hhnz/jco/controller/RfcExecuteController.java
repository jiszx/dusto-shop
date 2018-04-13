package com.hhnz.jco.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.business.RfcCaller;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.jco.message.RFCMessage;
import com.hhnz.jco.model.TRfcExecute;
import com.hhnz.jco.model.TRfcExecuteHistory;
import com.hhnz.jco.service.IRFCExecuteService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年11月10日
 * @time:下午2:59:44
 * @email:chaoyang.ren@foxmail.com
 */
@Controller
@RequestMapping("/monitor/rfc")
public class RfcExecuteController {
	@Resource
	private IRFCExecuteService rfcExecuteService;
	private static final Log LOG = LogFactory.getLog(RfcExecuteController.class);
	
	@RequestMapping(value="current/list.html",method=RequestMethod.GET)
	public String listPage(){
		return "monitor/rfclist";
	}
	
	/**
	 * 查询当前任务
	 * @author: chaoyang.ren 
	 * @date:2016年11月10日  下午3:11:47
	 * @param bean
	 * @param re
	 * @return
	 */
	@RequestMapping(value="current/list",method=RequestMethod.GET)
	public @ResponseBody AjaxDTO searchCurrentExecutions(AjaxDTO bean, TRfcExecute re){
		return this.rfcExecuteService.searchCurrentExecutions(bean, re);
	}
	
	/**
	 * 查询历史任务
	 * @author: chaoyang.ren 
	 * @date:2016年11月10日  下午3:12:00
	 * @param bean
	 * @param re
	 * @return
	 */
	@RequestMapping(value="history/list",method=RequestMethod.GET)
	public @ResponseBody AjaxDTO searchHistoryExecutions(AjaxDTO bean, TRfcExecuteHistory reh){
		return this.rfcExecuteService.searchHistoryExecutions(bean, reh);
	}
	
	/**
	 * 更新参数
	 * @author: chaoyang.ren 
	 * @date:Jan 3, 2017  5:31:26 PM
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value="current/refresh",method=RequestMethod.POST)
	@ResponseBody
	public String refreshParam(String serialNo){
		TRfcExecute e = rfcExecuteService.findBySerialNo(serialNo);
		if(e == null){
			return "错误的请求!";
		}
		//rfc请求历史更新参数
		rfcExecuteService.refreshParam(e);
		return RFCConstants.SUCCESS_FLAG;
	}
	
	/**
	 * 手工触发
	 * @author: chaoyang.ren 
	 * @date:2016年11月10日  下午4:23:19
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value="current/manual",method=RequestMethod.POST)
	@ResponseBody
	public String manualExecute(String serialNo){
		TRfcExecute e = rfcExecuteService.findBySerialNo(serialNo);
		if(e == null){
			return "错误的请求!";
		}
		try {
			RFCMessage.put(new RfcRedoDto(e));
		} catch (InterruptedException e1) {
			return "加入执行队列失败！";
		}
		return RFCConstants.SUCCESS_FLAG;
	}
	
	/**
	 * 手工触发
	 * @author: chaoyang.ren 
	 * @date:2016年11月10日  下午4:23:19
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value="manual")
	@ResponseBody
	public String manualExecute(RfcExeType exeType,Long[] ids,String callback) {
		LOG.info("手动执行RFC开始，rfc类别:"+exeType.toString()+",crm id:"+ids);
		if(ids == null){
			return RFCConstants.ERROR_FLAG;
		}
		StringBuilder failedOns = new StringBuilder();
		for (Long id : ids) {
			RfcCaller rfcCaller = ApplicationContextUtil.getBean(exeType.getBeanName());
			String param = rfcCaller.constructParam(id);
			if(StringUtils.isBlank(param)){
				failedOns.append(id).append(",");
				continue;
			}
			RFCCallback c = null;
			if(StringUtils.isNotBlank(callback)){
				c = ApplicationContextUtil.getBean(callback);
			}
			try {
				RFCMessage.put(new RfcRedoDto(param, exeType, c));
			} catch (Exception e) {
				failedOns.append(id).append(",");
				continue;
			}
		}
		LOG.info("手动执行RFC完成，rfc类别:"+exeType.toString()+",失败的crm id:"+failedOns.toString());
		return RFCConstants.SUCCESS_FLAG;
	}
	
	/**
	 * 不再执行
	 * @author: chaoyang.ren 
	 * @date:2016年11月11日  下午4:27:22
	 * @param serialNo
	 * @return
	 */
	@RequestMapping(value="current/disable",method=RequestMethod.POST)
	@ResponseBody
	public String manualDisable(String serialNo){
		TRfcExecute e = rfcExecuteService.findBySerialNo(serialNo);
		if(e == null){
			return "错误的请求!";
		}
		rfcExecuteService.addToHistory(e);
		return RFCConstants.SUCCESS_FLAG;
	}
	
	/**
	 * 
	 * @author: chaoyang.ren 
	 * @date:2016年12月10日  下午1:49:00
	 * @param serialNo
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping(value="current/remoteExecute",method=RequestMethod.POST)
	@ResponseBody
	public String remoteExecute(RfcExeType exeType, Long key, String callback) throws InterruptedException{
		Assert.notNull(exeType);
		Assert.notNull(key);
		RfcCaller caller = ApplicationContextUtil.getBean(exeType.getBeanName());
		String json = caller.constructParam(key);
		Assert.notNull(json,"can not find suitable response for key");
		RFCCallback c = null;
		if(StringUtils.isNotBlank(callback)){
			c = ApplicationContextUtil.getBean(callback);
		}
		RFCMessage.put(new RfcRedoDto(json, exeType, c));
		return RFCConstants.SUCCESS_FLAG;
	}
	
	/**
	 * @author: chaoyang.ren 
	 * @date:2016年12月10日  下午1:49:00
	 * @param serialNo
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping(value="current/remoteExecute/param",method=RequestMethod.POST)
	@ResponseBody
	public String remoteParamConstract(RfcExeType exeType, Long key, String callback) throws InterruptedException{
		Assert.notNull(exeType);
		Assert.notNull(key);
		RfcCaller caller = ApplicationContextUtil.getBean(exeType.getBeanName());
		String json = caller.constructParam(key);
		Assert.hasLength(json,"can not find suitable response for key");
		return json;
	}
}
