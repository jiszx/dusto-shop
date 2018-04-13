package com.hhnz.order.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserStations;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.dto.ResponseResult;
import com.hhnz.order.model.OmOrderDeliveredV;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderHeadersAllV;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLogistics;
import com.hhnz.order.model.OmOrderMakeSure;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OrderDetail;
import com.hhnz.order.model.OrderLinesDetials;
import com.hhnz.order.model.OrderSearchModel;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.io.excel.util.excel.ExcelResult;

public interface OrderService {

	int addOrderHeader(OmOrderHeadersAll orderh);

	int addOrderLine(OmOrderLinesAll line);

	void updateOrderHeader(OmOrderHeadersAll orderh);

	AjaxDTO findOrderList(Map<String, Object> params);

	/* CrmSalesOrganization findOrderRegin(String stationorgid); */

	List<UserStations> getuserstations(String type, String orgid);

	int addSpiltsline(OmOrderSpilts spiltline);

	String orderAudit(Long headerid, String states, String orderType);

	OmOrderHeadersAll getOrderBYid(Long headerid);

	String addOrder(OmOrderHeadersAll orderh, List<OmOrderLinesAll> orderlines);

	OrderDetail orderDetail(Long id);

	int DeleteLine(Long id);

	/**
	 * 保存订单物流信息列表
	 * 
	 * @author: chaoyang.ren
	 * @date:2016年8月29日 上午11:31:55
	 * @param list
	 */
	public void save(List<OmOrderLogistics> list);

	/**
	 * 根据订单ID获取订单详细信息
	 * 
	 * @author: chaoyang.ren
	 * @date:2016年8月29日 下午5:41:23
	 * @param orderId
	 * @return
	 */
	OmOrderHeadersAllV getOrderDetailInfoById(Long orderId);

	/**
	 * 根据订单id获取发货信息
	 * 
	 * @author: chaoyang.ren
	 * @param orderId
	 * @date:2016年8月30日 上午9:45:12
	 * @return
	 */
	List<OmOrderDeliveredV> getOrderDeliveryItemsByOrderId(Long orderId);

	int editAddLine(OmOrderLinesAll lines);

	int editEditLine(OmOrderLinesAll lines);

	int delOrder(Long id);

	int updateOrderAmt(Long id);

	OmOrderHeadersAll orderSaveAgain(Long id, Long userid, UserStations station);

	void updateMakesure(List<OmOrderSpilts> orders,Long orderId,TEmployee user);

	/**
	 * @author: chaoyang.ren
	 * @date:2016年9月1日 上午10:44:16
	 * @param id
	 * @return
	 */
	List<OmOrderSpilts> findOrderSplitsByOrderId(Long id);

	/**
	 * @author: tangdonghui
	 * @date:2016年9月7
	 * @param id
	 * @return
	 */
	List<OrderLinesDetials> findOrderDeliveryDetails(String id);

	int receiveGoods(OrderLinesDetials[] orderLineDetialList);

	OrderDetail orderDelivery(Long id);

	Long findShipid(Long merchid, Long shipid);

	boolean isDistributeOrder(Long merchid);

	AjaxDTO orderLineDetails(Long id);

	/**
	 * 根据发货单号查询物流信息
	 * 
	 * @author: chaoyang.ren
	 * @date:2016年10月12日 下午2:29:48
	 * @param deliveryNo
	 * @return
	 */
	public OmOrderLogistics findLogisticsByDeliveryNo(String deliveryNo);

	Long findShipIdByMerchId(long merchid);

	BigDecimal getSumNumById(Long headerid);

	int updateRemark(Long id, String remark,Long shipid, String rdcCode,BigDecimal freight);

	int updateFactory(List<OrderLinesDetials> lines);

	List<CMerchCustDistribution> getMerchDistribution(Long merchCustId,
			String organizationId);

	/**
	 * @author: chaoyang.ren 
	 * @date:2016年10月26日  下午3:21:54
	 * @param emp
	 * @param headerid 
	 * @param orderType 
	 * @throws Exception 
	 */
	void startProcess(TEmployee emp, Long headerid, String orderType) throws Exception;

    Long distriButeMerchid(Long merchid);

    ResponseResult importOrders(String filePath, Long merchid, Long stationid, TEmployee user)
        throws EncryptedDocumentException, InvalidFormatException, IOException;

	AjaxDTO selectMakeSureList(AjaxDTO bean, String custname, Long orderId,
			String sapOrderId);

	void allocationSubmitMakeSure(List<OmOrderSpilts> orders,Long orderId,TEmployee user);

	ExcelResult importRetailOrders(String path, TEmployee user) throws InstantiationException, IllegalAccessException, IOException;

	/**
	 * 根据送达方及区间获取调拨单列表
	 * @author: chaoyang.ren 
	 * @date:Jun 7, 2017  5:19:22 PM
	 * @param custId
	 * @param period
	 * @return
	 */
	List<OmOrderHeadersAll> listTransferOrder(Long custId, String period);

	/**
	 * 根据仓储服务商ID及区间获取仓储服务商零售订单
	 * @author: chaoyang.ren 
	 * @date:Jun 8, 2017  3:06:52 PM
	 * @param custId
	 * @param period
	 * @return
	 */
	List<OmOrderHeadersAll> listRetailerOrder(Long custId, String period);

	List<OrderSearchModel> findOrderListAll(Map<String, Object> params);

	List<OrderSearchModel> selectOrderListAll(Map<String, Object> params);

	List<OmOrderMakeSure> selectMakeSureListall(AjaxDTO bean, String custname,
			Long orderId, String sapOrderId);

}
