//引入api
import {
  getProductrecommendlistDataByKeyword,
  getProductSearchData
} from 'api/productrs'
//导入types
import {
  GET_NEW_PRODUCT_BRUST,
  SET_NEW_PRODUCT_BRUST,
  GET_PRODUCT_LIST,
} from '../types'


let keywords={
  newProductBurst:"男士唇膏",
};
let goodsMockData = [{
  liked:true,
  image:require('assets/images/goods/banner-zx-6.png'),
  title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
  price:99.00,
  old_price:199.00
},{
  liked:true,
  image:require('assets/images/goods/banner-zx-6.png'),
  title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
  price:99.00,
  old_price:199.00
},{
  liked:false,
  image:require('assets/images/goods/banner-zx-6.png'),
  title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
  price:99.00,
  old_price:199.00
},{
  liked:false,
  image:require('assets/images/goods/banner-zx-6.png'),
  title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
  price:99.00,
  old_price:199.00
},{
  liked:false,
  image:require('assets/images/goods/banner-zx-6.png'),
  title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
  price:99.00,
  old_price:199.00
},{
  liked:false,
  image:require('assets/images/goods/banner-zx-6.png'),
  title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
  price:99.00,
  old_price:199.00
}]


const state = {
  newProductBurst:[],//新品爆款列表
  productSearchResult:[],//搜索结果列表
  productList:{
    hasMore:true,
    loading:false,
    data:[],
  },
  productDetail:{},
};

const actions = {
  //获取新品爆款数据
  [GET_NEW_PRODUCT_BRUST]({commit}) {
    getProductrecommendlistDataByKeyword(keywords.newProductBurst)
      .then(res => {
        commit(SET_NEW_PRODUCT_BRUST,res.response_data)
      })
      .catch(err => {

      })
  },
  //获取商品列表
  [GET_PRODUCT_LIST]({commit,state},params){
    state.productList.loading=true;
    getProductSearchData(params)
      .then(res=>{
        state.productList.loading=false;
        commit(GET_PRODUCT_LIST,res)
      })
      .catch(err=>{
        state.productList.loading=false;
      })
  }
};

const getters = {

};

const mutations = {
  [SET_NEW_PRODUCT_BRUST](state,data){
    state.newProductBurst = data||[];
  },
  [GET_PRODUCT_LIST](state,data){
    console.log(data)
    state.productList.hasMore = data.totalRecord>data.pageSize*data.currentPage;
    state.productList.data=data.response.data.product;
  }
};

export default {
  state,
  actions,
  getters,
  mutations
}
