//引入api
import {
  getShopList,
  getShopDetail
} from 'api/shop'
//导入types
import {
  GET_SHOP_LIST,
  GET_SHOP_DETAIL,
  UPDATE_SHOP_LIST,
} from '../types'

const state = {
  //数据
  shopList:[],
  //门店详情查询
  shopDetail:{
    saleTime:'',
    phone:'',
  },
};

const actions = {
  [GET_SHOP_LIST]({commit},data) {
    getShopList(data)
      .then(res => {
        commit(UPDATE_SHOP_LIST,res.response_data)
      })
      .catch(err => {

      })
  },
  //门店详情查询
  [GET_SHOP_DETAIL]({commit,state},params) {
    getShopDetail(state)
      .then(res => {
        state.shopDetail = res.response_data;
      })
      .catch(err => {

      })
  },
};

const getters = {
  /**
   * 通过shopNo获取该商店信息，如果shopList没有返回空
   * @param state
   * @returns {function(*)}
   */
  getShopDataById:state=>{
    return shopNo=>{
      for(let i = 0, len = state.shopList.length; i < len; i++){
        if(state.shopList[i].shopNo==shopNo){
          return state.shopList[i];
        }
      }
      return null;
    }
  }
};

const mutations = {
  [UPDATE_SHOP_LIST](state,data){
    data.map(iterm=>{
      iterm.distance = parseFloat(iterm.distance).toFixed(2)+'km'
    })
    state.shopList = data;
  }
};

export default {
  state,
  actions,
  getters,
  mutations
}
