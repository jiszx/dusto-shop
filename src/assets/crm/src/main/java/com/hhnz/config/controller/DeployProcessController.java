package com.hhnz.config.controller;

import com.hhnz.config.model.ProcessDeploy;
import com.hhnz.config.service.IProcessDefService;
import com.hhnz.util.AjaxDTO;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.zip.ZipInputStream;

/**
 * Created by 杨成锡 on 2016-11-5.
 * 人工部署流程
 */
@Controller
@RequestMapping("/config/process")
public class DeployProcessController {


    @Autowired
    private IProcessDefService service;
    @Autowired
    private RepositoryService repositoryService;

    /****
     * 页面跳转
     */
    @RequestMapping("index.html")
    public String index()throws Exception{
        return "monitor/process";
    }

    @RequestMapping("list")
    public @ResponseBody AjaxDTO list(AjaxDTO bean)throws Exception{
        AjaxDTO res = this.service.findDeploy(bean);
        return res;
    }

    @RequestMapping("deploy")
    public @ResponseBody String deploy(MultipartFile upload)throws Exception{
        ZipInputStream zipInputStream =new ZipInputStream(upload.getInputStream());
        Deployment deploy2 = repositoryService.createDeployment().addZipInputStream(zipInputStream).name("人工部署").deploy();
        return deploy2.getId();
    }
}
