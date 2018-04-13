<template>
  <div class="page-message-cxhd">
    <div class="message-cxhd-tab fontsize-32 clearfix">
      <router-link  class="tab-item pull-left" :to="{name:'MsgCXHDList'}" replace :class="{active:$route.name=='MsgCXHDList'}">优惠活动</router-link>
      <router-link class="tab-item pull-right" :to="{name:'MsgCXHDTickets'}" replace :class="{active:$route.name=='MsgCXHDTickets'}">优惠券</router-link>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    data(){
      return{}
    },
    methods: {

    }
  };
</script>

<style scoped>
  .page-message-cxhd{
    padding-top: 0.2rem;
  }
  .cxhd-wrapper {
    padding: 5px 12px;
  }

  .cxhd-wrapper img {
    width: 100%;
  }

  .message-cxhd-tab{
    height: 0.88rem;
    width: 64%;
    margin: 0 auto;
  }
  .message-cxhd-tab .tab-item{
    display: inline-block;
    border-bottom: 2px solid transparent;
    color: #1a1a1a;
    height: 0.64rem;
  }
  .message-cxhd-tab .tab-item.active{
    color: #ea5361;
    border-color: #ea5361;
  }
</style>
