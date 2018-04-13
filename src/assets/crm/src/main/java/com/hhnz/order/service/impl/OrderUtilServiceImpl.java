package com.hhnz.order.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.mapper.CMerchCustAdjustMapper;
import com.hhnz.account.mapper.CMerchUpaccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.service.impl.UpAccountServiceImpl;
import com.hhnz.crm.mapper.MoqMapper;
import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.mapper.UtilMapper;
import com.hhnz.crm.model.BusinessModelMoq;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.customer.dto.CustomerRetailDTO;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustBaseVMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.mapper.CMerchCustDistributionMapper;
import com.hhnz.customer.mapper.CMerchCustProductMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseExample;
import com.hhnz.customer.model.CMerchCustBaseV;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractExample;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.customer.model.CMerchCustDistributionExample;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.dto.ResponseResult;
import com.hhnz.order.dto.DistributeOrderDTO;
import com.hhnz.order.dto.DistributeOrderDetailsDTO;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.order.model.Invoice;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderHeadersAllExample;
import com.hhnz.order.model.OmOrderHeadersAllExample.Criteria;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.model.OrderCustomer;
import com.hhnz.order.model.OrderLinesDetials;
import com.hhnz.order.model.OrderMaterial;
import com.hhnz.order.model.OrderPolicy;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.organization.mapper.CrmSalesOrganizationMapper;
import com.hhnz.organization.mapper.CrmStationMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.process.task.order.ReduceDepotTask;
import com.hhnz.promotion.mapper.CrmPromotionMaterialVMapper;
import com.hhnz.promotion.model.CrmPromotionMaterialV;
import com.hhnz.promotion.model.CrmPromotionMaterialVExample;
import com.hhnz.salepolicy.mapper.OmPolicyLinesMapper;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.UnitConverter;
import com.hhnz.util.db.Page;
import com.hhnz.util.enmus.MaterialUnit;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;
/**
 * 销售订单管理工具Service
 * @author skevin
 *
 */
@Service
@Transactional
public class OrderUtilServiceImpl implements OrderUtilService {
    private static Logger log = Logger.getLogger(OrderUtilServiceImpl.class.getSimpleName());

	@Resource
	private OrderUtilMapper mapper;
	@Resource
	private CMerchCustDistributionMapper shipmapper;
	@Resource
	private CMerchCustAccountMapper accountmapper;
	@Resource
	private OmPolicyLinesMapper policylinemapper;
	@Resource
	private CMerchCustProductMapper  custproductmapper;
	@Resource  
	private CrmStationMapper  crmstationmapper;
	@Resource
	private UtilMapper utilmapper;
	@Resource
	private CMerchCustBaseMapper custbaseMapper;
	@Resource
	private CrmPromotionMaterialVMapper materialvMapper;
	@Resource
	private OmOrderHeadersAllMapper headerMapper;
	@Resource
	private OmOrderLinesAllMapper lineMapper;
	@Resource
	private TMaterialBaseMapper materialMapper;
	@Resource
    private CMerchCustAdjustMapper adjustMapper;
    @Resource
    private CMerchCustAccountLogMapper accountLogMapper;
    @Resource
    private CMerchUpaccountMapper upaccountmapper;
    @Resource
    private CMerchCustBalancesMapper balancemapper;
    @Resource
    private UpAccountServiceImpl upaccountService;
    @Resource
    private CrmSalesOrganizationMapper orgMapper;
    @Resource
    private CMerchCustBaseVMapper merchCustBaseVMapper;
    @Resource
    private ICustomerService customerService;
    @Resource
    private OrderService orderservice;
    @Resource
    private CustomerContractService contractService;
    @Resource
    private ReduceDepotTask reduceDepot;
    @Resource
    private OmOrderSpiltsMapper spliteMapper;
    @Resource
    private CMerchCustContractMapper contractMapper;
    @Resource
    private MoqMapper moqMapper;
	
	@Override
	public AjaxDTO getUserCustomerInfo(List<Long> stationids) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("stationid", stationids);
		return getUserCustomerInfo(stationids, params);
	}
	
	@Override
	public AjaxDTO getOwnerMerch(Long merchid){
	  CMerchCustBaseV merch = customerService.findCustWithPosition(merchid);
	  List<OrderCustomer> merchs = new ArrayList<>(1);
	  OrderCustomer cust = new OrderCustomer();
	  cust.setId(merch.getId());
	  cust.setOrganizationId(merch.getSalesOrgId());
	  cust.setStationid(merch.getPositionId());
	  cust.setCustname(merch.getName());
	  merchs.add(cust);
	  AjaxDTO dto = new AjaxDTO();
	  dto.setRows(merchs);
	  return dto;
	}
	
	@Override
	public AjaxDTO getUserCustomerInfo(List<Long> stationids, Map<String, Object> param) {
      AjaxDTO dto = new AjaxDTO();
      param.put("stationid", stationids);
      List<OrderCustomer> list = this.mapper.getUserCustomerInfo(param);
      dto.setRows(list);
      dto.setTotal(list.size());
      return dto;
  }
	
	//这个是添加了可以从名字，地区，客户类型三方面筛选的获取客户信息的方法
	@Override
	public AjaxDTO getUserCustomerInfo(List<Long> stationids,String name,String[] areaList,String[] custType,String[] statusList, AjaxDTO page) {
      Map<String,Object> params = new HashMap<String, Object>();
      params.put("stationid", stationids);
      params.put("name",name);
      params.put("begin",page.getOffset());
      params.put("end",page.getLimit());
      params.put("areaList", areaList);
      params.put("custTypeList", custType);
      params.put("statusList", statusList);
      params.put("type", "1");
      List<OrderCustomer> list = this.mapper.getUserCustomerInfo(params);
      page.setRows(list);
      return page;
    }
	
	@Override
	public AjaxDTO getCustomerShip(Long merchid, String orgid) {
		AjaxDTO dto = new AjaxDTO();
		CMerchCustDistributionExample ex = new CMerchCustDistributionExample();
		ex.createCriteria().andMerchCustIdEqualTo(merchid);
		ex.createCriteria().andOrganizationIdEqualTo(orgid);
		List<CMerchCustDistribution> list = this.shipmapper.selectByExample(ex);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	
	@Override
	public Long hasContractMerch(Long merchid){
	  CMerchCustBase merch = custbaseMapper.selectByPrimaryKey(merchid);
	  if("5".equals(merch.getCustType())){
	    return merch.getPid();
	  }
	  return merchid;
	}
	
	/**
	 *获取用户的账户余额信息
	 */
	@Override
	public AjaxDTO getCustomerAccount(Long merchid, String orgid) {
		CMerchCustAccountExample ex = new CMerchCustAccountExample();
		ex.createCriteria().andMerchCustIdEqualTo(merchid).andOrganizationIdEqualTo(orgid);
		List<CMerchCustAccount> list = this.accountmapper.selectByExample(ex);
		AjaxDTO dto = new AjaxDTO();
		if(list.size()==0){
			CMerchCustAccount account = new CMerchCustAccount();
			account.setCashAmt(new BigDecimal(0));
			account.setCreditAmt(new BigDecimal(0));
			account.setSubsidyAmt(new BigDecimal(0));
			list.add(account);
		}
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	@Override
	public AjaxDTO getCustomerProduct(Long merchid, String orgid,String type, String materialid, String combination) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("merchid", merchid);
		params.put("orgid", orgid);
		params.put("materialid", StringUtils.isEmpty(materialid) ? null : materialid);
		params.put("type", StringUtils.isEmpty(type)?null:type);
		List<OrderMaterial> list = this.mapper.getCustomerMaterial(params);
		//获取套餐物料
		if("1".equals(combination)){
			List<OrderMaterial> combinationlist = this.mapper.selectCombinationMaterial(params);
			if(combinationlist !=null && combinationlist.size() >0){
				for (OrderMaterial material :combinationlist){
					list.add(material);
				}
			}
		}
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	
	@Override
	public AjaxDTO getCustProductByRdc(Long merchid, String orgid,
			String rdcCode, String combination,String type) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("merchid", merchid);
		params.put("orgid", orgid);
		params.put("rdcCode", rdcCode);
		params.put("type", type);
		List<OrderMaterial> list = this.mapper.selectCustProductByRdc(params);
		//获取套餐物料
		if("1".equals(combination)){
			List<OrderMaterial> combinationlist = this.mapper.selectCombinationMaterial(params);
			if(combinationlist !=null && combinationlist.size() >0){
				for (OrderMaterial material :combinationlist){
					list.add(material);
				}
			}
		}
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	@Override
	public AjaxDTO selectRetailProduct(Long merchid, String orgid) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("merchid", merchid);
		params.put("orgid", orgid);
		List<OrderMaterial> list = this.mapper.selectRetailProduct(params);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	@Override
	public AjaxDTO selectKaProduct(Long merchid, String orgid) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("merchid", merchid);
		params.put("orgid", orgid);
		List<OrderMaterial> list = this.mapper.selectKaProduct(params);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	// 只为api提供服务
	@Override
	public AjaxDTO findProductWithCondition(Long merchid, String orgid, OrderMaterial material,AjaxDTO bean) {
      AjaxDTO dto = new AjaxDTO();
      Map<String,Object> params = new HashMap<String, Object>();
      params.put("merchid", hasContractMerch(merchid));
      params.put("orgid", orgid);
      params.put("type", null);
      params.put("begin", bean.getOffset());
      params.put("end", bean.getLimit()+bean.getOffset());
      if(StringUtils.isNotEmpty(material.getSku())){
        params.put("sku", "%"+material.getSku()+"%");
      }
      if(StringUtils.isNotEmpty(material.getCategory())){
        params.put("category", material.getCategory());
      }
      if(StringUtils.isNotEmpty(material.getBrand())){
        params.put("brand", material.getBrand());
      }
      if(StringUtils.isNotEmpty(material.getSeries())){
        String[] seriess = material.getSeries().split(",");
        params.put("series", Arrays.asList(seriess));
      }
      if(StringUtils.isNotEmpty(material.getiPackage())){
        params.put("ipackage", material.getiPackage());
      }
      List<OrderMaterial> list = this.mapper.getCustomerMaterial(params);
      dto.setRows(list);
      dto.setTotal(list.size());
      return dto;
  }
	
	@Override
	public AjaxDTO getOrderPolicy(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderPolicy> list = this.mapper.getOrderPolicy(params);
		params.put("materialid", null); // 去掉上个查询条件的参数
		// 增加产品信息
		for(OrderPolicy policy:list){
		  if(StringUtils.isEmpty(policy.getDiscount()) || StringUtils.isEmpty(policy.getDiscountIntensity()) || policy.getDiscount().length()<3){
		    continue;
		  }
          params.put("materialId", policy.getDiscount());
          List<OrderMaterial> materials  = this.mapper.getCustomerMaterial(params);
          OrderMaterial material = new OrderMaterial();
          if(materials.size()!=0){
              material = materials.get(0);
          }else{
              //搭赠SKU不存在于客户代理产品中
              BigDecimal price=new BigDecimal(this.mapper.getmaterialprice(policy.getDiscount()));
              //price /= 100;
              TMaterialBase base = materialMapper.selectByPrimaryKey(policy.getDiscount());
              material.setBasePrice(BigDecimalASME.divide(price));
              material.setUnitprice(BigDecimalASME.divide(price));
              material.setUnit(base.getUnit());
              material.setSku(base.getSku());
              material.setMaterialId(base.getSapId());
          }
          policy.setMaterial(material);
		  
		}
		// 增加描述信息
		for(OrderPolicy policy:list){
		  policy.setDiscountname(policyDescription(policy));
		}
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	
	private String policyDescription(OrderPolicy policy){
	  String type = policy.getVerification();
	  StringBuilder builder = new StringBuilder();
	  if("1".equals(type)){
	    OrderMaterial material = policy.getMaterial();
	    String sku = "";
	    if(material!=null){
	      sku = material.getSku();
	    }
	    return builder.append("搭赠-满").append(policy.getPrimary()).append("送").append(policy.getDiscountIntensity())
	        .append(sku).append("，最高搭赠").append(policy.getLimit()).toString();
	  }
	  if("2".equals(type)){
	    return builder.append("折扣-满").append(policy.getPrimary()).append("可享受")
	        .append(Double.parseDouble(policy.getDiscountIntensity())/10).append("折")
	        .append("，最多").append(policy.getLimit()).toString();
	  }
	  return "";
	}
	
	@Override
	public String getPolicyDisacoount(String discountMaterialid,Long merchid,String type,String orgid) {
		if(StringUtils.equals("1", type)){
			//搭赠
		    //获搭赠物料在客户代理SKU价
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("merchid", merchid);
			params.put("materialId", discountMaterialid);
			params.put("orgid", orgid);
			List<OrderMaterial> list  = this.mapper.getCustomerMaterial(params);
		    if(list.size()!=0){
		    	return list.get(0).getUnitprice().toString();
		    }else{
		    	//搭赠SKU不存在于客户代理产品中
		    	Long price= this.mapper.getmaterialprice(discountMaterialid);
		    	return price==null?"":price.toString();
		    }
		}else if(StringUtils.equals("5", type)){
			//促销品
			CrmPromotionMaterialVExample ex = new CrmPromotionMaterialVExample();
			CrmPromotionMaterialVExample.Criteria ext = ex.createCriteria();
			ext.andIdEqualTo(Long.valueOf(discountMaterialid));
			ext.andOrganizationIdEqualTo(orgid);
			List<CrmPromotionMaterialV> list = this.materialvMapper.selectByExample(ex);
			return list !=null&&list.size()>0?list.get(0).getPrice().toString():"0";
		}
		return "0";
	}
	@Override
	public AjaxDTO getlinedata(Long headerid) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderLinesDetials> list = this.mapper.getlinedata(headerid);
		nullNumToZero(list);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	@Override
	public CrmStation getStationByID(Long stationid) {
		return this.crmstationmapper.selectByPrimaryKey(stationid);
	}
	@Override
	public String getcustnameByorderId(Long id) {
		return this.mapper.getcustnameByorderId(id);
	}

	@Override
	public String getcustDisByorderId(Long id) {
		return this.mapper.getcustDisByorderId(id);
	}
	@Override
    public String getOrgnameByOrderid(Long id){
      return this.mapper.getOrgnameByorderId(id);
    }
	@Override
	public AjaxDTO getOrderAmt(Long id) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderLinesDetials> order = this.mapper.getOrderAmt(id);
		dto.setRows(order);
		dto.setTotal(order.size());
		return dto;
	}
	@Override
	public List<CrmSalesOrganization> getOrderOrg(List<UserJobs> userjobs) {
		List<CrmSalesOrganization> list = new ArrayList<CrmSalesOrganization>();
		for(UserJobs job:userjobs){		
			//获取用户能查看的职位类型
			String type = (StringUtils.equals("1", job.getJobType())||StringUtils.equals("1", job.getDataView().substring(3, 4)))?"1":"2";
			//获取数据
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("type", type);
			params.put("orgid", job.getOrgid());
			List<CrmSalesOrganization> orglist = new ArrayList<CrmSalesOrganization>();
			orglist= this.utilmapper.getOrderOrg(params);
			if(orglist.size()>0&& orglist !=null){
				for(CrmSalesOrganization org:orglist){
					list.add(org);
				}
			}
		}
		return list;
	}
	@Override
	public AjaxDTO customerByOrgid(Long id) {
		List<OrderCustomer> list =new ArrayList<OrderCustomer>();
		list = this.mapper.customerByOrgid(id);
		AjaxDTO dto = new AjaxDTO();
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	
	public void nullNumToZero(List<OrderLinesDetials> lines){
	  if(lines==null){
	    return;
	  }
	  for(OrderLinesDetials line:lines){
	    /*line.setNum(StringUtils.isEmpty(line.getNum())?"0":line.getNum());
	    line.setHbNum(line.getHbNum()==null?0L:line.getHbNum());
	    line.setAttribute2(StringUtils.isEmpty(line.getAttribute2())?"0":line.getAttribute2());
	    line.setDeliverynum(StringUtils.isEmpty(line.getDeliverynum())?"0":line.getDeliverynum());*/
		line.setNum(line.getNum()==null?new BigDecimal("0"):line.getNum());
		line.setHbNum(line.getHbNum()==null?new BigDecimal("0"):line.getHbNum());
		line.setAttribute2(StringUtils.isEmpty(line.getAttribute2())?"0":line.getAttribute2());
		line.setDeliverynum(line.getDeliverynum()==null?new BigDecimal("0"):line.getDeliverynum());
	  }
	}

	@Override
	public int validateNum(String materialId, String orgid, Long merchCustId,BigDecimal num) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("materialId", materialId);
		params.put("orgid", orgid);
		params.put("merchCustId", merchCustId);
		BigDecimal invNum=new BigDecimal(this.mapper.validateNum(params));
		if(invNum ==null ||invNum.compareTo(BigDecimal.ZERO) ==0){
			return 0;
		}else{
			return	invNum.compareTo(num)>=0?1:0;
		}
	}

	@Override
	public int validateNumByRdc(String materialId, String orgid,
			Long merchCustId, String rdcCode, BigDecimal num) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("materialId", materialId);
		params.put("orgid", orgid);
		params.put("merchCustId", merchCustId);
		params.put("rdcCode", rdcCode);
		BigDecimal invNum=new BigDecimal(this.mapper.validateNumByRdc(params));
		if(invNum ==null ||invNum.compareTo(BigDecimal.ZERO) ==0){
			return 0;
		}else{
			return	invNum.compareTo(num)>=0?1:0;
		}
	}
	@Override
	public List<Invoice> orderInvoices(Long crmOrderid){
      OmOrderHeadersAll order = headerMapper.selectByPrimaryKey(crmOrderid);
      if(StringUtils.isEmpty(order.getSapOrderId())){
        return Collections.emptyList();
      }
      return mapper.findOrderInvoice(order.getSapOrderId());
    }

	@Override
	public BigDecimal getInvNum(String materialId, Long merchCustId, String orgid) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("materialId", materialId);
		params.put("merchCustId", merchCustId);
		params.put("orgid", orgid);
		Long validateNum = this.mapper.validateNum(params);
		if(validateNum == null){
			validateNum = new Long(0);
		}
		return new BigDecimal(validateNum);
	}
	
	@Override
	public BigDecimal getInvNumByRdc(String materialId, Long merchCustId, String orgid,String rdcCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("materialId", materialId);
		params.put("merchCustId", merchCustId);
		params.put("orgid", orgid);
		params.put("rdcCode", rdcCode);
		Long validateNum = this.mapper.validateNumByRdc(params);
		if(validateNum == null){
			validateNum = new Long(0);
		}
		return new BigDecimal(validateNum);
	}
	@Override
	public AjaxDTO findDistributeOrders(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<DistributeOrderDTO> list = this.mapper.findDistributeOrders(params);
		int total = this.mapper.countDistributeOrders(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO getDistributeDetailList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<DistributeOrderDetailsDTO> list = this.mapper.getDistributeDetailList(params);
		int total = this.mapper.countDistributeDetailList(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public List<CMerchCustBase> getOrderShip(Long merchid, String orgid) {
		return getOrderShip(new AjaxDTO(), merchid, orgid);
	}
	
	@Override
    public List<CMerchCustBase> getOrderShip(AjaxDTO page, Long merchid, String orgid) {
        CMerchCustBaseExample ex = new CMerchCustBaseExample();
        if(page.getLimit()!=0){
          Page p = new Page();
          p.setLimit(page.getLimit());
          p.setOffset(page.getOffset());
          ex.setPage(p);
        }
        CMerchCustBaseExample.Criteria cc = ex.createCriteria();
        cc.andIdEqualTo(merchid);
        cc.andOrganizationIdEqualTo(orgid);
        cc.andStatesEqualTo("3");
        CMerchCustBaseExample.Criteria cd = ex.createCriteria();
        cd.andPidEqualTo(merchid);
        cd.andCustTypeNotEqualTo("5");
//        cd.andCustTypeEqualTo("6");
        cd.andStatesEqualTo("3");
        ex.or(cd);
        List<CMerchCustBase> list= this.custbaseMapper.selectByExample(ex);
        return list;
    }
	
	@SuppressWarnings("unused")
	private List<OmOrderHeadersAll> getOrdersByBatch(String batchNum, String processId){
	  return getOrders(batchNum, "2", null, processId);
	}
	
	@Override
	public List<OmOrderHeadersAll> getOrders(String batchNum, String states, String isMatchAmt, String processId){
	  OmOrderHeadersAllExample exa = new OmOrderHeadersAllExample();
	  Criteria condition = exa.createCriteria();
	  condition.andAttribute8EqualTo(batchNum);
	  if(StringUtils.isNotEmpty(states)){
	    condition.andStatesEqualTo(states);
	  }
	  if(StringUtils.isNotEmpty(processId)){
	    condition.andAttribute1EqualTo(processId);
	  }
	  if(StringUtils.isNotEmpty(isMatchAmt)){
	    condition.andAttribute9EqualTo("1");
	  }
	  return headerMapper.selectByExample(exa);
	}
	
    
    /**
     * 配送商订单删除
     */
	@Override
	public Map<String, Object> delDistributeOrder(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		OmOrderHeadersAll  header = this.headerMapper.selectByPrimaryKey(id);
		if(header ==null){
			result.put("type", "500");
			result.put("msg", "删除订单失败，未查询到该订单");
			return result;
		}
		if(!StringUtils.equals("1", header.getStates())){
			result.put("type", "500");
			result.put("msg", "订单状态未非编辑状态，请确定是否被他人修改");
			return result;
		}
		this.headerMapper.deleteByPrimaryKey(id);
		result.put("type", "200");
		result.put("msg", "删除订单成功");
		return result;
	}

	@Override
	public AjaxDTO distributorEditList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<DistributeOrderDetailsDTO> list = this.mapper.distributorEditList(params);
		int total = this.mapper.countDistributorEditList(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO distributCustomer(TEmployee user,List<Long> stations,String custType) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("usertype", user.getUserType());
		params.put("merchid", user.getMerchId());
		params.put("stationids", stations);
		params.put("custType", custType);
		List<CustomerRetailDTO> list = this.mapper.selectRetailCustomers(params);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	
	@Override
	public int censorDist(String orderid, TEmployee user) throws NumberFormatException, Exception{
	  List<Long> orderids = idsWithComma(orderid);
	  if(orderids.isEmpty()){
	    return 0;
	  }
	  
	  OmOrderHeadersAll firstOrder = headerMapper.selectByPrimaryKey(orderids.get(0));
	  String batchNum = batchNum(firstOrder.getShipId());
	  OmOrderHeadersAll updateOrderBatch = new OmOrderHeadersAll();
      updateOrderBatch.setAttribute8(batchNum);
      
      OmOrderHeadersAllExample updateExa = new OmOrderHeadersAllExample();
      updateExa.createCriteria().andIdIn(orderids);
      headerMapper.updateByExampleSelective(updateOrderBatch, updateExa);
	  
	  CMerchCustBase distributor = customerService.findCustBaseById(firstOrder.getShipId());
	  BigDecimal minOrder = distributor.getMinOrder()==null?BigDecimal.ZERO:distributor.getMinOrder();
	  int result = validateMinOrder(batchNum, minOrder);
	  if(result==1){
	    OmOrderHeadersAll updateOrderStates = new OmOrderHeadersAll();
	    updateOrderStates.setStates("2");
	    headerMapper.updateByExampleSelective(updateOrderStates, updateExa);
	    orderservice.startProcess(user, Long.parseLong(batchNum), "1");
	  }
	  return result;
	}
	
	@Override
	public ResponseResult validateDepo(String orderids) {
	  return validateOrdersDepo(orderids);
	}
	
	private ResponseResult validateOrdersDepo(String orderids){
	  StringBuilder builder = new StringBuilder();
	  CMerchCustContract contract = null;
	  Map<String, BigDecimal> materials = new HashMap<>();
	  for(String id:orderids.split(",")){
	    Long orderid = Long.parseLong(id);
	    Map<String, Object> param = new HashMap<>();
        param.put("id", orderid);
        OmOrderHeadersAll order = headerMapper.selectByPrimaryKey(orderid);
        OmOrderLinesAllExample ex = new OmOrderLinesAllExample();
        ex.createCriteria().andHeaderIdEqualTo(orderid);
        List<OmOrderLinesAll> lines = lineMapper.selectByExample(ex);
        if(contract==null){
          Long hasContractMerch = orderservice.distriButeMerchid(order.getMerchCustId());
          List<CMerchCustContract> contracts = contractService.findByCustomerAndOrgId(hasContractMerch, order.getOrganizationId());
          contract = contracts.get(0);
        }
        
        for (OmOrderLinesAll line : lines) {
          BigDecimal lineSum = line.getNum().add(line.getHbNum()==null?BigDecimal.ZERO:line.getHbNum());
          BigDecimal materialSum = materials.get(line.getMaterialId());
          materialSum = materialSum==null ? BigDecimal.ZERO : materialSum;
          materials.put(line.getMaterialId(), materialSum.add(lineSum));
        }
      }
	  
	  int code = 0;
	  for(String key:materials.keySet()){
        BigDecimal materialNum = materials.get(key);
        CrmVirtualWarehouse warehouse =
          reduceDepot.getWarehouse(contract.getFactoryId(), key, contract.getVirtualWarehouse());
        if(warehouse==null){
          code = 1;
          builder.append("物料 ").append(key).append("无库存；");
        }else if(warehouse.getAmt().compareTo(materialNum)<0){
          code = 1;
          builder.append("物料 ").append(key).append("订单合计").append(materialNum).append(" 库存总量 ").append(warehouse.getAmt()).append(";");
        }
      }
	  return ResponseResult.builder().code(code).data(builder.toString()).build();
	}
	
	private int validateMinOrder(String batchNum, BigDecimal minOrder){
	  List<OmOrderHeadersAll> orders = getOrdersByBatchNum(batchNum);
	  BigDecimal sumNum = BigDecimal.ZERO;
	  for(OmOrderHeadersAll order:orders){
	    OmOrderLinesAllExample ex = new OmOrderLinesAllExample();
	    ex.createCriteria().andHeaderIdEqualTo(order.getId());
	    List<OmOrderLinesAll> lines = lineMapper.selectByExample(ex);
	    for(OmOrderLinesAll line:lines){
	      BigDecimal toNum = UnitConverter.convert(line.getNum(), line.getMaterialId(), MaterialUnit.TO);
          sumNum = sumNum.add(toNum);
	    }
	  }
	  if(minOrder.compareTo(sumNum)<1){
	    return 1;
	  }
	  return 0;
	}
	
	public int validateMoney(List<OmOrderHeadersAll> orders){
	  Map<String, BigDecimal> moneys = new HashMap<>();
	  for(OmOrderHeadersAll order:orders){
	    String merchid = String.valueOf(order.getMerchCustId());
	    BigDecimal money = moneys.get(merchid);
	    money = money==null?BigDecimal.ZERO:money;
	    moneys.put(merchid, money.add(order.getOrderAmt()));
	  }
	  for(String merch:moneys.keySet()){
	    CMerchCustAccountExample ex = new CMerchCustAccountExample();
	    ex.createCriteria().andMerchCustIdEqualTo(Long.parseLong(merch)).andOrganizationIdEqualTo(orders.get(0).getOrganizationId());
	    List<CMerchCustAccount> accounts = accountmapper.selectByExample(ex);
	    if(accounts.isEmpty()){
	      return 0;
	    }
	    CMerchCustAccount account = accounts.get(0);
	    if(BigDecimalASME.multiply(account.getCashAmt()).compareTo(moneys.get(merch))<0){
	      return 0;
	    }
	  }
	  return 1;
	}
	
	private List<OmOrderHeadersAll> getOrdersByBatchNum(String batchNum){
      OmOrderHeadersAllExample ex = new OmOrderHeadersAllExample();
      ex.createCriteria().andAttribute8EqualTo(batchNum);
      return headerMapper.selectByExample(ex);
    }
	
	@Override
	public String batchNum(Long merchid){
	  return ""+merchid+System.currentTimeMillis();
	}
	
	private List<Long> idsWithComma(String ids){
	  if(StringUtils.isEmpty(ids)){
	    return Collections.emptyList();
	  }
	  List<Long> result = new ArrayList<>();
	  for(String id:ids.split(",")){
	    result.add(Long.parseLong(id));
	  }
	  return result;
	}

	@Override
	public String cancelTransferOrder(long headerId){
	  // 调用存储过程
	  return "200";
	}

	@Override
	public AjaxDTO selectBillCustomer(Long merchId) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderCustomer> list = this.mapper.selectBillCustomer(merchId);
	    dto.setRows(list);
	    dto.setTotal(list.size());
		return dto;
	}
	
	@Override
	public boolean canTransfer(Long orderid) {
	    OmOrderHeadersAll order = headerMapper.selectByPrimaryKey(orderid);
	    CMerchCustBase merch = custbaseMapper.selectByPrimaryKey(order.getMerchCustId());
	    CMerchCustContractExample ex = new CMerchCustContractExample();
	    ex.createCriteria().andMerchCustIdEqualTo(merch.getId()).andStatesEqualTo("4");
	    List<CMerchCustContract> contracts = contractMapper.selectByExample(ex);
	    if (contracts.isEmpty()) {
	      log.warning("客户没有可用的合同 客户id:"+merch.getId());
	      return false;
	    }
	    CMerchCustContract contract = contracts.get(0);
	    BusinessModelMoq moq = moqMapper.findByModelId(Long.parseLong(contract.getAgentType()));
	    if(moq==null || moq.getMinOrderQuantity()==null || moq.getMinOrderQuantity().compareTo(BigDecimal.ZERO)<=0){
	      return true;
	    }
	    Preconditions.checkNotNull(moq.getRange());
	    BigDecimal min = moq.getMinOrderQuantity();
	    BigDecimal range = moq.getRange();

	    OmOrderSpiltsExample sex = new OmOrderSpiltsExample();
	    sex.createCriteria().andHeaderIdEqualTo(order.getId());
	    List<OmOrderSpilts> lines = spliteMapper.selectByExample(sex);
	    BigDecimal totalWeight = BigDecimal.ZERO;
	    for (OmOrderSpilts line : lines) {
	      BigDecimal weight =
	          UnitConverter.convert(line.getNum(), line.getMaterialId(), MaterialUnit.TO);
	      totalWeight = totalWeight.add(weight);
	    }
	    return checkRange(min, range, totalWeight);

	  }

	  private boolean checkRange(BigDecimal min, BigDecimal range, BigDecimal value) {
	    BigDecimal num = value.divide(min, 0, RoundingMode.FLOOR);
	    if (num.intValue() < 1) {
	      return false;
	    }
	    BigDecimal delta = value.subtract(min.multiply(num));
	    if (delta.compareTo(range.multiply(num)) > 0) {
	      return false;
	    }
	    return true;
	  }
}
