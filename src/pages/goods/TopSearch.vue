<template>
	<div class="weui-search-bar top-search-box">
  		<form class="weui-search-bar__form">
	        <div class="weui-search-bar__box">
	            <i class="weui-icon-search"></i>
	            <router-link :to="{name:'Search'}">
	              <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" v-bind:value="keyword"  required="">
	            </router-link>
	        </div>
	    </form>
    </div>
</template>

<script>
	export default{
		props: {
			keyword:{
				type:String,
				required:true
			}
		},
		data(){
			return {}
		},
		created(){
			
		},
	}
</script>

<style>
	.top-search-box .weui-search-bar__form{
  		background-color: transparent;
  	}
  	.top-search-box .weui-search-bar__box{
  		background-color: #f7f7f7;
  	}
	.top-search-box{
		padding: 0.2rem 0.3rem;
		background: #fff;
	}
</style>