<template>
	<div class="order-info">
		<div class="order-info-item" v-for="(item, index) in infoData">
			<span class="order-info-key">{{item.name}}：</span>
			<span class="order-info-val" id="orderNum">{{item.val}}</span>
			<button 
				v-if="orderType&&!index&&orderType!==11&&orderType!==16" 
				class="copy-btn clipboardBtn"
				@click="copyFun">复制</button >
		</div>
		<!--<div class="order-info-item">
			<span class="order-info-key">下单时间：</span>
			<span class="order-info-val">2018-02-01 10:56:45</span>
		</div>
		<div class="order-info-item">
			<span class="order-info-key">下单时间：</span>
			<span class="order-info-val">2018-02-01 10:56:45</span>
		</div>-->
	</div>
</template>

<script>
	import Clipboard from "clipboard"
	export default{
		name:"ODorderInfo",
		components:{
		},
		props:{
			isPropsData:{
				type: Boolean,
				default: false
			},
			PropsData:{
				type: Array
			},
			orderType:{
				type: Number
			}
		},
		data(){
			return{
				infoData: this.isPropsData || this.orderType,
				listData:{
					1:[{
						name:"订单编号",
						val:125578888888888
					},{
						name:"下单时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"付款时间",
						val:"2018-02-01 10:56:45"
					}],
					2:[{
						name:"订单编号",
						val:125578888888888
					},{
						name:"下单时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"付款时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"发货时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"成交时间",
						val:"2018-02-01 10:56:45"
					}],
					3:[{
						name:"下单编号",
						val:125578888888888
					},{
						name:"下单时间",
						val:"2018-02-01 10:56:45"
					}],
					4:[{
						name:"订单编号",
						val:125578888888888
					},{
						name:"下单时间",
						val:"2018-02-01 10:56:45"
					}],
					5:[{
						name:"退款编号",
						val:125578888888888
					},{
						name:"申请时间",
						val:"2018-02-01 10:56:45"
					}],
					8:[{
						name:"退款编号",
						val:125578888888888
					},{
						name:"申请时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"申请件数",
						val:"1件"
					},{
						name:"退款金额",
						val:"￥119.00"
					}],
					9:[{
						name:"退款编号",
						val:125578888888888
					},{
						name:"申请时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"申请件数",
						val:"1件"
					},{
						name:"退款金额",
						val:"￥119.00"
					}],
					10:[{
						name:"订单编号",
						val:125578888888888
					},{
						name:"下单时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"付款时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"发货时间",
						val:"2018-02-01 10:56:45"
					}],
					11:[{
						name:"退款原因",
						val:"质量问题"
					},{
						name:"退款金额",
						val:"￥119.00"
					},{
						name:"扣除积分",
						val:"198"
					},{
						name:"申请件数",
						val:"19件"
					},{
						name:"申请时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"退款编号",
						val:"19844585555555"
					}],
					16:[{
						name:"退款原因",
						val:"质量问题"
					},{
						name:"申请件数",
						val:"19件"
					},{
						name:"申请时间",
						val:"2018-02-01 10:56:45"
					},{
						name:"退款编号",
						val:"19844585555555"
					}]
				}
			}
		},
		created(){
			this.init();
		},
		methods:{
			init(){
				this.infoData  = !this.isPropsData ? this.listData[this.orderType] : this.PropsData
			},
			copyFun(){
				new Clipboard('.clipboardBtn', {
					text: function() {
						return document.getElementById("orderNum").innerHTML;
					}
				});
			}
		}
	}
</script>

<style>
	/*复制按钮*/
	.copy-btn{
		display: inline-block;
		margin-left: 0.1rem;
		width: 1.08rem;
		height: 0.38rem;
		
		line-height: 0.38rem;
		text-align: center;
		border: 1px solid #999;
		background: #fff;
	}
	.order-info{
		padding: 0 0.3rem 0.1rem;
		border-top: 0.2rem solid #f7f7f7;
		background: #fff;
	}
	.order-info-item{
		margin: 0.2rem 0;
		font-size: 0.24rem;
		color: #999;
		line-height: 1;
	}
</style>