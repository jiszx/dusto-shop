package com.hhnz.order.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hhnz.customer.dto.CustomerRetailDTO;
import com.hhnz.order.dto.DistributeOrderDTO;
import com.hhnz.order.dto.DistributeOrderDetailsDTO;
import com.hhnz.order.model.Invoice;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OrderCustomer;
import com.hhnz.order.model.OrderLinesDetials;
import com.hhnz.order.model.OrderMaterial;
import com.hhnz.order.model.OrderPolicy;
import com.hhnz.order.model.OrderSearchModel;

public interface OrderUtilMapper {

	List<OrderCustomer> getUserCustomerInfo(Map<String, Object> params);

	List<OrderMaterial> getCustomerMaterial(Map<String, Object> params);

	List<OrderPolicy> getOrderPolicy(Map<String, Object> params);

	Long getmaterialprice(String materialid);

	List<OrderSearchModel> findOrderList(Map<String, Object> params);

	String getcustnameByorderId(Long id);
	
	String getOrgnameByorderId(Long id);

	String getcustDisByorderId(Long id);

	List<OrderLinesDetials> getlinedata(Long headerid);
	
	List<OmOrderSpilts> findDeliverys(Map<String, Object> param);

	List<OrderLinesDetials> getOrderAmt(Long id);

	List<OrderCustomer> customerByOrgid(Long id);

	List<OrderLinesDetials> findOrderDeliveryDetails(String id);

	Long validateNum(Map<String, Object> params);
	
	Long validateNumByRdc(Map<String, Object> params);

	int getSpiltOrderNo(Long id);

	List<OrderLinesDetials> orderLineDetails(Long id);

	int orderInvoiceNumTotal(String orderid);
	
	List<Invoice> findOrderInvoice(String orderid);

	int countOrderList(Map<String, Object> params);

	BigDecimal getSunNumById(Long headerid);

	List<DistributeOrderDTO> findDistributeOrders(Map<String, Object> params);

	int countDistributeOrders(Map<String, Object> params);

	List<DistributeOrderDetailsDTO> getDistributeDetailList(
			Map<String, Object> params);

	int countDistributeDetailList(Map<String, Object> params);

	List<DistributeOrderDetailsDTO> distributorEditList(
			Map<String, Object> params);

	int countDistributorEditList(Map<String, Object> params);

	OrderLinesDetials getOrderLinesAllAmt(Long id);

	List<String> getOrderProcessIds(Long merchid);

	List<OrderMaterial> selectCombinationMaterial(Map<String, Object> params);

	BigDecimal selectPriceByMaterialId(Map<String, Object> params);

	List<CustomerRetailDTO> selectRetailCustomers(Map<String, Object> params);

	List<OrderMaterial> selectRetailProduct(Map<String, Object> params);

	List<OrderMaterial> selectCustProductByRdc(Map<String, Object> params);

	List<OrderLinesDetials> allocationDelivery(Long orderId);

	void updateLineDeliveredNum(Long orderId);

	List<OrderMaterial> selectKaProduct(Map<String, Object> params);

	List<OrderCustomer> selectBillCustomer(Long merchId);
	
	int updateTransferStatesWhenDelivery();

}
