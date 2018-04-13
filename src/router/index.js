import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/home/index/Index'
import Search from '@/pages/home/Search'
import SearchResult from '@/pages/home/SearchResult'
import Auth from "@/pages/author"
import GoodsList from '@/pages/goods/GoodsList' // 商品列表
import PresentList from '@/pages/goods/PresentList' // 赠品列表

import ServiceType from'@/pages/order/ServiceType'//选择服务类型
import Contact from'@/pages/home/Contact'//联系客服

Vue.use(Router)

import message from "./message"
import loginRegister from './loginRegister'
import shop from './shop'
import mine from './mine'
import order from './order'
import commodity from './commodity'

export default new Router({
  routes: [
    ...message,//资讯路由
    ...loginRegister,//登录注册路由
    ...shop,
    ...mine, //我的路由
    ...order, //我的订单
    ...commodity, //商品详情
    /****** 首页 ******/
    {
      path: '/',
      name: 'Home',
      component: Home,
      meta: {
        title: "大东鞋业"
      }
    },
    {
      path: "/auth",
      component: Auth
    },
    {
      path: "/home/Search",
      name: "Search",
      component: Search,
      meta: {
          title: '搜索'
      }
    },
    {
      path: "/home/SearchResult",
      name: "SearchResult",
      component: SearchResult,
      meta: {
          title: '搜索'
      }
    },
    /**
     * 商品列表
     */
    {
      path: "/goodsList",
      name: "GoodsList",
      component: GoodsList,
      meta: {
          title: '商品列表',
        showQuickBar:true,
      }
    },
    /**
     * 赠品列表
     */
    {
      path: "/presentList",
      name: "PresentList",
      component: PresentList,
      meta: {
        title: '挑选赠品',
        showQuickBar:true,
      }
    },
    /**
     * 待评价
     */
    {
      path: "/serviceType",
      name: "ServiceType",
      component: ServiceType,
      meta: {
          title: '选择售后服务'
      }
    },
    /**
     * 联系客服
     */
    {
      path: "/contact",
      name: "Contact",
      component: Contact,
      meta: {
        title: '联系客服'
      }
    }
  ]
})
