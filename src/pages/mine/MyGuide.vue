<template>
  <div id="app">
    我的专属导购
    <tool-bar></tool-bar>
  </div>
</template>

<script>
  import ToolBar from "../../components/ToolBar";
  export default {components: {ToolBar}};
</script>

<style>
  body {
    background-color: #f8f8f8;
  }

  .van-cell__title .van-icon {
    font-size: 18px;
  }
</style>
