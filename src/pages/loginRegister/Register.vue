<template>
    <div class="login-box">
      <div class="lg-title register-title">注册VIP会员</div>
      <div class="login-form forgetPwd">

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">手机号码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="tel" placeholder="请输入您注册会员的手机号">
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <label class="weui-label">校验码</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="tel" placeholder="请输入短信验证码">
            </div>
            <div class="weui-cell__ft">
                <button class="weui-vcode-btn">发送验证码</button>
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">输入新密码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="password" placeholder="输入6-12位密码">
            </div>
        </div>
        <div class="weui-cell confirm__psd confirm__psd__reg">
            <div class="weui-cell__hd"><label class="weui-label">确认新密码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="password" placeholder="再次输入密码">
            </div>
        </div>

        <div class="forgets agree">
          <a href="javascript:void (0)"><input id="weuiAgree" type="checkbox" class="weui-agree__checkbox"><span>我已同意并阅读<span>《注册协议》</span></span></a>
        </div>
        <div class="forgets">
          <a href="javascript:void (0)"><input id="weuiAgree" type="checkbox" class="weui-agree__checkbox"><span>我愿意订阅并收到来自大东的相关信息</span></a>
        </div>
        
        <router-link to="/">
          <a  class="login-btn common-div modify-btn">提交</a>
        </router-link>
      </div>
    </div>
</template>
<script>
  export default {
    created (){
      $('head title').html(this.headData);
      $('body').css({
        "background":"#fff"
      });
    },
    data (){
      return {
        headData: '注册VIP会员'
      }
    },
    components:{
    }
  }
</script>


