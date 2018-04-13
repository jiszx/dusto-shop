package com.hhnz.crm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.service.IEmployeeService;
import com.hhnz.crm.service.IRoleService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.util.AjaxDTO;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/system/emp/")
public class EmployeeController {

	/**
	 * 用户服务接口
	 */
    @Autowired
    private IEmployeeService service;
    /**
     * 用户角色接口
     */
    @Autowired
    private IRoleService roleService;
    @Resource
    private ICustomerService customerService;

    /**
     * 用户管理首页
     * @return 用户界面
     * @throws Exception 异常
     */
    @RequestMapping("index.html")
    public ModelAndView indexPage()throws Exception{
        ModelAndView mv = new ModelAndView();
        AjaxDTO dto = this.roleService.findRole(new HashMap<String, Object>(),new AjaxDTO());
        mv.addObject("roles",dto.getRows());
        mv.setViewName("system/emp");
        return mv;
    }
    
    @RequestMapping("delivery.html")
    public ModelAndView deliveryPage()throws Exception{
        ModelAndView mv = new ModelAndView();
        AjaxDTO dto = this.roleService.findRole(new HashMap<String, Object>(),new AjaxDTO());
        mv.addObject("roles",dto.getRows());
        mv.setViewName("system/delivery");
        return mv;
    }

    /**
     * 用户列表
     * @param orgId 组织ID
     * @param bean ajax请求参数
     * @return 数组
     * @throws Exception 异常信息
     */
    @RequestMapping("list")
    public @ResponseBody
    AjaxDTO listPage(String orgId,@ModelAttribute AjaxDTO bean, TEmployee emp, HttpServletRequest request) throws Exception {
        TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        Map<String,Object> map = new HashMap<String,Object>();
        if(StringUtils.hasLength(orgId)){
            map.put("org",orgId);
        }
        if(StringUtils.hasLength(emp.getUserType())){
          map.put("userType", emp.getUserType());
        }
        if(StringUtils.hasLength(emp.getUserType()) && user.getMerchId()!=null){
          map.put("merchid", user.getMerchId());
        }
        if(StringUtils.hasLength(emp.getStates())){
          map.put("states", emp.getStates());
        }
        if(emp.getRoleId() !=null){
            map.put("role", emp.getRoleId());
        }
        AjaxDTO dto = this.service.findUserByCondation(map,bean);
        return dto;
    }
    
    
    /**
     * 检查重复
     * @param loginName 用户名
     * @param response response
     * @throws Exception 异常
     */
    @RequestMapping("checkUser")
    public void checkUser(String loginName,HttpServletResponse response) throws Exception {
    	int res = this.service.checkUser(loginName);
    	if(res > 0){
    		response.getWriter().write("false");
    	}else{
    		response.getWriter().write("true");
    	}
    	response.getWriter().close();
    }

    /**
     * 新增用户
     * @param model 用户信息
     * @param request 请求对象
     * @return 是否成功
     * @throws Exception 异常
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public @ResponseBody Integer addDict(@ModelAttribute TEmployee model, HttpServletRequest request)throws Exception{
        TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        model.setCreateTs(new Date());
        model.setStates("0");
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String newPwd = encoder.encodePassword("12345678",null);
        model.setPasswd(newPwd);
        if(!StringUtils.hasLength(model.getUserType())){
          model.setUserType("0");
        }
        if("1".equals(model.getUserType()) && user.getMerchId()!=null){
          model.setMerchId(user.getMerchId());
          model.setStates("1");
          model.setOrganizationId(user.getOrganizationId());
        }
        return this.service.addEmployee(model);
    }

    /**
     * 用户编辑
     * @param model 用户模型
     * @return 是否成功
     * @throws Exception 异常
     */
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public @ResponseBody Integer edit(@ModelAttribute TEmployee model)throws Exception{
        return this.service.updateEmployee(model);
    }

    /**
     * 用户停用
     * @param id 用户ID
     * @return 是否成功
     * @throws Exception 异常
     */
    @RequestMapping(value = "userStop",method = RequestMethod.POST)
    public @ResponseBody Integer userStop(@RequestParam("id")Long id)throws Exception{
        TEmployee model = new TEmployee();
        model.setStates("2");
        model.setId(id);
        return this.service.updateEmployee(model);
    }

    /**
     * 密码重设
     * @param id 用户名
     * @return 是否重设成功
     * @throws Exception 异常
     */
    @RequestMapping(value = "pwdReset",method = RequestMethod.POST)
    public @ResponseBody Integer pwdReset(@RequestParam("id")Long id)throws Exception{
        TEmployee model = new TEmployee();
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String newPwd = encoder.encodePassword("12345678",null);
        model.setId(id);
        model.setPasswd(newPwd);
        return this.service.updateEmployee(model);
    }

    /**
     * 用户启用
     * @param id 用户ID
     * @return 是否成功
     * @throws Exception 异常信息
     */
    @RequestMapping(value = "userStart",method = RequestMethod.POST)
    public @ResponseBody Integer userStart(@RequestParam("id")Long id)throws Exception{
        TEmployee model = new TEmployee();
        model.setStates("1");
        model.setId(id);
        return this.service.updateEmployee(model);
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否删除成功
     * @throws Exception 异常
     */
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public @ResponseBody Integer del(@RequestParam("id") Long id)throws Exception{
        return this.service.delEmployee(id);
    }
    
    // 关联配送商的客户
    @ResponseBody
    @RequestMapping(value = "/relate",method = RequestMethod.POST)
    public int relate(Long userid, Long merchid)throws Exception{
      TEmployee user = service.findById(userid);
      CMerchCustBase merch = customerService.findCustBaseById(merchid);
      user.setMerchId(merchid);
      user.setOrganizationId(merch.getOrganizationId());
      user.setStates("1");
      return service.updateEmployee(user);
    }
}
