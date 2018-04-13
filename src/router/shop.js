import ShopList from '@/pages/shop/ShopList'
import ShopDetail from '@/pages/shop/ShopDetail'

export default [
  /**
   * 店铺模块
   */
  {
    path: "/shop",
    name: "ShopList",
    component: ShopList,
    meta: {
      title: "店铺列表",
    }
  }, {
    path: "/shop/detail",
    name: "shopDetail",
    component: ShopDetail,
    meta: {
      title: "门店详情",
    }
  },

]
