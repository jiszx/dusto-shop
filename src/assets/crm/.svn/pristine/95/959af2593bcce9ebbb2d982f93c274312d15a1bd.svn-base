package com.hhnz.organization.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.organization.model.CrmOrgArea;
import com.hhnz.organization.model.CrmOrganizationInvoices;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.pub.service.IAreaService;
import com.hhnz.util.AjaxDTO;

/**
 * Created by yang on 2016-7-6.
 */
@Controller
@RequestMapping("/Org")
public class OrganizationController {

    @Autowired
    private IorganizationService service;

    @Autowired
    private IAreaService areaService;

//    @RequestMapping("/index.html")
//    public String indexPage(){
//        return "organization/organization";
//    }

    @RequestMapping("/index.html")
    public String addOrgPage(){
        return "organization/org";
    }

    @RequestMapping("/station.html")
    public String stationPage(){
        return "organization/station";
    }

    @RequestMapping("/area.html")
    public String areaPage(){
        return "organization/area";
    }
    
    @RequestMapping("/invoices.html")
    public String infoInvoices(){
    	return "organization/infoInvoices";
    }
    @RequestMapping("list")
    @ResponseBody
    public List<CrmSalesOrganization> listAll(String level)throws Exception{
        if(StringUtils.hasLength(level)){
            return this.service.findLessLevel(level);
        }else{
            return this.service.findAll();
        }

    }
    @RequestMapping("listPid")
    @ResponseBody
    public List<CrmSalesOrganization> listPid(String pid)throws Exception{
        return this.service.findPid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "list/level/{leve}", method = RequestMethod.GET)
    public List<CrmSalesOrganization> listLevel(@PathVariable String leve)throws Exception{
        return this.service.findByLevel(leve);
    }

    @RequestMapping("listArea")
    @ResponseBody
    public List<CrmSalesOrganization> listArea()throws Exception{
        List<CrmSalesOrganization> list = new ArrayList<CrmSalesOrganization>();
        List<CrmSalesOrganization> plist = this.service.findAll();
        List<CrmOrgArea> areas = this.service.findOrgArea();
        list.addAll(plist);
        Set<String> pids = new HashSet<String>();
        for(CrmSalesOrganization canSee:plist){
            pids.add(canSee.getId());
        }
        for (CrmOrgArea  cc:areas) {
            if(!pids.contains(cc.getPid())){
                continue;
            }
            CrmSalesOrganization temp =new CrmSalesOrganization();
            temp.setId(cc.getId());
            temp.setPid(cc.getPid());
            temp.setName(cc.getName());
            temp.setAbbrName(cc.getName());
            list.add(temp);
        }
        return list;
    }

    @RequestMapping("add")
    @ResponseBody
    public Integer addOrg(CrmSalesOrganization model)throws Exception{
        model.setCreateTs(new Date());
        model.setType ("0");
        return this.service.addOrg(model);
    }

    @RequestMapping("edit")
    @ResponseBody
    public Integer edit(CrmSalesOrganization model)throws Exception{
        return this.service.editOrg(model);
    }

    @RequestMapping("del")
    @ResponseBody
    public Integer edit(String id)throws Exception{
        CrmSalesOrganization model = new CrmSalesOrganization();
        model.setId(id);
        model.setStates("1");
        return this.service.editOrg(model);
    }

    @RequestMapping(value = "getAuth")
    @ResponseBody
    public List<String> findRoleAuth(@RequestParam("id") String id) throws Exception {
        List<String> ids = this.service.findArea(id);
        return ids;
    }

    @RequestMapping(value = "grant", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer grant(@RequestParam("orgId") String id, @RequestParam("aid") List<String> auths) throws Exception {
        return this.service.orgGrantArea(id,auths);
    }
    
    @RequestMapping("infoInvoicesList")
    @ResponseBody 
    public AjaxDTO  getInfoInvoices(@ModelAttribute AjaxDTO bean) throws Exception{
    	return this.service.getInfoInvoices(bean);
    }
    
    @RequestMapping("maintainInfoInvoices")
    @ResponseBody
    public Integer maintainInfoInvoices(@ModelAttribute CrmOrganizationInvoices model) throws Exception{
    	return  this.service.maintainInfoInvoices(model);
    }
}
