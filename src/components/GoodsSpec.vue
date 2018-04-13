<template>
	<div class="as-goods-spac">
		<div class="null-box"></div>
		<div class="as-goods-com">
			<div class="as-goods-info">
				<img class="as-goods-img" alt="" :src="goodsData.choose.image" />
				<div class="as-goods-text">
					<p class="as-goods-txt">{{goodsData.title}}</p>
					<div class="price-wrap">
						<span class="new-price"><em>￥</em>{{goodsData.price}}</span>
						<span class="old-price"><em>￥</em>{{goodsData.old_price}}</span>
					</div>
					<p class="text-spec">{{goodsData.Integral}}积分可兑换</p>
				</div>
			</div>
			<div class="as-goods-color">
				<div class="as-gc-name">颜色</div>
				<div class="as-gci-wrap">
					<img 
						class="as-gc-img as-active-img" 
						v-for="item in goodsData.colorType" 
						:key="item.id" 
						alt="pic" 
						:src="item.image" />
					<!--<img class="as-gc-img" alt="" src="../assets/images/list-goods/img-2.png" />
					<img class="as-gc-img" alt="" src="../assets/images/list-goods/img-3.png" />-->
				</div>
			</div>
			<div class="as-size user-size">
				<span class="as-size-key user-size-key">您日常选择尺寸：</span>
				<div class="as-size-list">
					<a class="size-item">36</a>
					<a class="size-item">37</a>
					<a class="size-item">38</a>
					<a class="size-item">39</a>
				</div>
			</div>
			<div class="as-size">
				<span class="as-size-key">选择尺寸：</span>
				<div class="as-size-list">
					<a class="size-item" v-for="sizeItem in goodsData.size" :key="sizeItem">{{sizeItem}}</a>
				</div>
			</div>
		</div>
		<div class="as-goods-bar">
			<div class="as-good-num line-one-px">
				<span class="as-number-key">购买数量：</span>
				<stepper v-model="numberVal" />
			</div>
			<slot></slot>
		</div>
	</div>
</template>

<script>
import { Stepper } from 'vant';
import UseNumber from "components/UseNumber"
export default{
	name:"goodsSpac",
	components:{
		Stepper
	},
	props:{
		goodsData:{
			type: Object
		}
	},
	created(){
		console.log(this.goodsData);
	},
	data(){
		return{
			numberVal: 5
		}
	}
}
</script>

<style>
.as-goods-spac{
	position: absolute;
	height: 100%;
	width: 100%;
	padding-bottom: 1.76rem;
}
.null-box{
	height: 0.5rem;
	background: transparent;
}
.as-goods-com{
	height: calc(100% - 0.5rem);
	padding-left: 0.32rem;
	padding-right: 0.2rem;
	
	background: #fff;
}
.as-goods-info{
	position: relative;
	
}
.as-goods-img{
	width: 1.8rem;
	height: 1.8rem;
	position: absolute;
	top: -0.5rem;
	left: 0;
}
.as-goods-text{
	padding-left: 2rem;
}
.as-goods-txt{
	padding-top: 0.12rem;
	font-size: 0.28rem;
	color: #000;
}
.text-spec{
	font-size: 0.24rem;
	color: #ea5361;
}

/*颜色*/
.as-gc-name{
	padding-top: 0.62rem;
	padding-bottom: 0.1rem;
	
	font-size: 0.28rem;
	line-height: 1;
}
.as-gci-wrap{
	display: flex;
}
.as-gc-img{
	width: 1rem;
	height: 1rem;
	border: 1px solid transparent;
	margin: 0.1rem 0.1rem 0 0;
}
.as-active-img{
	border-color: #999;
}
/*尺寸*/
.user-size{
	padding-top: 0.48rem;
}
.as-size{
	padding-top: 0.4rem;
	
	display: flex;
}
.user-size-key{
	margin-right: 0.1rem;
}
.as-size-key{
	font-size: 0.24rem;
	line-height: 0.44rem;
	white-space: nowrap;
}
.as-size-list{
	display: flex;
	flex-wrap: wrap;
}
.user-size .size-item{
	margin-bottom: 0;
}
.size-item{
	margin-bottom: 0.1rem;
	margin-left: 0.3rem;
	width: 0.44rem;
	height: 0.44rem;
	
	line-height: 0.44rem;
	font-size: 0.24rem;
	text-align: center;
	background: #f7f7f7;
	
	border-radius: 0.1rem;
}

/*底部*/
.as-goods-bar{
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
}
.as-good-num{
	position: relative;
	display: flex;
	align-items: center;
	justify-content: space-between;
	
	padding: 0 0.3rem 0 0.32rem;
	height: 0.76rem;
	background: #fff;
}

/*底部对加减数字的样式*/
.as-good-num .van-stepper__stepper{
	width: 0.48rem;
	height: 0.48rem;
	font-size: 0.32rem;
	background: #f7f7f7;
	border: none;
}
.as-good-num .van-stepper__minus{
	border-radius: 0.1rem 0 0 0.1rem;
}
.as-good-num .van-stepper__input{
	padding: 0;
	margin: 0.02rem;
	width: 0.88rem;
	height: 0.48rem;
	font-size: 0.28rem;
	background: #f7f7f7;
	border: none;
}
.as-good-num .van-stepper__plus{
	border-radius: 0 0.1rem 0.1rem 0;
}
</style>