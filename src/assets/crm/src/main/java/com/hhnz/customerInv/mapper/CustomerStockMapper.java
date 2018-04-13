package com.hhnz.customerInv.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hhnz.customerInv.dto.AllocationDetailsDTO;
import com.hhnz.customerInv.dto.ArInvoicesItemDTO;
import com.hhnz.customerInv.dto.CustomerInvAllocationDTO;

public interface CustomerStockMapper {

	List<CustomerInvAllocationDTO> getList(Map<String, Object> params);

	int countList(Map<String, Object> params);

	int validateAmt(Long id);

	List<String> validateNum(Long id);

	BigDecimal getPriceByMaterialId(Map<String, Object> params);

	List<AllocationDetailsDTO> selectOrderLineDetails(Long id);

	List<ArInvoicesItemDTO> selectArInvoicesItems(Long id);

	BigDecimal getCombinationPrice(Map<String, Object> params);
	
	List<CustomerInvAllocationDTO> transferDetails(Map<String, Object> params);

	void closeOrder(Map<String, Object> params);

	int validateSubsidyAmt(Long id);

}
