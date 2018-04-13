<template>
  <div class="page-product-list">
    <!--商品列表-->
    <div class="product-list-wrapper">
      <div class="product-filtrate-wrapper">
        <div class=" van-hairline--bottom">
          <ul class="product-filtrate-wrapper-ul fontsize-32 clearfix">
            <li class="van-hairline--right" :class="{'active':activeTab===1}" @click="filtrate(1,'type')">
              <p>
                综合
              </p>
            </li>
            <li class="van-hairline--right" :class="{'active':activeTab===2}" @click="filtrate(2,'type')">
              <p>
                销量
                <span class="filtrate-arrow-wrapper">
                  <span class="filtrate-arrow-btn filtrate-arrow-up active"></span>
                  <span class="filtrate-arrow-btn filtrate-arrow-down"></span>
                </span>
              </p>
            </li>
            <li class="van-hairline--right" :class="{'active':activeTab===3}" @click="filtrate(3,'type')">
              <p>
                新品
                <span class="filtrate-arrow-wrapper">
                  <span class="filtrate-arrow-btn filtrate-arrow-up"></span>
                  <span class="filtrate-arrow-btn filtrate-arrow-down"></span>
                </span>
              </p>
            </li>
            <li class="van-hairline--right" :class="{'active':activeTab===4}" @click="filtrate(4,'type')">
              <p>
                价格
                <span class="filtrate-arrow-wrapper">
                  <span class="filtrate-arrow-btn filtrate-arrow-up"></span>
                  <span class="filtrate-arrow-btn filtrate-arrow-down"></span>
                </span>
              </p>
            </li>
          </ul>
          <ul class="product-filtrate-btn-ul fontsize-32">
            <li class="van-hairline--right switch-exhibition">
              <p @click="switchExhibition" class="" >
                <span class="inline-block switch-exhibition-btn1" v-if="isListExhibition"></span>
                <span class="inline-block switch-exhibition-btn2" v-else></span>
              </p>
            </li>
            <li class="switch-exhibition">
              <p @click="openFiltratePopup" class="" >
                筛选
                <span class="inline-block switch-exhibition-btn3"></span>
              </p>
            </li>
          </ul>
        </div>
      </div>
      <div class="product-list clearfix" :class="[isListExhibition?'isListExhibition':'']">
        <div
          v-for="(item, index) in goodsList"
          class="product-item"
          :class="[!(index===goodsList.length-1)&&isListExhibition?'van-hairline--bottom':'']"
          @click="goToProductDetail(item)">
          <GoodsCard
            show-like-btn
            :type="isListExhibition?'list':'card'"
            :id = '1'
            :image="item.image"
            :title = "item.title"
            :price = "item.price"
            :old_price = "item.old_price"
            :liked = "item.liked"
            @like-click="toggleLikeClick(item)"
          />
        </div>
      </div>
    </div>

    <!--加载更多-->
    <div class="loading-more-box">
      <p class="loading-more" v-if="!hasMore">没有更多</p>
      <LoadingMore v-else :loading-fn="loadingMore" :loading="loading"/>
    </div>

    <!--筛选弹层-->
    <FiltratePopup :filtrateShow="filtrateShow" @close="closeFiltrate"></FiltratePopup>
  </div>
</template>

<script>
  import SearchBtn from '@/components/SearchBtn'
  import GoodsCard from '@/components/goods-card'
  import LoadingMore from '@/components/base/loading-more'
  import FiltratePopup from 'components/FiltratePopup';
  export default {
    data(){
      return{
        specialOffersImage:require('@/assets/images/img-hd-yhq.png'),
        isListExhibition:false,
        activeTab:1,
        filtrateShow:false,
        goodsList:[{
          liked:true,
          image:require('./images/banner-zx-4.png'),
          title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
          price:99.00,
          old_price:199.00
        },{
          liked:true,
          image:require('./images/banner-zx-4.png'),
          title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
          price:99.00,
          old_price:199.00
        },{
          liked:false,
          image:require('./images/banner-zx-4.png'),
          title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
          price:99.00,
          old_price:199.00
        },{
          liked:false,
          image:require('./images/banner-zx-4.png'),
          title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
          price:99.00,
          old_price:199.00
        },{
          liked:false,
          image:require('./images/banner-zx-4.png'),
          title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
          price:99.00,
          old_price:199.00
        },{
          liked:false,
          image:require('./images/banner-zx-4.png'),
          title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
          price:99.00,
          old_price:199.00
        }],
        hasMore:true,
        loading:false,
        uuid:'',
        type:'',
        selectedGoods:[],
      }
    },
    created(){
      this.uuid = this.$route.query.uuid;
      this.type = this.$route.query.type;
      this.selectedGoods=this.$route.query.selectedGoods;
    },
    components:{
      SearchBtn,
      GoodsCard,
      LoadingMore,
      FiltratePopup,
    },
    methods:{
      /**
       *切换商品列表展示形式
       */
      switchExhibition(){
        this.isListExhibition=!this.isListExhibition;
      },
      /**
       * 点击筛选排序按钮
       * @param num 点击第几个
       * @param type 类型
       */
      filtrate(num,type){
        //设置当前tabs按钮为选中状态
        this.activeTab=num;
        //to do 筛选排序

      },
      /**
       * 商品喜欢收藏按钮被点击时触发
       * @param item 被点击时商品数据对象
       */
      toggleLikeClick(item){
        //切换收藏状态
        item.liked=!item.liked;
        //todo 向后台发送收藏或取消收藏请求
      },
      /**
       * 加载更多
       */
      loadingMore(){
        //todo 加载更多代码
        //模拟加载更多
        this.loading=true;
        var timer = setTimeout(()=>{
          clearTimeout(timer);
          this.goodsList = this.goodsList.concat(this.goodsList);
          this.loading=false;
        },1500)
      },
      /**
       * 点击赠品将赠品添加到相应订单中，跳转到购物车页面
       * @param item
       */
      goToProductDetail(item){
        //todo 伪造数据展示、后续从后台接口返回数据，此处需要商品详情的信息
        let eventType = this.type==='orderPresent'? 'addCartOrderPresent':'addPresentByUuid';

        this.$store.dispatch(eventType,{
          uuid:this.uuid,
          data:{
            color:{
              id:1,
              image:require("../commodity/images/banner-zx-4.png"),
              sizeList:[
                {size:34,num:300},
                {size:35,num:300},
                {size:36,num:300},
                {size:37,num:300},
                {size:38,num:300},
                {size:39,num:300},
              ],
              text:'黑色',
            },
            num:1,
            productData:{
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
            size:37
          }
        });
        this.$router.replace({name:'Cart',query:{selectedGoods:this.selectedGoods}})
      },
      /**
       * 点击筛选按钮
       */
      openFiltratePopup(){
        this.filtrateShow=true;
      },
      /**
       * 关闭筛选弹层closeFiltrate
       */
      closeFiltrate(){
        this.filtrateShow=false;
      },
    },
  };
</script>

<style>
  .special-offers-wrapper{
    box-sizing: border-box;
    /*height: 1.8rem;*/
    padding: 0.2rem 0.24rem;
  }
  .special-offers-wrapper img{
    display: block;
    width: 100%;
  }


  .product-filtrate-wrapper{
    background-color: #f7f7f7;
    /*padding: 0 0.4rem;*/
    position: relative;
  }
  .product-filtrate-wrapper>div{
    box-sizing: border-box;
    position: relative;
    width: 100%;
    height: 100%;
    padding-right: 2.3rem;
    line-height: 0.76rem;
    background-color: #f7f7f7;
  }
  .product-filtrate-wrapper-ul{

  }
  .product-filtrate-wrapper-ul>li{
    float: left;
    box-sizing: border-box;
    width: 25%;
    text-align: center;
    background-color: #f7f7f7;
  }
  .product-filtrate-wrapper-ul>li.active{
    color: #f50;
  }
  .switch-exhibition{
    /*position: absolute;*/
    /*right: 0.3rem;*/
    /*top: 0;*/
    /*height: 0.76rem;*/
    /*width: 1rem;*/
    text-align: center;
  }
  .product-filtrate-btn-ul{
    position: absolute;
    top: 0;
    right: 0;
    height: 0.76rem;
    width: 2.3rem;
  }
  .product-filtrate-btn-ul>li{
    float: left;
    box-sizing: border-box;
    text-align: center;
    background-color: #f7f7f7;
  }
  .product-filtrate-btn-ul>li:first-child{
    width: 0.8rem;
  }
  .product-filtrate-btn-ul>li:last-child{
    width: 1.5rem;
  }


  .switch-exhibition>p>span{
    width: 0.4rem;
    height: 0.4rem;
    vertical-align: middle;
  }
  .switch-exhibition-btn1{
    background: url(../../assets/icon/icon-lieb.png) no-repeat center center;
    background-size: cover;
  }
  .switch-exhibition-btn2{
    background: url(../../assets/icon/icon-fenl.png) no-repeat center center;
    background-size: cover;
  }
  .switch-exhibition-btn3{
    width: 0.4rem;
    height: 0.4rem;
    vertical-align: middle;
    background: url(../../assets/icon/shaixuan.png) no-repeat center center;
    background-size: cover;
  }

  .product-list{
    padding: 0 0.32rem;
    box-sizing: border-box;
  }
  .product-list .product-item{
    float: left;
    box-sizing: border-box;
    width: 50%;
    padding-bottom: 0.2666666rem;
    padding-top: 0.2666666rem;
  }
  .product-list .product-item:nth-of-type(2n+1){
    padding-right: 0.1466666rem;
  }
  .product-list .product-item:nth-of-type(2n+2){
    padding-left: 0.1466666rem;
  }
  .product-list.isListExhibition .product-item{
    float: none;
    width: 100%;
  }

  .product-list.isListExhibition .product-item:nth-of-type(2n+1){
    padding-right: 0;
  }
  .product-list.isListExhibition .product-item:nth-of-type(2n+2){
    padding-left: 0;
  }

  /*快速导航*/
  .fast-btn-wrapper{
    position: fixed;
    top: 50%;
    right: 0;
    z-index: 100;
  }


  .filtrate-arrow-wrapper{
    display: inline-block;
    vertical-align: middle;
    width: 0.2rem;
    height: 0.76rem;
  }
  .filtrate-arrow-wrapper .filtrate-arrow-btn{
    display: block;
    box-sizing: border-box;
    border-width:  0.1rem;
  }
  .filtrate-arrow-up{
    border-style: dashed dashed solid dashed;
    border-color: transparent transparent #999 transparent;
    border-top: none;
    margin-bottom: 0.1rem;
    margin-top: 0.19rem;
  }
  .filtrate-arrow-up.active{
    border-color: transparent transparent #ea5361 transparent;
  }
  .filtrate-arrow-down{
    border-style: dashed dashed solid dashed;
    border-color: #999 transparent transparent transparent;
    border-bottom: none;
  }
  .filtrate-arrow-down.active{
    border-color: #ea5361 transparent transparent transparent;
  }
</style>
