package com.hhnz.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.dto.MoqList;
import com.hhnz.crm.model.BusinessModelMoq;
import com.hhnz.crm.service.IMoqService;

/**
 * @author: chaoyang.ren
 * @date:Mar 16, 2017
 * @time:2:27:19 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Controller
@RequestMapping("/config/moq")
public class MoqController {

    @Autowired
    private IMoqService moqService;

    @RequestMapping("index.html")
    public ModelAndView indexPage() {
    	ModelAndView mv = new ModelAndView("system/business_model_moq");
    	List<BusinessModelMoq> moqs = this.moqService.findAll();
    	mv.addObject("moqs", moqs);
        return mv;
    }

    @RequestMapping(value="save", method=RequestMethod.POST)
    public String save(MoqList moqList) {
    	List<BusinessModelMoq> moqs = moqList.getMoqs();
    	moqService.save(moqs);
        return "redirect:index.html";
    }

}
