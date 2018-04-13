package com.hhnz.customer.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.customer.service.ICustomerChangeOrgService;

@Controller
@RequestMapping("/customer/")
public class CustomerChangeOrgController {
	
	 @Resource
	 private  ICustomerChangeOrgService service;
	 /**
     * 变更客户销售组织
     * @param merchId
     * @param newOrgId
     * @return
     */
    @RequestMapping(value="/changeOrg",method=RequestMethod.POST)
    @ResponseBody
    public String changeOrg(Long merchId,String newOrgId,String oldOrgId,String changeType,String states,Long stationId,Long salesrepId ){
    	return this.service.changeMerchOrg(merchId,newOrgId,oldOrgId,changeType,states,stationId,salesrepId);
    }
}
