package com.hhnz.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TDict;
import com.hhnz.crm.service.IDictService;
import com.hhnz.pub.service.IAreaService;

@Controller
@RequestMapping("/api/dict")
public class ApiDictController {

  @Resource
  private IDictService dictService;
  @Resource
  private IAreaService areaService;

  @ResponseBody
  @RequestMapping(value = "/customer/type", method = RequestMethod.GET)
  public List<TDict> costomerType() {
    return dictService.findByName("CUST_MERCH_TYPE");
  }

  @ResponseBody
  @RequestMapping(value = "/business/mode", method = RequestMethod.GET)
  public List<TDict> businessMode() {
    return dictService.findByName("BUSINESS_MODEL");
  }

  @ResponseBody
  @RequestMapping(value = "/system/limit", method = RequestMethod.GET)
  public List<TDict> systemLimit() {
    return dictService.findByName("SYSTEM_LIMIT");
  }

}
