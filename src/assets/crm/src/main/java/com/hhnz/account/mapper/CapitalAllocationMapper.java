package com.hhnz.account.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.account.dto.AllocationDetailsDTO;
import com.hhnz.account.dto.OrderReceiveDTO;
import com.hhnz.account.dto.OrderVerifieDTO;
import com.hhnz.account.model.CMerchCustAccountV;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.customer.model.CMerchCustBase;

public interface CapitalAllocationMapper {

	List<CMerchCustBase> getMerchs(Map<String, Object> params);

	List<CMerchUpaccount> getReceives(Long merchCustId);

	List<CMerchCustAccountV> selectRetailerAccount(Map<String, Object> params);

	int countRetailerAccount(Map<String, Object> params);

	List<OrderVerifieDTO> getOrders(Long merchCustId);

	int countOrders(Long merchCustId);

	List<OrderReceiveDTO> selectRetailerOrders(Map<String, Object> params);

	int countRetailerOrders(Map<String, Object> params);

	List<AllocationDetailsDTO> getAllocationDetail(Map<String, Object> params);

	int countAllocationDetail(Long upaccountId);

	List<OrderVerifieDTO> getRecords(Long merchCustId);

	int countRecords(Long merchCustId);

}
