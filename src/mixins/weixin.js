/**
 * 封装一个mixins用于微信登录认证
 *
 */

import wx from 'weixin-js-sdk'
import fetch from 'assets/js/fetch';
export default {
  data(){
    return {
      apiGetWeixinSignature:'',
      appId: '',
      signature: '',
      timestamp: '',
      nonceStr: ''
    }
  },
  created(){

  },
  methods:{
    /**
     * 检测是否是微信
     */
    isweixin() {
      const ua = window.navigator.userAgent.toLowerCase();
      if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
      } else {
        return false;
      }
    },
    /**
     * 微信初始化
     */
    initWeixin(){
      let url = window.location.href.split("#")[0];
      //通过后台接口拉去微信签名所需数据
      fetch.get(this.apiGetWeixinSignature,{
        url:url
      })
        .then(response => {
          wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: this.appId, // 必填，公众号的唯一标识
            timestamp: this.timestamp, // 必填，生成签名的时间戳
            nonceStr: this.nonceStr, // 必填，生成签名的随机串
            signature: this.signature, // 必填，签名，见附录1
            jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
          });
        })
        .catch((error) => {
          console.log(res);
        })
    },
    /**
     * 分享
     */
    wxShard (title, desc, link,imgUrl) {
      this.initWeixin();
      let shareParams = {
        title: title,       // 分享标题
        desc: desc,   // 分享描述
        link: link,       // 分享链接 默认以当前链接
        imgUrl: imgUrl,// 分享图标
        success: function () {
          //todo
          console.log('分享成功');
        },
        cancel: function () {
          //todo
          console.log('分享取消');
        }
      };
      wx.ready(() =>{
        wx.onMenuShareAppMessage({ // 分享给朋友
          ...shareParams,
        });
        //分享到朋友圈
        wx.onMenuShareTimeline({
          ...shareParams,
        });
      });
      wx.error((res)=>{
        console.log(res);
      });
    },
    /**
     * 分享
     */
    wxPay(){

    },
    /**
     * 获取微信号地址
     */
    wxGetAddress(){
      wx.openAddress({
        success: function (res) {
          // 用户成功拉出地址
          console.log('拉取成功',res)
        },
        cancel: function (res) {
          // 用户取消拉出地址
          console.log('拉取失败',res)
        }
      });
    },
  },
}

