package com.hhnz.jco.business.inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.customer.mapper.CMerchCustDistributionMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.jco.DefaultRFCInvoker;
import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.RFCInvoker;
import com.hhnz.jco.business.base.AbstractRfcCaller;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.CommonResult;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.jco.business.inventory.InputDTO.Header;
import com.hhnz.jco.business.inventory.InputDTO.Item;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.service.OrderService;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.pub.service.IAreaService;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.DateUtil;
import com.hhnz.util.JsonUtil;

/**
 * 库存调拨
 * @author: chaoyang.ren
 * @date:2016年12月13日
 * @time:下午1:51:39
 * @email:chaoyang.ren@foxmail.com
 */
@Component("inventoryRFC")
@Transactional
public class InventoryRFC extends AbstractRfcCaller{
	private static final Log LOG = LogFactory.getLog(InventoryRFC.class);
	@Resource
	private OrderService orderService;
	@Resource
	private ICustomerService customerService;
	@Resource
	private CMerchCustDistributionMapper distributionMapper;
	@Resource
	private IorganizationService organizationService;
	@Resource
	private TMaterialBaseMapper materialMapper;
	@Resource
	private CustomerContractService contractService;
	@Autowired
    private IAreaService areaService;

	/**
	 * 库存调拨处理
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param input
	 */
	public ResultDTO execute(InputDTO input){
		String inputJson = JsonUtil.toJSON(input);
		return this.execute(new RfcRedoDto(inputJson, RfcExeType.INVENTORY));
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
		super.executeInThread(new RfcRedoDto(inputJson, RfcExeType.INVENTORY, c));
	}
	
	/**
	 * 库存调拨传SAP
	 */
	@Override
	@Transactional
	public ResultDTO execute(RfcRedoDto rfcDto) {
		LOG.info("===== CALL EXECUTE METHOD!");
		RFCInvoker rfcInvoker = new DefaultRFCInvoker();
		String json = null;
		InputDTO input = JsonUtil.fromJSON(rfcDto.getJsonParam(), InputDTO.class);
		String crmOrderId = input.getHeader().getCrmRelatedId();
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
		InputDTO inputDto = constructInputParam(Long.valueOf(id.toString()));
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
	public InputDTO constructInputParam(Long id){
		OmOrderHeadersAll order = orderService.getOrderBYid(id);
		return constructInputParam(order);
	}
	
	@Transactional(readOnly=true)
	public InputDTO constructInputParam(OmOrderHeadersAll order){
		String orderType = order.getOrderType();
		boolean returnFlag = "3".equals(orderType);//是否退货订单
		InputDTO input = new InputDTO();
		
		//退货订单获取源订单信息
		if(returnFlag){
			OmOrderHeadersAll originOrder = orderService.getOrderBYid(Long.parseLong(order.getAttribute13()));
			orderType = originOrder.getOrderType();
		}
		Header header = input.new Header();
		header.setCrmRelatedId(order.getId().toString());
		header.setFiscalYear(String.valueOf(DateUtil.getYear(order.getCreateTs())));
		CMerchCustBase saleTo = customerService.findCustBaseById(order.getMerchCustId());
		CMerchCustDistribution shipToDis = distributionMapper.selectByPrimaryKey(order.getShipId());
		CrmSalesOrganization org = organizationService.findById(order.getOrganizationId());
		if("5".equals(orderType) || "8".equals(orderType)){
			if("8".equals(saleTo.getCustType())){
				if(!StringUtils.isEmpty(order.getAttribute8()) && order.getAttribute8() != null){
					header.setVwNo(order.getAttribute8());
				}else{
					header.setVwNo("Z902");// 物流商的调拨单					
				}
			}else{
				header.setVwNo("Z903");//仓储服务商收货库存地
			}
		}
		//工厂
		Long merchidWithContract = orderService.distriButeMerchid(saleTo.getId());
		List<CMerchCustContract> contracts = contractService.findByCustomerAndOrgId(merchidWithContract, org.getId());
		if(contracts != null && !contracts.isEmpty() && contracts.get(0) != null){
			header.setFactory(contracts.get(0).getFactoryId());
		}else{
			//没有合同时，根据客户sapcode传工厂
			CrmSalesOrganization cborg = organizationService.findById(saleTo.getOrganizationId());
			header.setFactory(cborg.getSapId());
		}
		
		header.setVendor(saleTo.getSapCustomerId());
		header.setTel(shipToDis.getMobile());
		header.setAddress(shipToDis==null?saleTo.getAddress():shipToDis.getAddress());
		header.setContacts(shipToDis.getContacter());
		
		//crm 的order-split表对应sap行项目
		List<OmOrderSpilts> orderSplits = orderService.findOrderSplitsByOrderId(order.getId());
		List<Item> items = new ArrayList<Item>();
		//此处行项目定价条件与行项目一一对应
		int i = 10;
		for (OmOrderSpilts os : orderSplits) {
			Item item = input.new Item();
			item.setItemNo(String.valueOf(i));
			i += 10;
			String materialId = os.getMaterialId();
			if("8".equals(orderType)){
				item.setVwNo(order.getRdcCode());
			}else{
				String rdc = areaService.findRDCCodeByCity(saleTo.getCityId());
				Assert.notNull(rdc,"找不到城市对应的rdc！");
				item.setVwNo(rdc);
			}
			item.setMaterialId(materialId);
			if(returnFlag){
				item.setQuantity(os.getNum().negate());
			}else{
				item.setQuantity(os.getNum());
			}
			TMaterialBase mb = materialMapper.selectByPrimaryKey(materialId);
			item.setUnit(mb.getUnit());
			items.add(item);
		}
		input.setHeader(header);
		input.setItems(items);
		return input;
	}

	@Override
	public String constructParam(Object obj) {
		Assert.isTrue(obj instanceof OmOrderHeadersAll);
		OmOrderHeadersAll order = (OmOrderHeadersAll) obj;
		InputDTO inputDto = constructInputParam(order);
		return JsonUtil.toJSON(inputDto);
	}
	
}
