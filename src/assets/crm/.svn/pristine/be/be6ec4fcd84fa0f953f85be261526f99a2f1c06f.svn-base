package com.hhnz.config.controller;

import com.hhnz.config.service.IModelAndBrandService;
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
 * 生意模式匹配品牌
 */
@Controller
@RequestMapping("/config/ModelBrand/")
public class ModelVsBrandController {

    @Autowired
    private IModelAndBrandService service;

    @RequestMapping("index.html")
    @ResponseBody
    public ModelAndView index()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<TDict> dicts = this.service.findDictByName("BUSINESS_MODEL");
        mv.addObject("models",dicts);
        List<String> brands = this.service.findBrand();
        mv.addObject("brands",brands);
        mv.setViewName("monitor/modelBrand");
        return mv;
    }

    @RequestMapping("brands")
    public @ResponseBody List<String> modelsBrand(String model)throws Exception{
        List<String> aids = this.service.findModelBrand(model);
        return aids;
    }

    @RequestMapping(value = "grant", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer grant(@RequestParam("rdc") String id, @RequestParam(value = "aid",required = false) List<String> auths) throws Exception {
        return this.service.modelGrand(id,auths);
    }

}
