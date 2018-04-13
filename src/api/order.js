import fetch from 'assets/js/fetch';

/**
 * 获取全部订单列表 wx-c-08
 */
export const getOrderList = (data)=> {
  return fetch.post('/interf-rf/orderRs/WxOrderList',{
    request_data:{
      memberNo:data.memberNo
    }
  })
};
/**
 * 获取订单详情 wx-c-10
 */
export const getOrderDetail = (data)=> {
  return fetch.post('/interf-rf/orderRs/WxOrderDetails',{
    request_data:{
      orderNo:data.orderNo
    }
  })
};

/**
 * 查看物流信息 wx-c-11
 */
export const getWmsHistoryInfo = (data)=> {
  return fetch.post('/interf-rf/orderRs/wmsHistoryInfo',{
    request_data:{
      orderNo:data.orderNo
    }
  })
};

/**
 * 获取订单售后信息列表
 */
export const getCustomerServiceList = ()=>{

};
