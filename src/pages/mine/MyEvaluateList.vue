<template>
	<div class="evaluate-list">
		<div class="evaluate-list-item">
			<div class="evaluate-com">
				<p class="evaluate-txt">评价内容评价内容评价内容评价内容评价内容评价内容</p>
			</div>
			<div class="goods-info-wrap">
				<img class="goods-img" alt="" src="../../assets/images/list-goods/img-2.png" />
				<div class="goods-text">
					<p class="goods-txt">2019新品上市</p>
					<div class="goods-spec-price">
						<span class="goods-spec">灰色 38码</span>
						<span class="goods-price">￥119.00</span>
					</div>
				</div>
			</div>
			<div class="evaluate-time-wrap line-one-px">
				<span class="evaluate-time">2018-12-25 18:12:56</span>
			</div>
		</div>
		<div class="evaluate-list-item">
			<div class="evaluate-com">
				<p class="evaluate-txt">评价内容评价内容评价内容评价内容评价内容评价内容</p>
			</div>
			<div class="goods-info-wrap">
				<img class="goods-img" alt="" src="../../assets/images/list-goods/img-2.png" />
				<div class="goods-text">
					<p class="goods-txt">2019新品上市</p>
					<div class="goods-spec-price">
						<span class="goods-spec">灰色 38码</span>
						<span class="goods-price">￥119.00</span>
					</div>
				</div>
			</div>
			<div class="evaluate-time-wrap line-one-px">
				<span class="evaluate-time">2018-12-25 18:12:56</span>
			</div>
		</div>
	</div>
</template>

<script>
</script>

<style scoped>
	@import url("../../assets/css/list.css");
	.evaluate-list{
		position: fixed;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		background: #f7f7f7;
	}
	.evaluate-list-item{
		margin-top: 0.2rem;
	}
	.evaluate-com{
		padding: 0.26rem 0.3rem 0.28rem 0.24rem;
		background: #fff;
	}
	.evaluate-txt{
		font-size: 0.28rem;
		line-height: 0.36rem;
	}
	/*商品信息*/
	.goods-info-wrap{
		display: flex;
		background: #f6f6f6;
		padding: 0 0.3rem 0 0.24rem;
	}
	.goods-img{
		width: 1.2rem;
		height: 1.2rem;
	}
	.goods-text{
		padding-left: 0.24rem;
		flex: 1;
		display: flex;
		flex-direction: column;
	}
	.goods-txt{
		margin-top: 0.14rem;
		line-height: 0.36rem;
		font-size: 0.28rem;
		height: 0.64rem;
		overflow: hidden;
		-webkit-line-clamp: 2;
	}
	.goods-spec-price{
		display: flex;
		justify-content: space-between;
		line-height: 1;
	}
	.goods-spec{
		font-size: 0.2rem;
		color: #999;
	}
	.goods-price{
		font-size: 0.24rem;
		color: #4d4d4d;
	}
	/*时间*/
	.evaluate-time-wrap{
		height: 0.76rem;
		display: flex;
		justify-content: flex-end;
		align-items: center;
		padding-right: 0.3rem;
		background: #fff;
	}
	.evaluate-time{
		font-size: 0.24rem;
		color: #b3b3b3;
	}
</style>