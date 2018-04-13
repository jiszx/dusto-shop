import fetch from 'assets/js/fetch';

/**
 * 我的专属导购信息接口 WX-C-07
 */
export const getGuiderInfo = (data)=> {
  return fetch.post('/interf-rf/memberRs/guiderMessage',{
    request_data:{
      phone:data.phone,
      memberNo:data.memberNo,
    }
  })
};
/**
 * 我的专属导购-评价添加接口 WX-C-09
 */
export const addGuiderEvaluate = (data)=> {
  return fetch.post('/interf-rf/memberRs/addGuiderEvaluate',{
    request_data:{
      isRate:data.isRate,
      isHelp:data.isHelp,
      grade:data.grade,
      memberNo:data.memberNo,
    }
  })
};
