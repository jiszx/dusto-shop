package com.hhnz.config.controller;

import com.hhnz.config.service.IAreaRDCConfigService;
import com.hhnz.crm.model.TDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 杨 on 2016/12/16.
 */
@Controller
@RequestMapping("config/RDC")
public class AreaRDCController {

    @Autowired
    private IAreaRDCConfigService service;

    @RequestMapping("index.html")
    public ModelAndView index() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<TDict> dicts = this.service.findDictByName("VIRTUAL_WAREHOUSE_CODE");
        mv.addObject("rdc",dicts);
        mv.setViewName("monitor/RDConfig");
        return mv;
    }

    @RequestMapping("rdcArea")
    public @ResponseBody List<String> rdcAreas(String rdc)throws Exception{
        List<String> aids = this.service.findRdcArea(rdc);
        return aids;
    }

    @RequestMapping(value = "grant", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer grant(@RequestParam("rdc") String id, @RequestParam("aid") List<String> auths) throws Exception {
        return this.service.rdcGrantArea(id,auths);
    }
}
