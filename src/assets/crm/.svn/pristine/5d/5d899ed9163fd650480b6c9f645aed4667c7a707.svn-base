package com.hhnz.virtualwarehouse.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Constants;
import com.hhnz.virtualwarehouse.dto.VirtualWarehouseQueryDTO;
import com.hhnz.virtualwarehouse.enu.VirtualWarehouseStatusEnu;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseEntry;
import com.hhnz.virtualwarehouse.service.IVirtualWarehouseEntryService;

/**
 * 虚拟仓库入库
 * @author: chaoyang.ren
 * @date:2016年9月12日
 * @time:上午10:41:08
 * @email:chaoyang.ren@foxmail.com
 */
@Controller
@RequestMapping("/vw/entry")
public class VirtualWarehouseEntryContorller {
	
	@Resource
	private IVirtualWarehouseEntryService vwEntryService;
	
	/**
	 * 虚拟仓库入库页面
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午5:28:36
	 * @return
	 */
	@RequestMapping("index.html")
	public String index(){
		return "virtualwarehouse/vmEntry";
	}
	
	/**
	 * 虚拟仓库入库信息列表
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午5:29:01
	 * @param bean
	 * @param queryDto
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public AjaxDTO vmEntryList(AjaxDTO bean, VirtualWarehouseQueryDTO queryDto){
		return vwEntryService.find(bean,queryDto);
	}
	
	/**
	 * 虚拟仓库入库信息添加
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午5:29:13
	 * @param vmEntry
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody String addVMEntry(@Valid CrmVirtualWarehouseEntry vmEntry, BindingResult result, HttpServletRequest request){
		if(result.hasErrors()){
			return result.getGlobalError().getDefaultMessage();
		}
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		vmEntry.setStatus(VirtualWarehouseStatusEnu.DRAFT.getCode());
		vmEntry.setCreateOid(user.getId());
		vmEntry.setCreateTs(new Date());
		vmEntry.setUpdateOid(user.getId());
		vmEntry.setUpdateTs(vmEntry.getCreateTs());
		vwEntryService.save(vmEntry);
		return Constants.SUCCESS_CODE_STR;
	}
	
	/**
	 * 虚拟仓库入库信息编辑
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午5:29:24
	 * @param vmEntry
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody String editVMEntry(@Valid CrmVirtualWarehouseEntry vmEntry, BindingResult result, HttpServletRequest request){
		if(result.hasErrors()){
			return result.getGlobalError().getDefaultMessage();
		}
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		vmEntry.setStatus(VirtualWarehouseStatusEnu.DRAFT.getCode());
		if(vmEntry.getId() != null){
			CrmVirtualWarehouseEntry existedOne = vwEntryService.findById(vmEntry.getId());
			if(!VirtualWarehouseStatusEnu.DRAFT.getCode().equals(existedOne.getStatus()) && !VirtualWarehouseStatusEnu.REJECT.getCode().equals(existedOne.getStatus())){
				return "当前入库信息状态不能修改!";
			}
			vmEntry.setStatus(VirtualWarehouseStatusEnu.DRAFT.getCode());
			vmEntry.setCreateOid(existedOne.getCreateOid());
			vmEntry.setCreateTs(existedOne.getCreateTs());
			vmEntry.setUpdateOid(user.getId());
			vmEntry.setUpdateTs(vmEntry.getCreateTs());
			vwEntryService.save(vmEntry);
			return Constants.SUCCESS_CODE_STR;
		}else{
			return "非法参数!";
		}
	}
	
	/**
	 * 虚拟仓库入库信息删除
	 * @author: chaoyang.ren 
	 * @date:2016年9月12日  下午5:29:36
	 * @param vmEntry
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody String delVMEntry(Long id){
		CrmVirtualWarehouseEntry existedOne = vwEntryService.findById(id);
		if(!VirtualWarehouseStatusEnu.DRAFT.getCode().equals(existedOne.getStatus()) && !VirtualWarehouseStatusEnu.REJECT.getCode().equals(existedOne.getStatus())){
			return "当前入库信息状态不能删除!";
		}
		vwEntryService.delete(id);
		return Constants.SUCCESS_CODE_STR;
	}
	
	/**
	 * 虚拟仓库入库信息提交审批
	 * @author: chaoyang.ren 
	 * @date:2016年9月13日  上午9:14:37
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "apply", method = RequestMethod.POST)
	public @ResponseBody String applyVMEntry(Long id){
		CrmVirtualWarehouseEntry existedOne = vwEntryService.findById(id);
		if(!VirtualWarehouseStatusEnu.DRAFT.getCode().equals(existedOne.getStatus()) && !VirtualWarehouseStatusEnu.REJECT.getCode().equals(existedOne.getStatus())){
			return "当前入库信息状态不能申请审批!";
		}
		//TODO 审批流程添加
		return Constants.SUCCESS_CODE_STR;
	}
}
