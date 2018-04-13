package com.hhnz.api.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IHomeService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ApiCustomerService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.dto.ResponseResult;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.Order;
import com.hhnz.order.model.OrderDetail;
import com.hhnz.order.model.OrderLinesDetials;
import com.hhnz.order.model.OrderMaterial;
import com.hhnz.order.model.OrderSearchModel;
import com.hhnz.order.service.ApiOrderService;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.order.service.impl.OrderUtil;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.organization.service.IStationService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;
import com.hhnz.util.JsonUtil;
import com.hhnz.util.ResponseMessage;

@Controller
@RequestMapping("/api/order")
public class ApiOrderController extends BaseController {

  private static final String Syso = null;
  @Resource
  private OrderService orderservice;
  @Resource
  private IHomeService homeService;
  @Resource
  private OrderUtilService utilService;

  @Resource
  private IStationService stationService;
  
  @Resource
  private ApiOrderService apiOrderservice;
  @Resource
  private IPowerOrgService  powerService;
  @Resource
  private IProcessService processService;
  @Autowired
  private ApiCustomerService apiCustomerService;
  @Autowired
  private ICustomerService customerService;
  @Autowired
  private CustomerStockService customerStockService;
  
  //2期，开票
  @ResponseBody
  @RequestMapping(value = "/invoice", method = RequestMethod.GET)
  public ResponseResult issueInvoice(String  orderHeadId,HttpServletRequest request) throws Exception {
	  Map<String,Object> params = new HashMap<String,Object>();

	  Map<String,Object> result = null;
	  if(orderHeadId!=null &&(!"".equals(orderHeadId))){
		  String[] orderHeaderIdList = orderHeadId.split(",");  
		  params.put("orderHeaderIdList", orderHeaderIdList);
		  result = apiOrderservice.issueInvoice(params);
		  if(orderHeaderIdList.length == (int) result.get("row")){
			  result.put("result", "1");//成功了
		  }else{
			  result.put("result","0");//失败了
		  }
	  }else{
		  result = new HashMap<String,Object>();
	  }
	   
	  return ResponseResult.builder().data(result).build();
  }

  //2期     配送商获取  所需的 订单
  @ResponseBody
  @RequestMapping(value = "/userOrderlist", method = RequestMethod.GET)
  public ResponseResult userOrderlist(AjaxDTO bean,String custPID,String  states,String  name,HttpServletRequest request) throws Exception {
    limitVerify(bean);//对分页设置属性
    TEmployee user = loginApiUser(request);//获取用户对象好像用到了accesstoken    
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("begin", bean.getOffset());
    params.put("end", bean.getOffset()+bean.getLimit());
    params.put("custPID", custPID);
    if((name != null) && (!"".equals(name))){
    	 params.put("name", "'%"+name+"%'");
    }
   
    
    String[] stateList = null; //查找所需状态的
    if((states != null) && (!"".equals(states)) ){
    	stateList = states.split(","); 	   	
    }    
    params.put("states",stateList);
    List<OrderSearchModel> data = apiOrderservice.findCustOrderList(params);
    return ResponseResult.builder().data(data).build();
  }
  //2期  零售商的  订单列表
  @ResponseBody
  @RequestMapping(value = "/retailersOrderlist", method = RequestMethod.GET)
  public ResponseResult retailersOrderlist(AjaxDTO bean,String custID,String  states,String date,String name, HttpServletRequest request) throws Exception {
    limitVerify(bean);//对分页设置属性
    TEmployee user = loginApiUser(request);//获取用户对象好像用到了accesstoken    
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("begin", bean.getOffset());
    params.put("end", bean.getOffset()+bean.getLimit());
    params.put("custID", custID);
    if((name != null) && (!"".equals(name))){
   	 params.put("name", "'%"+name+"%'");
   }
    String[] stateList = null; //查找所需状态的
    if((states != null) && (!"".equals(states)) ){
    	stateList = states.split(","); 	   	
    }    
    params.put("states",stateList);
    

    if((date!=null) && (!"".equals(date))){
    	int month = Integer.parseInt(date);
    	if(month>=1 && month <=12){
        	Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);   
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR,year);
            //设置月份
            cal.set(Calendar.MONTH, month-1);
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            params.put("bdate",year+"-"+date+"-01");
            params.put("edate",year+"-"+date+"-"+lastDay);
        }
    }
    
    
    
    List<Map> data = apiOrderservice.findRetailersOrderList(params);
    
    
    return ResponseResult.builder().data(data).build();
  }

  
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ResponseResult listPage(AjaxDTO bean, Long stationId, String states, String custname,
      String bdate, String edate, String keyWord, String organization,
      String area, HttpServletRequest request) throws Exception {
    limitVerify(bean);//对分页设置属性
    TEmployee user = loginApiUser(request);//获取用户对象好像用到了accesstoken
    if (user == null) {//没有token，要求登录，获取token
      return ResponseMessage.tokenExpired();
    }
    Map<String, Object> userInfo = userInfo(request);
    List<UserJobs> userjobs = userJobs(userInfo);
    UserStations userstations = userStation(userInfo, stationId);//用户岗位
    Map<String, Object> params = new HashMap<String, Object>();
    List<Long> stationids =  this.powerService.getUserStations(user, userjobs, userstations);//获取用户可以查看的岗位
    params.put("stations", stationids);
    params.put("begin", bean.getOffset());
    int end = bean.getOffset()+bean.getLimit();
    params.put("end", end);
    params.put("states", states);
    params.put("custname", StringUtils.isEmpty(custname)?null : "'%"+custname+"%'");
    params.put("custname",  StringUtils.isEmpty(keyWord)?null : "'%"+keyWord+"%'");
    params.put("bdate", bdate);
    params.put("edate", edate);
    params.put("organization", organization);
    params.put("area", area);
    // params.put("", value)
    AjaxDTO dto = this.orderservice.findOrderList(params);
    return ResponseResult.builder().data(dto.getRows()).build();
  }

  @ResponseBody
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public ResponseResult detail(Long id) {
    OrderDetail detail = orderservice.orderDetail(id);
    CMerchCustBase ship = detail.getShip();
    if(ship!=null){
      CMerchCustBase tempMerch = new CMerchCustBase();
      tempMerch.setId(ship.getId());
      tempMerch.setName(ship.getName());
      tempMerch.setAbbrName(ship.getAbbrName());
      tempMerch.setAddress(ship.getAddress());
      tempMerch.setContactName(ship.getContactName());
      tempMerch.setContactTel(ship.getContactTel());
      tempMerch.setSapCustomerId(ship.getSapCustomerId());
      detail.setShip(tempMerch);
    }
    return ResponseResult.builder().data(detail).build();
  }

  //crm二期，删除订单
  @ResponseBody
  @RequestMapping(value = "/deleteOrder",method = RequestMethod.GET)
  public ResponseResult deleteOrder(String orderHeadId,HttpServletRequest request)throws Exception{
	  
	  return  ResponseResult.builder().data(orderservice.delOrder(Long.parseLong(orderHeadId))).build();
  }
  
  //crm二期，配送商保存多个订单，也就是配送商帮助零售商下订单
  @ResponseBody
  @RequestMapping(value = "/saveOrder",method = RequestMethod.POST)
  public ResponseResult saveOrder(String orders,Long merchId,HttpServletRequest request)throws Exception{
	  //首先要获取配送商的送达方shipId
	//订单的shipId用配送商的送达方id
//	  Long shipIdAll = orderservice.findShipIdByMerchId(merchId);//之前用的配送商的送达方现在由于需求改动客户也是送达方
	  Long shipIdAll = merchId ;//配送商模式下用配送商来当
	  
	  if((orders!=null)&&(!"".equals(orders))){
		  List<Order> ords = JsonUtil.fromJSON(orders, new TypeToken<List<Order>>() {}.getType());//解析数据
		  for(Order order : ords){
				  
			  //依次对每一个订单处理,订单头的销售组织用下订单的零售商的销售组织id
			  String merch = order.getMerchCustId();//零售商客户id
			  CMerchCustBase custBase = customerService.findCustBaseById(Long.parseLong(merch));
			  order.setOrgid(custBase.getOrganizationId());//订单的销售组织用零售商的销售组织
			  
			  order.setShipId(shipIdAll.toString());
			  
			  
			  OmOrderHeadersAll orderh = new OmOrderHeadersAll();
			    // 获取用户信息
			    TEmployee user = loginApiUser(request);
			    if (user == null) {
			      return ResponseMessage.tokenExpired();
			    }
			    Long stationId = order.getStationId();
//			    CrmStation stations = new CrmStation();
//			    if(stationId==null||"".equals(stationId)){
//			    	 List<UserStations> rows = this.stationService.findUserStations(user.getId());
//			    	 UserStations userstations = new UserStations();
//			    	 if(!rows.isEmpty()){
//			    		 userstations = rows.get(0);
//			         }
//			    	 stations = this.utilService.getStationByID(userstations.getStationid());
//			    	 order.setStationId(userstations.getStationid().toString());
//			    }else{
//			    	stations= this.utilService.getStationByID(Long.parseLong(order.getStationId()));
//			    }
			    
				CrmStation stations = this.utilService.getStationByID(stationId);
				if(stations==null){
				  UserStations sta = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);
				  stations = new CrmStation();
				  if(sta==null){// 配送商没有岗位
				    stations.setId(0L);
				    stations.setOrganizationId(user.getOrganizationId());
				  }else{
				    stations.setId(sta.getStationid());
			        stations.setOrganizationId(sta.getOrgid());
				  }
				}			    
			    
			    if (StringUtils.isEmpty(order.getStates())) {
			      order.setStates("1");
			    }
			    if(StringUtils.isNotEmpty(order.getOldOrderId())){
			      orderservice.delOrder(Long.parseLong(order.getOldOrderId()));
			    }
			    // 订单行
			    List<OmOrderLinesAll> orderlines = order.getLines();

			    orderh.setRemark(order.getRemark());
			    orderh.setMerchCustId(Long.parseLong(order.getMerchCustId()));
			    orderh.setSalesrepId(user.getId());
			    orderh.setShipId(Long.parseLong(order.getShipId()));

			    orderh.setStationId(stations.getId());
			   
			    orderh.setOrganizationId(order.getOrgid());
			    orderh.setCreateTs(new Date());
			    orderh.setCreateOid(user.getId());			    
			    orderh.setCashAmt(BigDecimalASME.multiply(order.getXjamt()));
			    orderh.setHbAmt(BigDecimalASME.multiply(order.getHbamt()));
			    orderh.setCreditAmt(BigDecimalASME.multiply(order.getSxamt()));
			    
			    
			    // 设置省区
			    orderh.setProviId(stations.getOrganizationId());
			    // 设置大区
			    orderh.setRegionId(stations.getOrganizationId().length() > 7
			        ? stations.getOrganizationId().substring(0, 7) : stations.getOrganizationId());
			    orderh.setStates(order.getStates());
			    orderh.setOrderType(OrderUtil.getOrderTypeByUser(user));
			    if( (order.getAttribute1() == null) || ("".equals(order.getAttribute1()))){
			    	orderh.setAttribute1("0");
			    }
			    if( (order.getAttribute2() == null) || ("".equals(order.getAttribute2()))){
			    	orderh.setAttribute2("0");
			    }
			    orderservice.addOrder(orderh, orderlines);
		  }
		  return ResponseResult.builder().data("0").build();
	  }else{
		  return ResponseResult.builder().data("1").build();
	  }
	  
	  
	 
  }
  
  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ResponseResult addOrder(Long merchCustId, Long shipId, Long stationId, String orgid,
      String remark, String states, String lines, BigDecimal xjamt, BigDecimal sxamt, BigDecimal hbamt,
      String oldOrderId,String attribute1,String attribute2, HttpServletRequest request) throws Exception {
    OmOrderHeadersAll orderh = new OmOrderHeadersAll();
    // 获取用户信息
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    CrmStation stations = this.utilService.getStationByID(stationId);

    if (StringUtils.isEmpty(states)) {
      states = "1";
    }
    
    if(StringUtils.isNotEmpty(oldOrderId)){
      orderservice.delOrder(Long.parseLong(oldOrderId));
    }

    /*
     * // 如果岗位定义到行政省则截取到业务省 String stationorgid = stations.getOrganizationId().length() > 9 ?
     * stations .getOrganizationId().substring(0, 9) : stations.getOrganizationId();
     * 
     * CrmSalesOrganization org = this.orderservice .findOrderRegin(stationorgid);
     */
    // 订单行
    List<OmOrderLinesAll> orderlines =
        JsonUtil.fromJSON(lines, new TypeToken<List<OmOrderLinesAll>>() {}.getType());
    
    // 设置头信息
    orderh.setRemark(remark);
    orderh.setMerchCustId(merchCustId);
    orderh.setSalesrepId(user.getId());
    orderh.setShipId(shipId);
    orderh.setStationId(stationId);
    orderh.setOrganizationId(orgid);
    orderh.setCreateTs(new Date());
    orderh.setCreateOid(user.getId());
    orderh.setCashAmt(BigDecimalASME.multiply(xjamt));
    orderh.setHbAmt(BigDecimalASME.multiply(hbamt));
    orderh.setCreditAmt(BigDecimalASME.multiply(sxamt));
    // 设置省区
    orderh.setProviId(stations.getOrganizationId());
    // 设置大区
    orderh.setRegionId(stations.getOrganizationId().length() > 7
        ? stations.getOrganizationId().substring(0, 7) : stations.getOrganizationId());
    orderh.setStates(states);
    orderh.setOrderType(orderservice.isDistributeOrder(merchCustId)?"1":"4");
    if( (attribute1 == null) || ("".equals(attribute1))){
    	orderh.setAttribute1("0");
    }
    if( (attribute2 == null) || ("".equals(attribute2))){
    	orderh.setAttribute2("0");
    }
    CMerchCustBase cust = customerService.findCustBaseById(merchCustId);
    boolean isTransfer = false;
    if(cust!=null && "7".equals(cust.getCustType())){ // 转化为调拨单
      isTransfer = true;
      orderh.setOrderType("5");
    }
    String result = orderservice.addOrder(orderh, orderlines);
    if("2".equals(states)){
      if(isTransfer){
        customerStockService.updateStates(orderh.getId(), "8", user);
      }else{
        orderAudit(orderh.getId(), states, orderh.getOrderType(), request);
      }
    }
    return ResponseResult.builder().data(result).build();
  }
  
  public String orderAudit(Long headerid,String states,String orderType, HttpServletRequest request) throws Exception{
    if("2".equals(states)){
      TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
      try {
          orderservice.startProcess(emp,headerid,orderType);
          return this.orderservice.orderAudit(headerid,states, orderType);
      } catch (Exception e) {
          throw e;
      }
  }
  return "";
}

  // 获取岗位下的客户信息
  // 好像是一个领导 想知道他的所有的客户
  // 然后首先要知道这个领导下有哪些岗位人员，每个岗位人员对应的有自己的客户
  // 所以第一步是知道领导下的所有岗位，然后把各个岗位的客户汇总就是结果
  @ResponseBody
  @RequestMapping(value = "/customer", method = RequestMethod.GET)
  public ResponseResult getUserCustomers(HttpServletRequest request, Long stationId,String name,String areaId,String custType,String status, AjaxDTO page)
      throws Exception {
      //获取客户信息
    limitVerify(page);
    TEmployee user = loginApiUser(request);
    Map<String, Object> userInfo = userInfo(request);
    if (userInfo == null) {
      return ResponseMessage.tokenExpired();
    }
    List<Long> stationids = null;
    if(user!=null && stationId!=null && !stationId.equals(0)){
      UserStations userstations = userStation(userInfo, stationId);//用户岗位
      List<UserJobs> jobs = userJobs(userInfo);
      stationids =  this.powerService.getUserStations(user, jobs, userstations);//获取用户可以查看的岗位
    }
    
    String[] areaList = null;
    Long[] areas = null;
    if((areaId != null) && (! "".equals(areaId))){
        areaList = areaId.split(",");
        areas = new Long[areaList.length];
        for (int i = 0; i < areaList.length; i++) {
            areas[i]=Long.parseLong(areaList[i]);
        }
    }
  //  顾客类型列表
    String[] custTypeList = {"4","7"};
//  if((custType != null) && (! "".equals(custType))){
//     custTypeList = custType.split(",");
//  }
  //  客户姓名
    if((name != null) && (! "".equals(name))){
        name = "'%"+name+"%'" ;
    }else if((name != null) && ( "".equals(name))){
        name = null;
    }
  //  这个是新添加的status也必须是转换成list
     String[] statusList = null;
     if((status != null) && (! "".equals(status))){
         statusList = status.split(",");
     } 
      
    
//    AjaxDTO dto = this.utilService.getUserCustomerInfo(stationids);
    AjaxDTO dto = this.utilService.getUserCustomerInfo(stationids,name,areaList,custTypeList,statusList, page);
    return ResponseResult.builder().data(dto.getRows()).build();
  }

  // 获取客户的送达方信息
  @ResponseBody
  @RequestMapping(value = "/customer/ship", method = RequestMethod.GET)
  public ResponseResult getCustomerShip(Long merchid, Long stationId, HttpServletRequest request)
      throws Exception {
    Map<String, Object> userInfo = userInfo(request);
    if (userInfo == null) {
      return ResponseMessage.tokenExpired();
    }
    UserStations userstation = userStation(userInfo, stationId);
    AjaxDTO dto = this.utilService.getCustomerShip(merchid, userstation.getOrgid());
    return ResponseResult.builder().data(dto.getRows()).build();
  }
  
  @ResponseBody
  @RequestMapping(value = "/customer/ship2", method = RequestMethod.GET)
  public ResponseResult getCustomerShip2(AjaxDTO page, Long merchid, Long stationId, HttpServletRequest request)
      throws Exception {
    Map<String, Object> userInfo = userInfo(request);
    if (userInfo == null) {
      return ResponseMessage.tokenExpired();
    }
    limitVerify(page);
    CMerchCustBase cust = customerService.findCustBaseById(merchid);
    List<CMerchCustBase> list = utilService.getOrderShip(page, merchid, cust.getOrganizationId());
    List<CMerchCustBase> result = new ArrayList<>();
    for(CMerchCustBase merch:list){
      CMerchCustBase tempMerch = new CMerchCustBase();
      tempMerch.setId(merch.getId());
      tempMerch.setName(merch.getName());
      tempMerch.setAbbrName(merch.getAbbrName());
      tempMerch.setAddress(merch.getAddress());
      tempMerch.setContactName(merch.getContactName());
      tempMerch.setContactTel(merch.getContactTel());
      tempMerch.setSapCustomerId(merch.getSapCustomerId());
      result.add(tempMerch);
    }
    return ResponseResult.builder().data(result).build();
  }

  // 获取客户产品信息
  @ResponseBody
  @RequestMapping(value = "/customer/product", method = RequestMethod.GET)
  public ResponseResult getCustProduct(AjaxDTO bean,Long merchid, Long stationId, OrderMaterial mateial,
      HttpServletRequest request) throws Exception {
	limitVerify(bean);//对分页设置属性
    Map<String, Object> userInfo = userInfo(request);
    if (userInfo == null) {
      return ResponseMessage.tokenExpired();
    }
    CMerchCustBase cust = customerService.findCustBaseById(merchid);
    AjaxDTO dto =
        this.utilService.findProductWithCondition(merchid, cust.getOrganizationId(), mateial,bean);
    return ResponseResult.builder().data(dto.getRows()).build();
  }

  // 获取客户对应的账户余额信息
  @ResponseBody
  @RequestMapping(value = "/customer/account", method = RequestMethod.GET)
  public ResponseResult getCustomerAccount(Long merchid, Long stationId, HttpServletRequest request)
      throws Exception {
    Map<String, Object> userInfo = userInfo(request);
    if (userInfo == null) {
      return ResponseMessage.tokenExpired();
    }
    CMerchCustBase merch = customerService.findCustBaseById(merchid);
    AjaxDTO dto = utilService.getCustomerAccount(merchid, merch.getOrganizationId());
    return ResponseResult.builder().data(dto.getRows()).build();
  }

  
  // 获取订单行物料的销售政策
  @ResponseBody
  @RequestMapping(value = "/policy", method = RequestMethod.GET)
  public ResponseResult getOrderPolicy(Long merchid, String materialid, Long stationId,AjaxDTO bean,
      HttpServletRequest request) throws Exception {
	  limitVerify(bean);
    Map<String, Object> userInfo = userInfo(request);
    if (userInfo == null) {
      return ResponseMessage.tokenExpired();
    }
    UserStations userstation = userStation(userInfo, stationId);
    int begin = bean.getOffset(); 
    int end = bean.getOffset()+bean.getLimit();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("merchid", merchid);
    params.put("materialid", materialid);
    params.put("orgid", userstation.getOrgid().substring(0, 5));
    params.put("begin",begin);
    params.put("end", end);
    AjaxDTO dto = this.utilService.getOrderPolicy(params);
    return ResponseResult.builder().data(dto.getRows()).build();
  }
  
  @ResponseBody
  @RequestMapping(value = "/verify/receive", method = RequestMethod.GET)
  public String verifyReceive(Long id) {
    String result = orderservice.orderAudit(id, "8", "");
    return "200".equals(result) ? "1" : "0";
  }

  // 获取销售搭赠和促销品对应的价格
  @ResponseBody
  @RequestMapping(value = "/policy/discount", method = RequestMethod.GET)
  public String getpolicydiscount(String materialid, Long merchid, String type, String orgid) {
    String policydiscount = this.utilService.getPolicyDisacoount(materialid, merchid, type, orgid);
    return policydiscount;
  }

  //删除订单行
  @RequestMapping(value ="/deleteLine",method = RequestMethod.POST)
	public @ResponseBody String delete(Long id) throws Exception{
		return this.orderservice.DeleteLine(id)==1?"0":"1";
	}
	
//添加订单行
	@RequestMapping(value="/addLine",method = RequestMethod.POST)
	public @ResponseBody String add(@ModelAttribute OmOrderLinesAll lines,HttpServletRequest request){
//		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		TEmployee user = loginApiUser(request);
		lines.setCreateTs(new Date());
		lines.setCreateOid(user.getId());
		lines.setStates("1");
		return this.orderservice.editAddLine(lines)==1?"0":"1";
	}
	//修改订单行
	@RequestMapping(value="/editLine",method=RequestMethod.POST)
	public @ResponseBody String edit(@ModelAttribute OmOrderLinesAll lines,HttpServletRequest request){
//		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		 TEmployee user = loginApiUser(request);

		lines.setUpdateTs(new Date());
		lines.setUpdateOid(user.getId());
		//lines.setStates("1");
		return this.orderservice.editEditLine(lines)==1?"0":"1";
	}
	
	//根据订单的id，找到这个订单的所有的货物的发送情况，也就是这个订单有什么货物，这些货物的发送情况
	@RequestMapping(value="/orderDeliveryDetail",method=RequestMethod.POST)
	public @ResponseBody List<OrderLinesDetials> orderDeliveryDetails(String orderId){
		
		List<OrderLinesDetials> orderLineDetialList = this.orderservice.findOrderDeliveryDetails(orderId);
		
		return orderLineDetialList;
	}
	
	//确认收货
	@RequestMapping(value="/receiveGoods",method=RequestMethod.POST)
	public @ResponseBody int receiveGoods(String data){
		
		List<OrderLinesDetials> lines = JsonUtil.fromJSON(data, new TypeToken<List<OrderLinesDetials>>() {}.getType());

		OrderLinesDetials[] linesArray = new OrderLinesDetials[lines.size()];//由于无法直接用(OrderLinesDetials[])(list.toArray)转换所以选择一个个地转换
		
		for (int i = 0; i < linesArray.length; i++) {
			linesArray[i] = lines.get(i);
		}
		int rows = this.orderservice.receiveGoods(linesArray);
		return rows==linesArray.length?1:0;//确实改动了所有的数据才返回1否则返回0
	
	}
	
}
