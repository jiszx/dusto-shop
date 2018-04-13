package com.hhnz.process.task.paper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.service.IAdjustAccountService;
import com.hhnz.crm.mapper.UtilMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.mapper.CMerchCustProductVMapper;
import com.hhnz.customer.mapper.CMerchCustStationMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractExample;
import com.hhnz.customer.model.CMerchCustContractExample.Criteria;
import com.hhnz.customer.model.CMerchCustStation;
import com.hhnz.customer.model.CMerchCustStationExample;
import com.hhnz.salepolicy.mapper.OmPolicyHeadersMapper;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.DateUtil;

/**
 * Created by yang on 2016-8-23.
 */
@Service("paperStateTask")
@Transactional
public class PaperStateTask implements JavaDelegate {

	@Autowired
	private CMerchCustContractMapper mapper;

	@Resource
	private OmPolicyHeadersMapper policymapper;

	@Resource
	private CMerchCustStationMapper stationmapper;

	@Resource
	private UtilMapper utilmapper;

	@Resource
	private CMerchCustAccountMapper accountmapper;

	@Resource
	private CMerchCustProductVMapper productmapper;

	@Resource
	private CMerchCustBaseMapper merchmapper;
	
	@Resource
    private CMerchCustAccountLogMapper accountLogMapper;
	
	@Resource
	private IAdjustAccountService  adjustService;
	
	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		Long id = Long.parseLong(delegateExecution.getVariable("key").toString());// 客户编号
		int flag = (int) delegateExecution.getVariable("FLAG");// 0 驳回 1通过
		CMerchCustContract vo = new CMerchCustContract();
		// vo.setId(id);
		vo = this.mapper.selectByPrimaryKey(id);
		CMerchCustBase merch = this.merchmapper.selectByPrimaryKey(vo.getMerchCustId());
		if (flag == 1) {
			//审批通过
			vo.setStates("4");
			// 判断当前客户是否存在已经生效的合同，如果有生效合同则失效旧合同
			CMerchCustContractExample ex = new CMerchCustContractExample();
			Criteria param = ex.createCriteria();
			param.andMerchCustIdEqualTo(vo.getMerchCustId());
			param.andOrganizationIdEqualTo(vo.getOrganizationId());
			param.andIdNotEqualTo(id);
			param.andStatesEqualTo("4");
			List<CMerchCustContract> list = this.mapper.selectByExample(ex);
			if (list != null && list.size() == 1) {
				// 失效合同
				CMerchCustContract cmcc = list.get(0);
				cmcc.setStates("5");
				this.mapper.updateByPrimaryKeySelective(cmcc);
				// 失效该客户所有产品
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("states", "5");
				//map.put("contractId", vo.getId());
				map.put("merchCustId", vo.getMerchCustId());
				this.productmapper.updateProductStates(map);
			}
			//非仓储服务商添加对授信额度的调整
			if(!"7".equals(merch.getCustType()) && !"70".equals(merch.getCustType())){
				updateMerchAccount(vo);
			}
			// 激活当前合同对应的产品
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("states", "4");
			map.put("contractId", vo.getId());
			map.put("merchCustId", vo.getMerchCustId());
			this.productmapper.updateProductStates(map);

			// 查看当前客户是否可以享受销售政策
			// 获取当前客户岗位
			CMerchCustStationExample sex = new CMerchCustStationExample();
			sex.createCriteria().andMerchCustIdEqualTo(vo.getMerchCustId());
			List<CMerchCustStation> stations = this.stationmapper
					.selectByExample(sex);
			if (!stations.isEmpty()) {
				CMerchCustStation station = stations.get(0);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("merchCustId", vo.getMerchCustId());
				params.put("stationid", station.getId());
				this.utilmapper.insertPolicyCust(params);
			}
		} else {
			//审批驳回
			vo.setStates("3");
		}
		this.mapper.updateByPrimaryKeySelective(vo);
	}
	
	private void updateMerchAccount(CMerchCustContract vo){
		//非仓储服务商和非合作仓储服务商处理授信额度
		CMerchCustAccountExample aex = new CMerchCustAccountExample();
		CMerchCustAccountExample.Criteria aexc = aex.createCriteria();
		aexc.andMerchCustIdEqualTo(vo.getMerchCustId());
		aexc.andOrganizationIdEqualTo(vo.getOrganizationId());
		List<CMerchCustAccount> accountlist = this.accountmapper.selectByExample(aex);
		if(accountlist !=null && accountlist.size()>0){
			CMerchCustAccount account = accountlist.get(0);
			account.setContractId(vo.getId());
			account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
			account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
			account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
			account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
			//处理授信额度
			if(vo.getCreditAmt().compareTo(BigDecimal.ZERO)==0){
				//合同授信额度等于0时，合同授信额度等于当前客户对应的授信额度
				vo.setCreditAmt(account.getContractCreditAmt());
				this.mapper.updateByPrimaryKeySelective(vo);
			}else{
				//合同授信额度不等于0
				CMerchCustAccountLog accountLog = new CMerchCustAccountLog();
			    accountLog.setAccountType("3");
			    accountLog.setCreateTs(new Date());
			    accountLog.setMerchCustId(vo.getMerchCustId());
			    accountLog.setOrderId(vo.getId());
			    accountLog.setOrganizationId(vo.getOrganizationId());
			    accountLog.setType("3"); // 调整
			    accountLog.setStates("S");
			    accountLog.setPeriod(DateUtil.periodNow());
			    accountLog.setAttribute5("新签合同授信调整");
				BigDecimal amt =vo.getCreditAmt().subtract(account.getContractCreditAmt());
				if (amt.compareTo(BigDecimal.ZERO) >0){
					//新签合同授信额度大于客户现有授信额度
					accountLog.setdAmt(amt.abs());
					accountLog.setcAmt(BigDecimal.ZERO);
				}else if(amt.compareTo(BigDecimal.ZERO) <0){
					//新签合同授信金额小于客户现有授信额度
					accountLog.setdAmt(BigDecimal.ZERO);
					accountLog.setcAmt(amt.abs());
				}
				accountLogMapper.insert(accountLog);
				this.adjustService.changeBalanceAmt("3",vo.getMerchCustId(),vo.getOrganizationId(),amt);
				account.setCreditAmt(account.getCreditAmt().add(
						(vo.getCreditAmt() == null ? BigDecimal.ZERO : vo.getCreditAmt()).subtract(account.getContractCreditAmt())));
				account.setContractCreditAmt(vo.getCreditAmt());
				this.accountmapper.updateByPrimaryKeySelective(account);
			}
		}
	}
}
