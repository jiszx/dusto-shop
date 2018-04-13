package com.hhnz.customerInv.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.MoreObjects;
import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAccountLogExample;
import com.hhnz.account.uti.AccountLogTypeEnum;
import com.hhnz.account.uti.AccountTypeEnum;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.service.IDictService;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customerInv.dto.AllocationDetailsDTO;
import com.hhnz.customerInv.dto.CustomerInvAllocationDTO;
import com.hhnz.customerInv.mapper.CMerchCustProductBalancesMapper;
import com.hhnz.customerInv.mapper.CMerchCustProductLogMapper;
import com.hhnz.customerInv.mapper.CMerchCustProudctInvMapper;
import com.hhnz.customerInv.mapper.CMerchCustProudctInvVMapper;
import com.hhnz.customerInv.mapper.CMerchInvLogVMapper;
import com.hhnz.customerInv.mapper.CustomerStockMapper;
import com.hhnz.customerInv.mapper.MerchProudctBalancesVMapper;
import com.hhnz.customerInv.model.CMerchCustProductBalances;
import com.hhnz.customerInv.model.CMerchCustProductBalancesExample;
import com.hhnz.customerInv.model.CMerchCustProductLog;
import com.hhnz.customerInv.model.CMerchCustProductLogExample;
import com.hhnz.customerInv.model.CMerchCustProudctInv;
import com.hhnz.customerInv.model.CMerchCustProudctInvExample;
import com.hhnz.customerInv.model.CMerchCustProudctInvV;
import com.hhnz.customerInv.model.CMerchCustProudctInvVExample;
import com.hhnz.customerInv.model.CMerchInvLogV;
import com.hhnz.customerInv.model.CMerchInvLogVExample;
import com.hhnz.customerInv.model.MerchProudctBalancesV;
import com.hhnz.customerInv.model.MerchProudctBalancesVExample;
import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.jco.business.inventory.InputDTO;
import com.hhnz.jco.business.inventory.InventoryRFC;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;
import com.hhnz.util.Excels;
import com.hhnz.util.Files;
import com.hhnz.util.UnitConverter;
import com.hhnz.util.db.Page;
import com.hhnz.util.enmus.MaterialUnit;
import com.hhnz.virtualwarehouse.mapper.CrmVirtualWarehouseMapper;

/**
 * 客户库存管理Service
 * @author hhnz-skevin 2016-12-06
 *
 */
@Service
public class CustomerStockServiceImpl implements CustomerStockService {
	private static Logger logger = LoggerFactory.getLogger(CustomerStockServiceImpl.class);
	
	@Resource
	private  CustomerStockMapper mapper;
	
	@Resource
	private  CMerchCustProudctInvVMapper  invmapper;
	
	@Resource
	private  MerchProudctBalancesVMapper  balancemapper;
	
	@Resource
	private  CMerchCustBalancesMapper  balancesmapper;
	
	@Resource
	private CMerchCustProductBalancesMapper  bmapper;
	
	@Resource
	private CMerchInvLogVMapper logvmapper;
	
	@Resource
	private IProcessService processService;
	
	@Resource
	private  CMerchCustAccountMapper  accountmapper;
	@Resource
	private  CMerchCustAccountLogMapper  accountlogmapper;
	
	@Resource
	private  CMerchCustProudctInvMapper  invmapper2;
	
	@Resource
	private CMerchCustProductLogMapper  logmapper;
	
	@Resource
	private OmOrderHeadersAllMapper  headermapper;
	
	@Resource
	private CMerchCustBaseMapper  merchmapper;
	
	@Resource
	private  OmOrderLinesAllMapper  linemapper;
	
	@Resource
	private OmOrderSpiltsMapper  spiltmapper;
	@Resource
	private InventoryRFC    inventoryRFC;
	
	@Resource
	private  CMerchCustContractMapper  contractmapper;
	@Resource
    private CrmVirtualWarehouseMapper warehouseMapper;
	@Resource
	private IDictService dictService;
	@Resource
	private OrderUtilService utilService;
	@Value("${upload.file.path}")
	private String basePath;
	
	
	@Override
	public AjaxDTO getList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<CustomerInvAllocationDTO> list = this.mapper.getList(params);
		int total = this.mapper.countList(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	
	@Override
	public List<CustomerInvAllocationDTO> getListAll(Map<String, Object> params) {
		List<CustomerInvAllocationDTO> list = this.mapper.getList(params);
		return list;
	}
	
	/**
	 * 客户库存查询
	 */
	@Override
	public AjaxDTO getInvList(AjaxDTO bean, CMerchCustProudctInvV inv, List<Long> stationids, TEmployee user) {
		AjaxDTO dto = new AjaxDTO();
		Page  page = new Page();
		page.setLimit(bean.getLimit());
	    page.setOffset(bean.getOffset());
		CMerchCustProudctInvVExample ex = new CMerchCustProudctInvVExample();
		CMerchCustProudctInvVExample.Criteria ext =ex.createCriteria();
		if(!StringUtils.isEmpty(inv.getCustname())){
			ext.andCustnameLike("%"+inv.getCustname()+"%");
		}
		if(!StringUtils.isEmpty(inv.getOrganizationId())){
			ext.andOrganizationIdLike(inv.getOrganizationId()+"%");
		}
		if(!StringUtils.isEmpty(inv.getSku())){
			ext.andSkuLike("%"+inv.getSku()+"%");
		}
		if(!StringUtils.isEmpty(inv.getRdcCode())){
			ext.andRdcCodeEqualTo(inv.getRdcCode());
		}
		if(!StringUtils.isEmpty(inv.getMaterialId())){
			ext.andMaterialIdLike("%"+inv.getMaterialId()+"%");
		}
		if(user.getMerchId() !=null){
			ext.andMerchCustIdEqualTo(user.getMerchId());
		}else{
			if(stationids.size()>0 && stationids !=null){
				ext.andStationIdIn(stationids);
			}
		}
		ex.setPage(page);
		List<CMerchCustProudctInvV> list = this.invmapper.selectByExample(ex);
		int total = this.invmapper.countByExample(ex);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	
	/**
	 * 获取期间数量list
	 */
	@Override
	public AjaxDTO getBalancesList(AjaxDTO bean, MerchProudctBalancesV inv,List<Long> stationids, TEmployee user) {
		AjaxDTO dto = new AjaxDTO();
		Page  page = new Page();
		page.setLimit(bean.getLimit());
	    page.setOffset(bean.getOffset());
		MerchProudctBalancesVExample ex = new MerchProudctBalancesVExample();
		MerchProudctBalancesVExample.Criteria ext =ex.createCriteria();
		if(!StringUtils.isEmpty(inv.getCustname())){
			ext.andCustnameLike("%"+inv.getCustname()+"%");
		}
		if(!StringUtils.isEmpty(inv.getOrganizationId())){
			ext.andOrganizationIdLike(inv.getOrganizationId()+"%");
		}
		if(!StringUtils.isEmpty(inv.getSku())){
			ext.andSkuLike("%"+inv.getSku()+"%");
		}
		if(!StringUtils.isEmpty(inv.getRdcCode())){
			ext.andRdcCodeEqualTo(inv.getRdcCode());
		}
		if(!StringUtils.isEmpty(inv.getMaterialId())){
			ext.andMaterialIdLike("%"+inv.getMaterialId()+"%");
		}
		if(!StringUtils.isEmpty(inv.getPeriod())){
			ext.andPeriodEqualTo(inv.getPeriod());
		}
		if(user.getMerchId() !=null){
			ext.andMerchCustIdEqualTo(user.getMerchId());
		}else{
			if(stationids.size()>0 && stationids !=null){
				ext.andStationIdIn(stationids);
			}
		}
		ex.setPage(page);
		ex.setOrderByClause("period desc");
		List<MerchProudctBalancesV> list = this.balancemapper.selectByExample(ex);
		int total = this.balancemapper.countByExample(ex);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	
	/**
	 * 获取期间明细list
	 */
	@Override
	public AjaxDTO getbalanceDetailsList(AjaxDTO bean, Long id) {
		CMerchCustProductBalances  balance = this.bmapper.selectByPrimaryKey(id);
		AjaxDTO dto = new AjaxDTO();
		Page page = new Page();
		page.setLimit(bean.getLimit());
	    page.setOffset(bean.getOffset());
	    CMerchInvLogVExample ex  = new CMerchInvLogVExample();
	    CMerchInvLogVExample.Criteria ext = ex.createCriteria();
	    ext.andPeriodEqualTo(balance.getPeriod());
	    ext.andMaterialIdEqualTo(balance.getMaterialId());
	    ext.andMerchCustIdEqualTo(balance.getMerchCustId());
	    ex.setPage(page);
	    List<CMerchInvLogV> list = this.logvmapper.selectByExample(ex);
	    int total = this.logvmapper.countByExample(ex);
	    dto.setRows(list);
	    dto.setTotal(total);
		return dto;
	}
	
	/**
	 * 更新调拨单状态
	 */
	@Override
	public Map<String,Object> updateStates(Long id, String states,TEmployee user)throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		OmOrderHeadersAll header = this.headermapper.selectByPrimaryKey(id);
		CMerchCustBase  merch = this.merchmapper.selectByPrimaryKey(header.getMerchCustId());
		if("70".equals(merch.getCustType())){
			//合资盐业公司下的仓储服务商不推送SAP
			//生成销售订单
			Long hid=bulidOrder(header,merch,user);
			header.setStates("5");
			header.setAttribute13(hid.toString());
			this.headermapper.updateByPrimaryKeySelective(header);
			result.put("type", "S");
			result.put("msg", "调拨单生成订单完成");
			return result;
		}else{			
			//推送sap
			InputDTO input = inventoryRFC.constructInputParam(id);
			inventoryRFC.executeInThread(input,CustomerStockCallBack.class.getSimpleName());
			result.put("type", "S");
			result.put("msg", "调拨入库执行中");
			header.setStates("5");
			this.headermapper.updateByPrimaryKeySelective(header);
			return result;
		}
	}
	
	/**
	 * 提交审批是验证库存数量 客户可用配货金额（授信） 起定量  起运量  
	 */
	@Override
	public Map<String,Object> ValidateAmtAndNum(Long id) {
		//验证金额
		Map<String,Object> result = new HashMap<String, Object>();
		String type ="S";
		String msg ="";
		int amt = this.mapper.validateAmt(id);
		if(amt !=1){
			type="E1";
			msg ="可用可配金额不足";
		}
		List<String> inv  = this.mapper.validateNum(id);
		if(inv !=null && inv.size()>0){
			StringBuffer  str = new StringBuffer();
			for(String s:inv){
				if(!"S".equals(s)){
					type ="E2";					
					str.append(s+",");
					msg= str.toString()+"库存数量不足";
				}
			}
		}
		//验证货补金额
		int flag = this.mapper.validateSubsidyAmt(id);
		if(flag !=1){
			type="E1";
			msg ="货补金额不足";
		}
		if("S".equals(type)){
		  OmOrderHeadersAll order = headermapper.selectByPrimaryKey(id);
	      CMerchCustBase merch = merchmapper.selectByPrimaryKey(order.getMerchCustId());
	      BigDecimal minOrder = MoreObjects.firstNonNull(merch.getMinOrder(), BigDecimal.ZERO);
	      BigDecimal sumNum = BigDecimal.ZERO;
	      OmOrderSpiltsExample ex = new OmOrderSpiltsExample();
	      OmOrderSpiltsExample.Criteria ext = ex.createCriteria();
	      ext.andHeaderIdEqualTo(order.getId());
	      List<OmOrderSpilts> lines = this.spiltmapper.selectByExample(ex);
	      /*OmOrderLinesAllExample ex = new OmOrderLinesAllExample();
	      ex.createCriteria().andHeaderIdEqualTo(order.getId());
	      List<OmOrderLinesAll> lines = linemapper.selectByExample(ex);*/
	      for(OmOrderSpilts line:lines){
	        BigDecimal toNum = UnitConverter.convert(line.getNum(), line.getMaterialId(), MaterialUnit.TO);
	        sumNum = sumNum.add(toNum);
	      }
	      if(minOrder.compareTo(sumNum)>0){
	        type = "E3";
	        msg = "订单不满足最小起订量："+minOrder+"吨";
	      }
	      if("70".equals(merch.getCustType())){
	    	  //合作仓储服务商验证对应的盐业公司的价格
	    	  Map<String, Object> s = this.validateMerchNumByPid(id,merch.getId(),merch.getPid());
	    	  if("E".equals(s.get("type"))){
	    		  type="E4";
	    		  msg =(String) s.get("msg");
	    	  }
	      }
		}
		if("S".equals(type)){
//		  boolean canTransfer = utilService.canTransfer(id);
//		  if(canTransfer==false){
//		    type = "E5";
//		    msg = "订单不满足运输重量";
//		  }
		}
		result.put("type", type);
		result.put("msg", msg);
		return result;
	}
	
	/**
	 * 更新客户库存数据
	 */
	@Override
	public Integer updateInv() {
		return this.invmapper.updateInv();
	}
	
	/**
	 * 更新客户库存期间数据
	 */
	@Override
	public String updateBalance(Long merchId,String materialId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("merchId", merchId);
		params.put("materialId", materialId);
	    this.balancemapper.updateBalance(params);
	    return (String)params.get("p_result");
	}
	
	
	/**
	 * 更新客户库存数据
	 * @param header
	 * @param line
	 * @param contract
	 * @param period
	 */
	public void doAddProductInv(OmOrderHeadersAll header, OmOrderSpilts line,String period,TEmployee user){
		//判断调拨单是否存入日志表
		CMerchCustProductLogExample  logEx = new CMerchCustProductLogExample();
		CMerchCustProductLogExample.Criteria logExt = logEx.createCriteria();
		logExt.andVoucherIdEqualTo(header.getId().toString());
		logExt.andMaterialIdEqualTo(line.getMaterialId());
		logExt.andMerchCustIdEqualTo(header.getMerchCustId());
		List<CMerchCustProductLog> logs = this.logmapper.selectByExample(logEx);
		if(logs.size()>0 && logs !=null){
			return;
		}
		//插入日志表
		CMerchCustProductLog  log = new CMerchCustProductLog();
		log.setMerchCustId(header.getMerchCustId());
		log.setCreateTs(new Date());
		log.setCreateOid(user.getId());
		log.setdNum(new BigDecimal(line.getDeliveredNum()));//增加数量
		log.setMaterialId(line.getMaterialId());
		log.setVoucherId(header.getId().toString());
		log.setPeriod(period);
		log.setType("2");//入库
		log.setSource("1");//调拨单
		log.setcNum(BigDecimal.ZERO);
		log.setPrice(line.getPrice());
		log.setAmt(line.getAmt());
		log.setRemark(header.getRemark());
		this.logmapper.insert(log);
		
		//插入库存表
		CMerchCustProudctInvExample invex = new CMerchCustProudctInvExample();
		CMerchCustProudctInvExample.Criteria invext = invex.createCriteria();
		invext.andMerchCustIdEqualTo(header.getMerchCustId());
		invext.andMaterialIdEqualTo(line.getMaterialId());
		invext.andOrganizationIdEqualTo(header.getOrganizationId());
		List<CMerchCustProudctInv> invs = this.invmapper2.selectByExample(invex);
		CMerchCustProudctInv inv = new CMerchCustProudctInv();
		if(invs !=null && invs.size()==1){
			 inv =invs.get(0);
			 inv.setInvNum(inv.getInvNum().add(new BigDecimal(line.getDeliveredNum())));
			 this.invmapper2.updateByPrimaryKeySelective(inv);
		}else if(invs ==null||invs.size()==0){
			inv.setMerchCustId(header.getMerchCustId());
			inv.setOrganizationId(header.getOrganizationId());
			inv.setCreateTs(new Date());
			//inv.setContractId(contract.getId());
			inv.setInvNum(new BigDecimal(line.getDeliveredNum()));
			inv.setFrozenNum(BigDecimal.ZERO);
			inv.setCreateOid(user.getId());
			inv.setMaterialId(line.getMaterialId());
			this.invmapper2.insert(inv);
		}
		
		//修改库存期间表
		CMerchCustProductBalancesExample balancesEx = new CMerchCustProductBalancesExample();
		CMerchCustProductBalancesExample.Criteria balancesExt= balancesEx.createCriteria();
		balancesExt.andMerchCustIdEqualTo(header.getMerchCustId());
		balancesExt.andMaterialIdEqualTo(line.getMaterialId());
		balancesExt.andPeriodEqualTo(period);
		List<CMerchCustProductBalances> balances = this.bmapper.selectByExample(balancesEx);
		CMerchCustProductBalances balance = new CMerchCustProductBalances();
		if(balances.size()==1 && balances!=null){
			balance = balances.get(0);
		}else{
			balance.setMaterialId(line.getMaterialId());
			balance.setMerchCustId(header.getMerchCustId());
			balance.setOrganizationId(header.getOrganizationId());
			balance.setPeriod(period);
			balance.setStates("1");
			balance.setYtd(BigDecimal.ZERO);
			balance.setdNum(BigDecimal.ZERO);
			balance.setcNum(BigDecimal.ZERO);
			balance.setPtd(BigDecimal.ZERO);
			this.bmapper.insert(balance);
		}
		balance.setdNum(balance.getdNum().add(new BigDecimal(line.getDeliveredNum())));
		this.bmapper.updateByPrimaryKeySelective(balance);
	}
	
	/**
	 * 扣减客户库存
	 * @param retailOrder
	 * @param line
	 * @param contract
	 * @param period
	 */
	@Override
	public int reduceProductInvByOrder(OmOrderHeadersAll retailOrder, OmOrderSpilts line,CMerchCustBase merch,String period){
      
      //插入日志表
      CMerchCustProductLogExample  logEx = new CMerchCustProductLogExample();
      CMerchCustProductLogExample.Criteria logExt = logEx.createCriteria();
      logExt.andVoucherIdEqualTo(retailOrder.getId().toString());
      logExt.andMaterialIdEqualTo(line.getMaterialId());
      logExt.andMerchCustIdEqualTo(retailOrder.getMerchCustId());
      List<CMerchCustProductLog> logs = this.logmapper.selectByExample(logEx);
      if(logs.size()>0 && logs !=null){
          logger.warn("扣减客户库存 订单对应的日志已存在 orderid:{}", retailOrder.getId());
          return 0;
      }
      CMerchCustProductLog  log = new CMerchCustProductLog();
      log.setMerchCustId(retailOrder.getMerchCustId());
      log.setCreateTs(new Date());
      log.setcNum(line.getNum());
      log.setdNum(BigDecimal.ZERO);
      log.setMaterialId(line.getMaterialId());
      log.setVoucherId(retailOrder.getId().toString());
      log.setPeriod(period);
      log.setType("1");//出库
      log.setSource("2");//零售订单
      log.setPrice(line.getPrice());
      log.setAmt(line.getAmt());
      log.setRemark(retailOrder.getRemark());
      log.setAttribute1(merch.getId().toString());
      this.logmapper.insert(log);
      
      //库存表
      CMerchCustProudctInvExample invex = new CMerchCustProudctInvExample();
      CMerchCustProudctInvExample.Criteria invext = invex.createCriteria();
      invext.andMerchCustIdEqualTo(merch.getId());
      invext.andMaterialIdEqualTo(line.getMaterialId());
      invext.andOrganizationIdEqualTo(retailOrder.getOrganizationId());
      List<CMerchCustProudctInv> invs = this.invmapper2.selectByExample(invex);
      CMerchCustProudctInv inv = new CMerchCustProudctInv();
      if(invs !=null && invs.size()==1){
           inv =invs.get(0);
           if(inv.getInvNum().compareTo(line.getNum())<0){
             return -1;
           }
           inv.setInvNum(inv.getInvNum().subtract(line.getNum()));
           inv.setFrozenNum(inv.getFrozenNum().add(line.getNum()));
           this.invmapper2.updateByPrimaryKeySelective(inv);
      }else if(invs ==null||invs.size()==0){
          logger.error("订单扣减客户库存，无库存数据 orderid:{} merch:{} material:{} org:{}", retailOrder.getId(), 
        		  merch.getId(), line.getMaterialId(), retailOrder.getOrganizationId());
          return -1;
      }
      
      //修改库存期间表
      CMerchCustProductBalancesExample balancesEx = new CMerchCustProductBalancesExample();
      CMerchCustProductBalancesExample.Criteria balancesExt= balancesEx.createCriteria();
      balancesExt.andMerchCustIdEqualTo(merch.getId());
      balancesExt.andMaterialIdEqualTo(line.getMaterialId());
      balancesExt.andPeriodEqualTo(period);
      List<CMerchCustProductBalances> balances = this.bmapper.selectByExample(balancesEx);
      CMerchCustProductBalances balance = new CMerchCustProductBalances();
      if(balances.size()==1 && balances!=null){
          balance = balances.get(0);
      }else{
    	  logger.error("获取库存期间数据失败 orderid:{} merch:{} material:{} org:{}", retailOrder.getId(), 
        		  merch.getId(), line.getMaterialId(), retailOrder.getOrganizationId());
    	  return -1;
    	  
      }
      balance.setcNum(balance.getcNum().add(line.getNum()));
      this.bmapper.updateByPrimaryKeySelective(balance);
      return 1;
	}
	
	
	/**
	 * 更新客户库存数据（傳入更新數量）
	 * @param header
	 * @param line
	 * @param contract
	 * @param period
	 */
	/*public void doAddProductInv(OmOrderHeadersAll header, OmOrderSpilts line,CMerchCustContract contract,String period,BigDecimal num){
		CMerchCustProudctInvExample invex = new CMerchCustProudctInvExample();
		CMerchCustProudctInvExample.Criteria invext = invex.createCriteria();
		invext.andMerchCustIdEqualTo(header.getMerchCustId());
		invext.andMaterialIdEqualTo(line.getMaterialId());
		invext.andOrganizationIdEqualTo(header.getOrganizationId());
		List<CMerchCustProudctInv> invs = this.invmapper2.selectByExample(invex);
		CMerchCustProudctInv inv = new CMerchCustProudctInv();
		if(invs !=null && invs.size()==1){
			 inv =invs.get(0);
		}else if(invs ==null||invs.size()==0){
			inv.setMerchCustId(header.getMerchCustId());
			inv.setOrganizationId(header.getOrganizationId());
			inv.setCreateTs(new Date());
			inv.setContractId(contract.getId());
			inv.setInvNum(num);
			inv.setFrozenNum(BigDecimal.ZERO);
			inv.setCreateOid(contract.getCreateOid());
			inv.setMaterialId(line.getMaterialId());
			this.invmapper2.insert(inv);
		}
		//插入日志表
		CMerchCustProductLog  log = new CMerchCustProductLog();
		log.setMerchCustId(header.getMerchCustId());
		log.setCreateTs(new Date());
		log.setdNum(num);
		log.setMaterialId(line.getMaterialId());
		log.setVoucherId(header.getId().toString());
		log.setPeriod(period);
		log.setType("1");
		log.setSource("1");
		log.setcNum(BigDecimal.ZERO);
		log.setPrice(line.getPrice());
		log.setAmt(line.getPrice().multiply(num));
		log.setRemark("调拨单生成订单入库");
		this.logmapper.insert(log);
		
		//更新库存表
		inv.setInvNum(inv.getInvNum().add(num));
		this.invmapper2.updateByPrimaryKeySelective(inv);
	}*/
	/**
	 * 更新客户期间数据
	 * @param header
	 * @param period
	 */
	public void doUpdateAccount(OmOrderHeadersAll header, String period) {
		//判断订单是否已经存在于日志表中
		CMerchCustAccountLogExample accountlogex =new CMerchCustAccountLogExample();
		CMerchCustAccountLogExample.Criteria logext =accountlogex.createCriteria();
		logext.andOrderIdEqualTo(header.getId());
		logext.andMerchCustIdEqualTo(header.getMerchCustId());
		logext.andStatesEqualTo("S");
		List<CMerchCustAccountLog> accountlogs = this.accountlogmapper.selectByExample(accountlogex);
		if(accountlogs !=null && accountlogs.size() >0){
			return;
		}
		//获取订单行数据
		OmOrderLinesAllExample linesEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria linesExt = linesEx.createCriteria();
		linesExt.andHeaderIdEqualTo(header.getId());
		List<OmOrderLinesAll> lines = this.linemapper.selectByExample(linesEx);
		BigDecimal orderAmt = BigDecimal.ZERO;
		BigDecimal hbAmt = BigDecimal.ZERO;
		if(lines !=null && lines.size() >0){
			for(OmOrderLinesAll line:lines){
				//计算授信和货补金额
				orderAmt = orderAmt.add(line.getNum().multiply(line.getOrderPrice()));
				hbAmt= hbAmt.add(line.getHbNum().multiply(line.getOrderPrice()));
			}
		}
		CMerchCustBase merch = this.merchmapper.selectByPrimaryKey(header.getMerchCustId());//获取客户信息
		if(orderAmt.compareTo(BigDecimal.ZERO) >0){
			//插入日志表和更新期间表数据
			insertiAccountLog(header.getMerchCustId(),header.getId(),
					merch.getOrganizationId(),  period, orderAmt, AccountTypeEnum.credit.getCode(),
					AccountLogTypeEnum.allocationOrder.getCode());
		}
		if(hbAmt.compareTo(BigDecimal.ZERO) >0){
			//插入日志表和更新期间表数据
			insertiAccountLog(header.getMerchCustId(),header.getId(),
					merch.getOrganizationId(),  period, hbAmt, AccountTypeEnum.subsidy.getCode(),
					AccountLogTypeEnum.allocationOrder.getCode());
		}
		

		// 扣减账户余额
		CMerchCustAccountExample accountex = new CMerchCustAccountExample();
		CMerchCustAccountExample.Criteria accountext = accountex.createCriteria();
		accountext.andMerchCustIdEqualTo(header.getMerchCustId());
		accountext.andOrganizationIdEqualTo(header.getOrganizationId());
		List<CMerchCustAccount> accounts = this.accountmapper.selectByExample(accountex);
		if (accounts.size() == 1 && accounts != null) {
			CMerchCustAccount account = accounts.get(0);
			account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
			account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
			account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
			account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
			if (hbAmt.compareTo(BigDecimal.ZERO)>0) {
				account.setSubsidyAmt(account.getSubsidyAmt().subtract(hbAmt));
			}
			if (orderAmt.compareTo(BigDecimal.ZERO)>0) {
				account.setCreditAmt(account.getCreditAmt().subtract(orderAmt));
			}
			this.accountmapper.updateByPrimaryKeySelective(account);
		}
	}
	
	private void insertiAccountLog(Long merchCustId, Long headerId,
			String orgid, String period, BigDecimal amt, String accountType,
			String type) {
		// 插入日志表
		CMerchCustAccountLog accountlog2 = new CMerchCustAccountLog();
		accountlog2.setAccountType(accountType);
		accountlog2.setMerchCustId(merchCustId);
		accountlog2.setCreateTs(new Date());
		accountlog2.setcAmt(amt);
		accountlog2.setOrderId(headerId);
		accountlog2.setOrganizationId(orgid);
		accountlog2.setPeriod(period);
		accountlog2.setdAmt(BigDecimal.ZERO);
		accountlog2.setType(type);//8
		accountlog2.setStates("S");
		this.accountlogmapper.insert(accountlog2);
		// 扣减账户期间数据表
		CMerchCustBalancesExample hbex = new CMerchCustBalancesExample();
		CMerchCustBalancesExample.Criteria hbext = hbex.createCriteria();
		hbext.andMerchCustIdEqualTo(merchCustId);
		hbext.andOrganizationIdEqualTo(orgid);
		hbext.andAccountTypeEqualTo(accountType);
		hbext.andPeriodEqualTo(period);
		List<CMerchCustBalances> hbbalances = this.balancesmapper
				.selectByExample(hbex);
		if (hbbalances.size() == 1 && hbbalances != null) {
			CMerchCustBalances hb = hbbalances.get(0);
			hb.setcAmt(hb.getcAmt().add(amt));
			this.balancesmapper.updateByPrimaryKeySelective(hb);
		}
	}
	
	private Long bulidOrder(OmOrderHeadersAll header,CMerchCustBase  merch, TEmployee user) throws Exception{
		//获取合资盐业公司
		CMerchCustBase cust = this.merchmapper.selectByPrimaryKey(merch.getPid());
		OmOrderHeadersAll  h = new OmOrderHeadersAll();
		h.setMerchCustId(cust.getId());
		//h.setShipId(merch.getId());
		h.setShipId(header.getShipId());
		h.setBillTo(cust.getId());
		h.setCreateTs(new Date());
		h.setCreateOid(header.getCreateOid());
		h.setDiscountAmt(BigDecimal.ZERO);
		h.setAmt(BigDecimal.ZERO);
		h.setCreditAmt(BigDecimal.ZERO);
		h.setHbAmt(BigDecimal.ZERO);
		h.setOrderAmt(BigDecimal.ZERO);
		h.setOrderType("7");
		h.setOrganizationId(cust.getOrganizationId());
		h.setStates("1");
		h.setUpdateTs(new Date());
		h.setUpdateOid(header.getCreateOid());
		h.setStationId(header.getStationId());
		h.setSalesrepId(header.getSalesrepId());
		h.setRemark("合资盐业公司下调拨单生成订单");
		h.setProviId(header.getProviId());
		h.setRegionId(header.getRegionId());
		h.setAttribute13(header.getId().toString());
		h.setRdcCode(header.getRdcCode());
		h.setFreight(header.getFreight());
		this.headermapper.insert(h);
		
		BigDecimal orderamt=addLinesDetails(h,cust,header.getId());
		//更新销售订单头表金额
		updateHeaderAmt(h,orderamt);
		
		//扣减资金
		updateInvAndAmt(header);
		return h.getId();
	}
	private void updateInvAndAmt(OmOrderHeadersAll header){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		//扣减客户资金
		doUpdateAccount(header,sf.format(new Date()));
		
		//合作倉儲服務商庫存數量增加修改為訂單確認收貨時操作
		/*//获取合同数据用于扣减库存
		CMerchCustContractExample  contractEx = new CMerchCustContractExample();
		CMerchCustContractExample.Criteria contractExt = contractEx.createCriteria();
		contractExt.andMerchCustIdEqualTo(header.getMerchCustId());
		contractExt.andOrganizationIdEqualTo(header.getOrganizationId());
		contractExt.andStatesEqualTo("4");
		List<CMerchCustContract> contracts = this.contractmapper.selectByExample(contractEx);
		CMerchCustContract  contract = new CMerchCustContract();
		if(contracts.size()==1 && contracts !=null){
			contract = contracts.get(0);
		}else{
			return;
		}
		//获取订单行数据
		OmOrderSpiltsExample  ex = new OmOrderSpiltsExample();
		OmOrderSpiltsExample.Criteria ext = ex.createCriteria();
		ext.andHeaderIdEqualTo(header.getId());
		List<OmOrderSpilts> lines = this.spiltmapper.selectByExample(ex);
		if(lines !=null && lines.size()>0){
			for (OmOrderSpilts line :lines){					
				this.doAddProductInv(header,line,contract,sf.format(new Date()));
			}
		}*/
	}
	private void updateHeaderAmt(OmOrderHeadersAll h,BigDecimal amt) {
		//获取客户资金
		CMerchCustAccountExample accountEx = new CMerchCustAccountExample();
		CMerchCustAccountExample.Criteria accountExt = accountEx.createCriteria();
		accountExt.andMerchCustIdEqualTo(h.getMerchCustId());
		accountExt.andOrganizationIdEqualTo(h.getOrganizationId());
		List<CMerchCustAccount> accounts = this.accountmapper.selectByExample(accountEx);
		CMerchCustAccount account = accounts.get(0);
		if(amt.compareTo(BigDecimalASME.multiply(account.getCashAmt())) <=0){
			//订单金额小于合资盐业公司现金金额
			h.setCashAmt(amt);
		}else{
			//订单金额大于现金金额
			h.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
			h.setCreditAmt(amt.subtract(BigDecimalASME.multiply(account.getCashAmt())));
		}
		//h.setAmt(amt);
		//h.setOrderAmt(amt);
		this.headermapper.updateByPrimaryKeySelective(h);
	}

	private BigDecimal getPriceByMaterialId(OmOrderLinesAll line,CMerchCustBase merch){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("materialId", line.getMaterialId());
		//params.put("type", line.getAttribute11());
		params.put("merchCustId", merch.getId());
		BigDecimal price = BigDecimal.ZERO;
		//attribute11等于1表示套餐数据
	    price ="1".equals(line.getAttribute11())?this.mapper.getCombinationPrice(params):this.mapper.getPriceByMaterialId(params);
	    return price !=null?price:BigDecimal.ZERO;
	}
	
	private BigDecimal addLinesDetails(OmOrderHeadersAll h, CMerchCustBase merch,
			Long headerId) throws Exception {
		BigDecimal orderamt =BigDecimal.ZERO;
		BigDecimal amt = BigDecimal.ZERO;
		BigDecimal hbAmt= BigDecimal.ZERO;
		// 获取订单行数据
		OmOrderLinesAllExample lineEx = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineExt = lineEx.createCriteria();
		lineExt.andHeaderIdEqualTo(headerId);
		List<OmOrderLinesAll> lines = this.linemapper.selectByExample(lineEx);
		if (lines != null && lines.size() > 0) {
			for (OmOrderLinesAll line : lines) {
				Long lineid = line.getId();
				OmOrderLinesAll l = line;
				l.setId(null);
				l.setHeaderId(h.getId());
				//BigDecimal price = getPriceByMaterialId(line,merch);//获取合作盐业公司价格
				BigDecimal price =line.getOrderPrice().add(merch.getAdjustPrice());
				l.setPrice(price);
				l.setOrderPrice(price);
				l.setHighPrice(BigDecimal.ZERO);
				BigDecimal orderAmt = price.multiply(l.getNum()).setScale(0, BigDecimal.ROUND_HALF_UP);//计算现金授信购买金额
				BigDecimal hbamt = price.multiply(l.getHbNum()).setScale(0, BigDecimal.ROUND_HALF_UP);//计算货补金额
				l.setAmt(orderAmt.add(hbamt));
				l.setOrderAmt(orderAmt);
				l.setHbAmt(hbamt);
				l.setDiscountAmt(hbamt);
				l.setFreight(line.getFreight());
				this.linemapper.insert(l);
				orderamt =orderamt.add(orderAmt);
				amt = amt.add(l.getAmt());
				hbAmt = hbAmt.add(hbamt);
				addSpiltData(headerId,lineid,price,h,l);
			}
		}
		h.setAmt(amt);
		h.setOrderAmt(orderamt);
		h.setHbAmt(hbAmt);
		h.setDiscountAmt(hbAmt);
		this.headermapper.updateByPrimaryKey(h);
		return orderamt;
	}
	
	private void addSpiltData(Long headerId,Long lineid,BigDecimal price,OmOrderHeadersAll h,OmOrderLinesAll l){
		// 获取拆分行数据
		OmOrderSpiltsExample spiltEx = new OmOrderSpiltsExample();
		OmOrderSpiltsExample.Criteria spiltExt = spiltEx
				.createCriteria();
		spiltExt.andHeaderIdEqualTo(headerId);
		spiltExt.andLineIdEqualTo(lineid);
		List<OmOrderSpilts> spilts = this.spiltmapper
				.selectByExample(spiltEx);
		if (spilts.size() > 0 && spilts != null) {
			for (OmOrderSpilts spilt : spilts) {
				OmOrderSpilts s = spilt;
				if("1".equals(s.getType()) ||"2".equals(s.getType())){
					s.setPrice("2".equals(spilt.getType())?BigDecimal.ZERO:price);
					s.setHeaderId(h.getId());
					s.setLineId(l.getId());
					s.setAmt("2".equals(spilt.getType())?BigDecimal.ZERO:price.multiply(s.getNum()).setScale(0, BigDecimal.ROUND_HALF_UP));
					s.setMerchCustId(h.getMerchCustId());
					s.setShipTo(h.getShipId());
					s.setCreateTs(new Date());
					s.setId(null);
					this.spiltmapper.insert(s);
				}
			}
		}
	}

	@Override
	public AjaxDTO getOrderLineDetails(Long id) {
		AjaxDTO dto = new AjaxDTO();
		List<AllocationDetailsDTO> list = this.mapper.selectOrderLineDetails(id);
		/*if(list.size()>0 && list !=null){
			for(AllocationDetailsDTO line:list){
				line.setWeight(UnitConverter.convert(line.getNum(), line.getMaterialId(), MaterialUnit.TO));
			}
		}*/
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	/*@Override
	public void updateCustInv(Long id) {
		List<ArInvoicesItemDTO> list = this.mapper.selectArInvoicesItems(id);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		if(list.size()>0 && list !=null){
			for(ArInvoicesItemDTO item :list){
				OmOrderSpiltsExample ex = new OmOrderSpiltsExample();
				OmOrderSpiltsExample.Criteria ext = ex.createCriteria();
				ext.andHeaderIdEqualTo(item.getCrmorderId());
				ext.andMaterialIdEqualTo(item.getMaterialId());
				ext.andOrderitemSapNoEqualTo(item.getOrderItemId());
				ext.andOrganizationIdEqualTo(item.getOrganizationId());
				List<OmOrderSpilts> lines = this.spiltmapper.selectByExample(ex);
				OmOrderSpilts line  = new OmOrderSpilts();
				if(lines !=null && lines.size()==1){					
					lines.get(0);
				}else{
					continue;
				}
				OmOrderHeadersAll header = this.headermapper.selectByPrimaryKey(item.getCrmorderId());
				CMerchCustContractExample contractEx  = new CMerchCustContractExample();
				CMerchCustContractExample.Criteria contractExt= contractEx.createCriteria();
				contractExt.andMerchCustIdEqualTo(item.getMerchCustId());
				contractExt.andOrganizationIdEqualTo(item.getOrganizationId());
				contractExt.andStatesEqualTo("4");
				List<CMerchCustContract> contracts = this.contractmapper.selectByExample(contractEx);
				CMerchCustContract contract = contracts.size()==1?contracts.get(0):null;
				doAddProductInv(header,line,contract,sf.format(new Date()),item.getNum());			
			}
		}
	}*/

	@Override
	public OmOrderHeadersAll selectByPrimaryKey(Long id) {
		return this.headermapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 验证合作仓储服务商调拨单转化订单时对应盐业公司的价格
	 * @param id
	 * @param merchid
	 * @return
	 */
	private Map<String,Object> validateMerchNumByPid(Long id,Long merchid,Long merchpid){
		Map<String,Object> result = new HashMap<String, Object>();
		OmOrderLinesAllExample ex = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria ext = ex.createCriteria();
		ext.andHeaderIdEqualTo(id);
		List<OmOrderLinesAll> list = this.linemapper.selectByExample(ex);
		CMerchCustBase merch = this.merchmapper.selectByPrimaryKey(merchpid);
		String msg="";
		String type ="";
		if(list !=null && list.size()>0){
			for(OmOrderLinesAll line :list){
				BigDecimal price = getPriceByMaterialId(line,merch);
				if(price.compareTo(BigDecimal.ZERO)==0){
					type ="E";
					msg +=merch.getName()+"没有维护物料"+line.getMaterialId()+"价格";
				}
			}
		}else{
			type="E";
			msg ="获取订单数据失败";
		}
		result.put("type", type);
		result.put("msg", msg);
		return result;
	}
	
	/*@Override
	public int addMerchInv(Long transferOrderId){
	  OmOrderHeadersAll  header = this.headermapper.selectByPrimaryKey(transferOrderId);
      //通过
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
      //获取合同数据
      CMerchCustContractExample  contractEx = new CMerchCustContractExample();
      CMerchCustContractExample.Criteria contractExt = contractEx.createCriteria();
      contractExt.andMerchCustIdEqualTo(header.getMerchCustId());
      contractExt.andOrganizationIdEqualTo(header.getOrganizationId());
      contractExt.andStatesEqualTo("4");
      List<CMerchCustContract> contracts = this.contractmapper.selectByExample(contractEx);
      CMerchCustContract  contract = new CMerchCustContract();
      if(contracts.size()==1 && contracts !=null){
          contract = contracts.get(0);
      }else{
          return 0;
      }
      //获取订单行数据
      OmOrderSpiltsExample  ex = new OmOrderSpiltsExample();
      OmOrderSpiltsExample.Criteria ext = ex.createCriteria();
      ext.andHeaderIdEqualTo(header.getId());
      List<OmOrderSpilts> lines = spiltmapper.selectByExample(ex);
      if(lines !=null && lines.size()>0){
          for (OmOrderSpilts line :lines){    
              //更新客户库存数据
             // doAddProductInv(header,line,contract,sf.format(new Date()));
          }
      }
      return 1;
	}*/
	
	@Override
	public String generateTransferDetails(Map<String, Object> param) throws IOException{
	  List<CustomerInvAllocationDTO> details = mapper.transferDetails(param);
	  
	  // 创建文件
      String fileName = UUID.randomUUID().toString() + ".xlsx";
      String filePath = Files.standardFolderPath(basePath) + fileName;
      File file = new File(filePath);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      String title[] = {"销售组织", "大区", "业务省", "行政省", "rdc", "送达方名称", "地址", "sap客户编码", "调拨单号", "CRM订单号", "创建时间",
         "状态", "物料编码", "sku", "业务模式", "单价", "下单数量", "下单金额", "发货数量", "发货重量", "发货金额","发货时间"};
      
      Workbook wb = null;
      OutputStream stream = null;
      try{
        wb = new XSSFWorkbook();
        stream = new FileOutputStream(file);
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        // 写入头数据
        Row header = (Row) sheet1.createRow(0);
        // 循环写入头列数据
        for (int j = 0; j < title.length; j++) {
          Cell cell = header.createCell(j);
          cell.setCellValue(title[j]);
        }
        // 内容
        int rowIndex = 1;
        for (CustomerInvAllocationDTO detail : details) {
          fillRow(sheet1, rowIndex++, detail);
        }
        wb.write(stream);
      }finally{
        IOUtils.closeQuietly(stream);
        IOUtils.closeQuietly(wb);
      }
      
      return fileName;
	}
	
	private void fillRow(Sheet sheet, int rowIndex, CustomerInvAllocationDTO detail) {
      int cellIndex = 0;
      Row row = (Row) sheet.createRow(rowIndex);
      // 写入列数据
      Excels.fillCell(row, cellIndex++, detail.getOrgname());
      Excels.fillCell(row, cellIndex++, detail.getReginname());
      Excels.fillCell(row, cellIndex++, detail.getProvname());
      Excels.fillCell(row, cellIndex++, detail.getProv());
      Excels.fillCell(row, cellIndex++, detail.getRdcName());
      Excels.fillCell(row, cellIndex++, detail.getCustname());
      Excels.fillCell(row, cellIndex++, detail.getAddress());
      Excels.fillCell(row, cellIndex++, detail.getSapCustomerId());
      Excels.fillCell(row, cellIndex++, detail.getId().toString());
      Excels.fillCell(row, cellIndex++, detail.getOrderId()==null?"":detail.getOrderId().toString());
      Excels.fillCell(row, cellIndex++, DateUtil.format(detail.getCreateTs(), "yyyy-MM-dd"));
      Excels.fillCell(row, cellIndex++, detail.getStates());
      Excels.fillCell(row, cellIndex++, detail.getMaterialId());
      Excels.fillCell(row, cellIndex++, detail.getSku());
      Excels.fillCell(row, cellIndex++, detail.getAgentType());
      Excels.fillCell(row, cellIndex++, detail.getPrice().toString());
      Excels.fillCell(row, cellIndex++, detail.getNum().toString());
      Excels.fillCell(row, cellIndex++, detail.getAmt().toString());
      Excels.fillCell(row, cellIndex++, detail.getAllocationNum().toString());
      Excels.fillCell(row, cellIndex++, detail.getAllocationWeight().toString());
      Excels.fillCell(row, cellIndex++, detail.getAllocationAmt().toString());
      Excels.fillCell(row, cellIndex++, detail.getSendTime());
    }
	
	@Override
	public String generateInvDetail(AjaxDTO bean, CMerchCustProudctInvV inv, List<Long> stationids, TEmployee user) throws IOException{
      CMerchCustProudctInvVExample ex = new CMerchCustProudctInvVExample();
      CMerchCustProudctInvVExample.Criteria ext =ex.createCriteria();
      if(user.getMerchId() !=null){
          ext.andMerchCustIdEqualTo(user.getMerchId());
      }else{
          if(stationids.size()>0 && stationids !=null){
              ext.andStationIdIn(stationids);
          }
      }
      List<CMerchCustProudctInvV> list = this.invmapper.selectByExample(ex);
      
     // 创建文件
      String fileName = UUID.randomUUID().toString() + ".xlsx";
      String filePath = Files.standardFolderPath(basePath) + fileName;
      File file = new File(filePath);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      String title[] = {"销售组织", "客户名", "客户sap编码", "物料名", "物料编码", "虚拟仓", "库存数量库存数量(包/袋/瓶)", "库存数量(箱/吨)"};
      
      Workbook wb = null;
      OutputStream stream = null;
      try{
        wb = new XSSFWorkbook();
        stream = new FileOutputStream(file);
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        // 写入头数据
        Row header = (Row) sheet1.createRow(0);
        // 循环写入头列数据
        for (int j = 0; j < title.length; j++) {
          Cell cell = header.createCell(j);
          cell.setCellValue(title[j]);
        }
        // 内容
        int rowIndex = 1;
        for (CMerchCustProudctInvV detail : list) {
          fillRow(sheet1, rowIndex++, detail);
        }
        wb.write(stream);
      }finally{
        IOUtils.closeQuietly(stream);
        IOUtils.closeQuietly(wb);
      }
      return fileName;
	}
	
	private void fillRow(Sheet sheet, int rowIndex, CMerchCustProudctInvV detail) {
      int cellIndex = 0;
      Row row = (Row) sheet.createRow(rowIndex);
      // 写入列数据
      Cell orgname = row.createCell(cellIndex++);
      orgname.setCellValue(detail.getOrgname());
      Cell merch = row.createCell(cellIndex++);
      merch.setCellValue(detail.getCustname());
      Cell merchSap = row.createCell(cellIndex++);
      merchSap.setCellValue(detail.getSapCustomerId());
      Cell sku = row.createCell(cellIndex++);
      sku.setCellValue(detail.getSku());
      Cell materialId = row.createCell(cellIndex++);
      materialId.setCellValue(detail.getMaterialId());
      Cell rdc = row.createCell(cellIndex++);
      rdc.setCellValue(detail.getRdcname());
      Cell invWithBase = row.createCell(cellIndex++);
      invWithBase.setCellValue(detail.getInvNum().toPlainString());
      Cell invWithbox = row.createCell(cellIndex++);
      invWithbox.setCellValue(detail.getInvNum().divide(new BigDecimal(detail.getAmounts())).toPlainString());

    }

	@Override
	public String closeOrder(Long id) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("p_order_id", id);
		this.mapper.closeOrder(params);
		return (String)params.get("p_result");
	}
}
