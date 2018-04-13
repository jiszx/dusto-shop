/**
 *  用于根据订单状态编号返回当前订单typy值
 * */

// * 1: 买家已付款
// * 2: 待评价
// * 3：等待买家付款
// * 4：等待买家付款 - 使用积分兑换
// * 5: 提交等待商家处理
// * 6: 退换货处理结果-审核通过-选择换货渠道
// * 7： 退换货处理结果-审核不通过
// * 8: 退货成功
// * 9： 退货详情-在线退货详情-退货物流信息
// * 10：退换货处理结果 - 大东已发货    或 待收货
// * 11：线下退换货处理情况 - 大东已同意退款 您需要退货
var typeVsCode = {
  1:['0150'],
  2:[],
  3:['0120'],
  4:[],
  5:[],
  6:[],
  8:[],
  9:[],
  10:['0130'],
  11:[],
};

export default {
  methods:{
    /**
     * 该方法用于将orderStateCode转换成订单详情页面需要的type值
     * @param orderStateCode
     */
    _getOrderType(orderStateCode){
      for(let key in typeVsCode){
        if(typeVsCode[key].includes(orderStateCode)){
          return key;
        }
      }
      return false;
    },
  },
}
