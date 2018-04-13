import fetch from 'assets/js/fetch';

/**
 * 会员信息查询 WX-C-01
 */
export const getMyMessageData = (data)=> {
  return fetch.post('/interf-rf/memberRs/myMessage',{
    request_data:{
      memberNo:data.memberNo,
      phone:data.phone
    }
  })
};

/**
 * 推荐商品列表查询 WX-C-02
 */
export const getProductrecommendlistData = ()=> fetch.post('/productrs/productrecommendlist',{
  request_data:{
    "keyword":""
  }
});


 /**
 * 积分明细查询  WX-C-04
 */
export const getPointTotalSearchData = (data)=> {
  return fetch.post('/interf-rf/memberRs/pointTotalSearch',{
    request_data:{
      memberNo:data.memberNo
    }
  })
};

 /**
 * 我的足型信息  WX-C-17
 */
export const getFootMessageData = (data)=> {
  return fetch.post('/interf-rf/memberRs/footMessage',{
    request_data:{
      memberNo:data.memberNo,
      phone:data.phone
    }
  })
};


 /**
 * 会员信息更新 WX-C-06
 */
export const getUpdateMemberData = (data)=> {
  return fetch.post('/interf-rf/memberRs/updateMember',{
    request_data:{
      memberId:data.memberId,
      memberName:data.memberName,
      nickName:data.nickName,
      sex:data.sex,
      birthday:data.birthday,
      size:data.size,
      qzw:data.qzw,
      hbw:data.hbw,
      shopAddress:data.shopAddress,
      footSize:data.footSize
      }
    })
};

/**
 * 会员收藏列表/我的心愿清单 WX-C-32
 */
export const getCollectionListData = (data) =>{
  return fetch.post('/interf-rf/memberRs/collectionList',{
    request_data:{
      memberNo:data.memberNo,
      cityCode:data.cityCode
    }
  })
};