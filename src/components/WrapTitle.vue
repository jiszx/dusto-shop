<template>
	<div class="wrap-title line-one-px">
		<span class="wrap-title-name">{{name}}</span>
		<a @click="aLinkFun" class="seeMore">查看更多<i class="more-icon"></i></a>
	</div>
</template>

<script>
	import router from "@/router";
	export default{
		name: "wrapTitle",
		props:{
			name: {
				type: String,
				default: "标题"
			},
			routerTo: {
				type: String,
				default: "/"
			}
		},
		created(){
			
		},
		methods:{
			aLinkFun(){
				router.push(this.routerTo);
			}
		}
	}
</script>

<style>
	
	.wrap-title{
		display: flex;
		justify-content: space-between;
		align-items: center;
		
		height: 0.76rem;
		margin-left: 0.3rem;
		line-height: 0.76rem;
	}
	.wrap-title-name{
		font-size: 0.28rem;
		color: #000;
	}
	.seeMore{
		font-size: 0.24rem;
		margin-right: 0.3rem;
		color: #666;
	}
</style>