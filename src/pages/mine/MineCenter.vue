<template>
  <div id="app" class="MineCenter">
    <div class="wy-center-outer">
      <div>
          <div class="myText">
            <div class="weui-flex__item">
              <router-link to="/mine/MyProfile" tag="a">完善信息，领取优惠券></router-link>
            </div>
          </div>

          <div class="weui-media-box weui-media-box_appmsg">
            <div class="weui-media-box__hd" id="userHead">
              <img class="weui-media-box__thumb" :src="memberMsg.memberImg" alt="">
              <router-link to="/mine/MineQR" tag="a">
                <img class="myCode" src="../../assets/images/mine/icon-wdewm.png" alt="">
              </router-link>
            </div>
          </div>

          <div class="weui-media-box__bd" id="mycenterText">
            <h4 class="weui-media-box__title">
              <img class="weui-media-box__thumb  iconImg iconImgBig" src="../../assets/images/mine/icon-xb-nv.png" alt="">
              <span id="username">{{memberMsg.memberName}}</span>
            </h4>
            <h4 class="weui-media-box__title">
              <img class="weui-media-box__thumb iconImg" src="../../assets/images/mine/icon-shouji.png" alt="">
              <span class="myTel">{{memberMsg.phone}}</span>
              <img class="weui-media-box__thumb  iconImg" src="../../assets/images/mine/icon-shenri.png" alt="">
              <span>{{memberMsg.birthday}}</span>
            </h4>
            <p class="user-grade">会员积分：{{memberMsg.point}}</p>
          </div>
      </div>


      <div class="weui-panel weui-panel_access MineCenterList">
        <div class="weui-panel__bd">
          <div class="weui-flex threeGird">
            <div class="weui-flex__item" v-for="(item,index) in items_pt" :key="item.name" v-if="memberType===1">
              <router-link :to="{name:item.toName}" tag="a" class="center-ordersModule">
                <div class="imgicon"><img :src="item.url"></div>
                <div class="name">{{item.name}}</div>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="weui-panel weui-panel_access MineCenterList">
      <div class="weui-panel__hd">
        <router-link :to="{name:'GoodsList'}" tag="a" class="weui-cell weui-cell_access center-alloder" >
          <div class="weui-cell__bd wy-cell">
            <div class="weui-cell__bd weui-cell_primary"><p class="center-list-txt explosive">积分专享</p></div>
          </div>
          <span class="weui-cell__ft">查看更多</span>
        </router-link>
      </div>

      <div class="scoreOuter">
        <ul>
          <router-link :to="{name:'CommodityDetails'}" tag="li">
            <img src="../../assets/images/mine/img-hyzx-banner-1.png">
            <!--<span>{{advertisementinfo.memberImg}}</span>-->
            <!--<img :src="advertisementinfo.memberImg">-->
          </router-link>
        </ul>
      </div>
    </div>

    <div class="scoreOuter scoreThree">
      <ul>
        <router-link :to="{name:'CommodityDetails'}" tag="li"><img src="../../assets/images/list-goods/img-2.png"></router-link>
        <router-link :to="{name:'CommodityDetails'}" tag="li"><img src="../../assets/images/list-goods/img-3.png"></router-link>
        <router-link :to="{name:'CommodityDetails'}" tag="li"><img src="../../assets/images/list-goods/img-4.png"></router-link>
      </ul>
    </div>

    <explosive-recommendation></explosive-recommendation>
    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import ExplosiveRecommendation from "components/ExplosiveRecommendation";
  import QuickBar from "components/QuickBar";
  import {mapState} from "vuex";
  export default {
    data() {
      return {
        formData:{
          memberNo:'25000105079',
          phone:'18200000000',
          positionID:'ol_top_001',
          channelType:'ONLINE',
        },
        memberType:1,
        items_pt: [
          {
            name: "我的普通会员",
            toName: "MineQR",
            url:require("../../assets/images/mine/img-hyzx-hy-pt.png")
          },
          {
            name: "我的鞋码信息",
            toName: "MyFoots",
            url: require("../../assets/images/mine/img-wdxmxx.png")
          },
          {
            name: "我的专属导购",
            toName: "ShoppingGuide",
            url: require("../../assets/images/mine/img-wdzsdg.png")
          }
        ],
        items_ff: [
          {
            name: "我的付费会员",
            toName: "MineQR",
            url:require("../../assets/images/mine/img-hyzx-hy-hj.png")
          },
          {
            name: "我的鞋码信息",
            toName: "MyFoots",
            url: require("../../assets/images/mine/img-wdxmxx.png")
          },
          {
            name: "我的专属导购",
            toName: "ShoppingGuide",
            url: require("../../assets/images/mine/img-wdzsdg.png")
          }
        ]
      }
    },
    computed:{
      ...mapState({
        memberMsg:state => state.auth.memberMsg,
        advertisementinfo:state => state.auth.advertisementinfo,
      })
    },
    created() {
      this.$store.dispatch('getMyMessage',this.formData);
      this.$store.dispatch('getAdvertisementinfo',this.formData);
    },
    components: {
      ExplosiveRecommendation, //爆品推荐
      QuickBar
    }
  };
</script>

<style>
  .MineCenter,.MineCenterList{
    background:#262626;
  }
  .MineCenter .weui-panel__hd .weui-panel__hd:after{
  	border-bottom:none;
  }
  .wy-center-outer {
    background-image:url("../../assets/images/mine/img-hyzx-bj.png");
    background-size: 100%;
  }
  .weui-media-box_appmsg .weui-media-box__thumb{
      width:auto;
  }
  .weui-media-box_appmsg .weui-media-box__hd{
      margin: 0 auto;
  }
  #userHead{
    width: 1.8rem;
    height:1.2rem;
    position: relative;
  }
  .myText{position: absolute;top: 0.6rem;left: 0.3rem;z-index: 1;}
  @media screen and (max-width: 320px) {
      .myText {
          width:2rem;
      }
  }
  .weui-media-box:before{border-top:none;}
  #mycenterText h4,#mycenterText p,#username{
    color:#fff;
    text-align:center;
  }
  #mycenterText h4 #username{color:#fff; font-size:0.3rem;}
  #mycenterText h4 span{
    font-size:0.24rem;
    color:#808080;
  }
  .weui-media-box__bd p{
    font-size:0.24rem;
  }
  img.iconImg{
    width:0.32rem;
    vertical-align: middle;
  }
  img.myCode{width:0.32rem;vertical-align: bottom;}
  img.iconImgBig{ width:0.4rem;}
  span.myTel{padding-right: 0.44rem;}

  .weui-media-box__bd h4 .iconImg2,.weui-media-box__bd p img{padding: 0 0 0 0.1rem;}
  .weui-flex__item a{ text-decoration: underline;font-size:0.2rem;color:#808080;}

  a, a:link, a:active, a:visited, a:hover {
    text-decoration: none;
  }
  .weui-panel:before{border-top:none;}
  .weui-panel:after{
    border-bottom: 0.02rem soild #666;
  }
  .center-ordersModule {
      text-align: center;
      display: block;
      padding: 0.2rem 0;
      position: relative;
  }
  .center-ordersModule .imgicon {
      display: inline-block;
      height: 0.48rem;
      text-align: center;
      margin-bottom: 0.1rem;
  }
  .center-ordersModule .imgicon img {
      height: 0.64rem;
      width: auto;
  }
  a img {
      border: 0;
  }
  .center-ordersModule .name {
      font-size: 0.22rem;
      color: #808080;
  }
  .center-ordersModule {
      text-align: center;
      display: block;
      padding: 0.2rem 0;
      position: relative;
  }
  .threeGird{
    padding:0.08rem 0;
  }

  .MineCenter p.explosive{color:#a9a9a9;}
  .MineCenter span.weui-cell__ft{color: #4d4d4d;}
  .weui-cell_access .weui-cell__ft:after{border-color: #4d4d4d;}
  .weui-panel:after,.weui-panel__hd:after{border-bottom:none;}

  .MineCenter .centerBPTJ{
    background: #262626;
  }
  .scoreOuter{
    padding: 0.1rem 0.24rem 0 0.24rem;
  }
  .scoreOuter ul li img{
    width:100%;
  }
  .scoreThree ul{
    display: -webkit-flex; /* Safari */
    display: flex;
  }
  .scoreThree ul li{
    padding-right: 0.2rem;
  }
  .scoreThree ul li:last-child{
    padding-right: 0;
  }
</style>
