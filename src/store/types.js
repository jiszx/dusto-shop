/**
 * 全局相关types
 */
export const FETCH_LOADING = "fetchLoading";//接口请求loading状态标识
export const SHOW_MSG_TIPS = "showMsgTips";//显示提示弹层
export const HIDE_MSG_TIPS = "hideMsgTips";//隐藏提示弹层


/**
 * 登录相关
 * @type {string}
 */
export const GET_LOGIN_STATE = "getLoginState";
export const SET_WEIXIN_TOKEN = "setWeixinToken";
export const SET_HASBOUND = "setHasBound";
export const GET_VERIFICATION_CODE = "getVerificationCode";
/**
 * 我的相关
 */
//我的专属导购
export const GUIDER_GET_INFO = "guiderGetInfo";
export const GUIDER_SUBMIT_EVALUATE = "guiderSubmitEvaluate";
// 我的地址
export const ADDRESS_GET_DATA = "addressGetData"
export const ADDRESS_ADD = "addressAdd";
export const ADDRESS_UPDATE_BY_ID = "addressUpdateById";
export const ADDRESS_DEL_BY_ID = "addressDelById";
export const ADDRESS_SET_DEFAULT = "addressSetDefault";

/**
 * 购物车
 * */
export const ADD_TO_SHOPPING_CART = "addToShoppingCart";
export const DECREMENT_ONE_FROM_SHOPPING_CART = "decrementOneFromShoppingCart";
export const EMPTY_SHOPPING_CART = "emptyShoppingCart";
export const DELETE_SHOPPING_CART_GOODS_BY_UUID = "deleteShoppingCartGoodsByUuid";
export const UPDATE_SHOPPING_CART_BY_UUID = "updateShoppingCartByUuid";
export const ADD_PRESENT_BY_UUID = "addPresentByUuid";
export const DELETE_PRESENT_BY_UUID = "deletePresentByUuid";
export const UPDATE_PRESENT_BY_UUID = "updatePresentByUuid";
export const UPDATE_CART_HAS_PRESENT = 'updateCartHasPresent';
export const ADD_CART_ORDER_PRESENT = "addCartOrderPresent";
export const UPDATE_CART_SELECT_GOODS = "updateCartSelectGoods";
export const ADD_CURRENT_ORDER_PRESENT = "addCurrentOrderPresent";//给整个订单添加赠品
export const DELETE_CURRENT_ORDER_PRESENT = "deleteCurrentOrderPresent";//给整个订单删除赠品
export const EMPTY_CURRENT_ORDER_PRESENT = 'emptyCurrentOrderPresent';//清空订单赠品
export const EMPTY_CART_SELECT = "emptyCartSelect";

/**
 * 订单相关
 */
export const ORDER_GET_LIST = "orderGetList";
export const ORDER_GET_DETAIL = "orderGetDetail";
export const ORDER_UPDATE_LIST = "orderUpdateList";
export const ORDER_UPDATE_DETAIL = "orderUpdateDetail";

export const ADD_SUBMITING_ORDER = 'addSubmitingOrder';
export const UPDATE_SUBMITING_ORDER_ADDRESS = "updateSubmitingOrderAddress";
export const UPADTE_SUBMITING_ORDER_COUPON = "updateSubmitingOrderCoupon";
export const UPDATE_SUBMITING_ORDER_JIFEN = "updateSubmitingOrderJifen";
export const UPDATE_SUBNITING_ORDER_ACTIVITY = "updateSubmitingOrderActivity";

//售后信息相关
export const GET_CUSTOMER_SERVICE = "getCustomerService";


/**
 * 会员相关
 */
//会员信息查询
export const GET_MY_MESSAGE= "getMyMessage"

/**
 * 营销相关
 */
 //广告信息查询
 export const GET_ADVERTISEMENT_INFO = "getAdvertisementinfo";

/**
 * 门店相关
 */
export const GET_SHOP_LIST = "getShopList";
export const GET_SHOP_DETAIL = "getShopDetail";
export const UPDATE_SHOP_LIST = "updateShopList";

/**
 * 商品相关
 */
 export const GET_PRODUCT_LIST = "getProductList";//获取产品列表
 export const PRODUCT_LIST_LOADING_MORE = "productListLoadingMore"; //加载更多
 export const GET_MSG_LIST = "getMsgList";//资讯列表查询
 export const GET_MESSAGA_DETAIL = "getMessageDetai";//资讯详情查询
 //推荐商品列表查询
 export const GET_NEW_PRODUCT_BRUST = "getNewProductBrust";//获取新品爆款列表
 export const SET_NEW_PRODUCT_BRUST = "setNewProductBrust";//设置新品爆款列表

