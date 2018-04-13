package com.hhnz.account.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.account.model.AdjustCustomer;
import com.hhnz.account.model.CMerchCustAccountV;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.customer.model.CMerchCustBase;

public interface AccountUtilMapper {

	List<AdjustCustomer> getAdjustCustomer(Map<String, Object> params);
	
	/**
	 * 配送商代收货款list
	 * @param params
	 * @return
	 */
	List<CMerchUpaccount> getdistributorsList(Map<String, Object> params);
	
	/**
	 * 获取指定客户的账户余额(不包含零售商)
	 */
	CMerchCustAccountV getAccountByMerchId(Long merchCustId);
	
	/**
	 * 账户余额list(不包含零售商)
	 */
	List<CMerchCustAccountV> getmerchaccount(Map<String, Object> params);
	
	/**
	 * 账户余额总数(不包含零售商)
	 * @param params
	 * @return
	 */
	int countMerchaccount(Map<String, Object> params);
    
	/**
	 * 获取零售商资金明细
	 */
	List<CMerchCustAccountV> getaccountByPid(Map<String, Object> params);
	
	/**
	 * 银行到款信息录入List
	 * @param params
	 * @return
	 */
	List<CMerchUpaccount> getInputAccountList(Map<String, Object> params);
	int countInputAccountList(Map<String, Object> params);
	/**
	 * 销售资金确认List
	 * @param params
	 * @return
	 */
	List<CMerchUpaccount> getSalesAccountList(Map<String, Object> params);
	int countSalesAccountList(Map<String, Object> params);
	/**
	 * 资金上账List
	 * @param params
	 * @return
	 */
	List<CMerchUpaccount> getUpAccountList(Map<String, Object> params);
	int countUpAccountList(Map<String, Object> params);

	/**
	 * 资金上账查询
	 * @param params
	 * @return
	 */
	List<CMerchUpaccount> findAllList(Map<String, Object> params);
	int countAllList(Map<String, Object> params);
	
	List<CMerchCustBase> getorgCustomer(Map<String, Object> params);

	int upSapVoucherId(Long id);

	int countDistributorsList(Map<String, Object> params);

	String getMaxPeriod();

	void upBalance(Map<String, Object> map);

	String validateTask(String processId);

	List<CMerchCustAccountV> getRetailMerchaccount(Map<String, Object> params);

	int countRetailMerchaccount(Map<String, Object> params);

}
