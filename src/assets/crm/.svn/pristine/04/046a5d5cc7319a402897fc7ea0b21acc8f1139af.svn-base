package com.hhnz.process.task.merchAllocated;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractExample;
import com.hhnz.customerInv.mapper.CMerchCustProductLogMapper;
import com.hhnz.customerInv.mapper.CMerchCustProudctInvMapper;
import com.hhnz.customerInv.model.CMerchCustProductLog;
import com.hhnz.customerInv.model.CMerchCustProudctInv;
import com.hhnz.customerInv.model.CMerchCustProudctInvExample;
import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.util.BigDecimalASME;
@Service("allocatedAuditServie")
@Transactional
public class AllocatedAuditServie implements JavaDelegate{
	@Resource
	private  CustomerStockService  service;
	
	@Resource
	private  OmOrderHeadersAllMapper  headermapper;
	
	@Resource
	private OmOrderLinesAllMapper  linemapper;
	
	@Resource
	private  CMerchCustProudctInvMapper  invmapper;
	
	@Resource
	private CMerchCustProductLogMapper  logmapper;
	
	@Resource
	private  CMerchCustContractMapper  contractmapper;
	
	@Resource
	private CMerchCustAccountMapper  accountmapper;
	
	@Resource
	private  CMerchCustAccountLogMapper  accountlogmapper;
	
	@Resource
	private  CMerchCustBalancesMapper  balancesmapper;
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//入库编号
		int flag = (int) delegateExecution.getVariable("FLAG");//0 驳回 1通过
		if(flag==0){
			this.service.updateStates(id, "4", null);			
		}else{
			//通过
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			OmOrderHeadersAll header = this.headermapper.selectByPrimaryKey(id);
			OmOrderLinesAllExample  ex = new OmOrderLinesAllExample();
			OmOrderLinesAllExample.Criteria ext = ex.createCriteria();
			ext.andHeaderIdEqualTo(id);
			List<OmOrderLinesAll> lines = this.linemapper.selectByExample(ex);
			if(lines !=null && lines.size()>0){
				for (OmOrderLinesAll line :lines){	
					CMerchCustProudctInvExample invex = new CMerchCustProudctInvExample();
					CMerchCustProudctInvExample.Criteria invext = invex.createCriteria();
					invext.andMerchCustIdEqualTo(header.getMerchCustId());
					invext.andMaterialIdEqualTo(line.getMaterialId());
					invext.andOrganizationIdEqualTo(header.getOrganizationId());
					List<CMerchCustProudctInv> invs = this.invmapper.selectByExample(invex);
					CMerchCustProudctInv inv = new CMerchCustProudctInv();
					if(invs !=null && invs.size()==1){
						 inv =invs.get(0);
					}else if(invs ==null||invs.size()==0){
						CMerchCustContract  contract = new CMerchCustContract();
						CMerchCustContractExample  contractEx = new CMerchCustContractExample();
						CMerchCustContractExample.Criteria  contractExt= contractEx.createCriteria();
						contractExt.andMerchCustIdEqualTo(header.getMerchCustId());
						contractExt.andOrganizationIdEqualTo(header.getOrganizationId());
						contractExt.andStatesEqualTo("4");
						List<CMerchCustContract> contracts = this.contractmapper.selectByExample(contractEx);
						if(contracts.size()==1 && contracts !=null){
							contract = contracts.get(0);
							inv.setMerchCustId(header.getMerchCustId());
							inv.setOrganizationId(header.getOrganizationId());
							inv.setCreateTs(new Date());
							inv.setContractId(contract.getId());
							inv.setInvNum(BigDecimal.ZERO);
							inv.setFrozenNum(BigDecimal.ZERO);
							inv.setCreateOid(contract.getCreateOid());
							inv.setMaterialId(line.getMaterialId());
							this.invmapper.insert(inv);
						}
					}
					//插入日志表
					CMerchCustProductLog  log = new CMerchCustProductLog();
					log.setMerchCustId(header.getMerchCustId());
					log.setCreateTs(new Date());
					log.setdNum(line.getNum());
					log.setMaterialId(line.getMaterialId());
					log.setVoucherId(id.toString());
					log.setPeriod(sf.format(new Date()));
					log.setType("1");
					log.setSource("1");
					log.setcNum(BigDecimal.ZERO);
					log.setRemark(header.getRemark());
					this.logmapper.insert(log);
					
					//更新库存表
					inv.setInvNum(inv.getInvNum().add(line.getNum()));
					this.invmapper.updateByPrimaryKeySelective(inv);
					
					//插入日志表
					CMerchCustAccountLog accountlog = new CMerchCustAccountLog();
					accountlog.setAccountType("3");
					accountlog.setMerchCustId(header.getMerchCustId());
					accountlog.setCreateTs(new Date());
					accountlog.setcAmt(header.getOrderAmt());
					accountlog.setOrderId(header.getId());
					accountlog.setOrganizationId(header.getOrganizationId());
					accountlog.setPeriod(sf.format(new Date()));
					accountlog.setdAmt(BigDecimal.ZERO);
					accountlog.setType("8");
					this.accountlogmapper.insert(accountlog);
					//扣减授信
					CMerchCustAccountExample  accountex =new CMerchCustAccountExample();
					CMerchCustAccountExample.Criteria accountext = accountex.createCriteria();
					accountext.andMerchCustIdEqualTo(header.getMerchCustId());
					accountext.andOrganizationIdEqualTo(header.getOrganizationId());
					List<CMerchCustAccount> accounts = this.accountmapper.selectByExample(accountex);
					if(accounts.size()==1 && accounts !=null){
						CMerchCustAccount account = accounts.get(0);
						account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
						account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
						account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
						account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
						account.setCreditAmt(account.getCreditAmt().subtract(header.getOrderAmt()));
						this.accountmapper.updateByPrimaryKeySelective(account);
					}
					//扣减期间数据表
					CMerchCustBalancesExample balancesex  = new CMerchCustBalancesExample();
					CMerchCustBalancesExample.Criteria balancesext = balancesex.createCriteria();
					balancesext.andMerchCustIdEqualTo(header.getMerchCustId());
					balancesext.andOrganizationIdEqualTo(header.getOrganizationId());
					balancesext.andAccountTypeEqualTo("3");
					balancesext.andPeriodEqualTo(sf.format(new Date()));
					List<CMerchCustBalances> balances = this.balancesmapper.selectByExample(balancesex);
					if(balances.size()==1 && balances !=null){
						CMerchCustBalances  balance = balances.get(0);
						balance.setcAmt(balance.getcAmt().add(header.getOrderAmt()));
						this.balancesmapper.updateByPrimaryKeySelective(balance);
					}
				}
			}
			//推送sap
		}
	}

}
