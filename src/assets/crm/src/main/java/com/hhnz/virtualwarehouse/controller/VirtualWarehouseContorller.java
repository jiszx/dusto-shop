package com.hhnz.virtualwarehouse.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TFactory;
import com.hhnz.crm.service.IFactoryService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.virtualwarehouse.dto.VirtualWarehouseQueryDTO;
import com.hhnz.virtualwarehouse.service.IVirtualWarehouseService;

/**
 * 虚拟仓库
 * @author: chaoyang.ren
 * @date:2016年9月12日
 * @time:上午10:41:08
 * @email:chaoyang.ren@foxmail.com
 */
@Controller
@RequestMapping("/vw")
public class VirtualWarehouseContorller {
	
	@Resource
	private IVirtualWarehouseService vwService;
	
	@Resource
	private IFactoryService factoryService;
	
	/**
	 * 虚拟仓库页面
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午5:28:36
	 * @return
	 */
	@RequestMapping("index.html")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("virtualwarehouse/vm");
		List<TFactory> factoryList = factoryService.findAll();
		mv.addObject("factoryList", factoryList);
		return mv;
	}
	
	/**
	 * 虚拟仓库信息列表
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午5:29:01
	 * @param bean
	 * @param queryDto
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public AjaxDTO vmList(AjaxDTO bean, VirtualWarehouseQueryDTO queryDto){
		return vwService.find(bean,queryDto);
	}
	
	@ResponseBody
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String generateExcel(AjaxDTO bean, VirtualWarehouseQueryDTO queryDto) throws IOException{
	  return vwService.generateExcel(bean, queryDto);
	}
	
}
