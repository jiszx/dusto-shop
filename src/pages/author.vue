<template>
  <div class="page-auth">
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  export default {
    data(){
      return{
        formData:{//todo 待删除数据
          memberNo:'25000105079',
          phone:'18200000000',
        },
      }
    },
    computed:{
      ...mapState({
        token: state => state.auth.weixinToken,
        hasBound:state => state.auth.hasBound,
      })
    },
    created() {
      //token & openId
      let accessToken = this.$route.query.token;
      let hasBound = this.$route.query.hasBound;
      if(accessToken){
        this.$store.dispatch("setWeixinToken", accessToken);
        this.$store.dispatch("setHasBound", hasBound.toString() === "true" ? true : false);
      }
      console.log(this.$route,this.$router)
      if(this.token&&this.hasBound){//如果有token则跳转到首页
        //如果已经绑定手机号，进入程序并登陆 todo 此处应该提供根据token获取用户信息接口
        this.$store.dispatch('getMyMessage',this.formData);

        this.$router.push({name: "Home"});
      }else if(this.token&&!this.hasBound){//如果没有绑定手机号则跳转到绑定手机号页面
        this.$router.push({name:"BindingPhone"})
      }else{
        //todo 待完善跳转到认证页面
      }
    },
    methdos:{
      doLogin(){
        //todo 获取用户信息操作
      },
    },
  };
</script>

<style>
</style>
