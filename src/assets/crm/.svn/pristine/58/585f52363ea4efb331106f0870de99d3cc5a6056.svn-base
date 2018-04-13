package com.hhnz.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.dto.ResponseResult;
import com.hhnz.order.model.Invoice;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OrderMaterial;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.util.AjaxDTO;

public interface OrderUtilService {

	AjaxDTO getUserCustomerInfo(List<Long> stationids);

	AjaxDTO getUserCustomerInfo(List<Long> stationids, String name,
			String[] areaList, String[] custType, String[] statusList,
			AjaxDTO page);

	AjaxDTO getCustomerShip(Long merchid, String orgid);

	AjaxDTO getCustomerAccount(Long merchid, String orgid);

	AjaxDTO getCustomerProduct(Long merchid, String orgid, String type,
			String materialid, String combination);

	AjaxDTO getCustProductByRdc(Long merchid, String orgid, String rdcCode,
			String combination, String type);

	AjaxDTO getOrderPolicy(Map<String, Object> params);

	String getPolicyDisacoount(String discountMaterialid, Long merchid,
			String type, String orgid);

	AjaxDTO getlinedata(Long headerid);

	CrmStation getStationByID(Long stationid);

	AjaxDTO findProductWithCondition(Long merchid, String orgid,
			OrderMaterial material, AjaxDTO bean);

	String getcustnameByorderId(Long id);

	String getOrgnameByOrderid(Long id);

	String getcustDisByorderId(Long id);

	AjaxDTO getOrderAmt(Long id);

	List<CrmSalesOrganization> getOrderOrg(List<UserJobs> userjobs);

	AjaxDTO customerByOrgid(Long id);

	AjaxDTO getUserCustomerInfo(List<Long> stationids, Map<String, Object> param);

	int validateNum(String materialId, String orgid, Long merchCustId,
			BigDecimal num);

	Long hasContractMerch(Long merchid);

	List<Invoice> orderInvoices(Long crmOrderid);

	BigDecimal getInvNum(String materialId, Long merchCustId, String orgid);

	AjaxDTO findDistributeOrders(Map<String, Object> params);

	AjaxDTO getDistributeDetailList(Map<String, Object> params);

	List<CMerchCustBase> getOrderShip(Long merchid, String orgid);

	List<OmOrderHeadersAll> getOrders(String batchNum, String states,
			String isMatchAmt, String processId);

	Map<String, Object> delDistributeOrder(Long id);

	AjaxDTO distributorEditList(Map<String, Object> params);

	AjaxDTO distributCustomer(TEmployee user, List<Long> stations,
			String custType);

	int censorDist(String orderid, TEmployee user)
			throws NumberFormatException, Exception;

	String batchNum(Long merchid);

	ResponseResult validateDepo(String orderids);

	List<CMerchCustBase> getOrderShip(AjaxDTO page, Long merchid, String orgid);

	AjaxDTO getOwnerMerch(Long merchid);

	AjaxDTO selectRetailProduct(Long merchid, String orgid);

	int validateNumByRdc(String materialId, String orgid, Long merchCustId,
			String rdcCode, BigDecimal num);

	String cancelTransferOrder(long headerId);

	AjaxDTO selectKaProduct(Long merchid, String orgid);

	AjaxDTO selectBillCustomer(Long merchId);

	boolean canTransfer(Long orderid);

	BigDecimal getInvNumByRdc(String materialId, Long merchCustId,
			String orgid, String rdcCode);

}
