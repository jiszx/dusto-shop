package com.hhnz.process.task.priceAdjust;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.enu.PriceAdjustOperation;
import com.hhnz.crm.enu.PriceAdjustStatus;
import com.hhnz.crm.enu.PriceAdjustType;
import com.hhnz.crm.model.TMaterialPrice;
import com.hhnz.crm.model.TMaterialPriceAdjust;
import com.hhnz.crm.model.TMaterialPriceAdjustRecord;
import com.hhnz.crm.model.TPriceAdjustApproved;
import com.hhnz.crm.service.IProductPriceService;
import com.hhnz.rmi.util.BeanUtils;
import com.hhnz.rmi.util.BigDecimalUtil;

/**
 * 审批完成的流程
 * @author: chaoyang.ren
 * @date:Sep 22, 2017
 * @time:4:44:27 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Service("priceAdjustTaskService")
@Transactional
public class PriceAdjustTaskService implements JavaDelegate {
	private static final Log LOG = LogFactory.getLog(PriceAdjustTaskService.class);
	@Autowired
	private IProductPriceService productPriceService;
	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//客户编号
        Integer flag = (Integer) delegateExecution.getVariable("FLAG");//0 驳回 1通过
        TMaterialPriceAdjust priceAdjust = productPriceService.findAdjustById(id);
        if(flag == 1){
        	PriceAdjustType adjustType = priceAdjust.getAdjustTypeEnu();
        	List<TMaterialPriceAdjustRecord> adjustDetails = productPriceService.findPriceAdjustDetails(id);
        	if(adjustDetails == null || adjustDetails.isEmpty()){
        		LOG.warn("审批通过的价格调整数据没有具体的物料，调整ID："+id);
        	}
        	//调整的是标准价
        	Date now = new Date();
			if(PriceAdjustType.STANDARD.equals(adjustType)){
        		List<TMaterialPrice> mps = new ArrayList<>();
        		for (TMaterialPriceAdjustRecord par : adjustDetails) {
        			TMaterialPrice mp = new TMaterialPrice();
        			BeanUtils.copyProperties(par, mp);
        			mp.setPrice(par.getPrice().longValue());
        			/*
        			 * 如果标准价现在就生效，查询当前有效调整价，需以自动任务处理每日的调整价生效情况
        			 * 以防止基础价有效期与调整价有效期冲突的情况
        			 */
        			if(mp.getBdate().compareTo(now) <= 0){
        				TPriceAdjustApproved adjust = productPriceService.findActiveAdjustByMaterialIdAndOrgIdAndChannel(now, mp.getMaterialId(), mp.getOrganizationId(), mp.getChannel());
        				if(adjust != null){
        					BigDecimal adjustPrice = adjust.getPrice();
							if(PriceAdjustOperation.ADD.equals(adjust.getOptEnu())){
        						mp.setAdjustPrice(adjustPrice);
        					}else if (PriceAdjustOperation.MULTIPLY.equals(adjust.getOptEnu())){
        						adjustPrice = BigDecimalUtil.divide100(adjustPrice);//倍数时adjustPrice不应由元转为分，因此转换回去
        						BigDecimal originPrice = BigDecimal.valueOf(mp.getPrice());
        						BigDecimal transfertoAdd = adjustPrice.multiply(originPrice).subtract(originPrice);
        						mp.setAdjustPrice(transfertoAdd);
        					}
        				}
        			}
        			mps.add(mp);
        			//后续如果有重复期间会直接取最新值,此处不处理重复有效期部分,否则需增加处理记录
				}
        		productPriceService.insert(mps);
        	}
        	//调整的是调整价
        	else if(PriceAdjustType.ADJUSTED.equals(adjustType)){
        		List<TPriceAdjustApproved> adjustApprovedList = new ArrayList<>();
        		List<TMaterialPrice> mps = new ArrayList<>();
        		for (TMaterialPriceAdjustRecord par : adjustDetails) {
        			TPriceAdjustApproved paa = new TPriceAdjustApproved();
        			BeanUtils.copyProperties(par, paa);
        			paa.setRecordId(par.getId());
        			paa.setCreateTs(now);
        			paa.setOpt(priceAdjust.getAdjustOptEnu());
        			adjustApprovedList.add(paa);
        			/*
        			 * 如果调整价现在就生效，查询当前有效标准价，需以自动任务处理每日的调整价生效情况
        			 * 以防止基础价有效期与调整价有效期冲突的情况
        			 */
        			if(paa.getBdate().compareTo(now) < 0){
        				TMaterialPrice activePrice = productPriceService.findActiveByMaterialIdAndOrgIdAndChannel(paa.getMaterialId(), paa.getOrganizationId(), paa.getChannel());
        				if(activePrice != null){
        					BigDecimal adjustPrice = paa.getPrice();
							if(PriceAdjustOperation.ADD.equals(paa.getOptEnu())){
        						activePrice.setAdjustPrice(adjustPrice);
        					}else if (PriceAdjustOperation.MULTIPLY.equals(paa.getOptEnu())){
        						adjustPrice = BigDecimalUtil.divide100(adjustPrice);//倍数时adjustPrice不应由元转为分，因此转换回去
        						BigDecimal originPrice = BigDecimal.valueOf(activePrice.getPrice());
        						BigDecimal transfertoAdd = adjustPrice.multiply(originPrice).subtract(originPrice);
        						activePrice.setAdjustPrice(transfertoAdd);
        					}
							mps.add(activePrice);
        				}
        			}
				}
        		productPriceService.insertApprovedAdjustBatch(adjustApprovedList);
        		if(!mps.isEmpty()){
        			productPriceService.update(mps);
        		}
        	}
        	priceAdjust.setStatus(PriceAdjustStatus.APPROVED);
        }else{
        	priceAdjust.setStatus(PriceAdjustStatus.REJECT);
        }
        productPriceService.updateAdjustPrice(priceAdjust);
    }
	
}
