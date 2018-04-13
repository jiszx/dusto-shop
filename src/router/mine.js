import Mine from '@/pages/mine/MineHome'//我的
import MineCenter from '@/pages/mine/MineCenter'//会员中心
import MineQR from '@/pages/mine/MineQR'//我的二维码
import MyProfile from '@/pages/mine/MyProfile'//我的信息
import MyFoots from '@/pages/mine/MyFoots'//我的足型信息
import MyFootsOperation from '@/pages/mine/MyFootsOperation'//我的足型信息新增编辑
import MyAddress from '@/pages/mine/MyAddress' // 我的地址列表
import AddAddress from '@/pages/mine/AddAddress' // 我的-新增收货地址

import Cart from '@/pages/cart/CartList' // 购物车列表
import MyMsg from '@/pages/mine/MyMsg' // 我的-消息列表
import WishList from '@/pages/mine/WishList' // 我的心愿清单
import ShoppingGuide from '@/pages/mine/ShoppingGuide' // 我的专属导购
import GuideEvaluate from '@/pages/mine/GuideEvaluate' // 我的导购评价
import MyEvaluateList from '@/pages/mine/MyEvaluateList' // 我的评价列表 CommodityEvaluate
import WriteEvaluate from "@/pages/order/WriteEvaluate" // 商品评价
import ShopList from "@/pages/shop/ShopList" // 店铺列表
import MySaleCard from "@/pages/mine/MySaleCard" // 我的优惠券

const CloakRoom = () => import('@/pages/mine/CloakRoom')

  export default [
  /**
     * 我的模块
     */
    {
      path: "/mine",
      name: "Mine",
      component: Mine,
      meta: {
        title: "我的",
        requiresAuth: true
      }
    },
    /**
     * 会员中心
     */
    {
      path: "/mine/MineCenter",
      name: "MineCenter",
      component: MineCenter,
      meta: {
          title: '会员中心'
      }
    },
    /**
     * 我的二维码
     */
    {
      path: "/mine/MineQR",
      name: "MineQR",
      component: MineQR,
      meta: {
          title: '我的二维码'
      }
    },
    /**
     * 我的资料
     */
    {
      path: "/mine/MyProfile",
      name: "MyProfile",
      component: MyProfile,
      meta: {
          title: '我的信息'
      }
    },
    /**
     * 我的足型信息
     */
    {
      path: "/mine/MyFoots",
      name: "MyFoots",
      component: MyFoots,
      meta: {
          title: '我的足型信息'
      }
    },
    /**
     * 我的足型信息新增编辑
     */
    {
      path: "/mine/MyFootsOperation",
      name: "MyFootsOperation",
      component: MyFootsOperation,
      meta: {
          title: '我的足型信息新增编辑'
      }
    },
    /**
     * 我的地址列表
     */
    {
      path: "/mine/MyAddress",
      name: "MyAddress",
      component: MyAddress,
      meta: {
        title: "我的地址列表",
      }
    },
    /**
     * 新增收货地址
     */
    {
      path: "/mine/AddAddress",
      name: "AddAddress",
      component: AddAddress,
      meta: {
        title: "新增收货地址"
      }
    },
    /**
     * 我的-购物车列表
     */
    {
      path: "/mine/cart",
      name: "Cart",
      component: Cart,
      meta: {
          title: '购物车'
      }
    },
    /**
     * 我的-消息列表
     */
    {
      path: "/mine/msg",
      name: "MyMsg",
      component: MyMsg,
      meta: {
          title: '我的消息'
      }
    },
    /**
     * 我的-心愿清单
     */
    {
      path: "/mine/wishList",
      name: "WishList",
      component: WishList,
      meta: {
          title: '心愿清单'
      }
    },
    /**
     * 我的专属导购
     */
    {
      path: "/mine/shoppingGuide",
      name: "ShoppingGuide",
      component: ShoppingGuide,
      meta: {
          title: '我的专属导购'
      }
    },
    /**
     * 我的导购评价
     */
    {
      path: "/mine/GuideEvaluate",
      name: "GuideEvaluate",
      component: GuideEvaluate,
      meta: {
          title: '我的导购评价'
      }
    },
    /**
     * 我的-评价列表
     */
    {
      path: "/mine/myEvaluateList",
      name: "MyEvaluateList",
      component: MyEvaluateList,
      meta: {
          title: '评价列表'
      }
    },
    /**
     * 商品评价
     */
    {
      path: "/writeEvaluate",
      name: "WriteEvaluate",
      component: WriteEvaluate,
      meta: {
          title: '商品评价'
      }
    },
    {
      path: "/cloakroom",
      name: "CloakRoom",
      component: CloakRoom,
      meta: {
        title: '我的衣帽间'
      }
    },
    {
    	path: "/mine/MySaleCard",
      name: "MySaleCard",
      component: MySaleCard,
      meta: {
        title: '我的优惠券'
      }
    }
  ]
