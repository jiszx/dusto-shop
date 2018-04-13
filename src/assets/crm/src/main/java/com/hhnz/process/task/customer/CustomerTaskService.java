package com.hhnz.process.task.customer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.crm.service.IEmployeeService;
import com.hhnz.customer.enu.CustomerBaseStateEnu;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.receivable.mapper.CMerchArBalanceMapper;
import com.hhnz.receivable.model.CMerchArBalance;
import com.hhnz.receivable.model.CMerchArBalanceExample;

/**
 * Created by yang on 2016-8-18.
 * 客户的各类账号建立
 * 修改状态
 */
@Service("customerTaskService")
@Transactional
public class CustomerTaskService implements JavaDelegate {
	private static final Log LOG = LogFactory.getLog(CustomerTaskService.class);
	@Autowired
	private ICustomerService customerService;
    
	@Autowired
    private CMerchCustAccountMapper accountmapper;
	
	@Autowired
	private CMerchCustBalancesMapper balanceMapper;
	@Autowired
    private IEmployeeService employeeService;
	
	@Resource
	private CMerchArBalanceMapper  armapper;
	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//客户编号
        int flag = (int) delegateExecution.getVariable("FLAG");//0 驳回 1通过
        CMerchCustBase custBase = customerService.findCustBaseById(id);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        if(flag == 1){
        	String sapid = (String) delegateExecution.getVariable("sapId");
    		custBase.setSapCustomerId(sapid);
    		custBase.setStates(CustomerBaseStateEnu.FORMAL.getCode());
    		custBase.setCode(custBase.genCode());
        	// 新建默认用户
    		employeeService.generateUser(custBase, custBase.getCode());
        	
        	//零售商/ 不添加合同，在客户建档时新增余额信息
            if("5".equals(custBase.getCustType()) ){
                CMerchCustAccountExample ex = new CMerchCustAccountExample();
                ex.createCriteria().andMerchCustIdEqualTo(custBase.getId()).andOrganizationIdEqualTo(custBase.getOrganizationId());
                List<CMerchCustAccount> accounts = accountmapper.selectByExample(ex);
                if(accounts.isEmpty()){
                  CMerchCustAccount  account  = new CMerchCustAccount();
                  account.setOrganizationId(custBase.getOrganizationId());
                  account.setSubsidyAmt(BigDecimal.ZERO);
                  account.setCashAmt(BigDecimal.ZERO);
                  account.setBondAmt(BigDecimal.ZERO);
                  account.setCreditAmt(BigDecimal.ZERO);
                  account.setMerchCustId(custBase.getId());
                  account.setContractCreditAmt(BigDecimal.ZERO);
                  this.accountmapper.insert(account);
                }
                
                CMerchCustBalancesExample balanceex = new CMerchCustBalancesExample();
                CMerchCustBalancesExample.Criteria bext = balanceex.createCriteria();
                bext.andMerchCustIdEqualTo(custBase.getId());
                bext.andOrganizationIdEqualTo(custBase.getOrganizationId());
                bext.andPeriodEqualTo(sf.format(new Date()));
                bext.andAccountTypeEqualTo("1");// 现金账户
                List<CMerchCustBalances> balances = balanceMapper.selectByExample(balanceex);
                if(balances.isEmpty()){
                  //添加科目余额表
                  CMerchCustBalances cb = new CMerchCustBalances();
                  cb.setMerchCustId(custBase.getId());
                  cb.setOrganizationId(custBase.getOrganizationId());
                  cb.setcAmt(BigDecimal.ZERO);
                  cb.setdAmt(BigDecimal.ZERO);
                  cb.setPtd(BigDecimal.ZERO);
                  cb.setYtd(BigDecimal.ZERO);
                  cb.setAccountType("1");//现金账户
                  cb.setPeriod(sf.format(new Date()));
                  this.balanceMapper.insert(cb);
                }
            }
            //添加客户应收账款期间数据
            CMerchArBalanceExample arEx =new CMerchArBalanceExample();
            CMerchArBalanceExample.Criteria arExt = arEx.createCriteria();
            arExt.andMerchCustIdEqualTo(custBase.getId());
            arExt.andOrganizationIdEqualTo(custBase.getOrganizationId());
            List<CMerchArBalance> ars = this.armapper.selectByExample(arEx);
            //仓储服务商，合作仓储服务商，配送商，物流商无应收账款
            if(ars.isEmpty() && !"70".equals(custBase.getCustType())
            		&& !"7".equals(custBase.getCustType())
            		&& !"2".equals(custBase.getCustType())
            		&& !"8".equals(custBase.getCustType())
            		&& !"6".equals(custBase.getCustType())){                	
            	CMerchArBalance  ar = new CMerchArBalance();
            	ar.setMerchCustId(custBase.getId());
            	ar.setOrganizationId(custBase.getOrganizationId());
            	ar.setYtd(BigDecimal.ZERO);
            	ar.setdAmt(BigDecimal.ZERO);
            	ar.setcAmt(BigDecimal.ZERO);
            	ar.setPtd(BigDecimal.ZERO);
            	ar.setPeriod(sf.format(new Date()));
            	this.armapper.insert(ar);
            }
        }else{
        	LOG.warn("客户传送SAP后未成功生成SAP编号，客户ID为："+id+"，客户状态改为潜在客户！");
        	custBase.setStates(CustomerBaseStateEnu.POTENTIAL.getCode());
        }
        customerService.save(custBase);
    }
	
}
