package com.hhnz.crm.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.hhnz.crm.model.TAuthority;
import com.hhnz.crm.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 描述: 权限类控制层
 * JDK 版本: 1.7*
 * 创建人: 杨成锡
 * 描述：对系统的权限资源进行管理
 */
@Controller
@RequestMapping("/system/auth")
public class AuthController {

    /**
     * 权限服务接口
     */
    @Autowired
    private IAuthorityService service;

    /**
     * 权限管理页面
     *
     * @return 页面
     * @throws Exception 异常
     */
    @RequestMapping("/index.html")
    public String indexPage() throws Exception {
        return "system/auths";
    }

    /**
     * 权限列表
     *
     * @return 列表数组
     * @throws Exception 异常
     */
    @RequestMapping("list")
    @ResponseBody
    public List<TAuthority> list() throws Exception {
        List<TAuthority> authes = this.service.findAll();
        List<TAuthority> res = new ArrayList<TAuthority>();
        for (TAuthority au : authes) {
            if (!"1".equals(au.getXtype())) {
                res.add(au);
            }
        }
        return res;
    }

    /**
     * 下载权限资源（用于开发环境和测试环境的权限统一）
     *
     * @param response 输出对象
     * @throws Exception 异常信息
     */
    @RequestMapping("download.html")
    public void downloadFile(HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=exp.txt");
        List<TAuthority> authes = this.service.findAll();
        Gson gson = new Gson();
        response.getOutputStream().write(gson.toJson(authes).getBytes("utf-8"));
        response.getOutputStream().close();
    }

    /**
     * 上传资源并更新
     *
     * @param upload 更新文件
     * @return 更新条数
     * @throws Exception 异常信息
     */
    @RequestMapping("upload")
    @ResponseBody
    public Integer upload(MultipartFile upload) throws Exception {
        int res = 0;
        File tmp = new File("tmp" + UUID.randomUUID().toString() + ".txt");
        upload.transferTo(tmp);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tmp), "utf-8"));
        String str = br.readLine();
        Gson gson = new Gson();
        List<TAuthority> auths = gson.fromJson(str, new TypeToken<List<TAuthority>>() {
        }.getType());
        res = this.service.updateAll(auths);
        return res;
    }


    /**
     * 更新权限
     *
     * @param bean 权限BEAN
     * @return 更新条数
     * @throws Exception 异常
     */
    @RequestMapping("edit")
    @ResponseBody
    public int edit(TAuthority bean) throws Exception {
        return this.service.update(bean);
    }


    /**
     * 添加权限
     * @param bean 权限对象
     * @return 更新记录数量
     * @throws Exception 异常信息
     */
    @RequestMapping("add")
    @ResponseBody
    public int add(TAuthority bean) throws Exception {
        return this.service.add(bean);
    }

    /**
     * 删除权限
     * @param resId 主键
     * @return 影响记录数
     * @throws Exception 异常
     */
    @RequestMapping("del")
    @ResponseBody
    public int add(Long resId) throws Exception {
        return this.service.delete(resId);
    }


}
