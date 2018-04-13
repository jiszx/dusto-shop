package com.hhnz.sapdata.controller;

import com.hhnz.sapdata.model.TProductCategory;
import com.hhnz.sapdata.service.ISAPDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yang on 2016-7-12.
 */
@Controller
@RequestMapping("/product")
public class SAPDataController {

    @Autowired
    private ISAPDataService service;

    @RequestMapping("group.html")
    public String productGroupPage()throws Exception{
        return "sapData/group";
    }

    @RequestMapping("factory.html")
    public String factoryPage()throws Exception{
        return "sapData/factory";
    }

    @RequestMapping("product.html")
    public String productPage()throws Exception{
        return "sapData/product";
    }

    @RequestMapping("categroy/list")
    @ResponseBody
    public List<TProductCategory> listByPid(String id)throws Exception{
        return this.service.findCategoryByPid(id);
    }
    
    @RequestMapping("categroy/categroys")
    @ResponseBody
    public List<TProductCategory> listAll(String id)throws Exception{
        return this.service.findAllCategoryByPid();
    }

    @RequestMapping("categroy/add")
    @ResponseBody
    public Integer addCategory(@ModelAttribute TProductCategory model)throws Exception{
        return this.service.addCate(model);
    }

    @RequestMapping("categroy/edit")
    @ResponseBody
    public Integer editCategory(@ModelAttribute TProductCategory model)throws Exception{
        return this.service.updateCate(model);
    }
    @RequestMapping("categroy/del")
    @ResponseBody
    public Integer delCategory(@RequestParam("id") String id)throws Exception{
        return this.service.delCate(id);
    }
}
