<template>
    <div class="login-box">
      <div class="lg-title">找回密码</div>
      <div class="login-form forgetPwd">

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">手机号码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="tel" pattern="[0-9]*" placeholder="请输入您注册会员的手机号">
            </div>
        </div>
        <div class="weui-cell weui-cell_vcode">
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
        <button class="weui-btn login-btn common-div" @click="submitLogin()">下一步</button>
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
        headData: '忘记密码'
      }
    },
    components:{
    }
  }
</script>


