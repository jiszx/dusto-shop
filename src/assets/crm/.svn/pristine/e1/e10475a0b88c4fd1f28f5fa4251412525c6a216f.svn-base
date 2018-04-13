package com.hhnz.crm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.dto.ProductPriceAdjustDTO;
import com.hhnz.crm.dto.ProductPriceAdjustEditDTO;
import com.hhnz.crm.dto.ProductPriceDTO;
import com.hhnz.crm.enu.PriceAdjustStatus;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialPriceAdjust;
import com.hhnz.crm.model.TMaterialPriceAdjustRecord;
import com.hhnz.crm.service.IProductPriceService;
import com.hhnz.dto.RespMsg;
import com.hhnz.process.service.IProcessService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.Constants;
import com.hhnz.util.Files;
import com.hhnz.util.ValidationResult;
import com.hhnz.util.ValidationUtil;


@Controller
@RequestMapping("/product/price")
public class ProductPriceController {
	private static Logger LOG = Logger.getLogger(ProductPriceController.class);
	@Resource
	private IProductPriceService productPriceService;
	@Autowired
	private IProcessService processService;

	@RequestMapping(value = "/adjust.html", method = RequestMethod.GET)
	public String adjustPrice() {
		return "sapData/adjust";
	}
	
	@ResponseBody
	@RequestMapping(value = "/adjust/list", method = RequestMethod.GET)
	public AjaxDTO adjustList(AjaxDTO bean, TMaterialPriceAdjust model) {
		return this.productPriceService.findPriceAdjusts(bean, model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/adjust/detail/list/{id}", method = RequestMethod.GET)
	public AjaxDTO adjustDetailList(AjaxDTO bean, @PathVariable("id") Long adjustId) {
		if(bean.getLimit() == 0){
			bean.setLimit(Integer.MAX_VALUE);
		}
		return this.productPriceService.findPriceAdjustDetails(bean, adjustId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public RespMsg<TMaterialPriceAdjustRecord> editPrice(ProductPriceDTO model) {
		ValidationResult validateResult = ValidationUtil.validateEntity(model);
		RespMsg<TMaterialPriceAdjustRecord> resp = new RespMsg<>();
		if(validateResult.isHasErrors()){
			resp.setErrorCode(-1);
			resp.setErrorMessage(validateResult.getErrorMsg().toString());
		}
		productPriceService.editPrice(model);
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/adjust/edit", method = RequestMethod.POST)
	public RespMsg<?> editAdjustPrice(ProductPriceAdjustEditDTO model) {
		ValidationResult validateResult = ValidationUtil.validateEntity(model);
		RespMsg<?> resp = new RespMsg<>();
		if(validateResult.isHasErrors()){
			resp.setErrorCode(-1);
			resp.setErrorMessage(validateResult.getErrorMsg().toString());
		}
		productPriceService.editAdjustPrice(model);
		return resp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/adjust/del", method = RequestMethod.POST)
	public RespMsg<?> deleteAdjustPrice(Long adjustId) {
		RespMsg<?> resp = new RespMsg<>();
		TMaterialPriceAdjust mpa = productPriceService.findAdjustById(adjustId);
		if(mpa.getStatusEnu().equals(PriceAdjustStatus.DRAFT) || mpa.getStatusEnu().equals(PriceAdjustStatus.REJECT)){
			productPriceService.deleteAdjustPrice(adjustId);
		}else{
			resp.setErrorCode(-1);
			resp.setErrorMessage("只能删除草稿或者退回状态的价格调整数据！");
		}
		return resp;
	}
	
	@RequestMapping(value = "/adjust/detail/{adjustId}.html", method = RequestMethod.GET)
	public ModelAndView adjustPriceDetail(@PathVariable("adjustId") Long adjustId) {
		ModelAndView mv = new ModelAndView("sapData/adjust_detail");
		TMaterialPriceAdjust mpa = productPriceService.findAdjustById(adjustId);
		mv.addObject("priceAdjust", mpa);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/adjust/edit/batch", method = RequestMethod.POST)
	public RespMsg<?> editAdjustPriceBatch(ProductPriceAdjustDTO model) {
		ValidationResult validateResult = ValidationUtil.validateEntity(model);
		RespMsg<?> resp = new RespMsg<>();
		if(validateResult.isHasErrors()){
			resp.setErrorCode(-1);
			resp.setErrorMessage(validateResult.getErrorMsg().toString());
		}
		productPriceService.editAdjustBatchPrice(model);
		return resp;
	}

	@ResponseBody
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public String importPrice(@RequestParam("file") CommonsMultipartFile file, TAttachment annex,
			HttpServletRequest request) throws Exception {
		String suffix = Files.excelSuffix(file.getOriginalFilename());
		if (suffix == null) {
			LOG.warn("导入的价格文件格式不正确，仅支持msexcel导入，源文件名："+file.getOriginalFilename());
			return "0";
		}
		productPriceService.importPrice(file);
		return "";
	}
	
	@RequestMapping(value = "/adjust/apply", method = RequestMethod.POST)
    public @ResponseBody String applyAdjust(Long adjustId) throws Exception {
		TMaterialPriceAdjust mpa = productPriceService.findAdjustById(adjustId);

		Assert.isTrue(PriceAdjustStatus.DRAFT.equals(mpa.getStatusEnu()), "草稿状态的调整记录才能提交！");
        Map<String,Object> params = new HashMap<>();
        String processName = "price_adjust_apply";
        String viewPage = "product/price/adjust/detail/"+adjustId+".html";
        params.put("key",adjustId);
        params.put("name", mpa.getAdjustDesc());
        params.put("viewPage",viewPage);
        //启动流程
        TEmployee emp = ApplicationContextUtil.getCurrentUser();
        this.processService.startProcess(params, processName, emp.getLoginName());
        return Constants.SUCCESS_CODE_STR;
    }

}
