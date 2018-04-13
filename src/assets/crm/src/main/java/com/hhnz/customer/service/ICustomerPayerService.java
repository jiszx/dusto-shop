package com.hhnz.customer.service;

import java.util.List;

import com.hhnz.customer.model.CMerchPayer;

public interface ICustomerPayerService {

  List<CMerchPayer> getPayer(Long merchid);

  boolean addOrUpdatePayer(CMerchPayer merchPayer);

  boolean deletePayer(CMerchPayer merchPayer);

  boolean deleteByMerch(Long merchid);

}
