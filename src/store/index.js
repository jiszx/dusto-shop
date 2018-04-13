import Vue from 'vue'
import Vuex from 'vuex'
/**
 * 全局状态管理
 */
import state from './state'
import actions from './actions'
import mutations from './mutations'
import getters from './getters'

/**
 * 分模块状态管理
 */
import home from './module/home'
import message from './module/message'
import auth from './module/auth'
import shop from './module/shop'
import memberRs from './module/memberRs'
import mine from './module/mine'
import order from './module/order'
import products from './module/products'
import information from './module/information'
import cart from './module/cart'
Vue.use(Vuex);

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations,
  modules: {
    home,
    message,
    auth,
    shop,
    mine,
    memberRs,
    order,
    products,
    cart,
    information,
  }
});
