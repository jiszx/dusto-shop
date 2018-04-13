package com.hhnz.encrypt.controller;

import com.hhnz.encrypt.service.IEncryptService;
import com.hhnz.util.AjaxDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by 杨 on 2017/1/7.
 */
@Controller
@RequestMapping("system/encrypt/")
public class EncryptController {

    @Autowired
    private IEncryptService service;

    @RequestMapping("index.html")
    private String indexPage()throws Exception{
        return "encrypt/index";
    }

    @RequestMapping("list.json")
    public
    @ResponseBody
    AjaxDTO listPage() throws Exception {
        AjaxDTO dto = this.service.findEncrypts(new HashMap<String, Object>());
        return dto;
    }
}
