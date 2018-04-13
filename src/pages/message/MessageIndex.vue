<template>
  <div class="msg-index">
    <search placeholder="请输入资讯关键字" @click="toSearch"/>
    <swipe :autoplay="3000" style="margin:10px 12px;">
      <swipe-item v-for="swiper in swipperData" :key="swiper.targetID">
        <router-link :to="{ name: 'MsgDetail', params: {id:swiper.targetID }}">
          <img style="width: 100%;" src="../../assets/images/swiper.png"/>
        </router-link>
      </swipe-item>
    </swipe>
    <div class="htitle">
      <div class="titleZh">─ 发现生活 ─</div>
      <div class="titleEn">Discover Life</div>
    </div>

    <div class="msg-content" v-for="cateLine in categoryData">
      <div v-for="cate in cateLine" :class="cate.display==2?'msgL':'msgM'">
        <router-link :to="{ name: cate.display==1?'msgList':'MsgCXHD', params: { title: cate.title,id:cate.typeID }}">
          <img src="../../assets/images/msgL.png" v-if="cate.display==2"/>
          <img src="../../assets/images/msgM.png" v-if="cate.display==1"/>
        </router-link>
      </div>
    </div>
    <tool-bar></tool-bar>
  </div>
</template>
<script>
  import ToolBar from "../../components/ToolBar";
  import {Search, Swipe, SwipeItem} from 'vant'

  import {mapState} from "vuex"

  export default {
    data() {
      return {}
    },
    components: {
      ToolBar,
      Search,
      Swipe,
      SwipeItem
    },
    computed: {
      ...
        mapState({
          swipperData: state => state.message.swipperData,
          categoryData: state => state.message.categoryData
        })
    },
    methods:{
    	toSearch(){
    		this.$router.push({name:"Search"})
    	}
    }
  }
  ;
</script>
<style scoped>
  body * {
    background: none !important;
  }
	.msg-index{
		padding-bottom: 1.2rem;
	}
  .htitle {
    display: flex;
    flex-direction: column;
    height: 70px;
    align-items: center;
    text-align: center;
    justify-content: center;
  }

  .htitle .titleZh {
    font-size: 18px;
    font-weight: bold;
    color: #000;
  }

  .htitle .titleEn {
    font-size: 11px;
    color: #666;
  }

  .msg-content {
    margin: 0px 12px;
    display: flex;
    flex-direction: row;
  }

  .msg-content .line {
    flex-grow: 1;
    display: flex;
    flex-direction: row;
    margin-bottom: 5px;
  }

  .msg-content div {
    margin-right: 5px;
  }

  .msg-content div :last-child {
    margin-right: 0px;
  }

  .msg-content div .msgM {
    flex-grow: 1;
  }

  .msg-content div .msgL {
    flex-grow: 2;
  }

  img {
    height: 100%;
    width: 100%;
  }
</style>
