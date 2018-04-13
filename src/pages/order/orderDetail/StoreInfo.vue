<template>
	<div class="order-datail-shopInfo line-one-px">
        <div class="od-si-left">
          	<div>
	            <div class="od-si-area">温州</div>
	            <div class="od-si-shopName">下沈线大东<img src="../../../assets/images/img-star-hb.png" class="black-star-icon"/>
	            </div>
            	<div class="od-si-address">门店地址门店地址门店地址门店地址</div>
          	</div>
        </div>
        <div class="od-si-right">
			<div class="od-si-right-txt">店长：王大富</div>
			<div class="od-si-right-txt">13982184960</div>
        </div>
    </div>
</template>

<script>
</script>

<style scoped>
	.order-datail-shopInfo{
		display: flex;
		justify-content: space-between;
		padding: 0.24rem 0.3rem 0.2rem 0.3rem;
		line-height: 1;
		border-top: 0.2rem solid #f7f7f7;
		background: #fff;
	}
	.od-si-area{
		font-size: 0.24rem;
		color: #1a1a1a;
	}
	.od-si-shopName{
		margin-top: 0.12rem;
		font-size: 0.36rem;
		color: #1a1a1a;
	}
	.black-star-icon{
		margin-left: 0.2rem;
		width: 0.32rem;
		height: 0.32rem;
	}
	.od-si-address{
		margin-top: 0.24rem;
		font-size: 0.24rem;
		color: #999;
	}
	.od-si-right-txt{
		margin-top: 0.06rem;
		font-size: 0.24rem;
		color: #999;
		text-align: right;
	}
	.od-si-right-txt + .od-si-right-txt{
		margin-top: 0.16rem;
	}
</style>