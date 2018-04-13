<template>
	<div class="od-goods-info">
    <order-details-row  v-if="showType[orderType][0]" :dataList="orderDetailData.productList"></order-details-row>
		<!--积分兑换-->
		<div v-if="showType[orderType][1]" class="goods-check-integral goods-integral-deduction line-one-px">
			<div class="gci-left">
				<p class="gci-tit">积分兑换 <em class="gci-spec">该商品500积分可兑换</em></p>
				<p class="gci-txt">您有699积分</p>
			</div>
      <Checkbox class="gci-radio" v-model="radio"></Checkbox>
		</div>
		<!--积分抵扣-->
		<div v-if="showType[orderType][2]&&radio" class="goods-integral-deduction line-one-px">
			<div>
				<p class="gid-left-tet">您有699积分可抵扣6元</p>
				<p class="gid-left-tet">积分满100可使用</p>
			</div>
			<div class="gid-txt">
				使用
				<Field class="gid-num" type="number" v-model="value" placeholder="户名" ></Field>
				00个 可抵扣：￥1.00
			</div>
		</div>
		<!--优惠抵扣-->
		<router-link tag="div" :to="{name:'ChooseSaleCard'}" v-if="showType[orderType][3]" class="goods-sale-deduction line-one-px">
			<span class="gsd-txt">优惠券抵扣</span>
			<a class="seeMore">已减40<i class="more-icon"></i></a>
		</router-link>
		<!--结算-->
		<div v-if="showType[orderType][4]" class="goods-price-wrap line-one-px">
			<span class="order-from">订单来源：在线商城</span>
			<div class="gp-keyword">
				<span class="gp-key">商品总额</span>
				<span class="gp-val">￥119.00</span>
			</div>
			<div class="gp-keyword" v-if="orderType!==4">
				<span class="gp-key">积分抵扣</span>
				<span class="gp-val">-￥1.00</span>
			</div>
			<div class="gp-keyword" v-if="orderType===3">
				<span class="gp-key">优惠券抵扣</span>
				<span class="gp-val">-￥40.00</span>
			</div>
			<div class="gp-keyword">
				<span class="gp-key">+运费</span>
				<span class="gp-val">￥0.00</span>
			</div>
		</div>
		<!--结算2-->
		<div v-if="showType[orderType][5]" class="goods-all-price-wrap line-one-px">
			<span v-if="orderType!==11" class="goods-ap-txt">共1件商品 实付：<em>￥118.00</em></span>
			<span v-if="orderType!==11" class="goods-ap-txt">(含运费￥0.00)</span>
			<div class="goods-info-bar" v-if="btnArr[orderType].length">
				<a class="pub-btn"
					:class="{'evaluate-btn':item.type}"
					v-for="(item,index) in btnArr[orderType]"
					:key="index"
					@click="btnRouter(item.routerName)">{{item.name}}</a>
			</div>

		</div>
		<!--积分返点-->
		<div v-if="showType[orderType][6]" class="gi-integral-wrap line-one-px">
			<span>返积分<em>{{orderDetailData.point}}</em>点</span>
			<i class="warn-icon"></i>
		</div>
		<!--联系-->
		<contact v-if="showType[orderType][7]"></contact> <!--:class="{'contact-spec':orderType===1}"-->
	</div>
</template>

<script>
	import { Field, Checkbox } from 'vant';
	import OrderDetailsRow from "components/OrderDetailsRow"
	import Contact from "./Contact"
	export default{
		name:"orderGoodsInfo",
		components:{
			Field,
      Checkbox,
			OrderDetailsRow,
			Contact
		},
		props:{
			orderType:{
				type: Number,
			},
		    orderDetailData:{
			    type:Object
		    },
		},
		data(){
			return{
				value: 12,
				radio:false,
				showType:{
					1:[true,false,false,false,true,true,true,true],
					2:[true,false,false,false,true,true,true,true],
					3:[true,false,true,true,true,true,false,true],
					4:[true,true,true,false,true,true,false,true],
					5:[true,false,false,false,true,true,false,false],
					7:[false,false,false,false,true,true,false,false],
					10:[true,false,false,false,true,true,true,true],
					11:[true,false,false,false,false,true,false,false],
					16:[true,false,false,false,true,true,false,false] //在线退换货
				},
				btnArr:{
					1:[{
						name:"退款",
						type: 0,
						routerName:"ApplyForBack"
					}],
					2:[{
						name:"申请售后",
						type: 0,
						routerName:"AfterSaleGoods"
					},{
						name:"查看物流",
						type: 0,
						routerName:"Logistics"
					},{
						name:"评价",
						type: 1,
						routerName:"WriteEvaluate"
					}],
					3:[],
					4:[],
					5:[{
						name:"撤销申请",
						type: 0,
						routerName:""
					},{
						name:"修改申请",
						type: 0,
						routerName:"ApplyForBack"
					}],
					10:[{
						name:"售后详情",
						type: 0,
						routerName:""
					},{
						name:"查看物流",
						type: 0,
						routerName:"Logistics"
					},{
						name:"确认收货",
						type: 1,
						routerName:""
					}],
					11:[{
						name:"撤销申请",
						type: 0,
						routerName:""
					},{
						name:"我已退鞋",
						type: 1,
						routerName:""
					}],
					16:[]
				}
			}
		},
		methods:{
			btnRouter(name){
				this.$router.push({name})
			},
		}
	}
</script>
<style>
	.gid-num .van-field__control{
		text-align: center;
		color: #808080;
		background: #f2f2f2;
	}
	.gci-radio .van-radio__label{
		margin-left: 0;
	}
</style>
<style scoped>
	@import "../../../assets/css/list.css";
	.od-goods-info{
		margin-top: 0.2rem;
		background: #fff;
	}
	/*商品价格块*/
	.goods-price-wrap{
		padding: 0.18rem 0.3rem 0.28rem 0.3rem;
		font-size: 0.24rem;
		color: #999;
		line-height: 1;
	}
	.goods-price-wrap::after{
		left: 0.24rem;
	}
	.order-from{
		display: block;
		padding-bottom: 0.14rem;
	}
	.gp-keyword{
		padding-top: 0.06rem;
		display: flex;
		justify-content: space-between;
	}
	.gp-val{
		color: #b3b3b3;
	}
	/*积分兑换*/
	.gci-left{
		font-size: 0.24rem;
	}
	.gci-spec{
		color: #ea5361;
	}
	.gci-tit{
		color: #000;
	}
	.gci-txt{
		color: #999;
	}
	/*积分抵扣*/
	.goods-integral-deduction{
		padding-left: 0.24rem;
		padding-right: 0.3rem;
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 0.88rem;
	}
	.gid-left-tet{
		line-height: 1.2;
		font-size: 0.2rem;
		color: #999;
	}
	.gid-txt{
		display: flex;
		color: #808080;
	}
	.gid-num{
		width: 0.68rem;
		height: 0.48rem;
		padding: 0;
		margin: 0 0.12rem;
	}
	/*优惠券抵扣*/
	.goods-sale-deduction{
		padding-left: 0.24rem;
		padding-right: 0.3rem;
		height: 0.76rem;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.gsd-txt{
		color: #4d4d4d;
	}
	.seeMore{
		font-size: 0.24rem;
		color: #808080;
	}
	/*结算*/
	.goods-all-price-wrap{
		padding: 0.16rem 0.3rem 0.2rem 0;
		display: flex;
		flex-direction: column;
		align-items: flex-end;
	}
	.goods-ap-txt{
		font-size: 0.2rem;
		color: #999;
		line-height: 1.2;
	}
	.goods-ap-txt em{
		font-size: 0.24rem;
		color: #ea5361;
	}
	.goods-info-bar{
		margin-top: 0.26rem;
		display: flex;
		justify-content: flex-end;
	}
	.pub-btn{
		width: 1.48rem;
		line-height: 0.56rem;
		text-align: center;
		border: 1px solid #999;
		font-size: 0.28rem;
		color: #4d4d4d;
	}
	.pub-btn + .pub-btn{
		margin-left: 0.2rem;
	}
	.evaluate-btn{
		border-color: #ea5361;
		color: #ea5361;
	}
	/*积分*/
	.gi-integral-wrap{
		display: flex;
		align-items: center;
		height: 0.96rem;
		padding-left: 0.3rem;

		border-top: 0.2rem solid #f7f7f7;
		font-size: 0.24rem;
		color: #999;
	}
	.gi-integral-wrap em{
		color: #ea5362;
	}
	.warn-icon{
		display: block;
		width: 0.28rem;
		height: 0.28rem;
		margin-left: 0.18rem;
		background: url(../../../assets/images/icon/iconizhuyi.png) no-repeat center;
		background-size: 100% auto;
	}
	/**/
	.contact-btn-bar{
		display: flex;
	}
	.contact-btn{
		flex: 1;
		text-align: center;
		line-height: 0.76rem;
		font-size: 0.28rem;
		color: #4d4d4d;
	}
	.contact-btn.line-one-px{
		border-top: none;
		border-right: 1px solid #e5e5e5;
		top: 0;
		left: auto;
		right: 0;
	}
</style>
