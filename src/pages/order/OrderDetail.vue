<template>
	<div
		class="order-datail cart-goods"
		:class="{'order-datail-padding':componentsArr[type][10]}">
		<!--title-->
		<detail-title
			v-if="componentsArr[type][0]"
			:titleData="titleData[type]">
		</detail-title>
		<!--提交等待大东处理 -注意事项-->
		<attention v-if="componentsArr[type][1]"></attention>
		<!--门店信息-->
		<store-info v-if="componentsArr[type][2]" :orderDetailData="orderDetailData"></store-info>
		<!--退换货审核不通过-->
		<apply-failed v-if="componentsArr[type][3]"></apply-failed>
		<!--交易成功或已发货 物流块-->
		<logistics-wrap v-if="componentsArr[type][4]"></logistics-wrap>
		<!--收获地址 收货人-->
		<user-address
			v-if="componentsArr[type][5]"
      :orderDetailData="orderDetailData"
			v-show="!foldString">
		</user-address>
		<!--商品信息-->
		<goods-info
			v-if="componentsArr[type][6]"
      :orderDetailData="orderDetailData"
			:orderType="type">
		</goods-info>
		<!--订单信息-->
		<order-info
			v-if="componentsArr[type][7]"
			:orderType="type"
			:class="{'order-info-top':type===8||type===9}">
		</order-info>
		<!--物流列表-->
		<logistics-list
			v-if="componentsArr[type][8]"
      :orderDetailData="orderDetailData"
			v-show="!foldString">
		</logistics-list>
		<!--折叠-->
		<div v-show="type===8" class="fold-wrap line-one-px">
			<i class="fold-icon"
				:class="{'fold-open':foldString}"
				@click="foldFun"></i>
		</div>
		<!--推荐-->
		<div class="hot-recommend"  v-if="componentsArr[type][9]">
			<explosive-recommendation :orderDetailData="orderDetailData"></explosive-recommendation>
		</div>
		<!--联系-->
		<contact v-if="type===5||type===7" :class="{'contact-spec':type===5}"></contact>
		<!-- 取消售后按钮 -->
		<Button size="large" v-if="type===7" class="cancel-order-btn">取消售后</Button>
		<!-- 底部按钮-->
		<div class="order-detail-btn-bar" v-if="componentsArr[type][10]">
			<a class="pub-btn" @click="showAS('cancel')">取消订单</a>
			<a class="pub-btn pay-btn" @click="showAS('pay')">支付</a>
		</div>

		<!--退换货处理结果-审核通过-选中换货渠道-->
		<choose-type v-if="componentsArr[type][11]"></choose-type>
		<!--取消订单原因-->
		<ActionSheet :showAs="showAs" @hideAs="hideAs">
			<div v-if="isShowAs.cancel">
				<div class="as-top-bar">
					<a class="as-top-btn" @click="overChoose">完成</a>
				</div>
				<picker :columns="columns" @change="choooseReson"></picker>
			</div>
			<div v-if="isShowAs.pay">
				<div class="as-top-bar">
					请选择支付方式
					<a class="as-top-btn" @click="cancelPay">取消</a>
				</div>
				<div class="as-pay-list">
					<div class="pay-list-item line-one-px">
						<a class="pay-type">微信支付<em class="recommend-type">推荐使用</em></a>
						<i class="more-icon"></i>
					</div>
					<div class="pay-list-item line-one-px">
						<a class="pay-type">支付宝支付</a>
						<i class="more-icon"></i>
					</div>
					<div class="pay-list-item line-one-px">
						<a class="pay-type">银行卡支付</a>
						<i class="more-icon"></i>
					</div>
				</div>
			</div>
		</ActionSheet>
		<quick-bar></quick-bar>
	</div>
</template>

<script>
	import { Picker, Button } from 'vant';
	import ActionSheet from "components/ActionSheet";
	import DetailTitle from "./orderDetail/Title"
	import Attention from "./orderDetail/Attention"
	import StoreInfo from "./orderDetail/StoreInfo"
	import ApplyFailed from "./orderDetail/ApplyFailed"
	import LogisticsWrap from "./orderDetail/LogisticsWrap"
	import UserAddress from "./orderDetail/UserAddress"
	import GoodsInfo from "./orderDetail/GoodsInfo"
	import OrderInfo from "./orderDetail/OrderInfo"
	import ExplosiveRecommendation from "components/ExplosiveRecommendation";
	import LogisticsList from "./orderDetail/LogisticsList"
	import ChooseType from "./orderDetail/ChooseType"
	import Contact from "./orderDetail/Contact"
	import QuickBar from "components/QuickBar";

  import { getOrderDetail } from 'api/order'

  import mixinOrderType from 'mixins/orderType'
  	export default {
  		name:"orderDetail",
      mixins:[mixinOrderType],
  		components:{
  			Picker,
  			Button,
  			ActionSheet,
  			DetailTitle, // 头部
  			ChooseType, // 退换货处理结果-审核通过-选中换货渠道
  			ApplyFailed, // 退换货处理结果-审核未通过
  			Attention, // 注意事项
  			StoreInfo, // 门店信息
  			LogisticsWrap, // 交易成功或已发货 物流块
  			UserAddress, // 联系人信息
  			GoodsInfo, // 商品信息
  			OrderInfo, // 订单信息
  			LogisticsList, // 物流列表
  			ExplosiveRecommendation, // 爆款推荐
  			Contact, // 联系按钮
  			QuickBar
  		},
  		data(){
  			return{
  				/*
  				 * 1: 买家已付款
  				 * 2: 待评价
  				 * 3：等待买家付款
  				 * 4：等待买家付款 - 使用积分兑换
  				 * 5: 提交等待商家处理
  				 * 6: 退换货处理结果-审核通过-选择换货渠道
  				 * 7： 退换货处理结果-审核不通过
  				 * 8: 退货成功
  				 * 9： 退货详情-在线退货详情-退货物流信息
  				 * 10：退换货处理结果 - 大东已发货    或 待收货
  				 * 11：线下退换货处理情况 - 大东已同意退款 您需要退货
  				 * 12：3.8.2.1 订单详情--商品待发货
  				 */

  				type: 7,
  				titleData:{
  					1:{
  						type: "space",
  						slotLft: ["买家已付款"],
  						slotRight:['发货时间：发货后5天内']
  					},
  					2:{
  						type: "center",
  						text:"交易成功"
  					},
  					3:{
  						type: "space",
  						slotLft: ["等待买家付款"],
  						slotRight:["剩余：8小时25分自动关闭","需要付款：￥78.00"]
  					},
  					4:{
  						type: "space",
  						slotLft: ["等待买家付款"],
  						slotRight:["剩余：8小时25分自动关闭","需要付款：￥78.00"]
  					},
  					5:{
  						type: "space",
  						slotLft: ["请等待大东处理"],
  						slotRight:["剩余：2天8小时25分自动关闭"]
  					},
  					6:{
  						type: "space",
  						slotLft: ["大东已同意审核成功","选择渠道换货"],
  						slotRight:["剩余：2天8小时25分自动关闭"]
  					},
  					7:{
  						type: "center",
  						text:"很抱歉，退换货审核不通过"
  					},
  					8:{
  						type: "center",
  						text:"退货成功"
  					},
  					9:{
  						type: "center",
  						text:"退货物流信息"
  					},
  					10:{
  						type: "space",
  						slotLft: ["大东已发货"],
  						slotRight:["剩余：2天8小时25分自动关闭"]
  					},
  					11:{
  						type: "space",
  						slotLft: ["大东已同意退款","需要您退货"],
  						slotRight:["剩余：2天8小时25分自动关闭"]
  					}
  				},
  				columns:[{
  					text:"我不想买了",
  					id:0
  				},{
  					text:"信息填写错误从新拍",
  					id:1
  				},{
  					text:"卖家缺货",
  					id:2
  				},{
  					text:"同城见面交易",
  					id:3
  				}],
  				componentsArr:{
					1:[true,false,false,false,false,true,true,true,false,true,false,false],
  					2:[true,false,false,false,true,true,true,true,false,true,false,false],
  					3:[true,false,false,false,false,true,true,true,false,false,true,false],
  					4:[true,false,false,false,false,true,true,true,false,false,true,false],
  					5:[true,true,false,false,false,false,true,true,true,false,false,false],
  					6:[true,false,false,false,false,false,false,false,false,false,false,true],
  					7:[true,false,false,true,false,false,false,false,false,false,false,false],
  					8:[true,false,false,false,false,true,false,true,true,true,false,false],
  					9:[true,false,false,false,false,true,false,true,true,true,false,false],
  					10:[true,false,false,false,true,true,true,true,false,true,false,false],
  					11:[true,true,true,false,false,false,true,true,false,false,false,false]},
  				showAs: false,
  				isShowAs: {
  					cancel: false,
  					pay:false
  				},
  				foldString: false, // 折叠
          formData:{
            orderNo:'123000533301',
          },
          orderDetailData:{
            orderStatus: "",
            signMsg: null,
            signTime: null,
            recevieName: "",
            recevieAddress: "",
            receviePhone: "",
            totalCount: "",
            totalPayAmount: "",
            orderNo: "",
            createDate: "",
            payDate: null,
            deliverDate: null,
            productList: []
          },
  			}
  		},
  		created(){
			  this.init();
			  this.getData();
  		},
  		methods:{
  			init(){
  				this.type = parseInt(this.$route.query.type) || this.type;
  			},
  			// 显示弹层内容
  			showAS(type){
  				for(var key in this.isShowAs){
  					this.isShowAs[key] = false
  				}
  				this.showAs = true
  				this.isShowAs[type] = true;
  			},
  			choooseReson(){
  			},
  			hideAs(){
  				this.showAs = false
  			},
  			// 折叠事件
  			foldFun(){
  				this.foldString = !this.foldString;
  			},
  			// 取消订单-完成
  			overChoose(){
  				this.showAs = false
  			},
  			// 支付 - 取消
  			cancelPay(){
  				this.showAs = false
  			},

        getData(){
          getOrderDetail(this.formData).then(res=>{
            if(res.response_code=='S000000'){
              this.orderDetailData = res.response_data;
              this.type = parseInt(this._getOrderType(this.orderDetailData.orderStatus));
              this.orderDetailData.orderType = this.type;
            }
          }).catch(err=>{
            this.$store.commit('showMsgTips','请求失败，请重试！')
          })
        },
  		}
  	}
</script>

<style>
	.order-datail{
		min-height: 100%;
		display: flex;
		flex-direction: column;
		background: #f7f7f7;
	}
	.order-datail-padding{
		padding-bottom: 1rem;
		position: relative;
	}
	.hot-recommend{
		border-top: 0.2rem solid #f7f7f7;
	}
	.order-detail-btn-bar{
		position: absolute;
		bottom: 0;
		left: 0;
		right: 0;
		padding-right: 0.3rem;
		height: 1rem;
		display: flex;
		justify-content: flex-end;
		align-items: center;
		background: #fff;
	}
	.pub-btn{
		width: 1.96rem;
		line-height: 0.6rem;
		text-align: center;
		border: 1px solid #999;
		font-size: 0.3rem;
		color: #4d4d4d;
	}
	.pub-btn + .pub-btn{
		margin-left: 0.2rem;
	}
	.pay-btn{
		border-color: #ea5361;
		color: #fff;
		background: #ea5361;
	}
	/*取消订单弹出框*/
	.as-top-bar{
		position: relative;
		height: 0.76rem;
		line-height: 0.76rem;
		background: #f7f7f7;
		text-align: center;
		color: #000;
	}
	.as-top-btn{
		position: absolute;
		right: 0.3rem;
		top: 0;
	}
	/*支付方式弹出层*/
	.pay-list-item{
		height: 0.88rem;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 0 0.3rem;
		background: #fff;
	}
	.pay-list-item::after{
		left: 0.3rem;
		right: 0.3rem;
	}
	.more-icon{
		margin-left: 0.2rem;
		display: inline-block;
		width: 0.1rem;
		height: 0.2rem;

		background: url(../../assets/images/icon/icon-right.png) no-repeat center;
		background-size: 100%;
	}
	.recommend-type{
		display: inline-block;
		margin-left: 0.2rem;
		width: 0.98rem;
		font-size: 0.2rem;
		text-align: center;
		line-height: 0.36rem;
		border: 1px solid #ea5361;
		border-radius: 0.1rem;
		color: #ea5361;
	}
	/*联系*/
	.contact-spec{
		border-top: 0.1rem solid #f7f7f7;
	}
	/*取消按钮样式*/
	.cancel-order-btn{
		margin-top: 0.88rem;
		height: 0.88rem;
		border-left: none;
		border-right: none;
		border-radius: initial;
		color: #4d4d4d;
	}
	/* 把订单信息栏上移 */
	.order-info-top.order-info{
		border-top: none;
		order: -1;
	}
	/* 折叠按钮 */
	.fold-wrap{
		background: #fff;
	}
	.fold-wrap::before{
		content: "";
	  	position: absolute;
	  	top: 0;
	  	left: 0;
	  	right: 0;
	  	height: 0;
	  	border-top: 1px #e5e5e5 solid;
	  	transform: scaleY(0.5);
	}
	.fold-icon{
		display: block;
		height: 0.48rem;
		background: url(../../assets/images/icon/icon-up.png) no-repeat center;
		background-size: auto 30%;
	}
	.fold-open{
		transform: rotate(180deg);
	}
</style>
