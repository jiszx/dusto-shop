import fetch from 'assets/js/fetch';

 /**
 * 广告信息查询 WX-C-03
 */
export const getAdvertisementinfoData = (data)=> {
  return fetch.post('/interf-rf/promors/advertisementinfo',{
    request_data:{
      positionID:data.positionID,
      channelType:data.channelType
    }
  })
};

 /**
 * 优惠券列表查询 WX-C-30
 */
export const getCouponlistData = (data)=> {
  return fetch.post('/interf-rf/promors/couponlist',{
    request_data:{
      
    }
  })
};

/**
 * 领券 WX-C-31
 */
export const getCouponreceiveData = (data)=> {
  return fetch.post('/interf-rf/promors/couponreceive',{
    request_data:{
      promoCodelist:[
        {promoCode:data.promoCode}
      ],
      memberNo:data.memberNo
    }
  })
};
