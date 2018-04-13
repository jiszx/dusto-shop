package com.hhnz.account.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.mapper.CMerchCustAdjustMapper;
import com.hhnz.account.mapper.CMerchCustAdjustVMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAccountLogExample;
import com.hhnz.account.model.CMerchCustAdjust;
import com.hhnz.account.model.CMerchCustAdjustV;
import com.hhnz.account.model.CMerchCustAdjustVExample;
import com.hhnz.account.service.IAdjustAccountService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBalancesExample.Criteria;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.jco.business.account.AccountAdjustRFC;
import com.hhnz.jco.business.account.InputDTO;
import com.hhnz.jco.business.account.MarginsRFC;
import com.hhnz.process.service.IProcessService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;
import com.hhnz.util.db.Page;

/**
 * 客户资金调整service
 * @author dell
 *
 */
@Service
@Transactional
public class AdjustAccountServiceImpl implements IAdjustAccountService {
    private static Logger logger = Logger.getLogger(AdjustAccountServiceImpl.class);
	
	@Resource
	private CMerchCustAdjustMapper mapper;
	@Resource
	private CMerchCustAdjustVMapper vmapper;
	@Resource
	private CMerchCustAccountMapper accountMapper;
	@Resource
    private CMerchCustAccountLogMapper accountLogMapper;
    @Resource
    private CMerchCustBalancesMapper balanceMapper;
    @Autowired
    private AccountAdjustRFC accountAdjustRFC;
    @Resource
    private MarginsRFC   marginsRFC;
    @Autowired
    private CMerchCustBaseMapper baseMapper;
    
    @Resource
    private CMerchCustContractMapper  contractmapper;
    @Resource
	private IProcessService processService;
	
	//获取资金调整list
	@Override
	public AjaxDTO findAccountAdjustList(CMerchCustAdjustV adjust,
			AjaxDTO bean) {
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        CMerchCustAdjustVExample ex =new CMerchCustAdjustVExample();
        CMerchCustAdjustVExample.Criteria ext = ex.createCriteria();
        if(!StringUtils.isEmpty(adjust.getCustname())){
        	String custname="";
			try {
				custname = URLDecoder.decode(adjust.getCustname(),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	ext.andCustnameLike(custname+"%");
        }
        if(adjust.getId() !=null){
        	ext.andIdEqualTo(adjust.getId());
        }
        if(!StringUtils.isEmpty(adjust.getReason())){
        	ext.andReasonEqualTo(adjust.getReason());
        }
        if(!StringUtils.isEmpty(adjust.getAccountType())){
        	ext.andAccountTypeEqualTo(adjust.getAccountType());
        }
        if(!StringUtils.isEmpty(adjust.getType())){
        	ext.andTypeEqualTo(adjust.getType());
        }
        if(!StringUtils.isEmpty(adjust.getOrganizationId())){
        	ext.andOrganizationIdEqualTo(adjust.getOrganizationId());
        }
        if(!StringUtils.isEmpty(adjust.getCustType())){
          ext.addCriterion("CUST_TYPE=", adjust.getCustType(), "custType");
        }
        ex.setPage(page);
        ex.setOrderByClause("create_ts desc");
        List<CMerchCustAdjustV> list = this.vmapper.selectByExample(ex);
        int total = this.vmapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
	}
	//新增客户资金调整
	@Override
	public Integer addAccountAdjust(CMerchCustAdjust model) {
		return this.mapper.insert(model);
	}
	@Override
	public Integer updateAdjust(CMerchCustAdjust model) {
		// TODO Auto-generated method stub
		 return this.mapper.updateByPrimaryKeySelective(model);
	}
	@Override
	public Integer delAdjust(Long id) {
		// TODO Auto-generated method stub
		return this.mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public CMerchCustAccount getAccount(CMerchCustAdjust adjust){
	  return getAccount(adjust.getMerchCustId(), adjust.getOrganizationId());
	}
	
	private CMerchCustAccount getAccount(Long merchid, String orgid){
      CMerchCustAccountExample example = new CMerchCustAccountExample();
      example.createCriteria().andMerchCustIdEqualTo(merchid).andOrganizationIdEqualTo(orgid);
      List<CMerchCustAccount> list = accountMapper.selectByExample(example);
      if(!list.isEmpty()){
        return list.get(0);
      }
      return null;
    }
	
	@Override
	public int customerToCustomer(CMerchCustAdjust adjust){
	  if(adjust.getAccountType().equals("1") && adjust.getAdjustDirection().equals("1")){
	    BigDecimal amt = adjust.getAmt();
	    Long merchid = adjust.getTargetMerchCustId();
	    CMerchCustAccount account = getAccount(merchid, adjust.getOrganizationId());
	    adjustAccountAmt(account, adjust.getAccountType(), amt);
	    
	    CMerchCustAccountLog accountLog = new CMerchCustAccountLog();
        accountLog.setAccountType(adjust.getAccountType());
        accountLog.setdAmt(amt.abs());
        accountLog.setCreateTs(new Date());
        accountLog.setMerchCustId(adjust.getMerchCustId());
        accountLog.setOrderId(adjust.getId());
        accountLog.setOrganizationId(adjust.getOrganizationId());
        accountLog.setType("3"); // 调整
        accountLog.setPeriod(DateUtil.periodNow());
        accountLogMapper.insert(accountLog);
        changeBalanceAmt(adjust.getAccountType(), adjust.getMerchCustId(), adjust.getOrganizationId(), amt);
        
        return 1;
	  }
	  return 0;
	}

	/**
	 * 客户资金调整
	 * @param id
	 * @param states
	 * @return
	 * @throws Exception
	 *
	 * 杨成锡 12.16日修改 合作盐业公司的仓储服务商不发送到SAP
	 */
    @Override
    public Integer audit(Long id, String states) throws Exception {
      CMerchCustAdjust adjust = this.mapper.selectByPrimaryKey(id);
      adjust.setStates(states);
      if ("3".equals(states)) {
    	//判断数据是否已经存在生效记录
    	  CMerchCustAccountLogExample logex = new CMerchCustAccountLogExample();
    	  CMerchCustAccountLogExample.Criteria logext = logex.createCriteria();
    	  logext.andOrderIdEqualTo(id);
    	  logext.andStatesEqualTo("S");
    	  logext.andTypeEqualTo("3");
    	  logext.andMerchCustIdEqualTo(adjust.getMerchCustId());
    	  List<CMerchCustAccountLog> logs = this.accountLogMapper.selectByExample(logex);
    	  if(logs.size() >0 && logs !=null){
    		  return 0;
    	  }
        // 修改客户余额，日志表插入数据，修改科目余额表本期增加（本期减少数据）
        // c_merch_cust_account
        // c_merch_cust_account_log
        // c_merch_cust_balances
        BigDecimal amt = adjust.getAmt();
  
        CMerchCustAccountLog accountLog = new CMerchCustAccountLog();
        accountLog.setAccountType(adjust.getAccountType().equals("5")?"3":adjust.getAccountType());
        if (amt.floatValue() < 0) {
          accountLog.setcAmt(amt.abs());
        } else {
          accountLog.setdAmt(amt.abs());
        }
        accountLog.setCreateTs(new Date());
        accountLog.setMerchCustId(adjust.getMerchCustId());
        accountLog.setOrderId(id);
        accountLog.setOrganizationId(adjust.getOrganizationId());
        accountLog.setType("3"); // 调整
        accountLog.setPeriod(DateUtil.periodNow());
        String str = adjust.getAccountType().equals("5")?"授信金额调整":(adjust.getAccountType().equals("3")?"授信额度":"");
        accountLog.setAttribute5(str+adjust.getRemark());
        accountLogMapper.insert(accountLog);

        CMerchCustBase base = this.baseMapper.selectByPrimaryKey(adjust.getMerchCustId());
        // 现金
        if("1".equals(adjust.getAccountType())){
        	logger.info("提交现金调整异步处理");
        	InputDTO input = accountAdjustRFC.constructInputParam(accountLog.getId());
            accountAdjustRFC.executeInThread(input,AdjustAccountCallback.class.getSimpleName());
        }
        //保证金
        else if("4".equals(adjust.getAccountType())){
        	//合作仓储服务商
        	if("70".equals(base.getCustType())){
        		CMerchCustBase pmerch = this.baseMapper.selectByPrimaryKey(base.getPid());

				logger.info("保证金异步处理");
				InputDTO input = marginsRFC.constructInputParam(accountLog.getId());
				marginsRFC.executeInThread(input,MarginsAccountCallback.class.getSimpleName());

//        		if("1421".equals(pmerch.getSapCustomerId())){
//        			//合作仓储服务商对应盐业公司SAPCODE等于1421则推送SAP，推送到1570
//        			logger.info("保证金异步处理");
//                	InputDTO input = marginsRFC.constructInputParam(accountLog.getId());
//        			marginsRFC.executeInThread(input,MarginsAccountCallback.class.getSimpleName());
//        		}else{
//        			accountLog.setStates("S");
//                	this.accountLogMapper.updateByPrimaryKeySelective(accountLog);
//                	CMerchCustAccount account = getAccount(adjust);
//                	//修改账户余额
//                	adjustAccountAmt(account,adjust.getAccountType(),adjust.getAmt());
//                	//修改期间数据
//                	changeBalanceAmt(adjust.getAccountType(),adjust.getMerchCustId(),adjust.getOrganizationId(),adjust.getAmt());
//                	changeBalanceAmt("3",adjust.getMerchCustId(),adjust.getOrganizationId(),adjust.getAmt());
//                	accountLog.setId(null);
//                	accountLog.setAccountType("3");
//                	accountLog.setAttribute5("保证金调整");
//                	this.accountLogMapper.insert(accountLog);
//                	//更新调整状态
//                	adjust.setStates("3");
//            		adjust.setAttribute2("");
//            		mapper.updateByPrimaryKeySelective(adjust);
//        		}
        	}
        	//物流商推送到1570
        	else if ("8".equals(base.getCustType())){
        		logger.info("保证金异步处理");
            	InputDTO input = marginsRFC.constructInputParam(accountLog.getId());
    			marginsRFC.executeInThread(input,MarginsAccountCallback.class.getSimpleName());
        	}
        	else{
        		accountLog.setStates("S");
            	this.accountLogMapper.updateByPrimaryKeySelective(accountLog);
            	CMerchCustAccount account = getAccount(adjust);
            	//修改账户余额
            	adjustAccountAmt(account,adjust.getAccountType(),adjust.getAmt());
            	//修改期间数据
            	changeBalanceAmt(adjust.getAccountType(),adjust.getMerchCustId(),adjust.getOrganizationId(),adjust.getAmt());
            	if("7".equals(base.getCustType())){
            		//仓储服务商 保证金调整同时调整授信额度（可配货金额）
            		changeBalanceAmt("3",adjust.getMerchCustId(),adjust.getOrganizationId(),adjust.getAmt());
            		accountLog.setId(null);
            		accountLog.setAccountType("3");
            		accountLog.setAttribute5("保证金调整");
            		this.accountLogMapper.insert(accountLog);
            	}
            	//更新调整状态
            	adjust.setStates("3");
        		adjust.setAttribute2("");
        		mapper.updateByPrimaryKeySelective(adjust);
        	}
        	
        }
        else{
        	accountLog.setStates("S");
        	this.accountLogMapper.updateByPrimaryKeySelective(accountLog);
        	CMerchCustAccount account = getAccount(adjust);
        	//修改账户余额
        	adjustAccountAmt(account,adjust.getAccountType(),adjust.getAmt());
        	//修改期间数据
        	changeBalanceAmt(adjust.getAccountType(),adjust.getMerchCustId(),adjust.getOrganizationId(),adjust.getAmt());
        	/*CMerchCustBase base = this.baseMapper.selectByPrimaryKey(adjust.getMerchCustId());
        	if(("70".equals(base.getCustType())||"7".equals(base.getCustType())) && "4".equals(adjust.getAccountType())){
        		//仓储服务商（包含合资仓储服务商）保证金调整同时调整授信额度（可配货金额）
        		changeBalanceAmt("3",adjust.getMerchCustId(),adjust.getOrganizationId(),adjust.getAmt());
        		accountLog.setId(null);
        		accountLog.setAccountType("3");
        		accountLog.setAttribute5("保证金调整");
        		this.accountLogMapper.insert(accountLog);
        	}*/
        	//更新调整状态
        	adjust.setStates("3");
    		adjust.setAttribute2("");
    		mapper.updateByPrimaryKeySelective(adjust);
        }
        return 1;
      }else{
    	  return this.mapper.updateByPrimaryKeySelective(adjust);
      }
    }
	
    @Override
	public void adjustAccountAmt(CMerchCustAccount account, String accountType, BigDecimal amt){
      CMerchCustBase merch = this.baseMapper.selectByPrimaryKey(account.getMerchCustId());
	  if("1".equals(accountType)){ // 现金
	    account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()).add(amt));
	    account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
	    account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
	    account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
	  }else if("2".equals(accountType)){ // 货补
	    account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()).add(amt));
	    account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
	    account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
	    account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
	  }else if("3".equals(accountType)){ // 授信额度
		account.setContractCreditAmt(account.getContractCreditAmt().add(amt));
	    account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()).add(amt));
	    account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
	    account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
	    account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
	    //获取当前合同信息
	    if(!"7".equals(merch.getCustType()) && !"70".equals(merch.getCustType())){
	    	CMerchCustContract  contract = this.contractmapper.selectByPrimaryKey(account.getContractId());
		    if(contract !=null){
		    	contract.setCreditAmt(contract.getCreditAmt().add(amt));
		    	contractmapper.updateByPrimaryKeySelective(contract);
		    }
	    }
	  }else if("5".equals(accountType)){ // 授信金额
		  account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()).add(amt));
		  account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
		  account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
		  account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
	  }else if("4".equals(accountType)){
		  //金保证
		  account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()).add(amt));
		  account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
		  account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
		  account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()).add(amt));
		  /*if("7".equals(merch.getCustType())||"70".equals(merch.getCustType())){
			//获取当前合同信息
			CMerchCustContract  contract = this.contractmapper.selectByPrimaryKey(account.getContractId());
			if(contract !=null){
			  contract.setCreditAmt(contract.getCreditAmt().add(amt));
			  contractmapper.updateByPrimaryKeySelective(contract);
			}  
		  }*/
		  
	  }
	  //account.setCashAmt(BigDecimalASME);
	  accountMapper.updateByPrimaryKeySelective(account);
	}
	
    @Override
	public void changeBalanceAmt(String accountType, Long merchid, String orgid, BigDecimal amt){
      CMerchCustBalancesExample balanceEx = new CMerchCustBalancesExample();
      Criteria param = balanceEx.createCriteria();
      param.andMerchCustIdEqualTo(merchid);
      param.andOrganizationIdEqualTo(orgid);
      param.andAccountTypeEqualTo(accountType.equals("5")?"3":accountType);
      param.andPeriodEqualTo(DateUtil.periodNow());
      List<CMerchCustBalances> balances = balanceMapper.selectByExample(balanceEx);
      if(!balances.isEmpty()){
        CMerchCustBalances balance = balances.get(0);
        if(amt.floatValue()<0){
          balance.setcAmt(balance.getcAmt().add(amt.abs()));
          //balance.setdAmt(BigDecimal.ZERO);
        }else{
          balance.setdAmt(balance.getdAmt().add(amt.abs()));
          //balance.setcAmt(BigDecimal.ZERO);
        }
        balanceMapper.updateByPrimaryKey(balance);
      }
    }
	
	@Override
	public CMerchCustAdjust findAdjustById(Long id) {
		return this.mapper.selectByPrimaryKey(id);
	}
	@Override
	public Integer startprocess(Long id, Map<String, Object> param,TEmployee user) {
		String processId="";
		try {
			processId = this.processService.startProcess(param, "account_adjust",
					user.getLoginName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		CMerchCustAdjust  adjust = this.mapper.selectByPrimaryKey(id);
		adjust.setAttribute1(processId);
		adjust.setStates("2");
		return this.mapper.updateByPrimaryKeySelective(adjust)==1?200:500;
	}

}
