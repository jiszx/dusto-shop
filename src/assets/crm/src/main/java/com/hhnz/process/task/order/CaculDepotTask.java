package com.hhnz.process.task.order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractExample;
import com.hhnz.order.mapper.OmOrderDeliveredMapper;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.model.OmOrderDelivered;
import com.hhnz.order.model.OmOrderDeliveredExample;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.virtualwarehouse.mapper.CrmVirtualWarehouseMapper;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseExample;

@Service("caculDepot")
@Transactional
public class CaculDepotTask implements JavaDelegate {

  @Resource
  private OmOrderHeadersAllMapper orderHeaderMapper;
  @Resource
  private OmOrderDeliveredMapper deliveryMapper;
  @Resource
  private OmOrderSpiltsMapper spliteMapper;
  @Resource
  private CrmVirtualWarehouseMapper warehouseMapper;
  @Resource
  private CMerchCustContractMapper contractMapper;
  @Resource
  private CMerchCustBaseMapper custbaseMapper;

  /**
   * 若发货数量少于订单数量，增加库存
   */
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Long id = (Long) execution.getVariable("key");// 订单号

    Map<String, Object> param = new HashMap<>();
    param.put("id", id);
    OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(id);
    
    CMerchCustContractExample contractEx = new CMerchCustContractExample();
    contractEx.or().andMerchCustIdEqualTo(order.getMerchCustId());
    contractEx.setOrderByClause("CONTRACT_EDATE desc");
    List<CMerchCustContract> contracts = contractMapper.selectByExample(contractEx);
    CMerchCustContract contract = contracts.get(0);

    OmOrderSpiltsExample splitsEx = new OmOrderSpiltsExample();
    splitsEx.createCriteria().andHeaderIdEqualTo(id);
    List<OmOrderSpilts> splites = spliteMapper.selectByExample(splitsEx);

    OmOrderDeliveredExample example = new OmOrderDeliveredExample();
    example.createCriteria().andSapOrderIdEqualTo(order.getSapOrderId());
    List<OmOrderDelivered> list = deliveryMapper.selectByExample(example);
    for (OmOrderSpilts splite : splites) {
      BigDecimal depotAddNum = depotAddNum(splite, list);
      CrmVirtualWarehouseExample warehouseEx = new CrmVirtualWarehouseExample();
      warehouseEx.createCriteria().andFactoryCodeEqualTo(contract.getFactoryId())
          .andMaterialIdEqualTo(splite.getMaterialId()).andCustTypeEqualTo(getCustType(order.getMerchCustId()));
      List<CrmVirtualWarehouse> warehouses = warehouseMapper.selectByExample(warehouseEx);
      CrmVirtualWarehouse warehouse = warehouses.get(0);
      warehouse.setAmt(warehouse.getAmt().add(depotAddNum));
      warehouse.setFrozenAmt(warehouse.getFrozenAmt().subtract(splite.getNum()));
      warehouseMapper.updateByPrimaryKey(warehouse);
    }
  }
  
  private String getCustType(Long merchid){
    CMerchCustBase merch = custbaseMapper.selectByPrimaryKey(merchid);
    if(!"5".equals(merch.getCustType())){
      return merch.getCustType();
    }
    CMerchCustBase dist = custbaseMapper.selectByPrimaryKey(merch.getPid());
    return dist.getCustType();
  }

  private BigDecimal depotAddNum(OmOrderSpilts splite, List<OmOrderDelivered> deliverys) {
    if (splite == null) {
      return new BigDecimal("0");
    }
    if (deliverys == null || deliverys.isEmpty()) {
      return splite.getNum();
    }
    BigDecimal result = splite.getNum();
    for (OmOrderDelivered delivery : deliverys) {
      if (delivery.getOrderitemSapNo().equals(splite.getOrderitemSapNo())) {
        result = result.subtract(new BigDecimal(delivery.getNum()));
      }
    }
    return result;
  }

}
