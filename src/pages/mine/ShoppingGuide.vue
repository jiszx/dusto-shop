<template>
	<div class="shopping-guide">
		<div class="sg-head">
			<img class="sg-head-bg" alt="" :src="shoppingGuide.salesImg" />
			<div class="sg-head-wrap">
				<img class="sg-head-img" alt="" :src="shoppingGuide.salesImg" />
				<div class="sg-head-info">
					<router-link tag="div" :to="{name:'GuideEvaluate'}" class="sg-name-wrap">
						<span class="sg-name">{{shoppingGuide.name}}</span>
						<star class="sg-star-wrap" :isWrite="false"></star>
					</router-link>
					<p class="sg-head-address">{{shoppingGuide.shopName}}</p>
					<span class="sg-head-pgone">
						<Icon name="phone sg-phone-icon"></Icon>联系电话：{{shoppingGuide.phone}}
					</span>
				</div>

			</div>
		</div>
		<div>
			<div class="sg-tit line-one-px"><i class="sg-tit-icon"></i>服务记录</div>
			<ul>
				<li>
					<div class="list-item line-one-px">
						<img class="goods-img" alt="" src="../../assets/images/list-goods/img-2.png" />
						<div class="goods-text">
							<p class="goods-txt">2019新品上市</p>
							<p class="goods-buy-time">购买时间：2017-12-12</p>
						</div>
					</div>
				</li>
				<li>
					<div class="list-item line-one-px">
						<img class="goods-img" alt="" src="../../assets/images/list-goods/img-2.png" />
						<div class="goods-text">
							<p class="goods-txt">2019新品上市</p>
							<p class="goods-buy-time">购买时间：2017-12-12</p>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<quick-bar></quick-bar>
	</div>
</template>

<script>
	import { Icon } from 'vant';
  import Star from "components/Star"
  import QuickBar from "components/QuickBar";
  import { mapState } from 'vuex';
	export default{
    data(){
		  return{
		    formData:{
		      phone:'18200000000',
          memberNo:'25000105079',
        },
      }
    },
    computed:{
      ...mapState({
        shoppingGuide:state => state.mine.shoppingGuide,
      })
    },
    created(){
      console.log(this.shoppingGuide)
		  this.$store.dispatch('guiderGetInfo',this.formData)
    },
		components:{
			Icon,
			Star,
			QuickBar
		}
	}
</script>
<style>
	.sg-star-wrap{
		margin-left: 0.12rem;
	}
	.sg-star-wrap .star{
		margin-left: 0.12rem;
	}
</style>
<style scoped>
	@import url("../../assets/css/list.css");
	.sg-head{
		position: relative;
		/*height: 4.6rem;*/
		text-align: center;
		overflow: hidden;
	}
	.sg-head-bg{
		position:absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		filter: blur(3px);
		z-index: -1;
		transform: scale(1.2) translate(-8%,-8%);
	}
	.sg-head-wrap{
		padding: 0.36rem 0 0.4rem;
		background: rgba(0,0,0,.2);
		background:-moz-linear-gradient(top, rgba(0,0,0,0), rgba(0, 0, 0, 0.5));
		background: -webkit-gradient(linear, 0 0, 0 bottom, from(rgba(0,0,0,0)), to(rgba(0, 0, 0, 0.5)));
		background:-o-linear-gradient(top, rgba(0,0,0,0), rgba(0, 0, 0, 0.5));
		color: #fff;
	}
	.sg-head-img{
		display: block;
		margin: 0 auto;
		width: 2.2rem;
		height: 2.2rem;
		border-radius: 50%;
		border: 0.06rem solid rgba(255,255,255,.5);
		line-height: 0;
		box-sizing: border-box;
	}
	.sg-head-info{
		line-height: 1;
	}
	.sg-name-wrap{
		margin-top: 0.28rem;
		display: flex;
		justify-content: center;
	}
	.sg-name{
		font-size: 0.32rem;
	}
	.sg-head-address{
		margin-top: 0.24rem;
		font-size: 0.24rem;
	}
	.sg-head-pgone{
		display: block;
		margin-top: 0.26rem;
		font-size: 0.28rem;
	}
	.sg-phone-icon{
		vertical-align: middle;
		font-size: 0.28rem;
		margin-right: 0.12rem;
	}
	/*tit */
	.sg-tit{
		height: 0.76rem;
		display: flex;
		align-items: center;

		background: #f7f7f7;
		padding-left: 0.3rem;

		font-size: 0.28rem;
		color: #1a1a1a;
	}
	.sg-tit-icon{
		display: inline-block;
		width: 0.32rem;
		height: 0.32rem;
		margin-right: 0.28rem;

		background: url(../../assets/images/icon/icon-shijian.png) no-repeat center;
		background-size: 100%;
	}
	/*列表样式*/
	.list-item{
		display: flex;
		padding: 0.2rem 0.24rem;
	}
	.list-item::after{
		left: 0.24rem;
	}
	.goods-img{
		width: 1.2rem;
		height: 1.2rem;
	}
	.goods-text{
		padding-left: 0.24rem;
		flex: 1;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	.goods-txt{
		margin-top: 0.06rem;
		line-height: 0.36rem;
		font-size: 0.28rem;
		height: 0.72rem;
		overflow: hidden;
		-webkit-line-clamp: 2;
	}
	.goods-buy-time{
		font-size: 0.2rem;
		color: #999;
	}
</style>
