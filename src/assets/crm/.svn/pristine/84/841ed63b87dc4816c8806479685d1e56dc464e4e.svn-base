package com.hhnz.customer.service;

import java.util.List;

import com.hhnz.crm.model.TFactoryV;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractLines;
import com.hhnz.customer.model.CMerchCustContractV;
import com.hhnz.customer.model.ContractDetail;
import com.hhnz.util.AjaxDTO;

public interface CustomerContractService {

	AjaxDTO list(AjaxDTO page, CMerchCustContractV contract, List<Long> stationids);

	Long addPaper(CMerchCustContract model, String lineData) throws Exception;

	int addPaperLines(CMerchCustContractLines model) throws Exception;

	AjaxDTO listLines(Long id) throws Exception;

	int delLines(Long id) throws Exception;

	int updateLines(CMerchCustContractLines model) throws Exception;

	CMerchCustContract findById(Long id) throws Exception;

	String delContract(Long contractid);

	String updateContractStates(Long contractid, String states);

	void updateContractProcess(Long contractid, String processId);

	ContractDetail merchContractdetail(Long merchid);

	List<TFactoryV> getfactorys(String orgid);

	/**
	 * 根据客户、销售组织查询当前生效的合同
	 * 
	 * @author: chaoyang.ren
	 * @date:2016年10月8日 下午2:46:14
	 * @param customerId
	 * @param orgId
	 * @return
	 */
	public List<CMerchCustContract> findByCustomerAndOrgId(Long customerId,
			String orgId);

	AjaxDTO getAgents(String agentType);

	int addLineByagentType(CMerchCustContract contract);

	String getvirtualWarehouse(Long merchId);

  void periodCodeToTime(List<CMerchCustContractV> contracts);

  List<CMerchCustContractLines> getLines(Long id);

  void fillAgentName(CMerchCustContract contract);

  AjaxDTO selectBondsByModel(String model);

}
