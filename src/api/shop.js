import fetch from 'assets/js/fetch';

/**
 * 获取商店列表 wx-c-14
 */
export const getShopList = (data)=> {
  return fetch.post('/interf-rf/shoprs/searchShop',{
    request_data:{
      keyword:data.keyword,
      longitude:data.longitude,
      latitude:data.latitude
    }
  })
};
/**
 * 获取商店详情 wx-c-18
 * @param data
 * @returns {*}
 */
export const getShopDetail = (data)=> {
  return fetch.post('/interf-rf/shoprs/findShopInfoWeChat',{
    request_data:{
      shopNo:data.shopNo,
      longitude:data.longitude,
      latitude:data.latitude
    }
  })
};

/**
 * 获取省市地区级联数据
 *
 * @param id 查询id
 */
export const getAreainfo = (id)=>{
  return fetch.post('/interf-rf/commonrs/areainfo',{
    request_data:{
      parentID:id,
    }
  })
};
