<template>
	<div class="failed">
		<div class="failed-tit line-one-px">不通过原因：</div>
		<p class="failed-txt">您的商品部符合我们七天无理由退换货，详情请联系商家或重新申请退换货</p>
		<div class="btn-bar line-one-px">
			<router-link tag="a" :to="{name:'ApplyForBack'}" class="edit-apply-btn">修改申请</router-link>
		</div>
	</div>
</template>

<script>
	export default{
		name:"applyFailed",
		data(){
			return{
				txtArr:[
					"同意后，请按照退回 地址选项进行退货",
					"如快递寄出，请记录退货运单",
					"如大东拒绝，您可以修改退款申请再起发起，大东会重新处理"
				]
			}
		}
	}
</script>

<style scoped>
	.failed{
		background: #fff;
	}
	.failed-tit{
		padding-left: 0.3rem;
		line-height: 0.76rem;
		color: #000;
	}
	.failed-txt{
		padding: 0.22rem 0.3rem;
		line-height: 1.2;
		color: #999;
	}
	.btn-bar{
		display: flex;
		justify-content: flex-end;
		padding-right: 0.3rem;
		padding-bottom: 0.2rem;
	}
	.edit-apply-btn{
		display: block;
		width: 1.48rem;
		line-height: 0.56rem;
		text-align: center;
		border: 1px solid #999;
		color: #4d4d4d;		
	}
</style>