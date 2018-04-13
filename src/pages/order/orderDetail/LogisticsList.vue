<template>
	<Steps class="logistic-list" direction="vertical" :active="active" active-color="#000">
	  	<Step v-for="(item,index) in data" :key="index">
	    	<div class="logistic-text">{{item.wmsDesc}}</div>
	    	<div class="step-timer-wrap">
	    		<p class="step-time-txt">{{$mUtils.formatDate(item.wmsTime,'yyyy-MM-dd')}}</p>
	    		<p class="step-time-txt">{{$mUtils.formatDate(item.wmsTime,'hh-mm-ss')}}</p>
	    	</div>
	  	</Step>
	</Steps>
</template>

<script>
	import { Step, Steps } from 'vant';
	export default{
		name:"step",
    props:['data'],
		components:{
			Step,
			Steps
		},
		data(){
			return{
				active: 0,
			}
		}
	}
</script>

<style scoped>
	.logistic-list{
		padding-left: 2.2rem;
	}

	.logistic-list .van-step{
		font-size: 0.2rem;
	}
	.step-timer-wrap{
    position: absolute;
    top: 0.2rem;
    left: -2.15rem;
    width: 1.6rem;
    text-align: right;
	}
	.step-time-txt{
		font-size: 0.16rem;
		color: #ea5361;
	}
	.logistic-list .van-step--vertical:not(:last-child)::after{
		border: 0;
	}
	.logistic-list .van-step{
		min-height: 1rem;
	}
</style>
