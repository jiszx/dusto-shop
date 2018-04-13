package com.hhnz.task.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hhnz.api.cache.CacheService;
import com.hhnz.jco.business.inventory.InventoryQueryCallback;
import com.hhnz.jco.business.inventory.InventoryQueryRFC;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.rmi.db.model.inventory.VirtualWarehouse;
import com.hhnz.rmi.db.repository.inventory.VirtualWarehouseRepository;

/**
 * 库存查询、同步任务 执行时间：每隔7天凌晨4点
 * 
 * @author: chaoyang.ren
 * @date:Apr 26, 2017
 * @time:5:07:01 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Service
public class InventoryQueryTask {
	private static Logger log = LoggerFactory.getLogger(InventoryQueryTask.class);

	private static String lockKey = "lockTag-checkInvquery";

	@Resource
	private VirtualWarehouseRepository virtualWarehouseRepository;
	@Autowired
	private InventoryQueryRFC inventoryQueryRFC;
	@Resource
	private InventoryQueryCallback inventoryQueryCallback;

	@Resource
	private CacheService cacheService;

	@Scheduled(cron = "0 0 4 ? * MON-FRI")
	public void checkInv() {
		log.info("执行库存同步任务！");
		boolean result = cacheService.putIfAbsent(lockKey, "1");
		if (result == false) {
			return;
		}
		cacheService.getAndExpire(lockKey);
		List<VirtualWarehouse> vwList = virtualWarehouseRepository.findAll();
		for (VirtualWarehouse vw : vwList) {
			Long vwId = vw.getId();
			try {
				String input = inventoryQueryRFC.constructParam(vw);
				inventoryQueryRFC.execute(new RfcRedoDto(input, RfcExeType.INVENTORY_QUERY, inventoryQueryCallback));
			} catch (Exception e) {
				log.error("库存同步失败，失败的ID:"+vwId);
			}

		}
		cacheService.delete(lockKey);
	}

}
