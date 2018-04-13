import fetch from 'assets/js/fetch';



 /**
 * 会员信息更新 WX-C-06
 */
export const getUpdateMemberData = ()=> fetch.post('/memberRs/updateMember',{
  request_data:{
    memberID:"",
	memberName:"xxx",
	nickName:"json",
	sex:"女",
	birthday:"2018-01-01",
	size:"35",
	qzw:"44",
	hbw:"40",
	shopAddress:"广州市-白云区",
	footSize:"43"
  }
});


/**
 * 会员收藏店铺人数 WX-C-19  （已取消不需要单独获取）
 */
export const getCollectorsCountData = (data)=> {
	return fetch.post('/interf-rf/memberRs/collectorsCount',{
	  request_data:{
	    shopNo:data.shopNo
	  }
	})
};


/**
 * 收藏添加/取消 WX-C-20
 */
export const getCollectorsAddOrDelData = ()=> fetch.post('/memberRs/collectorsAddOrDel',{
  request_data:{
    shopNo:"",
	memberNo:"123"
  }
});

/**
 * 会员登陆 WX-C-21
 */
export const getMemberLoginData = ()=> fetch.post('/memberRs/memberLogin',{
  request_data:{
    phone:"18200000000",
	password:"123",
	wxOpenID:"123"
  }
});

/**
 * 修改密码  WX-C-22   不需要了
 */
export const getUpdatePasswordelData = ()=> fetch.post('/memberRs/updatePassword',{
  request_data:{
    newpassword:"",
	confirmNewpassword:"123",
	phone:"18200000000"
  }
});

/**
 * 手机获取验证码 WX-C-23
 */
export const getVerificationCodeSendlData = (data)=> {
	return fetch.post('/interf-rf/memberRs/verificationCodeSend',{
	  request_data:{
	    phone:data.phone
	  }
	})
};


/**
 * 查询密码接口 WX-C-24
 */
export const getFindPasswordData = ()=> fetch.post('/memberRs/findPassword',{
  request_data:{
    phone:"18200000000",
	verificationCode:"123567"
  }
});

