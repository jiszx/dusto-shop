package com.hhnz.process.task.paper;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhnz.customer.mapper.CMerchCustContractMapper;
import com.hhnz.customer.mapper.CMerchCustStationMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustContract;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.jco.business.customer.CustomerRFC;
import com.hhnz.jco.business.customer.InputDTO;
import com.hhnz.jco.business.customer.TempCustomerRFCCallback;

/**
 * Created by yang on 2016-8-23.
 */

@Service("paperHandlerTask")
public class PaperCustomerTask implements JavaDelegate {

    @Autowired
    private CMerchCustContractMapper contractMapper;

    @Autowired
    private CMerchCustStationMapper stationMapper;
    
    @Autowired
    private CustomerRFC customerRFC;
    
    @Autowired
	private ICustomerService customerService;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//合同号
        CMerchCustContract vo = this.contractMapper.selectByPrimaryKey(id);
        CMerchCustBase base = customerService.findCustBaseById(vo.getMerchCustId());
        if(!(base.getOrganizationId().startsWith(vo.getOrganizationId()))){ //不是同一个销售组织
            base.setOrganizationId(vo.getOrganizationId());
            //新的销售组织推送SAP
            InputDTO input = customerRFC.constructInputParam(base);
            customerRFC.executeInThread(input, TempCustomerRFCCallback.class.getSimpleName());
            base.setCreateTs(new Date());
            base.setUpdateTs(base.getCreateTs());
            customerService.add(base);
            //合同对应新销售组织客户
            vo.setMerchCustId(base.getId());
            contractMapper.updateByPrimaryKeySelective(vo);
        }


        //根据合同号，找到客户，如果合同所对应的类型，创建不同的客户基本信息，并关联主档ID
    }
}
