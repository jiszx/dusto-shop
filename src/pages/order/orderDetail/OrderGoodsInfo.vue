<template>
	<div class="order-goods-info van-hairline--bottom">
		<div class="ogi-left">
			<img class="ogi-img" alt="goods" src="../../../assets/images/list-goods/img-2.png" />
			<div class="ogi-info">
				<p class="ogi-txt">2018春季新品简约时尚高帮鞋</p>
				<div class="ogi-font">
					<span class="ogi-spec">颜色：灰色 </span>
					<span class="ogi-spec">尺寸：38码</span>
				</div>
				<p class="ogi-font">2018-01-01 12:32:26</p>
			</div>
		</div>
		<div class="ogi-right">
			<span class="ogi-font ogi-price">￥118.00</span>
			<span class="ogi-font">￥192.00</span>
			<span class="ogi-font ogi-num">x1</span>
		</div>
    </div>
</template>

<script>
</script>

<style>
	/*商品样式*/
	.order-goods-info{
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 0.2rem 0.3rem;
		background: #fff;
	}
	.ogi-left{
		display: flex;
		align-items: center;
	}
	.ogi-img{
		width: 1.2rem;
		height: 1.2rem;
	}
	.ogi-info{
		margin-left: 0.2rem;
	}
	.ogi-txt{
		padding-bottom: 0.1rem;
		color: #000;
		line-height: 1;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.ogi-font{
		font-size: 0.2rem;
		color: #999;
		padding-top: 0.12rem;
		line-height: 1;
	}
	.ogi-right{
		display: flex;
		flex-direction: column;
	}
	.ogi-price{
		color: #000;
		font-size: 0.24rem;
	}
	.ogi-num{
		text-align: right;
	}
</style>