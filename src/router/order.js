import OrderList from '@/pages/order/OrderList'//我的订单
import OrderDetail from "@/pages/order/OrderDetail" // 订单详情
import OnlineReturn from "@/pages/order/OnlineReturn" // 在线退货
import OnlineExchange from "@/pages/order/OnlineExchange" // 在线换货
import ChooseShop from "@/pages/order/ChooseShop" // 选择换货门店
import ApplyForBack from "@/pages/order/ApplyForBack" // 待收货-申请退款
import ExhangeGoods from '@/pages/order/ExchangeGoods'
import LogisticsCompany from "@/pages/order/LogisticsCompany" // 选择物流公司页面
import Logistics from "@/pages/order/Logistics" // 物流信息页面
import ChooseSaleCard from "@/pages/order/ChooseSaleCard" // 选择优惠券
import FirmOrder from "@/pages/order/FirmOrder" // 确认订单
import AfterSaleGoods from "@/pages/order/AfterSaleGoods" // 选择售后商品
import CustomerService from "@/pages/order/CustomerService" //售后

export default [
    {//我的订单
	  	path: "/order/OrderList",
	  	name: 'OrderList',
	  	component: OrderList,
	  	meta: {
	    	title: "我的订单"
	  	}
    },
    // 订单详情
	{
	    path: "/order/orderDetail",
	    name: "OrderDetail",
	    component: OrderDetail,
	    meta: {
	      	title: "订单详情"
    	}
   	},
   	// 在线退货
	{
	    path: "/order/onlineReturn",
	    name: "OnlineReturn",
	    component: OnlineReturn,
	    meta: {
	      	title: "在线退货"
	    }
   	},
   	// 在线换货
	{
	    path: "/order/OnlineExchange",
	    name: "OnlineExchange",
	    component: OnlineExchange,
	    meta: {
	      	title: "在线换货"
	    }
   	},
   	// 订单-选择换货门店
   	// 在线换货
	{
	    path: "/order/ChooseShop",
	    name: "ChooseShop",
	    component: ChooseShop,
	    meta: {
	      	title: "选择线下门店"
	    }
   	},
    // 待收货-申请退款
    {
      	path: "/order/ApplyForBack",
     	name: "ApplyForBack",
     	component: ApplyForBack,
     	meta: {
        	title: "待收货-申请退款"
      	}
    },
  	// 申请换货
  	{
	    path: "/order/ExhangeGoods",
	    name: "ExhangeGoods",
	    component: ExhangeGoods,
	    meta: {
	      	title: "待评价-申请换货"
	    }
  	},
  	// 选择物流公司
  	{
	    path: "/order/LogisticsCompany",
	    name: "LogisticsCompany",
	    component: LogisticsCompany,
	    meta: {
	      	title: "选择物流公司"
	    }
  	},
  	// 物流信息页面
  	{
	    path: "/order/Logistics",
	    name: "Logistics",
	    component: Logistics,
	    meta: {
	      	title: "订单物流信息"
	    }
  	},
  	// 选择优惠券 ChooseSaleCard
  	{
	    path: "/order/ChooseSaleCard",
	    name: "ChooseSaleCard",
	    component: ChooseSaleCard,
	    meta: {
	      	title: "选择优惠券"
	    }
  	},
  	// 确认订单
  	{
	    path: "/order/FirmOrder",
	    name: "FirmOrder",
	    component: FirmOrder,
	    meta: {
	      	title: "确认订单"
	    }
  	},
  	// 选择售后商品
  	{
  		path: "/order/AfterSaleGoods",
	    name: "AfterSaleGoods",
	    component: AfterSaleGoods,
	    meta: {
	      	title: "选择售后订单"
	    }
  	},
  	//售后
  	{
  		path: "/order/CustomerService",
  		name: "CustomerService",
  		component: CustomerService,
  		meta: {
  			title:"售后",
        showQuickBar:true,
  		}
  	}
  ]
