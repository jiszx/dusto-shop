<template>
	<div class="logistics-page">
		<div class="l-adress line-one-px">
			<span class="l-adress-key">收货地址：</span>
			<p>四川省 成都市 高新区 火车南站火车南站火车南站火车南站火车南站</p>
		</div>
		<div class="l-tab">
			<a class="l-tab-item" v-for="(item, index) in tabNavData" :key="index" :class="{'l-tab-item--active':activeTab===index}" @click="activeTab=index">{{item.title}}</a>
		</div>
		<div class="l-img-list">
			<img class="l-img" alt="goods" src="../../assets/images/list-goods/img-2.png" />
			<img class="l-img" alt="goods" src="../../assets/images/list-goods/img-2.png" />
			<img class="l-img" alt="goods" src="../../assets/images/list-goods/img-2.png" />
		</div>
		<!--订单信息-->
		<order-info
			class="lp-order-info"
			:isPropsData="true"
			:PropsData="Logistics">
		</order-info>
		<!--物流列表-->
		<div class="lp-wrap">
			<logistics-list :data="Logistics"></logistics-list>
		</div>
		<quick-bar></quick-bar>
	</div>
</template>

<script>
	import { Tab, Tabs} from 'vant';
	import OrderInfo from "./orderDetail/OrderInfo"
	import LogisticsList from "./orderDetail/LogisticsList"
	import QuickBar from "components/QuickBar";
	import { getWmsHistoryInfo } from 'api/order';
	export default{
		name:"logistics",
		components:{
			Tabs,
			Tab,
			OrderInfo,
			LogisticsList,
			QuickBar
		},
		data(){
			return{
				activeTab:0,
				tabNavData:[{
		          	title:'订单一'
		        },{
		          	title:'订单二'
		        },{
		          	title:'订单三'
		        },{
		          	title:'订单三'
		        },{
		          	title:'订单三'
		        }],
				Logistics:[],
        formData:{
          orderNo:1,
        },
			}
		},
    created(){
      this.wmsData();
    },
    methods:{
      wmsData(){
        getWmsHistoryInfo(this.formData)
          .then(res=>{
              this.Logistics = res.response_data
          })
          .catch(err=>{
            console.log(err);
          })
      },
    },
	}
</script>

<style>
	/*收货地址*/
	.l-adress{
		padding: 0.16rem 0.3rem 0.26rem 0.3rem;
		display: flex;
		color: #f8b500;
		font-size: 0.24rem;
		line-height: 1.2;
		background: #f7eac6;
	}
	.l-adress::after{
		border-color: #f8b500;
	}
	.l-adress-key{
		white-space: nowrap;
	}
	/*tab*/
	.l-tab{
		display: flex;
		flex-wrap: wrap;
		padding: 0.3rem 0 0.1rem 0.3rem;
	}
	.l-tab-item{
		margin-right: 0.3rem;
		margin-bottom: 0.2rem;
		width: calc((100% - 0.9rem) / 3);

		line-height: 0.88rem;
		text-align: center;
		border: 1px solid #999;
		color: #4d4d4d;
	}
	.l-tab-item--active{
		border-color: #ea5361;
		color: #ea5361;
		background: #fff;
	}
	/*图片*/
	.l-img-list{
		padding: 0.2rem 0.3rem;
		background: #fff;
		font-size: 0;
	}
	.l-img{
		width: 1.2rem;
		height: 1.2rem;
	}
	.l-img + .l-img{
		margin-left: 0.2rem;
	}
	.logistics-page{
		height: 100%;
		background: #f7f7f7;
	}
	.logistics-page .lp-order-info{
		border-top: none;
	}
	.lp-wrap{
		margin-top: 0.2rem;
	}
</style>
