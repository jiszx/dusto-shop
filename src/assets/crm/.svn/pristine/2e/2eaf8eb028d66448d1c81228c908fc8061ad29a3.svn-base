package com.hhnz.combination.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.combination.dto.CombinationDTO;
import com.hhnz.combination.model.CrmMaterialPackageHeader;
import com.hhnz.combination.model.CrmMaterialPackageLines;
import com.hhnz.combination.model.CrmMaterialPackageRebate;
import com.hhnz.combination.service.CombinationService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.util.AjaxDTO;

/**
 * Created by 杨 on 2016/12/13.
 *
 * 套餐页面
 */
@Controller
@RequestMapping("/combination")
public class CombinationController {
	
	@Resource
	private  CombinationService service;
	
	/**
	 * 列表页面
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("index.html")
    public String indexPage()throws Exception{
        return "combination/index";
    }
    
    /**
     * 添加页面
     * @return
     * @throws Exception
     */
    @RequestMapping("add.html")
    public String add()throws Exception{
        return "combination/addCombin";
    }
    
    
    /**
     * 编辑页面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("edit.html")
    public ModelAndView edit(Long id) throws Exception{
    	ModelAndView mv = new ModelAndView("combination/edit");
    	CrmMaterialPackageHeader header = this.service.getHeaderById(id);
    	mv.addObject("h", header);
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	mv.addObject("bdate",sf.format(header.getbDate()));
    	mv.addObject("edate",sf.format(header.geteDate()));
    	return mv;
    }
    
    /**
     * 详情页面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("view.html")
    public ModelAndView view(Long id) throws Exception{
    	ModelAndView mv = new ModelAndView("combination/view");
    	CrmMaterialPackageHeader header = this.service.getHeaderById(id);
    	mv.addObject("h", header);
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	mv.addObject("bdate",sf.format(header.getbDate()));
    	mv.addObject("edate",sf.format(header.geteDate()));
    	return mv;
    }
    /**
     * 套餐添加/修改
     * @param header
     * @param lines
     * @param rebates
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("addOrUpdateCombination")
    @ResponseBody
    public String addOrUpdateCombination(CrmMaterialPackageHeader header,String linedata,String rebatedata,HttpServletRequest request)  throws Exception{
    	TEmployee user = (TEmployee) request.getSession().getAttribute("user");
    	
    	List<CrmMaterialPackageLines> lines = new ArrayList<CrmMaterialPackageLines>();
    	JSONArray lineArray = JSONArray.fromObject(linedata);
    	lines = linedata !=null?(List) JSONArray.toCollection(lineArray,CrmMaterialPackageLines.class):null;
    	
    	List<CrmMaterialPackageRebate> rebates= new ArrayList<CrmMaterialPackageRebate>();
    	JSONArray rebateArray = JSONArray.fromObject(rebatedata);
    	rebates = rebatedata !=null?(List) JSONArray.toCollection(rebateArray,CrmMaterialPackageRebate.class):null;
    	CombinationDTO combination = new CombinationDTO();
    	combination.setHeader(header);
    	combination.setLines(lines);
    	combination.setRebates(rebates);
    	return this.service.addOrUpdateCombination(combination,user);
    }
    
    /**
     * 获取套餐对应的产品
     */
    @RequestMapping("product")
    @ResponseBody
    public AjaxDTO  getProduct(@ModelAttribute AjaxDTO bean,String modelType,String materialId) throws Exception{
    	return this.service.getProduct(bean,modelType,materialId);
    }
    
    /**
     * 返利产品
     * @return
     * @throws Exception
     */
    @RequestMapping("rebateMaterial")
    @ResponseBody
    public  AjaxDTO getRebateMaterial()throws Exception{
    	return this.service.getRebateMaterial();
    }
    
    /**
     * 套餐列表
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping("combinationList")
    @ResponseBody
    public AjaxDTO getCombinationList(@ModelAttribute AjaxDTO bean) throws Exception{
    	return this.service.getCombinationList(bean);
    }
    
    /**
     * 获取返利列表数据
     * @param headerId 套餐头表ID
     * @return
     * @throws Exception
     */
    @RequestMapping("rebatedata")
    @ResponseBody
    public  AjaxDTO getRebateData(Long headerId) throws Exception{
    	return this.service.getRebateData(headerId);
    }
    /**
     * 获取套餐产品列表
     * @param headerId 套餐头表ID
     * @return
     * @throws Exception
     */
    @RequestMapping("linesdata")
    @ResponseBody
    public  AjaxDTO getLinesData(Long headerId) throws Exception{
    	return this.service.getLinesData(headerId);
    }
    
    /**
     * 删除套餐
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("del")
    @ResponseBody
    public String delCombination(Long id) throws Exception{
    	return this.service.delById(id);
    }
    
    /**
     * 修改套餐--添加产品行数据
     * @param line
     * @return
     * @throws Exception
     */
    @RequestMapping("edit/addLine")
    @ResponseBody
    public String addProductLine(@ModelAttribute CrmMaterialPackageLines line) throws Exception{
    	return this.service.addProductLine(line);
    }
    @RequestMapping("edit/editLine")
    @ResponseBody
    public String editProductLine(@ModelAttribute CrmMaterialPackageLines line) throws Exception{
    	return this.service.editProductLine(line);
    }
    /**
     * 修改套餐-删除产品数据
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("edit/delLine")
    @ResponseBody
    public String delProductLine(Long id) throws Exception{
    	return this.service.delProductLine(id);
    }
    
    /**
     * 修改套餐--添加返利行数据
     * @param line
     * @return
     * @throws Exception
     */
    @RequestMapping("edit/addRebateLine")
    @ResponseBody
    public String addRebateLine(@ModelAttribute CrmMaterialPackageRebate line) throws Exception{
    	return this.service.addRebateLine(line);
    }
    
    /**
     * 修改套餐-删除返利数据
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("edit/delRebate")
    @ResponseBody
    public String delRebate(Long id) throws Exception{
    	return this.service.delRebateById(id);
    }
    
    /**
     * 提交
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("audit")
    @ResponseBody
    public String audit(Long id) throws Exception{
    	return this.service.audit(id);
    }
    
    /**
     * 验证套餐编码唯一性
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping("validateCode")
    @ResponseBody
    public String validateCode(String code) throws Exception{
    	return this.service.validateCode(code);
    }
    
    /**
     * 更新套餐价格
     * @param id
     * @param price
     * @return
     * @throws Exception
     */
    @RequestMapping("updatePrice")
    @ResponseBody
    public  String updatePrice(Long id,BigDecimal price) throws Exception{
    	return this.service.updatePrice(id,price);
    }
}
