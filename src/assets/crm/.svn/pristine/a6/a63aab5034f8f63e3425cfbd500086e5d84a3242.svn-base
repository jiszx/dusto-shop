package com.hhnz.jco.business.product;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.hhnz.crm.mapper.TMaterialFactoryMapper;
import com.hhnz.crm.model.TMaterialFactory;
import com.hhnz.jco.DefaultRFCInvoker;
import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.RFCInvoker;
import com.hhnz.jco.business.base.AbstractRfcCaller;
import com.hhnz.jco.business.base.BaseResultDTO;
import com.hhnz.jco.business.base.CommonResult;
import com.hhnz.jco.business.product.ResultDTO.Item;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.util.JsonUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年8月9日
 * @time:下午4:23:23
 * @email:chaoyang.ren@foxmail.com
 */
@Component("productInvRFC")
@Transactional
public class ProductInvRFC extends AbstractRfcCaller {
	private static final Log LOG = LogFactory.getLog(ProductInvRFC.class);
	
	@Resource
	private TMaterialFactoryMapper materialFactoryMapper;
	
	/**
	 * 更新调味品成品库存
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param input
	 */
	public BaseResultDTO execute(InputDTO input){
		String inputJson = JsonUtil.toJSON(input);
		return this.execute(new RfcRedoDto(inputJson, RfcExeType.PRODUCT_INV));
	}

	/**
	 * 更新调味品成品库存
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param inputJson 输入参数{@link RfcRedoDto}
	 */
	@Override
	public BaseResultDTO execute(RfcRedoDto rfcDto) {
		RFCInvoker rfcInvoker = new DefaultRFCInvoker();
		String json = null;
		try {
			json = rfcInvoker.invokeRFC(rfcDto.getRfcExeType().getFuncName(), rfcDto.getJsonParam());
		} catch (Exception e) {
			LOG.error("从RFC接口获取数据失败！", e);
			//执行失败后缓存参数
			failureRedo(rfcDto);
			return null;
		}
		ResultDTO result = JsonUtil.fromJSON(json, ResultDTO.class);
		JsonObject jsonObject = JsonUtil.toJsonObject(json);
		
		CommonResult cr = result.getResult();
		if(cr.getTYPE().equals(RFCConstants.ERROR_FLAG)){
			LOG.warn(cr.getMESSAGE());
			return result;
		}
		LOG.info(jsonObject.get(Constants.TABLE_2));
		List<ResultDTO.Item> items = result.getItems();
		//数据本地化
		for (Item item : items) {
			TMaterialFactory mf = item.toMaterialFactory();
			TMaterialFactory emf = materialFactoryMapper.selectByPrimaryKey(mf);
			if(emf != null){
				emf.setInvNum(mf.getInvNum());
				materialFactoryMapper.updateByPrimaryKeySelective(emf);
			}else{
				materialFactoryMapper.insertSelective(mf);
			}
		}
		return result;
	}
	
	public String constructParam(Serializable id){
//		InputDTO inputDto = constructInputParam((Long) id);
//		return JsonUtil.toJSON(inputDto);
		return null;
	}

	@Override
	public String constructParam(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
