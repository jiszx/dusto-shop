<template>
	<ul>
		<li class="goods-list-item line-one-px" @click="toGoodsDatails">
			<img class="list-img" alt="" src="../assets/images/list-goods/img-1.png" />
			<div class="list-goods-text">
				<p class="list-goods-txt">商品内容<em class="font-keyword spec-keyword">特俗电话电话</em>商品内容商品内容商品内容商品内容</p>
				<div class="list-price-wrap">
					<div class="price-wrap">
						<span class="new-price"><em>￥</em>199.00</span>
						<span class="old-price"><em>￥</em>199.00</span>
					</div>
					<i class="save-icon"></i>
				</div>
			</div>
		</li>
		<li class="goods-list-item line-one-px" @click="toGoodsDatails">
			<img class="list-img" alt="" src="../assets/images/list-goods/img-2.png" />
			<div class="list-goods-text">
				<p class="list-goods-txt">商品内容<em class="font-keyword spec-keyword">特俗电话电话</em>商品内容商品内容商品内容商品内容</p>
				<div class="list-price-wrap">
					<div class="price-wrap">
						<span class="new-price"><em>￥</em>199.00</span>
						<span class="old-price"><em>￥</em>199.00</span>
					</div>
					<i class="save-icon active-save-icon"></i>
				</div>
			</div>
		</li>
		<li class="goods-list-item line-one-px" @click="toGoodsDatails">
			<img class="list-img" alt="" src="../assets/images/list-goods/img-4.png" />
			<div class="list-goods-text">
				<p class="list-goods-txt">商品内容<em class="font-keyword spec-keyword">特俗电话电话</em>商品内容商品内容商品内容商品内容</p>
				<div class="list-price-wrap">
					<div class="price-wrap">
						<span class="new-price"><em>￥</em>199.00</span>
						<span class="old-price"><em>￥</em>199.00</span>
					</div>
					<i class="save-icon active-save-icon"></i>
				</div>
			</div>
		</li>
	</ul>
</template>

<script>
	export default{
		name: "listCololumn",
		methods:{
			toGoodsDatails(){
				this.$router.push({name:'CommodityDetails'})
			}
		}
	}
</script>
<style scoped>
	.list-goods-text{
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		padding-left: 0.2rem;
		flex: 1;
	}
	.list-goods-txt{
		font-size: 0.28rem;
		color: #000;
		line-height: 1.3;
	}
	.spec-keyword{
		font-size: 0.26rem;
	}
	.list-price-wrap{
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.save-icon{
		margin-right: 0.3rem;
	}
</style>