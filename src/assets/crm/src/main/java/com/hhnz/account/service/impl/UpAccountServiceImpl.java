package com.hhnz.account.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.hhnz.account.mapper.AccountUtilMapper;
import com.hhnz.account.mapper.CDistributorsUpaccountMapper;
import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.mapper.CMerchUpaccountMapper;
import com.hhnz.account.model.CDistributorsUpaccount;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAccountLogExample;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.account.service.IUpAccountService;
import com.hhnz.crm.mapper.TDictMapper;
import com.hhnz.crm.mapper.UtilMapper;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TDictExample;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.business.account.AccountAdjustRFC;
import com.hhnz.jco.business.account.InputDTO;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;

/**
 * 客户资金上账service
 * 
 * @author skevin
 *
 */
@Service
@Transactional
public class UpAccountServiceImpl implements IUpAccountService {
	private static Logger logger = Logger.getLogger(UpAccountServiceImpl.class);
	@Resource
	private CMerchUpaccountMapper mapper;

	@Resource
	private UtilMapper utilmapper;

	@Resource
	private CMerchCustAccountMapper accountmapper;

	@Resource
	private CMerchCustAccountLogMapper logmapper;

	@Resource
	private CMerchCustBalancesMapper balancemapper;

	@Resource
	private AccountUtilMapper accountutilmapper;

	@Resource
	private CDistributorsUpaccountMapper distributormapper;

	@Resource
	private AccountAdjustRFC accountAdjustRFC;
	
	@Resource
	private  CMerchCustBaseMapper  merchmapper;
	@Resource
    private TDictMapper dictMapper;

	@Override
	public Integer addupaccount(CMerchUpaccount model) {
		return mapper.insert(model);
	}

	@Override
	public Integer updateupaccount(CMerchUpaccount model) {
		return this.mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public int updateUpaccoutMerch(CMerchUpaccount model) {
		CMerchUpaccount record = this.mapper.selectByPrimaryKey(model.getId());
		record.setMerchCusId(model.getMerchCusId());
		record.setUpdateOid(model.getUpdateOid());
		record.setUpdateTs(model.getUpdateTs());
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public AjaxDTO findInputAccountList(CMerchUpaccount upaccount, AjaxDTO bean) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(upaccount.getPayBankNo())) {
			params.put("payBankNo", upaccount.getPayBankNo());
		}
		if (!StringUtils.isEmpty(upaccount.getPayType())) {
			params.put("payType", upaccount.getPayType());
		}
		if (!StringUtils.isEmpty(upaccount.getPayCity())) {
			params.put("payCity", upaccount.getPayCity());
		}
		if (!StringUtils.isEmpty(upaccount.getOrganizationId())) {
			params.put("orgid", upaccount.getOrganizationId() + "%");
		}
		if( !StringUtils.isEmpty(upaccount.getBankSerial())){
			params.put("bankSerial", "%"+upaccount.getBankSerial() + "%");
		}
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit() + bean.getOffset());
		List<CMerchUpaccount> list = this.accountutilmapper.getInputAccountList(params);
		int total = this.accountutilmapper.countInputAccountList(params);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public Integer delUpheader(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	/**
	 * 修改资金上账状态，如果等于5时资金上账并推送SAP
	 */
	@Override
	public Map<String, Object> censor(Long id, String states){
		Map<String, Object> result = new HashMap<String, Object>();
		CMerchUpaccount upaccount = mapper.selectByPrimaryKey(id);
		if (states.equals("5")) {
			 result = upaccount(upaccount);
		} else {
			upaccount.setStates(states);
			mapper.updateByPrimaryKeySelective(upaccount);
			result.put("type", "200");
			result.put("msg", "操作成功");
		}
		return result;
	}

	/**
	 * 资金上账
	 * 
	 * @param upaccount
	 * @return
	 */
	public Map<String, Object> upaccount(CMerchUpaccount upaccount){
		//获取客户基本信息
		CMerchCustBase  merch = this.merchmapper.selectByPrimaryKey(upaccount.getMerchCusId());
		Map<String, Object> result = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String period = sdf.format(new Date());// 当前年月
		CMerchCustAccountLog accountlog = new CMerchCustAccountLog();
		CMerchCustAccountLogExample logex = new CMerchCustAccountLogExample();
		CMerchCustAccountLogExample.Criteria  logext = logex.createCriteria();
		logext.andTypeEqualTo("1");
		logext.andOrderIdEqualTo(upaccount.getId());
		List<CMerchCustAccountLog> loglist = this.logmapper.selectByExample(logex);
		if(loglist !=null && loglist.size() >0){
			result.put("type", "500");
			result.put("msg", "已上账成功，不允许重复提交");
			return result;
		}
		// 调整客户资金
		//CMerchCustAccount merchaccount = new CMerchCustAccount();
		CMerchCustAccountExample ex = new CMerchCustAccountExample();
		ex.createCriteria().andMerchCustIdEqualTo(upaccount.getMerchCusId());
		ex.createCriteria().andOrganizationIdEqualTo(
				upaccount.getOrganizationId());
		List<CMerchCustAccount> list = new ArrayList<CMerchCustAccount>();
		list = this.accountmapper.selectByExample(ex);
		if (list != null && list.size() > 0) {
			if (list.size() > 1) {
				result.put("type", "500");
				result.put("msg", "客户资金余额存在两条记录请联系运维人员排除原因");
				return result;
			}
			/*merchaccount = list.get(0);
			if (merchaccount.getContractCreditAmt().compareTo(
					merchaccount.getCreditAmt()) > 0 ) {
				// 可用授信和授信额度不相等
				BigDecimal amt = merchaccount.getContractCreditAmt().subtract(
						merchaccount.getCreditAmt());
				if (upaccount.getPayAmt().compareTo(amt) >= 0) {
					// 上账金额大于已用授信额度--补全授信额度，余额保存到现金账户
					insertAccountLog(period, upaccount, "3", amt);

					// 剩余金额添加到现金账户中
					accountlog = insertAccountLog(period, upaccount, "1",
							upaccount.getPayAmt().subtract(amt));
				} else {
					// 上账金额小于已用授信额度，则补全到授信额度中
					accountlog = insertAccountLog(period, upaccount, "3",
							upaccount.getPayAmt());
				}
			} else {
				// 如果可用授信和授信额度相同全额资金添加到现金账户
				accountlog = insertAccountLog(period, upaccount, "1",
						upaccount.getPayAmt());
			}*/
			accountlog = insertAccountLog(period, upaccount, "1",
					upaccount.getPayAmt());
			if (accountlog != null && (StringUtils.equals("7", merch.getCustType())|| StringUtils.equals("70", merch.getCustType()))) {
				//仓储服务商授信处理
				accountlog.setStates("S");
				accountlog.setAccountType("3");
				this.logmapper.updateByPrimaryKeySelective(accountlog);
				updateAccountAndBalance(upaccount,list.get(0),period);
				result.put("type", "200");
				result.put("msg", "资金上账成功");
				upaccount.setStates("5");
				this.mapper.updateByPrimaryKeySelective(upaccount);
				return result;
			}
			// 推送SAP()
			if (accountlog != null && !StringUtils.equals("7", merch.getCustType()) && !StringUtils.equals("70", merch.getCustType())) {
				upaccount.setUpdateTs(new Date());// 推送SAP之前修改更新时间
				SendToSap(accountlog, upaccount);
				upaccount.setStates("5");
				this.mapper.updateByPrimaryKeySelective(upaccount);
				result.put("type", "200");
				result.put("msg", "资金上账处理中");
				return result;
			} else {
				// 日志中没有数据，回滚操作，并返回错误信息
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				result.put("type", "500");
				result.put("msg", "资金上账失败，插入日志表错误");
				return result;
			}
		} else {
			result.put("type", "500");
			result.put("msg", "未查询到客户资金余额信息");
			return result;
		}
	}
	
	//仓储服务商授信处理
	public void updateAccountAndBalance(CMerchUpaccount upaccount,CMerchCustAccount account,String period){
		//日志表添加成功,仓储服务商不推送SAP
		account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
		account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
		account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
		account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
		account.setCreditAmt(account.getCreditAmt().add(upaccount.getPayAmt()));
		this.accountmapper.updateByPrimaryKeySelective(account);
		// 获取客户的期间资金表
		CMerchCustBalancesExample balanceex = new CMerchCustBalancesExample();
		CMerchCustBalancesExample.Criteria bext = balanceex.createCriteria();
		bext.andMerchCustIdEqualTo(upaccount.getMerchCusId());
		bext.andOrganizationIdEqualTo(upaccount.getOrganizationId());
		bext.andPeriodEqualTo(period);
		bext.andAccountTypeEqualTo("3");// 授信账户
		List<CMerchCustBalances> custbalance = this.balancemapper
				.selectByExample(balanceex);
		if (custbalance != null && custbalance.size() == 1) {
			CMerchCustBalances balance = custbalance.get(0);
			balance.setdAmt(balance.getdAmt().add(upaccount.getPayAmt()));
			this.balancemapper.updateByPrimaryKeySelective(balance);
		}
	}

	/**
	 * @author: chaoyang.ren 
	 * @date:2016年11月16日  下午4:36:18
	 * @param period
	 * @param upaccount
	 * @param accountType
	 * @param amt
	 * @return
	 */
	private CMerchCustAccountLog insertAccountLog(String period,
			CMerchUpaccount upaccount, String accountType, BigDecimal amt) {
		// 添加到客户资金日志表
		CMerchCustAccountLog accountlog = new CMerchCustAccountLog();
		accountlog.setAccountType(accountType);
		accountlog.setdAmt(amt);
		accountlog.setMerchCustId(upaccount.getMerchCusId());
		accountlog.setOrganizationId(upaccount.getOrganizationId());
		accountlog.setType("1");
		// accountlog.setAttribute1("0");
		accountlog.setOrderId(upaccount.getId());
		accountlog.setPeriod(period);
		accountlog.setCreateTs(new Date());
		this.logmapper.insert(accountlog);
		return accountlog;
	}

	/**
	 * 推送SAP
	 * 
	 * @param accountlog资金上账日记记录
	 * @param upaccount
	 *            资金上账信息
	 * @return int
	 */
	public Map<String, Object> SendToSap(CMerchCustAccountLog accountlog,
			CMerchUpaccount upaccount){
		Map<String, Object> result = new HashMap<String, Object>();
		InputDTO input = accountAdjustRFC.constructInputParam(accountlog
				.getId());
		logger.info("提交资金上账异步处理");
		accountAdjustRFC.executeInThread(input, UpAccountCallback.class.getSimpleName());
		result.put("type", "200");
		result.put("msg", "资金上账处理中");
		return result;
	}

	@Override
	public AjaxDTO getaccountCustomer(String orgid, List<Long> stationid) {
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgid", orgid);
		params.put("stationid", stationid);
		List<CMerchCustBase> list = this.accountutilmapper
				.getorgCustomer(params);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	@Override
	public AjaxDTO getSalesAccountList(Map<String, Object> params, AjaxDTO bean) {

		// 分页
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit() + bean.getOffset());
		List<CMerchUpaccount> list = this.accountutilmapper
				.getSalesAccountList(params);
		int total = this.accountutilmapper.countSalesAccountList(params);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public AjaxDTO getdistributorsList(AjaxDTO bean, String orgid,
			String custname, String states,String bankSerial) {
		List<CMerchUpaccount> list = new ArrayList<CMerchUpaccount>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit() + bean.getOffset());
		params.put("orgid", orgid);
		params.put("custname", custname);
		if (StringUtils.isNotEmpty(states)) {
			params.put("states", states);
		}
		if (StringUtils.isNotEmpty(bankSerial)) {
			params.put("bankSerial", "%"+bankSerial+"%");
		}
		list = this.accountutilmapper.getdistributorsList(params);
		int total = this.accountutilmapper.countDistributorsList(params);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public int addDistributorAdd(CDistributorsUpaccount distributor) {
		return this.distributormapper.insert(distributor);
	}

	@Override
	public AjaxDTO findUpAccountList(CMerchUpaccount upaccount, AjaxDTO bean) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(upaccount.getPayBankNo())) {
			params.put("payBankNo", upaccount.getPayBankNo());
		}
		if (!StringUtils.isEmpty(upaccount.getPayType())) {
			params.put("payType", upaccount.getPayType());
		}
		if (!StringUtils.isEmpty(upaccount.getPayCity())) {
			params.put("payCity", upaccount.getPayCity());
		}
		if (!StringUtils.isEmpty(upaccount.getOrganizationId())) {
			params.put("orgid", upaccount.getOrganizationId() + "%");
		}
		if (!StringUtils.isEmpty(upaccount.getCustname())) {
			try {
				params.put("custname",
						URLDecoder.decode(upaccount.getCustname(), "utf-8")
								+ "%");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if( !StringUtils.isEmpty(upaccount.getBankSerial())){
			params.put("bankSerial", "%"+upaccount.getBankSerial() + "%");
		}
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit() + bean.getOffset());
		List<CMerchUpaccount> list = this.accountutilmapper
				.getUpAccountList(params);
		int total = this.accountutilmapper.countUpAccountList(params);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public AjaxDTO findAllList(CMerchUpaccount upaccount, AjaxDTO bean) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(upaccount.getPayBankNo())) {
			params.put("payBankNo", upaccount.getPayBankNo());
		}
		if (!StringUtils.isEmpty(upaccount.getPayType())) {
			params.put("payType", upaccount.getPayType());
		}
		if (!StringUtils.isEmpty(upaccount.getPayCity())) {
			params.put("payCity", upaccount.getPayCity());
		}
		if (!StringUtils.isEmpty(upaccount.getOrganizationId())) {
			params.put("orgid", upaccount.getOrganizationId() + "%");
		}
		if (!StringUtils.isEmpty(upaccount.getCustname())) {
			try {
				params.put("custname",
						URLDecoder.decode(upaccount.getCustname(), "utf-8")
								+ "%");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if( !StringUtils.isEmpty(upaccount.getBankSerial())){
			params.put("bankSerial", "%"+upaccount.getBankSerial() + "%");
		}
		if (!StringUtils.isEmpty(upaccount.getStates())) {
			params.put("states", upaccount.getStates());
		}
		if (!StringUtils.isEmpty(upaccount.getSapCustomerId())) {
			params.put("sapCustomerId", upaccount.getSapCustomerId());
		}
		params.put("bpage", bean.getOffset());
		params.put("epage", bean.getLimit() + bean.getOffset());
		List<CMerchUpaccount> list = this.accountutilmapper.findAllList(params);
		int total = this.accountutilmapper.countAllList(params);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public String getSapMsg(Long id) {
		// TODO Auto-generated method stub
		CMerchCustAccountLogExample ex = new CMerchCustAccountLogExample();
		CMerchCustAccountLogExample.Criteria ext = ex.createCriteria();
		ext.andOrderIdEqualTo(id);
		ext.andAttribute1EqualTo("1");
		ext.andAccountTypeEqualTo("1");
		ext.andTypeEqualTo("1");
		List<CMerchCustAccountLog> log = this.logmapper.selectByExample(ex);
		return (log != null && log.size() > 0) ? log.get(0).getAttribute2()
				: "数据错误";
	}

	@Override
	public void updateErrorMsg(Long id, String msg) {
		CMerchUpaccount upaccount = this.mapper.selectByPrimaryKey(id);
		upaccount.setAttribute2(msg);
		this.mapper.updateByPrimaryKeySelective(upaccount);
	}
	
	@Override
    public AjaxDTO bankCodeToName(AjaxDTO page){
      List<CMerchUpaccount> records = page.getRows();
      if(records==null || records.isEmpty()){
        return page;
      }
      TDictExample ex =new TDictExample();
      ex.createCriteria().andColNameEqualTo("BANK");
      List<TDict> banks = dictMapper.selectByExample(ex);
      for(CMerchUpaccount up:records){
        up.setPayBank(getBankName(up.getPayBank(), banks));
      }
      return page;
    }
    
    private String getBankName(String bankCode, List<TDict> banks){
      if(banks==null || banks.isEmpty()){
        return "";
      }
      for(TDict bank:banks){
        if(bank.getChooseVal().equals(bankCode)){
          return bank.getShowText();
        }
      }
      return "";
    }
    
    /**
     * 仓储服务商打款验证
     */
	@Override
	public Map<String,Object>  validateStorage(Long id, Long merchCustId) {
		Map<String,Object> result = new HashMap<String, Object>();
		String type;
		String msg;
		//验证可配货金额（授信）是否大于保证金金额
		CMerchCustAccountExample  accountEx = new CMerchCustAccountExample();
		CMerchCustAccountExample.Criteria accountExt = accountEx.createCriteria();
		accountExt.andMerchCustIdEqualTo(merchCustId);
		List<CMerchCustAccount> accounts = this.accountmapper.selectByExample(accountEx);
		CMerchUpaccount  upaccount = this.mapper.selectByPrimaryKey(id);
		if(accounts.size() ==1 && accounts !=null){
			CMerchCustAccount account = accounts.get(0);
			if(account.getCreditAmt().compareTo(account.getBondAmt())>=0){
				//保证金和授信相等
				type="E";
				msg ="可配货金额(可用授信)大于或者等于保证金金额，不允许直接上账，请完成销售订单，再调拨单入库";
				result.put("type", type);
				result.put("msg", msg);
				return  result;
			}
			//授信和上账金额合计(分)
			BigDecimal amt =BigDecimalASME.multiply(account.getCreditAmt()).add(upaccount.getPayAmt());
			if(amt.compareTo(BigDecimalASME.multiply(account.getBondAmt())) >0){
				//可用授信加上上账金额大于保证金
				type ="E2";
				msg ="可用配货金额(可用授信)加上上账金额大于保证金，不允许直接上账，请完成销售订单，再调拨单入库";
				result.put("type", type);
				result.put("msg", msg);
				return  result;
			}
		}else{
			result.put("type", "E3");
			result.put("msg", "未查询到客户对应的账户数据，请联系管理员核实");
			return result;
		}
		result.put("type", "S");
		result.put("msg", "验证成功");
		return result;
	}

}
