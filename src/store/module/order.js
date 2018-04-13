//引入router用于路由跳转
import router from 'router/index';
//引入api
import {
  getOrderList,
  getOrderDetail,
} from 'api/order'
//导入types
import {
  ORDER_GET_LIST,
  ORDER_UPDATE_LIST,
  ORDER_GET_DETAIL,
  ORDER_UPDATE_DETAIL,
  ADD_SUBMITING_ORDER,
  UPDATE_SUBMITING_ORDER_ADDRESS,
  UPADTE_SUBMITING_ORDER_COUPON,
  UPDATE_SUBMITING_ORDER_JIFEN,
  UPDATE_SUBNITING_ORDER_ACTIVITY,
  GET_CUSTOMER_SERVICE,
} from '../types'
const state = {
  //我的订单列表
  myOrderList:[],
  currentOrderDetail:{

  },
  submitingOrder:[],//正在完善信息的待提交订单，每个订单配置一个唯一的uuid，再次进入页面根据uuid获取订单详情
  customerServiceList:[],
};

//todo 假数据待删除
//将列表分类存入vuex中
let mockData = [
  {
    orderNo:1,
    orderStatus:'0120',
    orderType:1,
    orderTime:new Date(),
    productTotalCount:1,
    totalPayAmount:100,
    discountTotal:10.99,
    totalPromo:10,
    discountTransport:6,
    pointDiscountAmount:8,
    productList:[{
      productImg:'http://www.ccnovel.com/e/data/tmp/titlepic/584bfdb4b89eb0b81a5fa0775364d152.jpg',
      productName:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
      buyTime:new Date(),
      color:'黑色',
      size:37,
      price:100,
      buyPrice:199,
      count:1,
      skuNo:'123',
    }],
  }
];

const actions = {
  //获取我的订单列表
  [ORDER_GET_LIST]({commit}, params){
    getOrderList(params)
      .then(res=>{
        commit(ORDER_UPDATE_LIST,res.response_data);
      })
      .catch(err=>{

      })
  },
  //获取订单详情
  [ORDER_GET_DETAIL]({commit}, params){
    getOrderDetail(params)
      .then(res=>{
        commit(ORDER_UPDATE_DETAIL,res.response_data);
      })
      .catch(err=>{
        console.log(err)
      })
  },
  //添加一个待提交订单
  [ADD_SUBMITING_ORDER]({commit},data){
    commit(ADD_SUBMITING_ORDER,data);
  },
  //修改待提交订单地址
  [UPDATE_SUBMITING_ORDER_ADDRESS]({commit},{uuid,address}){
    commit(UPDATE_SUBMITING_ORDER_ADDRESS,{uuid,address});
  },
  //修改待提交订单所选商城活动
  [UPDATE_SUBNITING_ORDER_ACTIVITY]({commit},{uuid,data}){
    commit(UPDATE_SUBNITING_ORDER_ACTIVITY,{uuid,data});
  },
  //修改待提交订单所选优惠券
  [UPADTE_SUBMITING_ORDER_COUPON]({commit},{uuid,data}){
    commit(UPADTE_SUBMITING_ORDER_COUPON,{uuid,data});
  },
  //修改待提交订单所选积分
  [UPDATE_SUBMITING_ORDER_JIFEN]({commit},{uuid,data}){
    commit(UPDATE_SUBMITING_ORDER_JIFEN,{uuid,data});
  },

  //获取售后信息列表
  [GET_CUSTOMER_SERVICE]({commit}){
    //todo 待实现接口请求
    commit(GET_CUSTOMER_SERVICE);
  }
};

const getters = {
  /**
   * getter orderDetail
   * @param orderId
   */
  getOrderDetailById(state){
    return (orderId)=>{
      return state.currentOrderDetail[orderId+'']
    }
  },
  /**
   * 通过uuid获取当前待提交订单信息
   *
   */
  getSubmitingOrderDataByUuid(state){
    return (uuid)=>{
     var index = state.submitingOrder.findIndex(v=>{
       return v.uuid ==uuid;
     })
      if(index>-1){
       return state.submitingOrder[index]
      }
      return {};
    }
  },
};

const mutations = {
  /**
   * 更新设置订单列表
   * @param state
   */
  [ORDER_UPDATE_LIST](state,list) {
    // state.myOrderList = list||[];
    state.myOrderList = mockData//模拟数据调试
  },
  [ORDER_UPDATE_DETAIL](state,detailObj){
    console.log(detailObj);
    state.currentOrderDetail[detailObj.orderNo] = detailObj;
  },
  //添加一个待提交订单
  [ADD_SUBMITING_ORDER](state,orderData){
    state.submitingOrder.push(orderData)
  },
  //修改待提交订单地址
  [UPDATE_SUBMITING_ORDER_ADDRESS](state,{uuid,address}){
    let index = state.submitingOrder.findIndex(v=>{
      return v.uuid == uuid;
    })
    if(index>-1){
      state.submitingOrder[index].address = address;
    }
  },
  //修改待提交订单所选商城活动
  [UPDATE_SUBNITING_ORDER_ACTIVITY](state,{uuid,data}){

  },
  //修改待提交订单优惠券
  [UPADTE_SUBMITING_ORDER_COUPON](state,{uuid,data}){

  },
  //修改待提交订单积分
  [UPDATE_SUBMITING_ORDER_JIFEN](state,{uuid,data}){

  },

  //
  [GET_CUSTOMER_SERVICE](state){
    state.customerServiceList = mockData//模拟数据调试
  },

};

export default {
  state,
  actions,
  getters,
  mutations
}
