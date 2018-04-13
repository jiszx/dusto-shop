package com.hhnz.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TKnowledgeCategory;
import com.hhnz.crm.model.TMaterialBaseV;
import com.hhnz.crm.service.IApiKnowledgeCategoryService;
import com.hhnz.crm.service.IApiProductService;
import com.hhnz.crm.service.IProductService;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.dto.ResponseResult;
import com.hhnz.order.model.OrderMaterial;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.util.AjaxDTO;

/**
 * 产品api接口
 * 
 * @author: chaoyang.ren
 * @date:2016年8月8日
 * @time:下午4:31:05
 * @email:chaoyang.ren@foxmail.com
 */
@Controller
@RequestMapping("/api/product")
public class ApiProductController extends BaseController {
  @Resource
  private IProductService productService;
  @Resource
  private IApiProductService apiProductService;
  @Resource
  private IApiKnowledgeCategoryService apiKnowledgeCategoryService;
  @Resource
  private OrderUtilService utilService;
  @Autowired
  private ICustomerService customerService;
  
  // 产品列表
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public List<?> getProducts(AjaxDTO bean, TMaterialBaseV model, String isFilter,
      HttpServletRequest request) {
    limitVerify(bean);
    return this.productService.apiFindProducts(bean, model, isFilter).getRows();
  }
  
  // 产品详情
  @ResponseBody
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public Map<String, Object> findAttachment(AjaxDTO bean, String id) throws Exception {
    limitVerify(bean);
    Map<String, Object> result = new HashMap<>();
    TMaterialBaseV material = new TMaterialBaseV();
    material.setSapId(id);
    List<TMaterialBaseV> rows = this.productService.apiFindProducts(bean, material, null).getRows();
    material = rows.get(0);
    result.put("product", material);
    this.productService.fillOrgAndFactory(material);
    List<TAttachment> atts = productService.findAttachment(id).getRows();
    result.put("attachment", atts);
    productService.fillAttachmentUrl(atts);
    return result;
  }
  
  // 2期  登录之后出现的首页
  @ResponseBody
  @RequestMapping(value = "/indexList", method = RequestMethod.GET)
  public ResponseResult getIndexList(AjaxDTO bean,HttpServletRequest request) {
    limitVerify(bean);
    TEmployee user = loginApiUser(request);//获取用户对象好像用到了accesstoken
    AjaxDTO dto = new AjaxDTO();
//	  根据id找客户基本信息
	CMerchCustBase custBase = customerService.findCustBaseById(user.getMerchId());
   
	Long hasContractMerch = utilService.hasContractMerch(user.getMerchId());
	
	dto = this.utilService.getCustomerProduct(hasContractMerch,custBase.getOrganizationId(),"1", null, null);//获取配送商下可以查看的商品

	List<String> materialIds = new ArrayList<String>();//获取其中可看的物品的id
	for(Object tmp : dto.getRows()){
		OrderMaterial orderMaterial = (OrderMaterial)tmp;
		materialIds.add(orderMaterial.getSapId());
	}
	//user中存放    用户数据    ，用于之后   的推荐部分
	//bean中存放    分页数据    
	Map<String,Object> params = new HashMap<String, Object>();
	params.put("begin", bean.getOffset());
	params.put("end",(bean.getLimit()+bean.getOffset()));
	params.put("materialIdList", materialIds);
	List<TMaterialBaseV> product = apiProductService.findProductById(params);//获取对应的配送商可看到的物料的所有的信息
	for(Object tmp : dto.getRows()){
		OrderMaterial orderMaterial = (OrderMaterial)tmp;
		for(TMaterialBaseV line: product){
			if(orderMaterial.getSapId().equals(line.getSapId())){
				line.setInvnum(Integer.parseInt(orderMaterial.getInvnum()));
				line.setBasePrice(orderMaterial.getUnitprice());//*****用基本价格就是所谓的输出的价格
			}
		}
	}
	
	Map<String,Object> result = new HashMap<String,Object>();
    result.put("productList",product);
    result.put("activityList", null);
    return  ResponseResult.builder().data(result).build();
  }
  
  public  AjaxDTO getCustProduct(Long merchid,String orgid,String type){
		AjaxDTO dto = new AjaxDTO();
		Long hasContractMerch = utilService.hasContractMerch(merchid);
		dto = this.utilService.getCustomerProduct(hasContractMerch,orgid,type, null, null);
		return dto;
  }
  
  // 2期  分类列表
  //这个接口的    
  @ResponseBody
  @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
  public ResponseResult getCategoryList(AjaxDTO bean,String custPID,HttpServletRequest request) {
	 limitVerify(bean);
    //获取顶级分类
	TEmployee user = loginApiUser(request);//获取用户对象好像用到了accesstoken
//	  根据id找客户基本信息
	CMerchCustBase custBase = customerService.findCustBaseById(Long.parseLong(custPID));
  
    List<TMaterialBaseV> product = apiProductService.findCategoryProducts(bean);//获取顶级分类下的  两个  产品
    List<TKnowledgeCategory>  category = apiKnowledgeCategoryService.getTopCategory();//获取顶级的种类
    
    Map<String,Object> type = null;    
    Map<String,Object> result = new HashMap<String,Object>();
    
    List<Map> listInfo = new ArrayList<Map>();
    
    for(int index=0;index < category.size() ;index++){
    	TKnowledgeCategory temp = category.get(index);
    	String id = temp.getId();
    	String name = temp.getName();
    	type =  new HashMap<String,Object>();//引用一个新的map对象
    	List<TMaterialBaseV> list = new ArrayList<TMaterialBaseV>();
    	for(TMaterialBaseV tmp : product){
    		if(tmp.getCategory().equals(id)){
    			BigDecimal num = utilService.getInvNum(tmp.getSapId(),custBase.getId(), custBase.getOrganizationId());   			
    			String numString = (num.equals(null))?"0":num.toString();
    			tmp.setInvnum(Integer.parseInt(numString));
    			list.add(tmp);
    		}
    	}    	
    	
    	type.put("name",name);
    	type.put("list",list);
    	type.put("id",id);
    	listInfo.add(type);    	
    }
    
       
    result.put("productList",listInfo);
    return  ResponseResult.builder().data(result).build();
  }
  
  // 2期  登录之后出现的首页
  @ResponseBody
  @RequestMapping(value = "/typeProductList", method = RequestMethod.GET)
  public ResponseResult getTypeProductList(AjaxDTO bean,String custPID,String categoryId,String orderStation,HttpServletRequest request) {
    limitVerify(bean);
    TEmployee user = loginApiUser(request);//获取用户对象好像用到了accesstoken
    
    AjaxDTO dto = new AjaxDTO();
    
//	  根据id找客户基本信息
	CMerchCustBase custBase = customerService.findCustBaseById(Long.parseLong(custPID));
	
	Long hasContractMerch = utilService.hasContractMerch(user.getMerchId());
	
	dto = this.utilService.getCustomerProduct(hasContractMerch,custBase.getOrganizationId(),"1", null, null);//获取配送商下可以查看的商品

	List<String> materialIds = new ArrayList<String>();//获取其中可看的物品的id
	for(Object tmp : dto.getRows()){
		OrderMaterial orderMaterial = (OrderMaterial)tmp;
		materialIds.add(orderMaterial.getSapId());
	}
	
	
    Map<String,Object> params = new HashMap<String,Object>();
    params.put("AjaxDTO", bean);
    params.put("categoryId", categoryId);
    params.put("materialIdList",materialIds);
    if(orderStation!=null){
    	orderStation = orderStation.toLowerCase();
    	orderStation = orderStation.trim();
    }
    params.put("orderStation",orderStation);
    List<TMaterialBaseV> product = apiProductService.findTypeProduct(params);//猜测可能   需要 有分页和推荐  ，所以选择了用户和分页数据做参数，返回推荐产品列表
    Map<String,Object> result = new HashMap<String,Object>();
//    for(TMaterialBaseV tmp : product){
//		tmp.setInvnum(Integer.parseInt(utilService.getInvNum(tmp.getSapId(),custBase.getId(), custBase.getOrganizationId()).toString()));
//		
//	}
    List<TMaterialBaseV> info = new ArrayList<TMaterialBaseV>();
	for(Object tmp : dto.getRows()){
		OrderMaterial orderMaterial = (OrderMaterial)tmp;
		for(TMaterialBaseV line: product){
			if(orderMaterial.getSapId().equals(line.getSapId())){
				line.setInvnum(Integer.parseInt(orderMaterial.getInvnum()));
				line.setBasePrice(orderMaterial.getUnitprice());//*****用基本价格就是所谓的输出的价格
				info.add(line);
			}
		}
	}
	
	if("desc".equals(orderStation)){
		Object[] list =  info.toArray();
		Arrays.sort(list,new Comparator<Object>(){
			public int compare(Object temp1,Object temp2){
				TMaterialBaseV tmp1 = (TMaterialBaseV)temp1;
				TMaterialBaseV tmp2 = (TMaterialBaseV)temp2;
				return tmp1.getBasePrice().compareTo(tmp2.getBasePrice());
			}
		});	
		result.put("productList",list);
	}else if("asc".equals(orderStation)){
		Object[] list =  info.toArray();
		Arrays.sort(list,new Comparator<Object>(){
			public int compare(Object temp1,Object temp2){
				TMaterialBaseV tmp1 = (TMaterialBaseV)temp1;
				TMaterialBaseV tmp2 = (TMaterialBaseV)temp2;
				return tmp2.getBasePrice().compareTo(tmp1.getBasePrice());
			}
		});	
		result.put("productList",list);
	}else{
		result.put("productList",info);  
	}
	
     
    result.put("activityList", null);
    return  ResponseResult.builder().data(result).build();
  }
  
  //2期的首页 上面的搜索 接口
  @ResponseBody
  @RequestMapping(value = "/indexSearchList", method = RequestMethod.POST)
  public ResponseResult indexSearchList(AjaxDTO bean,String search,String custPID){
	  limitVerify(bean);
//	  根据id找客户基本信息
	CMerchCustBase custBase = customerService.findCustBaseById(Long.parseLong(custPID));
	
	
	AjaxDTO dto = new AjaxDTO();
  	Long hasContractMerch = utilService.hasContractMerch(custBase.getId());
	dto = this.utilService.getCustomerProduct(hasContractMerch,custBase.getOrganizationId(),"1", null, null);//获取配送商下可以查看的商品

	
	
	  //种类名称搜索
	  Map<String,Object> params = new HashMap<String,Object>();
	  params.put("begin", bean.getOffset());
	  params.put("end", bean.getLimit()+bean.getOffset());
	  params.put("name","'%"+search+"%'");
	  List<TKnowledgeCategory>  category = apiKnowledgeCategoryService.searchCategory(params);
	  //产品名称搜索
	  List<TMaterialBaseV> product = apiProductService.searchProduct(params);
	 //合并结果
	  Map<String,Object> result = new HashMap<String,Object>();
	  
	  List<TMaterialBaseV> info = new ArrayList<TMaterialBaseV>();
	  for(Object tmp : dto.getRows()){
			OrderMaterial orderMaterial = (OrderMaterial)tmp;
			for(TMaterialBaseV line: product){
				if(orderMaterial.getSapId().equals(line.getSapId())){//必须是配送商可以看的而且是符合要求的产品物料
					line.setInvnum(Integer.parseInt(orderMaterial.getInvnum()));
					line.setBasePrice(orderMaterial.getUnitprice());//*****用基本价格就是所谓的输出的价格
					info.add(line);
				}
			}
		}
  
	  result.put("category",category );
	  result.put("product", info);
	  return ResponseResult.builder().data(result).build();
  }
  
}
