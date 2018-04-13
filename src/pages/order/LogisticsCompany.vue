<template>
	<div class="logistics-company-page">
		<div class="logistics-company-title lc-name-tit">常用快递</div>
		<div class="logistics-company-list line-one-px">
			<a 
      			class="logistics-company-item line-one-px" 
      			v-for="(item,index) in sortData"
      			:key="item.typeId"
      			:class="{'lc-item-avtive':item.typeId===sortActive}"
      			@click="sortItemFun(item.typeId,index)">{{item.name}}<i class="active-icon"></i></a>
		</div>
		<div class="logistics-company-other">
			<p class="line-one-px logistics-company-title lc-other-tit">未找到相应快递请在下方输入快递名称</p>
			<field class="lc-other-input" v-model="otherLC" placeholder="请输入物流公司名称" />
		</div>
		<a class="submit-lc-btn" @click="submitCb">确认</a>
		<quick-bar></quick-bar>
	</div>
</template>

<script>
	import { Field } from 'vant';
	import QuickBar from "components/QuickBar";
	export default{
		name:"",
		components:{
			Field,
			QuickBar
		},
		data(){
			return{
				sortActive:0, // 选中项
		        sortData:[{
		        	name:"智能排序",
		        	typeId: 0
		        },{
		        	name:"距离优先",
		        	typeId: 1
		        },{
		        	name:"服务优先",
		        	typeId: 2
		        },{
		        	name:"环境优先",
		        	typeId: 3
		        }],
		        sortTxt:"智能排序",
		        otherLC: "", // 用户输入的公司名称
			}
		},
		methods:{
			// 点击排序列表
	      	sortItemFun(typeId,index){
	      		this.sortActive = typeId;
	      		this.sortTxt = this.sortData[index].name;
	      		
	      		this.$emit("shopListTabCB",0,typeId);
	      		this.aiSort = false;
	      	},
	      	// 点击确定
	      	submitCb(){
	      		this.$router.back();
	      	}
		}
	}
</script>

<style>
	.logistics-company-page{
		height: 100%;
		padding-bottom: 0.88rem;
		background: #f7f7f7;
	}
	.logistics-company-title{
		line-height: 0.76rem;
		padding-left: 0.3rem;
	}
	.lc-name-tit{
		color: #ea5361;
	}
	.lc-other-tit{
		font-size: 0.24rem;
		color: #999;
	}
	.logistics-company-list{
		padding-left: 0.3rem;
		background: #fff;
	}
	.logistics-company-item{
		padding-right: 0.3rem;
  		display: block;
  		line-height: 0.76rem;
  		text-align: left;
  		color: #1a1a1a;
  	}
  	.logistics-company-item:last-child::after{
  		border: none;
  	}
  	.lc-item-avtive{
  		color: #EA5361;
  	}
  	.lc-item-avtive .active-icon{
  		margin-top: 0.25rem;
  		float: right;
  		width: 0.4rem;
  		height: 0.26rem;
  		background: url(../../assets/images/icon/icon-xz.png) no-repeat center;
  		background-size: 100% auto;
  	}
  	.lc-other-input{
  		padding-left: 0.3rem;
  		line-height: 0.36rem;
  	}
  	.lc-other-input .van-field__control{
  		height: 0.36rem;
  	}
  	.lc-other-input::after{
  		top: auto;
  		bottom: 0;
  	}
  	/*按钮*/
  	.submit-lc-btn{
  		position: fixed;
  		right: 0;
  		bottom: 0;
  		left: 0;
  		
  		line-height: 0.88rem;
  		text-align: center;
  		color: #fff;
  		font-size: 0.32rem;
  		background: #ea5361;
  	}
</style>