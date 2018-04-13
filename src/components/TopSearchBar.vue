<!--
  封装一个顶部搜索条组件
  props:
    message-num: Number 消息数量
    goods-num: Number 已添加购物车物品数量

  events:
    wap-nav-click:点击展开侧边栏导航按钮

  methods:

-->

<template>
  <div class="component-top-search-bar fontsize-26">
    <div class="component-top-search-bar-inner">
      <!--侧边栏按钮-->
      <Icon name="wap-nav" @click="wapNavClick" class="wap-nav-btn"/>
      <!--搜索输入按钮-->
      <router-link class="top-search block" :to="{name:'Search'}">
        <Icon name="search" class="search-btn inline-block vertical-align-middle"/>
        <span class="inline-block search-btn-text vertical-align-middle">搜索</span>
      </router-link>
      <!--右侧消息图标和购物车图标-->
      <div class="top-right-bar-box">
        <div class="message-btn-wrapper inline-block">
          <router-link :to="{name:'Contact'}" class="top-search-bar-color">
            <div class="message-btn"><img src="../assets/icon/icon-kf.png"/></div>
            <!--<span class="num-badge">{{messageNum}}</span>-->
          </router-link>
        </div>
        <div class="shop-car-btn-wrapper inline-block">
          <router-link :to="{name:'Cart'}" class="top-search-bar-color">
            <Icon name="cart"/>
            <span class="num-badge" v-if="goodsNum">{{goodsNum}}</span>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {
    Icon,
  } from 'vant';

  export default {
    props: {
      messageNum: {
        type: Number,
        default: 0,
      },
      goodsNum: {
        type: Number,
        default: 0,
      },
    },
    data() {
      return {}
    },
    components: {
      Icon,
    },
    methods: {
      /**
       * 点击展开侧边栏导航按钮
       */
      wapNavClick() {
        this.$emit('wap-nav-click')
      },
    },
  }
</script>

<style scoped>
  .top-search-bar-color {
    color: #b3b3b3;
  }

  .component-top-search-bar {
    width: 100%;
    color: #b3b3b3;
  }

  .component-top-search-bar-inner {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    height: 1rem;
    padding: 0.21rem 1.4rem 0.21rem 0.6rem;

  }

  .wap-nav-btn {
    position: absolute;
    top: 0;
    left: 0;
    height: 1rem;
    width: 0.4rem;
    font-size: 0.45rem;
    line-height: 1rem;
  }

  .top-search {
    line-height: 0.52rem;
    background: #f7f7f7;
    padding-left: 0.3rem;
  }

  .search-btn {
    font-size: 0.4rem;
    vertical-align: middle;
  }

  .search-btn-text {
    font-size: 0.26rem;
    margin-left: 0.16rem;
  }

  .top-right-bar-box {
    position: absolute;
    top: 0;
    right: 0;
    width: 1.2rem;
    height: 100%;
    text-align: right;
  }

  .message-btn-wrapper,
  .shop-car-btn-wrapper {
    width: 0.4rem;
    font-size: 0.45rem;
    color: #b3b3b3;
    position: relative;
  }

  .shop-car-btn-wrapper {
    margin-left: 0.3rem;
    display: inline-block;
    vertical-align: middle;
  }

  .top-search {
    color: #b3b3b3;
  }

  .num-badge {
    display: inline-block;
    width: 0.3rem;
    height: 0.3rem;
    position: absolute;
    right: -0.2rem;
    top: 0;
    z-index: 2;
    background-color: #ea5361;
    border-radius: 50%;
    font-size: 10px;
    text-align: center;
    line-height: 0.3rem;
    color: #fff;
  }
  img{
    display: block;
    width: 100%;
  }
  .message-btn{
    display: inline-block;
    width: 0.56rem;
    vertical-align: middle;
  }
</style>
