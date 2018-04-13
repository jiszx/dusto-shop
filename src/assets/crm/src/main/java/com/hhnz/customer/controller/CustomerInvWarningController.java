package com.hhnz.customer.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.ICustomerInvWarningService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.dto.CustomerBaseDTO;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseV;
import com.hhnz.customer.service.ICustomerInvService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.rmi.db.model.customer.CustInvWarning;
import com.hhnz.rmi.db.model.customer.CustInvWarningItem;
import com.hhnz.rmi.db.model.customer.Customer;
import com.hhnz.rmi.db.model.customer.enu.InvWarningType;
import com.hhnz.rmi.db.model.inventory.CustInventory;
import com.hhnz.rmi.db.util.Pager;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.Constants;

/**
 * @author: chaoyang.ren
 * @date:Mar 16, 2017
 * @time:2:27:19 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Controller
@RequestMapping("/customer/config/inv/warning")
public class CustomerInvWarningController {

	@Resource
    private IPowerOrgService  powerService;
	@Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerInvWarningService customerInvWarningService;
    @Autowired
    private ICustomerInvService customerInvService;

    @RequestMapping("index.html")
    public ModelAndView indexPage() {
    	ModelAndView mv = new ModelAndView("customerInv/invWarningList");
        return mv;
    }
    
    @RequestMapping("list")
    @ResponseBody
    public AjaxDTO list(CustomerBaseDTO customerBasedto, AjaxDTO bean, String type, HttpServletRequest request) {
    	TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);//用户信息
        @SuppressWarnings("unchecked")
        List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);//用户职位信息
        UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);//用户当前岗位
        List<Long> stationids =  this.powerService.getUserStations(user, jobs, station);//获取用户可以查看的岗位
        customerBasedto.setCustType("8");
        customerBasedto.setStatus("3");
        AjaxDTO custs = customerService.findCustBase(customerBasedto, bean, stationids);
        @SuppressWarnings("unchecked")
		List<CMerchCustBaseV> rows = custs.getRows();
		for (CMerchCustBaseV custV : rows) {
			CustInvWarning custWarning = customerInvWarningService.findByCustomerId(custV.getId());
			if(custWarning != null){
				Customer customer = custWarning.getCustomer();
				customer.setDistributions(null);
				custV.setCustWarning(custWarning);
			}
		}
    	return custs;
    }

    @RequestMapping(value="setting", method=RequestMethod.GET)
    public ModelAndView settingPage(Long custId) {
    	ModelAndView mv = new ModelAndView("customerInv/invWarningSetting");
    	CMerchCustBase custBase = customerService.findCustBaseById(custId);
    	mv.addObject("custBase", custBase);
        return mv;
    }
    
    @RequestMapping(value="setting", method=RequestMethod.POST)
    @ResponseBody
    public String setting(Long custInvId, BigDecimal target, InvWarningType warningType) {
    	CustInventory ci = customerInvService.findById(custInvId);
    	CustInvWarning ciw = customerInvWarningService.findByCustomerId(ci.getMerchId());
    	Long current = ApplicationContextUtil.getCurrentUser().getId();
    	if(ciw == null){
    		ciw = new CustInvWarning();
    		ciw.setCustomerId(ci.getMerchId());
    		ciw.setCreateTs(new Date());
    		ciw.setCreateOid(current);
    		ciw.setUpdateTs(new Date());
    		ciw.setUpdateOid(current);
    		ciw.setWarning(true);
    	}else{
    		ciw.setUpdateTs(new Date());
    		ciw.setUpdateOid(current);
    		ciw.setWarning(true);
    	}
    	CustInvWarningItem ciwi = customerInvWarningService.findItemByCustInventory(ci);
    	if(ciwi == null){
    		ciwi = new CustInvWarningItem();
    		ciwi.setActive(true);
    		ciwi.setCreateOid(current);
    		ciwi.setCreateTs(new Date());
    		ciwi.setCustInventory(ci);
    		ciwi.setCustInvWarning(ciw);
    		ciwi.setTarget(target);
    		ciwi.setUpdateOid(current);
    		ciwi.setUpdateTs(new Date());
    		ciwi.setWarningType(warningType);
    	}else{
    		ciwi.setCustInvWarning(ciw);
    		ciwi.setUpdateOid(current);
    		ciwi.setUpdateTs(new Date());
    		ciwi.setWarningType(warningType);
    		ciwi.setTarget(target);
    	}
    	customerInvWarningService.saveItem(ciwi);
    	return Constants.SUCCESS_CODE_STR;
    }

    @RequestMapping("materialList")
    @ResponseBody
    public AjaxDTO materialList(Long custId, Pager<CustInventory> pager, String type, HttpServletRequest request) {
    	Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("EQ_merchId", custId);
        if(StringUtils.isNotBlank(pager.getSearch())){
        	searchParams.put("LIKE_materialId", pager.getSearch());
        }
		pager.setSortName("createTs");
		pager.setSortOrder("desc");
		Pageable pageable = pager.getPageRequest();
		Page<CustInventory> page = customerInvService.findByCondition(searchParams, pageable);
		AjaxDTO ajaxDTO = new AjaxDTO();
		ajaxDTO.setLimit(pager.getLimit());
		ajaxDTO.setOffset(pager.getOffset());
		ajaxDTO.setRows(page.getContent());
		ajaxDTO.setTotal((int)page.getTotalElements());
    	return ajaxDTO;
    }

    @RequestMapping(value="toggle", method=RequestMethod.POST)
    @ResponseBody
    public String toggle(String cids, Integer type) {
    	String[] custIds = cids.split(",");
    	Assert.isTrue(custIds != null && custIds.length>0, "No customer selected!");
    	Assert.isTrue(type == 1 || type == 0, "illegal argument!");
    	Long current = ApplicationContextUtil.getCurrentUser().getId();
    	List<CustInvWarning> custInvWarningList = new ArrayList<CustInvWarning>();
    	for (String custId : custIds) {
    		CustInvWarning ciw = customerInvWarningService.findByCustomerId(Long.valueOf(custId));
    		if(ciw == null){
    			continue;
    		}
    		//如果变更的预警状态与当前状态一致，则不变更
    		if((type == 1 && ciw.isWarning()) || (type == 0 && !ciw.isWarning())){
    			continue;
    		}
    		ciw.setUpdateTs(new Date());
    		ciw.setUpdateOid(current);
    		ciw.setWarning(type == 1);
    		custInvWarningList.add(ciw);
		}
    	if(!custInvWarningList.isEmpty()){
    		customerInvWarningService.save(custInvWarningList);
    	}
    	return Constants.SUCCESS_CODE_STR;
    }
    
}
