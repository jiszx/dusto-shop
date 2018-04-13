<template>
	<div class="write-evaluate">
		<div class="we-tit line-one-px">
			<span class="we-key">商品尺码：</span>
			<div class="we-size-wrap">
				<a class="we-size-type we-size-type-active">偏大</a>
				<a class="we-size-type">偏大</a>
				<a class="we-size-type">偏大</a>
			</div>
		</div>
		<div class="we-text-wrap">
			  <field
			    v-model="message"
			    label=""
			    type="textarea"
			    class="we-input-wrap"
			    placeholder="请输入留言"
			    rows="1"
			    autosize
			/>
			<div class="we-img-list">
				<img class="we-img" alt="" src="../../assets/images/goods/banner-zx-6.png" />
				<uploader class="we-img-upload" :after-read="onRead">
				  	<i class="photograph-icon"></i>
				  	<span class="we-upload-txt">添加图片</span>
				</uploader>
			</div>
			
		</div>
		<div class="we-point-wrap">
			<div class="we-tit line-one-px we-point-tit">
				<span class="we-key">店铺评分：</span>
			</div>
			
			<div class="we-writer-piont">
				<div class="we-star-wrap" 
					v-for="(item,index) in starArr"
					:key="index">
					<span class="we-piont-key">{{item.name}}</span>
					<star @starCB="starCB" :itemIndex="index"></star>
					<span class="we-point-txt">{{item.text}}</span>
				</div>
				<!--<div class="we-star-wrap">
					<span class="we-piont-key">店铺服务</span>
					<star @starCB="starCB":itemIndex="1"></star>
					<span class="we-point-txt">非常满意</span>
				</div>-->
			</div>
		</div>
		<div class="we-buy-reason">
			<div class="we-tit">
				<span class="we-key">购买原因（多选）：</span>
			</div>
			<div class="we-reason-list">
				<a class="we-reason-item">款式</a>
				<a class="we-reason-item we-reason-item_active">价格</a>
				<a class="we-reason-item">舒适度</a>
				<a class="we-reason-item we-reason-item_active">价格</a>
				<a class="we-reason-item">舒适度</a>
				<a class="we-reason-item">舒适度</a>
				
			</div>
		</div>
		<div class="evaluate-submint-wrap">
			<Button class="evaluate-submint-btn" size="large">提交</Button>
		</div>
	</div>
</template>

<script>
	import { Icon, Field, Uploader, Button } from 'vant';
	import Star from "components/Star"
	export default{
		name:"writeEvaluate",
		components:{
			Icon,
			Button,
			Field,
			Uploader,
			Star
		},
		data(){
			return{
				message: "",
				starTxt:{
					0:"",
					1:"极差",
					2:"一般",
					3:"非常满意"
				},
				starArr:[{
					name:"店铺形象",
					text:"",
					point:0
				},{
					name:"店铺服务",
					text:"",
					point:0
				}],
				mapText:0
			}
		},
		methods: {
		    onRead(file) {
		      console.log(file)
		    },
		    // 星星评论回掉
		    starCB(point,index){
		    	if(point>=4.5){
		    		this.mapText = 3;
		    	}else if(point<4.5&&point>=2){
		    		this.mapText = 2
		    	}else{
		    		this.mapText = 1
		    	}
		    	this.starArr[index].text = this.starTxt[this.mapText];
		    },
		}
	}
</script>
<style>
	.we-input-wrap .van-field__control{
		line-height: 0.4rem;
		min-height: 1rem;
	}
	.we-star-wrap .star{
		margin-right: 0.3rem;
	}
</style>
<style scoped>
	.write-evaluate{
		height: 100%;
		background: #f7f7f7;
	}
	@import url("../../assets/css/list.css");
	/*标题*/
	.we-tit{
		padding-left: 0.3rem;
		height: 0.76rem;
		display: flex;
		align-items: center;
		background: #fff;
	}
	.we-key{
		font-size: 0.28rem;
		color: #1a1a1a;
	}
	.we-point-tit.line-one-px::after{
		left: 0.3rem;
		right: 0.3rem;
	}
	/*商品尺码*/
	.we-size-wrap{
		display: flex;
	}
	.we-size-type{
		margin-left: 0.58rem;
		width: 0.98rem;
		
		text-align: center;
		line-height: 0.44rem;
		font-size: 0.24rem;
		color: #ea5361;
		border: 1px solid #ea5361;	
	}
	.we-size-type-active{
		background:  #ea5361;
		color: #fff;
	}
	/*评价文字和上传*/
	.we-input-wrap{
		padding: 0.24rem 0.3rem;
	}
	.we-input-wrap::after{
		border: none;
	}
	.we-input-wrap .van-field__control{
		line-height: 0.4rem;
	}
	.we-img-list{
		padding-left: 0.1rem;
		display: flex;
		align-items: center;
		background: #fff
	}
	.we-img-upload{
		width: 1.4rem;
		height: 1.4rem;
		margin-bottom: 0.2rem;
		
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		
		background: #f7f7f7;
	}
	.we-img{
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
	.we-upload-txt{
		font-size: 0.2rem;
		color: #ccc;
	}
	/*店铺评分*/
	.we-point-wrap{
		margin-top: 0.2rem;
		background:#fff;
	}
	.we-writer-piont{
		padding: 0.38rem 0 0.38rem 0.56rem;
	}
	.we-star-wrap{
		display: flex;
		align-items: center;
	}
	.we-star-wrap + .we-star-wrap{
		padding-top: 0.3rem;
	}
	.we-piont-key{
		font-size: 0.28rem;
		color: #999;
		margin-right: 0.24rem;
	}
	.we-point-txt{
		color: #999;
	}
	/*购买原因*/
	.we-buy-reason{
		margin-top: 0.2rem;
		background: #fff;
	}
	.we-reason-list{
		padding: 0 0.98rem 0.1rem;
		display: flex;
		flex-wrap: wrap;
	}
	.we-reason-item{
		margin-right:calc((100% - (1.38rem * 3)) / 2);
		margin-bottom: 0.3rem;
		width: 1.38rem;
		height: 0.48rem;
		border: 1px solid #ea5361;
		
		color: #ea5361;
		font-size: 0.24rem;
		text-align: center;
		line-height: 2;
	}
	.we-reason-item:nth-of-type(3n){
		margin-right: 0;
	}
	.we-reason-item_active{
		background: #ea5361;
		color: #fff;
	}
	/*按钮*/
	.evaluate-submint-wrap{
		padding: 0.88rem 0.6rem;
	}
	.evaluate-submint-btn{
		border: none;
		font-size: 0.32rem;
		color: #ea5361;
		height: 0.88rem;
	}
</style>