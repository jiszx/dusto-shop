<template>
	<div class="weui-content my-msg">
	    <div class="weui-panel weui-panel_access">
		    <!--<div class="weui-panel__hd">最近</div>-->
		    <div class="weui-panel__bd">
		        <msg-list-item></msg-list-item>
		    </div>
		    <div class="weui-panel__hd my-msg-tit"><i class="clock-icon"></i>两周前的消息</div>
		    <div class="weui-panel__bd">
		        <msg-list-item></msg-list-item>
		        <msg-list-item></msg-list-item>
		        <msg-list-item></msg-list-item>
		    </div>
		</div>
	</div>
</template>
<script>
	import MsgListItem from "./myMsg/MsgListItem"
  	export default {
  		name:"myMsg",
	    data (){
	      	return {
	      	}
	    },
	    components:{
	    	MsgListItem
	    }
  	}
</script>
<style>
	/*标题*/
	.my-msg-tit{
		display: flex;
		align-items: center;
		padding: 0 0.24rem;
		height: 0.76rem;
		
		font-size: 0.28rem;
		color: #1a1a1a;
		
		background: #f7f7f7;
	}
	.my-msg-tit::after{
		height: 0;
		top: 0;
		left: 0;
	}
	.clock-icon{
		display: inline-block;
		width: 0.32rem;
		height: 0.32rem;
		margin-right: 0.28rem;
		
		background: url(../../assets/images/icon/icon-shijian.png) no-repeat center;
		background-size: 100%;
	}
</style>
