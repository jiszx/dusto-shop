package com.hhnz.crm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TFactory;
import com.hhnz.crm.model.TFactoryV;
import com.hhnz.crm.service.IFactoryService;
import com.hhnz.util.AjaxDTO;

/**
 * 工厂类
 */
@Controller
@RequestMapping("/factory")
public class FactoryController {

	/**
	 * 工厂服务接口
	 */
	@Resource
	private IFactoryService factoryService;

	/**
	 * 工厂列表
	 * @param bean ajax请求对象
	 * @param factory 工厂对象
	 * @param request request对象
	 * @return ajax对象
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AjaxDTO listFactorys(AjaxDTO bean, TFactoryV factory, HttpServletRequest request) {
		return factoryService.findFactorys(bean, factory);
	}

	/**
	 * 物料列表
	 * @param bean ajax请求对象
	 * @param id 工厂id
	 * @return AJAX对象
	 */
	@ResponseBody
	@RequestMapping(value = "/materials", method = RequestMethod.GET)
	public AjaxDTO listMaterials(AjaxDTO bean, String id) {
		return factoryService.findMaterials(bean, id);
	}

	/**
	 * 删除工厂
	 * @param id 工厂编号
	 * @return 更新数量
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int deleteFactory(String id) {
		return factoryService.delete(id);
	}

	/**
	 * 编辑工程
	 * @param factory 工厂
	 * @return 是否成功
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public int editOrganization(TFactory factory) {
		return factoryService.editOrganization(factory);
	}

	/**
	 * 工厂页面
	 * @return 工厂视图页面
	 */
	@RequestMapping(value = "/factory.html", method = RequestMethod.GET)
	public String factory() {
		return "sapData/factory";
	}

}
