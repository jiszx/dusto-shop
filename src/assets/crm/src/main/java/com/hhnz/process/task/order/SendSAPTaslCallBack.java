package com.hhnz.process.task.order;

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

@Component("SendSAPTaslCallBack")
@Transactional
public class SendSAPTaslCallBack implements RFCCallback{
	
	@Resource
	private  OmOrderHeadersAllMapper headermapper;
	
	@Resource
	private  OmOrderSpiltsMapper  linemapper;
	
	@Resource
	private CustomerStockService  service;
	
	@Resource
	private  CMerchCustContractMapper  contractmapper;
	
	@Override
	public void errorCallBack(CallbackParam result) {
		
	}

	@Override
	public void successCallBack(CallbackParam result) {
		/*SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		Long id = result.getId();
		//获取订单头
		OmOrderHeadersAll  order = this.headermapper.selectByPrimaryKey(id);
		if("7".equals(order.getOrderType())){
			//调拨单转化订单增加库存
			OmOrderHeadersAll header = this.headermapper.selectByPrimaryKey(Long.parseLong(order.getAttribute13()));
			
			OmOrderSpiltsExample linex = new OmOrderSpiltsExample();
			OmOrderSpiltsExample.Criteria linext = linex.createCriteria();
			linext.andHeaderIdEqualTo(header.getId());
			List<OmOrderSpilts> lines = this.linemapper.selectByExample(linex);
			
			CMerchCustContractExample contractEx  = new CMerchCustContractExample();
			CMerchCustContractExample.Criteria contractExt= contractEx.createCriteria();
			contractExt.andMerchCustIdEqualTo(header.getMerchCustId());
			contractExt.andOrganizationIdEqualTo(header.getOrganizationId());
			contractExt.andStatesEqualTo("4");
			List<CMerchCustContract> contracts = this.contractmapper.selectByExample(contractEx);
			CMerchCustContract contract = contracts.size()==1?contracts.get(0):null;
			for(OmOrderSpilts line :lines){
				this.service.doAddProductInv(header,line,contract,sf.format(new Date()));			
			}
		}*/
	}

}
