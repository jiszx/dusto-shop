import {
  GET_LOGIN_STATE,
  SET_WEIXIN_TOKEN,
  SET_HASBOUND,
  GET_MY_MESSAGE,
  GET_ADVERTISEMENT_INFO,
} from '../types.js'

import {setLocalStorage} from 'assets/js/mUtils';
//引入api

//会员信息查询 WX-C-01
import {
  getMyMessageData
} from 'api/member'
//广告信息查询 WX-C-03
import {
  getAdvertisementinfoData
} from 'api/promors'


const state = {
  //数据
  logined: false,
  hasBound: false,
  weixinToken: '',
  //我的会员信息查询
  memberMsg:{
    memberImg:'',
    memberName:'',
    sex:"",
    grade:'',
    point:"",
    phone:'',
    size:'',
    qparam:'',
    bparam:"",
    cparam:'',
    birthday:"",
    memberNo:'',
    qrCodeUrl:'',
  },
  //广告信息查询
  advertisementinfo:[
    {
      memberImg:"",
      grade:"",
      memberNo:"",
    }
  ],

};

const actions = {
  login({commit}, param) {//登录操作
    commit(GET_LOGIN_STATE, true)
  },
  //设置微信token
  [SET_WEIXIN_TOKEN]({commit},token){
    commit(SET_WEIXIN_TOKEN,token);
  },
  //设置hasBound
  [SET_HASBOUND]({commit},hasBound){
    commit(SET_HASBOUND,!!hasBound)
  },
  //获取我的会员信息查询
  [GET_MY_MESSAGE]({commit,state }, params){
    getMyMessageData(params)
      .then(res=>{
        for (let key in res.response_data){
          state.memberMsg[key] = res.response_data[key];
        }
      })
      .catch(err=>{

      })
  },
  //广告信息查询
  [GET_ADVERTISEMENT_INFO]({commit,state }, params){
    getAdvertisementinfoData(params)
      .then(res=>{
        state.advertisementinfo = res.response_data;
      })
      .catch(err=>{

      })
  },
};

const getters = {
  hasLogin(state){
    return state.logined;
  },
};

const mutations = {
  [GET_LOGIN_STATE](state, res) {
    state.logined = true;
  },
  //更新是否绑定手机状态hasBound
  [SET_HASBOUND](state,hasBound){
    console.log(12,hasBound)
    setLocalStorage("hasBound", hasBound);
    state.hasBound = hasBound;
  },
  [SET_WEIXIN_TOKEN](state,token){
    setLocalStorage("weixinToken", token);
    state.weixinToken = token;
  }
};

export default {
  state,
  actions,
  getters,
  mutations
}
