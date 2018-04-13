package com.hhnz.process.task.costpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhnz.cost.mapper.CrmCostAdjustMapper;
import com.hhnz.cost.mapper.CrmCostBalancesMapper;
import com.hhnz.cost.mapper.CrmCostLogMapper;
import com.hhnz.cost.mapper.CrmCostMainMapper;
import com.hhnz.cost.model.CrmCostAdjust;
import com.hhnz.cost.model.CrmCostBalances;
import com.hhnz.cost.model.CrmCostBalancesExample;
import com.hhnz.cost.model.CrmCostLog;
import com.hhnz.cost.model.CrmCostMain;
import com.hhnz.cost.model.CrmCostMainExample;

/**
 * Created by yang on 2016-8-4.
 */

/**
 * 费用调整处理节点
 */
@Service("costAdjustTask")
@Transactional
public class CostAdjustServiceTask implements JavaDelegate{
  private static Logger logger = LoggerFactory.getLogger(CostAdjustServiceTask.class);
  private static ObjectMapper mapper = new ObjectMapper(); 
  
  @Autowired
  private CrmCostAdjustMapper adjustMapper;
  @Autowired
  private CrmCostMainMapper costMapper;
  
  @Resource
  private  CrmCostLogMapper logmapper;
  
  @Resource
  private CrmCostBalancesMapper balancemapper;
    /**
     * 费用调整通过后，更改调整条目状态，更新费用池金额
     */
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());
        logger.info("cost adjust:{}",mapper.writeValueAsString(delegateExecution.getVariables()));
        CrmCostAdjust adjust = adjustMapper.selectByPrimaryKey(id);
        CrmCostMainExample ex = new CrmCostMainExample();
        ex.createCriteria().andOrganizationIdEqualTo(adjust.getOrganizationId())
            .andRegionIdEqualTo(adjust.getReginId()).andCostTypeidEqualTo(adjust.getCostTypeid());
        List<CrmCostMain> costMains = costMapper.selectByExample(ex);
        CrmCostMain cost = costMains.get(0);
        cost.setAmt(cost.getAmt().add(adjust.getAdjustAmt()));
        int update = costMapper.updateByPrimaryKey(cost);
        if(update==1){
          //修改期间余额
         SimpleDateFormat  sf = new SimpleDateFormat("yyyy-MM");	
         String period = sf.format(new Date());
         CrmCostLog  log = new CrmCostLog();
         log.setdAmt(adjust.getAdjustAmt());
         log.setPeriod(period);
         log.setCostTypeid(adjust.getCostTypeid());
         log.setOrganizationId(adjust.getOrganizationId());
         log.setOrderId(adjust.getId());
         log.setRegionId(adjust.getReginId());
         log.setReason(adjust.getReason());
         log.setProvId(adjust.getProvId());
         log.setType("1");
         log.setRemark(adjust.getRemark());
         this.logmapper.insert(log);
         
         //更新期间表
         CrmCostBalancesExample bex = new CrmCostBalancesExample();
         CrmCostBalancesExample.Criteria bext = bex.createCriteria();
         bext.andPeriodEqualTo(period);
         bext.andCostMainidEqualTo(cost.getId());
         List<CrmCostBalances> balances = this.balancemapper.selectByExample(bex);
         if(balances !=null && balances.size()>0){        	 
        	 CrmCostBalances balance = balances.get(0);
        	 balance.setdAmt(balance.getdAmt().add(adjust.getAdjustAmt()));
        	 this.balancemapper.updateByPrimaryKeySelective(balance);
         }
        }else{
          logger.warn("adjust cost fail adjustid:{} costid:{} amt:{}", id, cost.getId(), adjust.getAdjustAmt());
        }
        
    }
}
