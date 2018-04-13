<template>
  <div id="app" class="MyFoots">
    <ul>
      <li class="list-item">
        <div class="userImg"><img :src="footMessageData.img" /></div>
        <div class="goods-info">
          <div class="goods-info-txt">
            <span class="goods-txt">{{footMessageData.name}}</span>
            <i @click="editFootsInfo" class="goods-edit-btn"></i>
          </div>
          <div class="goods-spec">
            <span class="goods-spec-item">尺码:{{footMessageData.size}}码</span>
          </div>
          <div class="goods-spec sanwei">
            <span class="goods-spec-item">前掌围:{{footMessageData.qzw}}mm</span>
            <span class="goods-spec-item">脚背围:{{footMessageData.jbw}}mm</span>
            <span class="goods-spec-item">脚长:{{footMessageData.footSize}}mm</span>
          </div>
        </div>
      </li>
    </ul>

    <div class="addInfo">
      <p @click="addFootsInfo"><span class="addIcon"><img class="list-img" alt="" src="../../assets/images/mine/icon-xzshdz.png" /></span>新增足型信息</p>
    </div>

    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import QuickBar from "components/QuickBar";
  import { getFootMessageData } from 'api/member'
  export default {
    data() {
      return {
        uuid:undefined,
        formData:{
          memberNo:"123",
          phone:"123"
        },
        footMessageData:{
          img:"",
          name:"",
          size:"",
          qzw:"",
          jbw:"",
          footSize:""
        }
      }
    },
    components: {
      QuickBar // 快速导航
    },
    created(){
      this.uuid = this.$route.query.uuid;
      this.getData();
    },
    methods:{
      getData(){
        getFootMessageData(this.formData).then(res=>{
          if(res.response_code=="S000000"){
            this.footMessageData = res.response_data;
          }
          }).catch(err=>{
            this.$store.commit('showMsgTips','请求失败，请重试！')
          })
      },
      addFootsInfo:function(){
        this.$router.push({path:'/mine/MyFootsOperation',query:{type:'add',uuid:this.uuid}})
      },
      editFootsInfo:function(){
        this.$router.push({path:'/mine/MyFootsOperation',query:{type:'edit',uuid:this.uuid}})
      }
    }
  };
</script>

<style>
  .MyFoots {
    background: #f7f7f7;
    position:fixed;
    top:0;
    bottom:;
    left:0;
    right:0;
  }
  .MyFoots ul li{
    background: #fff;
    margin-bottom:0.2rem;
  }
  .MyFoots ul li.list-item {
    display: flex;
    padding: 0.2rem 0.3rem 0.2rem 0.24rem;
  }
  .list-img {
      width: 1.6rem;
      height: 1.6rem;
  }
  .userImg {
    width: 1.6rem;
    height: 1.6rem;
  }
  .userImg img{
    width:100%;
    border-radius: 0.1rem;
  }

  .goods-info {
    padding: 0.16rem 0 0.24rem 0.22rem;
    -webkit-box-flex: 1;
    -webkit-flex: 1;
    flex: 1;
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -webkit-flex-direction: column;
    flex-direction: column;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    justify-content: space-between;
  }
  .MyFoots .goods-info-txt {
      display: -webkit-box;
      display: -webkit-flex;
      display: flex;
      padding-top: 0.1rem;
  }
  .goods-txt {
      height: 0.46rem;
      font-size: 0.28rem;
      color: #000;
      line-height: 0.36rem;
      overflow: hidden;
      -webkit-box-flex: 1;
      -webkit-flex: 1;
      flex: 1;
  }

  .goods-edit-btn{
    margin-top: 0.04rem;
    display: block;
    width: 1rem;
    height: 0.3rem;
    background: url(../../assets/images/icon/icon-bianji-x.png) no-repeat center right;
    background-size: auto 100%;
  }

  .goods-spec {
    font-size: 0;
  }
  .sanwei{margin-top: -3px;}
  .goods-spec-item {
      color: #999;
      font-size: 0.2rem;
  }
  .goods-spec-item + .goods-spec-item {
      margin-left: 0.2rem;
  }
</style>
