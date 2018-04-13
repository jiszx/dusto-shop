package com.hhnz.task.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hhnz.api.cache.CacheService;
import com.hhnz.crm.enu.PriceAdjustOperation;
import com.hhnz.crm.model.TMaterialPrice;
import com.hhnz.crm.model.TPriceAdjustApproved;
import com.hhnz.crm.service.IProductPriceService;
import com.hhnz.rmi.util.BigDecimalUtil;
import com.hhnz.util.DateUtil;

/**
 * 定时处理调整价的匹配
 * @author: chaoyang.ren
 * @date:Sep 25, 2017
 * @time:2:53:43 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Service
public class AdjustPriceSettingTask {
	private static Logger log = LoggerFactory.getLogger(AdjustPriceSettingTask.class);

	private static String lockKey = "lockTag-checkAdjustPriceSetting";

	@Autowired
	private IProductPriceService productPriceService;

	@Resource
	private CacheService cacheService;

	@Scheduled(cron = "0 58 12 * * ?")
	public void checkInv() {
		log.info("调整价匹配任务！");
		boolean result = cacheService.putIfAbsent(lockKey, "1");
		if (result == false) {
			return;
		}
		cacheService.getAndExpire(lockKey);
		List<TMaterialPrice> allActivePrice = productPriceService.findAllActive();
		int i = 0;
		int batchNo = 50;
		List<TMaterialPrice> tempList = new ArrayList<>();
		for (TMaterialPrice mp : allActivePrice) {
			i++;
			Date today = DateUtil.getBeginingOfDay(new Date());
			TPriceAdjustApproved adjust = productPriceService.findActiveAdjustByMaterialIdAndOrgIdAndChannel(today, mp.getMaterialId(), mp.getOrganizationId(), mp.getChannel());
			if(adjust != null){
				BigDecimal adjustPrice = adjust.getPrice();
				Assert.notNull(adjustPrice, "调整价的价格不能为空！");
				if(PriceAdjustOperation.ADD.equals(adjust.getOptEnu())){
					//如果与当前记录的调整价相等，则不处理
					if(adjustPrice.compareTo(mp.getAdjustPrice()) == 0){
						continue;
					}
					mp.setAdjustPrice(adjustPrice);
				}else if (PriceAdjustOperation.MULTIPLY.equals(adjust.getOptEnu())){
					adjustPrice = BigDecimalUtil.divide100(adjustPrice);//倍数时adjustPrice不应由元转为分，因此转换回去
					BigDecimal originPrice = BigDecimal.valueOf(mp.getPrice());
					BigDecimal transfertoAdd = adjustPrice.multiply(originPrice).subtract(originPrice);
					//如果与当前记录的调整价相等，则不处理
					if(transfertoAdd.compareTo(mp.getAdjustPrice()) == 0){
						continue;
					}
					mp.setAdjustPrice(transfertoAdd);
				}
				tempList.add(mp);
			}else{
				//如果不存在调整价且当前记录的调整价为空或者0，则不处理
				if(mp.getAdjustPrice() == null || BigDecimal.ZERO.equals(mp.getAdjustPrice())){
					continue;
				}else{
					//如果不存在调整价且当前记录的调整价不为空或者0，则置为0
					mp.setAdjustPrice(BigDecimal.ZERO);
					tempList.add(mp);
				}
			}
			int size = tempList.size();
			if(size%batchNo == batchNo-1){
				productPriceService.update(tempList);
				tempList = new ArrayList<>();
			}
			if(!tempList.isEmpty() && size%batchNo < batchNo-1 && i == allActivePrice.size()-1){
				productPriceService.update(tempList);
			}
		}
		cacheService.delete(lockKey);
	}

}
