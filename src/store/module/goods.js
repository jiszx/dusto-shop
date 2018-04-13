import * as types from '../types.js'

const state = {
  //购物车数据 目前一层循环
  cardGoods:[]
}

const actions = {

};

const getters = {

};

const mutations = {
	pushCardGoods(state,item){
		state.cardGoods.push(item);
	},
	removeCardGoods(state,item){
		state.cardGoods.splice(item);
	}
}

export default {
  state,
  actions,
  getters,
  mutations
}
