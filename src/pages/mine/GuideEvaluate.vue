<template>
	<div class="guide-evaluate">
		<div class="ge-star">
			<p class="ge-star-tit">您对你的专属导购总体是否满意：</p>
			<div class="ge-star-wrapper">
				<star class="ge-star-wrap" v-model="formData.grade"></star>
				<div class="ge-star-text">
					<span class="ge-star-txt">极差</span>
					<span class="ge-star-txt">一般</span>
					<span class="ge-star-txt">非常满意</span>
				</div>
			</div>
		</div>
		<div class="ge-question-list">
			<div class="ge-question-item">
				<p class="ge-question-txt line-one-px">专属导购员对你购物时有帮助：</p>
				<ul class="ge-anwer-list">
					<li>
						<radio class="ge-radio line-one-px" :name="0" v-model="formData.isRate">很不错</radio>
					</li>
					<li>
						<radio class="ge-radio line-one-px" :name="1" v-model="formData.isRate">好</radio>
					</li>
					<li>
						<radio class="ge-radio" :name="2" v-model="formData.isRate">一般</radio>
					</li>
          <li>
            <radio class="ge-radio" :name="3" v-model="formData.isRate">反应慢</radio>
          </li>
				</ul>
			</div>
			<div class="ge-question-item">
				<p class="ge-question-txt line-one-px">专属导购员对你购物时有帮助：</p>
				<ul class="ge-anwer-list">
					<li>
						<radio class="ge-radio line-one-px" :name="0" v-model="formData.isHelp">很多帮助</radio>
					</li>
					<li>
            <radio class="ge-radio line-one-px" :name="1" v-model="formData.isHelp">还不错</radio>
					</li>
					<li>
            <radio class="ge-radio line-one-px" :name="2" v-model="formData.isHelp">一般</radio>
					</li>
          <li>
            <radio class="ge-radio line-one-px" :name="3" v-model="formData.isHelp">完全没帮助</radio>
          </li>
				</ul>
			</div>
		</div>

		<div class="evaluate-submint-wrap">
			<Button class="evaluate-submint-btn" size="large" @click="submit">提交</Button>
		</div>
		<quick-bar></quick-bar>
	</div>
</template>

<script>
	import { Radio, Button, RadioGroup } from 'vant';
	import Star from "components/Star";
	import QuickBar from "components/QuickBar";
	export default{
		name:"guideEveluate",
		components:{
			Radio,
      RadioGroup,
			Button,
			Star,
			QuickBar
		},
		data(){
			return{
        radio:1,
				formData:{
          isRate:0,
          isHelp:0,
          grade:0,
          memberNo:'123',
        },
			}
		},
    mounted(){

    },
    methods:{
      /**
       * 提交评论
       */
      submit(){
        if(!this.formData.grade){
          this.$store.dispatch('showMsgTips','请为导购满意度评星级');
          return;
        }
        this.$store.dispatch('guiderSubmitEvaluate',this.formData)
      },
    },
	}
</script>
<style>
	.guide-evaluate{
		height: 100%;
		background: #f7f7f7;
	}
	/*星星*/
	.ge-star-wrap{
		padding-right: 0.26rem;
	}
	.ge-star-wrap .star{
		margin-top: 0.34rem;
		width: 0.56rem;
		height: 0.56rem;
	}
</style>
<style scoped>
	@import url("../../assets/css/list.css");
	.ge-star-wrapper{
		width: 70%;
		margin: 0 auto;
	}
	/*评价区*/
	.ge-star{
		padding: 0.24rem 0.3rem;
		background: #fff;
	}
	.ge-star-tit{
		font-size: 0.28rem;
		color: #1a1a1a;
		line-height: 1;
	}
	.ge-star-text{
		margin-top: 0.34rem;
		display: flex;
		justify-content: space-between;
	}
	.ge-star-txt{
		font-size: 0.28rem;
		color: #999;
		line-height: 1;
	}
	/*问题*/
	.ge-question-list{
		background: #fff;
	}
	.ge-question-txt{
		padding-left: 0.3rem;
		line-height: 0.76rem;
		font-size: 0.28rem;
		color: #1a1a1a;

		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;

		background: #f7f7f7;
	}
	.ge-question-txt.line-one-px::before{
		content: "";
	  	position: absolute;
	  	top: 0;
	  	left: 0;
	  	right: 0;
	  	height: 0;
	  	border-top: 1px #e5e5e5 solid;
	  	transform: scaleY(0.5);
	}
	.ge-radio{
		padding-left: 0.24rem;

		height: 0.76rem;
		display: flex;]
		justify-content: space-between;
		align-items: center;
	}
	.ge-anwer-list .line-one-px::after{
		left: 0.3rem;
	}
	/*按钮*/
	.evaluate-submint-wrap{
		padding: 0.68rem 0.6rem 0.6rem;
	}
	.evaluate-submint-btn{
		border: none;
		font-size: 0.32rem;
		color: #ea5361;
		height: 0.88rem;
	}
</style>
