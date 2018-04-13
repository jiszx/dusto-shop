/**
 * 存储全局state变量
 * @type {{}}
 */
const state ={
  // 请求数据时加载状态loading
  fetchLoading: false,
  //提示弹层
  messageTips: {
    isShow:false,
    text:'',
    type:'text',//可以配置弹出提示框类型,支持success fail default
  },

};

export default state;
