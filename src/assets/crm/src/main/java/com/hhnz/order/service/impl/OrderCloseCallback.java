package com.hhnz.order.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.mapper.OmOrderLinesAllMapper;
import com.hhnz.order.mapper.OmOrderSpiltsMapper;
import com.hhnz.order.mapper.OrderCloseMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.model.OmOrderSpilts;
import com.hhnz.order.model.OmOrderSpiltsExample;
import com.hhnz.order.service.IOrderCloseService;
import com.hhnz.organization.mapper.CrmSalesOrganizationMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.pub.mapper.TAreaMapper;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.virtualwarehouse.mapper.CrmVirtualWarehouseMapper;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouse;
import com.hhnz.virtualwarehouse.model.CrmVirtualWarehouseExample;

/**
 * @author: chaoyang.ren
 * @date:2016年11月16日
 * @time:上午11:31:42
 * @email:chaoyang.ren@foxmail.com
 */
@Component("OrderCloseCallback")
@Transactional
public class OrderCloseCallback implements RFCCallback{
	@Resource
	private OrderCloseMapper closemapper;
	
	@Resource
	private OmOrderHeadersAllMapper  headermapper;
	
	@Resource
	private OmOrderLinesAllMapper  linemapper;
	
	@Resource
	private OmOrderSpiltsMapper  spiltmapper;
	
	@Resource
	private CMerchCustAccountMapper  accountmapper;
	
	@Resource
	private CMerchCustBalancesMapper  balancesmapper;
	
	@Resource
	private IOrderCloseService orderCloseService;
	
	@Resource
	private CMerchCustAccountLogMapper  accountlogmapper;
	
	@Resource
	private OmOrderSpiltsMapper  spiltslinemapper;
	
	@Resource
	private CrmVirtualWarehouseMapper warehouseMapper;
	
	@Resource
	private  CMerchCustContractMapper  contractmapper;
	
	@Resource
	private CrmSalesOrganizationMapper csomapper;
	
	@Resource
	private TAreaMapper  areamapper;
	
	@Resource
	private CMerchCustBaseMapper merchmapper;
	
	@Resource
	private CrmVirtualWarehouseMapper cvwMapper;
	@Override
	public void errorCallBack(CallbackParam result) {
		Long id = result.getId();
		//获取订单头
		OmOrderHeadersAll order = this.headermapper.selectByPrimaryKey(id);
		order.setAttribute10(result.getResult().getMESSAGE());
		this.headermapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public void successCallBack(CallbackParam result) {
		Long id = result.getId();
		//获取订单头
		OmOrderHeadersAll  order = this.headermapper.selectByPrimaryKey(id);
		BigDecimal  cashAmt = BigDecimal.ZERO;
		BigDecimal  hbAmt= BigDecimal.ZERO;
		//BigDecimal  plicyAmt = BigDecimal.ZERO;
		OmOrderLinesAllExample  lineex = new OmOrderLinesAllExample();
		OmOrderLinesAllExample.Criteria lineext = lineex.createCriteria();
		lineext.andHeaderIdEqualTo(id);
		//订单行
		List<OmOrderLinesAll> lines = this.linemapper.selectByExample(lineex);
		for(OmOrderLinesAll line :lines){
			//拆分订单信息
			OmOrderSpiltsExample spiltex = new OmOrderSpiltsExample();
			OmOrderSpiltsExample.Criteria spiltext = spiltex.createCriteria();
			spiltext.andHeaderIdEqualTo(id);
			spiltext.andLineIdEqualTo(line.getId());
			List<OmOrderSpilts> spilts = this.spiltmapper.selectByExample(spiltex);
			BigDecimal  cashNum = BigDecimal.ZERO;
			BigDecimal  hbNum = BigDecimal.ZERO;
			BigDecimal  plicyNum = BigDecimal.ZERO;
			for(OmOrderSpilts spilt:spilts){
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("orderid", order.getSapOrderId());
				params.put("lineid", line.getId());
				params.put("itemNo", spilt.getOrderitemSapNo());
				params.put("materialId", spilt.getMaterialId());
				//应收发票数量
				BigDecimal  invouceNum = this.closemapper.getInvouceNum(params);
				//处理差异数量
				if(StringUtils.equals("2", spilt.getType())){
					//货补
					hbNum = hbNum.add(spilt.getNum().subtract(invouceNum));
					hbAmt = hbAmt.add(line.getOrderPrice().multiply(spilt.getNum().subtract(invouceNum)));
				}else if(StringUtils.equals("3", spilt.getType())){
					//销售政策
					plicyNum=plicyNum.add(spilt.getNum().subtract(invouceNum));
				}else{
					//标准订单
					cashNum=cashNum.add(spilt.getNum().subtract(invouceNum));
					cashAmt = cashAmt.add(line.getOrderPrice().multiply(spilt.getNum().subtract(invouceNum)));
				}
			}
			line.setReturnNum((line.getReturnNum()==null?BigDecimal.ZERO:line.getReturnNum()).add(cashNum));
			line.setRetrunHbNum((line.getRetrunHbNum()==null?BigDecimal.ZERO:line.getRetrunHbNum()).add(hbNum));
			line.setReturnPolicyNum((line.getReturnPolicyNum()==null?BigDecimal.ZERO:line.getReturnPolicyNum()).add(plicyNum));
			this.linemapper.updateByPrimaryKeySelective(line);
		}
		//获取客户资金余额
		CMerchCustAccountExample  accountex = new CMerchCustAccountExample();
		CMerchCustAccountExample.Criteria accountext = accountex.createCriteria();
		accountext.andMerchCustIdEqualTo(order.getMerchCustId());
		accountext.andOrganizationIdEqualTo(order.getOrganizationId());
		List<CMerchCustAccount> accounts = this.accountmapper.selectByExample(accountex);
		if(accounts !=null && accounts.size()==1){
			CMerchCustAccount account = accounts.get(0);
			account.setCashAmt(BigDecimalASME.multiply(account.getCashAmt()));
			account.setCreditAmt(BigDecimalASME.multiply(account.getCreditAmt()));
			account.setBondAmt(BigDecimalASME.multiply(account.getBondAmt()));
			account.setSubsidyAmt(BigDecimalASME.multiply(account.getSubsidyAmt()));
			//货补
			if(hbAmt.compareTo(BigDecimal.ZERO)==1){
				account.setSubsidyAmt(account.getSubsidyAmt().add(hbAmt));
				orderCloseService.InsertLog("2",hbAmt,order);
			}
			//现金
			if(cashAmt.compareTo(BigDecimal.ZERO)==1){
				if(account.getCreditAmt().compareTo(account.getContractCreditAmt()) ==-1){
					BigDecimal  amt = account.getContractCreditAmt().subtract(account.getCreditAmt());
					if(cashAmt.compareTo(amt)>0){
						//取消金额大于授信额度和可用授信额度差额
						orderCloseService.InsertLog("3",amt,order);
						account.setCreditAmt(account.getCreditAmt().add(amt));
						
						orderCloseService.InsertLog("1",cashAmt.subtract(amt),order);
						account.setCashAmt(account.getCashAmt().add(cashAmt.subtract(amt)));
					}else{
						//已使用授信金额大于退货金额，全部退换授信
						orderCloseService.InsertLog("3",cashAmt,order);
						account.setCreditAmt(account.getCreditAmt().add(cashAmt));
					}
				}else{
					//授信额度等于可用授信，退货金额全部退回现金账户
					orderCloseService.InsertLog("1",cashAmt,order);
					account.setCashAmt(account.getCashAmt().add(cashAmt));
				}
			}
			this.accountmapper.updateByPrimaryKeySelective(account);
		}
		
		order.setStates("10");
		this.headermapper.updateByPrimaryKeySelective(order);
		
		if("7".equals(order.getOrderType())){
			//合资仓储服务商碉堡单转换订单取消时，关闭调拨单
			this.orderCloseService.closeTransferOrder(order.getId());
		}else{
			//查询是否有发货
			String flag = this.closemapper.judgeDelivered(order.getSapOrderId());
			//E 表示没有发货
			if("E".equals(flag)){
				
			//处理客户库存数据
			String organizationId= order.getOrganizationId();
			//获取销售组织对应的SAPid
			CrmSalesOrganization cso= this.csomapper.selectByPrimaryKey(organizationId);
			String sapOrdId= cso.getSapId();
			
			//获取发货RDC
			String rdcCode = order.getRdcCode();
			if(rdcCode ==null || StringUtils.isEmpty(rdcCode)){
				CMerchCustBase merch= this.merchmapper.selectByPrimaryKey(order.getMerchCustId());
				rdcCode = this.areamapper.findRDCCodeByCity(merch.getCityId());
			}
			//获取订单物料信息
			OmOrderSpiltsExample spiltex = new OmOrderSpiltsExample();
			OmOrderSpiltsExample.Criteria spiltext = spiltex.createCriteria();
			spiltext.andHeaderIdEqualTo(id);
			List<OmOrderSpilts> spilts = this.spiltmapper.selectByExample(spiltex);
			if(spilts !=null && spilts.size() >0){
				for (OmOrderSpilts spilt:spilts){
					CrmVirtualWarehouseExample  ex= new CrmVirtualWarehouseExample();
					CrmVirtualWarehouseExample.Criteria ext = ex.createCriteria();
					ext.andFactoryCodeEqualTo(sapOrdId);
					ext.andCustTypeEqualTo(rdcCode);
					ext.andMaterialIdEqualTo(spilt.getMaterialId());
					List<CrmVirtualWarehouse> cvws = this.cvwMapper.selectByExample(ex);
					if(cvws !=null && cvws.size()==1){
						CrmVirtualWarehouse cvw = cvws.get(0);
						//库存数量增加，冻结数量减少
						cvw.setAmt(cvw.getAmt().add(spilt.getNum()));
						cvw.setFrozenAmt(cvw.getFrozenAmt().subtract(spilt.getNum()));
						this.cvwMapper.updateByPrimaryKey(cvw);
					}
				}
			}
			}
		}
	}
	
}
