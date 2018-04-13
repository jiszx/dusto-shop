package com.hhnz.order.dto;

import java.util.List;

import com.hhnz.order.model.OmOrderSpilts;

public class OrderMakesureDto {
  private List<OmOrderSpilts> orders;

  public List<OmOrderSpilts> getOrders() {
    return orders;
  }

  public void setOrders(List<OmOrderSpilts> orders) {
    this.orders = orders;
  }
  
}
