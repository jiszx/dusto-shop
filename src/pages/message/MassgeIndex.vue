<template>
  <div>
    <div class="weui-search-bar" id="searchBar" style="padding: 8px 60px;">
      <search placeholder="请输入商品名称"/>
    </div>
    <swipe :autoplay="3000">
      <swipe-item>1</swipe-item>
      <swipe-item>2</swipe-item>
      <swipe-item>3</swipe-item>
      <swipe-item>4</swipe-item>
    </swipe>
    <tool-bar></tool-bar>
  </div>
</template>
<script>
  import ToolBar from "../../components/ToolBar";
  import {Search, Swipe, SwipeItem} from 'vant'

  export default {
    components: {
      ToolBar,
      Search,
      Swipe,
      SwipeItem
    }
  };
</script>
<style>
  .lim_float_icon {
    display: none;
  }
</style>
