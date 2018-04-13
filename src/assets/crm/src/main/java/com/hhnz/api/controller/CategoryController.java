package com.hhnz.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.sapdata.model.TProductCategory;
import com.hhnz.sapdata.service.ISAPDataService;

@Controller
@RequestMapping("/api/category")
public class CategoryController extends BaseController {

  @Autowired
  private ISAPDataService service;

  // 根据父id获取分类表
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public List<TProductCategory> listByPid(String id) throws Exception {
    return this.service.findCategoryByPid(id);
  }
  
  // 版本2 获取品牌，系列，内包装
  @ResponseBody
  @RequestMapping(value = "/categorys", method = RequestMethod.GET)
  public List<TProductCategory> category() {
    return service.findCategorys();
  }

  // 获取分类表
  @ResponseBody
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<TProductCategory> categorys() throws Exception {
    return this.service.findAllCategoryByPid();
  }

  //crm  二期   返回系列
  //
  @ResponseBody
  @RequestMapping(value = "/allCategory", method = RequestMethod.GET)
  public List<TProductCategory> allCategory() {
	  List<TProductCategory> categorys = service.findCategorys();
	  List<TProductCategory> result = new ArrayList<TProductCategory>();
	  for(int index=0;index<categorys.size();index++){
		  TProductCategory tmp = categorys.get(index);
		  if("2".equals(tmp.getLevel())){
			  tmp.setId(tmp.getName());
			  result.add(tmp);
		  }
	  }	  
	  return result;
  }
}
