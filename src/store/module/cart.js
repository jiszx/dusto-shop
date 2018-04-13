import {
  ADD_TO_SHOPPING_CART,
  DECREMENT_ONE_FROM_SHOPPING_CART,
  EMPTY_SHOPPING_CART,
  DELETE_SHOPPING_CART_GOODS_BY_UUID,
  UPDATE_SHOPPING_CART_BY_UUID,
  ADD_PRESENT_BY_UUID,
  DELETE_PRESENT_BY_UUID,
  UPDATE_PRESENT_BY_UUID,
  UPDATE_CART_HAS_PRESENT,
  ADD_CART_ORDER_PRESENT,
  UPDATE_CART_SELECT_GOODS,
  ADD_CURRENT_ORDER_PRESENT,
  DELETE_CURRENT_ORDER_PRESENT,
  EMPTY_CURRENT_ORDER_PRESENT,
  EMPTY_CART_SELECT,
} from '../types.js'


/**
 itemObj:{
    uuid:undefined, 唯一标识
    productID:undefined, 商品id
    size:undefined, 选择的尺寸
    color:undefined, 颜色
    num:1, 数量，默认是1
    productData:{}, 商品数据
    presentList:[], 赠品，每个赠品配置一个唯一的uuid
  }
 */
const state = {
  //数据
  shoppingCart:[],
  hasSelectGoods:{
    goodsList:[],
    presentList:[],
    hasPresent:true,//默认调试开放所有订单都有赠品
    num:0,//几件商品
    price:0,//价格
  },
};

const actions = {
  // 加入商品到购物车
  [ADD_TO_SHOPPING_CART]({commit},goods){
    commit(ADD_TO_SHOPPING_CART,goods)
  },
  // 商品减一
  [DECREMENT_ONE_FROM_SHOPPING_CART]({commit,state},uuid){
    let item = state.shoppingCart.find(item=>{
      return item.uuid==uuid;
    });
    if(item.num>1){
      commit(DECREMENT_ONE_FROM_SHOPPING_CART,uuid)//减一
    }else{
      commit(DECREMENT_ONE_FROM_SHOPPING_CART,uuid)//从购物车删除此商品
    }

  },
  // 删除购物车商品
  [DELETE_SHOPPING_CART_GOODS_BY_UUID]({commit},uuid){
    commit(DELETE_SHOPPING_CART_GOODS_BY_UUID,uuid)
  },
  // 清空购物车
  [EMPTY_SHOPPING_CART]({commit}){
    commit(EMPTY_SHOPPING_CART)
  },
  // 通过uuid更新购物车商品
  [UPDATE_SHOPPING_CART_BY_UUID]({commit},uuid){
    commit(UPDATE_SHOPPING_CART_BY_UUID,uuid)
  },
  //通过uuid添加赠品
  [ADD_PRESENT_BY_UUID]({commit},{uuid,data}){
    commit(ADD_PRESENT_BY_UUID,{uuid,data})
  },
  //通过uuid更新赠品信息
  [UPDATE_PRESENT_BY_UUID]({commit},{productUuid,uuid,data}){
    commit(UPDATE_PRESENT_BY_UUID,{productUuid,uuid,data})
  },
  //添加present
  [ADD_CART_ORDER_PRESENT]({commit},data){
    commit(ADD_CART_ORDER_PRESENT,data)
  },
  //购物车选中的商品发生变化时触发
  [UPDATE_CART_SELECT_GOODS]({commit}, uuidList){
    commit(UPDATE_CART_HAS_PRESENT,uuidList)
  },
  //清空购物车整单赠品
  [EMPTY_CURRENT_ORDER_PRESENT]({commit}){
    commit(EMPTY_CURRENT_ORDER_PRESENT)
  },
  //清空购物车选中的商品
  [EMPTY_CART_SELECT]({commit}){
    commit(EMPTY_CART_SELECT);
  },
};

const getters = {
  getShoppingCartNum(state){
    return state.shoppingCart.length;
  },
};

const mutations = {
  [ADD_TO_SHOPPING_CART](state,{productData,num,color,size}){
    console.log(productData,num,color,size)
    //todo 根据商品id 颜色 和尺码判断该类型商品是否存在，若存在则num+1，不存在则新增
    let dataIndex = state.shoppingCart.findIndex(item=>{
      return item.productData.productID==productData.productID&&item.size==size&&item.color.id==color.id;
    });
    if(dataIndex>-1){//如果商品已存在
      state.shoppingCart[dataIndex].num++;
      return;
    }
    let goodsObj = {
      uuid:'dusto'+(new Date).getTime(),
      productID:productData.productID,
      size:size,
      color:color,
      num:num||1,
      productData:productData,
      presentList:[],
    };
    state.shoppingCart.push(goodsObj);
  },
  [DECREMENT_ONE_FROM_SHOPPING_CART](state,uuid) {
    let dataIndex = state.shoppingCart.findIndex(item => {
      return item.uuid == uuid;
    });
    state.shoppingCart[dataIndex].num--;
  },
  [DELETE_SHOPPING_CART_GOODS_BY_UUID](state,uuid){
    let dataIndex = state.shoppingCart.findIndex(item => {
      return item.uuid == uuid;
    });
    console.log(uuid,dataIndex)
    state.shoppingCart.splice(dataIndex,1);
  },
  [EMPTY_SHOPPING_CART](state){
    state.shoppingCart = [];
  },
  [UPDATE_SHOPPING_CART_BY_UUID](state,{uuid,productData,num,color,size}){
    let dataIndex = state.shoppingCart.findIndex(item => {
      return item.uuid == uuid;
    });
    let goodsObj = {
      uuid:uuid,
      productID:productData.productID,
      size:size,
      color:color,
      num:num||1,
      productData:productData,
      presentList:state.shoppingCart[dataIndex].presentList
    };
    state.shoppingCart[dataIndex] = goodsObj;
  },
  [ADD_PRESENT_BY_UUID](state,{uuid,data}){
    let dataIndex = state.shoppingCart.findIndex(item => {
      return item.uuid == uuid;
    });
    let presentData = {
      ...data,
      uuid:'present'+(new Date).getTime(),//为每一个赠品设置一个唯一的uuid
    };
    state.shoppingCart[dataIndex].presentList.push(presentData);
  },
  [UPDATE_PRESENT_BY_UUID](state,{productUuid,uuid,data}){
    console.log(productUuid,uuid,data)
    let dataIndex = state.shoppingCart.findIndex(item => {
      return item.uuid == productUuid;
    });
    let presentIndex = state.shoppingCart[dataIndex].presentList.findIndex(item => {
      return item.uuid == uuid;
    })

    let present = {
      uuid:uuid,
      productID:data.productData.productID,
      size:data.size,
      color:data.color,
      num:1,
      productData:data.productData
    };
    state.shoppingCart[dataIndex].presentList[presentIndex] = present;
  },
  [ADD_CART_ORDER_PRESENT](state,{data}){
    let presentData = {
      ...data,
      uuid:'order-present'+(new Date).getTime(),//为每一个赠品设置一个唯一的uuid
    };
    state.hasSelectGoods.presentList.push(presentData);
  },
  [EMPTY_CURRENT_ORDER_PRESENT](state){
    state.hasSelectGoods.presentList = [];
  },
  [EMPTY_CART_SELECT](state){
    state.hasSelectGoods={
       goodsList:[],
        presentList:[],
        hasPresent:true,//默认调试开放所有订单都有赠品
        num:0,//几件商品
        price:0,//价格
    }
  },
};

export default {
  state,
  actions,
  getters,
  mutations
}
