package com.hhnz.crm.controller;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.service.IEmployeeService;
import com.hhnz.crm.util.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yang on 2016-8-2.
 */
@Controller
@RequestMapping("/userCenter")
public class UserCenterController {

    @Autowired
    private IEmployeeService service;

    @RequestMapping("/user.jhtml")
    public String indexPage()throws Exception{
        return "userCenter/index";
    }
    @RequestMapping("/modifyPwd.jhtml")
    public String pwdPage()throws Exception{
        return "userCenter/pwd";
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public @ResponseBody
    Integer edit(@ModelAttribute TEmployee model, HttpServletRequest request)throws Exception{
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        model.setId(emp.getId());
        int res= this.service.updateEmployee(model);
        if(res > 0){
            TEmployee nemp = this.service.findById(emp.getId());
            request.getSession().setAttribute(SessionKey.USER_INFO,nemp);
        }
        return res;
    }

    @RequestMapping(value = "pwd",method = RequestMethod.POST)
    public @ResponseBody
    Integer pwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd, HttpServletRequest request)throws Exception{
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String oldPwdStr = encoder.encodePassword(oldPwd,null);
        String newPwdStr = encoder.encodePassword(newPwd,null);
        TEmployee vo = this.service.findById(emp.getId());
        if(oldPwdStr.equals(vo.getPasswd())){
            vo.setPasswd(newPwdStr);
            return this.service.updateEmployee(vo);
        }else{
            return 0;
        }
    }
}
