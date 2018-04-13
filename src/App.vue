<template>
  <div class="page-app">
    <!--页面路由切换区-->
    <transition name="router-fade" mode="out-in" v-if="$route.meta.keepAlive">
      <keep-alive>
        <router-view v-if="$route.meta.keepAlive" class="router-keep-alive"></router-view>
      </keep-alive>
    </transition>
    <transition name="router-fade" mode="out-in" v-if="!$route.meta.keepAlive">
      <router-view class="router-no-alive"></router-view>
    </transition>
    <!--公共元素-->
    <div class="common-doms">
      <QuickBar  v-if="$route.meta.showQuickBar" />
    </div>
  </div>
</template>

<script>
  import { Toast } from 'vant';
  import QuickBar from "components/QuickBar";
  import {mapState} from 'vuex'
  export default {
    name: "app",
    data(){
      return{}
    },
    computed:{
      ...mapState([
        'messageTips',
      ]),
      showMessageTips(){
        return this.messageTips.isShow;
      },
    },
    mounted(){

    },
    watch:{
      showMessageTips(current,old){
        if(current){
          Toast({
            type:this.messageTips.type,
            message:this.messageTips.text,
            forbidClick:true,
            duration:1500,
          });
          this.$store.commit('hideMsgTips')
        }
      },
    },
    components:{
      QuickBar,
    },
    methods:{
    },
  };
</script>

<style>
  @import './assets/css/weui.min.css';
  @import './assets/css/style.css';

  #app {
    height: 100%;
  }
</style>
