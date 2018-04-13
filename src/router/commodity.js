import CommodityDetails from '@/pages/commodity/CommodityDetails'
import CommodityEvaluate from '@/pages/commodity/CommodityEvaluate'
export default [
    {//商品详情
      path: "/commodity/CommodityDetails",
      name: 'CommodityDetails',
      component: CommodityDetails,
      meta: {
        title: "商品详情",
        showQuickBar:true,
      }
    },
    // 商品详情评价
	{
	  path: "/commodity/CommodityEvaluate",
	  name: "CommodityEvaluate",
	  component: CommodityEvaluate,
	  meta: {
	     title: "商品详情评价"
	  }
    }
  ]
