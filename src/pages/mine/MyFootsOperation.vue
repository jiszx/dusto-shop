<template>
  <div id="app" class="MyProfile">
    <div class="myProfile">
      <div class="weui-cell">
          <div class="weui-cell__hd"><label class="weui-label">名称：</label></div>
          <div class="weui-cell__bd">
              <input class="weui-input" type="text" v-model="footMessageData.name" placeholder="请输入真实名字">
          </div>

          <div class="weui-cell__hd"><label class="weui-label">性别：</label></div>
          <div class="weui-cell__bd">
              <input class="weui-input" type="text" v-model="footMessageData.sex" placeholder="请输入性别">
          </div>
      </div>

      <div class="weui-cell">
          <div class="weui-cell__hd">
            <label class="weui-label myShoeCode">我的鞋码<span class="notice">可多选</span></label>
          </div>
      </div>

      <div class="weui-media-box_appmsg">
        <div class="weui-media-box__bd">
          <div class="promotion-sku clear">
            <ul>
              <li v-for="(item,index) in footSizeList" :class="{active:footMessageData.size.includes(item.size)}" @click="chooseSize(item)"><a href="javascript:;">{{item.size}}</a></li>
            </ul>
          </div>
        </div>
      </div>

      <div class="weui-cell">
          <div class="weui-cell__hd">
            <label class="weui-label myShoeCode">我的脚维<span class="notice">（单位：cm）</span></label>
          </div>
      </div>
      <div class="weui-media-box_appmsg">
        <div class="weui-media-box__bd">
          <div class="promotion-sku clear myHem">
            <ul>
              <li>
                <field v-model="footMessageData.footSize" label="脚长" type="number"/>
              </li>
              <li>
                <field v-model="footMessageData.jbw" label="脚宽" type="number"/>
              </li>
              <li>
                <field v-model="footMessageData.qzw" label="前脚维" type="number"/>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="weui-cell">
          <img alt="" src="../../assets/images/mine/img-cmck.png" />
      </div>

      <div class="login-form">
        <button class="weui-btn login-btn common-div" @click="goMyFoots()">确定</button>
      </div>

    </div>

    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import { Icon, Field, Button } from 'vant';
  import QuickBar from "components/QuickBar";
  import { getFootMessageData } from 'api/member'
  export default {
    data() {
      return {
        headData: '我的足型信息新增修改',
        formData:{
          memberNo:"123",
          phone:"123"
        },
        footMessageData:{
          name:"",
          sex:"",
          size:[],
          footSize:"",
          jbw:"",
          qzw:""
        },
        footSizeList:[
          {size:34},
          {size:35},
          {size:36},
          {size:37},
          {size:38},
          {size:39},
          {size:40},
          {size:41},
          {size:42},
          {size:43},
          {size:44},
        ],
        qparam:"前掌围",
        bparam:"脚背围",
        cparam:"脚长",
      }
    },
    created() {
      this.uuid = this.$route.query.uuid;
      this.id = this.$route.query.id;
      this.type = this.$route.query.type;
      if(this.type==='edit'){
        this.getData();
      }
      
    },
    methods:{/**
       * 检验表单是否填写完整
       */
      validateForm(){
        if(!this.footMessageData.name){
          this.$store.dispatch('showMsgTips','请输入用户名')
          return false;
        }
        return true;
      },
      goMyFoots:function(){
        if(!this.validateForm()){//如果表单验证不通过，则返回
          return;
        }
        this.$router.go(-1)     
      },
      getData(){
        getFootMessageData(this.formData).then(res=>{
          if(res.response_code=="S000000"){
            this.footMessageData = res.response_data;
          }
          }).catch(err=>{
            this.$store.commit('showMsgTips','请求失败，请重试！')
          })
      },
      /**
       * 选择尺码
       * @param item
       */
      chooseSize(item){
          if(this.footMessageData.size.includes(item.size)){
            this.footMessageData.size.splice(this.footMessageData.size.indexOf(item.size),1);
          }else{
            this.footMessageData.size.push(item.size); 
          }
          console.log(this.footMessageData.size)
      },
    },
    components: {
      Icon, 
      Field, 
      Button,
      QuickBar, // 快速导航
    }
  };
</script>

<style>
  .MyProfile {
    background: #fff;
    font-size:0.24rem;
    color:#000;
  }

  .myProfile .userImg {
    width: 1.8rem;
    height: 1.8rem;
    margin: 0.3rem auto;
    background-image:url("../../assets/images/mine/img-mrtx.png");
    background-size: 100%;
    border-radius: 0.1rem;
  }
  .albumSelection-upload{
    width:100%;
    height:1rem;
    position:absolute;
    left:0;
  }
  .weui-cell img{
    width:100%;
    padding: 0.3rem 0 0.6rem 0;
  }
</style>
