package com.hhnz.jco.business.inventory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.rmi.db.model.inventory.VirtualWarehouse;
import com.hhnz.rmi.db.repository.inventory.VirtualWarehouseRepository;
import com.hhnz.rmi.util.BigDecimalUtil;

/**
 * 库存查询成功后更新数据库
 * @author: chaoyang.ren
 * @date:Apr 26, 2017
 * @time:9:51:03 AM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
@Component("InventoryQueryCallback")
@Transactional
public class InventoryQueryCallback implements RFCCallback{
	private static Logger log = LoggerFactory.getLogger(InventoryQueryCallback.class);
	
	@Resource
	private VirtualWarehouseRepository virtualWarehouseRepository;
	
	@Override
	public void errorCallBack(CallbackParam result) {
		//do nothing but log.
		log.error("库存同步失败，原因:"+result.getResult().getMESSAGE()+", param:"+result.getData().get("param"));
	}

	@Override
	public void successCallBack(CallbackParam result) {
		Map<String, Object> data = result.getData();
		String factoryCode = (String) data.get("factoryCode");
		String rdcCode = (String) data.get("rdcCode");
		String materialId = (String) data.get("materialId");
		BigDecimal atpNumber = (BigDecimal) data.get("atpNumber");
		BigDecimal totalNumber = (BigDecimal) data.get("totalNumber");
		List<VirtualWarehouse> vws = virtualWarehouseRepository.findByFactoryCodeAndVwNoAndMaterialId(factoryCode, rdcCode, materialId);
		if(vws != null && !vws.isEmpty()){
			VirtualWarehouse virtualWarehouse = vws.get(0);
			virtualWarehouse.setAmt(atpNumber);
			virtualWarehouse.setFrozenAmt(BigDecimalUtil.substract(totalNumber, atpNumber));
			virtualWarehouse.setUpdateTs(new Date());
			virtualWarehouse.setUpdateOid(0l);
			virtualWarehouseRepository.save(virtualWarehouse);
		}
	}
}
