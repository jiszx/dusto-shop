package com.hhnz.order.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.combination.mapper.CrmMaterialPackageHeaderMapper;
import com.hhnz.combination.mapper.CrmMaterialPackageLinesMapper;
import com.hhnz.combination.mapper.CrmMaterialPackageRebateMapper;
import com.hhnz.combination.model.CrmMaterialPackageHeader;
import com.hhnz.combination.model.CrmMaterialPackageHeaderExample;
import com.hhnz.combination.model.CrmMaterialPackageLines;
import com.hhnz.combination.model.CrmMaterialPackageLinesExample;
import com.hhnz.combination.model.CrmMaterialPackageRebate;
import com.hhnz.combination.model.CrmMaterialPackageRebateExample;
import com.hhnz.crm.mapper.TEmployeeMapper;
import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.mapper.UtilMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.crm.model.TMaterialBaseExample;
import com.hhnz.crm.model.UserStations;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.mapper.CMerchCustDistributionMapper;
import com.hhnz.customer.mapper.CMerchCustProductMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseV;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractExample;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.customer.model.CMerchCustDistributionExample;
import com.hhnz.customer.model.CMerchCustProduct;
import com.hhnz.customer.model.CMerchCustProductExample;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.customerInv.mapper.CMerchCustProudctInvMapper;
import com.hhnz.customerInv.model.CMerchCustProudctInv;
import com.hhnz.customerInv.model.CMerchCustProudctInvExample;
import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.dto.ResponseResult;
import com.hhnz.jco.business.order.InputDTO;
import com.hhnz.jco.business.order.OrderRFC;
import com.hhnz.order.dto.DistributeOrderDTO;
import com.hhnz.order.dto.RetailOrderImportDTO;
import com.hhnz.order.enu.OrderDeliveryType;
import com.hhnz.order.mapper.OmOrderDeliveredVMapper;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderHeadersAllVMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderLogisticsMapper;
import com.hhnz.order.mapper.OmOrderMakeSureMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.order.model.OmOrderDeliveredV;
import com.hhnz.order.model.OmOrderDeliveredVExample;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderHeadersAllExample;
import com.hhnz.order.model.OmOrderHeadersAllExample.Criteria;
import com.hhnz.order.model.OmOrderHeadersAllV;
import com.hhnz.order.model.OmOrderHeadersAllVExample;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.model.OmOrderLogistics;
import com.hhnz.order.model.OmOrderLogisticsExample;
import com.hhnz.order.model.OmOrderMakeSure;
import com.hhnz.order.model.OmOrderMakeSureExample;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.model.OrderDetail;
import com.hhnz.order.model.OrderLinesDetials;
import com.hhnz.order.model.OrderMaterial;
import com.hhnz.order.model.OrderPolicy;
import com.hhnz.order.model.OrderSearchModel;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.order.validator.OrderValidator;
import com.hhnz.organization.mapper.CrmSalesOrganizationMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.process.task.order.ReduceDepotTask;
import com.hhnz.process.task.order.ReduceMerchAccountTask;
import com.hhnz.process.task.order.ReduceRetailOrderDepoTask;
import com.hhnz.rmi.db.model.order.enu.OrderStatus;
import com.hhnz.rmi.db.model.order.enu.OrderType;
import com.hhnz.rmi.util.BigDecimalUtil;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;
import com.hhnz.util.ValidationResult;
import com.hhnz.util.ValidationUtil;
import com.hhnz.util.db.Page;
import com.hhnz.util.exception.HHNZException;
import com.hhnz.util.io.excel.util.excel.ExcelCallback;
import com.hhnz.util.io.excel.util.excel.ExcelCheckResult;
import com.hhnz.util.io.excel.util.excel.ExcelResult;
import com.hhnz.util.io.excel.util.excel.ExcelUtil;
import com.hhnz.util.io.excel.util.excel.ExcelUtil.ExcelType;

/**
 * 销售订单service
 * 
 * @author skevin
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	private static Logger logger = Logger
			.getLogger(OrderServiceImpl.class);
	@Resource
	private OmOrderHeadersAllMapper headmapper;
	@Resource
	private OmOrderLinesAllMapper linemapper;
	@Resource
	private OrderUtilMapper utilmapper;
	@Resource
	private UtilMapper crmutilMapper;
	@Resource
	private OmOrderSpiltsMapper spiltMapper;
	@Resource
	private CMerchCustAccountMapper accountmapper;
	@Resource
	private OmOrderLogisticsMapper logisticsMapper;
	@Resource
	private OmOrderHeadersAllVMapper orderHeadersAllVMapper;
	@Resource
	private OmOrderDeliveredVMapper orderDeliveredVMapper;
	@Resource
	private OrderUtilService orderUtilService;
	@Resource
	private CMerchCustDistributionMapper shipmapper;
	@Resource
	private CrmSalesOrganizationMapper organizationMapper;
	@Resource
	private TEmployeeMapper employeeMapper;
	@Resource
	private OrderValidator  validator;
	@Resource
	private CMerchCustBaseMapper custbaseMapper;
	@Resource
	private ICustomerService customerService;
	@Resource
    private OrderUtilService utlservice;
	@Resource
	private CMerchCustDistributionMapper distributionMapper;
	@Resource
	private IProcessService processService;
	@Resource
	private ReduceDepotTask reduceDepo;
	@Resource
	private ReduceMerchAccountTask reduceAccount;
	@Resource
	private  CrmMaterialPackageHeaderMapper combinatonheadermapper;
	@Resource
	private CrmMaterialPackageLinesMapper  combinationlinemapper;
	@Resource
	private CrmMaterialPackageRebateMapper combinationrebatemapper;
	@Resource
	private  TMaterialBaseMapper  materialmapper;
	@Resource
	private  CMerchCustContractMapper  contractmapper;
	@Resource
	private CustomerStockService  invsercice;
	@Resource
	private OmOrderMakeSureMapper  makeSureMapper;
	@Resource
	private CMerchCustContractMapper contractMapper;
	@Resource
	private IorganizationService orgservice;
	@Resource
	private OrderRFC orderRFC;
	@Resource
	private CMerchCustProductMapper productMapper;
	@Resource
	private CMerchCustProudctInvMapper proudctInvMapper;
	@Override
	public int addOrderHeader(OmOrderHeadersAll orderh) {
		return this.headmapper.insert(orderh);
	}

	@Override
	public int addOrderLine(OmOrderLinesAll line) {
		return this.linemapper.insert(line);

	}

	@Override
	public void updateOrderHeader(OmOrderHeadersAll orderh) {
		orderh=updateFreight(orderh, BigDecimalASME.divide(orderh.getFreight()));
		this.headmapper.updateByPrimaryKeySelective(orderh);

	}

	@Override
	public AjaxDTO findOrderList(Map<String, Object> params) {
		dealWithParam(params);
		List<OrderSearchModel> list = new ArrayList<OrderSearchModel>();
		list = this.utilmapper.findOrderList(params);
		int total = this.utilmapper.countOrderList(params);
		AjaxDTO dto = new AjaxDTO();
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	
	@Override
	public List<OrderSearchModel> findOrderListAll(Map<String, Object> params) {
		dealWithParam(params);
		List<OrderSearchModel> list = this.utilmapper.findOrderList(params);
		return list;
	}

	private void dealWithParam(Map<String, Object> params) {
		if (params.get("area") != null) {
			String areas = (String) params.get("area");
			if (StringUtils.isNotEmpty(areas)) {
				List<String> areaList = Arrays.asList(areas.split(","));
				params.put("area", areaList);
			} else {
				params.put("area", null);
			}
		}
		if (params.get("states") != null) {
			String states = (String) params.get("states");
			if (StringUtils.isNotEmpty(states)) {
				List<String> stats = Arrays.asList(states.split(","));
				params.put("states", stats);
			} else {
				params.put("states", null);
			}
		}
	}

	/*
	 * @Override public CrmSalesOrganization findOrderRegin(String stationorgid)
	 * { // TODO Auto-generated method stub return
	 * this.orgmapper.selectByPrimaryKey(stationorgid); }
	 */
	@Override
	public List<UserStations> getuserstations(String type, String orgid) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("orgid", orgid);
		List<UserStations> list = new ArrayList<UserStations>();
		list = this.crmutilMapper.getUserStations(params);
		return list;
	}

	@Override
	public int addSpiltsline(OmOrderSpilts spiltline) {
		// TODO Auto-generated method stub
		return this.spiltMapper.insert(spiltline);
	}

	@Override
	public String orderAudit(Long headerid, String states, String orderType) {
	    List<OmOrderHeadersAll> orders = getOrders(headerid, orderType);
	    String result = "200";
	    for(OmOrderHeadersAll order:orders){
	      OmOrderHeadersAll updateStatus = new OmOrderHeadersAll();
	      updateStatus.setId(order.getId());
	      updateStatus.setStates(states);
	      int r = headmapper.updateByPrimaryKeySelective(updateStatus);
	      if(r!=1){
	        result = "500";
	      }
	    }
	    return result;
	}

	@Override
	public OmOrderHeadersAll getOrderBYid(Long headerid) {
		// TODO Auto-generated method stub
		return this.headmapper.selectByPrimaryKey(headerid);
	}
	
	private BigDecimal getSumNum(List<OmOrderHeadersAll> orders){
	  BigDecimal totalNum = BigDecimal.ZERO;
	  for(OmOrderHeadersAll order:orders){
	    BigDecimal orderNum = getSumNumById(order.getId());
	    totalNum = totalNum.add(orderNum);
	  }
	  return totalNum;
	}
	
	private List<OmOrderHeadersAll> getOrders(Long id, String orderType){
	  List<OmOrderHeadersAll> orders = new ArrayList<>();
	  if("1".equals(orderType)){
	    return getOrdersByBatchNum(id);
	  }else{
	    orders.add(headmapper.selectByPrimaryKey(id));
        return orders;
	  }
	}
	
	private List<OmOrderHeadersAll> getOrdersByBatchNum(Long batchNum){
	  OmOrderHeadersAllExample ex = new OmOrderHeadersAllExample();
	  ex.createCriteria().andAttribute8EqualTo(String.valueOf(batchNum));
	  return headmapper.selectByExample(ex);
	}
	
	@Override
	public Long findShipid(Long merchid, Long shipid){
	  CMerchCustBase merch = custbaseMapper.selectByPrimaryKey(merchid);
	  if("5".equals(merch.getCustType())){
	    CMerchCustDistributionExample ex = new CMerchCustDistributionExample();
	    ex.createCriteria().andMerchCustIdEqualTo(merch.getPid());
	    List<CMerchCustDistribution> ships = shipmapper.selectByExample(ex);
	    return ships.get(0).getId();
	  }
	  return shipid;
	}
	
	@Override
	public boolean isDistributeOrder(Long merchid){
	  CMerchCustBase merch = custbaseMapper.selectByPrimaryKey(merchid);
      if("5".equals(merch.getCustType())){
        return true;
      }
      return false;
	}
	
	@Override
	public Long distriButeMerchid(Long merchid){
	  CMerchCustBase merch = custbaseMapper.selectByPrimaryKey(merchid);
	  if("5".equals(merch.getCustType()) && merch.getPid()!=null){ // 零售客户
	    return merch.getPid();
	  }
	  if("3".equals(merch.getCustType()) && merch.getPid()!=null){ // ka
	    return merch.getPid();
	  }
	  return merchid;
	}

	@Override
	public String addOrder(OmOrderHeadersAll orderh,
			List<OmOrderLinesAll> orderlines) {
		//添加生意模式
		CMerchCustContractExample ex = new CMerchCustContractExample();
		CMerchCustContractExample.Criteria ext = ex.createCriteria();
		ext.andMerchCustIdEqualTo(orderh.getMerchCustId());
		ext.andStatesEqualTo("4");
		List<CMerchCustContract> contracts = this.contractmapper.selectByExample(ex);
		if(contracts.size()>0 && contracts !=null){
			CMerchCustContract contract = contracts.get(0);
			orderh.setAttribute5(contract.getId().toString());
		}
		int i = this.headmapper.insert(orderh);
		if (i != 1) {
			return "0";
		}
		BigDecimal hbAmt = BigDecimal.ZERO;
		BigDecimal orderAmt = BigDecimal.ZERO;
		BigDecimal amt = BigDecimal.ZERO;
		BigDecimal freight = orderh.getDeliveryType().equals(OrderDeliveryType.NOMAIL.getCode()) ? orderh.getFreight().divide(new BigDecimal(orderlines.size()),BigDecimal.ROUND_HALF_UP):BigDecimal.ZERO; 
		for (OmOrderLinesAll line : orderlines) {
			if (line.getId() != 0) {
				line.setCreateOid(orderh.getCreateOid());
				line.setCreateTs(new Date());
				line.setHeaderId(orderh.getId());
				line.setStates(orderh.getStates());
				line.setId(null);
				line.setAmt(BigDecimalASME.multiply(line.getAmt()));
				line.setHbAmt(BigDecimalASME.multiply(line.getHbAmt()));
				line.setOrderAmt(BigDecimalASME.multiply(line.getOrderAmt()));
				line.setDiscountAmt(BigDecimalASME.multiply(line.getDiscountAmt()));
				line.setPrice(BigDecimalASME.multiply(line.getPrice()));
				line.setOrderPrice(BigDecimalASME.multiply(line.getOrderPrice()));
				line.setAttribute12("0");
				line.setFreight(freight);
				this.linemapper.insert(line);
				hbAmt = hbAmt.add(line.getHbAmt());
				orderAmt = orderAmt.add(line.getOrderAmt());
				amt = amt.add(line.getAmt());
			}
			/*else {
				orderh.setOrderAmt(BigDecimalASME.multiply(line.getOrderAmt()));
				orderh.setAmt(BigDecimalASME.multiply(line.getAmt()));
				orderh.setDiscountAmt(BigDecimalASME.multiply(line
						.getDiscountAmt()));
				this.headmapper.updateByPrimaryKeySelective(orderh);
			}*/
		}
		//获取金额
		orderh.setOrderAmt(orderAmt);
		orderh.setAmt(amt);
		orderh.setDiscountAmt(hbAmt);
		orderh.setHbAmt(hbAmt);
		this.headmapper.updateByPrimaryKeySelective(orderh);
		try{			
			spiltline(orderlines, orderh);
			return "1";
		}catch(Exception e){
			logger.error("添加订单失败："+e.getMessage());
			return "0";
		}
	}

	/**
	 * 拆分订单行
	 * 
	 * @param lines
	 * @return
	 */
	public void spiltline(List<OmOrderLinesAll> lines, OmOrderHeadersAll orderh) {
		if (lines.size() > 0 && lines != null) {
			int i = 0;
			for (OmOrderLinesAll line : lines) {
				if (line.getId() == 0 || line.getId() == null) {
					continue;
				}
				if("1".equals(line.getAttribute11())){
					//套餐
					addCombinationSpiltline(line,orderh);
					continue;
				}
				int size =0;
				if(line.getNum().compareTo(BigDecimal.ZERO)>0) {
					size++;
				}
				if (line.getPolicyLineId() != null
						&& line.getPolicyHeaderId() != null
						&& StringUtils.equals("1", line.getPolicyVerfication())) {
					size ++;
				}
				if (line.getHbNum()!=null && line.getHbNum().compareTo(new BigDecimal(0)) >0) {
					size++;
				}
				BigDecimal freight = line.getFreight().divide(new BigDecimal(size));
				i = i + 10;
				if(line.getNum().compareTo(BigDecimal.ZERO)>0){
					//现金购买数量不等于0
					OmOrderSpilts spiltline = new OmOrderSpilts();
					spiltline.setOrderitemSapNo(String.valueOf(i));
					spiltline.setHeaderId(orderh.getId());
					spiltline.setLineId(line.getId());
					spiltline.setMerchCustId(orderh.getMerchCustId());
					spiltline.setCreateTs(new Date());
					spiltline.setCreateOid(orderh.getCreateOid());
					spiltline.setPrice(line.getOrderPrice());
					spiltline.setNum(line.getNum());
					spiltline.setOrganizationId(orderh.getOrganizationId());
					spiltline.setType("1");
					spiltline.setAmt(line.getOrderAmt());
					spiltline.setMaterialId(line.getMaterialId());
					spiltline.setStates("1");
					spiltline.setShipTo(orderh.getShipId());
					spiltline.setCreateOid(orderh.getCreateOid());
					spiltline.setCreateTs(new Date());
					spiltline.setStationId(orderh.getStationId());
					spiltline.setReginId(orderh.getRegionId());
					spiltline.setProvId(orderh.getProviId());
					spiltline.setSalesrepId(orderh.getSalesrepId());
					spiltline.setFreight(freight);
					this.spiltMapper.insert(spiltline);
				}
				if (line.getPolicyLineId() != null
						&& line.getPolicyHeaderId() != null
						&& StringUtils.equals("1", line.getPolicyVerfication())) {
					// 搭赠
					i = i + 10;
					OmOrderSpilts discountlines = new OmOrderSpilts();
					String materialid = line.getPolicyDiscount();
					discountlines.setHeaderId(orderh.getId());
					discountlines.setOrderitemSapNo(String.valueOf(i));
					discountlines.setLineId(line.getId());
					discountlines.setPrice(new BigDecimal(0));
					// discountlines.setNum(line.getPolicyDiscountIntensity());
					discountlines.setAmt(new BigDecimal(0));
					discountlines.setMerchCustId(orderh.getMerchCustId());
					discountlines.setMaterialId(materialid);
					discountlines.setStates("1");
					discountlines.setOrganizationId(orderh.getOrganizationId());
					discountlines.setNum(new BigDecimal(line.getPolicyDiscountIntensity()));
					discountlines.setType("3");
					discountlines.setShipTo(orderh.getShipId());
					discountlines.setCreateOid(orderh.getCreateOid());
					discountlines.setCreateTs(new Date());
					discountlines.setStationId(orderh.getStationId());
					discountlines.setReginId(orderh.getRegionId());
					discountlines.setProvId(orderh.getProviId());
					discountlines.setSalesrepId(orderh.getSalesrepId());
					discountlines.setFreight(freight);
					this.spiltMapper.insert(discountlines);
				}
				if (line.getHbNum()!=null && line.getHbNum().compareTo(new BigDecimal(0)) >0) {
					// 货补
					i = i + 10;
					OmOrderSpilts discountlines = new OmOrderSpilts();
					discountlines.setHeaderId(orderh.getId());
					discountlines.setOrderitemSapNo(String.valueOf(i));
					discountlines.setLineId(line.getId());
					discountlines.setPrice(new BigDecimal(0));
					discountlines.setAmt(new BigDecimal(0));
					discountlines.setNum(line.getHbNum());
					discountlines.setMerchCustId(orderh.getMerchCustId());
					discountlines.setMaterialId(line.getMaterialId());
					discountlines.setStates("1");
					discountlines.setOrganizationId(orderh.getOrganizationId());
					discountlines.setType("2");
					discountlines.setShipTo(orderh.getShipId());
					discountlines.setCreateOid(orderh.getCreateOid());
					discountlines.setCreateTs(new Date());
					discountlines.setStationId(orderh.getStationId());
					discountlines.setReginId(orderh.getRegionId());
					discountlines.setProvId(orderh.getProviId());
					discountlines.setSalesrepId(orderh.getSalesrepId());
					discountlines.setFreight(freight);
					this.spiltMapper.insert(discountlines);
				}
			}
		}
	}

	public void spiltline(OmOrderLinesAll line, OmOrderHeadersAll orderh) {
		if (line != null && orderh != null) {
			//获取最大订单行号
			if("1".equals(line.getAttribute11())){
				addCombinationSpiltline(line,orderh);
				return ;
			}
			int  i =0;
			i= this.utilmapper.getSpiltOrderNo(orderh.getId());
			i =i+10;
			if(line.getNum().compareTo(BigDecimal.ZERO)>0){				
				OmOrderSpilts spiltline = new OmOrderSpilts();
				spiltline.setOrderitemSapNo(String.valueOf(i));
				spiltline.setHeaderId(orderh.getId());
				spiltline.setLineId(line.getId());
				spiltline.setMerchCustId(orderh.getMerchCustId());
				spiltline.setCreateTs(new Date());
				spiltline.setCreateOid(orderh.getCreateOid());
				spiltline.setPrice(line.getOrderPrice());
				spiltline.setNum(line.getNum());
				spiltline.setOrganizationId(orderh.getOrganizationId());
				spiltline.setType("1");
				spiltline.setAmt(line.getOrderAmt());
				spiltline.setMaterialId(line.getMaterialId());
				spiltline.setStates("1");
				spiltline.setShipTo(orderh.getShipId());
				spiltline.setCreateOid(orderh.getCreateOid());
				spiltline.setCreateTs(new Date());
				spiltline.setStationId(orderh.getStationId());
				spiltline.setReginId(orderh.getRegionId());
				spiltline.setProvId(orderh.getProviId());
				spiltline.setSalesrepId(orderh.getSalesrepId());
				this.spiltMapper.insert(spiltline);
			}
			if (line.getPolicyLineId() != null
					&& line.getPolicyHeaderId() != null
					&& StringUtils.equals("1", line.getPolicyVerfication())) {
				// 搭赠
				i = i + 10;
				OmOrderSpilts discountlines = new OmOrderSpilts();
				String materialid = line.getPolicyDiscount();
				discountlines.setHeaderId(orderh.getId());
				discountlines.setOrderitemSapNo(String.valueOf(i));
				discountlines.setLineId(line.getId());
				discountlines.setPrice(new BigDecimal(0));
				// discountlines.setNum(line.getPolicyDiscountIntensity());
				discountlines.setAmt(new BigDecimal(0));
				discountlines.setMerchCustId(orderh.getMerchCustId());
				discountlines.setMaterialId(materialid);
				discountlines.setStates("1");
				discountlines.setOrganizationId(orderh.getOrganizationId());
				discountlines.setType("3");
				discountlines.setShipTo(orderh.getShipId());
				discountlines.setCreateOid(orderh.getCreateOid());
				discountlines.setCreateTs(new Date());
				discountlines.setStationId(orderh.getStationId());
				discountlines.setReginId(orderh.getRegionId());
				discountlines.setProvId(orderh.getProviId());
				discountlines.setSalesrepId(orderh.getSalesrepId());
				this.spiltMapper.insert(discountlines);
			}
			if (line.getHbNum()!=null && line.getHbNum().compareTo(new BigDecimal(0)) == 1) {
				// 货补
				i = i + 10;
				OmOrderSpilts discountlines = new OmOrderSpilts();
				discountlines.setHeaderId(orderh.getId());
				discountlines.setOrderitemSapNo(String.valueOf(i));
				discountlines.setLineId(line.getId());
				discountlines.setPrice(new BigDecimal(0));
				discountlines.setAmt(new BigDecimal(0));
				discountlines.setNum(line.getHbNum());
				discountlines.setMerchCustId(orderh.getMerchCustId());
				discountlines.setMaterialId(line.getMaterialId());
				discountlines.setStates("1");
				discountlines.setOrganizationId(orderh.getOrganizationId());
				discountlines.setType("2");
				discountlines.setShipTo(orderh.getShipId());
				discountlines.setCreateOid(orderh.getCreateOid());
				discountlines.setCreateTs(new Date());
				discountlines.setStationId(orderh.getStationId());
				discountlines.setReginId(orderh.getRegionId());
				discountlines.setProvId(orderh.getProviId());
				discountlines.setSalesrepId(orderh.getSalesrepId());
				this.spiltMapper.insert(discountlines);
			}
		}
	}

	// 修改订单删除订单行
	@Override
	public int DeleteLine(Long id) {
		// 删除拆分数据
	    OmOrderLinesAll line = linemapper.selectByPrimaryKey(id);
		OmOrderSpiltsExample ex = new OmOrderSpiltsExample();
		ex.createCriteria().andLineIdEqualTo(id);
		this.spiltMapper.deleteByExample(ex);
		// 删除行数据
		int result = this.linemapper.deleteByPrimaryKey(id);
		updateOrderHeaderAmt(line.getHeaderId());
		return result;
	}

	// 修改订单增加行
	@Override
	public int editAddLine(OmOrderLinesAll lines) {
		this.linemapper.insert(lines);
		//获取订单头
		OmOrderHeadersAll header = this.headmapper.selectByPrimaryKey(lines
				.getHeaderId());
		updateOrderHeaderAmt(lines.getHeaderId());
		//新增拆分数据
		try{			
			spiltline(lines, header);
			return 1;
		}catch(Exception e){
			logger.error(lines.getHeaderId()+"新增订单行失败");
			return 0;
		}
	}

	// 修改订单修改行
	@Override
	public int editEditLine(OmOrderLinesAll lines) {
		this.linemapper.updateByPrimaryKeySelective(lines);
		OmOrderLinesAll line = this.linemapper
				.selectByPrimaryKey(lines.getId());
		OmOrderHeadersAll header = this.headmapper.selectByPrimaryKey(line
				.getHeaderId());
		// 删除拆分数据
		OmOrderSpiltsExample ex = new OmOrderSpiltsExample();
		ex.createCriteria().andLineIdEqualTo(line.getId());
		this.spiltMapper.deleteByExample(ex);
		try{				
			// 新增拆分行数据
			spiltline(line, header);
			//修改订单头金额
			updateOrderHeaderAmt(line.getHeaderId());
			return 1;
		}catch(Exception e){
			logger.error(lines.getHeaderId()+"修改订单行失败");
			return 0;
		}
	}

	// 删除销售订单
	@Override
	public int delOrder(Long id) {
		OmOrderHeadersAll header = this.headmapper.selectByPrimaryKey(id);
		if (!header.getStates().equals("1") && !header.getStates().equals("4")) {
			return 0;
		}
		// 删除头表
		this.headmapper.deleteByPrimaryKey(id);

		// 删除拆分表
		OmOrderSpiltsExample sex = new OmOrderSpiltsExample();
		sex.createCriteria().andHeaderIdEqualTo(id);
		this.spiltMapper.deleteByExample(sex);

		// 删除行表
		OmOrderLinesAllExample ex = new OmOrderLinesAllExample();
		ex.createCriteria().andHeaderIdEqualTo(id);
		return this.linemapper.deleteByExample(ex);
	}
	
	private int updateOrderHeaderAmt(Long id){
	  OmOrderHeadersAll order = headmapper.selectByPrimaryKey(id);
	  OmOrderLinesAllExample example = new OmOrderLinesAllExample();
	  example.createCriteria().andHeaderIdEqualTo(id);
	  List<OmOrderLinesAll> lines = linemapper.selectByExample(example);
	  if(lines.isEmpty()){
	    order.setAmt(BigDecimal.ZERO);
	    order.setOrderAmt(BigDecimal.ZERO);
	    return headmapper.updateByPrimaryKey(order);
	  }
	  BigDecimal amt = BigDecimal.ZERO;
	  BigDecimal orderAmt = BigDecimal.ZERO;
	  BigDecimal hbAmt = BigDecimal.ZERO;
	  for(OmOrderLinesAll line:lines){
	    amt = amt.add(line.getAmt());
	    hbAmt = hbAmt.add(line.getHbAmt());
	    orderAmt = orderAmt.add(amt.add(hbAmt));
	  }
	  order.setAmt(amt);
	  order.setOrderAmt(orderAmt);
	  order.setHbAmt(hbAmt);
	  order.setDiscountAmt(hbAmt);
	  return headmapper.updateByPrimaryKey(order);
	}

	// 更新订单的扣减金额
	@Override
	public int updateOrderAmt(Long id) {
		OmOrderHeadersAll header = this.headmapper.selectByPrimaryKey(id);
		// 获取订单各种金额
		OrderLinesDetials orderamt = this.utilmapper.getOrderLinesAllAmt(id);
		
		CMerchCustAccountExample ex = new CMerchCustAccountExample();
		ex.createCriteria().andMerchCustIdEqualTo(header.getMerchCustId());
		ex.createCriteria()
				.andOrganizationIdEqualTo(header.getOrganizationId());
		// 客户账户余额
		CMerchCustAccount custaccount = this.accountmapper.selectByExample(ex).get(0);
		
		if(orderamt==null || orderamt.getOrderAmt()==null){
		  orderamt = new OrderLinesDetials();
		  orderamt.setAmt(BigDecimal.ZERO);
		  orderamt.setOrderAmt(BigDecimal.ZERO);
		  orderamt.setDiscountAmt(BigDecimal.ZERO);
		  orderamt.setHbAmt(BigDecimal.ZERO);
		}
		
		if (orderamt.getOrderAmt().compareTo(custaccount.getCashAmt()) == 1) {
			//订单金额大于客户现金可用余额
			header.setCashAmt(BigDecimalASME.multiply(custaccount.getCashAmt()));
			header.setCreditAmt(BigDecimalASME.multiply(orderamt.getOrderAmt()).subtract(BigDecimalASME.multiply(custaccount.getCashAmt())));
		} else {
			header.setCashAmt(BigDecimalASME.multiply(orderamt.getOrderAmt()));
			header.setCreditAmt(BigDecimal.ZERO);
		}
		header.setOrderAmt(BigDecimalASME.multiply(orderamt.getOrderAmt()));
		header.setAmt(BigDecimalASME.multiply(orderamt.getAmt()));
		header.setDiscountAmt(BigDecimalASME.multiply(orderamt.getDiscountAmt()));
		header.setHbAmt(BigDecimalASME.multiply(orderamt.getHbAmt()));
		return this.headmapper.updateByPrimaryKeySelective(header);
	}

	@Override
	public void save(List<OmOrderLogistics> list) {
		for (OmOrderLogistics ol : list) {
			OmOrderLogisticsExample ex = new OmOrderLogisticsExample();
			ex.createCriteria().andSapDeliveryNoEqualTo(ol.getSapDeliveryNo());
			List<OmOrderLogistics> exists = logisticsMapper.selectByExample(ex);
			if (exists == null || exists.size() == 0) {
				ol.setCreateTs(new Date());
				logisticsMapper.insertSelective(ol);
			} else {
				OmOrderLogistics exist = exists.get(0);
				ol.setId(exist.getId());
				logisticsMapper.updateByPrimaryKeySelective(ol);
			}
		}

	}
	
	@SuppressWarnings("unused")
	private String add(String leftNum, String rightNum){
	  if(StringUtils.isEmpty(leftNum)){
	    leftNum = "0";
	  }
	  if(StringUtils.isEmpty(rightNum)){
	    rightNum = "0";
	  }
	  return new BigDecimal(leftNum).add(new BigDecimal(rightNum)).toPlainString();
	}
	
	@SuppressWarnings("unused")
	private BigDecimal add(BigDecimal left, BigDecimal right){
	  if(left==null){
	    left = BigDecimal.ZERO;
	  }
	  if(right==null){
	    right = BigDecimal.ZERO;
	  }
	  return left.add(right);
	}

	@Override
	public void updateMakesure(List<OmOrderSpilts> orders,Long orderId,TEmployee user) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		if (orders == null || orders.isEmpty()) {
			return;
		}
		OmOrderHeadersAll order = this.headmapper.selectByPrimaryKey(orderId);
		//订单确认时间，确认人
		order.setAttribute4(d.format(new Date()));
		order.setAttribute3(user.getName());
		this.headmapper.updateByPrimaryKeySelective(order);
		if("7".equals(order.getOrderType())){
			//获取对应的调拨单数据
			OmOrderHeadersAll header = this.headmapper.selectByPrimaryKey(Long.parseLong(order.getAttribute13()));
			header.setStates("8");
			//调拨单确认时间和操作人
			header.setAttribute4(d.format(new Date()));
			header.setAttribute3(user.getName());
			this.headmapper.updateByPrimaryKeySelective(header);
			for (OmOrderSpilts line : orders) {
				//处理调拨单转换订单发货数量
				OmOrderSpilts spilt = this.spiltMapper.selectByPrimaryKey(line.getId());
				BigDecimal  deliveredNum = BigDecimal.ZERO;
				deliveredNum = new BigDecimal(line.getDeliveredNum()).multiply(new BigDecimal(line.getAttribute1()));
				spilt.setDeliveredNum(deliveredNum.toString());//确认发货数量
				spilt.setAttribute3(line.getAttribute3());//系统发货数量
				spilt.setAttribute4(line.getAttribute4());//备注
				this.spiltMapper.updateByPrimaryKeySelective(spilt);
				
				//处理对应调拨单数据
				OmOrderSpiltsExample  ex = new OmOrderSpiltsExample();
				OmOrderSpiltsExample.Criteria ext = ex.createCriteria();
				ext.andHeaderIdEqualTo(header.getId());
				ext.andMaterialIdEqualTo(spilt.getMaterialId());
				ext.andOrderitemSapNoEqualTo(spilt.getOrderitemSapNo());
				
				List<OmOrderSpilts> spilts = this.spiltMapper.selectByExample(ex);
				OmOrderSpilts  spilt2= spilts.get(0);
				spilt2.setDeliveredNum(deliveredNum.toString());
				this.spiltMapper.updateByPrimaryKeySelective(spilt2);
				
				//更新调拨单对应客户库存
			    this.invsercice.doAddProductInv(header,spilt2,sf.format(new Date()),user);
			}
			updateOrderLineDeliveredNum(header.getId());
		}else{
			for (OmOrderSpilts line : orders) {
				OmOrderSpilts spilt = this.spiltMapper.selectByPrimaryKey(line.getId());
				BigDecimal  deliveredNum = BigDecimal.ZERO;
				deliveredNum = new BigDecimal(line.getDeliveredNum()).multiply(new BigDecimal(line.getAttribute1()));
				spilt.setDeliveredNum(deliveredNum.toString());//确认发货数量
				spilt.setAttribute3(line.getAttribute3());//系统发货数量
				spilt.setAttribute4(line.getAttribute4());//备注
				this.spiltMapper.updateByPrimaryKeySelective(spilt);
			}
		}
		//更新订单行上面的数据
		updateOrderLineDeliveredNum(orderId);
	}
	
	/**
	 * 调拨单确认收货操作
	 */
	@Override
	public void allocationSubmitMakeSure(List<OmOrderSpilts> orders,Long orderId,TEmployee user) {
		if (orders == null || orders.isEmpty()) {
			return;
		}
		OmOrderHeadersAll header = this.headmapper.selectByPrimaryKey(orderId);
		header.setStates("8");
		//调拨单确认时间和操作人
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		header.setAttribute4(d.format(new Date()));
		header.setAttribute3(user.getName());
		this.headmapper.updateByPrimaryKeySelective(header);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		for (OmOrderSpilts line : orders) {
			OmOrderSpilts spilt = this.spiltMapper.selectByPrimaryKey(line.getId());
			BigDecimal  deliveredNum = BigDecimal.ZERO;
			deliveredNum = new BigDecimal(line.getDeliveredNum()).multiply(new BigDecimal(line.getAttribute1()));
			spilt.setDeliveredNum(deliveredNum.toString());//确认发货数量
			spilt.setAttribute3(line.getAttribute3());//系统发货数量
			spilt.setAttribute4(line.getAttribute4());//备注
			this.spiltMapper.updateByPrimaryKeySelective(spilt);
		    this.invsercice.doAddProductInv(header,spilt,sf.format(new Date()),user);
		}
		//更新订单行上面的数据
		updateOrderLineDeliveredNum(orderId);
		
	}
	private void updateOrderLineDeliveredNum(Long orderId){
		this.utilmapper.updateLineDeliveredNum(orderId);
	};
	private void changeOrderAmt(OmOrderHeadersAll order) {
		order.setAmt(BigDecimalASME.divide(order.getAmt()));
		order.setCashAmt(BigDecimalASME.divide(order.getCashAmt()));
		order.setCreditAmt(BigDecimalASME.divide(order.getCreditAmt()));
		order.setDiscountAmt(BigDecimalASME.divide(order.getDiscountAmt()));
		order.setHbAmt(BigDecimalASME.divide(order.getHbAmt()));
		order.setOrderAmt(BigDecimalASME.divide(order.getOrderAmt()));
	}

	@Override
	public OrderDetail orderDelivery(Long id) {
		OrderDetail detail = orderDetail(id);
		List<OrderLinesDetials> lines = utilmapper.orderLineDetails(id);
		detail.setLines(lines);
		return detail;
	}
   
	
	public String deliveryNum(OrderLinesDetials line, List<OmOrderSpilts> deliverys) {
		if (deliverys == null) {
			return "0";
		}
		int result = 0;
		for (OmOrderSpilts delivery : deliverys) {
			if (delivery.getLineId().equals(line.getId()) && delivery.getType().equals(line.getType())) {
				result += delivery.getNum().intValue();
			}
		}
		return String.valueOf(result);
	}
  @Override
  public int updateFactory(List<OrderLinesDetials> lines){
    int result = 0;
    for(OrderLinesDetials line:lines){
      if(line.getSpliteid()==null || line.getSpliteid().equals(0L) || StringUtils.isEmpty(line.getAttribute2())){
        continue;
      }
      OmOrderSpilts splits = spiltMapper.selectByPrimaryKey(line.getSpliteid());
      splits.setAttribute2(line.getAttribute2());
      spiltMapper.updateByPrimaryKey(splits);
      result++;
    }
    return result;
  }

	@Override
	public OrderDetail orderDetail(Long id) {
		OrderDetail detail = new OrderDetail();
		OmOrderHeadersAll order = headmapper.selectByPrimaryKey(id);
		changeOrderAmt(order);

		@SuppressWarnings("unchecked")
		List<OrderLinesDetials> lines = orderUtilService.getlinedata(id)
				.getRows();
		CMerchCustDistribution distribution = shipmapper
				.selectByPrimaryKey(order.getShipId());
		String custname = utilmapper.getcustnameByorderId(id);
		String orgname = utilmapper.getOrgnameByorderId(id);
		CrmSalesOrganization region = organizationMapper
				.selectByPrimaryKey(order.getRegionId());
		CrmSalesOrganization prov = organizationMapper.selectByPrimaryKey(order
				.getProviId());
		TEmployee saleMan = employeeMapper.selectByPrimaryKey(order
				.getSalesrepId());
		CMerchCustBase ship = custbaseMapper.selectByPrimaryKey(order.getShipId());
		detail.setCustid(order.getMerchCustId());
		detail.setOrderid(id);
		detail.setRemark(order.getRemark());
		detail.setStates(order.getStates());
		detail.setLines(lines);
		detail.setDistribution(distribution);
		detail.setOrgname(orgname);
		detail.setRegion(region.getName());
		detail.setProv(prov.getName());
		detail.setCustname(custname);
		detail.setSaleman(saleMan.getName());
		detail.setHeader(order);
		detail.setShip(ship);
		detail.setAmtNum(BigDecimal.ZERO);
		detail.setHbNum(BigDecimal.ZERO);
		detail.setHandselNum(BigDecimal.ZERO);
		for (OrderLinesDetials line : lines) {
			if (line.getId().equals(0L)) {
				continue;
			}

			/*detail.setAmtNum(detail.getAmtNum() + parse(line.getNum()));
			detail.setHbNum(detail.getHbNum() + line.getHbNum().intValue());
			detail.setHandselNum(detail.getHandselNum()
					+ parse(line.getAttribute2()));
			if(line.getPolicyDiscount()!=null && line.getPolicyDiscount().length()>2){
			  detail.setHandselNum(detail.getHandselNum()+parse(line.getPolicyDiscountIntensity()));
			}*/
			detail.setAmtNum(detail.getAmtNum().add(line.getNum()));
			detail.setHbNum(detail.getHbNum().add(line.getHbNum()));
			detail.setHandselNum(detail.getHandselNum().add(new BigDecimal(line.getAttribute2())));
			if(line.getPolicyDiscount()!=null && line.getPolicyDiscount().length()>2){
			  detail.setHandselNum(detail.getHandselNum().add(new BigDecimal(line.getPolicyDiscountIntensity())));
			}
			Map<String, Object> materialParam = new HashMap<>();
			materialParam.put("merchid", order.getMerchCustId());
			materialParam.put("orgid", order.getOrganizationId());
			materialParam.put("materialid", line.getMaterialId());
			// 产品信息
            List<OrderMaterial> products = utilmapper
                    .getCustomerMaterial(materialParam);
            if (!products.isEmpty()) {
                line.setMaterial(products.get(0));
            }
            
            // 销售政策
            if(StringUtils.isEmpty(line.getPolicyDiscount()) || line.getPolicyHeaderId()==null){
              continue;
            }
            Map<String, Object> param = new HashMap<>();
            param.put("policyid", line.getPolicyLineId());
            List<OrderPolicy> policys = utilmapper.getOrderPolicy(param);
            if (!policys.isEmpty()) {
                OrderPolicy policy = policys.get(0);
                List<OrderMaterial> materials = utilmapper
                        .getCustomerMaterial(materialParam);
                if (!materials.isEmpty()) {
                    policy.setMaterial(materials.get(0));
                }
                line.setPolicy(policys.get(0));
            }

		}
		return detail;
	}

	@Override
	public OmOrderHeadersAllV getOrderDetailInfoById(Long orderId) {
		OmOrderHeadersAllVExample ex = new OmOrderHeadersAllVExample();
		ex.createCriteria().andIdEqualTo(orderId);
		List<OmOrderHeadersAllV> orderList = orderHeadersAllVMapper
				.selectByExample(ex);
		if (orderList != null && orderList.size() > 0) {
			OmOrderHeadersAllV order = orderList.get(0);
			return order;
		}
		return null;
	}

	@Override
	public List<OmOrderDeliveredV> getOrderDeliveryItemsByOrderId(Long orderId) {
		OmOrderDeliveredVExample ex = new OmOrderDeliveredVExample();
		ex.createCriteria().andOrderIdEqualTo(orderId);
		return orderDeliveredVMapper.selectByExample(ex);
	}

	@Override
	public OmOrderHeadersAll orderSaveAgain(Long id, Long userid,UserStations station) {
		OmOrderHeadersAll header = this.headmapper.selectByPrimaryKey(id);
		header.setId(null);
		header.setStates("1");
		header.setCreateTs(new Date());
		header.setCreateOid(userid);
		header.setUpdateOid(null);
		header.setUpdateTs(null);
		header.setSalesrepId(userid);
		header.setStationId(station==null?header.getStationId():station.getStationid());
		header.setAttribute1(" ");
		header.setReturnCashAmt(BigDecimal.ZERO);
		header.setReturnCreditAmt(BigDecimal.ZERO);
		header.setReturnHbAmt(BigDecimal.ZERO);
		OmOrderLinesAllExample ex = new OmOrderLinesAllExample();
		ex.createCriteria().andHeaderIdEqualTo(id);
		List<OmOrderLinesAll> lines = this.linemapper.selectByExample(ex);
		//普通订单新数据验证可用库存
		if(header.getOrderType().equals("1")){
			String validate = this.validator.validateOrderAdd(header,lines);
			if(!StringUtils.equals("validated", validate)){
				return null;
			}
		}
		this.headmapper.insert(header);
		if (lines.size() > 0 && lines != null) {
			for (OmOrderLinesAll line : lines) {
				line.setHeaderId(header.getId());
				line.setCreateTs(new Date());
				line.setCreateOid(userid);
				line.setUpdateOid(null);
				line.setUpdateTs(null);
				line.setStates("1");
				line.setId(null);
				line.setDeliveredNum(BigDecimal.ZERO);
				line.setRetrunHbNum(BigDecimal.ZERO);
				line.setReturnNum(BigDecimal.ZERO);
				line.setReturnPolicyNum(BigDecimal.ZERO);
				this.linemapper.insert(line);
				spiltline(line, header);
			}
		}
		return header;
	}

	@Override
	public List<OmOrderSpilts> findOrderSplitsByOrderId(Long id) {
		OmOrderSpiltsExample ex = new OmOrderSpiltsExample();
		ex.createCriteria().andHeaderIdEqualTo(id);
		return spiltMapper.selectByExample(ex);
	}

	@Override
	public List<OrderLinesDetials> findOrderDeliveryDetails(String id) {
		List<OrderLinesDetials> orderLinesDetialList = this.utilmapper
				.findOrderDeliveryDetails(id);
		return orderLinesDetialList;
	}

	// 确认收货
	@Override
	public int receiveGoods(OrderLinesDetials[] orderLineDetialList) {

		int rows = 0;
		if ((orderLineDetialList != null) && (orderLineDetialList.length > 0)) {
			for (OrderLinesDetials orderLine : orderLineDetialList) {
				rows += this.linemapper.receiveGoods(orderLine);
			}
			OmOrderLinesAll orderLine = this.linemapper
					.selectByPrimaryKey(orderLineDetialList[0].getId());
			if (rows == orderLineDetialList.length) {
				OmOrderHeadersAll orderHeader = headmapper
						.selectByPrimaryKey(orderLine.getHeaderId());
				orderHeader.setStates("8");
				headmapper.updateByPrimaryKeySelective(orderHeader);
			}
		}
		return rows;
	}

	@Override
	public AjaxDTO orderLineDetails(Long id) {
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		List<OrderLinesDetials> list = this.utilmapper.orderLineDetails(id);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	@Override
	public OmOrderLogistics findLogisticsByDeliveryNo(String deliveryNo) {
		OmOrderLogisticsExample ex = new OmOrderLogisticsExample();
		ex.createCriteria().andSapDeliveryNoEqualTo(deliveryNo);
		List<OmOrderLogistics> exists = logisticsMapper.selectByExample(ex);
		return (exists == null || exists.size() == 0)?null:exists.get(0);
	}
	
	@Override
    public ResponseResult importOrders(String filePath, Long merchid, Long stationid, TEmployee user)
        throws EncryptedDocumentException, InvalidFormatException, IOException {
	  ResponseResult resp = validateExcel(filePath, merchid, stationid, user);
	  if(resp.getErrorCode()!=200){
	    return resp;
	  }
	  CMerchCustBase merch = custbaseMapper.selectByPrimaryKey(merchid);
	  File excelFile = new File(filePath); // 创建文件对象
      FileInputStream is = new FileInputStream(excelFile); // 文件流
      Workbook workbook = WorkbookFactory.create(is); 
      Sheet sheet = workbook.getSheetAt(0);
      int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
      // 遍历每一行
      for (int r = 1; r < rowCount; r++) {
        Row row = sheet.getRow(r);
        String materialId = getValue(row.getCell(0), false);
        String num = getValue(row.getCell(3), true);
        String retailId = getValue(row.getCell(4), false);

        List<OmOrderLinesAll> lines = new ArrayList<>();
        AjaxDTO product = orderUtilService.getCustomerProduct(merch.getId(), merch.getOrganizationId(), "1", materialId,null);
        OmOrderLinesAll line = new OmOrderLinesAll();
        line.setId(1L);
        line.setMaterialId(materialId);
        OrderMaterial pro = (OrderMaterial) product.getRows().get(0);
        line.setPrice(pro.getUnitprice());
        line.setOrderPrice(pro.getUnitprice());
        line.setNum(new BigDecimal(num));
        line.setAmt(line.getOrderPrice().multiply(line.getNum()));
        line.setOrderAmt(line.getOrderPrice().multiply(line.getNum()));
        lines.add(line);
        
        OmOrderHeadersAll orderh = new OmOrderHeadersAll();
        CrmStation stations = utlservice.getStationByID(stationid);
        orderh.setMerchCustId(Long.parseLong(retailId));
        orderh.setSalesrepId(user.getId());
        orderh.setShipId(merchid);
        orderh.setStationId(stations==null?0L:stations.getId());
        orderh.setOrganizationId(merch.getOrganizationId());
        orderh.setRegionId(stations==null?merch.getOrganizationId():stations.getOrganizationId().substring(0, 7));
        orderh.setProviId(merch.getOrganizationId());
        orderh.setCreateTs(new Date());
        orderh.setCreateOid(user.getId());
        orderh.setCashAmt(BigDecimalASME.multiply(line.getAmt()));
        orderh.setAmt(BigDecimalASME.multiply(line.getAmt()));
        orderh.setOrderAmt(BigDecimalASME.multiply(line.getOrderAmt()));
        orderh.setHbAmt(BigDecimal.ZERO);
        orderh.setCreditAmt(BigDecimal.ZERO);
        orderh.setOrderType("1");
        orderh.setStates("1");
        orderh.setAttribute9("0");
        addOrder(orderh,lines);
      }
      return ResponseResult.builder().data(rowCount-1).build();
    }
	
	private ResponseResult validateExcel(String filePath, Long merchid, Long stationid, TEmployee user) throws EncryptedDocumentException, InvalidFormatException, IOException{
	  CMerchCustBase merch = custbaseMapper.selectByPrimaryKey(merchid);
      File excelFile = new File(filePath); // 创建文件对象
      FileInputStream is = new FileInputStream(excelFile); // 文件流
      Workbook workbook = WorkbookFactory.create(is); 
      int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
      if(sheetCount<1){
        return ResponseResult.builder().message("表格无内容").code(501).build();
      }
      Sheet sheet = workbook.getSheetAt(0);
      int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
      if(rowCount<2){
        return ResponseResult.builder().message("表格无内容").code(501).build();
      }
      // 遍历每一行
      for (int r = 1; r < rowCount; r++) {
        Row row = sheet.getRow(r);
        String msg = validateRow(row, merch);
        if(!msg.equals("200")){
          return ResponseResult.builder().message(""+(r+1)+"行："+msg).code(501).build();
        }
        
      }
      return ResponseResult.builder().code(200).build();
	}
	
	private String validateRow(Row row, CMerchCustBase merch){
	  String materialId = getValue(row.getCell(0), false);
      String num = getValue(row.getCell(3), true);
      String retailId = getValue(row.getCell(4), false);
      if(StringUtils.isEmpty(materialId)){
        return "产品id为空";
      }
      if(StringUtils.isEmpty(num)){
        return "购买数量为空";
      }
      if(!num.matches("^\\d+$|^\\d+\\.\\d{1,3}$")){
        return "购买数量必须为正数且小数不大于3位";
      }
      if(new BigDecimal(num).compareTo(BigDecimal.ZERO)<1){
        return "购买数量不能为0";
      }
      if(StringUtils.isEmpty(retailId)){
        return "零售客户id为空";
      }
      if(!retailId.matches("^\\d+$")){
        return "零售客户id必须为数字";
      }
      AjaxDTO product = orderUtilService.getCustomerProduct(merch.getId(), merch.getOrganizationId(), "1", materialId,null);
      if(product.getRows().isEmpty()){
        return "没有找到此产品，必须在生效合同中且价格大于0";
      }
      CMerchCustBase retail = custbaseMapper.selectByPrimaryKey(Long.parseLong(retailId));
      if(retail ==null){
        return "零售客户不存在";
      }
      if(!"5".equals(retail.getCustType())){
        return "此客户不是零售客户";
      }
      if(!retail.getPid().equals(merch.getId())){
        return "选择的配送商无此零售客户";
      }
      return "200";
	}
	
	private String getValue(Cell cell, boolean isDecimal){
	  String result = null;
	  if(cell!=null){
	    int cellType = cell.getCellType();
        switch (cellType) {
          case Cell.CELL_TYPE_STRING: // 文本
            result = cell.getStringCellValue();
            break;
          case Cell.CELL_TYPE_NUMERIC: // 数字、日期
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
              // 读取日期格式
              result = cell.getDateCellValue().toString();
            } else {
              // 读取数字
              if(isDecimal){
                result = String.valueOf(cell.getNumericCellValue());
              }else{
                DecimalFormat df = new DecimalFormat("0");    
                result = df.format(cell.getNumericCellValue());
              }
            }
            break;
          case Cell.CELL_TYPE_BOOLEAN: // 布尔型
            result = String.valueOf(cell.getBooleanCellValue());
            break;
          case Cell.CELL_TYPE_BLANK: // 空白
            result = cell.getStringCellValue();
            break;
          case Cell.CELL_TYPE_ERROR: // 错误
            break;
          case Cell.CELL_TYPE_FORMULA: // 公式
            break;
          default:
            break;
        }
	  }
	  return result;
	}

	@Override
	public Long findShipIdByMerchId(long merchid) {
		    CMerchCustDistributionExample ex = new CMerchCustDistributionExample();
		    ex.createCriteria().andMerchCustIdEqualTo(merchid);
		    List<CMerchCustDistribution> ships = shipmapper.selectByExample(ex);
		    return ships.get(0).getId();	
	}

	@Override
	public BigDecimal getSumNumById(Long headerid) {
		// TODO Auto-generated method stub
		return this.utilmapper.getSunNumById(headerid);
	}

	@Override
	public int updateRemark(Long id, String remark,Long shipid,String rdcCode,BigDecimal freight) {
		// TODO Auto-generated method stub
		OmOrderHeadersAll  header = this.headmapper.selectByPrimaryKey(id);
		header.setRemark(StringUtils.isEmpty(remark)?" ":remark);
		header.setShipId(shipid);
		header=updateFreight(header,freight);
		if(!StringUtils.isEmpty(rdcCode)){
			header.setRdcCode(rdcCode);
		}
		return this.headmapper.updateByPrimaryKeySelective(header);
	}
	private OmOrderHeadersAll updateFreight(OmOrderHeadersAll  header,BigDecimal freight) {
		if(OrderDeliveryType.NOMAIL.getCode().equals(header.getDeliveryType())) {
			//不包邮
			header.setFreight(BigDecimalASME.multiply(freight));
			
			OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
			OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
			lineExt.andHeaderIdEqualTo(header.getId());
			List<OmOrderLinesAll> orderlines = this.linemapper.selectByExample(lineEx);
			if(orderlines!=null && orderlines.size()>0) {
				BigDecimal linefreight=BigDecimalASME.multiply(freight).divide(new BigDecimal(orderlines.size()));
				for(OmOrderLinesAll line :orderlines) {
					line.setFreight(linefreight);
					this.linemapper.updateByPrimaryKeySelective(line);
					
					OmOrderSpiltsExample spiltEx = new OmOrderSpiltsExample();
					OmOrderSpiltsExample.Criteria spiltExt = spiltEx.createCriteria();
					spiltExt.andHeaderIdEqualTo(header.getId());
					spiltExt.andLineIdEqualTo(line.getId());
					List<OmOrderSpilts> orderspilts= this.spiltMapper.selectByExample(spiltEx);
					
					BigDecimal spiltfreight=line.getFreight().divide(new BigDecimal(orderspilts.size()));
					if(orderspilts!=null && orderspilts.size() >0) {
						for(OmOrderSpilts spilt :orderspilts) {
							spilt.setFreight(spiltfreight);
							this.spiltMapper.updateByPrimaryKeySelective(spilt);
						}
					}
				}
				
			}
			
		}else {
			header.setFreight(BigDecimal.ZERO);
			OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
			OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
			lineExt.andHeaderIdEqualTo(header.getId());
			List<OmOrderLinesAll> orderlines = this.linemapper.selectByExample(lineEx);
			if(orderlines!=null && orderlines.size()>0) {
				for(OmOrderLinesAll line :orderlines) {
					line.setFreight(BigDecimal.ZERO);
					this.linemapper.updateByPrimaryKeySelective(line);
				}
			}
			OmOrderSpiltsExample spiltEx = new OmOrderSpiltsExample();
			OmOrderSpiltsExample.Criteria spiltExt = spiltEx.createCriteria();
			spiltExt.andHeaderIdEqualTo(header.getId());
			List<OmOrderSpilts> orderspilts= this.spiltMapper.selectByExample(spiltEx);
			if(orderspilts!=null && orderspilts.size() >0) {
				for(OmOrderSpilts spilt :orderspilts) {
					spilt.setFreight(BigDecimal.ZERO);
					this.spiltMapper.updateByPrimaryKeySelective(spilt);
				}
			}
		}
		return header;
	}
	@Override
	public List<CMerchCustDistribution> getMerchDistribution(Long merchCustId,
			String organizationId) {
		// TODO Auto-generated method stub
		CMerchCustDistributionExample ex = new CMerchCustDistributionExample();
		CMerchCustDistributionExample.Criteria ext = ex.createCriteria();
		ext.andMerchCustIdEqualTo(merchCustId);
		ext.andOrganizationIdEqualTo(organizationId);
		return this.distributionMapper.selectByExample(ex);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void startProcess(TEmployee emp, Long headerid, String orderType) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		List<OmOrderHeadersAll> orders = getOrders(headerid, orderType);
	    String processName = "saleOrder";
	    param.put("viewPage","order/orderaudit.html?id="+headerid);
	    if("1".equals(orderType)){
	       Escaper escaper = UrlEscapers.urlFormParameterEscaper();
	    	processName = "Order2B";
	    	StringBuilder builder = new StringBuilder();
	    	for(OmOrderHeadersAll order:orders){
	    	  builder.append(order.getId()).append(",");
	    	}
	    	param.put("ids", builder.toString());
	    	OmOrderHeadersAll order = orders.get(0);
	    	Map<String, Object> batchs = new HashMap<>();
	    	batchs.put("merchid", order.getShipId());
	    	batchs.put("batchnum", order.getAttribute8());
	    	List<DistributeOrderDTO> disOrders = utilmapper.findDistributeOrders(batchs);
	    	DistributeOrderDTO disOrder = disOrders.get(0);
	    	param.put("order_centerviewPage", "order/distributor/distributeOrderDetails?lpno="+order.getAttribute8()
	    	    +"&states=2&amt="+disOrder.getAmt()+"&merchname="+escaper.escape(disOrder.getMerchname())+"&shipid="+disOrder.getShipid()
	    	    +"&shipname="+escaper.escape(disOrder.getShipname())+"&merchid="+disOrder.getMerchid()+"&isMatched=0&limit=10&audit=1");
	    } else if("4".equals(orderType)){
	    	param.put("ORDER_CENTERviewPage", "order/orderaudit.html");
	    	processName = "saleSpecialOrder";
	    } else if("6".equals(orderType)){
	      processName = "transferOrder";
	    } else if("7".equals(orderType)){
	      processName = "saleOrder1";
	    } else if("10".equals(orderType)){
	      processName = "LKASaleOrder";
	    } else if("5".equals(orderType)){
	    	param.put("viewPage", "order/orderdetails.html?type=1&id="+headerid+"&isCenter=1");
	      processName = "ALLOCATION_ORDER";
	    }
//		OmOrderHeadersAll  order =this.getOrderBYid(headerid);
//		BigDecimal countNum = this.getSumNumById(headerid);
	    
	    BigDecimal countNum = getSumNum(orders);
		param.put("type", orderType);
		param.put("key", headerid);
		param.put("name", "销售订单编号:"+headerid);
		param.put("startUser",emp.getLoginName());
		param.put("groupType", "admin");
		param.put("level", "1");
		param.put("SKIP", 0);
		param.put("ADD_WLviewPage","order/appendWL.html");
		param.put("sendCount", countNum);
		param.put("SALE_MAKESUREviewPage", "order/makeSure.html");
		param.put("CHECK_PERIODviewPage", "order/checkPeriod.html");
		param.put("RGGDviewPage", "order/closeOrder.html");
		param.put("MAKESURE_SENDviewPage", "order/detail.html");
		param.put("CLOSE_ORDERviewPage", "order/closeOrder.html");
		
		String processId = processService.startProcess(param, processName,
			emp.getLoginName());
		for(OmOrderHeadersAll order:orders){
		  OmOrderHeadersAll updateOrder = new OmOrderHeadersAll();
		  updateOrder.setId(order.getId());
		  updateOrder.setAttribute1(processId);
	      this.updateOrderHeader(updateOrder);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public int doneTransferOrder(Long orderid){
	  // 扣减库存  扣减资金 ，可扣为负数 
	  reduceDepo.reduceByOrder(orderid);
	  reduceAccount.reduceByOrderId(orderid, 0L);
	  OmOrderHeadersAll updateOrder = new OmOrderHeadersAll();
	  updateOrder.setId(orderid);
	  updateOrder.setStates("8");
	  return headmapper.updateByPrimaryKeySelective(updateOrder);
	}
	
	//套餐
	private void addCombinationSpiltline(OmOrderLinesAll line ,OmOrderHeadersAll header){
		int i=0;
		//获取套餐头数据
		CrmMaterialPackageHeaderExample headerEx = new CrmMaterialPackageHeaderExample();
		CrmMaterialPackageHeaderExample.Criteria headerExt= headerEx.createCriteria();
		headerExt.andCodeEqualTo(line.getMaterialId());
		List<CrmMaterialPackageHeader> headers = this.combinatonheadermapper.selectByExample(headerEx);
		CrmMaterialPackageHeader combinationHeader = headers.get(0);
		//获取套餐行数据   
		CrmMaterialPackageLinesExample  lineEx = new CrmMaterialPackageLinesExample();
		CrmMaterialPackageLinesExample.Criteria lineExt = lineEx.createCriteria();
		lineExt.andHeaderIdEqualTo(combinationHeader.getId());
		List<CrmMaterialPackageLines> combinationlines = this.combinationlinemapper.selectByExample(lineEx);
		for(CrmMaterialPackageLines combinationline :combinationlines){
			i =i+10;
			//获取物料信息
			TMaterialBaseExample  materialEx = new TMaterialBaseExample();
			TMaterialBaseExample.Criteria materialExt=materialEx.createCriteria();
			materialExt.andSapIdEqualTo(combinationline.getMaterialId());
			materialExt.andActiveEqualTo("1");
			List<TMaterialBase> materials = this.materialmapper.selectByExample(materialEx);
			TMaterialBase material = materials.get(0);
			OmOrderSpilts spiltline = new OmOrderSpilts();
			spiltline.setOrderitemSapNo(String.valueOf(i));
			spiltline.setHeaderId(header.getId());
			spiltline.setLineId(line.getId());
			spiltline.setMerchCustId(header.getMerchCustId());
			spiltline.setCreateTs(new Date());
			spiltline.setCreateOid(header.getCreateOid());
			BigDecimal price = getPriceByMaterialId(combinationline.getMaterialId(),header.getMerchCustId());
			spiltline.setPrice(price);
			//数量等于购买数量乘以箱内数量
			spiltline.setNum(line.getNum().multiply(new BigDecimal(material.getAttribute6()!=null?material.getAttribute6():"1")).multiply(combinationline.getNum()));
			spiltline.setOrganizationId(header.getOrganizationId());
			spiltline.setType("1");
			//金额等于购买数量乘以套餐维护单价（箱）
			spiltline.setAmt(spiltline.getNum().multiply(spiltline.getPrice()));
			spiltline.setMaterialId(combinationline.getMaterialId());
			spiltline.setStates("1");
			spiltline.setShipTo(header.getShipId());
			spiltline.setCreateOid(header.getCreateOid());
			spiltline.setCreateTs(new Date());
			spiltline.setStationId(header.getStationId());
			spiltline.setReginId(header.getRegionId());
			spiltline.setProvId(header.getProviId());
			spiltline.setSalesrepId(header.getSalesrepId());
			this.spiltMapper.insert(spiltline);
		}
		//获取返利数据
		CrmMaterialPackageRebateExample rebateEx =new CrmMaterialPackageRebateExample();
		CrmMaterialPackageRebateExample.Criteria rebateExt= rebateEx.createCriteria();
		rebateExt.andHeaderIdEqualTo(combinationHeader.getId());
		List<CrmMaterialPackageRebate> rebates = this.combinationrebatemapper.selectByExample(rebateEx);
		if(rebates !=null && rebates.size()>0){
			for(CrmMaterialPackageRebate rebate:rebates){
				i =i+10;
				//获取物料信息
				TMaterialBaseExample  materialEx = new TMaterialBaseExample();
				TMaterialBaseExample.Criteria materialExt=materialEx.createCriteria();
				materialExt.andSapIdEqualTo(rebate.getMaterialId());
				materialExt.andActiveEqualTo("1");
				List<TMaterialBase> materials = this.materialmapper.selectByExample(materialEx);
				TMaterialBase material = materials.get(0);
				OmOrderSpilts spiltline = new OmOrderSpilts();
				spiltline.setOrderitemSapNo(String.valueOf(i));
				spiltline.setHeaderId(header.getId());
				spiltline.setLineId(line.getId());
				spiltline.setMerchCustId(header.getMerchCustId());
				spiltline.setCreateTs(new Date());
				spiltline.setCreateOid(header.getCreateOid());
				spiltline.setPrice(BigDecimal.ZERO);
				BigDecimal price = getPriceByMaterialId(rebate.getMaterialId(),header.getMerchCustId());//获取物料单价
				if(price !=null && price.compareTo(BigDecimal.ZERO) >0){
					BigDecimal amt =BigDecimalASME.divide(header.getOrderAmt().multiply(new BigDecimal(rebate.getRatio())));//获取返利金额
					BigDecimal amounts = material.getAttribute6()==null?new BigDecimal("1"):new BigDecimal(material.getAttribute6());//箱内数量					
					BigDecimal num = amt.divide(price,0,BigDecimal.ROUND_HALF_UP).divide(amounts,0,BigDecimal.ROUND_HALF_UP);//获取返利数量（箱） 返利金额除以单价除以箱内数量
					//返利数量大于限制数量取限制数量
					spiltline.setNum((num.compareTo(rebate.getLimitNum())>0?rebate.getLimitNum():num).multiply(amounts));
				}else{
					spiltline.setNum(rebate.getLimitNum());
				}
				spiltline.setOrganizationId(header.getOrganizationId());
				spiltline.setType("2");
				//金额等于购买数量乘以套餐维护单价（箱）
				spiltline.setAmt(BigDecimal.ZERO);
				spiltline.setMaterialId(rebate.getMaterialId());
				spiltline.setStates("1");
				spiltline.setShipTo(header.getShipId());
				spiltline.setCreateOid(header.getCreateOid());
				spiltline.setCreateTs(new Date());
				spiltline.setStationId(header.getStationId());
				spiltline.setReginId(header.getRegionId());
				spiltline.setProvId(header.getProviId());
				spiltline.setSalesrepId(header.getSalesrepId());
				this.spiltMapper.insert(spiltline);
			}
		}
	}

	private BigDecimal getPriceByMaterialId(String materialId, Long merchCustId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("materialId", materialId);
		params.put("merchCustId", merchCustId);
		return this.utilmapper.selectPriceByMaterialId(params);
	}
	
	/**
	 * 调拨单确认收货获取数据List
	 */
	@Override
	public AjaxDTO selectMakeSureList(AjaxDTO bean, String custname,
			Long orderId, String sapOrderId) {
		AjaxDTO dto = new AjaxDTO();
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        OmOrderMakeSureExample  ex = new OmOrderMakeSureExample();
        OmOrderMakeSureExample.Criteria ext = ex.createCriteria();
        if(!StringUtils.isEmpty(custname)){
        	ext.andNameEqualTo(custname);
        }
        if(orderId !=null){
        	ext.andIdEqualTo(orderId);
        }
        if(!StringUtils.isEmpty(sapOrderId)){
        	ext.andSapOrderIdEqualTo(sapOrderId);
        }
        ext.andSendWeigthGreaterThan(BigDecimal.ZERO);
        ex.setPage(page);
        ex.setOrderByClause("id desc");
        List<OmOrderMakeSure> list = this.makeSureMapper.selectByExample(ex);
        int total = this.makeSureMapper.countByExample(ex);
        dto.setRows(list);
        dto.setTotal(total);
		return dto;
	}
	@Override
	public List<OmOrderMakeSure> selectMakeSureListall(AjaxDTO bean,
			String custname, Long orderId, String sapOrderId) {
		OmOrderMakeSureExample  ex = new OmOrderMakeSureExample();
        OmOrderMakeSureExample.Criteria ext = ex.createCriteria();
        if(!StringUtils.isEmpty(custname)){
        	ext.andNameEqualTo(custname);
        }
        if(orderId !=null){
        	ext.andIdEqualTo(orderId);
        }
        if(!StringUtils.isEmpty(sapOrderId)){
        	ext.andSapOrderIdEqualTo(sapOrderId);
        }
        ext.andSendWeigthGreaterThan(BigDecimal.ZERO);
        ex.setOrderByClause("id desc");
        List<OmOrderMakeSure> list = this.makeSureMapper.selectByExample(ex);
		return list;
	}
	@SuppressWarnings("deprecation")
	@Override
	public ExcelResult importRetailOrders(String path, final TEmployee user) throws InstantiationException, IllegalAccessException, IOException{
		ExcelType excelType = ExcelUtil.getExcelType(path);
		InputStream inputStream = new FileInputStream(path);
		final Map<String,OmOrderHeadersAll> orders = new HashMap<String,OmOrderHeadersAll>();
		ExcelResult excelResult = ExcelUtil.importExcelWithHeader(excelType, 1, inputStream, RetailOrderImportDTO.class, new ExcelCallback<RetailOrderImportDTO>() {
			@Override
			public ExcelCheckResult handleImportData(RetailOrderImportDTO roid, int rowNum) {
				ExcelCheckResult cr = new ExcelCheckResult();
				try {
					ValidationResult vr = ValidationUtil.validateEntity(roid);
					cr.setSuccess(!vr.isHasErrors());
					cr.setErrorMsg(vr.getErrorMsg());
					OmOrderHeadersAll orderh = new OmOrderHeadersAll();

					Long merchCustId = roid.getMerchCustId();
					Long warehousingId = roid.getWarehousingId();
					
					CMerchCustBaseV cust = customerService.findCustWithPosition(merchCustId);
					Assert.notNull(cust,"客户不存在！");
					
					CMerchCustBase warehousing = custbaseMapper.selectByPrimaryKey(warehousingId);
					Long salesRepId = custbaseMapper.findSalesRepIdById(warehousingId);
					orderh.setMerchCustId(merchCustId);
					orderh.setSalesrepId(salesRepId == null?user.getId():salesRepId);//仓储服务商对应的销售，如果为空则默认当前用户
					orderh.setShipId(warehousingId);
					orderh.setStationId(cust.getPositionId());
					orderh.setOrganizationId(warehousing.getOrganizationId());
					orderh.setCreateTs(roid.getOrderDate());
					orderh.setCreateOid(user.getId());
					orderh.setOrderAmt(BigDecimalASME.multiply(BigDecimal.valueOf(roid.getAmount())));
					orderh.setDiscountAmt(BigDecimal.ZERO);
					orderh.setAmt(orderh.getOrderAmt().subtract(orderh.getDiscountAmt()));
					orderh.setCashAmt(null);
					orderh.setHbAmt(null);
					orderh.setCreditAmt(null);
					orderh.setRemark(null);
					orderh.setAttribute2(null);//最晚送达时间
					orderh.setOrderType(OrderType.WAREHOUSING.getCode());
					orderh.setBillTo(null);//付款方
					orderh.setProviId(cust.getOrganizationId());
					orderh.setRegionId(cust.getSalesAreaId());
					orderh.setStates(OrderStatus.SUBMIT.getCode());
					
					OmOrderLinesAll line = new OmOrderLinesAll();
					line.setAttribute12("0");
					line.setAmt(BigDecimal.valueOf(roid.getAmount()));
					line.setHbAmt(null);
					line.setOrderAmt(BigDecimal.valueOf(roid.getAmount()));
					line.setDiscountAmt(null);
					line.setPrice(BigDecimal.valueOf(roid.getPrice()));
					line.setOrderPrice(BigDecimal.valueOf(roid.getPrice()));
					String materialNo = roid.getMaterialNo();
					
					line.setMaterialId(materialNo);
					line.setNum(BigDecimal.valueOf(roid.getNum()));
					line.setCreateOid(user.getId());
					line.setCreateTs(new Date());
					TMaterialBase materialBase = materialmapper.selectByPrimaryKey(materialNo);
					line.setUnit(materialBase.getUnit());
					//同一日期、客户、仓储服务商的行合并为一个订单
					String key = DateUtil.format(roid.getOrderDate(), DateUtil.YYYYMMDD)+merchCustId+warehousingId;
					OmOrderHeadersAll order = orders.get(key);
					if(order != null){
						List<OmOrderLinesAll> orderLines = order.getOrderLines();
						line.setId(Long.valueOf(orderLines.size()+1));
						orderLines.add(line);
						order.setOrderAmt(BigDecimalUtil.add(order.getOrderAmt(), orderh.getOrderAmt()));
						order.setAmt(BigDecimalUtil.add(order.getAmt(), orderh.getAmt()));
						orders.put(key, order);
					}else{
						line.setId(1L);//调用addOrder方法时的订单行序号参数处理
						List<OmOrderLinesAll> lines = new ArrayList<OmOrderLinesAll>();
						lines.add(line);
						orderh.setOrderLines(lines);
						orders.put(key, orderh);
					}
				} catch (Exception e) {
					cr.setSuccess(false);
					logger.error("Import Retail order error, info=" + roid, e);
				}
				return cr;
			}
		});
		for (Entry<String, OmOrderHeadersAll> entry : orders.entrySet()) {
			OmOrderHeadersAll oh = entry.getValue();
			List<OmOrderLinesAll> orderLines = oh.getOrderLines();
			/*
			 * 校验订单金额是否小于仓储服务商的库存总金额
			 * 当订单金额大于库存总金额时不能导入零售订单
			 */
			//获取仓储服务商的库存总金额
			Long warehousingId = oh.getShipId();
			CMerchCustProudctInvExample ccex = new CMerchCustProudctInvExample();
			CMerchCustProudctInvExample.Criteria cc = ccex.createCriteria();
			cc.andMerchCustIdEqualTo(warehousingId);
			List<CMerchCustProudctInv> custInv = proudctInvMapper.selectByExample(ccex);
			CMerchCustProductExample ex = new CMerchCustProductExample();
			CMerchCustProductExample.Criteria criteria = ex.createCriteria();
			criteria.andStatesEqualTo("4");
			criteria.andMerchCustIdEqualTo(warehousingId);
			List<CMerchCustProduct> custProducts = productMapper.selectByExample(ex);
			BigDecimal totalInvAmt = BigDecimal.ZERO;
			for (CMerchCustProudctInv ci : custInv) {
				BigDecimal invNum = ci.getInvNum();
				String materialId = ci.getMaterialId();
				BigDecimal materialPrice = getPriceByMaterialId(materialId, warehousingId);
				for (CMerchCustProduct cp : custProducts) {
					String materialId2 = cp.getMaterialId();
					BigDecimal hprice = cp.gethPrice();
					BigDecimal fprice = BigDecimalUtil.add(materialPrice, hprice);
					if(Objects.equals(materialId, materialId2)){
						BigDecimal amt = BigDecimalUtil.multiply(invNum == null?BigDecimal.ZERO:invNum, fprice);
						totalInvAmt = totalInvAmt.add(amt);
						break;
					}
				}
			}
			//库存总金额与订单金额进行比较
			BigDecimal orderAmt = oh.getOrderAmt()==null?BigDecimal.ZERO:oh.getOrderAmt();
			if(orderAmt.compareTo(totalInvAmt) > 0){
				throw new HHNZException("仓储服务商"+warehousingId+"库存金额"+BigDecimalASME.divide(totalInvAmt)+"小于导入的订单金额："+BigDecimalASME.divide(orderAmt));
			}
			//保存订单
			this.addOrder(oh, orderLines);
			//扣减仓储服务商库存
			ReduceRetailOrderDepoTask reduceDepot = ApplicationContextUtil.getBean("reduceRetailOrderDepo");
			reduceDepot.reduceByOrder(oh.getId());
			//扣减零售客户资金
			ReduceMerchAccountTask reduceMerchAccountTask = ApplicationContextUtil.getBean("reduceMerchAccountTask");
			reduceMerchAccountTask.reduceByOrderId(oh.getId(), user.getId());
			//构造sap传入参数
			InputDTO inputParam = orderRFC.constructInputParam(oh);
			//调用sap接口
			orderRFC.executeInThread(inputParam, null);
		}
		
		return excelResult;
	}

	@Override
	public List<OmOrderHeadersAll> listTransferOrder(Long custId, String period) {
		CMerchCustDistributionExample ccex = new CMerchCustDistributionExample();
		ccex.createCriteria().andMerchCustIdEqualTo(custId);
		List<CMerchCustDistribution> ships = shipmapper.selectByExample(ccex);
		if(ships == null || ships.isEmpty()){
			return null;
		}
		List<Long> shipIds = new ArrayList<Long>();
		for (CMerchCustDistribution d : ships) {
			shipIds.add(d.getId());
		}
		OmOrderHeadersAllExample ex = new OmOrderHeadersAllExample();
		Criteria criteria = ex.createCriteria();
		criteria.andShipIdIn(shipIds);
		List<String> orderTypes = new ArrayList<String>();
		orderTypes.add("5");//调拨单
		orderTypes.add("8");//特殊调拨单
		criteria.andOrderTypeIn(orderTypes);
		Date firstDay = DateUtil.getFirstDayOfMonth(period);
		Date lastDay = DateUtil.getLastDayOfMonth(period);
		lastDay = DateUtil.getEndingOfDay(lastDay);
		criteria.andCreateTsBetween(firstDay, lastDay);
		List<String> orderStates = new ArrayList<String>();
		orderStates.add("5");//待发货
		orderStates.add("7");//已发货
		orderStates.add("8");//确认收货
		criteria.andStatesIn(orderStates);
		List<OmOrderHeadersAll> result = headmapper.selectByExample(ex);
		return result;
	}
	
	@Override
	public List<OmOrderHeadersAll> listRetailerOrder(Long custId, String period) {
		OmOrderHeadersAllExample ex = new OmOrderHeadersAllExample();
		Criteria criteria = ex.createCriteria();
		criteria.andOrderTypeEqualTo("6");//仓储服务商零售订单
		Date firstDay = DateUtil.getFirstDayOfMonth(period);
		Date lastDay = DateUtil.getLastDayOfMonth(period);
		lastDay = DateUtil.getEndingOfDay(lastDay);
		criteria.andCreateTsBetween(firstDay, lastDay);
		List<String> orderStates = new ArrayList<String>();
		orderStates.add("7");//已发货
		orderStates.add("8");//确认收货
		criteria.andStatesIn(orderStates);
		criteria.andShipIdEqualTo(custId);
		List<OmOrderHeadersAll> result = headmapper.selectByExample(ex);
		return result;
	}

	@Override
	public List<OrderSearchModel> selectOrderListAll(Map<String, Object> params) {
		return  this.utilmapper.findOrderList(params);
	}

}
