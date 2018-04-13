package com.hhnz.account.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.account.model.CMerchCustAccountV;
import com.hhnz.account.service.IAccountService;
import com.hhnz.account.service.ICustPeriodService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.service.OrderService;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.CurrencyUtil;
import com.hhnz.util.DateUtil;

/**
 * Created by yang on 2016-7-6.
 */
@Controller
@RequestMapping("/account")
public class AccountController {
		
	@Resource
	private  IAccountService service;
	@Resource
	private IorganizationService orgservice;
	@Autowired
    private ICustPeriodService custPeriodService;
	@Resource
	private IPowerOrgService  powerService;
	@Resource
	private OrderService  orderService;
	
	
	
	
    @RequestMapping("/index.html")
    public String indexPage()throws Exception{
        return "account/index";
    }

    @RequestMapping("/adjust.html")
    public String adjust()throws Exception{
        return "account/adjustAccount";
    }
    
    @RequestMapping("/month.html")
    public String month()throws Exception{
        return "account/month";
    }
    
    @RequestMapping("/statement.html")
    public ModelAndView statement(Long custId, String orgId, String period, String custName) {
    	ModelAndView mv = new ModelAndView();
    	Date date = DateUtil.getLastDayOfMonth(period);
    	//客户保证金余额
    	CMerchCustBalances balance = custPeriodService.getMarginBalance(custId, orgId, period);
    	
    	//调拨单
    	List<OmOrderHeadersAll> transferOrders = orderService.listTransferOrder(custId, period);
    	BigDecimal totalFinished = BigDecimal.ZERO;
    	BigDecimal totalProcessing = BigDecimal.ZERO;
    	if(transferOrders != null && !transferOrders.isEmpty()){
    		for (OmOrderHeadersAll order : transferOrders) {
    			if("8".equals(order.getStates())){
    				totalFinished = totalFinished.add(order.getOrderAmt());
    			}else{
    				totalProcessing = totalProcessing.add(order.getOrderAmt());
    			}
    		}
    	}
    	BigDecimal totalRetail = BigDecimal.ZERO;
    	//仓储服务商零售订单
    	List<OmOrderHeadersAll> retailerOrders = orderService.listRetailerOrder(custId, period);
    	if(retailerOrders != null && !retailerOrders.isEmpty()){
    		for (OmOrderHeadersAll order : retailerOrders) {
    			totalRetail = totalRetail.add(order.getOrderAmt());
    		}
    	}
//    	BigDecimal opening = balance.getYtd();
    	BigDecimal ending = balance.getPtd();
    	BigDecimal finished = totalFinished.subtract(totalRetail);
    	BigDecimal marginLeft = ending.subtract(finished);
    	mv.setViewName("account/statement");
    	mv.addObject("custName", custName);
    	int month = DateUtil.getMonth(date)+1;
    	String periodStr = null;
    	if(month == 1){
    		periodStr = "1";
    	}else{
    		periodStr = "1-"+month;
    	}
    	mv.addObject("lastDay", DateUtil.format(date, DateUtil.YYYYMMDD_CHN));
    	mv.addObject("current", DateUtil.format(new Date(), DateUtil.YYYYMMDD_CHN));
		mv.addObject("margin", CurrencyUtil.formatWithCommas(BigDecimalASME.divide(ending)));
    	mv.addObject("marginCN", CurrencyUtil.numberToCN(BigDecimalASME.divide(ending)));
    	mv.addObject("finished", CurrencyUtil.formatWithCommas(BigDecimalASME.divide(finished)));
    	mv.addObject("marginLeft", CurrencyUtil.formatWithCommas(BigDecimalASME.divide(marginLeft)));
    	mv.addObject("processing", CurrencyUtil.formatWithCommas(BigDecimalASME.divide(totalProcessing)));
    	mv.addObject("periodStr", periodStr);
    	mv.addObject("operator", ApplicationContextUtil.getCurrentUser());
    	return mv;
    }
    
    @RequestMapping("/custrebate.html")
    public String custrebate() throws Exception{
    	return "account/custRebate";
    }
    
    @RequestMapping("/custRebateDetails.html")
    public ModelAndView custRebateDetails(String id) throws Exception{
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("account/custRebateDetails");
    	mv.addObject("id", id);
    	return mv;
    }
    
    @RequestMapping("/custperiodexport.html")
    public String custperiodexport() throws Exception{
    	return "account/custPeriodExport";
    }
    
    /**
     * 配送商资金明细页面
     * 配送商资金明细为零售商资金情况
     * @param merchCustId
     * @return
     * @throws Exception
     */
    @RequestMapping("accountDetails.html")
    @ResponseBody
    public ModelAndView  accountDetails(Long merchCustId) throws Exception{
    	ModelAndView mv = new ModelAndView("account/accountDetails");
    	CMerchCustAccountV account = this.service.getAccountByMerchId(merchCustId);
    	mv.addObject("account", account);
    	return mv;
    }
    
    /**
     * 配送商资金明细list数据（零售商资金list）
     * @param bean
     * @param pid
     * @return
     * @throws Exception
     */
    @RequestMapping("accountDetails")
    @ResponseBody
    public AjaxDTO  accountDetailsList(@ModelAttribute AjaxDTO bean,Long pid) throws Exception{
    	bean = this.service.getAccountDetailsList(bean,pid);
    	return bean;
    }
    /**
     * 查询客户账户余额
     * 通过岗位查询客户余额
     * 如果是非部门负责且为销售人员，只可以查看当前岗位的客户余额。部门负责人可以查看所属下级的
     * @param bean
     * @param custname
     * @param orgid
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("merchaccount")
    @ResponseBody
    public AjaxDTO getMerchAccount(@ModelAttribute AjaxDTO bean,String custname,String orgid, String custType, HttpServletRequest request) throws Exception{
    	AjaxDTO dto = new AjaxDTO();
    	Map<String,Object> params = new HashMap<String, Object>();
    	TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);//用户信息
    	List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);//用户职位信息
    	UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);//用户当前岗位
    	List<Long> stationids =  this.powerService.getUserStations(user, jobs, station);//获取用户可以查看的岗位
    	if(!StringUtils.isEmpty(custname)&&!custname.equals("null")){    		
    		custname = URLDecoder.decode(custname,"utf-8");
    	}
    	params.put("orgid", StringUtils.isEmpty(orgid)?null:orgid);
    	params.put("stationid", stationids);
    	params.put("custname", StringUtils.isEmpty(custname)?null:custname);
    	params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit()+bean.getOffset());
		params.put("user", user);
		if(StringUtils.hasLength(custType)){
		  params.put("custTypeList", Arrays.asList(custType.split(",")));
		}
		if("1".equals(user.getUserType())){
			dto =this.service.getAccountDetailsList(bean,user.getMerchId());
		}else{			
			dto = this.service.getMerchAccount(params);
		}
    	return dto;
    }
    
    @ResponseBody
    @RequestMapping("/generate")
    public String generateAccountExcel(@ModelAttribute AjaxDTO bean,String custname,String orgid, String custType, HttpServletRequest request) throws Exception{
        Map<String,Object> params = new HashMap<String, Object>();
        TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);//用户信息
        @SuppressWarnings("unchecked")
        List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);//用户职位信息
        UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);//用户当前岗位
        List<Long> stationids =  this.powerService.getUserStations(user, jobs, station);//获取用户可以查看的岗位
        params.put("stationid", stationids);
        return service.generateAccountExcel(params);
    }
   
}
