<template>
	<div class="online-return">
		<!--标题-->
		<details-title :titleData="titleObj"></details-title>
		<!--注意事项-->
		<!--提交等待大东处理 -注意事项-->
		<attention class="line-one-px"></attention>
		<!--收获地址 收货人-->
		<user-address>
		</user-address>
		<div>
			<div class="or-title">填写物流信息</div>
			<div class="or-form-wrap">
				<router-link tag="div" :to="LogisticsCompany" class="logistics-company line-one-px">
					<a class="logistics-company-key">物流公司</a>
					<a class="choose-logistics">请选择物流公司<i class="more-icon"></i></a>
				</router-link>
			
			 	<field
			 		class="or-form-item"
				    v-model="logisticsNum"
				    label="物流单号"
				    icon="clear"
				    placeholder="请输入物流单号"
				    required
				    @click-icon="username = ''"/>
			
			  	<field
			  		class="or-form-item line-one-px"
				    v-model="phoneNum"
				    label="联系电话"
				    placeholder="请输入手机号码"
				    required/>
			</div>
		</div>
		<div class="or-upload-wrap">
			<div class="or-upload-title">上传凭证</div>
			<div class="or-img-list">
				<img class="or-img" alt="" src="../../assets/images/goods/banner-zx-6.png" />
				<uploader class="or-img-upload" :after-read="onRead">
				  	<i class="photograph-icon"></i>
				  	<span class="or-upload-txt">上传凭证</span>
				  	<span class="or-upload-txt">(最多3张)</span>
				</uploader>
			</div>
		</div>
		<a class="submit-btn">确认</a>
	</div>
</template>

<script>
	import { Field,Uploader } from 'vant';
	import DetailsTitle from "./orderDetail/Title"
	import Attention from "./orderDetail/Attention"
	import UserAddress from "./orderDetail/UserAddress"
	export default {
		name:"onlineReturn",
		components:{
			Field,
			Uploader,
			DetailsTitle,
			Attention,
			UserAddress
		},
		data(){
			return{
				titleObj:{
					type: "space",
					slotLft: ["请退货","并填写物流信息"],
					slotRight:['退货时间剩余：4天12小时5分']
				},
				logisticsNum:"",
				phoneNum:""
			}
		},
		methods:{
			onRead(file) {
		      console.log(file)
		    }
		}
	}
</script>

<style>
	.online-return{
		height: 100%;
		background: #f7f7f7;
		padding-bottom: 0.88rem;
	}
	/*表单*/
	.or-title{
		padding-left: 0.3rem;
		line-height: 0.76rem;
		color: #1a1a1a;
		background: #f7f7f7;
	}
	.or-form-wrap{
		padding-left: 0.3rem;
		background: #fff;
	}
	.or-form-item{
		padding: 0.14rem 0.2rem 0.14rem 0 ;
	}
	.or-form-item::before{
		top: 0.16rem;
		left: 0.04rem;
	}
	.or-form-item .van-cell__title{
		width: auto;
		top: 0.14rem;
		left: 0.2rem;
	}
	.or-form-item .van-field__control{
		color: #b3b3b3;
		font-size: 0.24rem;
	}
	.or-form-item .van-cell__value{
		padding-left: 1.42rem;
	}
	.logistics-company{
		padding-left: 0.2rem;
		height: 0.76rem;
		display: flex;
		justify-content: space-between;
		align-items: center;
		background: #fff;
	}
	.logistics-company::before{
		content: '*';
	    position: absolute;
	    left: 0.04rem;
	    color: #ea5361;

	}
	.choose-logistics{
		font-size: 0.24rem;
		margin-right: 0.3rem;
		color: #b3b3b3;
	}
	/*上传*/
	.or-upload-wrap{
		margin-top: 0.2rem;
		padding: 0.24rem 0.3rem 0;
		background: #fff;
	}
	.or-upload-title{
		line-height: 1;
		padding-bottom: 0.2rem;
	}
	.or-img-list{
		padding-left: 0.1rem;
		display: flex;
		align-items: center;
	}
	.or-img-upload{
		width: 1.4rem;
		height: 1.4rem;
		margin-bottom: 0.2rem;
		
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		
		background: #f7f7f7;
	}
	.or-img{
		padding-right: 0.1rem;
		margin-bottom: 0.2rem;
		width: 1.4rem;
		height: 1.4rem;
	}
	.photograph-icon{
		display: block;
		width: 0.5rem;
		height: 0.5rem;
		background: url(../../assets/images/icon/icon-scpz.png) no-repeat center;
		background-size: 100% auto;
	}
	.or-upload-txt{
		display: block;
		line-height: 1.2;
		font-size: 0.2rem;
		color: #ccc;
	}
	.submit-btn{
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		line-height: 0.88rem;
		text-align: center;
		font-size: 0.32rem;
		color: #fff;
		background: #ea5361;
	}
</style>
<style scoped>
	.line-one-px::after{
		top: auto;
	}
</style>