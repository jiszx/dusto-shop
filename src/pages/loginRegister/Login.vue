<template>
  <div class="login-box">
    <div class="lg-logo">
      <div><img src="../../assets/images/login/img-logo.png"/></div>
    </div>
    <div class="login-form">
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">手机号码</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="tel" placeholder="请输入您注册会员的手机号">
        </div>
      </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">输入密码</label></div>
        <div class="weui-cell__bd">
          <input class="weui-input" type="password" placeholder="输入密码">
        </div>
      </div>
      <div class="forgets">
        <a href="javascript:void (0)">
          <input id="weuiAgree" type="checkbox" class="weui-agree__checkbox"><span>保存密码</span>
        </a>
        <router-link to="/loginRegister/ForgetPwd">
          <a>忘记密码？</a>
        </router-link>
      </div>
      <!--<router-link to="/loginRegister/ModifyPwd"></router-link>-->
      <button class="weui-btn login-btn common-div" @click="submitLogin()">登陆</button>

      <router-link to="/loginRegister/Register">
        <a class="login-oth-btn common-div">注册VIP会员</a>
      </router-link>
    </div>
    <div class="page__ft">
      <router-link  tag="a" to="/loginRegister/BindingPhone">游客身份登录</router-link>
    </div>
  </div>
</template>
<script>
  import {mapActions} from 'vuex'

  export default {
    created() {
      $('head title').html(this.headData);
      $('body').css({
        "background": "#fff"
      });
    },
    data() {
      return {
        
      }
    },
    methods: {
      submitLogin() {
        //写登录调用方法
        this.$store.dispatch("login", true)
        this.$router.push({path: this.$route.query.redirect})
      }
    },
    computed: {
      ...mapActions([
        'login'
      ])
    },
    components: {}
  }
</script>


