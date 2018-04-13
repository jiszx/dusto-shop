<template>
  <div id="app">
    <div class="weui-search-bar" id="searchBar" style="padding: 8px 60px;">
      <form class="weui-search-bar__form">
        <div class="weui-search-bar__box">
          <i class="weui-icon-search"></i>
          <router-link to="/home/Search">
            <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
          </router-link>
        </div>
      </form>
    </div>
    <tool-bar></tool-bar>
  </div>
</template>

<script>
  import ToolBar from "../../components/ToolBar";

  export default {components: {ToolBar}};
</script>

<style>
</style>
