//引入router用于路由跳转
import router from 'router/index';
//引入api
import {
  getVerificationCodeSendlData,
} from 'api/memberRs'
//导入types
import {
  GET_VERIFICATION_CODE,
} from '../types'
const state = {

};

const actions = {
  //手机获取验证码
  [GET_VERIFICATION_CODE]({commit,state }, params){
    getVerificationCodeSendlData(params)
      .then(res=>{
        commit('showMsgTips','获取验证码成功',{root:true})
      })
      .catch(err=>{
        commit('showMsgTips','获取验证码失败')
      })
  },
};



const getters = {
};

const mutations = {

};

export default {
  state,
  actions,
  getters,
  mutations
}
