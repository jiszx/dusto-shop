package com.hhnz.monitor.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.monitor.service.IAppManageService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Files;

@Controller
@RequestMapping("/monitor/app")
public class AppManageController {

  @Value("${upload.file.path}")
  private String basePath;
  @Resource
  private IAppManageService appService;
  
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public AjaxDTO findAttachment(AjaxDTO page) {
    return appService.findAttachment(page);
  }

  @ResponseBody
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public String fileUpload(@RequestParam("file") CommonsMultipartFile file, TAttachment att,
      String objName, HttpServletRequest request) throws Exception {
    TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    String suffix = Files.appSuffix(file.getOriginalFilename());
    if(suffix==null){
      return "0";
    }
    String filename = "app/" + UUID.randomUUID().toString() + suffix;
    String path = Files.standardFolderPath(basePath) + filename;

    att.setFilePath(path);
    att.setFileName(filename);
    att.setObjectName(StringUtils.isEmpty(objName)?"appmanage":objName);
    att.setStat("0");
    appService.saveAttachment(att, user);
    Files.storeFile(file, path);
    return filename;
  }

}
