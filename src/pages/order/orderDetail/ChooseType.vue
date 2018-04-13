<template>
	<div class="choose-type">
		<router-link tag="a" :to="{name:'ChooseShop'}" class="choose-type-item line-one-px">
			<span class="choose-type-tit">门店换货</span>
			<i class="more-icon"></i>
		</router-link>
		<router-link tag="a" :to="{name:'OnlineExchange'}" class="choose-type-item">
			<span class="choose-type-tit">在线换货</span>
			<i class="more-icon"></i>
		</router-link>
	</div>
</template>

<script>
</script>

<style scoped>
	.choose-type{
		display: flex;
		flex-direction: column;
		background: #fff;
	}
	.choose-type-item{
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 0.76rem;
		padding-left: 0.24rem;
		padding-right: 0.3rem;
	}
	.choose-type-item::after{
		left: 0.24rem;
	}
	.choose-type-tit{
		font-size: 0.28rem;
		color: #000;
	}
	.more-icon{
		margin-left: 0.2rem;
		display: inline-block;
		width: 0.1rem;
		height: 0.2rem;
		
		background: url(../../../assets/images/icon/icon-right.png) no-repeat center;
		background-size: 100%;
	}
</style>