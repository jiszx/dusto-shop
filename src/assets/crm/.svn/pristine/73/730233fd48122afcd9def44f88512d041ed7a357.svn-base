package com.hhnz.order.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IStationService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
/**
 * 销售订单管理工具Controller
 * @author skevin
 *
 */
@Controller
@RequestMapping("order/util")
public class OrderUtilController {
	
	@Resource
	private OrderUtilService service;
	
	@Resource
	private IStationService stationService;
	
	@Resource
	private IPowerOrgService  powerService;
	
	/**
	 * 获取岗位下的客户信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("customer")
	public @ResponseBody AjaxDTO getUserCustomerInfo(HttpServletRequest request, String custType,String  type, String all){
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);// 用户信息
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		AjaxDTO dto = new AjaxDTO();
		List<Long> stationids = new ArrayList<Long>();
		Map<String, Object> param = new HashMap<>();
		if("admin".equals(user.getLoginName()) || user !=null ){	
		//if("admin".equals(user.getLoginName()) || (user !=null && StringUtils.equals("1", user.getIsSalesman()))){	//判断是否是销售人员,非销售人员不可以下单				
			stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		}
		if("1".equals(all) && user!=null && user.getMerchId()!=null){
		  return service.getOwnerMerch(user.getMerchId());
		}
		if("2".equals(type)){ //获取客户销售组织下的客户  不使用station
		  param.put("org", user.getOrganizationId());
		}else if(!"1".equals(type) && (stationids == null || stationids.isEmpty())){//如果岗位为空，则无需查询
			return dto;
		}
		if(StringUtils.isNotEmpty(custType)){
		  param.put("custTypeList", Arrays.asList(custType.split(",")));
		}
		if(StringUtils.equals("1", all)){
		  param.put("all", all);
		}
		param.put("type",type);
		dto = this.service.getUserCustomerInfo(stationids, param);
		return dto;
	}
	
	
	/**
	 * 查询零售商客户信息（
	 * @param request
	 * @param custType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("distributCustomer")
	@ResponseBody
	public AjaxDTO  distributCustomer(HttpServletRequest request,String custType){
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);// 用户信息
		List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);//用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);//用户当前岗位
	    List<Long> stationids =  this.powerService.getUserStations(user, jobs, station);//获取用户可以查看的岗位
	    if(StringUtils.isEmpty(custType)){ // 默认为零售商
	      custType = "5";
	    }
		return this.service.distributCustomer(user,stationids,custType);
	}
	@RequestMapping("customerByStation")
	@ResponseBody
	public AjaxDTO customerByOrgid(HttpServletRequest request) throws Exception{
		AjaxDTO dto = new AjaxDTO();
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);
		dto = this.service.customerByOrgid(station.getStationid());
		return dto;
	}
	/**
	 * 获取客户的到站信息
	 */
	@RequestMapping("customerShip")
	public @ResponseBody AjaxDTO getCustomerShip(Long merchid,String orgid){
		AjaxDTO dto = new AjaxDTO();
		Long hasContractMerch = service.hasContractMerch(merchid);
		dto= this.service.getCustomerShip(hasContractMerch,orgid);
		return dto;
	}
	
	/**
	 * 获取送达方信息
	 * @param merchid
	 * @param orgid
	 * @return
	 */
	@RequestMapping("customerShip2")
	public @ResponseBody AjaxDTO getCustomerShip2(Long merchid,String orgid){
		AjaxDTO dto = new AjaxDTO();
		//Long hasContractMerch = service.hasContractMerch(merchid);
		//dto= this.service.getCustomerShip(hasContractMerch,orgid);
		List<CMerchCustBase> list = this.service.getOrderShip(merchid, orgid);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	/**
	 * 获取客户产品信息
	 * @param orgid
	 * @param merchid
	 * @param type 1 表示要验证库存信息
	 * @return
	 */
	@RequestMapping("custProduct")
	public @ResponseBody AjaxDTO getCustProduct(Long merchid,String orgid,String type,String combination){
		AjaxDTO dto = new AjaxDTO();
		Long hasContractMerch = service.hasContractMerch(merchid);
		dto = this.service.getCustomerProduct(hasContractMerch,orgid,type, null,combination);
		return dto;
	}
	
	/**
	 * 指定RDC获取客户库存数据
	 * @param merchid
	 * @param orgid
	 * @param rdcCode
	 * @param combination
	 * @return
	 */
	@RequestMapping("custProductByRdc")
	public @ResponseBody AjaxDTO getCustProductByRdc(Long merchid,String orgid,String rdcCode,String combination,String type){
		AjaxDTO dto = new AjaxDTO();
		dto = this.service.getCustProductByRdc(merchid,orgid,rdcCode,combination,type);
		return dto;
	}
	/**
	 * 获取零售商下单产品信息
	 * @param orgid
	 * @param merchid
	 * @return
	 */
	@RequestMapping("retailProduct")
	public  @ResponseBody AjaxDTO getRetailProduct(Long merchid,String orgid){
		return this.service.selectRetailProduct(merchid,orgid);
	}
	
	@RequestMapping("kaProduct")
	public @ResponseBody AjaxDTO getkaProduct(Long merchid,String orgid){
		return this.service.selectKaProduct(merchid,orgid);
	}
	/**
	 * 获取客户对应的账户余额信息
	 * @param merchid
	 * @param orgid
	 * @return
	 */
	@RequestMapping("customerAccount")
	@ResponseBody
	public   AjaxDTO getCustomerAccount(Long merchid,String orgid){
		AjaxDTO dto = new AjaxDTO();
		dto= this.service.getCustomerAccount(merchid,orgid);
		return dto;
	}
	/**
	 * 获取订单行物料的销售政策
	 * @param merchid
	 * @param materialid
	 * @return
	 */
	@RequestMapping("orderPolicy")
	@ResponseBody
	public AjaxDTO getOrderPolicy(Long merchid,String materialid,String orgid){
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("merchid", merchid);
		params.put("materialid", materialid);
		params.put("orgid",orgid);
		dto = this.service.getOrderPolicy(params);
		return dto;
	}
	/**
	 * 获取销售搭赠和促销品对应的价格
	 * @param discountMaterialid
	 * @param merchid
	 * @param type
	 * @param orgid
	 * @return
	 */
	@RequestMapping("policydiscount")
	@ResponseBody
	public String getpolicydiscount(String  discountMaterialid,Long merchid,String type,String orgid){
		String policydiscount = this.service.getPolicyDisacoount(discountMaterialid,merchid,type,orgid);
		return policydiscount;
	}
	
	/**
	 * 获取订单行信息
	 * @param headerid
	 * @return
	 */
	@RequestMapping("linedata")
	@ResponseBody
	public AjaxDTO getlinedata(Long headerid){
		AjaxDTO dto = new AjaxDTO();
		dto = this.service.getlinedata(headerid);
		return dto;
	}
	
	/**
	 * 获取订单货补和应收金额
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderAmt")
	@ResponseBody
	public AjaxDTO getOrderAmt(Long id) throws Exception{
		return this.service.getOrderAmt(id);
	}
	
	/**
	 * 获取销售订单模块登陆用户可查看的组织结构
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("orderOrg")
	@ResponseBody
	public AjaxDTO  getOrderOrg(HttpServletRequest request) throws Exception{
		AjaxDTO dto = new AjaxDTO();
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);
		List<CrmSalesOrganization> list = new ArrayList<CrmSalesOrganization>();
		if(userjobs.size() >0 && userjobs !=null){
			list = this.service.getOrderOrg(userjobs);
		}
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	
	/**
	 * 验证客户
	 */
	@RequestMapping("validateNum")
	@ResponseBody
	public  String validateNum(String materialId,String orgid,Long merchCustId,BigDecimal num) throws Exception{
		return this.service.validateNum(materialId,orgid,merchCustId,num)==1?"200":"500";
	}
	
	/**
	 * 验证指定RDC库存数量
	 * @param materialId
	 * @param orgid
	 * @param merchCustId
	 * @param num
	 * @param rdcCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("validateNumByRdc")
	@ResponseBody
	public  String validateNumByRdc(String materialId,String orgid,Long merchCustId,BigDecimal num,String rdcCode) throws Exception{
		return this.service.validateNumByRdc(materialId,orgid,merchCustId,rdcCode,num)==1?"200":"500";
	}
	
	/**
	 * 获取客户库存数量
	 * @param materialId
	 * @param merchCustId
	 * @param orgid
	 * @return
	 */
	@RequestMapping("invnum")
	@ResponseBody
	public BigDecimal getInvNum(String materialId,Long merchCustId,String orgid){
		return this.service.getInvNum(materialId,merchCustId,orgid);
	}
	@RequestMapping("invnumByRdc")
	@ResponseBody
	public BigDecimal getInvNumByRdc(String materialId,Long merchCustId,String orgid,String rdcCode){
		return this.service.getInvNumByRdc(materialId,merchCustId,orgid,rdcCode);
	}
	/**
	 * 获取客户付款方
	 * @param merchId
	 * @return
	 */
	@RequestMapping("billCustomer")
	public @ResponseBody AjaxDTO getBillCustomer(Long merchId) {
		return this.service.selectBillCustomer(merchId);
	}
}
