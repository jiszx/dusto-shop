package com.hhnz.api.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TFactoryV;
import com.hhnz.crm.service.IFactoryService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("/api/factory")
public class ApiFactoryController extends BaseController {
  
  @Resource
  private IFactoryService factoryService;

  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public List<?> listFactorys(AjaxDTO page, TFactoryV factory, HttpServletRequest request) {
    page = limitVerify(page);
    return factoryService.findFactorys(page, factory).getRows();
  }
  
}
