<template>
	<div class="gl-warp line-one-px">
		<img class="list-img" alt="" :src="goodsData.color.image" />
		<div class="cl-goods-info">
			<div class="goods-info-txt">
        <router-link class="goods-txt" :to="{name:'CommodityDetails'}">{{goodsData.productData.title}}</router-link>
				<i class="goods-edit-btn" @click.stop="editGoodsFun"></i>
			</div>
			<div class="goods-spec">
				<span class="goods-spec-item">颜色：{{goodsData.color.text}}</span>
				<span class="goods-spec-item">尺码:{{goodsData.size}}码</span>
			</div>
			<div class="goods-price-num">
				<span class="goods-price">￥{{goodsData.productData.price}}</span>
				<span class="goods-num" v-if="!hideNum">x{{goodsData.num}}</span>
			</div>
		</div>
	</div>
</template>

<script>
export default{
	name:"cartGoods",
	props:{
	  hideNum:Boolean,
		goodsData:{
			type: Object
		}
	},
	data(){
		return{
		}
	},
	methods:{
		// 点击编辑商品规格参数
		editGoodsFun(){
			this.$emit("editGoodsFun");
		},
	}
}
</script>

<style>
.gl-warp{
	position: relative;
	padding: 0.2rem 0;

	display: flex;
}
.cl-goods-info{
	padding-left: 0.22rem;
	padding-right: 0.3rem;
	flex: 1;

	display: flex;
	flex-direction: column;
	justify-content: space-between;
}
.goods-info-txt{
	display: flex;
	padding-top: 0.06rem;
}
.goods-txt{
	height: 0.72rem;
	font-size: 0.28rem;
	color: #000;
	line-height: 0.36rem;
	overflow: hidden;
	flex: 1;
}
.goods-edit-btn{
	margin-top: 0.04rem;
	display: block;
	width: 1rem;
	height: 0.3rem;
	background: url(../../assets/images/icon/icon-bianji-x.png) no-repeat center right;
	background-size: auto 100%;
}
.goods-spec{
	font-size: 0;
}
.goods-spec-item{
	color: #b3b3b3;
	font-size: 0.2rem;
}
.goods-spec-item + .goods-spec-item{
	margin-left: 0.2rem;
}
.goods-price-num{
	display: flex;
	justify-content: space-between;
}
.goods-price{
	font-size: 0.24rem;
	color: #010101;
}
.goods-num{
	font-size: 0.24rem;
	color: #b3b3b3;
}

/* 商品规格弹层slot样式 */
.goods-submit-bar{
	display: flex;
	justify-content: flex-end;
	align-items: center;

	height: 1rem;
	padding-right: 0.3rem;

	background: #F7F7F7;
}
.to-cart-btn,.buy-btn{
	width: 1.96rem;
	height: 0.6rem;

	line-height: 0.6rem;
	text-align: center;
	font-size: 0.3rem;
	color: #fff;
}
.to-cart-btn{
	background: #f8b500;
}
.buy-btn{
	background: #ea5361;
}
</style>
