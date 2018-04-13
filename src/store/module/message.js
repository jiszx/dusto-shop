import * as types from '../types.js'

const state = {
  //滚动页面数据
  swipperData: [
    {imgURL: "s", targetID: "1"},
    {imgURL: "s", targetID: "2"},
    {imgURL: "s", targetID: "3"},
    {imgURL: "s", targetID: "4"},
    {imgURL: "s", targetID: "5"},
    {imgURL: "s", targetID: "6"}
  ],
  //分类数据
  categoryData: [
    [
      {imgURL: "s", title: "优惠促销", display: 2, typeID: 1},
      {imgURL: "s", title: "潮流专家", display: 1, typeID: 2}
    ],
    [
      {imgURL: "s", title: "新品/爆品", display: 1, typeID: 3},
      {imgURL: "s", title: "鞋专家", display: 1, typeID: 4},
      {imgURL: "s", title: "品牌咨询", display: 1, typeID: 5}
    ]
  ],
  msgListData: [
    {id: 1, title: "秋冬换新我的美腿修炼手册", pulishTS: "2018年12与12日", imgURL: "x"},
    {id: 2, title: "国庆遇到中秋，17年最后一个大大长假", pulishTS: "2018年12与12日", imgURL: "x"},
    {id: 3, title: "潮流女孩VS酷女孩！做真实的自己就对了", pulishTS: "2018年12与12日", imgURL: "x"},
    {id: 4, title: "时尚不等待新东上行ing~", pulishTS: "2018年12与12日", imgURL: "x"},
    {id: 5, title: "周五商城放肆新", pulishTS: "2018年12与12日", imgURL: "x"}
  ]
}

const actions = {}

const getters = {}

const mutations = {}

export default {
  state,
  actions,
  getters,
  mutations
}
