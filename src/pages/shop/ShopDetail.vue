<template>
  <div class="shopContent">
    <div class="shopInfomation">
      <div class="shopInfo" @click="toShopDetail(shop.id)">
        <div class="left">
          <div class="text">
            <div class="area">{{shopData.location}}</div>
            <div class="shopName">{{shopData.shopName}}
            </div>
            <div class="addr">{{shopData.address}}</div>
            <div class="distance">{{shopData.distance}}</div>
          </div>
        </div>
      </div>
      <div class="rightIcon">
        <div class="showIcon" @click="likeBtnClick">
          <img src="../../assets/images/icon-sc-dj.png" v-if="shopData.isLike"/>
          <img src="../../assets/images/icon/icon-sc-wdj.png" alt="" v-else>
          <span>{{shopData.collectionCount}} 人</span>
        </div>
        <div class="showIcon" @click="share">
          <img src="../../assets/images/icon-fenxian-h.png"/>
          <span>分享</span>
        </div>
      </div>
    </div>
    <div class="showMoreDetailWrapper">
      <div class="showMoreDetailWrapperInner">
        <div class="shopDetail" v-if="showDetail">
          <div class="detailIcon"><img src="../../assets/images/icon-shijian.png"/></div>
          <div class="detailText">营业时间：{{shopData.saleTime}}</div>
        </div>
        <div class="shopDetail" v-if="showDetail">
          <div class="detailIcon">
            <img src="../../assets/images/icon-lxdh-h.png"/>
          </div>
          <a :href="'tel:'+shopData.phone" class="detailText">联系电话 ：{{shopData.phone}}</a>
        </div>
        <div class="dropbar" @click="showToggle()"><i class="toggle-arrow-icon" :class="{'toggle-arrow-show-icon':showDetail}"></i></div>
      </div>
    </div>
    <div class="mapDIV">
      <div style="height: 100%;">
        <ShopMap style="height: 100%;" ref="mapDom" :currentShopData="shopData" @change="currentShopChange"></ShopMap>
      </div>
    </div>
    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import {mapState} from "vuex"
  import QuickBar from "components/QuickBar"
  import ShopMap from "../../components/ShopMap"
  import {getShopDetail} from 'api/shop'
  import wxMixins from 'mixins/weixin'
  export default {
    components: {
      QuickBar,
      ShopMap
    },
    mixins:[wxMixins],
    data(){
      return {
        showDetail:false,
        formData:{
          shopNo:'',
          longitude:"120.677329",
          latitude:"27.848174"
        },
        shopData:{

        },
      }
    },
    computed: {

    },
    created(){
      this.formData.shopNo = this.$route.query.shopNo;
      //如果没有shopNo则跳转到商店列表页面
      if(!!!this.formData.shopNo){
        this.$router.push({name:"ShopList"})
      }
      this.getShopData();
    },
    methods:{
      showToggle(){
        this.showDetail = !this.showDetail;
      },
      getShopData(){
        //先从vuex中获取，如果没有则从接口请求
        var shopData = this.$store.getters.getShopDataById(this.formData.shopNo);
        if(shopData){
          this.shopData=shopData;
          this.shopData.isLike = false;
          this.setMapPoint();
          return;
        };

        getShopDetail(this.formData)
          .then(res=>{
            res.response_data.distance=parseFloat(res.response_data.distance).toFixed(2)+'km';
            res.response_data.isLike = false;
            this.shopData=res.response_data;
            this.setMapPoint();
          })
          .catch(err=>{
            console.log(err)
            this.$store.dispatch("showMsgTips","获取店铺详情失败，请重试")
          })
      },
      setMapPoint(){
        var mapDom = this.$refs.mapDom;
        // mapDom.mapPanTo(this.shopData.shopJD,this.shopData.shopWD);
      },
      /**
       * 监听地图点击商店事件
       * @param shopData 当前点击商店的数据
       */
      currentShopChange(shopData){
        this.shopData=shopData;
      },
      /**
       * 点击收藏按钮
       */
      likeBtnClick(){
        this.shopData.isLike = !!!this.shopData.isLike;
        if(this.shopData.isLike){
          this.shopData.collectionCount++;
        }else{
          this.shopData.collectionCount--;
        }
      },
      /**
       * 分享
       */
      share(){
        //分享链接图片等
        this.wxShard();
      },
    },
  };
</script>

<style>
  html,body{
    height: 100%;
  }
  .shopContent {
    display: flex;
    flex-direction: column;
    height: 100%;
    justify-content:space-between;
  }

  .shopContent .shopInfomation {
    padding: 24px 0px 15px 17px;
    border-bottom: 1px solid #e5e5e5;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .shopContent .shopInfomation .shopInfo{
    border: none;
  }
  .shopContent .shopInfomation .rightIcon {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    padding-right: 17px;
  }

  .shopContent .shopInfomation .rightIcon .showIcon {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-left: 10px;
    justify-content: flex-start;
  }

  .shopContent .shopInfomation .rightIcon .showIcon img {
    width: 32px;
  }
  .shopContent .shopInfomation .rightIcon .showIcon span {
    color: #666666;
    font-size:12px;
  }

  .shopContent .shopInfomation .shopInfo {

  }
  .shopContent .shopDetail{
    height: 55px;
    border-bottom: 1px solid #e5e5e5;
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .shopContent .shopDetail .detailIcon{
    width: 45px;
    padding:0px 15px;
  }

  .shopContent .shopDetail .detailText{
    font-size: 12px;
    font-weight: bold;
    color: #1a1a1a;
  }
  .shopContent .shopDetail .detailIcon img{
    width: 100%;
  }

  .shopContent .shopInfomation .shopInfo .left .text .area {
    margin-bottom: 6px;
  }

  .shopContent .shopInfomation .shopInfo .left .text .shopName {
    font-size: 18px;
    font-weight: bold;
    color: #1a1a1a;
    margin-bottom: 12px;
  }

  .shopContent .shopInfomation .shopInfo .left .text .addr {
    font-size: 12px;
    color: #b3b3b3;
    margin-bottom: 10px;
  }

  .shopContent .shopInfomation .shopInfo .left .text .distance {
    font-size: 12px;
    color: #666666;
  }
  .dropbar{
    height: 24px;
    border-bottom: 1px solid #e5e5e5;
  }
  .showMoreDetailWrapper{
    height: 24px;
    position: relative;
  }
  .showMoreDetailWrapperInner{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    z-index: 100;
    background: #fff;
  }
  .toggle-arrow-icon{
  	display: block;
  	height: 100%;
  	margin: 0 auto;
  	width: 0.48rem;
  	background: url(../../assets/images/icon/icon-right.png) no-repeat center;
    background-size: auto 40%;
    transform: rotate(90deg);
    transition: transform .2s ease-in-out;
  }
  .toggle-arrow-show-icon{
  	transform: rotate(-90deg);
  }
  .mapDIV{
    background-color: #0bb20c;
    flex-grow: 1;
    display: flex;
    flex-direction: column;
    justify-content:space-between;
    position: relative;
  }
  .mapDIV>div{
    position: absolute;
    width: 100%;
    height: 100%;
  }
</style>
