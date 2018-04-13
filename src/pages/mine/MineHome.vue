<template>
  <div id="app" class="MineHome">
    <div class="wy-center-topNew">
        <div class="weui-media-box weui-media-box_appmsg">
          <div class="weui-media-box__hd">
            <img class="weui-media-box__thumb" :src="memberMsg.memberImg" alt="">
          </div>
          <div class="weui-media-box__bd myHomeMsg">
            <h4 class="weui-media-box__title">
              <img class="weui-media-box__thumb iconImg" src="../../assets/images/mine/img-jphy.png" alt="">
              <span>{{memberMsg.memberName}}
                <span>{{memberMsg.phone}}</span>
              </span>
              <router-link to="/mine/MyProfile">
                <img class="weui-media-box__thumb  iconImg iconImg2" src="../../assets/images/mine/icon-wd-bianji-h.png" alt="">
              </router-link>
            </h4>
            <p class="user-grade">会员积分：{{memberMsg.point}}</p>
            <p class="user-integral">我的二维码
              <router-link to="/mine/MineQR">
                <img class="center-list-icon  iconImg" src="../../assets/images/mine/icon-wdewm.png" alt="">
              </router-link>
            </p>
          </div>
        </div>

        <div class="xx-menu weui-flex" v-if="memberMsg.perfectState===1 ? false : true">
          <div class="weui-flex__item">
            <router-link to="/mine/MyProfile">
              <a>完善信息，领取优惠券><span v-if="perfectState===0">{{memberMsg.perfectState}}</span></a>
            </router-link>
          </div>
        </div>
        <div class="myCard">
          <img class="center-list-icon  iconImg iconImg4" src="../../assets/images/mine/img-wdhyk.png" alt="">
          <a  href="javascript:void(0);" @click="goMineCenter">我的会员卡></a>
        </div>
    </div>

    <div class="weui-panel weui-panel_access">
      <div class="weui-panel__bd">
        <div class="weui-flex threeGird">
          <div class="weui-flex__item">
            <router-link :to="{name:'CloakRoom'}" class="center-ordersModule">
              <div class="imgicon"><img src="../../assets/images/mine/icon-ymj.png"></div>
              <div class="name">我的衣帽间</div>
            </router-link>
          </div>
          <div class="weui-flex__item">
            <router-link :to="{name:'Cart'}" tag="a" class="center-ordersModule">
              <span class="weui-badge" style="position: absolute;top:5px;right:30px; font-size:10px;" v-if="shoppingCartNum">{{shoppingCartNum}}</span>
              <div class="imgicon"><img src="../../assets/images/mine/icon-wd-gwc.png"></div>
              <div class="name">购物车</div>
            </router-link>
          </div>
          <div class="weui-flex__item">
            <router-link :to="{name:'WishList'}" tag="a" class="center-ordersModule">
              <div class="imgicon"><img src="../../assets/images/mine/icon-wd-xxd.png"></div>
              <div class="name">心愿单</div>
            </router-link>
          </div>
          <div class="weui-flex__item">
            <router-link :to="{name:'CustomerService'}" tag="a" class="center-ordersModule">
              <div class="imgicon"><img src="../../assets/images/mine/icon-wd-sh.png"></div>
              <div class="name">售后</div>
            </router-link>
          </div>
      </div>
    </div>
    </div>

    <div class="weui-panel weui-panel_access">
      <div class="weui-panel__hd">
        <a class="weui-cell weui-cell_access center-alloder" href="javascript:void(0);">
          <div class="weui-cell__bd wy-cell">
            <div class="weui-cell__bd weui-cell_primary"><p class="center-list-txt">我的订单</p></div>
          </div>
          <router-link :to="{name:'OrderList'}" tag="span" class="weui-cell__ft">查看更多</router-link>
        </a>
      </div>
      <div class="weui-panel__bd">
         <div class="weui-flex threeGird">
            <div class="weui-flex__item" v-for="(item,index) in itemsFirst" :key="item.name">
              <router-link :to="{name:item.toName,query:{type:item.type}}" class="center-ordersModule">
                <div class="imgicon"><img :src="item.url"></div>
                <div class="name">{{item.name}}</div>
              </router-link>
            </div>
        </div>

        <div class="weui-flex threeGird">
            <div class="weui-flex__item" v-for="(item,index) in itemsSecond" :key="item.name">
              <router-link :to="{name:item.toName}" class="center-ordersModule">
                <div class="imgicon"><img :src="item.url"></div>
                <div class="name">{{item.name}}</div>
              </router-link>
            </div>
        </div>
      </div>
    </div>
    <explosive-recommendation></explosive-recommendation>
    <div class="bottomS"></div>
    <tool-bar></tool-bar>
  </div>
</template>

<script>
  import ExplosiveRecommendation from "components/ExplosiveRecommendation";
  import ToolBar from "../../components/ToolBar";
  import {mapState,mapGetters} from "vuex"
  export default {
    data() {
      return {
        perfectState:1,//0未完善信息，1为完善信息
        formData:{
          memberNo:'25000105079',
          phone:'18200000000',
        },
        itemsFirst: [
          {
            name: "待付款",
            toName: "OrderList",
            type:1,
            url:require("../../assets/images/mine/icon-dfk.png")
          },
          {
            name: "待发货",
            toName: "OrderList",
            type:2,
            url: require("../../assets/images/mine/icon-dfh.png")
          },
          {
            name: "待收货",
            toName: "OrderList",
            type:3,
            url: require("../../assets/images/mine/icon-dsh.png")
          },
          {
            name: "线下订单",
            toName: "Mine",
            url: require("../../assets/images/mine/icon-mend-dj.png")
          },
        ],
        itemsSecond: [
          {
            name: "地址簿",
            toName: "MyAddress",
            url:require("../../assets/images/mine/icon-dzb.png")
          },
          {
            name: "卡券包",
            toName: "MySaleCard",
            url: require("../../assets/images/mine/icon-kjb.png")
          },
          {
            name: "足型信息",
            toName: "MyFoots",
            url: require("../../assets/images/mine/icon-zxxx.png")
          },
          {
            name: "评价",
            toName: "MyEvaluateList",
            url: require("../../assets/images/mine/icon-pj.png")
          },
        ]
      }
    },
    computed:{
      ...mapState({
        memberMsg:state => state.auth.memberMsg,
      }),
      ...mapGetters({
        shoppingCartNum: 'getShoppingCartNum',
      })
    },
    created(){
      console.log(this.memberMsg)
      this.$store.dispatch('getMyMessage',this.formData)
    },
    methods:{
      goMineCenter:function(){
        this.$router.push({path:'/mine/MineCenter'})
      },
    },
    components: {
      ExplosiveRecommendation,//爆品推荐
      ToolBar
    }
  };
</script>

<style>
  .MineHome{
    background:#fff;
  }
  .wy-center-topNew {
    background-color: #333333;
  }
  .weui-media-box_appmsg .weui-media-box__thumb{
      width:auto;
  }3
  .weui-media-box__bd h4 span,.weui-media-box__bd p{
    color:#fff;
    text-align:left;
  }
  .weui-media-box__bd h4 span{
    font-size:0.3rem;
  }
  .weui-media-box__bd p{
    font-size:0.24rem;
  }
  .weui-media-box_appmsg img.iconImg{
    width:0.32rem;
    vertical-align: middle;
  }
  .weui-media-box__bd h4 .iconImg2,.weui-media-box__bd p img{padding: 0 0 0 0.1rem;}

  .weui-flex__item a{ text-decoration: underline;font-size:0.24rem;color:#8e8e8e;}
  div.myCard{position: relative;}
  .myCard .iconImg4{
    position: absolute;
    right: 0;
    bottom: 0;
    width: 3rem;
  }
  .myCard a{
    position: absolute;
    right: 0.3rem;
    bottom: 0.12rem;
    text-decoration: underline!important;
    color:#ffd7eb;
  }

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
      height: 0.48rem;
      width: auto;
  }
  a img {
      border: 0;
  }
  .center-ordersModule .name {
      font-size: 0.22rem;
      color: #4d4d4d;
  }
  .center-ordersModule {
      text-align: center;
      display: block;
      padding: 0.2rem 0;
      position: relative;
  }
  .threeGird{
    padding: 0.08rem 0;
  }
  .bottomS{
    height:1.08rem;
  }
  .MineHome .myHomeMsg{
    color:#fff;
  }
  .explosiveMyHome{

  }
</style>
