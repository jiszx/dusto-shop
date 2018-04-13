<template>
  <div class="page-home">
    <!--顶部导航-->
    <div class="top-header">
      <TopSearchBar
        @wap-nav-click="sidebarOpen"
      :message-num="2"
      :goods-num="shoppingCartNum" />
    </div>
    <!--内容区-->
    <div class="page-min">
      <!--tabs导航-->
      <div class="tabs-nav fontsize-32">
        <Tabs @click="_goToPosition" :active="activeTab">
          <Tab v-for="(item,index) in tabNavData" :key="index" :title="item.title"></Tab>
        </Tabs>
      </div>
      <!--轮播-->
      <div class="swiper-box">
        <Swipe :autoplay="3000">
          <SwipeItem v-for="(image, index) in swiperData" :key="index">
            <router-link :to="{name:'CXHDDetail'}">
              <div class="swiper-box-inner">
                <img :src="image" />
              </div>
            </router-link>
          </SwipeItem>
        </Swipe>
      </div>
      <!--新品推荐-->
      <div class="new-goods-wrapper goods-type-dom" :class="'goods-type0'">
        <newGoods  :data="newGoodsData" />
      </div>
      <!--商品分类-->
      <div>
        <exhibitionCard
          v-for="(item,index) in goodsClassifyData"
          :key="index"
          :data="item"
          :class="'goods-type-dom goods-type'+(index+1)"/>
      </div>
    </div>

    <!--侧边栏-->
    <SideBar :side-show="sideBarShow" @close="sideBarShow=!sideBarShow"/>

    <!--底部导航-->
    <tool-bar></tool-bar>
  </div>
</template>

<script>
  import ToolBar from "@/components/ToolBar";
  import TopSearchBar from '@/components/TopSearchBar';
  import SideBar from '@/components/SideBar'
  import newGoods from './_subpage/new-goods.vue'
  import exhibitionCard from './_subpage/goods-exhibition-card.vue'
  import { mapGetters } from 'vuex';
  import {
    Tab,
    Tabs,
    Swipe,
    SwipeItem,
  } from 'vant';
  export default {
    data(){
      return{
        sideBarShow:false,
        activeTab:0,
        tabNavData:[{
          title:'新品速递'
        },{
          title:'淑女馆'
        },{
          title:'绅士馆'
        },{
          title:'淑女馆'
        },{
          title:'淑女馆'
        },{
          title:'淑女馆'
        },{
          title:'淑女馆'
        }],
        swiperData:[
          require("../images/banner-zx-1.png"),
          require("../images/banner-zx-1.png"),
          require("../images/banner-zx-1.png"),
        ],
        newGoodsData:[
          require("../images/banner-zx-3.png"),
          require("../images/banner-zx-3.png"),
          require("../images/banner-zx-3.png"),
          require("../images/banner-zx-3.png"),
          require("../images/banner-zx-3.png"),
        ],
        goodsClassifyData:[
          {
            id:1,
            title:'淑女馆',
            title_en:'Lady Hall',
            image:require('../images/banner-zx-1.png'),
            goodsList:[{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            }],
          },
          {
            id:1,
            title:'绅士馆',
            title_en:"Gentleman's Hall",
            image:require('../images/banner-zx-1.png'),
            goodsList:[{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装',
              price:99.00,
              old_price:199.00
            }],
          },
          {
            id:1,
            title:'淑女馆',
            title_en:'Lady Hall',
            image:require('../images/banner-zx-1.png'),
            goodsList:[{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            }],
          },
          {
            id:1,
            title:'淑女馆',
            title_en:'Lady Hall',
            image:require('../images/banner-zx-1.png'),
            goodsList:[{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            }],
          },
          {
            id:1,
            title:'淑女馆',
            title_en:'Lady Hall',
            image:require('../images/banner-zx-1.png'),
            goodsList:[{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            }],
          },
          {
            id:1,
            title:'淑女馆',
            title_en:'Lady Hall',
            image:require('../images/banner-zx-1.png'),
            goodsList:[{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            },{
              image:require('../images/banner-zx-4.png'),
              title:'2018春季新款韩版春季新款休闲潮流装品牌鞋子',
              price:99.00,
              old_price:199.00
            }],
          },
        ],
      }
    },
    computed:{
      ...mapGetters({
        shoppingCartNum: 'getShoppingCartNum',
      })
    },
    mounted(){
      //监听滚动，显示当前active的tabs
      var doms = document.getElementsByClassName('goods-type-dom');
      var topValueList = [];
      for(let i = 0, len=doms.length; i<len; i++){
        topValueList.push(doms[i].offsetTop);
      }
      window.doms=doms;
      console.log(topValueList)
      window.addEventListener("scroll",()=>{
        for(let i = 0, len=topValueList.length; i<len; i++){
          if(topValueList[i]-100<window.scrollY){
            this.activeTab = i;
          }
        }
      });
    },
    beforeDestroy(){

    },
    components: {
      ToolBar,
      TopSearchBar,
      SideBar,
      Tab,
      Tabs,
      Swipe,
      SwipeItem,
      newGoods,
      exhibitionCard,
    },
    methods:{
      /**
       * 展开侧边栏导航
       */
      sidebarOpen(){
        this.sideBarShow = !this.sideBarShow;
      },
      /**
       * ，定位到第一个匹配项处
       */
      _goToPosition(id){
        var dom = document.getElementsByClassName('goods-type'+id);
        if(dom.length===0){
            return;
        }
        var top = dom[0].getBoundingClientRect().top;
        console.log(top);
        window.scrollTo(0,top-100);//通过scrollTop设置滚动到指定
      },
      /**
       *
       */

    },
  };
</script>

<style scoped>
  .page-home{
    margin-bottom: 60px;/* 底部导航导致一部分页面内容看不到 */
    background: #fff;
  }
  .top-header{
    padding: 0 0.3rem;
  }
  .swiper-box{
    padding: 0.2rem 0.24rem;
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

  .new-goods-wrapper{
    margin-top: 0.4rem;
    padding: 0 0.24rem 0.2rem;

  }
</style>
