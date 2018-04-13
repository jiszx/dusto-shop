package com.hhnz.customerInv.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.crm.mapper.TMaterialBaseMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialBase;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customerInv.dto.AdjustMaterialDTO;
import com.hhnz.customerInv.dto.CMerchCustInvAdjustDTO;
import com.hhnz.customerInv.mapper.CMerchCustProductAdjustMapper;
import com.hhnz.customerInv.mapper.CMerchCustProductBalancesMapper;
import com.hhnz.customerInv.mapper.CMerchCustProductLogMapper;
import com.hhnz.customerInv.mapper.CMerchCustProudctInvMapper;
import com.hhnz.customerInv.mapper.CustomerInvAdjustMapper;
import com.hhnz.customerInv.model.CMerchCustProductAdjust;
import com.hhnz.customerInv.model.CMerchCustProductBalances;
import com.hhnz.customerInv.model.CMerchCustProductBalancesExample;
import com.hhnz.customerInv.model.CMerchCustProductLog;
import com.hhnz.customerInv.model.CMerchCustProudctInv;
import com.hhnz.customerInv.model.CMerchCustProudctInvExample;
import com.hhnz.customerInv.service.CustomerInvAdjustService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

/**
 * 客户库存调整Service
 * @author hhnz-skevin 2016-12-09
 *
 */
@Service
@Transactional
public class CustomerInvAdjustServiceImpl implements CustomerInvAdjustService {
	private static Logger logger = Logger
			.getLogger(CustomerInvAdjustServiceImpl.class);
	@Resource
	private CustomerInvAdjustMapper adjustUtilMapper;
	
	@Resource
	private CMerchCustProductAdjustMapper  mapper;
		
	@Resource
	private IProcessService processService;
	
	@Resource
	private  CMerchCustContractMapper  contractmapper;
	
	@Resource
	private  CMerchCustProudctInvMapper  invVmapper;
	
	@Resource
	private CMerchCustProductLogMapper  logmapper;
	
	@Resource
	private  CMerchCustAccountLogMapper  accountlogmapper;
	
	@Resource
	private  CMerchCustAccountMapper  accountmapper;
	
	@Resource
	private  CMerchCustBalancesMapper  balancesmapper;
	@Resource
	private CMerchCustProductBalancesMapper inbalancemapper;
	
	@Resource
	private TMaterialBaseMapper  materialMapper;
	/**
	 * 获取调整客户
	 */
	@Override
	public AjaxDTO getCustomer(String orgid,String invValidate) {
		AjaxDTO dto = new AjaxDTO();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgid", orgid);
		params.put("invValidate", invValidate);
		List<CMerchCustBase> list = this.adjustUtilMapper.selectAdjustMerch(params);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	
	/**
	 * 获取客户可调整物料
	 */
	@Override
	public AjaxDTO getmaterials(String orgid, String merchId,String type) {
		AjaxDTO dto = new AjaxDTO();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgid", orgid);
		params.put("merchId", merchId);
		params.put("type", type);
		List<AdjustMaterialDTO> list = this.adjustUtilMapper.selectAdjustMaterials(params);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}


	@Override
	public Integer doAdd(CMerchCustProductAdjust adjust) {
		TMaterialBase  material= this.materialMapper.selectByPrimaryKey(adjust.getMaterialId());
		adjust.setAdjustPrice(BigDecimalASME.multiply(adjust.getAdjustPrice().divide(new BigDecimal(material.getAttribute6()), 4, BigDecimal.ROUND_HALF_UP)));
		adjust.setAdjustNum(adjust.getAdjustNum().multiply(new BigDecimal(material.getAttribute6())));
		adjust.setAdjustAmt(BigDecimalASME.multiply(adjust.getAdjustAmt()));
		adjust.setAttribute1(material.getAttribute6());//箱内数量
		return this.mapper.insert(adjust);
	}

	
	/**
	 * 库存调整List
	 */
	@Override
	public AjaxDTO selectAdjustList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
	    List<CMerchCustInvAdjustDTO> list = this.adjustUtilMapper.selectAdjustList(params);
	    int total = this.adjustUtilMapper.countAdjustList(params);
	    dto.setRows(list);
	    dto.setTotal(total);
		return dto;
	}

	
	/**
	 * 获取物料库存
	 */
	@Override
	public String selectInvNum(Map<String, Object> params) {
		return this.adjustUtilMapper.selectInvNum(params);
	}

	/**
	 * 删除
	 */
	@Override
	public String doDel(Long id) {
		return this.mapper.deleteByPrimaryKey(id)==1?"S":"E";
	}

	/**
	 * 审批
	 */
	@Override
	public String doAudit(Long id,TEmployee user,String states)throws Exception {
		CMerchCustProductAdjust adjust = this.mapper.selectByPrimaryKey(id);
		if("2".equals(states)){
			if("2".equals(adjust.getType())){
				//客户间调整
				CMerchCustAccountExample ex = new CMerchCustAccountExample();
				CMerchCustAccountExample.Criteria ext = ex.createCriteria();
				ext.andMerchCustIdEqualTo(adjust.getOtherMerchCustId());
				CMerchCustAccount account = this.accountmapper.selectByExample(ex).get(0);
				if(BigDecimalASME.multiply(account.getCreditAmt()).compareTo(adjust.getAdjustAmt()) <0){
					return "E2";
				}
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("key", id);
			param.put("startUser",user.getLoginName());
			param.put("groupType", "admin");
			param.put("name", "调整编号："+id);
			param.put("level", "1");
			param.put("viewPage","customerInvAdjust/audit.html?id="+id);
			String processId = this.processService.startProcess(param, "MERCH_INV_ADJUST",
					user.getLoginName());
			adjust.setAttribute1(processId);
		}
		adjust.setStates(states);
		return updateStates(adjust)==1?"S":"E";
	}
	
	/**
	 * 客户库存调整审批处理
	 */
	public Integer updateStates(CMerchCustProductAdjust adjust) throws Exception{
	    this.mapper.updateByPrimaryKeySelective(adjust);
		if("3".equals(adjust.getStates())){
			//审批通过
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			if(adjust.getType().equals("2")){
				//客户间调整
				adjust.setAdjustAmt(BigDecimal.ZERO.subtract(adjust.getAdjustAmt()));
				adjust.setAdjustNum(BigDecimal.ZERO.subtract(adjust.getAdjustNum()));
			}
			//更新客户库存数据
			doAdjustProductInv(adjust,sf.format(new Date()));
			//更新客户资金数据
			doUpdateAccount(adjust,sf.format(new Date()));
			
			if(adjust.getType().equals("2")){
				//客户间调整
				adjust.setMerchCustId(adjust.getOtherMerchCustId());
				adjust.setAdjustAmt(BigDecimal.ZERO.subtract(adjust.getAdjustAmt()));
				adjust.setAdjustNum(BigDecimal.ZERO.subtract(adjust.getAdjustNum()));
				doAdjustProductInv(adjust,sf.format(new Date()));
				//更新客户资金数据
				doUpdateAccount(adjust,sf.format(new Date()));
			}
		}
		return 1;
	}
	
	/**
	 * 调整更新客户库存数据
	 * @param header
	 * @param line
	 * @param contract
	 * @param period
	 */
	public void doAdjustProductInv(CMerchCustProductAdjust adjust,String period) throws Exception{
		logger.info("库存调整更新库存数据开始");
		//插入日志表
		CMerchCustProductLog  log = new CMerchCustProductLog();
		log.setMerchCustId(adjust.getMerchCustId());
		log.setCreateTs(new Date());
		if(adjust.getAdjustNum().compareTo(BigDecimal.ZERO)==1){
			//调增
			log.setdNum(adjust.getAdjustNum().abs());			
		}else{
			//调减
			log.setcNum(adjust.getAdjustNum().abs());
		}
		log.setMaterialId(adjust.getMaterialId());
		log.setVoucherId(adjust.getId().toString());
		log.setPeriod(period);
		//大于0调增，小于0调减
		log.setType(adjust.getAdjustNum().compareTo(BigDecimal.ZERO)==1?"2":"1");
		log.setSource("4");
		log.setRemark(adjust.getRemark());
		log.setPrice(adjust.getAdjustPrice());
		log.setAmt(adjust.getAdjustAmt());
		this.logmapper.insert(log);
		
		//更新期间数据
		CMerchCustProductBalancesExample bex = new CMerchCustProductBalancesExample();
		CMerchCustProductBalancesExample.Criteria bext = bex.createCriteria();
		bext.andMerchCustIdEqualTo(adjust.getMerchCustId());
		bext.andMaterialIdEqualTo(adjust.getMaterialId());
		bext.andPeriodEqualTo(period);
		List<CMerchCustProductBalances> balances= this.inbalancemapper.selectByExample(bex);
		CMerchCustProductBalances balance = new CMerchCustProductBalances();
		if(balances !=null && balances.size() >0){
			balance = balances.get(0);
		}
		if(adjust.getAdjustNum().compareTo(BigDecimal.ZERO)==1){
			//调增
			if(balance.getdNum()==null){
				balance.setdNum(BigDecimal.ZERO);
			}
			balance.setdNum(balance.getdNum().add(adjust.getAdjustNum().abs()));
		}else{
			//调减
			if(balance.getcNum() ==null){
				balance.setcNum(BigDecimal.ZERO);
			}
			balance.setcNum(balance.getcNum().add(adjust.getAdjustNum().abs()));
		}
		if("2".equals(adjust.getType()) && balance.getMerchCustId() ==null){
			balance.setMaterialId(adjust.getMaterialId());
			balance.setOrganizationId(adjust.getOrganizationId());
			balance.setMerchCustId(adjust.getMerchCustId());
			balance.setPeriod(period);
			balance.setStates("1");
			balance.setYtd(BigDecimal.ZERO);
			balance.setPtd(BigDecimal.ZERO);
			this.inbalancemapper.insert(balance);
		}else{			
			this.inbalancemapper.updateByPrimaryKeySelective(balance);
		}
		
		//更新库存表
		CMerchCustProudctInvExample invex = new CMerchCustProudctInvExample();
		CMerchCustProudctInvExample.Criteria invext = invex.createCriteria();
		invext.andMerchCustIdEqualTo(adjust.getMerchCustId());
		invext.andMaterialIdEqualTo(adjust.getMaterialId());
		invext.andOrganizationIdEqualTo(adjust.getOrganizationId());
		List<CMerchCustProudctInv> invs = this.invVmapper.selectByExample(invex);
		CMerchCustProudctInv inv = new CMerchCustProudctInv();
		if(invs.size()>0 && invs !=null){
			 inv = invs.get(0);			
		}
		if(inv.getInvNum() ==null){
			inv.setInvNum(BigDecimal.ZERO);
		}
		inv.setInvNum(inv.getInvNum().add(adjust.getAdjustNum()));
		if("2".equals(adjust.getType()) && inv.getMerchCustId() ==null){
			inv.setCreateOid(adjust.getCreateOid());
			inv.setCreateTs(new Date());
			inv.setFrozenNum(BigDecimal.ZERO);
			inv.setMaterialId(adjust.getMaterialId());
			inv.setMerchCustId(adjust.getMerchCustId());
			inv.setOrganizationId(adjust.getOrganizationId());
			this.invVmapper.insert(inv);
		}else{			
			this.invVmapper.updateByPrimaryKeySelective(inv);
		}
	}
	
	/**
	 * 更新客户期间数据
	 * @param header
	 * @param period
	 */
	public void doUpdateAccount(CMerchCustProductAdjust adjust,String period){
		//插入日志表
		CMerchCustAccountLog accountlog = new CMerchCustAccountLog();
		accountlog.setAccountType("3");
		accountlog.setMerchCustId(adjust.getMerchCustId());
		accountlog.setCreateTs(new Date());
		accountlog.setcAmt(adjust.getAdjustAmt());
		accountlog.setOrderId(adjust.getId());
		accountlog.setOrganizationId(adjust.getOrganizationId());
		accountlog.setPeriod(period);
		accountlog.setStates("S");
		if(adjust.getAdjustNum().compareTo(BigDecimal.ZERO)==1){
			//调增
			accountlog.setcAmt(adjust.getAdjustAmt().abs());
		}else{
			//调减
			accountlog.setdAmt(adjust.getAdjustAmt().abs());
		}
		accountlog.setType("11");
		this.accountlogmapper.insert(accountlog);
		//扣减授信
		CMerchCustAccountExample  accountex =new CMerchCustAccountExample();
		CMerchCustAccountExample.Criteria accountext = accountex.createCriteria();
		accountext.andMerchCustIdEqualTo(adjust.getMerchCustId());
		accountext.andOrganizationIdEqualTo(adjust.getOrganizationId());
		List<CMerchCustAccount> accounts = this.accountmapper.selectByExample(accountex);
		if(accounts.size()==1 && accounts !=null){
			CMerchCustAccount account = accounts.get(0);
			account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
			account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
			account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
			account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
			account.setCreditAmt(account.getCreditAmt().subtract(adjust.getAdjustAmt()));
			this.accountmapper.updateByPrimaryKeySelective(account);
		}
		//扣减期间数据表
		CMerchCustBalancesExample balancesex  = new CMerchCustBalancesExample();
		CMerchCustBalancesExample.Criteria balancesext = balancesex.createCriteria();
		balancesext.andMerchCustIdEqualTo(adjust.getMerchCustId());
		balancesext.andOrganizationIdEqualTo(adjust.getOrganizationId());
		balancesext.andAccountTypeEqualTo("3");
		balancesext.andPeriodEqualTo(period);
		CMerchCustBalances  balance = this.balancesmapper.selectByExample(balancesex).get(0);
		if(adjust.getAdjustNum().compareTo(BigDecimal.ZERO)==1){
			//调增
			balance.setcAmt(balance.getcAmt().add(adjust.getAdjustAmt().abs()));
		}else{
			//调减
			balance.setdAmt(balance.getcAmt().add(adjust.getAdjustAmt().abs()));
		}
		this.balancesmapper.updateByPrimaryKeySelective(balance);
	}


	@Override
	public Integer doEdit(CMerchCustProductAdjust adjust) {
		CMerchCustProductAdjust  editadjust = this.mapper.selectByPrimaryKey(adjust.getId());
		TMaterialBase  material= this.materialMapper.selectByPrimaryKey(editadjust.getMaterialId());
		//editadjust.setAdjustPrice(BigDecimalASME.multiply(adjust.getAdjustPrice().divide(new BigDecimal(material.getAttribute6())).setScale(2,BigDecimal.ROUND_HALF_UP)));
		editadjust.setAdjustNum(adjust.getAdjustNum().multiply(new BigDecimal(material.getAttribute6())));
		editadjust.setAdjustAmt(BigDecimalASME.multiply(adjust.getAdjustAmt()));
		return this.mapper.updateByPrimaryKeySelective(editadjust);
	}
}
