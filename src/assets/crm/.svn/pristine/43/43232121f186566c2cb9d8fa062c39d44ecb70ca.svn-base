package com.hhnz.process.task.orderback;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.business.inventory.InputDTO;
import com.hhnz.jco.business.inventory.InventoryRFC;
import com.hhnz.jco.business.order.OrderRFC;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.model.OmOrderHeadersAll;

@Service("sendBackOrderSAPTask")
public class SendBackOrderSAPTask implements JavaDelegate {

	@Autowired
	private OrderRFC orderRFC;
	@Resource
	private InventoryRFC inventoryRFC;
	@Resource
	private CMerchCustBaseMapper merchMapper;
	@Resource
	private OmOrderHeadersAllMapper orderHeaderMapper;
	
    @SuppressWarnings("deprecation")
	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//订单号
        OmOrderHeadersAll order = this.orderHeaderMapper.selectByPrimaryKey(id);
        CMerchCustBase merch = this.merchMapper.selectByPrimaryKey(order.getMerchCustId());
        if("8".equals(merch.getCustType())){
        	InputDTO input = inventoryRFC.constructInputParam(id);
        	inventoryRFC.executeInThread(input,SendBackOrderSapCallBack.class.getSimpleName());
        }if("70".equals(merch.getCustType())){
        	//合作仓储服务商推送对应合作盐业公司退货订单
        	Long headerId = Long.parseLong(order.getAttribute2());
        	com.hhnz.jco.business.order.InputDTO input = orderRFC.constructInputParam(headerId,true);
        	orderRFC.executeInThread(input,SendBackOrderSapCallBack.class.getSimpleName());  
        }else{
        	//标准订单，特殊订单推送订单
        	com.hhnz.jco.business.order.InputDTO input = orderRFC.constructInputParam(id,true);
        	orderRFC.executeInThread(input,SendBackOrderSapCallBack.class.getSimpleName());        	
        }
    }
}
