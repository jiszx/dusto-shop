package com.hhnz.organization.controller;

import com.hhnz.organization.service.ICompanySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 杨 on 2016/12/27.
 */
@Controller
@RequestMapping("/company/")
public class CompanySetController {

    @Autowired
    private ICompanySetService service;


    @RequestMapping("index.html")
    public ModelAndView index()throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("organization/companyConfig");
        return mv;
    }


    @RequestMapping("listComOrg")
    public @ResponseBody  String listOrg(String id)throws Exception{
        //List<String> orgs = this.service.findCompanyOrg(id);
        String com = this.service.findCompanyCode(id);
        if(com !=null){
            return com;
        }else{
            return "";
        }

    }
    @RequestMapping(value = "grant", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer grant(@RequestParam("cid") String id, @RequestParam("aid") List<String> auths) throws Exception {
        return this.service.grantOrg(id,auths);
    }



}