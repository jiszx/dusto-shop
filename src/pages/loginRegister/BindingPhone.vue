<template>
    <div class="login-box">
      <div class="lg-title">绑定手机号</div>
      <div class="login-form forgetPwd">

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">手机号码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" placeholder="请输入您注册会员的手机号" v-model="formMess.phone"/>
            </div>
        </div>
        <div class="weui-cell weui-cell_vcode">
            <div class="weui-cell__hd">
                <label class="weui-label">校验码</label>
            </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" placeholder="请输入短信验证码">
            </div>
            <div class="weui-cell__ft">
                <button type="button" :disabled="disabled" @click="sendcode" class="weui-vcode-btn">{{btntxt}}</button>
            </div>
        </div>
        <button class="weui-btn login-btn common-div" @click="toNext()">下一步</button>
      </div>
    </div>
</template>
<script>
  export default {
    created (){

    },
    data (){
      return {
        disabled:false,
        time:0,
        btntxt:"发送验证码",
        formMess:{
            phone:'',
        }
      }
    },
    mounted: function () {

    },
    components:{
    },
    methods:{
      toNext:function(){
        //todo 待完善点击下一步功能
        this.$store.dispatch('setHasBound',true);

        this.$router.push({name:'MyProfile'}) //query等同于toNext，这里暂时静态模拟跳转
      },
      //验证手机号码部分
      sendcode(){
          var reg=/^1[3|4|5|7|8][0-9]{9}$/;
          if(this.phone==''){
            console.log("请输入手机号码");
          }else if(!reg.test(this.formMess.phone)){
            console.log("手机格式不正确");
          }else{
            this.time=60;
            this.disabled=true;
            this.timer();
            this.$store.dispatch('getVerificationCode',this.formMess)
         }
      },
      timer() {
          if (this.time > 0) {
              this.time--;
              this.btntxt=this.time+"s后重新获取";
              setTimeout(this.timer, 1000);
          } else{
              this.time=0;
              this.btntxt="发送验证码";
              this.disabled=false;
          }
      },
      query(){
          var formMess=this.formMess
      },
    }
  }
</script>


