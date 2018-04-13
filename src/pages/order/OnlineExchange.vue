<template>
	<div class="online-exchange">
		<div>
			<a class="oe-space-between line-one-px">原商品</a>
			<!--<a class="oe-space-right">绑带尖头高跟鞋 ￥99.00<i class="more-icon"></i></a>-->
			<order-goods-info class="oe-goods-info"></order-goods-info>
		</div>
		<div class="oe-space-between line-one-px oe-choose-exchange-goods" @click="chooseExchange">
			<a>选择换货商品</a>
			<a class="oe-space-right"><i class="more-icon"></i></a>
		</div>
		<!--商品信息-->
		<goods-info :orderType="type" class="oe-goods-info-wrap"></goods-info>
		<!--订单信息-->
		<order-info :orderType="type" class="line-one-px"></order-info>
		<!--联系-->
		<contact :class="{'contact-spec':type===16}"></contact>
		
		<!-- 底部按钮-->
		<div class="order-detail-btn-bar">
			<a class="pub-btn pay-btn" @click="showAS('pay')">支付</a>
		</div>
	</div>
</template>

<script>
	import OrderGoodsInfo from "./orderDetail/OrderGoodsInfo"
	import GoodsInfo from "./orderDetail/GoodsInfo"
	import OrderInfo from "./orderDetail/OrderInfo"
	import Contact from "./orderDetail/Contact"
	export default{
		name:"onlineExchange",
		components:{
			OrderGoodsInfo,
			GoodsInfo,
			OrderInfo,
			Contact
		},
		data(){
			return{
				type:16
			}
		},
		methods:{
			chooseExchange(){
				console.log(this.$route)
				this.$router.push({
					name:"CommodityDetails",
					query:{
						type:"confirm"
					}
				})
			}
		}
	}
</script>

<style scoped>
	.online-exchange .oe-goods-info-wrap{
		margin-top: 0;
	}
	.online-exchange{
		height: 100%;
		padding-bottom: 1rem;
		background: #f7f7f7;
	}
	.oe-goods-info{
		padding-left: 0.24rem;
	}
	/*顶部样式*/
	.oe-choose-exchange-goods{
		margin-top: 0.2rem;
	}
	.oe-space-between{
		padding-left: 0.2rem;
		height: 0.76rem;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #fff;
	}
	.oe-raw-goods{
		justify-content: inherit;
	}
	.oe-space-right{
		font-size: 0.24rem;
		margin-right: 0.3rem;
		color: #b3b3b3;
	}
	/* 底部按钮 */
	.order-detail-btn-bar{
		position: absolute;
		bottom: 0;
		left: 0;
		right: 0;
		padding-right: 0.3rem;
		height: 1rem;
		display: flex;
		justify-content: flex-end;
		align-items: center;
		background: #fff;
	}
	.pub-btn{
		width: 1.96rem;
		line-height: 0.6rem;
		text-align: center;
		border: 1px solid #999;
		font-size: 0.3rem;
		color: #4d4d4d;
	}
	.pub-btn + .pub-btn{
		margin-left: 0.2rem;
	}
	.pay-btn{
		border-color: #ea5361;
		color: #fff;
		background: #ea5361;
	}
</style>