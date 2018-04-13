package com.hhnz.jco.business.inventory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
import com.hhnz.rmi.db.model.inventory.VirtualWarehouse;
import com.hhnz.rmi.db.repository.inventory.VirtualWarehouseRepository;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.JsonUtil;

/**
 * 库存查询
 * @author: chaoyang.ren
 * @date:Apr 25, 2017
 * @time:2:08:14 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Component("inventoryQueryRFC")
@Transactional
public class InventoryQueryRFC extends AbstractRfcCaller{
	private static final Log LOG = LogFactory.getLog(InventoryQueryRFC.class);

	@Resource
	private VirtualWarehouseRepository virtualWarehouseRepository;
	
	/**
	 * 库存查询
	 * @author: chaoyang.ren 
	 * @date:Apr 25, 2017  3:05:09 PM
	 * @param input
	 * @return
	 */
	public QueryResultDTO execute(QueryInputDTO input){
		String inputJson = JsonUtil.toJSON(input);
		return this.execute(new RfcRedoDto(inputJson, RfcExeType.INVENTORY_QUERY));
	}
	
	/**
	 * 异步处理
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param input
	 */
	public void executeInThread(QueryInputDTO input, String simpleName){
		String inputJson = JsonUtil.toJSON(input);
		RFCCallback c = null;
		if(StringUtils.isNotBlank(simpleName)){
			c = ApplicationContextUtil.getBean(simpleName);
		}
		super.executeInThread(new RfcRedoDto(inputJson, RfcExeType.INVENTORY_QUERY, c));
	}
	
	/**
	 * 库存查询
	 */
	@Override
	@Transactional
	public QueryResultDTO execute(RfcRedoDto rfcDto) {
		RFCInvoker rfcInvoker = new DefaultRFCInvoker();
		String json = null;
		RFCCallback c = rfcDto.getRfcCallback();
		String jsonParam = rfcDto.getJsonParam();
		try {
			json = rfcInvoker.invokeRFC(rfcDto.getRfcExeType().getFuncName(), jsonParam);
		} catch (Exception e) {
			LOG.error("从RFC接口获取数据失败！", e);
			QueryResultDTO result = new QueryResultDTO();
			result.setResult(CommonResult.error(e));
			//执行错误后的回调
			if(c != null){
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("param", jsonParam);
				c.errorCallBack(CallbackParam.of(null, data, result.getResult()));
			}
			return result;
		}
		QueryInputDTO input = JsonUtil.fromJSON(jsonParam, QueryInputDTO.class);
		QueryResultDTO result = JsonUtil.fromJSON(json, QueryResultDTO.class);
		CommonResult commonResult = result.getResult();
		if(RFCConstants.SUCCESS_FLAG.equals(commonResult.getTYPE())){
			//执行成功后的回调
			if(c != null){
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("atpNumber", result.getAtpNumber());
				data.put("totalNumber", result.getTotalNumber());
				data.put("rdcCode", input.getRdcCode());
				data.put("factoryCode", input.getFactoryCode());
				data.put("materialId", input.getMaterialId());
				c.successCallBack(CallbackParam.of(null, data, commonResult));
			}
        }else{
        	//执行错误后的回调
			if(c != null){
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("param", jsonParam);
				c.errorCallBack(CallbackParam.of(null, data, commonResult));
			}
        }
		return result;
	}
	
	public String constructParam(Serializable id){
		VirtualWarehouse vw = virtualWarehouseRepository.findOne(Long.valueOf(id.toString()));
		return constructParam(vw);
	}
	
	@Override
	public String constructParam(Object obj) {
		Assert.isTrue(obj instanceof VirtualWarehouse);
		VirtualWarehouse vw = (VirtualWarehouse) obj;
		return constructParam(vw);
	}
	
	public String constructParam(VirtualWarehouse vw){
		QueryInputDTO inputDto = new QueryInputDTO();
		inputDto.setFactoryCode(vw.getFactoryCode());
		inputDto.setMaterialId(vw.getMaterialId());
		inputDto.setRdcCode(vw.getVwNo());
		inputDto.setRule("B");
		return JsonUtil.toJSON(inputDto);
	}
	
}
