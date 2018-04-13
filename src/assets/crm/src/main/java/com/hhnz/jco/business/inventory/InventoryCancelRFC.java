package com.hhnz.jco.business.inventory;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hhnz.jco.DefaultRFCInvoker;
import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.RFCInvoker;
import com.hhnz.jco.business.base.AbstractRfcCaller;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.CommonResult;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.order.service.OrderService;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.JsonUtil;

/**
 * 库存调拨
 * @author: chaoyang.ren
 * @date:2016年12月13日
 * @time:下午1:51:39
 * @email:chaoyang.ren@foxmail.com
 */
@Component("inventoryCancelRFC")
@Transactional
public class InventoryCancelRFC extends AbstractRfcCaller{
	private static final Log LOG = LogFactory.getLog(InventoryCancelRFC.class);
	@Resource
	private OrderService orderService;

	/**
	 * 库存调拨处理
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param input
	 */
	public ResultDTO execute(InputDTO input){
		String inputJson = JsonUtil.toJSON(input);
		return this.execute(new RfcRedoDto(inputJson, RfcExeType.INVENTORY_CANCEL));
	}
	
	/**
	 * 异步处理
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param input
	 */
	public void executeInThread(InputDTO input, String simpleName){
		String inputJson = JsonUtil.toJSON(input);
		RFCCallback c = null;
		if(StringUtils.isNotBlank(simpleName)){
			c = ApplicationContextUtil.getBean(simpleName);
		}
		super.executeInThread(new RfcRedoDto(inputJson, RfcExeType.INVENTORY_CANCEL, c));
	}
	
	/**
	 * 库存调拨关闭传SAP
	 */
	@Override
	@Transactional
	public ResultDTO execute(RfcRedoDto rfcDto) {
		LOG.info("===== CALL EXECUTE METHOD!");
		RFCInvoker rfcInvoker = new DefaultRFCInvoker();
		String json = null;
		CancelInputDTO input = JsonUtil.fromJSON(rfcDto.getJsonParam(), CancelInputDTO.class);
		String crmOrderId = input.getCrmRelatedId().toString();
		Long orderId = Long.valueOf(crmOrderId);
		RFCCallback c = rfcDto.getRfcCallback();
		try {
			json = rfcInvoker.invokeRFC(rfcDto.getRfcExeType().getFuncName(), rfcDto.getJsonParam());
		} catch (Exception e) {
			LOG.error("从RFC接口获取数据失败！", e);
			//执行失败后缓存参数
			failureRedo(rfcDto);
			ResultDTO result = new ResultDTO();
			result.setResult(CommonResult.error(e));
			//执行错误后的回调
			if(c != null){
				c.errorCallBack(CallbackParam.of(orderId,result.getResult()));
			}
			return result;
		}
		LOG.info("===== INVOKE RFC FINISHED!");
		ResultDTO result = JsonUtil.fromJSON(json, ResultDTO.class);
		LOG.info("===== READ ORDER HEADER!");
		CommonResult commonResult = result.getResult();
		if(RFCConstants.SUCCESS_FLAG.equals(commonResult.getTYPE())){
			//执行成功后的回调
			if(c != null){
				c.successCallBack(CallbackParam.of(orderId,commonResult));
			}
        }else{
        	//执行错误后的回调
			if(c != null){
				c.errorCallBack(CallbackParam.of(orderId,commonResult));
			}
        }
		return result;
	}
	
	public String constructParam(Serializable id){
		CancelInputDTO inputDto = constructInputParam(Long.parseLong(id.toString()));
		return JsonUtil.toJSON(inputDto);
	}
	
	/**
	 * 构造库存调拨接口输入参数
	 * @author: chaoyang.ren 
	 * @date:2016年12月13日  下午2:42:41
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public CancelInputDTO constructInputParam(Long id){
		CancelInputDTO cid = new CancelInputDTO();
		cid.setCrmRelatedId(id);
		return cid;
	}

	@Override
	public String constructParam(Object obj) {
		Assert.isTrue(obj instanceof Long);
		Long id = (Long) obj;
		CancelInputDTO inputDto = constructInputParam(id);
		return JsonUtil.toJSON(inputDto);
	}
	
}
