package com.hhnz.process.task.order;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.mapper.CMerchCustDistributionMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.process.task.order.ReduceDepotTask.ReduceResult;
import com.hhnz.salepolicy.mapper.OmPolicyLinesMapper;
import com.hhnz.util.DateUtil;
import com.hhnz.virtualwarehouse.mapper.CrmVirtualWarehouseMapper;

@Transactional
@Service("reduceKaOrderDepo")
public class ReduceKaOrderDepoTask implements JavaDelegate {
  private static Logger logger = LoggerFactory.getLogger(ReduceKaOrderDepoTask.class);

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
  @Resource
  private ReduceDepotTask reduceDepo;
  @Resource
  private CustomerStockService stockService;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Long id = Long.parseLong(execution.getVariable("key").toString());// 订单号
    ReduceResult result = reduceByOrder(id);
    execution.setVariable("sendCount", result.depo);
    execution.setVariable("depo_enough", result.isDepoEnough ? 1 : 0);

  }

  ReduceResult reduceByOrder(Long id) {
    ReduceResult result = new ReduceResult();
    OmOrderHeadersAll header = orderHeaderMapper.selectByPrimaryKey(id);
    String period = DateUtil.periodNow();
    /*
     * // 获取合同数据 CMerchCustContract contract = reduceDepo.getContract(header);
     */
    CMerchCustBase merch = this.custbaseMapper.selectByPrimaryKey(header.getMerchCustId());
    CMerchCustBase transferMerch = custbaseMapper.selectByPrimaryKey(merch.getPid());
    // 获取订单行数据
    OmOrderSpiltsExample ex = new OmOrderSpiltsExample();
    OmOrderSpiltsExample.Criteria ext = ex.createCriteria();
    ext.andHeaderIdEqualTo(header.getId());
    List<OmOrderSpilts> lines = spliteMapper.selectByExample(ex);

    BigDecimal sum = BigDecimal.ZERO;
    for (OmOrderSpilts line : lines) {
      // 更新客户库存数据
      int reduceResult =
          stockService.reduceProductInvByOrder(header, line, transferMerch, period);
      if (reduceResult == -1) {
        logger.error("订单扣减客户库存，库存不足 orderid:{} merch:{} material:{} org:{}", header.getId(),
            transferMerch.getId(), line.getMaterialId(), header.getOrganizationId());
        throw new IllegalArgumentException("库存不足");
      }
      sum = sum.add(line.getNum());
    }
    result.depo = sum;
    return result;
  }

}
