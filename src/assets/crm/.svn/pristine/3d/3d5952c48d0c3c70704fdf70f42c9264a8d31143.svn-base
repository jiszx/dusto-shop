package com.hhnz.account.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.account.dto.AllocationDetailsDTO;
import com.hhnz.account.dto.OrderReceiveDTO;
import com.hhnz.account.dto.OrderVerifieDTO;
import com.hhnz.account.mapper.CMerchAccountAllocationMapper;
import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.mapper.CMerchUpaccountMapper;
import com.hhnz.account.mapper.CapitalAllocationMapper;
import com.hhnz.account.model.CMerchAccountAllocation;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAccountV;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.account.service.ICapitalAllocationService;
import com.hhnz.api.cache.CacheService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseExample;
import com.hhnz.jco.business.account.AccountAdjustRFC;
import com.hhnz.jco.business.account.InputDTO;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

@Service
@Transactional
public class CapitalAllocationServiceImpl implements ICapitalAllocationService {
	private static Logger logger = Logger.getLogger(CapitalAllocationServiceImpl.class);
	@Resource
	private CMerchCustBaseMapper  merchmapper;
	
	@Resource
	private CacheService  cacheService;
	
	@Resource
	private  CapitalAllocationMapper mapper;
	
	@Resource
	private CMerchCustAccountMapper accountmapper;
	
	@Resource
	private CMerchUpaccountMapper upaccountmapper;
	
	@Resource
	private CMerchCustAccountLogMapper logmapper;
	
	@Resource
	private AccountAdjustRFC accountAdjustRFC;
	
	@Resource
	private  CMerchCustBalancesMapper balancemapper;
	
	@Resource
	private CMerchAccountAllocationMapper  allocationmapper;
	@Override
	public CMerchCustBase getmerchByID(Long merchId) {
		return this.merchmapper.selectByPrimaryKey(merchId);
	}
	
	@Override
	public List<CMerchCustBase> getMerchs(List<Long> stationids,String custType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("stationdis", stationids);
		params.put("custType", custType);
		return this.mapper.getMerchs(params);
	}

	@Override
	public AjaxDTO getReceives(Long merchCustId) {
		AjaxDTO dto = new AjaxDTO();
		List<CMerchUpaccount> list = this.mapper.getReceives(merchCustId);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	@Override
	public AjaxDTO getRetailer(Long merchCustId) {
		CMerchCustBaseExample  ex =new CMerchCustBaseExample();
		CMerchCustBaseExample.Criteria ext =ex.createCriteria();
		ext.andPidEqualTo(merchCustId);
		ext.andStatesEqualTo("3");
		ext.andCustTypeEqualTo("5");
		List<CMerchCustBase> list = this.merchmapper.selectByExample(ex);
		int total = this.merchmapper.countByExample(ex);
		AjaxDTO dto = new AjaxDTO();
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO getRetailerAccount(String ids) {
		AjaxDTO dto = new AjaxDTO();
		List<Long> merchids = new ArrayList<Long>();
		if(StringUtils.isEmpty(ids)){
			return dto;
		}
		for(String id:ids.split(",")){
			merchids.add(Long.parseLong(id));
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ids", merchids);
		List<CMerchCustAccountV> list = this.mapper.selectRetailerAccount(params);
		int total = this.mapper.countRetailerAccount(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> doAllocation(String data,Long receiveId,TEmployee user,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<CMerchCustAccountV> list = new ArrayList<CMerchCustAccountV>();
		JSONArray jsonArray = JSONArray.fromObject(data);
		try{
			list = (List<CMerchCustAccountV>) JSONArray.toCollection(jsonArray,
					CMerchCustAccountV.class);			
		}catch(Exception e){
			logger.info("分配资金出错");
			logger.error(e.getMessage());
		}
		//获取分配的资金上账记录
		CMerchUpaccount  upaccount = this.upaccountmapper.selectByPrimaryKey(receiveId);
		//需要推送SAP的数据
		List<CMerchCustAccountLog> temp = new ArrayList<>();
		if(list !=null && list.size() >0){
			//获取当前期间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String period = sdf.format(new Date());// 当前年月
			for(CMerchCustAccountV accountv :list){
				if(accountv.getAllocationAmt().compareTo(BigDecimal.ZERO)>0){
					//获取零售商资金余额
					CMerchCustAccountExample accountex = new CMerchCustAccountExample();
					CMerchCustAccountExample.Criteria accountext = accountex.createCriteria();
					accountext.andMerchCustIdEqualTo(accountv.getMerchCustId());
					if(!StringUtils.isEmpty(accountv.getId())){
						accountext.andIdEqualTo(accountv.getId());
					}
					List<CMerchCustAccount> accounts =this.accountmapper.selectByExample(accountex);
					if(accounts ==null ||accounts.size()>1){
						continue;
					}
					CMerchCustAccount account = accounts.get(0);
					
					//零售商资金上账插入
					CMerchUpaccount retailerupaccount = this.addUpAccount(upaccount, user, account, BigDecimalASME.multiply(accountv.getAllocationAmt()));
					
					//插入日志表数据
					CMerchCustAccountLog log = this.addAccountLog(retailerupaccount, account, user, period);
					
					//插入分配表
					CMerchAccountAllocation  allocation = new CMerchAccountAllocation();
					allocation.setUpaccountId(upaccount.getId());
					allocation.setAllocationId(retailerupaccount.getId());
					allocation.setAllocationDate(new Date());
					allocation.setAllocationOid(user.getId());
					this.allocationmapper.insert(allocation);
					// 推送SAP
					temp.add(log);
					
					//修改上账已分配金额
					upaccount.setAllocationAmt(upaccount.getAllocationAmt().add(BigDecimalASME.multiply(accountv.getAllocationAmt())));
					this.upaccountmapper.updateByPrimaryKeySelective(upaccount);
				}
			}
		}else{
			result.put("type", "500");
			result.put("msg", "传入数据出错，请检查数据格式");
			return result;
		}
		cacheService.put(upaccount.getMerchCusId().toString()+"flag", temp.size());
		for (CMerchCustAccountLog log : temp) {
			SendToSap(log);
		}
		result.put("type", "200");
		result.put("msg", "分配资金处理中");
		return result;
	}
	
	public CMerchUpaccount addUpAccount(CMerchUpaccount upaccount,TEmployee user,CMerchCustAccount account,BigDecimal amt){
		//插入上账记录
		CMerchUpaccount retailerupaccount = new CMerchUpaccount();
		retailerupaccount.setOrganizationId(upaccount.getOrganizationId());
		retailerupaccount.setCreateTs(new Date());
		retailerupaccount.setCreateOid(user.getId());
		retailerupaccount.setPayName(upaccount.getPayName());
		retailerupaccount.setPayBank(upaccount.getPayBank());
		retailerupaccount.setPayCity(upaccount.getPayCity());
		retailerupaccount.setPayAmt(amt);
		retailerupaccount.setPayBankNo(upaccount.getPayBankNo());
		retailerupaccount.setPayDate(upaccount.getPayDate());
		retailerupaccount.setStates("5");
		retailerupaccount.setSalesrepId(upaccount.getSalesrepId());
		retailerupaccount.setSalesrepDate(upaccount.getSalesrepDate());
		retailerupaccount.setAuditTs(upaccount.getAuditTs());
		retailerupaccount.setAuditOid(upaccount.getAuditOid());
		retailerupaccount.setUpdateTs(new Date());
		retailerupaccount.setUpdateOid(user.getId());
		retailerupaccount.setPayType(upaccount.getPayType());
		retailerupaccount.setMerchCusId(account.getMerchCustId());
		retailerupaccount.setBankAccount(upaccount.getBankAccount());
		retailerupaccount.setBillInDate(upaccount.getBillInDate());
		retailerupaccount.setBillOutDate(upaccount.getBillOutDate());
		retailerupaccount.setBillNo(upaccount.getBillNo());
		retailerupaccount.setBillBank(upaccount.getBillBank());
		retailerupaccount.setBillInName(upaccount.getBillInName());
		retailerupaccount.setBillOutName(upaccount.getBillOutName());
		retailerupaccount.setReceviceAmt(BigDecimal.ZERO);
		retailerupaccount.setUnreceviceAmt(amt);
		this.upaccountmapper.insert(retailerupaccount);
		return retailerupaccount;
	}
	
	public CMerchCustAccountLog addAccountLog(CMerchUpaccount upaccount,CMerchCustAccount account,TEmployee user,String period){
		CMerchCustAccountLog log = new CMerchCustAccountLog();
		log.setMerchCustId(account.getMerchCustId());
		log.setOrganizationId(account.getOrganizationId());
		log.setType("1");
		log.setdAmt(upaccount.getPayAmt());
		log.setAccountType("1");
		log.setOrderId(upaccount.getId());
		log.setPeriod(period);
		log.setCreateTs(new Date());
		log.setCreater(user.getIsSalesman());
		this.logmapper.insert(log);
		return log;
	}
	/**
	 * 推送SAP
	 * 
	 * @param accountlog资金上账日记记录
	 * @param upaccount
	 *            资金上账信息
	 * @return int
	 */
	public Map<String, Object> SendToSap(CMerchCustAccountLog accountlog){
		Map<String, Object> result = new HashMap<String, Object>();
		InputDTO input = accountAdjustRFC.constructInputParam(accountlog
				.getId());
		logger.info("提交资金上账异步处理");
		accountAdjustRFC.executeInThread(input, CapitalAllocationCallBack.class.getSimpleName());
		result.put("type", "200");
		result.put("msg", "资金上账处理中");
		return result;
	}

	@Override
	public AjaxDTO getOrders(Long merchCustId) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderVerifieDTO> list = this.mapper.getOrders(merchCustId);
		int total = this.mapper.countOrders(merchCustId);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO getRetailerOrders(String ids,String type) {
		AjaxDTO dto = new AjaxDTO();
		List<Long> merchids = new ArrayList<Long>();
		if(StringUtils.isEmpty(ids)){
			return dto;
		}
		for(String id:ids.split(",")){
			merchids.add(Long.parseLong(id));
		}
		if(merchids ==null || merchids.size()==0){
			return dto;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ids", merchids);
		params.put("type", type);
		List<OrderReceiveDTO> list = this.mapper.selectRetailerOrders(params);
		int total = this.mapper.countRetailerOrders(params);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO allocationDetail(Long upaccountId,AjaxDTO bean) {
		AjaxDTO dto = new AjaxDTO();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("begin", bean.getOffset());
		params.put("end", bean.getLimit() + bean.getOffset());
		params.put("upaccountId", upaccountId);
		List<AllocationDetailsDTO> list = this.mapper.getAllocationDetail(params);
		int total = this.mapper.countAllocationDetail(upaccountId);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	@Override
	public AjaxDTO getRecord(Long merchCustId) {
		AjaxDTO dto = new AjaxDTO();
		List<OrderVerifieDTO> list = this.mapper.getRecords(merchCustId);
		int total = this.mapper.countRecords(merchCustId);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}
}
