package com.hhnz.customer.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.util.ApplicationContextUtil;

/**
 * <h1>客户基础信息后台校验类</h1>
 * <p>
 * 此类处理非单字段条件校验，单条属性的校验可直接由</br>
 * {@code Hibernate Validator specific constraints.}完成
 * </p>
 * @author: chaoyang.ren
 * @date:2016年8月22日
 * @time:下午2:37:56
 * @email:chaoyang.ren@foxmail.com
 */
public class CustBaseValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CMerchCustBase.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//当前不存在错误时校验以下信息，否则不处理以下信息，校验器会直接获取到当前的错误
		if(!errors.hasErrors()){
			CMerchCustBase custBase = (CMerchCustBase)obj;
			//空值判断
			/*if(custBase.getDistributions() != null && custBase.getDistributions().size() > 0){
				for (CMerchCustDistribution custDis : custBase.getDistributions()) {
					if(StringUtils.isBlank(custDis.getLogistics())){
						errors.rejectValue("distributions", null, "物流公司不能为空!");
					}
					if(StringUtils.isBlank(custDis.getName())){
						errors.rejectValue("distributions", null, "送达方名称不能为空!");
					}
					if(StringUtils.isBlank(custDis.getSite())){
						errors.rejectValue("distributions", null, "到站信息不能为空!");
					}
				}
			}*///此三项信息不再处理校验@20161213
			//字段长度判断-done with bean annotation.
			//业务逻辑判断
			ICustomerService customerService = ApplicationContextUtil.getBean("customerServiceImpl");
			if(StringUtils.isNotBlank(custBase.getBusinessLicense())
					&& customerService.isLicenseNoExisted(custBase.getBusinessLicense(), custBase.getId())){
				errors.rejectValue("businessLicense", null, "营业执照号已存在!");
			}
		}
    }

}
