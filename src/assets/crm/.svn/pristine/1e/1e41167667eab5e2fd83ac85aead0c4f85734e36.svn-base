package com.hhnz.process.task.paper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseExample;
import com.hhnz.customer.model.CMerchCustContract;

/**
 * Created by yang on 2016-8-23.
 */
@Service("paperInitAccountTask")
public class PaperInitAccountTask implements JavaDelegate {

    @Autowired
    private CMerchCustAccountMapper mapper;

    @Autowired
    private CMerchCustContractMapper contractMapper;

    @Autowired
    private CMerchCustBaseMapper baseMapper;

    @Autowired
    private CMerchCustBalancesMapper balanceMapper;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//合同号
        CMerchCustContract contract  = this.contractMapper.selectByPrimaryKey(id);
        //判断该客户在当前销售组织下是否存在资金余额记录
        CMerchCustAccountExample aex = new CMerchCustAccountExample();
        CMerchCustAccountExample.Criteria aexc = aex.createCriteria();
        aexc.andMerchCustIdEqualTo(contract.getMerchCustId());
        aexc.andOrganizationIdEqualTo(contract.getOrganizationId());
        List<CMerchCustAccount> accountlist = this.mapper.selectByExample(aex);
        if(accountlist !=null && accountlist.size()>0){
        	return;
        }
        
        //根据客户类型添加账户
        CMerchCustBaseExample ex = new CMerchCustBaseExample();
        CMerchCustBaseExample.Criteria ext = ex.createCriteria();
        ext.andIdEqualTo(contract.getMerchCustId());
        ext.andOrganizationIdEqualTo(contract.getOrganizationId());
        ext.andStatesEqualTo("3");
        List<CMerchCustBase> list = this.baseMapper.selectByExample(ex);
        if(list !=null && list.size()==1){
        	CMerchCustBase merch = list.get(0);
        	//添加账户余额数据
        	CMerchCustAccount account = new CMerchCustAccount();
            account.setOrganizationId(merch.getOrganizationId());
            account.setSubsidyAmt(new BigDecimal(Double.valueOf(0)));
            account.setCashAmt(new BigDecimal(Double.valueOf(0)));
            account.setBondAmt("7".equals(merch.getCustType())?contract.getCreditAmt():new BigDecimal(Double.valueOf(0)));
            account.setCreditAmt(contract.getCreditAmt());
            account.setMerchCustId(merch.getId());
            account.setContractId(contract.getId());
            account.setContractCreditAmt(contract.getCreditAmt());
            this.mapper.insert(account);
            
            //添加账户期间数据
            String[] str =null;
        	if("1".equals(merch.getCustType())){
        		//合资盐业公司
        		str =new String[]{"1","2","3"};//现金，授信，货补
        	}else if("2".equals(merch.getCustType())){
        		//配送商
        		str =new String[]{"1","3","2","4"};//现金，授信，保证金,货补
        	}else if("3".equals(merch.getCustType())){
        		//KA
        		str =new String[]{"1","2","3","4"};//现金，授信，货补,保证金
        	}else if("4".equals(merch.getCustType())){
        		//盐业公司
        		str =new String[]{"1","2","3"};//现金，授信，货补
        	}else if("7".equals(merch.getCustType())){
        		//仓储服务商
        		str =new String[]{"1","2","3","4"};//现金，货补,授信，保证金
        	}else if("70".equals(merch.getCustType())){
        		//合资盐业公司下仓储服务商
        		str =new String[]{"1","2","3","4"};//现金，货补,授信，保证金
        	}else if("8".equals(merch.getCustType())){
        		//物流商
        		str =new String[]{"1","2","3","4"};//现金，货补,授信，保证金
        	}else if("9".equals(merch.getCustType())){
        		//特通商户
        		str =new String[]{"1","2","3"};//现金，授信，货补
        	}
        	addAccount(merch,str,contract);
        }else{
        	return;
        }
    }
    
    private void addAccount(CMerchCustBase merch,String[] str,CMerchCustContract contract){
    	 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    	 for(String type:str){
             CMerchCustBalances cb = new CMerchCustBalances();
             cb.setMerchCustId(merch.getId());
             cb.setOrganizationId(merch.getOrganizationId());
             cb.setcAmt(new BigDecimal(Double.valueOf(0)));
             cb.setdAmt(new BigDecimal(Double.valueOf(0)));
             cb.setPtd(new BigDecimal(Double.valueOf(0)));
             cb.setYtd(type.equals("3")?contract.getCreditAmt():new BigDecimal(Double.valueOf(0)));
             cb.setAccountType(type);
             cb.setPeriod(sf.format(new Date()));
             this.balanceMapper.insert(cb);
         }
    }
}
