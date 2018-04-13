<template>
	<ul class="list-row">
		<li class="list-item-row" @click="toGoodsDatails" v-for="(item,index) in collectionListData" :key="item.name">
			<div class="lr-img-wrap">
				<!--<img class="list-row-img" alt="pic" :src="collectionListData[index].productImg" />-->
				<img class="list-row-img" alt="pic" src="../assets/images/list-goods/img-1.png" />
				<i class="save-icon"></i>
			</div>
			<p class="list-row-txt">{{collectionListData[index].productName}}</p>
			<div class="list-row-price">
				<span class="new-price"><em class="RMB">￥</em>{{collectionListData[index].buyPrice}}</span>
				<span class="old-price"><em class="RMB">￥</em>{{collectionListData[index].price}}</span>
			</div>
			<div class="expired-wrap" v-if="(parseInt(collectionListData[index].productID))%2===0">
				<p class="expired-txt">已失效</p>
				<a class="expired-remove-btn">删除</a>
			</div>
		</li>
	</ul>
</template>

<script>
	import { getCollectionListData } from 'api/member'
	export default{
		name: "listRow",
		data(){
			return {
				formData:{
					memberNo:"123",
					cityCode:"123"
		        },
		        collectionListData:[
	        		
	        	]
			}
		},
	    created(){
	      this.getData();
	    },
		methods:{
			toGoodsDatails(){
				this.$router.push({name:'CommodityDetails'})
			},
	      	getData(){
		        getCollectionListData(this.formData).then(res=>{
		          if(res.response_code=="S000000"){
		              this.collectionListData = res.response_data;
		            }
		          }).catch(err=>{
		            this.$store.commit('showMsgTips','请求失败，请重试！')
		          })
	      	},
		}
	}
</script>

<style>
	.list-row{
		display: flex;
		flex-wrap: wrap;
		padding-left: 0.24rem;
		padding-bottom: 0.2rem;
	}
	.list-item-row{
		margin-top: 0.2rem;
		width: calc((100% - 0.44rem) / 2);
		margin-right: 0.22rem;

		position: relative;
	}
	.lr-img-wrap{
		position: relative;
		line-height: 0;
	}
	.list-row-img{
		width: 100%;
	}
	.list-row-txt{
		color: #4d4d4d;
		font-size: 0.24rem;
		line-height: 1.2;
    padding: 0.1rem;
	}
	.list-row-keyword{
		font-size: 0.24rem;
	}
	.list-row-price{
		text-align: center;
	}
	.save-icon{
		position: absolute;
		right: 0.1rem;
		bottom: 0.1rem;
	}
	/* 失效 */
	.expired-wrap{
		position: absolute;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		background: rgba(0,0,0,.2);
		padding-top: 50%;
	}
	.expired-txt{
		font-size: 0.52rem;
		color: #f5f4f3;
		text-align: center;
	}
	.expired-remove-btn{
		margin: 0.2rem auto 0;
		display: block;
		width: 0.96rem;

		text-align: center;
		line-height: 0.32rem;
		color: #4d4d4d;
		font-size: 0.24rem;
		border-radius: 0.2rem;

		background: #fff;
	}
</style>
