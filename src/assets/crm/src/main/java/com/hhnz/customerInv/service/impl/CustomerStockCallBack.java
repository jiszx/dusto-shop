package com.hhnz.customerInv.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.model.CMerchCustContractExample;
import com.hhnz.customerInv.service.CustomerStockService;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.virtualwarehouse.mapper.CrmVirtualWarehouseMapper;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseExample;

@Component("CustomerStockCallBack")
@Transactional
public class CustomerStockCallBack implements RFCCallback {
	
	@Resource
	private OmOrderHeadersAllMapper headmapper;
	
	@Resource
	private  CMerchCustContractMapper  contractmapper;
	
	@Resource
	private OmOrderSpiltsMapper  linemapper;
	
	@Resource
	private CrmVirtualWarehouseMapper warehouseMapper;
	
	@Resource
	private  CustomerStockService  service;
	@Override
	public void errorCallBack(CallbackParam result) {
		
	}

	@Override
	public void successCallBack(CallbackParam result) {
		OmOrderHeadersAll  header = this.headmapper.selectByPrimaryKey(result.getId());
		header.setSapOrderId(result.getSapCode());
		//通过
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		//获取合同数据用于扣减库存
		CMerchCustContractExample  contractEx = new CMerchCustContractExample();
		CMerchCustContractExample.Criteria contractExt = contractEx.createCriteria();
		contractExt.andMerchCustIdEqualTo(header.getMerchCustId());
		contractExt.andOrganizationIdEqualTo(header.getOrganizationId());
		contractExt.andStatesEqualTo("4");
		List<CMerchCustContract> contracts = this.contractmapper.selectByExample(contractEx);
		CMerchCustContract  contract = new CMerchCustContract();
		if(contracts.size()==1 && contracts !=null){
			contract = contracts.get(0);
		}else{
			return;
		}
		//获取订单行数据
		OmOrderSpiltsExample  ex = new OmOrderSpiltsExample();
		OmOrderSpiltsExample.Criteria ext = ex.createCriteria();
		ext.andHeaderIdEqualTo(header.getId());
		List<OmOrderSpilts> lines = this.linemapper.selectByExample(ex);
		if(lines !=null && lines.size()>0){
			for (OmOrderSpilts line :lines){	
				//扣减库存
				CrmVirtualWarehouseExample warehouseEx = new CrmVirtualWarehouseExample();
				CrmVirtualWarehouseExample.Criteria warehouseExt= warehouseEx.createCriteria();
				warehouseExt.andFactoryCodeEqualTo(contract.getFactoryId());
				warehouseExt.andMaterialIdEqualTo(line.getMaterialId());
				//特殊调拨单通过订单获取RDC
				warehouseExt.andCustTypeEqualTo("8".equals(header.getOrderType())?header.getRdcCode():contract.getVirtualWarehouse());
				List<CrmVirtualWarehouse> warehouses = this.warehouseMapper.selectByExample(warehouseEx);
				CrmVirtualWarehouse warehouse = new CrmVirtualWarehouse();
				if(warehouses !=null && warehouses.size()>0){
				    warehouse = warehouses.get(0);
					warehouse.setAmt(warehouse.getAmt().subtract(line.getNum()));//扣减库存数据
					warehouse.setFrozenAmt(warehouse.getFrozenAmt().add(line.getNum()));//添加冻结数量
					this.warehouseMapper.updateByPrimaryKeySelective(warehouse);
				}
			}
		}
		//更新客户资金数据
		this.service.doUpdateAccount(header,sf.format(new Date()));
		header.setStates("5");
	    this.headmapper.updateByPrimaryKeySelective(header);
	}

}
