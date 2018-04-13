package com.hhnz.process.task.paper;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.CMerchCustContractLinesMapper;
import com.hhnz.customer.mapper.CMerchCustProductMapper;
import com.hhnz.customer.model.CMerchCustProductExample;

/**
 * Created by yang on 2016-8-23.
 */
@Service("paperProductsTask")
@Transactional
public class PaperProductsTask implements JavaDelegate {

    @Autowired
    private CMerchCustContractLinesMapper linesMapper;

    @Autowired
    private CMerchCustProductMapper productMapper;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = Long.parseLong(delegateExecution.getVariable("key").toString());//合同号
//        CMerchCustContractLinesExample ex = new CMerchCustContractLinesExample();
//        ex.createCriteria().andContractIdEqualTo(id);
//        List<CMerchCustContractLines> lines = this.linesMapper.selectByExample(ex);
//        for (CMerchCustContractLines line:lines) {
//            if("1".equals(line.getAgentType())){
//                //大类
//            }else if("2".equals(line.getAgentType())){
//                //品牌
//            }else if("3".equals(line.getAgentType())){
//                //系类
//            }else if("4".equals(line.getAgentType())){
//                //SKU
//            }
//        }
        // 根据合同号，找到合同行号，将相关产品插入到客户对应产品列表中 先删后存
        CMerchCustProductExample example = new CMerchCustProductExample();
        example.createCriteria().andContractIdEqualTo(id);
        productMapper.deleteByExample(example);
        productMapper.saveProduct(id);
    }
}
