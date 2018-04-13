<template>
  <div class="page-product-comment">
    <div
      class="comment-item"
      v-for="(item,index) in commonList"
      :key="index"
      :class="{'van-hairline--top':index!==0}">
      <Comment :data="item" />
    </div>
    <!--加载更多-->
    <div class="loading-more-box">
      <p class="loading-more" v-if="!hasMore">没有更多</p>
      <LoadingMore v-else :loading-fn="loadingMore" :loading="loading"/>
    </div>

    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import Comment from '@/components/comment'
  import LoadingMore from '@/components/base/loading-more'
  import QuickBar from "components/QuickBar";
  export default {
    data(){
      return{
        commonList:[{
          userid:1,
          userHead:require("./images/banner-zx-4.png"),
          username:'王**',
          comment:'鞋子不错，物超所值，高端大气，上档次，推荐购买。',
          colorType:'灰色',
          size:38,
          buyDate:'2018-01-10',
          commontDate:'2018-01-25',
        },{
          userid:1,
          userHead:require("./images/banner-zx-4.png"),
          username:'王**',
          comment:'鞋子不错，物超所值，高端大气，上档次，推荐购买。',
          colorType:'灰色',
          size:38,
          buyDate:'2018-01-10',
          commontDate:'2018-01-25',
          images:[require("./images/banner-zx-4.png"),require("./images/banner-zx-4.png")]
        },{
          userid:1,
          userHead:require("./images/banner-zx-4.png"),
          username:'王**',
          comment:'鞋子不错，物超所值，高端大气，上档次，推荐购买。',
          colorType:'灰色',
          size:38,
          buyDate:'2018-01-10',
          commontDate:'2018-01-25',
        },{
          userid:1,
          userHead:require("./images/banner-zx-4.png"),
          username:'王**',
          comment:'鞋子不错，物超所值，高端大气，上档次，推荐购买。',
          colorType:'灰色',
          size:38,
          buyDate:'2018-01-10',
          commontDate:'2018-01-25',
          images:[require("./images/banner-zx-4.png"),require("./images/banner-zx-4.png")]
        },{
          userid:1,
          userHead:require("./images/banner-zx-4.png"),
          username:'王**',
          comment:'鞋子不错，物超所值，高端大气，上档次，推荐购买。',
          colorType:'灰色',
          size:38,
          buyDate:'2018-01-10',
          commontDate:'2018-01-25',
        },{
          userid:1,
          userHead:require("./images/banner-zx-4.png"),
          username:'王**',
          comment:'鞋子不错，物超所值，高端大气，上档次，推荐购买。',
          colorType:'灰色',
          size:38,
          buyDate:'2018-01-10',
          commontDate:'2018-01-25',
          images:[require("./images/banner-zx-4.png"),require("./images/banner-zx-4.png")]
        }],
        hasMore:true,
        loading:false,
      }
    },
    components: {
      Comment,
      LoadingMore,
      QuickBar //快速导航
    },
    methods:{
      /**
       * 加载更多
       */
      loadingMore(){
        //todo 加载更多代码
        //模拟加载更多
        this.loading=true;
        var timer = setTimeout(()=>{
          clearTimeout(timer);
          this.commonList = this.commonList.concat(this.commonList.slice(3));
          this.loading=false;
        },1500)
      },
    },
  };
</script>

<style scoped>
  .page-product-comment{
    padding: 0 0.4rem;
  }
  .comment-item{
    padding: 0.2rem 0;
  }
</style>
