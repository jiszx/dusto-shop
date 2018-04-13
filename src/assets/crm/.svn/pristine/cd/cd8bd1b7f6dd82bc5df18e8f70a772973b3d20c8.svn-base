package com.hhnz.cost.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhnz.cost.model.CrmBudgetMainV;
import com.hhnz.cost.service.ICostBudgetService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Files;
import com.hhnz.util.io.excel.util.Excel2007Util;

@Controller
@RequestMapping("/budget")
public class CostBudgetController {

  @Resource
  private ICostBudgetService budgetService;
  @Value("${upload.file.path}")
  private String basePath;

  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public AjaxDTO findBudgets(AjaxDTO condition, CrmBudgetMainV budget) {
    return this.budgetService.findBudgets(condition, budget);
  }
  
  @ResponseBody
  @RequestMapping(value = "/import", method = RequestMethod.POST)
  public Integer fileUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
    TEmployee user = (TEmployee) request.getSession().getAttribute("user");
    String suffix = Files.excelSuffix(file.getOriginalFilename());
    if (suffix == null) {
      return 0;
    }
    String filename = UUID.randomUUID().toString() + suffix;
    String path = Files.standardFolderPath(basePath) + filename;
    Files.storeFile(file, path);
    String[][] text = Excel2007Util.read(path,0);
    File fi = new File(path);
    fi.delete();
    int result = budgetService.importBudget(text, user);
    return result;
  }

}
