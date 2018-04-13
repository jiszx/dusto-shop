<template>
	  <div class="search-result-wrap">
	  		<top-search v-bind:keyword="keyword"></top-search>
	    	<div class="sr-wrap">
						<wrap-title name="商品" routerTo="/goodsList"></wrap-title>
						<list-column></list-column>
	    	</div>
	    	<div class="sr-wrap ">
						<wrap-title name="资讯" routerTo="/message/list"></wrap-title>
						<article-list class=""></article-list>
            <article-list class="van-hairline--top van-hairline--bottom"></article-list>
	    	</div>
	    	<div class="sr-wrap">
						<wrap-title name="我的心愿单" routerTo="/mine/wishList"></wrap-title>
						<list-row></list-row>
	    	</div>
	    	<quick-bar></quick-bar>
	  </div>
</template>

<script>
	  import ToolBar from "components/ToolBar";
	  import TopSearch from "../goods/TopSearch";
	  import WrapTitle from "components/WrapTitle";
	  import ListColumn from "components/ListColumn";
	  import ArticleList from "./searchResult/ArticleList";
	  import ListRow from "components/ListRow";
	  import QuickBar from "components/QuickBar";
	  export default {
	  		name:"searchResult",
	  		components: {
	  				TopSearch, // 顶部搜索
	  				WrapTitle, // 内容块标题
	  				ListColumn, // 竖商品列表
	  				ArticleList, // 文章列表
	  				ListRow, // 左右商品列表
	  				QuickBar // 快速导航
	  		},
	  		data(){
	  			return {
	  				keyword : this.$route.query.keyword,
	  			}
	  		},
	  };
</script>

<style>
		@import url("../../assets/css/list.css");
	  .search-result-wrap{
	  	  background: #fff;
	  }
	  .van-cell__title .van-icon {
	    	font-size: 18px;
	  }
</style>
