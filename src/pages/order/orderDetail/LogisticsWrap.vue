<template>
	<div class="lgistics-wrap line-one-px" @click="toLogustics">
		<i class="lgistics-icon"></i>
		<div class="lgistics-text">
			<p class="lgistics-txt">这里是物理信息这里是无咯iuxinxn这里是物理信息这里是无咯iuxinxn</p>
			<span class="lgistics-time">2018-01-20 13:39:55</span>
		</div>
		<a class="seeMore"><i class="more-icon"></i></a>
	</div>
</template>

<script>
	export default{
		name:"logisticsWrap",
		methods:{
			toLogustics(){
				this.$router.push({name:'Logistics'})
			}
		}
	}
</script>

<style scoped>
	@import "../../../assets/css/list.css";
	.lgistics-wrap{
		display: flex;
		align-items: center;
		padding: 0.28rem 0.3rem 0.3rem 0.2rem;
		background: #fff;
	}
	.lgistics-text{
		flex: 1;
		line-height: 1;
	}
	.lgistics-txt{
		line-height: 1.2;
		font-size: 0.28rem;
		color: #70bc46;
	}
	.lgistics-time{
		display: block;
		margin-top: 0.14rem;
		font-size: 0.24rem;
		color: #999;
	}
	.lgistics-icon{
		width:0.28rem;
		height: 0.28rem;
		margin-right: 0.12rem;
		background: url(../../../assets/images/icon/icon-wlgz.png) no-repeat center;
		background-size: 100% auto;
	}
</style>
