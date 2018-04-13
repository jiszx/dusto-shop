package com.hhnz.crm.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.hhnz.crm.mapper.TEmployeeMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TEmployeeExample;
import com.hhnz.crm.service.IEmployeeService;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.util.TestBase;

public class UserOperate extends TestBase {
  @Autowired
  private IEmployeeService service;
  @Autowired
  private TEmployeeMapper mapper;
  @Resource
  private CMerchCustBaseMapper custMapper;

  /**
   *  更新仓储/合作仓储  用户name为对应客户名
   * @throws Exception
   */
//  @Test
  public void updateUserName() throws Exception {
    TEmployeeExample ex = new TEmployeeExample();
    TEmployeeExample.Criteria exc = ex.createCriteria();
    exc.andUserTypeEqualTo("1");
    List<TEmployee> users = mapper.selectByExample(ex);
    for (TEmployee user : users) {
      CMerchCustBase merch = custMapper.selectByPrimaryKey(user.getMerchId());
      if (merch != null && merch.getName() != null) {
        user.setName(merch.getName());
        service.updateEmployee(user);
      }
    }

  }
}
