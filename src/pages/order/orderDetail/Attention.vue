<template>
	<div class="attention">
		<div class="attention-tit">注意事项</div>
		<p class="attention-txt" v-for="(item,index) in txtArr" :key="index">{{item}}</p>
	</div>
</template>

<script>
	export default{
		name:"attention",
		data(){
			return{
				txtArr:[
					"同意后，请按照退回 地址选项进行退货",
					"如快递寄出，请记录退货运单",
					"如大东拒绝，您可以修改退款申请再起发起，大东会重新处理"
				],
				txtArr2:[
					"货款将在大东区人验货后返还",
					"预期未到店的，退货申请将关闭。",
				]
			}
		}
	}
</script>

<style>
	.attention{
		padding: 0.2rem 0.3rem 0.16rem 0.24rem;
		background: #fff;
	}
	.attention-tit{
		font-size: 0.28rem;
		color: #000;
		line-height: 1;
		padding-bottom: 0.08rem;
	}
	.attention-txt{
		font-size: 0.24rem;
		color: #999;
		line-height: 1.4;
	}
</style>