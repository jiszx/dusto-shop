package com.hhnz.process.task.order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.MoreObjects;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.mapper.CMerchCustDistributionMapper;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.salepolicy.mapper.OmPolicyLinesMapper;
import com.hhnz.virtualwarehouse.mapper.CrmVirtualWarehouseMapper;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseExample;

@Service("reduceDepot")
@Transactional
public class ReduceDepotTask implements JavaDelegate {
  private static Logger logger = LoggerFactory.getLogger(ReduceDepotTask.class);

  @Resource
  private OmOrderHeadersAllMapper orderHeaderMapper;
  @Resource
  private OmOrderSpiltsMapper spliteMapper;
  @Resource
  private OrderUtilMapper orderUtilMapper;
  @Resource
  private OmOrderLinesAllMapper orderLineMapper;
  @Resource
  private CrmVirtualWarehouseMapper warehouseMapper;
  @Resource
  private CMerchCustContractMapper contractMapper;
  @Resource
  private OmPolicyLinesMapper policyLineMapper;
  @Resource
  private CMerchCustBaseMapper custbaseMapper;
  @Resource
  private CustomerContractService contractService;
  @Resource
  private OrderService orderService;
  @Resource
  private OrderUtilService utilService;
  @Resource
  private CMerchCustDistributionMapper distMapper;
  /**
   * 通过订单扣减库存 若库存不够直接抛出异常
   */
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Long id = (Long) execution.getVariable("key");// 订单号 批次号
    String orderType = (String) execution.getVariable("type");
    String ids = (String) execution.getVariable("ids");

    BigDecimal total = BigDecimal.ZERO;
    boolean depoEnough = true;
    if("1".equals(orderType)){
      for(String orderid:ids.split(",")){
        ReduceResult reduce = reduceByOrder(Long.parseLong(orderid));
        total = total.add(reduce.depo);
        if(depoEnough){
          depoEnough = reduce.isDepoEnough;
        }
      }
    }else{
      ReduceResult reduce = reduceByOrder(id);
      total = total.add(reduce.depo);
    }
    
    execution.setVariable("sendCount", total);
    execution.setVariable("depo_enough", depoEnough?1:0);

  }
  
  public ReduceResult reduceByOrder(Long id){
    ReduceResult result = new ReduceResult();
    Map<String, Object> param = new HashMap<>();
    param.put("id", id);
    OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(id);
    CMerchCustContract contract = getContract(order);

    BigDecimal sum = BigDecimal.ZERO;
    OmOrderSpiltsExample exs = new OmOrderSpiltsExample();
    OmOrderSpiltsExample.Criteria ext = exs.createCriteria();
    ext.andHeaderIdEqualTo(order.getId());
    List<OmOrderSpilts> splits = spliteMapper.selectByExample(exs);
    for(OmOrderSpilts line:splits){
      CrmVirtualWarehouse warehouse =
        getWarehouse(contract.getFactoryId(), line.getMaterialId(), StringUtils.isNotEmpty(order.getRdcCode())?order.getRdcCode():contract.getVirtualWarehouse());
      if(warehouse==null && "1".equals(order.getOrderType())){
        recordReduceType1(line.getLineId(), BigDecimal.ZERO);
        result.isDepoEnough = false;
        result.depo = sum;
        continue;
      }
      if(warehouse==null){
        logger.warn("库存不足，订单：{} 物料：{} 库存：{}", order.getId(), line.getMaterialId(), null);
        throw new IllegalArgumentException("库存不足");
      }
      BigDecimal splitNum = MoreObjects.firstNonNull(line.getNum(), BigDecimal.ZERO);
      sum = sum.add(splitNum);
      BigDecimal warehouseResult = warehouse.getAmt().subtract(splitNum);
      boolean isDepoEnough = true;
      if (warehouseResult.compareTo(BigDecimal.ZERO)<0 && !"1".equals(order.getOrderType())) {
        logger.warn("库存不足，订单：{} 物料：{} 库存：{}", order.getId(), line.getMaterialId(), warehouseResult);
        throw new IllegalArgumentException("库存不足");
      }else if(warehouseResult.compareTo(BigDecimal.ZERO)<0){
        warehouseResult = BigDecimal.ZERO;
        result.isDepoEnough = false;
        isDepoEnough = false;
        recordReduceType1(line.getLineId(), warehouse.getAmt());
      }else{
        
      }
      if("1".equals(order.getOrderType()) && isDepoEnough){
        recordReduceType1(line.getLineId(), splitNum);
      }
      warehouse.setAmt(warehouseResult);
      warehouse.setFrozenAmt(warehouse.getFrozenAmt().add(splitNum));
      warehouseMapper.updateByPrimaryKey(warehouse);
    }
    result.depo = sum;
    return result;
  }
  
  CMerchCustContract getContract(OmOrderHeadersAll order){
    Long merchid = order.getMerchCustId();
    if("7".equals(order.getOrderType())){ // 转化订单客户使用送货地址对应的客户（合作仓储）
      CMerchCustDistribution dist = distMapper.selectByPrimaryKey(order.getShipId());
      merchid = dist.getMerchCustId();
    }
    Long merchWithContract = orderService.distriButeMerchid(merchid);
    List<CMerchCustContract> contracts = contractService.findByCustomerAndOrgId(merchWithContract, order.getOrganizationId());
    return contracts.get(0);
  }
  
  public CrmVirtualWarehouse getWarehouse(String factoryid, String materialid, String type) {
    CrmVirtualWarehouseExample warehouseEx = new CrmVirtualWarehouseExample();
    warehouseEx.createCriteria().andFactoryCodeEqualTo(factoryid)
        .andMaterialIdEqualTo(materialid).andCustTypeEqualTo(type);
    List<CrmVirtualWarehouse> warehouses = warehouseMapper.selectByExample(warehouseEx);
    return warehouses.isEmpty() ? null : warehouses.get(0);
  }
  
  private int recordReduceToLine(OmOrderLinesAll line, BigDecimal reduceDepo){
    line.setDeliveredNum(reduceDepo);
    return orderLineMapper.updateByPrimaryKey(line);
  }
  
  private int recordReduceType1(Long lineid, BigDecimal reduceDepo){
    OmOrderLinesAll line = orderLineMapper.selectByPrimaryKey(lineid);
    line.setDeliveredNum(reduceDepo);
    return orderLineMapper.updateByPrimaryKey(line);
  }
  
  static class ReduceResult{
    boolean isDepoEnough = true;
    BigDecimal depo;
  }
  
}
