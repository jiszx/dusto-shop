package com.hhnz.process.task.priceAdjust;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.enu.PriceAdjustStatus;
import com.hhnz.crm.model.TMaterialPriceAdjust;
import com.hhnz.crm.service.IProductPriceService;

/**
 * 价格审批流程初始化
 * @author: chaoyang.ren
 * @date:Sep 22, 2017
 * @time:4:43:49 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Service("priceAdjustInitService")
@Transactional
public class PriceAdjustInitService implements JavaDelegate {
	@Autowired
	private IProductPriceService productPriceService;
	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//价格调整编号
        TMaterialPriceAdjust existedOne = productPriceService.findAdjustById(id);
        existedOne.setProcessId(delegateExecution.getProcessInstanceId());
        existedOne.setStatus(PriceAdjustStatus.SUBMITED);
        productPriceService.updateAdjustPrice(existedOne);
	}
}
