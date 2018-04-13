import fetch from 'assets/js/fetch';

/**
 * 推荐商品列表查询 WX-C-02 如新品推荐，爆款等
 */
export const getProductrecommendlistData = (data)=> {
	return fetch.post('/interf-rf/productrs/productrecommendlist',{
	  request_data:{
	    keyword:data.keyword
	  }
	})
};

/**
 * 商品列表查询 WX-C-05
 */
export const getAllocationlistData = ()=> fetch.post('/productrs/allocationlist',{
  request_data:{
    "memberNo":"25000105079"
  }
});

/**
 * 商品搜索 WX-C-27
 */
export const getProductSearchData = (data) =>{
	return fetch.post('/interf-rf/productrs/productsearch',{
		request_data:{
      ...data
		},
    pageSize:data.pageSize,
    currentPage:data.currentPage,
	})
};

/**
 * 通过keyword获取商品列表数据
 */

export const getProductrecommendlistDataByKeyword = (keyword)=> {
  return fetch.post('/interf-rf/productrs/productrecommendlist',{
    request_data:{
      keyword:keyword
    }
  })
};

/**
 * 获取产品详情 wx-c-26
 *
 */
export const productDetail = (data)=>{
  return fetch.post('/interf-rf/productrs/productdetail',{
    request_data:{
      productID:data.productID,
      phone:data.phone,
      cityCode:data.cityCode,
    }
  })
};
/**
 * 获取库存信息 DG-C-18
 */
export const getStocksinfo = (data)=>{
  return fetch.post('/interf-rf/stockrs/stocksinfo',{
    request_data:{
      artNo:data.artNo,
      shopNo:data.shopNo,
    }
  })
};


