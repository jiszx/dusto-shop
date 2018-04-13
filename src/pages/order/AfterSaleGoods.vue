<template>
	<div class="choose-as-goods">
		<div class="cas-goods-item">
			<order-goods-info class="cas-goods-info"></order-goods-info>
	        <div class="cas-btn-bar">
	        	<a @click="applyAfterSale" class="cas-btn">申请售后</a>
	        </div>
		</div>
		<div class="cas-goods-item">
			<order-goods-info class="cas-goods-info"></order-goods-info>
	        <div class="cas-btn-bar">
	        	<a @click="applyAfterSale" class="cas-btn">申请售后</a>
	        </div>
		</div>
		<div class="cas-goods-item">
			<order-goods-info class="cas-goods-info"></order-goods-info>
	        <div class="cas-btn-bar">
	        	<a @click="applyAfterSale" class="cas-btn">申请售后</a>
	        </div>
		</div>
		<quick-bar></quick-bar>
	</div>
</template>

<script>
	import OrderGoodsInfo from "./orderDetail/OrderGoodsInfo";
	import QuickBar from "components/QuickBar";
	export default{
		name:"afterSaleGoods",
		components:{
			OrderGoodsInfo,
			QuickBar
		},
		methods:{
			applyAfterSale(){
				this.$router.push({
					name:"ServiceType"
				})
			}
		}
	}
</script>

<style>
	.choose-as-goods{
		height: 100%;
		background: #f7f7f7;
	}
	.cas-goods-item{
		margin-bottom: 0.2rem;
		background: #fff;
	}
	.cas-goods-info::after{
		left: 0.3rem;
	}
	/*按钮*/
	.cas-btn-bar{
		height: 0.96rem;
		display: flex;
		align-items: center;
		justify-content: flex-end;
	}
	.cas-btn{
		margin-right: 0.3rem;
		width: 1.48rem;
		height: 0.56rem;
		text-align: center;
		line-height: 0.52rem;
		border: 1px solid #ea5362;
		color: #ea5361;
	}
</style>