//引入router用于路由跳转
import router from 'router/index';
//引入api
import {
  addGuiderEvaluate,
  getGuiderInfo
} from 'api/mine'
//导入types
import {
  GUIDER_SUBMIT_EVALUATE,
  GUIDER_GET_INFO,
  ADDRESS_ADD,
  ADDRESS_UPDATE_BY_ID,
  ADDRESS_DEL_BY_ID,
  ADDRESS_SET_DEFAULT,
  ADDRESS_GET_DATA,
} from '../types'
const state = {
  //我的专属导购
  shoppingGuide:{
    salesImg:'',
    selesName:'',
    grade:0,
    shopName:'',
    phone:'',
    workTime:'',
  },
  // 我的地址
  address:[],
  //地址列表
  areaInfo:[],
};

const actions = {
  //获取我的专属导购信息
  [GUIDER_GET_INFO]({commit,state }, params){
    getGuiderInfo(params)
      .then(res=>{
        commit(GUIDER_GET_INFO,res.response_data);
      })
      .catch(err=>{

      })
  },
  //提交我的专属导购评价
  [GUIDER_SUBMIT_EVALUATE]({commit,state }, params){
    addGuiderEvaluate(params)
      .then(res=>{
        console.log(res);
        commit('showMsgTips','提交成功',{root:true})
        router.push({name:"ShoppingGuide"});
      })
      .catch(err=>{

      })
  },
  //获取我的地址信息
  [ADDRESS_GET_DATA]({commit},params){
    //todo 待完善
    //模拟数据
    let data = [{
      id:1,
      name: "陈先生",
      phone:"15885254454",
      area:"浙江省 温州市 瑞安市",
      areaDetail:"塘厦镇 罗凤北工业区 大东鞋业开发部",
      address:"浙江省 温州市 瑞安市 塘厦镇 罗凤北工业区 大东鞋业开发部",
      isDefault:true,
    },
      {
        id:2,
        name: "王女士",
        phone:"189524325421",
        area:"浙江省 温州市 瑞安市",
        areaDetail:"塘厦镇 罗凤北工业区 大东鞋业开发部",
        address:"浙江省 温州市 瑞安市 塘厦镇 罗凤北工业区 大东鞋业人资部",
        isDefault:false,
      }]
    commit(ADDRESS_ADD,data)
  },
  //删除地址
  [ADDRESS_DEL_BY_ID]({commit},id){
    //todo 待完善接口请求
    commit(ADDRESS_DEL_BY_ID,id)
  },
  [ADDRESS_SET_DEFAULT]({commit},id){
    //todo 待完善接口请求
    commit(ADDRESS_SET_DEFAULT,id)
  },
  [ADDRESS_UPDATE_BY_ID]({commit},{id,address}){
    //todo 待完善接口请求
    commit(ADDRESS_UPDATE_BY_ID,{id,address})
  },
  [ADDRESS_ADD]({commit},address){
    commit(ADDRESS_ADD,address)
  }
};

const getters = {
  //通过地址id获取地址详情
  getAddressById(state){
    return id=>{
      let address = state.address.find(v=>{
        return v.id == id;
      });
      return address;
    }
  },
};

const mutations = {
  //获取更新我的专属导购信息
  [GUIDER_GET_INFO](state,data){
    for (let key in data){
      state.shoppingGuide[key] = data[key];
    }
  },
  //添加地址
  [ADDRESS_ADD](state,data){
    if(Array.isArray(data)){
      state.address=[].concat(data);
    }else{
      console.log(123,state.address,data);
      data.address = data.area+data.areaDetail;
      data.id= +new Date()
      state.address.push(data)
    }
  },
  //通过id修改我的地址信息
  [ADDRESS_UPDATE_BY_ID](state,{id,address}){
    let index = state.address.findIndex(v=>{
      return v.id == id;
    });
    for (let key in address){
      state.address[index][key] = address[key]
    }
    state.address[index].address = address.area+address.areaDetail;
  },
  //设置默认地址
  [ADDRESS_SET_DEFAULT](state,id){
    state.address.forEach(item=>{
      if(item.id==id){
        item.isDefault=true;
      }else{
        item.isDefault=false;
      }
    });
  },
  //删除地址
  [ADDRESS_DEL_BY_ID](state, id){
    let index = state.address.findIndex(v=>{
      return v.id == id;
    });
    state.address.splice(index,1);
  }
};

export default {
  state,
  actions,
  getters,
  mutations
}
