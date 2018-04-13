package com.hhnz.jco.business.customer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.RFCCallback;


/**
 * 默认客户rfc处理参数
 */
@Component("DefaultCustomerRFCCallback")
public class DefaultCustomerRFCCallback implements RFCCallback{
	private static final Log LOG = LogFactory.getLog(DefaultCustomerRFCCallback.class);
	
	@Override
	public void errorCallBack(CallbackParam result) {
		LOG.warn("CustomerSendSAP RFC execute failed!");
	}

	@Override
	public void successCallBack(CallbackParam result) {
		LOG.info("CustomerSendSAP RFC execute success!");
	}
}
