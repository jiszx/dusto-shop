import fetch from 'assets/js/fetch';

/**
 * 资讯列表查询 WX-C-28
 */
export const getMsgListData = (data)=> {
	return fetch.post('/interf-rf/msgrs/msglist',{
	  request_data:{

	  }
	})
};

/**
 * 资讯详情查询 WX-C-29
 */
export const getMsgDetailData = (data)=> {
	return fetch.post('/interf-rf/msgrs/msgdetail',{
	  request_data:{
	    infoID:data.infoID
	  }
	})
};


