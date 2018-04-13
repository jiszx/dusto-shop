package com.hhnz.crm.task;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhnz.crm.mapper.TEmployeeMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TEmployeeExample;
import com.hhnz.crm.service.IEmployeeService;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustBaseExample;
import com.hhnz.customer.model.CMerchCustBaseExample.Criteria;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.util.TestBase;
import com.hhnz.util.io.excel.util.Excel2007Util;

public class MerchOperate extends TestBase {
  private static Logger logger = LoggerFactory.getLogger(MerchOperate.class);

  @Autowired
  private CMerchCustBaseMapper mapper;
  @Autowired
  private ICustomerService custService;
  @Autowired
  private TEmployeeMapper employeeMapper;
  @Autowired
  private IEmployeeService employeeService;

  /**
   * 生成仓储/合作仓储 正式客户的code
   */
//  @Test
  public void generateLoginCode() {
    CMerchCustBaseExample ex = new CMerchCustBaseExample();
    Criteria criteria = ex.createCriteria();
    criteria.andCustTypeIn(Arrays.asList("7", "70"));
    criteria.andStatesEqualTo("3");
    List<CMerchCustBase> merchs = mapper.selectByExample(ex);
    int count = 0;
    for (CMerchCustBase merch : merchs) {
      if (StringUtils.isEmpty(merch.getCode())) {
        merch.setCode(merch.genCode());
        custService.save(merch);
        count++;
      }
    }
    logger.info("生成登陆码{}个", count);
  }

  /**
   * 创建仓储/合作仓储 正式客户code对应的登陆账号
   * 
   * @throws Exception
   */
//  @Test
  public void generateUser() throws Exception {
    CMerchCustBaseExample ex = new CMerchCustBaseExample();
    Criteria criteria = ex.createCriteria();
    criteria.andCustTypeIn(Arrays.asList("7", "70"));
    criteria.andStatesEqualTo("3");
    List<CMerchCustBase> merchs = mapper.selectByExample(ex);
    int count = 0;
    for (CMerchCustBase merch : merchs) {
      if (StringUtils.isNotEmpty(merch.getCode())) {
        TEmployeeExample tex = new TEmployeeExample();
        TEmployeeExample.Criteria tc = tex.createCriteria();
        tc.andLoginNameEqualTo(merch.getCode());
        List<TEmployee> users = employeeMapper.selectByExample(tex);
        if (users.isEmpty()) {
          employeeService.generateUser(merch, merch.getCode());
          count++;
        }
      }
    }
    logger.info("生成默认用户完成，总共{}客户，生成用户{}个", merchs.size(), count);
  }

  /**
   * 根据表格更新送达方联系人及联系电话 2017/02/28
   */
  public void updateMerch() {
    String[][] text = Excel2007Util.read("E:/file.xlsx", 0);
    int startRow = 0;
    int startCol = 0;
    loop: for (int i = 0; i < text.length; i++) {
      for (int j = 0; j < text[i].length; j++) {
        if (StringUtils.isNotEmpty(text[i][j])) {
          startRow = i + 1;
          startCol = j;
          break loop;
        }
      }
    }

    int count = 0;
    for (int i = startRow; i < text.length; i++) {
      CMerchCustBase merch = custService.findCustBaseById(Long.parseLong(text[i][startCol]));
//      if (merch != null) {
//        merch.setContactName(text[i][startCol + 5]);
//        merch.setContactTel(text[i][startCol + 6]);
//        custService.save(merch);
//        count++;
//      }
    }
    logger.info("更新客户资料{}条", count);
  }

}
