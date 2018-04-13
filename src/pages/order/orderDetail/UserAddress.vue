<template>
	<div class="user-address" @click="userAddress">
		<div class="user-addr">
			<div class="user-name-phone">
				<span>收货人：{{orderDetailData.recevieName||orderDetailData.name}}</span>
				<span>{{orderDetailData.receviePhone||orderDetailData.phone}}</span>
			</div>
			<div class="user-address-wrap">
				<!--<i></i>-->
				<Icon class="address-icon" name="location"></Icon>
				<p class="address-txt">收货地址：{{orderDetailData.recevieAddress||orderDetailData.address}}</p>
			</div>
		</div>
		<slot></slot>
	</div>
</template>

<script>
	import { Icon } from 'vant';
	export default{
		name:"userAddrss",
		components:{
			Icon
		},
		props:{
			data:{
				type: Object
			},
			isClick:{
				type: Boolean,
				default: false
			},
	      	orderDetailData:{
				type:Object,
	        	default:function () {
		          return {
		            recevieName:'',
		            receviePhone:'',
		            recevieAddress:'',
		          }
	        	}
	      	},
		},
		methods:{
			userAddress(){
				if(!this.isClick) return;
				this.$emit("userAddr")
			}
		}
	}
</script>

<style scoped>
	.user-address{
		padding: 0.32rem 0.3rem 0.3rem 0;
		background: #fff;
	}
	.user-name-phone{
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding-left: 0.62rem;

		font-size: 0.28rem;
		color: #000;
		line-height: 1;
	}
	.user-address-wrap{
		margin-top: 0.2rem;
		padding-left: 0.24rem;

		display: flex;
		flex-wrap: nowrap;
		align-items: flex-start;

		font-size: 0.24rem;
		color: #999;
	}
	.address-icon{
		margin-top: 0.04rem;
		margin-right: 0.12rem;
	}
	.address-txt{
		line-height: 1.2;
	}
</style>
