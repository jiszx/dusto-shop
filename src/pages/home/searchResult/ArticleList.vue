<template>
	<ul class="article-list">
		<li class="article-list-item goods-list-item" @click="linkTo">
			<img class="list-img" alt="pic" src="../../../assets/images/goods/banner-zx-6.png" />
			<div class="article-list-text">
				<p class="article-list-title">冬季换新我的美腿修炼手册</p>
				<p class="article-list-txt">文章内容等等文章内容<em class="font-keyword">特俗电话电话</em>等等文章内容等等文章内容等等文章内容等等</p>
				<p class="article-list-time">2018年2月26日</p>
			</div>
		</li>
	</ul>
</template>

<script>
	export default{
		name: "articleList",
    data(){
		  return{}
    },
    methods:{
      linkTo(){
        this.$router.push({name:'MsgDetail'});
      }
    },
	}
</script>

<style>
	.article-list-item{
	}
	.article-list-text{
		padding-left: 0.2rem;
		flex: 1;
	}
	.article-list-title{
		font-size: 0.28rem;
		color: #000;
	}
	.article-list-txt{
		line-height: 1.2;
	}
	.article-list-txt,.article-list-time{
		font-size: 0.24rem;
		color: #999;
	}
</style>
