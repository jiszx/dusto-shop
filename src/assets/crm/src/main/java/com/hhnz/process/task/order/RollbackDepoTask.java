package com.hhnz.process.task.order;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.salepolicy.mapper.OmPolicyLinesMapper;
import com.hhnz.virtualwarehouse.mapper.CrmVirtualWarehouseMapper;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;

@Service("rollbackDepo")
@Transactional
public class RollbackDepoTask implements JavaDelegate {

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
  private ReduceDepotTask reduceDepot;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Long id = (Long) execution.getVariable("key");// 订单号或批次号
    String orderType = (String) execution.getVariable("type");
    String ids = (String) execution.getVariable("ids");

    if ("1".equals(orderType)) {
      for (String orderid : ids.split(",")) {
        rollbackDepo(Long.parseLong(orderid));
      }
    } else {
      rollbackDepo(id);
    }
    execution.setVariable("recvCount", BigDecimal.ZERO);
  }

  public int rollbackDepo(Long id) {
    OmOrderHeadersAll order = orderHeaderMapper.selectByPrimaryKey(id);
    OmOrderLinesAllExample ex = new OmOrderLinesAllExample();
    ex.createCriteria().andHeaderIdEqualTo(id);
    List<OmOrderLinesAll> lines = orderLineMapper.selectByExample(ex);
    Long hasContractMerch = orderService.distriButeMerchid(order.getMerchCustId());
    List<CMerchCustContract> contracts =
        contractService.findByCustomerAndOrgId(hasContractMerch, order.getOrganizationId());
    CMerchCustContract contract = contracts.get(0);

    int result = 0;
    for (OmOrderLinesAll line : lines) {
      BigDecimal lineSum = BigDecimal.ZERO;
      CrmVirtualWarehouse warehouse = reduceDepot.getWarehouse(contract.getFactoryId(),
          line.getMaterialId(), contract.getVirtualWarehouse());
      if(warehouse==null){
        return result;
      }

      lineSum = line.getNum().add(line.getHbNum() == null ? BigDecimal.ZERO : line.getHbNum());
      warehouse.setAmt(warehouse.getAmt()
          .add(line.getDeliveredNum() == null ? lineSum : line.getDeliveredNum()));
      warehouse.setFrozenAmt(warehouse.getFrozenAmt().subtract(lineSum));
      result += warehouseMapper.updateByPrimaryKey(warehouse);
    }
    return result;
  }

}
