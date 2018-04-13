package com.hhnz.organization.controller;

import com.hhnz.customer.model.CMerchCustBaseV;
import com.hhnz.organization.service.ICompanyAreaService;
import com.hhnz.organization.service.ICompanySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨 on 2016/12/27.
 * 运营主体覆盖范围
 */
@Controller
@RequestMapping("/company/area/")
public class CompanyAreaController {

    @Autowired
    private ICompanyAreaService service;


    @RequestMapping("index.html")
    public ModelAndView index()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<CMerchCustBaseV> merchs = this.service.findCompany();
        mv.setViewName("organization/companyArea");
        mv.addObject("merch",merchs);
        return mv;
    }


    @RequestMapping("listComArea")
    public @ResponseBody  List<String> listOrg(String id)throws Exception{
        List<String> aids = this.service.findCompanyArea(id);
        if(aids !=null){
            return aids;
        }else{
            return new ArrayList<String>();
        }

    }
    @RequestMapping(value = "grant", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer grant(@RequestParam("cid") String id, @RequestParam(value = "aid",required = false) List<String> auths) throws Exception {
        return this.service.grantArea(id,auths);
    }



}