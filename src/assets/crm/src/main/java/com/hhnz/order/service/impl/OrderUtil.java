package com.hhnz.order.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.hhnz.crm.model.TEmployee;

public class OrderUtil {
  
  private OrderUtil(){}
  
  public static String getOrderTypeByUser(TEmployee user){
    if(user==null || StringUtils.isEmpty(user.getUserType())){
      return "0";
    }
    return user.getUserType();
  }

}
