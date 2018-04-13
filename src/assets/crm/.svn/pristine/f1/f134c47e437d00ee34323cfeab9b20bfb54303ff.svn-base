package com.hhnz.process.task.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.order.mapper.OmOrderHeadersAllMapper;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.receivable.dto.ArOverdueDTO;
import com.hhnz.receivable.mapper.ArOverdueMapper;

/**
 * Created by yang on 账期检查
 */
@Service("orderCheckPeriod")
@Transactional
public class LKAOrderCheckPeriod implements JavaDelegate {
  @Resource
  private OmOrderHeadersAllMapper  ordermapper;
  @Resource
  private CMerchCustBaseMapper merchmapper;
  @Resource
  private ArOverdueMapper arOverdueMapper;
  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {
    Long id = Long.parseLong(delegateExecution.getVariable("key").toString());// 订单号或批次号
    OmOrderHeadersAll order= this.ordermapper.selectByPrimaryKey(id);
    CMerchCustBase merch = this.merchmapper.selectByPrimaryKey(order.getMerchCustId());
    Map<String,Object> params = new HashMap<String, Object>();
    params.put("sapCustomerId", merch.getSapCustomerId());
	params.put("orgid", merch.getOrganizationId());
	params.put("begin", 0);
	params.put("end",10);
	List<ArOverdueDTO> list = this.arOverdueMapper.selectArOverdueList(params);
	if(list !=null && list.size()>0){
		ArOverdueDTO aroverdue = list.get(0);
		if(aroverdue.getAramt().compareTo(aroverdue.getOverdue1())==0){
			//应收账款金额等于没有逾期金额
			delegateExecution.setVariable("CKECK", 0); //没有超期
		}else{
			delegateExecution.setVariable("CKECK", 1); //超期
		}
	}else{
		delegateExecution.setVariable("CKECK", 0); //没有超期
	}
  }
}
