package com.hhnz.crm.controller;

import com.hhnz.crm.model.*;
import com.hhnz.crm.service.IKnowledgeService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.pub.service.IAreaService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.AjaxImageURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by yang on 2016-7-19.
 */
@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    private IKnowledgeService service;
    @Autowired
    private IAttachmentService attaService;

    @RequestMapping("index")
    public String indexPage()throws Exception{
        return "system/knowledge";
    }


    @RequestMapping("list")
    public @ResponseBody
    AjaxDTO listPage(String category,@ModelAttribute AjaxDTO bean) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        if(StringUtils.hasLength(category)){
            map.put("category",category);
        }
        AjaxDTO dto = this.service.findList(map,bean);
        return dto;
    }

    @RequestMapping("/category")
    public @ResponseBody  List<TKnowledgeCategory> treeList()throws Exception{
        List<TKnowledgeCategory> categorys = this.service.findAllCategory();
        return categorys;
    }

    @RequestMapping(value = "addFile",method = RequestMethod.POST)
    public @ResponseBody Integer addDict(@ModelAttribute TAttachment model, @RequestParam(value = "upload", required = false) MultipartFile[] file,HttpServletRequest request)throws Exception{
        String name= UUID.randomUUID().toString();
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        return this.attaService.addAttachment(TKnowledgeCategory.class.getSimpleName(),model.getObjectName(),emp.getLoginName(),file);
    }

//    @RequestMapping(value = "edit",method = RequestMethod.POST)
//    public @ResponseBody Integer edit(@ModelAttribute TAttachment model,MultipartFile upload,HttpServletRequest request)throws Exception{
//        if(upload !=null && upload.getSize() > 0){
//            String name= UUID.randomUUID().toString();
//            TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
//            String ext = upload.getOriginalFilename().substring(upload.getOriginalFilename().indexOf("."));
//            this.filePath="D:\\upload\\images\\attachment\\";
//            upload.transferTo(new File(this.filePath+name+ext));
//            model.setAttachmentType("1");
//            model.setFileName("images/attachment/"+name+ext);
//            model.setFilePath(this.filePath+name+ext);
//            model.setUploadOid(emp.getId()+"");
//        }
//        return this.service.update(model);
//    }

    @RequestMapping(value = "del",method = RequestMethod.POST)
    public @ResponseBody Integer del(@RequestParam("id") Long id)throws Exception{
        return this.service.del(id);
    }
}
