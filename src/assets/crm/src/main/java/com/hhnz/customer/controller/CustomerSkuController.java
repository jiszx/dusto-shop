package com.hhnz.customer.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.dto.CMerchCustPriceMaintenanceDTO;
import com.hhnz.customer.dto.ImportCustPrice;
import com.hhnz.customer.enu.CustPriceBatchMaintainType;
import com.hhnz.customer.model.CMerchCustPriceMaintenance;
import com.hhnz.customer.model.CMerchCustProduct;
import com.hhnz.customer.model.CMerchCustProductV;
import com.hhnz.customer.service.ICustomerPriceService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.ExcelExport;
import com.hhnz.util.Files;
import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;

@Controller
@RequestMapping("/customer/sku")
public class CustomerSkuController {
	private static Logger log = Logger.getLogger(CustomerSkuController.class);
	@Resource
	private ICustomerService customerService;

	@Resource
	private ICustomerPriceService priceService;
	@Autowired
	private IAttachmentService attaService;
	
	
	/**
	 * 价格批量维护申请页面跳转
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/batchMaintain.html")
	public String batchMaintain() throws Exception {
		return "customer/merchPriceBatchMaintain";
	}
	
	
	/**
	 * 合作盐业公司调拨差价维护
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saltAdjust.html")
	public String saltAdjust() throws Exception {
		return "customer/saltAdjust";
	}
	
	
	/**
	 * 详情
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("merchPriceBatchMaintainDetail.html")
	public ModelAndView batchMaintainDetail(Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/merchPriceBatchMaintainDetail");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		AjaxDTO dto = this.priceService.selectBatchMaintainList(params);
		CMerchCustPriceMaintenanceDTO  maintenance = (CMerchCustPriceMaintenanceDTO) dto.getRows().get(0);
		mv.addObject("maintenance", maintenance);
		mv.addObject("flag","detail");
		String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
		mv.addObject("attachmentBASEURI", baseUrl);
		List<TAttachment> attachments = attaService.findAttachment(CMerchCustPriceMaintenance.class.getSimpleName(), String.valueOf(id), null);
		if(attachments !=null && attachments.size()==1) {
			mv.addObject("attachments", attachments.get(0));			
		}
		return mv;
	}
	/**
	 * 批量维护申请详情
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("batchMaintainAudit.html")
	public ModelAndView batchMaintainAudit(Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer/merchPriceBatchMaintainDetail");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		AjaxDTO dto = this.priceService.selectBatchMaintainList(params);
		CMerchCustPriceMaintenanceDTO  maintenance = null;
		List<CMerchCustPriceMaintenanceDTO> list = dto.getRows();

		if (list.size() > 0) { // 测试时发现有空list的情况
			maintenance = list.get(0);
		} else {
			maintenance = new CMerchCustPriceMaintenanceDTO();
		}
		mv.addObject("maintenance", maintenance);
		mv.addObject("flag","audit");
		String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
		mv.addObject("attachmentBASEURI", baseUrl);
		List<TAttachment> attachments = attaService.findAttachment(CMerchCustPriceMaintenance.class.getSimpleName(), String.valueOf(id), null);
		if(attachments !=null && attachments.size()==1) {
			mv.addObject("attachments", attachments.get(0));			
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public AjaxDTO list(AjaxDTO bean, CMerchCustProductV cust) throws RuntimeException {
		return customerService.findCustSkuPrice(bean, cust);
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Integer editSku(CMerchCustProduct product) {
		return customerService.editSkuPrice(product);
	}
	
	@ResponseBody
	@RequestMapping("saltPrice")
	public AjaxDTO getsaltPrice(AjaxDTO bean) {
		return customerService.getsaltPrice(bean);
	}
	
	@ResponseBody
	@RequestMapping(value="/editsaltPrice", method = RequestMethod.POST)
	public Integer editsaltPrice(Long id ,BigDecimal price) {
		return customerService.editsaltPrice(id,price);
	}
	/**
	 * 批量维护审批详情页面，客户List
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("batchmaintainDetails")
	@ResponseBody
	public AjaxDTO batchmaintainDetails(AjaxDTO dto,Long id) throws Exception {
		return this.priceService.batchmaintainDetails(dto,id);
	}
	/**
	 * 更新客户物料
	 * 
	 * @return
	 */
	@RequestMapping(value = "upMaterial", method = RequestMethod.POST)
	public @ResponseBody Integer upMaterial() throws Exception {
		return this.customerService.upMaterial();
	}

	/**
	 * 更改物料启用或停用
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeEnable", method = RequestMethod.POST)
	public int changeEnableStatus(Long id) {
		return customerService.changeEnableStatus(id);
	}

	/**
	 * 新增价格区间
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addProductPrice", method = RequestMethod.POST)
	public String addProductPrice(Long id, BigDecimal hPrice, String priceBdate, String priceEdate)
			throws ParseException {
		return this.customerService.addProductPrice(id, hPrice, priceBdate, priceEdate);
	}
	
	

	/**
	 * 获取客户信息
	 * @param orgid
	 * @return
	 */
	@RequestMapping("customers")
	@ResponseBody
	public AjaxDTO getCustomers(String orgid) {
		return this.priceService.selectCustomerAll(orgid);
	}
	
	/**
	 * 新增批量维护申请
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addProductBatchMaintain")
	@ResponseBody
	public int addProductBatchMaintain(@ModelAttribute CMerchCustPriceMaintenance model, HttpServletRequest request)
			throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setCreateTs(new Date());
		model.setCreateOid(user.getId());
		model.setStates("1");
		model.setPrice(BigDecimalASME.multiply(model.getPrice()));
		return this.priceService.addProductBatchMaintain(model);
	}
	/**
	 * 修改批量维护申请
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editProductBatchMaintain")
	@ResponseBody
	public int editProductBatchMaintain(@ModelAttribute CMerchCustPriceMaintenance model, HttpServletRequest request)
			throws Exception {
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setUpdateTs(new Date());
		model.setUpdateOid(user.getId());
		model.setPrice(BigDecimalASME.multiply(model.getPrice()));
		return this.priceService.editProductBatchMaintain(model);
	}
	/**
	 * 批量维护申请List
	 * @param bean
	 * @param dto
	 * @return
	 */
	@RequestMapping("batchMaintainList")
	@ResponseBody
	public AjaxDTO selectBatchMaintainList(AjaxDTO bean, CMerchCustPriceMaintenanceDTO dto) {
		// return this.priceService.
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit() + 1);
		params.put("brand", Strings.emptyToNull(dto.getBrand()));
		params.put("orgId", Strings.emptyToNull(dto.getOrgid()));
		params.put("materialId", Strings.emptyToNull(dto.getMaterialId()));
		params.put("custname", Strings.emptyToNull(dto.getCustname()));
		params.put("reginId", Strings.emptyToNull(dto.getReginId()));
		params.put("areaId", Strings.emptyToNull(dto.getAreaId()));
		return this.priceService.selectBatchMaintainList(params);
	}
	
	@RequestMapping("materials")
	@ResponseBody
	public AjaxDTO getMaterials() {
		return this.priceService.selectMaterials();
	}
	
	/**
	 * 删除申请
	 * @param id
	 * @return
	 */
	@RequestMapping(value ="del",method = RequestMethod.POST)
	public @ResponseBody Integer  del(@RequestParam("id") Long id) throws Exception{
		return this.priceService.del(id);
	}
	
	/**
	 * 提交审批
	 * @param id
	 * @param states
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="audit",method = RequestMethod.POST)
	public @ResponseBody Integer audit(@RequestParam("id") Long id,String states,HttpServletRequest request) throws Exception{
		if("2".equals(states)){
    		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    		Map<String, Object> param = new HashMap<String, Object>();
    		param.put("key", id);
    		param.put("startUser",user.getLoginName());
    		param.put("groupType", "admin");
    		param.put("level", "1");
    		param.put("name", "审批编号:"+id);
    		param.put("viewPage","customer/sku/batchMaintainAudit.html?id="+id);
    		return this.priceService.startprocess(id,param,user);
		}else{			
			return null;
		}
	}
	
	/**
	 * 客户价格批量导入
	 * @param file
	 * @param priceBdate
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/importCustPrice", method = RequestMethod.POST)
	public String importCustPrice(@RequestParam("file") CommonsMultipartFile file,String priceBdate, String priceEdate,	HttpServletRequest request) throws Exception {
		String suffix = Files.excelSuffix(file.getOriginalFilename());
		if (suffix == null) {
			log.warn("客户价格批量导入的价格文件格式不正确，仅支持msexcel导入，源文件名："+file.getOriginalFilename());
			return "0";
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		CMerchCustPriceMaintenance model = new CMerchCustPriceMaintenance();
		TEmployee user = (TEmployee) request.getSession().getAttribute("user");
		model.setCreateTs(new Date());
		model.setCreateOid(user.getId());
		model.setStates("1");
		model.setType(CustPriceBatchMaintainType.IMPORT.getCode());
		model.setAdjustDirection("1");
		model.setbDate(sf.parse(priceBdate));
		model.seteDate(sf.parse(priceEdate));
		this.priceService.importCustPrice(file,model);
		return "";
	}
	
	/**
	 * 下载导入模板
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("exportTemplate")
	public void exportOrder(HttpServletRequest request,HttpServletResponse response) throws IOException, IllegalArgumentException, IllegalAccessException
	{
		List<ImportCustPrice> orderList = new ArrayList<ImportCustPrice>();
		String excelName = "客户价格导入模板.xlsx";
		ExcelResult excelResult = ExcelExport.export(excelName, orderList, ExcelType.XLSX, 0, response, ImportCustPrice.class);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("file", excelName);
		result.put("totalNum", excelResult.getTotalNum());
		result.put("successNum", excelResult.getSuccessNum());
		result.put("failureNum", excelResult.getFailureNum());
		request.setAttribute("result", result);
		
	}
	
	/**
	 * 导出详情
	 * @param bean
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("exportDetails")
	public void exportOrder(@ModelAttribute AjaxDTO bean,Long id,
			HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		AjaxDTO dto = this.priceService.batchmaintainDetails(bean,id);
		List<CMerchCustPriceMaintenanceDTO> list =dto.getRows();
		String excelName = "客户价格批量维护详情.xlsx";
		ExcelResult excelResult = ExcelExport.export(excelName, list, ExcelType.XLSX, 0, response, CMerchCustPriceMaintenanceDTO.class);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("file", excelName);
		result.put("totalNum", excelResult.getTotalNum());
		result.put("successNum", excelResult.getSuccessNum());
		result.put("failureNum", excelResult.getFailureNum());
		request.setAttribute("result", result);
		
	}
}
