package com.hhnz.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TDict;
import com.hhnz.crm.service.IDictService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.util.AjaxDTO;

/**
 * 描述: 字典类控制层                         
 * JDK 版本: 1.7*                            
 * 创建人: 杨成锡 
 * 
 */	
@Controller
@RequestMapping("/system/dict")
public class DictController {

	/**
	 * 字典服务接口
	 */
    @Autowired
    private IDictService service;

    /**
     * 字典展示页面
     * @return 页面视图
     */
    @RequestMapping("index.html")
    public String indexPage() {
        return "system/dict";
    }
    
    @RequestMapping("/bank.html")
    public String bankPage() {
        return "system/bank";
    }

    /**
     * ajax字典数据请求页面
     * @param bean ajax请求参数对象
     * @return ajax字符串
     * @throws Exception 异常
     */
    @RequestMapping("list")
    public
    @ResponseBody
    AjaxDTO listPage(@ModelAttribute AjaxDTO bean) throws Exception {
        AjaxDTO dto = this.service.findDict(new HashMap<String, Object>(), bean);
        return dto;
    }

    /**
     * 字典信息添加
     * @param model 添加信息对象
     * @return 成功更新的条数
     * @throws Exception 异常
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer addDict(@ModelAttribute TDict model) throws Exception {
        return this.service.addDict(model);
    }
    /**
     * 字典信息修改
     * @param model 修改信息对象
     * @return 成功更新的条数
     * @throws Exception 异常
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer edit(@ModelAttribute TDict model) throws Exception {
        return this.service.updateDict(model);
    }
    /**
     * 字典信息删除
     * @param model 删除信息对象
     * @return 成功更新的条数
     * @throws Exception 异常
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer del(@RequestParam("id") Long id) throws Exception {
        return this.service.delDict(id);
    }
    
    /**
     * 详情页面
     * @param id 主键值
     * @return 页面视图
     * @throws Exception 异常信息
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView showDetail(@RequestParam("id") Long id) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/dictDetail");
        TDict vo = this.service.findById(id);
        mv.addObject("bean", vo);
        return mv;
    }

    /**
     * 初始化加载字典
     * @return 加载成功表示
     * @throws Exception 异常信息
     */
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer init() throws Exception {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        Map<String, List<TDict>> map = new HashMap<String, List<TDict>>();
        IDictService service = webApplicationContext.getBean(IDictService.class);
        List<TDict> list = service.findAll();
        for(TDict dict:list){
            if("APPLICATION".equals(dict.getColName())){
                servletContext.setAttribute(dict.getChooseVal(),dict.getShowText());
                continue;
            }
            if(!map.containsKey(dict.getColName())){
                map.put(dict.getColName(),new ArrayList<TDict>());
            }
            map.get(dict.getColName()).add(dict);
        }
        servletContext.setAttribute(SessionKey.DICT_NAME, map);
        return 1;
    }


}
