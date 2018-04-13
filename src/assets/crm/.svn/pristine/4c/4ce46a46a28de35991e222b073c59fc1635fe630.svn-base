package com.hhnz.customer.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.base.Verify;
import com.hhnz.customer.mapper.CMerchPayerMapper;
import com.hhnz.customer.model.CMerchPayer;
import com.hhnz.customer.model.CMerchPayerExample;
import com.hhnz.customer.model.CustomerPayerDetail;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Params;

@Service
public class CustomerPayerService {
  @Resource
  private CMerchPayerMapper merchPayerMapper;

  public AjaxDTO list(AjaxDTO page, CustomerPayerDetail model, List<Long> stations) {
    Map<String, ?> param = Params.builder().add("begin", page.getOffset())
        .add("end", page.getOffset() + page.getLimit()).add("merch", model.getMerchName())
        .add("merchsap", model.getMerchSap()).add("station", stations).buid();
    List<CustomerPayerDetail> list = merchPayerMapper.selectPayerDetail(param);
    int count = merchPayerMapper.countPayerdetail(param);
    page.setRows(list);
    page.setTotal(count);
    return page;
  }

  public List<CMerchPayer> getPayer(Long merchid) {
    Objects.requireNonNull(merchid);

    CMerchPayer self = new CMerchPayer();
    self.setMerchid(merchid);
    self.setPayer(merchid);

    CMerchPayerExample exam = new CMerchPayerExample();
    exam.createCriteria().andMerchidEqualTo(merchid);
    List<CMerchPayer> payers = merchPayerMapper.selectByExample(exam);
    payers.add(0, self);
    return payers;
  }

  public boolean addOrUpdatePayer(CMerchPayer merchPayer) {
    Objects.requireNonNull(merchPayer);
    Objects.requireNonNull(merchPayer.getMerchid());
    Objects.requireNonNull(merchPayer.getPayer());
    Verify.verify(!merchPayer.getMerchid().equals(merchPayer.getPayer()), "关联的付款方不能为自己%s",
        merchPayer.getMerchid());

    deletePayer(merchPayer);
    return merchPayerMapper.insert(merchPayer) == 1;
  }

  public boolean deletePayer(CMerchPayer merchPayer) {
    Objects.requireNonNull(merchPayer);
    Objects.requireNonNull(merchPayer.getMerchid());
    Objects.requireNonNull(merchPayer.getPayer());

    CMerchPayerExample exam = new CMerchPayerExample();
    exam.createCriteria().andMerchidEqualTo(merchPayer.getMerchid())
        .andPayerEqualTo(merchPayer.getPayer());
    return merchPayerMapper.deleteByExample(exam) == 1;
  }

  public boolean deleteByMerch(Long merchid) {
    Objects.requireNonNull(merchid);

    CMerchPayerExample exam = new CMerchPayerExample();
    exam.createCriteria().andMerchidEqualTo(merchid);
    return merchPayerMapper.deleteByExample(exam) > 0;
  }

}
