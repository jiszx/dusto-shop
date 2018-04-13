package com.hhnz.pub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TDict;
import com.hhnz.crm.service.IDictService;
import com.hhnz.pub.model.TArea;
import com.hhnz.pub.service.IAreaService;

/**
 * Created by yang on 2016-7-6.
 */
@Controller
@RequestMapping("/pub/area")
public class AreaController {

    @Autowired
    private IAreaService service;
    
    @Autowired
    private IDictService dictService;

    @RequestMapping("index.html")
    public String indexPage()throws Exception{
        return "config/area";
    }

    @RequestMapping(value = {"/listChildren","/list"})
    @ResponseBody
    public List<TArea> listArea(String pid,String level)throws Exception{
        if(StringUtils.hasLength(pid)){
            return this.service.findAll(pid);
        }else{
            if(StringUtils.hasLength(level)){
                return this.service.findallByLevel(level);
            }
            return this.service.findAll();
        }
    }

    @RequestMapping("edit")
    @ResponseBody
    public TArea edit(TArea bean )throws Exception{
        int res = this.service.updateArea(bean);
        if(res > 0){
            return bean;
        }else{
            throw new Exception();
        }
    }
    
    @RequestMapping(value = {"/rdc"},method=RequestMethod.POST,produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String showRDC(Long cityId) throws Exception{
    	TDict dict = dictService.findByCityId(cityId);
    	if(dict == null){
    		return "没有对应的RDC";
    	}
    	return dict.getShowText();
    }
}
