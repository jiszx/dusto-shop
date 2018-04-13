/**
  封装一个展示产品详情组件
  props:
    data: Object 产品数据
    active: Number 当前选中的color id
  events:
    select-color: 点击选择颜色 参数：当前color id


*/
<template>
  <div class="subpage-product-info">
    <div class="product-swipe-wrapper">
      <div class="product-swipe-wrapper-inner">
        <!--轮播-->
        <div class="swiper-box">
          <Swipe :autoplay="3000">
            <SwipeItem v-for="(image, index) in data.swiperData" :key="index">
              <div class="swiper-box-inner">
                <img :src="image" />
              </div>
            </SwipeItem>
          </Swipe>
        </div>
        <!--分享收藏按钮-->
        <div class="share-liked-btn">
          <div class="share-btn-wrapper" @click.prevent="share">
            <div class="share-btn"></div>
          </div>
          <div class="like-btn-wrapper" @click.prevent="switchLike">
            <div class="like-btn" :class="[data.liked?'like':'dislike']"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="info-text-wrapper text-center fontsize-28">
      <p class="title">{{data.title}}</p>
    </div>
    <div>
      <span class="red inline-block marginR5  fontsize-28 fontBold">￥{{data.price}}</span>
      <span class="gray inline-block line-through old_price fontsize-24" v-if="data.old_price">￥{{data.old_price}}</span>
    </div>
    <div v-if="data.Integral" class="paddingT10 paddingB20">
      <p class="red fontsize-24 fontBold">{{data.Integral}}积分可兑换</p>
    </div>
    <div>
      <ul class="clearfix color-type-list paddingB10">
        <li
          v-for="(item,index) in data.colorType"
          :key="index"
          :class="{active:active===item.id}"
          @click="colorTypeSelect(item.id)">
          <img :src="item.image" alt="">
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import {
    Swipe,
    SwipeItem,
  } from 'vant';
  export default {
    props:{
      data:{
          type:Object,
      },
      active:{
            type:Number,
            default:0,
      },
    },
    data(){
      return{

      }
    },
    components:{
      Swipe,
      SwipeItem,
    },
    methods:{
      /**
       * 选择颜色样式
       * @param id
       */
      colorTypeSelect(id){
          this.$emit('select-color',id);
      },
      /**
       * 分享
       */
      share(){
        this.$emit('share')
      },
      /**
       * 爱心按钮被点击，向上抛一个事件
       */
      switchLike(event){
        event.stopPropagation();
        this.$emit('like-click');
      },
    },
  };
</script>

<style scoped>
  .subpage-product-info{
    text-align: center;
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
  .info-text-wrapper{
    padding: 0.3rem 0.3rem 0.1rem;
    line-height: 1.4;
  }
  ul.color-type-list{
    text-align: center;
  }
  ul.color-type-list>li{
    box-sizing: border-box;
    display: inline-block;
    width: 1rem;
    height: 1rem;
    margin: 0 0.1rem;
    background: #f7f7f7;
  }
  ul.color-type-list>li.active{
    border: 1px solid #999;
  }
  ul.color-type-list>li img{
    display: block;
    width: 100%;
    height: 100%;
  }

  /*按钮*/
  .share-liked-btn{
    position: absolute;
    bottom: 0.4rem;
    right: 0.4rem;
  }
  .like-btn-wrapper{
    position: relative;
    width: 0.533333rem;
    height: 0.533333rem;
  }
  .like-btn{
    width: 100%;
    height: 100%;
    background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
  }
  .like-btn.like{
    background-image: url(../../../assets/icon/icon-sc-dj.png);
    animation-name: scale-spring;
    animation-duration: .28s;
    animation-fill-mode: both;
  }
  .like-btn.dislike{
    background-image: url(../../../assets/icon/icon-sc-wdj.png);
  }
  .share-btn{
    position: relative;
    width: 0.533333rem;
    height: 0.533333rem;
    margin-bottom: 0.32rem;
    background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
    background-image: url(../../../assets/icon/icon-fenxian.png);
  }
</style>
