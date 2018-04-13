import {
  GET_MSG_LIST,
  GET_MESSAGA_DETAIL,
} from '../types.js'

//资讯列表/详情查询 WX-C-28/29
import {
  getMsgListData,
  getMsgDetailData,
} from 'api/information'

const state = {
  //资讯列表查询
  msgListData:[
    {
      infoId:'',
      infoName:'',
      infoImg:'',
      createDate:'',
    }
  ],
  //资讯详情查询
  msgDetailData:{
	  infoId:'',
	  infoName:'',
	  infoContent:'',
	  createDate:'',
  }
}

const actions = {
  //资讯列表查询
  [GET_MSG_LIST]({commit,state}, params){
    getMsgListData(params)
      .then(res=>{
        console.log(res);
        state.msgListData = res.response_data;
      })
      .catch(err=>{

      })
  },
  //资讯详情查询
  [GET_MESSAGA_DETAIL]({commit,state}, params){
    getMsgDetailData(params)
      .then(res=>{
        console.log(res);
        state.msgDetailData = res.response_data;
      })
      .catch(err=>{

      })
  },
};


const getters = {}

const mutations = {}

export default {
  state,
  actions,
  getters,
  mutations
}
