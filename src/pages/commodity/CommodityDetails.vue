<template>
  <div class="page-product-detail">
    <div>
      <ProductInfo
        :data="productData"
        :active="activeColorId"
        @select-color="selectColor"
        @share="share"
      />
    </div>
    <!--优惠券-->
    <div class="select-tpye-tab-wrapper  van-hairline--top">
      <div class="select-tpye-tab  van-hairline--top-bottom">
        <div class="tab-text">
          <span class="tab-text-label inline-block fontsize-28">领券：</span>
          <span v-for="n in productData.coupon" class="youhuiuan-icon"></span>
        </div>
        <div class="tab-text-btn">
          <router-link :to="{name:'Tickets',query:{productID:productData.productID,fromRouteName:'CommodityDetails'}}">
            <p class="tab-text-btn-inner fontsize-24">共{{productData.coupon}}张 &gt;</p>
          </router-link>
        </div>
      </div>
    </div>
    <!--积分-->
    <div class="select-tpye-tab-wrapper">
      <div class="select-tpye-tab">
        <div class="tab-text fontsize-28">
          <span class="tab-text-label inline-block">促销：积分兑换</span>
        </div>
      </div>
    </div>
    <!--评价-->
    <div class="select-tpye-tab-wrapper  van-hairline--top">
      <div class="select-tpye-tab  van-hairline--top-bottom">
        <div class="tab-text">
          <span class="tab-text-label inline-block fontsize-28">评价：</span>
          <span class="gray commont-text-title-num fontsize-20">(偏大10) (正码80) (偏小2)</span>
        </div>
        <div class="tab-text-btn">
          <router-link :to="{name:'CommodityEvaluate'}">
            <p class="tab-text-btn-inner fontsize-24">查看全部 &gt;</p>
          </router-link>
        </div>
      </div>
    </div>

    <div class="commont-list-wrapper">
      <div
        class="commont-item"
        v-for="(item, index) in commonList"
        :key="index"
        :class="{'van-hairline--bottom':index!==(commonList.length-1)}">
        <Comment :data="item" />
      </div>
    </div>

    <div class="buy-now-btn-wrapper">
      <BuyNowPopups
        :product-data="productData"
        :active-color-id="activeColorId"
        :type="type"
        @buy-now="buyNow"
        @add-car="addCar"
        @confirm-click="confirmClick"
      />
    </div>

  </div>
</template>

<script>
	import { mapMutations } from 'vuex'
  import ProductInfo from './subpage/product-info.vue'

  import Comment from '@/components/comment'
  import BuyNowPopups from '@/components/BuyNowPopups'

  import { productDetail } from 'api/productrs'
  import wxMixins from 'mixins/weixin'
  export default {
	  mixins:[wxMixins],
    data(){
      return{
        type:'buy',
        productData:{
          artNo: "P2150200138722S",
          productID:123,
          title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
          price:99.00,
          old_price:199.00,
          Integral:500,
          swiperData:[
            require("./images/banner-zx-3.png"),
            require("./images/banner-zx-4.png"),
            require("./images/banner-zx-5.png"),
          ],
          colorType:[{
            id:1,
            sizeList:[
              {size:34,num:300},
              {size:35,num:300},
              {size:36,num:300},
              {size:37,num:300},
              {size:38,num:300},
              {size:39,num:300},
            ],
            text:"黑色",
            image:require("./images/banner-zx-4.png"),
          },{
            id:2,
            sizeList:[
              {size:34,num:300},
              {size:35,num:300},
              {size:36,num:300},
              {size:37,num:300},
              {size:38,num:300},
              {size:39,num:300},
            ],
            text:"黑色",
            image:require("./images/banner-zx-4.png"),
          },{
            id:3,
            sizeList:[
              {size:34,num:300},
              {size:35,num:300},
              {size:36,num:300},
              {size:37,num:300},
              {size:38,num:300},
              {size:39,num:300},
            ],
            text:"黑色",
            image:require("./images/banner-zx-4.png"),
          }],
          coupon:3,
          size:[34,35,36,37,38,39,40]
        },
        activeColorId:1,
        commonList:[{
          userid:1,
          userHead:require("./images/banner-zx-4.png"),
          username:'王**',
          comment:'鞋子不错，物超所值，高端大气，上档次，推荐购买。',
          colorType:'灰色',
          size:38,
          buyDate:'2018-01-10',
          commontDate:'2018-01-25',
        },{
          userid:1,
          userHead:require("./images/banner-zx-4.png"),
          username:'王**',
          comment:'鞋子不错，物超所值，高端大气，上档次，推荐购买。',
          colorType:'灰色',
          size:38,
          buyDate:'2018-01-10',
          commontDate:'2018-01-25',
          images:[require("./images/banner-zx-4.png"),require("./images/banner-zx-4.png")]
        }]
      }
    },
    components:{
      ProductInfo,
      Comment,
      BuyNowPopups,
    },
    created(){
      this.type = this.$route.query.type||'buy';
    },
    methods:{
      /**
       * 选择颜色
       * @param colorId
       */
      selectColor(colorId){
        this.activeColorId=colorId;
      },
      /**
       * 点击立即购买事件
       */
      buyNow(){
        this.$router.push({name:'FirmOrder'})
      },
      /**
       * 点击加入购物车事件
       */
      addCar(data){
        console.log(data);
        this.$store.dispatch("addToShoppingCart",data);
        this.$router.push({name:'Cart'})

      },
      /**
       * 点击确认按钮事件
       */
      confirmClick(){

      },
      /**
       * share分享
       */
      share(){
        this.wxShard();
      },
    },
  };
</script>

<style scoped>
  .page-product-detail{
    position: relative;
    padding-bottom: 1rem;
  }
  .product-swipe-wrapper{
    box-sizing: border-box;
    width: 100%;
    padding-top: 100%;
    position: relative;
  }
  .product-swipe-wrapper-inner{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
  .swiper-box-inner{
    width: 100%;
    height: 100%;
  }
  .swiper-box-inner img{
    display: block;
    width: 100%;
    height: 100%;
  }
  .select-tpye-tab-wrapper{
    box-sizing: border-box;
    height: 0.76rem;
    line-height: 0.76rem;
    padding: 0 0.3rem;
  }
  .tab-text{
    display: inline-block;
  }
  .youhuiuan-icon{
    display: inline-block;
    vertical-align: middle;
    width: 0.64rem;
    height: 0.4rem;
    background: url(../../assets/icon/img-yhq.png) no-repeat center center;
    background-size: cover;
    margin-right: 0.26rem;
  }
  .youhuiuan-icon:nth-of-type(1){

  }
  .tab-text-btn{
    display: inline-block;
    float: right;
  }
  .tab-text-btn-inner{
    color:#999;
  }
  .commont-text-title-num{

  }

  /*评论*/
  .commont-list-wrapper{
    padding: 0 0.3rem;
  }
  .commont-item{

  }

  .buy-now-btn-wrapper{
    position: fixed;
    width: 100%;
    max-width: 750px;
    height: 1rem;
    left: 0;
    bottom: 0;
    z-index: 100;
  }
</style>

