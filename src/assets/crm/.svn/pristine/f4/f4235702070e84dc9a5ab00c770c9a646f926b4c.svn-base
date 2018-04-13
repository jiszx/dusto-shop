package com.hhnz.jco.business.customer;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.CommonResult;
import com.hhnz.jco.business.base.RFCCallback;


/**
 * 临时的客户rfc处理参数
 */
@Component("TempCustomerRFCCallback")
public class TempCustomerRFCCallback implements RFCCallback{
	private static final Log LOG = LogFactory.getLog(TempCustomerRFCCallback.class);
	@Resource
    private CMerchCustBaseMapper merchCustBaseMapper;
	@Override
	public void errorCallBack(CallbackParam result) {
		CommonResult commonResult = result.getResult();
		if(commonResult != null){
			String message = commonResult.getMESSAGE();
			String last10 = null;
			if(StringUtils.isNotBlank(message)){
				if(message.length() > 10){
					last10 = message.substring(message.length()-10, message.length());
				}else{
					last10 = message;
				}
			}
			if(StringUtils.isNumeric(last10)){
				CMerchCustBase merchCustBase = merchCustBaseMapper.selectByPrimaryKey(result.getId());
				merchCustBase.setSapCustomerId(RFCConstants.X_FLAG+last10);
				merchCustBaseMapper.updateByPrimaryKeySelective(merchCustBase);
			}
		}
		LOG.warn("CustomerSendSAP RFC execute failed!");
	}

	@Override
	public void successCallBack(CallbackParam result) {
		LOG.info("CustomerSendSAP RFC execute success!");
	}
}
