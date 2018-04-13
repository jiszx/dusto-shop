package com.hhnz.config.controller;

import com.hhnz.config.service.IAreaRDCConfigService;
import com.hhnz.config.service.IodineService;
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
 * Created by 杨 on 2016/12/20.
 */
@Controller
@RequestMapping("/config/iodine")
public class IodineController {

    @Autowired
    private IodineService service;

    @RequestMapping("index.html")
    public ModelAndView index() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<String> iodines = this.service.findAllIodins();
        mv.addObject("iodines",iodines);
        mv.setViewName("monitor/iodine");
        return mv;
    }

    @RequestMapping("iodineArea")
    public @ResponseBody
    List<String> rdcAreas(String id)throws Exception{
        List<String> aids = this.service.findIodineById(id);
        return aids;
    }
    @RequestMapping(value = "grant", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer grant(@RequestParam("rdc") String id, @RequestParam(value = "aid",required = false) List<String> auths) throws Exception {
        return this.service.iodineGrantArea(id,auths);
    }

}
