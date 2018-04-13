<template>
    <div class="login-box">
      <div class="lg-title">修改密码</div>
      <div class="login-form">

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">输入新密码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="password" placeholder="输入6-12位密码">
            </div>
        </div>
        <div class="weui-cell confirm__psd">
            <div class="weui-cell__hd"><label class="weui-label">确认新密码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="password" placeholder="再次输入密码">
            </div>
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
        headData: '修改密码'
      }
    },
    components:{
    }
  }
</script>


