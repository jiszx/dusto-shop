package com.hhnz.order.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import com.google.gson.reflect.TypeToken;
import com.hhnz.account.model.CMerchCustAccountV;
import com.hhnz.account.service.IAccountService;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TFactoryV;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IFactoryService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.mapper.CMerchCustDistributionMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.dto.ResponseResult;
import com.hhnz.jco.business.order.InputDTO;
import com.hhnz.jco.business.order.OrderRFC;
import com.hhnz.jco.business.order.ResultDTO;
import com.hhnz.order.dto.OmOrderAddDTO;
import com.hhnz.order.dto.OrderLogisticsDTO;
import com.hhnz.order.dto.OrderMakesureDto;
import com.hhnz.order.enu.OrderDeliveryType;
import com.hhnz.order.model.Invoice;
import com.hhnz.order.model.OmOrderDeliveredV;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderHeadersAllV;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLogistics;
import com.hhnz.order.model.OmOrderMakeSure;
import com.hhnz.order.model.OrderDetail;
import com.hhnz.order.model.OrderLinesDetials;
import com.hhnz.order.model.OrderSearchModel;
import com.hhnz.order.service.OrderBackService;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.order.validator.OrderValidator;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.ExcelExport;
import com.hhnz.util.Files;
import com.hhnz.util.JsonUtil;
import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;

/**
 * Created by yang on 2016-7-5.
 */
@Controller
@RequestMapping("/order/")
public class OrderController {

	@Autowired
	private IProcessService processService;

	@Autowired
	private TaskService taskService;

	@Resource
	private OrderUtilService service;

	@Resource
	private OrderService orderservice;

	@Resource
	private OrderUtilService utlservice;

	@Resource
	private OrderValidator validator;

	@Resource
	private IPowerOrgService powerService;

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IAttachmentService attaService;

	@Resource
	private OrderRFC orderRFC;
	@Resource
	private CustomerContractService contractService;
	@Resource
	private IFactoryService factoryService;
	//@Resource
	//private CMerchCustDistributionMapper distributionMapper;

	@Resource
	private ICustomerService custbaseservice;

	@Resource
	private OrderBackService backservice;

	@Resource
	private IAccountService accountservice;
	
	@Resource
	private IorganizationService orgservice;
	
	@Value("${upload.file.path}")
	private String basePath;

	@RequestMapping(value = "/distributor", method = RequestMethod.GET)
	public String distributorOrder() {
		return "order/distributorOrder";
	}

	@RequestMapping("index.html")
	public String indexPage() throws Exception {
		return "order/order2";
	}

	@RequestMapping("startOrder")
	public String startOrder() throws Exception {
		return "order/startOrder";
	}

	@RequestMapping("specialstartOrder.html")
	public String specialstartOrder() throws Exception {
		return "order/specialstartOrder";
	}

	@RequestMapping("/addKaOrder.html")
	public String kaStartOrderPage() throws Exception {
		return "order/addKaOrder";
	}

	@RequestMapping("specialOrder.html")
	public String specialOrder() throws Exception {
		return "order/specialOrder";
	}

	@RequestMapping("/invoice")
	public String orderInvoice() {
		return "order/invoice";
	}

	@RequestMapping("orderDefaultRdc")
	public String orderDefaultRdc() {
		return "order/orderDefaultRdc";
	}

	/**
	 * 调拨单收货确认功能
	 * 
	 * @return
	 */
	@RequestMapping("orderMakeSureList.html")
	public String orderMakeSure() {
		return "order/orderMakeSureList";
	}

	/**
	 * 调拨单确认收货List
	 * 
	 * @param bean
	 * @param custname
	 * @param orderId
	 * @param sapOrderId
	 * @return
	 */
	@RequestMapping("orderMakeSurelist")
	@ResponseBody
	public AjaxDTO orderMakeSurelist(@ModelAttribute AjaxDTO bean,
			String custname, Long orderId, String sapOrderId) {
		return orderservice.selectMakeSureList(bean, custname, orderId,
				sapOrderId);
	}
	
	@RequestMapping("mkexportOrder")
	public void mkexportOrder(@ModelAttribute AjaxDTO bean, String custname,
			Long orderId, String sapOrderId, HttpServletRequest request,
			HttpServletResponse response) throws IllegalArgumentException,
			IllegalAccessException, IOException {
		List<OmOrderMakeSure> orderList = orderservice.selectMakeSureListall(
				bean, custname, orderId, sapOrderId);

		String excelName = "确认收货.xlsx";
		ExcelResult excelResult = ExcelExport.export(excelName, orderList,
				ExcelType.XLSX, 0, response, OmOrderMakeSure.class);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("file", excelName);
		result.put("totalNum", excelResult.getTotalNum());
		result.put("successNum", excelResult.getSuccessNum());
		result.put("failureNum", excelResult.getFailureNum());
		request.setAttribute("result", result);
	}
	/**
	 * 订单审批详情页面
	 * 
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderaudit.html")
	public ModelAndView orderaudit(String taskId) throws Exception {
		ModelAndView mv = new ModelAndView("order/orderaudit");
		Long id = (Long) this.taskService.getVariable(taskId, "key"); // 订单编号
		mv.addObject("taskId", taskId);
		String custname = this.service.getcustnameByorderId(id);
		mv.addObject("custname", custname);
		String custdis = this.service.getcustDisByorderId(id);
		OmOrderHeadersAll order = orderservice.getOrderBYid(id);
		AjaxDTO lines = orderservice.orderLineDetails(id);
		List<TFactoryV> factorys = factoryService.getFactorys(100);
		/*
		 * CMerchCustDistribution dist =
		 * distributionMapper.selectByPrimaryKey(order.getShipId());
		 */
		CMerchCustBase dist = this.custbaseservice.findCustBaseById(order
				.getShipId());
		List<CMerchCustContract> contracts = contractService
				.findByCustomerAndOrgId(
						orderservice.distriButeMerchid(order.getMerchCustId()),
						order.getOrganizationId());
		if (contracts != null && !contracts.isEmpty()
				&& contracts.get(0) != null) {
			mv.addObject("currentFactory", contracts.get(0).getFactoryId());
		}
		if("10".equals(order.getOrderType())){
			List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
			mv.addObject("attachments", attachments);
		}
		mv.addObject("dist", dist);
		mv.addObject("factory", factorys);
		mv.addObject("lines", lines.getRows());
		mv.addObject("orderType", order.getOrderType());
		mv.addObject("custdis", custdis);
		mv.addObject("id", id);
		mv.addObject("remark", order.getRemark());
		return mv;
	}
	
	/**
	 * LKA订单审批时有逾期金额处理页面
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkPeriod.html")
	public ModelAndView  checkPeriod(String taskId) throws Exception{
		ModelAndView mv = new ModelAndView("order/LkaOrderExpect");
		Long id = (Long) this.taskService.getVariable(taskId, "key"); // 订单编号
		mv.addObject("taskId", taskId);
		String custname = this.service.getcustnameByorderId(id);
		mv.addObject("custname", custname);
		String custdis = this.service.getcustDisByorderId(id);
		OmOrderHeadersAll order = orderservice.getOrderBYid(id);
		AjaxDTO lines = orderservice.orderLineDetails(id);
		List<TFactoryV> factorys = factoryService.getFactorys(100);
		CMerchCustBase  merch = this.custbaseservice.findCustBaseById(order.getMerchCustId());
		CMerchCustBase dist = this.custbaseservice.findCustBaseById(order
				.getShipId());
		List<CMerchCustContract> contracts = contractService
				.findByCustomerAndOrgId(
						orderservice.distriButeMerchid(order.getMerchCustId()),
						order.getOrganizationId());
		if (contracts != null && !contracts.isEmpty()
				&& contracts.get(0) != null) {
			mv.addObject("currentFactory", contracts.get(0).getFactoryId());
		}
		if("10".equals(order.getOrderType())){
			List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
			mv.addObject("attachments", attachments);
		}
		mv.addObject("sapCustomerId", merch.getSapCustomerId());
		mv.addObject("dist", dist);
		mv.addObject("factory", factorys);
		mv.addObject("lines", lines.getRows());
		mv.addObject("orderType", order.getOrderType());
		mv.addObject("custdis", custdis);
		mv.addObject("id", id);
		mv.addObject("remark", order.getRemark());
		return mv;
	}
	@RequestMapping("wl.html")
	public String wlPage() throws Exception {
		return "order/wlorder";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("wlexportOrder")
	public void  wlexportOrder(@ModelAttribute AjaxDTO bean, String states, String custname, String bdate,
			String edate, String orderType, Long crmorderid, String saporderid, 
			 HttpServletRequest request,HttpServletResponse response) throws IOException, IllegalArgumentException, IllegalAccessException{
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession()
				.getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		List<Long> stationids = this.powerService.getUserStations(user,
				userjobs, station);// 获取用户可以查看的岗位
		if(StringUtils.isNotEmpty(states)) {
			params.put("states", states.split(","));
		}
		if (StringUtils.isNotEmpty(orderType)) {
			if (orderType.contains(",")) {
				params.put("ordertypes", orderType.split(","));
			} else {
				params.put("ordertype", orderType);
			}
		}
		params.put("stations", stationids);
		params.put("custname", Strings.emptyToNull(custname));
		params.put("bdate", Strings.emptyToNull(bdate));
		params.put("edate", Strings.emptyToNull(edate));
		params.put("crmorderid", crmorderid);
		params.put("saporderid", Strings.emptyToNull(saporderid));
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit());
		
		
		List<OrderSearchModel> orderList = this.orderservice.selectOrderListAll(params);
		String excelName = "物流反馈.xlsx";
		ExcelResult excelResult = ExcelExport.export(excelName, orderList, ExcelType.XLSX, 0, response, OrderSearchModel.class);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("file", excelName);
		result.put("totalNum", excelResult.getTotalNum());
		result.put("successNum", excelResult.getSuccessNum());
		result.put("failureNum", excelResult.getFailureNum());
		request.setAttribute("result", result);
	}

	/**
	 * 订单修改页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderedit.html")
	public ModelAndView orderedit(Long id) throws Exception {
		String page = "order/orderEdit";
		ModelAndView mv = new ModelAndView();
		OmOrderHeadersAll order = this.orderservice.getOrderBYid(id);
		String custname = this.service.getcustnameByorderId(id);
		CMerchCustBase merch = custbaseservice.findCustBaseById(order
				.getMerchCustId());
		if ("5".equals(order.getOrderType())) { // 调拨单
			page = "customerInv/edit";
			//获取附近信息
			List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
			mv.addObject("attachments", attachments);
		} else if ("7".equals(order.getOrderType())) {
			return null;
		} else if ("8".equals(order.getOrderType())) {
			page = "customerInv/specialAllocationEdit";
		} else if ("3".equals(merch.getCustType())) {
			page = "order/kaOrderEdit";
			//获取附近信息
			List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
			mv.addObject("attachments", attachments);
			AjaxDTO billmerchs =this.service.selectBillCustomer(order.getMerchCustId());
			mv.addObject("bills", billmerchs.getRows());
		}
		mv.setViewName(page);
		List<CMerchCustDistribution> oldDist = this.orderservice
				.getMerchDistribution(order.getMerchCustId(),
						order.getOrganizationId());
		List<CMerchCustBase> distr = this.utlservice.getOrderShip(
				order.getMerchCustId(), order.getOrganizationId());
		mv.addObject("custname", custname);
		String custdis = this.service.getcustDisByorderId(id);
		mv.addObject("oldDist", oldDist);
		mv.addObject("custdis", custdis);
		mv.addObject("distr", distr);
		mv.addObject("order", order);
		mv.addObject("id", id);
		mv.addObject("merch", merch);
		return mv;
	}
    
	/**
	 * KA订单保存
	 * @param orderId
	 * @param files
	 * @param endTime
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="kaOrderEditSave",method = RequestMethod.POST)
	public  String kaOrderEditSave(Long orderId,@RequestParam(value="files") MultipartFile[] files,String  endTime,Long shipto,Long billto,String remark,BigDecimal freight,
			HttpServletRequest request) throws Exception{
		TEmployee user =(TEmployee)request.getSession().getAttribute(SessionKey.USER_INFO);
		OmOrderHeadersAll  order = this.orderservice.getOrderBYid(orderId);
		order.setAttribute2(endTime);
		order.setShipId(shipto);
		order.setBillTo(billto);
		order.setRemark(remark);
		order.setFreight(BigDecimalASME.multiply(freight));
		this.orderservice.updateOrderHeader(order);
		/*List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
		for(TAttachment attachment:attachments){
			this.attaService.delAttachent(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(orderId),attachment.getId());
		}*/
		this.attaService.addAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(orderId), String.valueOf(user.getId()), files);
		return "1";
	}

	/**
	 * 特殊订单修改页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("specialorderEdit.html")
	public ModelAndView specialorderEdit(Long id) throws Exception {
		ModelAndView mv = new ModelAndView("order/specialorderEdit");
		OmOrderHeadersAll order = this.orderservice.getOrderBYid(id);
		if ("7".equals(order.getOrderType())) {
			return null;
		}
		String custname = this.service.getcustnameByorderId(id);
		/*
		 * List<CMerchCustDistribution> distr=
		 * this.orderservice.getMerchDistribution
		 * (order.getMerchCustId(),order.getOrganizationId());
		 */
		List<CMerchCustBase> distr = this.utlservice.getOrderShip(
				order.getMerchCustId(), order.getOrganizationId());
		mv.addObject("custname", custname);
		String custdis = this.service.getcustDisByorderId(id);
		mv.addObject("custdis", custdis);
		mv.addObject("distr", distr);
		mv.addObject("order", order);
		mv.addObject("id", id);
		return mv;
	}

	/**
	 * 订单详情页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderdetails.html")
	public ModelAndView orderdetails(Long id, String type, String isCenter) throws Exception {
		OmOrderHeadersAll order = orderservice.getOrderBYid(id);
		ModelAndView mv = new ModelAndView();
		String page = "order/orderdetails";
		if ("1".equals(type)) {// 客户库存调拨单详情
			page = "customerInv/details";
			List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
			mv.addObject("attachments", attachments);
			mv.addObject("isCenter", isCenter);
			String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
			mv.addObject("attachmentBASEURI", baseUrl);
		}
		if("10".equals(order.getOrderType())){
			//获取附近信息
			List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
			mv.addObject("attachments", attachments);
			AjaxDTO billmerchs =this.service.selectBillCustomer(order.getMerchCustId());
			String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
			mv.addObject("attachmentBASEURI", baseUrl);
			mv.addObject("bills", billmerchs.getRows());
			CMerchCustBase merch = custbaseservice.findCustBaseById(order
					.getMerchCustId());
			CMerchCustBase logistics = custbaseservice.findCustBaseById(merch.getPid());
			mv.addObject("logisticsname", logistics.getName());
		}
		mv.setViewName(page);
		String custname = this.service.getcustnameByorderId(id);
		mv.addObject("custname", custname);
		String custdis = this.service.getcustDisByorderId(id);
		AjaxDTO lines = orderservice.orderLineDetails(id);
		List<TFactoryV> factorys = factoryService.getFactorys(100);
		// CMerchCustDistribution dist =
		// distributionMapper.selectByPrimaryKey(order.getShipId());
		CMerchCustBase dist = this.custbaseservice.findCustBaseById(order
				.getShipId());
		List<CMerchCustContract> contracts = contractService
				.findByCustomerAndOrgId(
						orderservice.distriButeMerchid(order.getMerchCustId()),
						order.getOrganizationId());
		if (contracts != null && !contracts.isEmpty()) {
			mv.addObject(
					"factoryName",
					factoryService.getFactoryById(
							contracts.get(0).getFactoryId()).getName());
		}
		// 物流信息
		List<OmOrderDeliveredV> allDeliveryItems = orderservice
				.getOrderDeliveryItemsByOrderId(id);
		Map<String, Date> deliveryNos = new HashMap<String, Date>();
		List<OmOrderLogistics> logisticsList = new ArrayList<OmOrderLogistics>();
		for (OmOrderDeliveredV di : allDeliveryItems) {
			String deliveryNo = di.getDeliveryNo();
			if (!deliveryNos.containsKey(deliveryNo)) {
				deliveryNos.put(deliveryNo, di.getPostTime());
				OmOrderLogistics logistics = orderservice
						.findLogisticsByDeliveryNo(deliveryNo);
				if (logistics != null) {
					logisticsList.add(logistics);
				}
			}
		}
		mv.addObject("dist", dist);
		mv.addObject("order", order);
		mv.addObject("factory", factorys);
		mv.addObject("lines", lines.getRows());
		mv.addObject("orderType", order.getOrderType());
		mv.addObject("custdis", custdis);
		mv.addObject("id", id);
		mv.addObject("logistics", logisticsList);
		return mv;
	}

	/**
	 * 再次购买方法
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderSaveAgain.html")
	public ModelAndView orderSaveAgain(Long id, HttpServletRequest request)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		UserStations station = (UserStations) request.getSession()
				.getAttribute(SessionKey.CURR_STATION);
		// 生成新的订单数据
		OmOrderHeadersAll order = this.orderservice.orderSaveAgain(id,
				user.getId(), station);
		if (order == null) {
			mv.setViewName("order/error");
			return mv;
		}
		String orderpage = null;
		if ("4".equals(order.getOrderType())) { // 特殊订单
			orderpage = "order/specialorderEdit";
		} else if ("5".equals(order.getOrderType())) { // 客户调拨单
			orderpage = "customerInv/edit";
		} else {
			orderpage = "order/orderEdit";
		}
		mv.setViewName(orderpage);
		String custname = this.service.getcustnameByorderId(id);
		/*
		 * List<CMerchCustDistribution> distr=
		 * this.orderservice.getMerchDistribution
		 * (order.getMerchCustId(),order.getOrganizationId());
		 */
		List<CMerchCustBase> distr = this.utlservice.getOrderShip(
				order.getMerchCustId(), order.getOrganizationId());
		mv.addObject("custname", custname);
		String custdis = this.service.getcustDisByorderId(id);
		mv.addObject("custdis", custdis);
		mv.addObject("distr", distr);
		mv.addObject("order", order);
		mv.addObject("id", id);
		return mv;
	}

	/**
	 * 再次购买验证资金
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("saveAginValidateAmt")
	@ResponseBody
	public Map<String, Object> saveAginValidateAmt(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 验证可用余额
		OmOrderHeadersAll order = this.orderservice.getOrderBYid(id);
		CMerchCustAccountV account = this.accountservice
				.getAccountByMerchId(order.getMerchCustId());
		BigDecimal hbamt = order.getHbAmt();
		if (BigDecimalASME.multiply(account.getSubsidyAmt()).compareTo(hbamt) < 0) {
			result.put("type", "500");
			result.put("msg", "货补可用余额不足");
			return result;
		}
		if (account.getCashAmt().add(account.getCreditAmt())
				.compareTo(BigDecimalASME.divide(order.getOrderAmt())) < 0) {
			result.put("type", "500");
			result.put("msg", "现金授信合计可用余额不足");
			return result;
		}
		result.put("type", "200");
		return result;
	}

	@RequestMapping("commitOrder")
	@ResponseBody
	public String commitOrder(Integer key, HttpServletRequest request)
			throws Exception {
		TEmployee emp = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", "1");
		param.put("startUser", emp.getId() + "");
		param.put("groupType", "admin");
		param.put("level", "1");
		param.put("name", "销售订单审批");
		param.put("SKIP", 0);
		param.put("ADD_WLviewPage", "order/appendWL.html");
		param.put("SALE_MAKESUREviewPage", "order/makeSure.html");
		param.put("CHECK_PERIODviewPage", "order/checkPeriod.html");
		param.put("RGGDviewPage", "order/close.html");
		param.put("MAKESURE_SENDviewPage", "order/detail.html");
		param.put("CLOSE_ORDERviewPage", "order/closeOrder.html");
		param.put("viewPage", "order/detail.html");
		String processId = this.processService.startProcess(param, "saleOrder",
				"admin");
		return processId;
	}

	/**
	 * 获取SAP订单反馈信息
	 * 
	 * @author: chaoyang.ren
	 * @date:2016年10月18日 上午10:53:45
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/feedback")
	public @ResponseBody String feedback(Long orderId) {
		OmOrderHeadersAll order = orderservice.getOrderBYid(orderId);
		if (order == null) {
			return StringUtils.EMPTY;
		}
		String processId = order.getAttribute1();
		if (StringUtils.isBlank(processId)) {
			return StringUtils.EMPTY;
		}
		return (String) runtimeService.getVariable(processId, "MSG");
	}

	/**
	 * 手动发送客户至SAP
	 * 
	 * @author: chaoyang.ren
	 * @date:2016年10月18日 上午10:25:50
	 * @param id
	 * @return
	 */
	@RequestMapping("manualSend")
	public @ResponseBody String manualSendSap(Long id) {
		InputDTO input = orderRFC.constructInputParam(id);
		ResultDTO resultDto = orderRFC.execute(input);
		return resultDto.getResult().getTYPE();
	}

	/**
	 * 物流追加
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "appendWL.html", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addLogistics(Long orderId) throws Exception {
		ModelAndView mv = new ModelAndView();
		OmOrderHeadersAllV order = orderservice.getOrderDetailInfoById(orderId);
		OmOrderHeadersAll orderHeader = orderservice.getOrderBYid(orderId);
		String orderType = orderHeader.getOrderType();
		List<OmOrderDeliveredV> allDeliveryItems = null;
		Map<String, Date> deliveryNos = new HashMap<String, Date>();
		List<OmOrderLogistics> logisticsList = new ArrayList<OmOrderLogistics>();
		
		if("5".equals(orderType) || "8".equals(orderType)){
			OmOrderLogistics logistics = orderservice.findLogisticsByDeliveryNo(orderId.toString());
			if (logistics != null) {
				logisticsList.add(logistics);
			}
		}else{
			allDeliveryItems = orderservice.getOrderDeliveryItemsByOrderId(orderId);
			for (OmOrderDeliveredV di : allDeliveryItems) {
				String deliveryNo = di.getDeliveryNo();
				if (!deliveryNos.containsKey(deliveryNo)) {
					deliveryNos.put(deliveryNo, di.getPostTime());
					OmOrderLogistics logistics = orderservice
							.findLogisticsByDeliveryNo(deliveryNo);
					if (logistics != null) {
						logisticsList.add(logistics);
					}
				}
			}
		}
		mv.setViewName("order/appendWL");
		mv.addObject("order", order);
		mv.addObject("orderType", orderType);
		mv.addObject("items", allDeliveryItems);
		mv.addObject("deliveryNos", deliveryNos);
		mv.addObject("logisticsList", logisticsList);
		return mv;
	}

	@RequestMapping(value = "submitWL.html", method = RequestMethod.POST)
	public String submitLogistics(HttpServletRequest request,
			OrderLogisticsDTO orderLogistics, Long orderId) throws Exception {
		// 检查物流对象，保存物流信息
		TEmployee emp = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		List<OmOrderLogistics> logisticsList = orderLogistics
				.getLogisticsList();
		if (logisticsList != null && logisticsList.size() > 0) {
			for (OmOrderLogistics ol : logisticsList) {
				ol.setCreateOid(emp.getId());
			}
			orderservice.save(logisticsList);
		}
		// 设置状态为已发货
		OmOrderHeadersAll order = orderservice.getOrderBYid(orderId);
		order.setStates("7");
		orderservice.updateOrderHeader(order);
		// 处理当前流程
		String pid = order.getAttribute1();
		Execution execution = runtimeService.createExecutionQuery()
				.processInstanceId(pid).activityId("RECV_WLFK").singleResult();
		// 如果当前执行对象为空则表示流程已经处理
		if (execution == null) {
			return "redirect:wl.html";
		} else {
			// 检查SAP发货信息，调用信号
			List<OmOrderDeliveredV> allDeliveryItems = orderservice
					.getOrderDeliveryItemsByOrderId(orderId);
			BigDecimal result = BigDecimal.ZERO;
			for (OmOrderDeliveredV di : allDeliveryItems) {
				String numString = di.getNum();
				BigDecimal num = new BigDecimal(numString);
				result = result.add(num);
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("recvCount", result);
			// 如果存在特殊订单的工厂发货流程，则说明当前为特殊订单的物流追加
			String id = execution.getId();
			this.runtimeService.signal(id, params);
			return "redirect:wl.html";
		}
	}

	/**
	 * 订单确认收货页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("makeSure.html")
	@ResponseBody
	public ModelAndView makeSure(String taskId) throws Exception {
		ModelAndView mv = new ModelAndView();
		Long orderId = (Long) this.taskService.getVariable(taskId, "key"); // 订单编号
		OrderDetail detail = orderservice.orderDelivery(orderId);
		mv.addObject("order", detail);
		mv.addObject("taskId", taskId);
		mv.setViewName("order/makeSure");
		return mv;
	}

	@RequestMapping("allocationMakeSure.html")
	@ResponseBody
	public ModelAndView allocationMakeSure(Long orderId) throws Exception {
		ModelAndView mv = new ModelAndView();
		OrderDetail detail = orderservice.orderDelivery(orderId);
		mv.addObject("order", detail);
		mv.setViewName("order/allocationMakeSure");
		return mv;
	}

	/**
	 * 订单确认收货提交
	 * 
	 * @param makesureDto
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("submitMakeSure.html")
	public String submitmakeSure(OrderMakesureDto makesureDto, String taskId,
			Long orderId, HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		orderservice.updateMakesure(makesureDto.getOrders(), orderId, user);
		this.taskService.complete(taskId, null);
		return "redirect:../order/orderMakeSureList.html";

	}

	/**
	 * 调拨单确认收货确认处理
	 * 
	 * @param makesureDto
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("allocationSubmitMakeSure")
	public String allocationSubmitMakeSure(OrderMakesureDto makesureDto,
			Long orderId, HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		orderservice.allocationSubmitMakeSure(makesureDto.getOrders(), orderId,
				user);
		return "redirect:../order/orderMakeSureList.html";
	}

	@RequestMapping("detail.html")
	@ResponseBody
	public ModelAndView makesureSend(String taskId) throws Exception {
		ModelAndView mv = new ModelAndView();
		Long orderId = (Long) this.taskService.getVariable(taskId, "key"); // 订单编号
		OrderDetail detail = orderservice.orderDelivery(orderId);
		List<Invoice> invoices = utlservice.orderInvoices(orderId);
		mv.addObject("invoice", invoices);
		mv.addObject("order", detail);
		mv.addObject("taskId", taskId);
		mv.setViewName("order/detail");
		return mv;
	}

	@RequestMapping("submitSendProduct.html")
	public String submitSendProduct(OrderMakesureDto makesureDto, String taskId)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		this.taskService.complete(taskId, params);
		return "redirect:../process/forMe.html";
	}

	@ResponseBody
	@RequestMapping(value = "/makesureOrder", method = RequestMethod.POST)
	public int makesureOrder(String orders, String taskId, int isPass) {
		Map<String, Object> param = new HashMap<>();
		param.put("makesure_send", isPass);
		this.taskService.complete(taskId, param);
		return 1;
	}

	@ResponseBody
	@RequestMapping(value = "/editFactory", method = RequestMethod.POST)
	public int editFactory(String orders, String taskId) {
		List<OrderLinesDetials> lines = JsonUtil.fromJSON(orders,
				new TypeToken<List<OrderLinesDetials>>() {
				}.getType());
		int res = orderservice.updateFactory(lines);
		if (res > 0) {
			this.taskService.complete(taskId);
		}
		return res;
	}

	// 订单关闭
	@RequestMapping("/closeOrder.html")
	@ResponseBody
	public ModelAndView orderClose(String taskId) throws Exception {
		ModelAndView mv = new ModelAndView();
		Long orderId = (Long) this.taskService.getVariable(taskId, "key"); // 订单编号
		OrderDetail detail = orderservice.orderDelivery(orderId);
		List<Invoice> invoices = utlservice.orderInvoices(orderId);
		mv.addObject("invoice", invoices);
		mv.addObject("order", detail);
		mv.addObject("taskId", taskId);
		mv.setViewName("order/close");
		return mv;
	}

	@RequestMapping("/submitCloseOrder.html")
	@ResponseBody
	public String submitCloseOrder(OrderMakesureDto makesureDto, String taskId)
			throws Exception {
		Long orderId = (Long) this.taskService.getVariable(taskId, "key"); // 订单编号
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("SKIP", 1);
		// 订单关闭时查询SAP发货数量加入流程
		List<OmOrderDeliveredV> allDeliveryItems = orderservice
				.getOrderDeliveryItemsByOrderId(orderId);
		BigDecimal result = BigDecimal.ZERO;
		for (OmOrderDeliveredV di : allDeliveryItems) {
			String numString = di.getNum();
			BigDecimal num = new BigDecimal(numString);
			result = result.add(num);
		}
		params.put("recvCount", result);
		this.taskService.complete(taskId, params);
		OmOrderHeadersAllV order = orderservice.getOrderDetailInfoById(orderId);
		// 物流反馈触发
		String wlid = runtimeService.createExecutionQuery()
				.processInstanceId(order.getAttribute1()).activityId("RECV_WL")
				.singleResult().getId();
		this.runtimeService.signal(wlid);
		// 取消订单流程信号发送
		String cancelId = runtimeService.createExecutionQuery()
				.processInstanceId(order.getAttribute1())
				.activityId("RECV_CANCLE_ORDER").singleResult().getId();
		this.runtimeService.signal(cancelId);
		return "ok";
	}

	/**
	 * 销售订单详情页面跳转
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("auditdetails.html")
	public ModelAndView indexPage(@RequestParam("id") Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("order/orderdetails");
		// CMerchCustUpAccountV cmu= this.service.findCustUpV(id);
		// DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// mv.addObject("bean",cmu);
		// mv.addObject("createTs",formatter.format(cmu.getCreateTs()));
		return mv;
	}

	/**
	 * 新增销售订单
	 * 
	 * @param order
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addOrder", method = RequestMethod.POST)
	public String addOrder( OmOrderAddDTO order,@RequestParam(required=false,value="files") MultipartFile[] files,
			HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		OmOrderHeadersAll orderh = new OmOrderHeadersAll();
		// 获取用户信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		// 获取岗位信息
		CrmStation stations = this.utlservice.getStationByID(order.getStationid());
		if (stations == null) {
			UserStations sta = (UserStations) request.getSession()
					.getAttribute(SessionKey.CURR_STATION);
			stations = new CrmStation();
			if (sta == null) {// 配送商没有岗位
				stations.setId(0L);
				stations.setOrganizationId(user.getOrganizationId());
			} else {
				stations.setId(sta.getStationid());
				stations.setOrganizationId(sta.getOrgid());
			}
		}

		/*
		 * // 如果岗位定义到行政省则截取到业务省 String stationorgid =
		 * stations.getOrganizationId().length() > 9 ? stations
		 * .getOrganizationId().substring(0, 9) : stations.getOrganizationId();
		 * 
		 * CrmSalesOrganization org = this.orderservice
		 * .findOrderRegin(stationorgid);
		 */
		// 订单行
		List<OmOrderLinesAll> orderlines = new ArrayList<OmOrderLinesAll>();
		//JSONArray jsonArray = JSONArray.fromObject(order.getLines());
		//orderlines = (List) JSONArray.toCollection(jsonArray,OmOrderLinesAll.class);
		orderlines = JsonUtil.fromJSON(order.getLines(),new TypeToken<ArrayList<OmOrderLinesAll>>(){}.getType());

		// 设置头信息
		orderh.setMerchCustId(order.getMerchCustId());
		orderh.setSalesrepId(user.getId());
		orderh.setShipId(order.getShipId());
		orderh.setStationId(stations.getId());
		orderh.setOrganizationId(order.getOrgid());
		orderh.setCreateTs(new Date());
		orderh.setCreateOid(user.getId());
		orderh.setCashAmt(BigDecimalASME.multiply(order.getXjamt()));
		//orderh.setHbAmt(BigDecimalASME.multiply(order.getHbamt()));
		orderh.setCreditAmt(BigDecimalASME.multiply(order.getSxamt()));
		orderh.setRemark(StringUtils.isEmpty(order.getRemark()) ? " " : order.getRemark());
		orderh.setAttribute9(order.getOthersOrderId());// Ka订单对应的KA客户的订单编号
		orderh.setAttribute2(order.getEndTime());//最晚送达时间
		orderh.setOrderType(StringUtils.isEmpty(order.getOrderType()) ? (orderservice
				.isDistributeOrder(order.getMerchCustId()) ? "1" : "0") : order.getOrderType());
		orderh.setBillTo(order.getBillto());//付款方
		orderh.setDeliveryType(order.getDeliveryType());
		//不包邮的情况下需要运费
		orderh.setFreight(OrderDeliveryType.NOMAIL.getCode().equals(order.getDeliveryType())?BigDecimalASME.multiply(order.getFreight()):BigDecimal.ZERO);//运费
		if ("5".equals(order.getOrderType())) {
			// 调拨单添加RDC_CODE
			String rdcCode = this.contractService.getvirtualWarehouse(orderh
					.getMerchCustId());
			orderh.setRdcCode(rdcCode);
		}
		if ("8".equals(order.getOrderType()) || "0".equals(order.getOrderType())) {
			// 特殊调拨单
			orderh.setRdcCode(order.getRdcCode());
			if(!StringUtils.isEmpty(order.getReceiveRDC()) && order.getReceiveRDC()!=null){
				orderh.setAttribute8(order.getReceiveRDC());
			}
		}
		// 设置省区
		orderh.setProviId(stations.getOrganizationId());
		// 设置大区
		CrmSalesOrganization cso= this.orgservice.findById(stations.getOrganizationId());
		orderh.setRegionId(cso.getPid());
		orderh.setStates(order.getStates());
		if (!"4".equals(order.getOrderType()) && !"5".equals(order.getOrderType())) {
			String validate = this.validator.validateOrderAdd(orderh,
					orderlines);
			if (!StringUtils.equals("validated", validate)) {
				result.put("type", "500");
				result.put("code", validate);
				return result.toString();
			}
		}
		String type = this.orderservice.addOrder(orderh, orderlines) == "1" ? "200"
				: "500";
		// 插入附件信息
        if(files != null && files.length > 0){
            attaService.addAttachment(OmOrderHeadersAll.class.getSimpleName(),
            		String.valueOf(orderh.getId()), 
            		String.valueOf(orderh.getCreateOid()), files);
        }
		result.put("type", type);
		result.put("id", orderh.getId());
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "addOrderFiles", method = RequestMethod.POST)
	public String addOrderFiles(@RequestParam(required=false,value="files") MultipartFile[] files, Long headerId, String delAtts, HttpServletRequest request) throws Exception {
		if(StringUtils.isNotBlank(delAtts)){
            String[] delAttArray = delAtts.split(",");
            for (String delAtt : delAttArray) {
                Long attId = null;
                try {
                    attId = Long.valueOf(delAtt);
                } catch (NumberFormatException e) {
                    continue;
                }
                attaService.delAttachent(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(headerId), attId);
            }
        }
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		// 插入附件信息
		if(files != null && files.length > 0){
			attaService.addAttachment(OmOrderHeadersAll.class.getSimpleName(),
					String.valueOf(headerId), 
					String.valueOf(user.getId()), files);
		}
		return "";
	}

	/**
	 * 订单查询
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderlist")
	public @ResponseBody AjaxDTO listPage(@ModelAttribute AjaxDTO bean, String states, String custname, String bdate,
			String edate, String orderType, Long crmorderid, String saporderid, String shipname, String saler,
			String transferOrderId, HttpServletRequest request) {
		Map<String, Object> params = constructParam(bean, states, custname, bdate, edate, orderType, crmorderid,
				saporderid, shipname, saler, transferOrderId, request);
		AjaxDTO dto = this.orderservice.findOrderList(params);
		return dto;
	}

	private Map<String, Object> constructParam(AjaxDTO bean, String states, String custname, String bdate, String edate,
			String orderType, Long crmorderid, String saporderid, String shipname, String saler, String transferOrderId,
			HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession()
				.getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		List<Long> stationids = this.powerService.getUserStations(user,
				userjobs, station);// 获取用户可以查看的岗位
		if(StringUtils.isNotEmpty(states)) {
			params.put("states", StringUtils.equals("0", states) ? "" : states);
		}
		/*
		 * if(!StringUtils.isEmpty(custname)&&!"null".equals(custname)){
		 * custname = URLDecoder.decode(custname,"utf-8"); }
		 */
		if (StringUtils.isNotEmpty(orderType)) {
			if (orderType.contains(",")) {
				params.put("ordertypes", orderType.split(","));
			} else {
				params.put("ordertype", orderType);
			}
		}
		if (user.getMerchId() != null) {
			params.put("shipid", user.getMerchId());
		}
		if (StringUtils.isNotEmpty(shipname)) {
			params.put("shipname", shipname);
		}
		if(StringUtils.isNotEmpty(transferOrderId)) {
			params.put("transferOrderId", transferOrderId);
		}
		params.put("stations", stationids);
		params.put("custname", Strings.emptyToNull(custname));
		params.put("bdate", Strings.emptyToNull(bdate));
		params.put("edate", Strings.emptyToNull(edate));
		params.put("crmorderid", crmorderid);
		params.put("saporderid", Strings.emptyToNull(saporderid));
		params.put("saler", Strings.emptyToNull(saler));
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit());
		return params;
	}
	
	/**
	 * 订单导出
	 * 
	 * @param bean
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping("exportOrder")
	public void exportOrder(@ModelAttribute AjaxDTO bean,
			String states, String custname, String bdate, String edate,
			String orderType, Long crmorderid, String saporderid,
			String shipname, String saler, String transferOrderId, HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalArgumentException, IllegalAccessException
	{
		Map<String, Object> params = constructParam(bean, states, custname, bdate, edate, orderType, crmorderid,
				saporderid, shipname, saler, transferOrderId, request);
		List<OrderSearchModel> orderList = orderservice.findOrderListAll(params);
		String excelName = "订单明细.xlsx";
		ExcelResult excelResult = ExcelExport.export(excelName, orderList, ExcelType.XLSX, 0, response, OrderSearchModel.class);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("file", excelName);
		result.put("totalNum", excelResult.getTotalNum());
		result.put("successNum", excelResult.getSuccessNum());
		result.put("failureNum", excelResult.getFailureNum());
		request.setAttribute("result", result);
		
	}
	
	/*
	 * public List<Long> getstationsByorgid(List<UserJobs> userjobs){ List<Long>
	 * list = new ArrayList<Long>(); if (userjobs != null && userjobs.size() >
	 * 0) { // 登陆人员存在职位则 for (UserJobs userjob : userjobs) { List<UserStations>
	 * stations = new ArrayList<UserStations>(); String orgid = ""; String type
	 * = ""; //获取可以查看的岗位数据 type = userjob.getDataView().substring(3,
	 * 4).equals("1")||StringUtils.equals("1", userjob.getJobType()) ? "1": "2";
	 * orgid = StringUtils.equals("1", type) ? userjob.getOrgid() + "%":
	 * userjob.getOrgid(); stations = this.orderservice.getuserstations(type,
	 * orgid); if (stations != null && stations.size() > 0) { for (UserStations
	 * station : stations) { list.add(station.getStationid()); } } } } return
	 * list; }
	 */

	/**
	 * 销售订单提交审批
	 * 
	 * @param headerid
	 * @param states
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderAudit")
	@ResponseBody
	public String orderAudit(Long headerid, String states, String orderType,
			HttpServletRequest request) throws Exception {
		if ("2".equals(states)) {
			TEmployee emp = (TEmployee) request.getSession().getAttribute(
					SessionKey.USER_INFO);
			try {
				String result = this.orderservice.orderAudit(headerid, states,
							orderType);
				orderservice.startProcess(emp, headerid, orderType);
				return result;
			} catch (Exception e) {
				// 流程出错 更改订单状态为编辑
				orderservice.orderAudit(headerid, "1", orderType);
				throw e;
			}
		}
		return "";
	}

	/**
	 * 删除销售订单
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delorder")
	@ResponseBody
	public String delOrder(String id) throws Exception {
		String result = "200";
		for (String headerid : id.split(",")) {
			int change = orderservice.delOrder(Long.parseLong(headerid));
			if (change < 1) {
				result = "500";
			}
		}
		return result;
	}

	/**
	 * 销售订单修改，删除行
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit/delLine")
	@ResponseBody
	public String editDelLine(Long id) throws Exception {
		return this.orderservice.DeleteLine(id) == 1 ? "200" : "500";
	}

	/**
	 * 销售订单修改增加行
	 * 
	 * @param lines
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit/addline", method = RequestMethod.POST)
	public @ResponseBody String addline(@ModelAttribute OmOrderLinesAll lines,
			HttpServletRequest request) {
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		lines.setCreateTs(new Date());
		lines.setCreateOid(user.getId());
		lines.setStates("1");
		lines.setAmt(BigDecimalASME.multiply(lines.getAmt()));
		lines.setDiscountAmt(BigDecimalASME.multiply(lines.getDiscountAmt()));
		lines.setHbAmt(BigDecimalASME.multiply(lines.getHbAmt()));
		lines.setOrderAmt(BigDecimalASME.multiply(lines.getOrderAmt()));
		lines.setOrderPrice(BigDecimalASME.multiply(lines.getOrderPrice()));
		lines.setPrice(BigDecimalASME.multiply(lines.getPrice()));
		OmOrderHeadersAll header = this.orderservice.getOrderBYid(lines
				.getHeaderId());
		if (!"4".equals(header.getOrderType())
				&& !"1".equals(header.getOrderType())
				&& !"5".equals(header.getOrderType())) {
			String validate = this.validator.validateLine(lines);
			// lines.setHighPrice(BigDecimalASME.multiply(lines.getHighPrice()));
			if (!StringUtils.equals("validated", validate)) {
				JSONObject result = new JSONObject();
				result.put("type", "500");
				result.put("code", validate);
				return result.toString();
			}
		}
		return this.orderservice.editAddLine(lines) == 1 ? "200" : "500";
	}

	/**
	 * 销售订单修改，修改行数据
	 * 
	 * @param lines
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit/editline", method = RequestMethod.POST)
	public @ResponseBody String editline(@ModelAttribute OmOrderLinesAll lines,
			HttpServletRequest request) {
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		lines.setUpdateTs(new Date());
		lines.setUpdateOid(user.getId());
		lines.setAmt(BigDecimalASME.multiply(lines.getAmt()));
		lines.setDiscountAmt(BigDecimalASME.multiply(lines.getDiscountAmt()));
		lines.setHbAmt(BigDecimalASME.multiply(lines.getHbAmt()));
		lines.setOrderAmt(BigDecimalASME.multiply(lines.getOrderAmt()));
		lines.setOrderPrice(BigDecimalASME.multiply(lines.getOrderPrice()));
		lines.setPrice(BigDecimalASME.multiply(lines.getPrice()));
		// lines.setStates("1");
		// 验证订单行数据
		OmOrderHeadersAll header = this.orderservice.getOrderBYid(lines
				.getHeaderId());
		if (!"4".equals(header.getOrderType())
				&& !"5".equals(header.getOrderType())) {
			String validate = this.validator.validateLine(lines);
			// lines.setHighPrice(BigDecimalASME.multiply(lines.getHighPrice()));
			if (!StringUtils.equals("validated", validate)) {
				JSONObject result = new JSONObject();
				result.put("type", "500");
				result.put("code", validate);
				return result.toString();
			}
		}
		return this.orderservice.editEditLine(lines) == 1 ? "200" : "500";
	}

	/**
	 * 获取销售订单金额
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateOrderAmt")
	@ResponseBody
	public String updateOrderAmt(Long id) throws Exception {
		return this.orderservice.updateOrderAmt(id) == 1 ? "200" : "500";
	}

	@RequestMapping("orderLineDetails")
	@ResponseBody
	public AjaxDTO orderLineDetails(Long id) throws Exception {
		return this.orderservice.orderLineDetails(id);
	}

	@ResponseBody
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public ResponseResult orderImport(Long shipid, Long merchCustId,
			Long stationid, String orgid,
			@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		String res = this.attaService.addAttachment(
				OmOrderHeadersAll.class.getSimpleName(), user.getLoginName(),
				file);
		String filePath = Files.standardFolderPath(basePath)
				+ OmOrderHeadersAll.class.getSimpleName() + res;
		ResponseResult resp = orderservice.importOrders(filePath, merchCustId,
				stationid, user);
		String md5 = "";
		if (resp.getErrorCode() == 0) {
			md5 = com.google.common.io.Files.hash(new File(filePath),
					Hashing.md5()).toString();
			resp.setData(md5);
		}
		return resp;
	}

	@RequestMapping("updateHeader")
	@ResponseBody
	public int updateRemark(Long id, String remark, Long shipid, String rdcCode,BigDecimal freight) {
		return this.orderservice.updateRemark(id, remark, shipid, rdcCode,freight);
	}

	@ResponseBody
	@RequestMapping(value = "/markInvoice", method = RequestMethod.POST)
	public int markInvoice(OmOrderHeadersAll order) {
		order.setAttribute6("1");
		orderservice.updateOrderHeader(order);
		return 1;
	}
	
	/**
	 * 删除订单附件
	 * @param id
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delOrderFile")
	@ResponseBody
	public int delOrderFile(Long id,Long orderId) throws Exception{
		return this.attaService.delAttachent(OmOrderHeadersAll.class.getSimpleName(), orderId.toString(), id);
	}
	
}
