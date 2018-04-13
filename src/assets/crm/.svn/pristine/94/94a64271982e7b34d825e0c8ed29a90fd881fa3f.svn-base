package com.hhnz.jco.business.order;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hhnz.api.cache.CacheService;
import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.mapper.TMaterialFactoryMapper;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.model.TMaterialFactoryExample;
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
import com.hhnz.jco.business.order.InputDTO.Item;
import com.hhnz.jco.business.order.InputDTO.ItemPriceCondition;
import com.hhnz.jco.business.order.InputDTO.OrderHeader;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.service.OrderService;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.JsonUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年8月24日
 * @time:上午9:42:10
 * @email:chaoyang.ren@foxmail.com
 */
@Component("orderRFC")
@Transactional
public class OrderRFC extends AbstractRfcCaller{
	private static final Log LOG = LogFactory.getLog(OrderRFC.class);
	@Resource
	private OrderService orderService;
	@Resource
	private ICustomerService customerService;
	@Resource
	private IorganizationService organizationService;
	@Resource
	private TMaterialBaseMapper materialMapper;
	@Resource
	private TMaterialFactoryMapper materialFactoryMapper;
	@Resource
	private CMerchCustDistributionMapper distributionMapper;
	@Resource
	private CustomerContractService contractService;
	@Resource
    private RuntimeService runtimeService;
	@Resource
	private CacheService cacheService;

	/**
	 * 订单处理
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param input
	 */
	public ResultDTO execute(InputDTO input){
		String inputJson = JsonUtil.toJSON(input);
		return this.execute(new RfcRedoDto(inputJson, RfcExeType.ORDER));
	}
	
	/**
	 * 异步订单处理
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
		super.executeInThread(new RfcRedoDto(inputJson, RfcExeType.ORDER, c));
	}
	
	/**
	 * 订单处理功能（新建修改删除）传SAP
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param rfcDto 输入参数{@link RfcRedoDto}
	 */
	@Override
	@Transactional
	public ResultDTO execute(RfcRedoDto rfcDto) {
		LOG.info("===== CALL EXECUTE METHOD!");
		RFCInvoker rfcInvoker = new DefaultRFCInvoker();
		String msg = StringUtils.EMPTY;
		String json = null;
		InputDTO input = JsonUtil.fromJSON(rfcDto.getJsonParam(), InputDTO.class);
		String crmOrderId = input.getOrderHeader().getSalesDocumentCRM();
		RFCCallback c = rfcDto.getRfcCallback();
		try {
			json = rfcInvoker.invokeRFC(rfcDto.getRfcExeType().getFuncName(), rfcDto.getJsonParam());
		} catch (Exception e) {
			LOG.error("从RFC接口获取数据失败！", e);
			msg = "从RFC接口获取数据失败！" + e.getMessage();
			OmOrderHeadersAll oh = orderService.getOrderBYid(Long.valueOf(crmOrderId));
			this.runtimeService.setVariable(oh.getAttribute1(),"MSG",msg);
			//执行失败后缓存参数
			failureRedo(rfcDto);
			ResultDTO result = new ResultDTO();
			result.setResult(CommonResult.error(e));
			//执行错误后的回调
			if(c != null){
				c.errorCallBack(CallbackParam.of(oh.getId(),result.getResult()));
			}
			return result;
		}
		LOG.info("===== INVOKE RFC FINISHED!");
		ResultDTO result = JsonUtil.fromJSON(json, ResultDTO.class);
		LOG.info("===== READ ORDER HEADER!");
		OmOrderHeadersAll oh = orderService.getOrderBYid(Long.valueOf(crmOrderId));
		if(RFCConstants.SUCCESS_FLAG.equals(result.getResult().getTYPE())){
			oh.setSapOrderId(result.getSapOrderId());
			if(("5").compareTo(oh.getStates())>0){
				oh.setStates("5");
			}
			LOG.info("===== UPDATE HEADER BEGIN!");
			orderService.updateOrderHeader(oh);
			LOG.info("===== UPDATE HEADER FINISHED!");
			//执行成功后的回调
			if(c != null){
				c.successCallBack(CallbackParam.of(oh.getId()));
			}
        }else{
        	msg = result.getResult().getMESSAGE();
        	//执行错误后的回调
			if(c != null){
				c.errorCallBack(CallbackParam.of(oh.getId(),result.getResult()));
			}
        	LOG.warn("==== 订单数据传输SAP返回错误！msg:"+result.getResult().getMESSAGE());
        	//订单推送接口第二次触发的时候会返回E并提示订单已存在，同时返回SAP订单号
        	if(StringUtils.isNotBlank(result.getSapOrderId()) && StringUtils.isBlank(oh.getSapOrderId())){
        		oh.setSapOrderId(result.getSapOrderId());
        		if(("5").compareTo(oh.getStates())>0){
    				oh.setStates("5");
    			}
    			orderService.updateOrderHeader(oh);
    			msg = "订单已存在于SAP，订单号："+result.getSapOrderId();
    			result.getResult().setTYPE(RFCConstants.SUCCESS_FLAG);
        	}
        }
		return result;
	}
	
	/**
	 * 构造创建订单接口输入参数
	 * @author: chaoyang.ren 
	 * @date:2016年9月28日  上午11:06:47
	 * @param id
	 * @return
	 */
	public InputDTO constructInputParam(Long id){
		OmOrderHeadersAll order = orderService.getOrderBYid(id);
		return constructInputParam(order);
	}
	
	public String constructParam(Serializable id){
		InputDTO inputDto = constructInputParam(Long.valueOf(id.toString()));
		return JsonUtil.toJSON(inputDto);
	}
	
	/**
	 * 构造创建订单接口输入参数
	 * @author: chaoyang.ren 
	 * @date:2016年10月12日  下午2:03:57
	 * @param id
	 * @param returnFlag 是否退货订单
	 * @return
	 * @deprecated Use {@link #constructInputParam(Long)} instead
	 */
	@Transactional(readOnly=true)
	@Deprecated
	public InputDTO constructInputParam(Long id, boolean returnFlag){
		OmOrderHeadersAll order = orderService.getOrderBYid(id);
		return constructInputParam(order);
	}
	
	@Transactional(readOnly=true)
	public InputDTO constructInputParam(OmOrderHeadersAll order){
		String orderType = order.getOrderType();
		boolean returnFlag = "3".equals(orderType);//是否退货订单
		InputDTO input = new InputDTO();
		OrderHeader header = input.new OrderHeader();
		
		//退货订单获取源订单信息
		if(returnFlag){
			OmOrderHeadersAll originOrder = orderService.getOrderBYid(Long.parseLong(order.getAttribute13()));
			//构建参数时把退货订单类型设置为源订单的类型以确保参数对应
			orderType = originOrder.getOrderType();
			//退货原因（原因代码，对应描述在字典表中）
			header.setBusiReason(order.getAttribute6());
		}
		header.setOperationType(RFCConstants.OPERATION_TYPE_ADD);
		header.setSalesDocumentCRM(order.getId().toString());
		header.setSalesDocumentSAP(null);
		
		CrmSalesOrganization org = organizationService.findById(order.getOrganizationId());
		if(org == null){
			return null;
		}
		String orgId = org.getId();
		String rootOrgId = orgId.substring(0, 3);
		/*
		 * 调味品ZT01;品种盐ZS01
		 */
		String key = rootOrgId+(returnFlag?1:0);
		if(RFCConstants.DOCUMENT_TYPE_MAPPING.containsKey(key)){
			header.setSalesDocumentType(RFCConstants.DOCUMENT_TYPE_MAPPING.get(key));
		}else{
			header.setSalesDocumentType(RFCConstants.DOCUMENT_TYPE_MAPPING.get(returnFlag?"1":"0"));
		}
		String sapOrgId = org.getSapId();
		header.setSalesOrg(sapOrgId);
		CMerchCustBase saleTo = customerService.findCustBaseById(order.getMerchCustId());
		/*
		 * 分销渠道处理
		 * 10分销,30跨公司
		 * modified at :20170527，新的销售渠道对应关系
		 */
		if("3".equals(order.getSource())){
			header.setChannel("81");//所有哈哈厨房源的订单渠道均为81
		}else{
			boolean is1420or1421 = "1420".equals(sapOrgId) || "1421".equals(sapOrgId);
			if("T03".equals(rootOrgId) && !is1420or1421){
				header.setChannel("83");//盐业销售中心一部
			}else if("T04".equals(rootOrgId) && !is1420or1421){
				header.setChannel("84");//盐业销售中心二部
			}else if("T05".equals(rootOrgId) && !is1420or1421){
				header.setChannel("85");//盐业销售中心三部
			}else if("T06".equals(rootOrgId) && !is1420or1421){
				header.setChannel("80");//特通
			}else{
				header.setChannel("10");//其他
			}
		}
		//产品组
		header.setProductGroup(RFCConstants.GROUP_MAPPING.get(rootOrgId));
		//不要票零售客户处理
		boolean isRetail = saleTo.getCustType().equals("5");
		boolean isNoNeedInvoice = "1".equals(saleTo.getIsInvoice());
		if(isRetail && isNoNeedInvoice){
			header.setSaleTo("Y004");//SAP的客户编号
			header.setSaleToName(saleTo.getId()+"&"+saleTo.getName());
		}else{
			header.setSaleTo(saleTo.getSapCustomerId());//SAP的客户编号
		}
		
		//设置送达方及合同
		CMerchCustBase sendTo = null;
		CMerchCustContract contract = null;
		if(order.getShipId() == null){//没有送达方时，默认售达方即为送达方
			sendTo = saleTo;
		}
		
		//不同订单类型的设置
		boolean isSpecialRetailOrder = false;
		if("7".equals(orderType)){
			CMerchCustDistribution shipToDis = distributionMapper.selectByPrimaryKey(order.getShipId());
			Long merchCustId = shipToDis.getMerchCustId();
			sendTo = saleTo;
			String address = shipToDis.getAddress();
			header.setText1(address);
			CMerchCustBase shipToCustBase = customerService.findSimpleCustBaseById(merchCustId);
			String codeAndName = "";
			if(shipToCustBase!=null){
				codeAndName = (shipToCustBase.getSapCustomerId()==null?"":shipToCustBase.getSapCustomerId())+"("+shipToCustBase.getName()+")";
			}
			header.setText3(codeAndName);
			header.setUnloadingPosition(shipToDis.getSite());
			contract = getContract(merchCustId, orgId);
			//联系人、联系电话
			header.setContact(shipToDis.getContacter());
			header.setContactTel(shipToDis.getMobile());
		}else if("9".equals(orderType)){
			sendTo = customerService.findCustBaseById(order.getShipId());
			String address = sendTo.getAddress();
			header.setText1(address);
			String codeAndName = sendTo.getName();
			header.setText3(codeAndName);
			contract = getContract(saleTo.getId(), orgId);
			//联系人、联系电话
			header.setContact(sendTo.getContactName());
			header.setContactTel(sendTo.getTel());
		}
		//仓储服务商零售订单标记
		else if("6".equals(orderType)){
			header.setText2(RFCConstants.X_FLAG);
			//仓储服务商零售订单售达方送达方均为零售商
			sendTo = saleTo;
			header.setText1(sendTo.getAddress());
			contract = getContract(saleTo.getId(), orgId);
			
			//合作仓储服务商零售订单，当对应的合作盐业公司sap编码为1421时，设置销售组织为1420
			CMerchCustBase ship = customerService.findCustBaseById(order.getShipId());
			if(ship != null && "70".equals(ship.getCustType())){
				Long shipPid = ship.getPid();
				if(shipPid != null){
					CMerchCustBase shipP = customerService.findCustBaseById(shipPid);
					if(shipP != null && "1421".equals(shipP.getSapCustomerId())){
						header.setSalesOrg("1420");
						isSpecialRetailOrder = true;
					}
				}
			}
		}else{
			sendTo = customerService.findCustBaseById(order.getShipId());
			header.setText1(sendTo.getAddress());
			contract = getContract(saleTo.getId(), orgId);
		}
		
		//联系人、联系电话
		if(StringUtils.isBlank(header.getContact())){
			header.setContact(sendTo.getContactName());
		}
		if(StringUtils.isBlank(header.getContactTel())){
			header.setContactTel(sendTo.getTel());
		}
		
		if("0".equals(orderType) || "4".equals(orderType)){
			header.setPrintFlag(RFCConstants.X_FLAG);//不校仓储服务商
		}else{
			//仓储服务商名称
			if(StringUtils.isBlank(header.getText3())){
				String codeAndName = (sendTo.getSapCustomerId()==null?"":sendTo.getSapCustomerId())+"("+sendTo.getName()+")";
				header.setText3(codeAndName);
			}
		}
		
		
		//如果送达方是不要票的零售商，则Y004.
		if("5".equals(sendTo.getCustType()) && "1".equals(sendTo.getIsInvoice())){
			header.setSendTo("Y004");
			header.setSendToName(sendTo.getId()+"&"+sendTo.getName());
		}else{
			if("9".equals(orderType)){
				header.setSendTo(saleTo.getSapCustomerId());
			}else{
				header.setSendTo(sendTo.getSapCustomerId());
			}
			
		}
		if(sendTo.getDistributions() != null && sendTo.getDistributions().size() > 0){
			header.setUnloadingPosition(sendTo.getDistributions().get(0).getSite());
		}
		
		//定价时间
		SimpleDateFormat sdf = new SimpleDateFormat(RFCConstants.SDF_PATTERN);
		header.setPriceDate(sdf.format(order.getCreateTs()));
		/*
		 * TODO 装运类型为必输字段，当前没有暂定为汽车
		 * 传编码 :                                  
		 * 00 自提
		 * 01 汽车运输
		 * 02 集装箱运输
		 * 03 火车运输
		 * 04 船运
		 */
		header.setShippingType("01");
		header.setSalesDep(null);
		header.setSalesArea(null);
		header.setSalesGroup(null);
		header.setPriceGroup(null);
		header.setPositionId(order.getStationId() == null?StringUtils.EMPTY:order.getStationId().toString());
		
		//crm 的order-split表对应sap行项目
		List<OmOrderSpilts> orderSplits = orderService.findOrderSplitsByOrderId(order.getId());
		List<Item> items = new ArrayList<Item>();
		//此处行项目定价条件与行项目一一对应 ---//changed at 2017-10-12 添加运费价格条款，与行项目对应变为多对一
		List<ItemPriceCondition> itemConditions = new ArrayList<InputDTO.ItemPriceCondition>();
		for (OmOrderSpilts os : orderSplits) {
			Item item = input.new Item();
			item.setSalesItemNo(os.getOrderitemSapNo());
			String materialId = os.getMaterialId();
			item.setMaterialNo(materialId);
			if("10".equals(orderType)){//KA订单有实际发货数量
				item.setOrderNum(os.getDeliveredNum());
			}else{
				item.setOrderNum(os.getNum().toString());
			}
			TMaterialBase mb = materialMapper.selectByPrimaryKey(materialId);
			item.setOrderUnit(mb.getUnit());
			TMaterialFactoryExample ex = new TMaterialFactoryExample();
			ex.createCriteria().andMaterialIdEqualTo(materialId);
			//工厂&库存地点
			boolean hasFactory = true;
			if(StringUtils.isNotBlank(os.getAttribute2())){
				item.setFactory(os.getAttribute2());
			}else{
				hasFactory = false;
			}
			boolean isSpecial = orderType.equals("4");
			//如果没有工厂，从合同中获取
			if(!isSpecial && !hasFactory && contract != null){
				item.setFactory(contract.getFactoryId());
			}
			// 合作盐业公司sap1421 对应的零售订单，发货工厂为1421
			if(isSpecialRetailOrder){
              item.setFactory("1421");
            }
			//如果不是特殊订单，需要传送库位信息 TODO 后续特殊订单也会传输库位
			if(!isSpecial){
				//仓储服务商零售订单
				if("6".equals(orderType)){
					item.setVwLocation("Z903");
				}else if("7".equals(orderType)){
					item.setVwLocation(order.getRdcCode());
				}else{
					if(StringUtils.isNotBlank(order.getRdcCode())){
						item.setVwLocation(order.getRdcCode());
					}else{
						if(contract != null){
							item.setVwLocation(contract.getVirtualWarehouse());
						}
					}
				}
			}
			item.setShippingPosition(null);
			item.setMemo(null);
			//退货时
			if(returnFlag){
				item.setGiftFlag("1".equals(os.getType())?"REN":"ZRN1");
			}
			//正常订单时
			else{
				item.setGiftFlag("1".equals(os.getType())?"ZTAN":"ZTN1");
			}
			items.add(item);
			
			/*
			 * 价格信息
			 * ZPR1: 单价
			 */
			ItemPriceCondition priceCondition = input.new ItemPriceCondition();
			priceCondition.setSalesItemNo(item.getSalesItemNo());
			String price = os.getPrice() == null?"0":os.getPrice().abs().toString();
			priceCondition.setConditionType("ZPR1");
			priceCondition.setPrice(price);
			priceCondition.setCurrency("CNY");
			priceCondition.setUnit(null);
			priceCondition.setPriceUnit("100");
			itemConditions.add(priceCondition);
			/*
			 * 运费信息
			 * ZFB6: 运费
			 */
			String freight = os.getFreight() == null?"0":os.getFreight().abs().toString();
			ItemPriceCondition freightCondition = input.new ItemPriceCondition();
			freightCondition.setSalesItemNo(item.getSalesItemNo());
			freightCondition.setConditionType("ZFB6");
			freightCondition.setPrice(freight);
			freightCondition.setCurrency("CNY");
			freightCondition.setUnit(null);
			freightCondition.setPriceUnit("100");
			itemConditions.add(freightCondition);
		}
		input.setOrderHeader(header);
		input.setItems(items);
		input.setItemConditions(itemConditions);
		return input;
	}
	
	/**
	 * 构造取消订单接口输入参数
	 * @author: chaoyang.ren 
	 * @date:2016年10月8日  上午10:02:33
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public InputDTO constructInputParamForDel(Long id){
		InputDTO input = new InputDTO();
		OmOrderHeadersAll order = orderService.getOrderBYid(id);
		OrderHeader header = input.new OrderHeader();
		header.setOperationType(RFCConstants.OPERATION_TYPE_DEL);
		header.setSalesDocumentCRM(id.toString());
		header.setSalesDocumentSAP(order.getSapOrderId());
		input.setOrderHeader(header);
		return input;
	}
	
	/**
	 * 获取最新生效的合同
	 * @author: chaoyang.ren 
	 * @date:Jan 9, 2017  3:51:57 PM
	 * @param custId
	 * @param orgId
	 * @return
	 */
	private CMerchCustContract getContract(Long custId, String orgId){
		Long merchidWithContract = orderService.distriButeMerchid(custId);
		List<CMerchCustContract> contracts = contractService.findByCustomerAndOrgId(merchidWithContract, orgId);
		return contracts==null || contracts.isEmpty()?null:contracts.get(0);
	}

	@Override
	public String constructParam(Object obj) {
		Assert.isTrue(obj instanceof OmOrderHeadersAll);
		OmOrderHeadersAll order = (OmOrderHeadersAll) obj;
		InputDTO inputDto = constructInputParam(order);
		return JsonUtil.toJSON(inputDto);
	}
}
