<template>
  <div>
    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg line-one-px">
      <div class="weui-media-box__hd">
        <img class="weui-media-box__thumb" src="../../../assets/images/goods/banner-zx-6.png" alt="">
      </div>
      <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">客服二</h4>
        <p class="weui-media-box__desc">好的，欢迎下次再来。</p>
      </div>
    </a>
  </div>
</template>

<script>
  export default {
    watch: {}
  }
</script>

<style scoped>
  @import url("../../../assets/css/list.css");

  .weui-media-box {
    padding: 0.2rem 0.24rem;
  }

  .weui-media-box::before {
    border: none;
  }

  .line-one-px {
    position: relative;
  }

  .line-one-px:last-child::after {
    border: none;
  }

  .line-one-px::after {
    left: 0.24rem;
    bottom: 0;
    top: auto;
  }

  /*图片文字*/
  .weui-media-box_appmsg .weui-media-box__hd {
    width: 0.8rem;
    height: 0.8rem;
    margin-right: 0.2rem;
    border-radius: 0.1rem;
    overflow: hidden;
  }

  .my-msg .weui-media-box__title {
    font-size: 0.32rem;
    color: #000;
    line-height: 0.4rem;
  }

  .my-msg .weui-media-box__desc {
    padding-top: 0.08rem;
    font-size: 0.28rem;
    -webkit-line-clamp: 1;
  }

</style>
