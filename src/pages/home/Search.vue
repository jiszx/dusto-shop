<template>
  <div id="app">
      <div class="weui-content">
        <div class="container js_container">
              <div class="page">
                  <div class="weui-jiaj-panel">
                      <div class="page__bd">
                          <!--<a href="javascript:;" class="weui-btn weui-btn_primary">点击展现searchBar</a>-->
                          <div class="weui-search-bar" id="searchBar" v-bind:class="{ 'weui-search-bar_focusing': show }">
                              <form class="weui-search-bar__form">
                                  <div class="weui-search-bar__box">
                                      <i class="weui-icon-search"></i>
                                      <input type="search" v-model="searchInput" class="weui-search-bar__input" id="searchInput"  ref="searchInput" placeholder="搜索" required="" @keyup.enter="search">
                                      <a href="javascript:" class="weui-icon-clear" id="searchClear" @click="searchClear"></a>
                                  </div>
                                  <label class="weui-search-bar__label" id="searchText" @click="showSearch">
                                      <i class="weui-icon-search"></i>
                                      <span>搜索</span>
                                  </label>
                              </form>
                              <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel" @click="searchCancel">取消</a>
                          </div>
                          <div class="weui-cells searchbar-result " id="searchResult" v-bind:class="{'none':!show}" v-if="searchTextList.length">
                              <div class="weui-cell weui-cell_access" v-for="(item, index) in searchTextList" :key="index">
                                  <div class="weui-cell__bd weui-cell_primary">
                                      <router-link :to="{name:'SearchResult',query:{keyword:item.text}}" tag="p">{{item.text}}</router-link>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>

              <div class="lg-title search-begin">热门搜索</div>
              <div class="weui-flex">
                  <div class="weui-flex__item"><div class="placeholder" @click="search">及裸靴</div></div>
                  <div class="weui-flex__item"><div class="placeholder" @click="search">箭头</div></div>
                  <div class="weui-flex__item"><div class="placeholder" @click="search">手提包</div></div>
                  <div class="weui-flex__item"><div class="placeholder" @click="search">冬靴</div></div>
              </div>
              <div class="weui-flex">
                  <div class="weui-flex__item"><div class="placeholder" @click="search">毛绒商务男鞋</div></div>
              </div>

              <div class="lg-title search-begin van-hairline--top">历史搜索</div>
              <div class="weui-flex">
                  <div class="weui-flex__item"><div class="placeholder" @click="search">高跟鞋</div></div>
              </div>

              <div class="lg-title search-begin van-hairline--top">历史浏览</div>
              <list-row></list-row>

          </div>
    </div>
    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import ListRow from "components/ListRow";
  import QuickBar from "components/QuickBar";
  export default {
    data (){
      return {
        show:false,
        searchInput:'',
        searchTextList:[]
      }
    },
    created (){
     
    },
    watch:{
      searchInput(currentVal, oldVal){
        console.log(currentVal);
        //todo 接口请求
        this.searchText();
      },
    },
    methods:{
      hideSearchResult: function(){
        this.show = false; //下拉关键字列表隐藏
        this.searchInput = "";
      },
      cancelSearch:function(){
        this.hideSearchResult();
        this.show = false;//取消weui-search-bar_focusing样式,同时还原搜索条原来样式
      },
      /**
       * 搜索关键字列表
       */
      searchText(){
        //todo 模拟接口请求
        var list = [{
          id:1,
          text:'手提包'
        },{
          id:2,
          text:'高跟鞋'
        }]
        this.searchTextList = list;
      },
      /**
      *点击搜索按钮
      */
      showSearch(){
        this.show = true;
      },
      /**
       * 搜索清除
       */
      searchClear(){
        this.hideSearchResult();
      },
      /**
       * 搜索取消
       */
      searchCancel(){
        this.cancelSearch();
      },
      search(){
        this.$router.push({name:'SearchResult',query:{keyword:this.searchInput}});
      }
    },

    components:{
      ListRow, // 左右商品列表
      QuickBar // 快速导航
    },
  }
</script>

<style>
  @import url("../../assets/css/list.css");
  body {
    background-color: #f8f8f8;
  }

  .van-cell__title .van-icon {
    font-size: 18px;
  }
  #searchResult div p{
    font-size:0.28rem;
    margin-top:0;
  }
  #searchResult{
    margin-top:0;
    position: fixed;
    width: 100%;
  }
  #searchText{
    transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);
  }
</style>
