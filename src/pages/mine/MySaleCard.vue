<template>
	<div class="my-sale-card">
		<Tabs :active="activeTab">
          <Tab v-for="(item,index) in tabNavData" :key="index" :title="item.title"></Tab>
        </Tabs>
        <div class="my-sc-list">
        	<div class="cateTitle">通用</div>
		    <div class="ticket">
		      	<div class="number">
			        <span class="yuan">10元</span>
			        <span class="guize">满99元使用</span>
		      	</div>
		      	<div class="detail">
			        <div class="title">下沈线大东10元店铺优惠券</div>
			        <div class="time">有效期：2018/01/28 - 2018/12/12</div>
		      	</div>
		    </div>
		    <div class="cateTitle">线下</div>
		    <div class="ticket">
		      	<div class="number">
			        <span class="yuan">10元</span>
			        <span class="guize">满99元使用</span>
		      	</div>
		      	<div class="detail">
			        <div class="title">下沈线大东10元店铺优惠券</div>
			        <div class="time">有效期：2018/01/28 - 2018/12/12</div>
		      	</div>
		    </div>
        </div>
        <quick-bar></quick-bar>
	</div>
</template>

<script>
	import { Tab, Tabs, Radio } from 'vant';
	import QuickBar from "components/QuickBar";
	export default{
		name:"",
		components:{
			Tab,
			Tabs,
			QuickBar
		},
		data(){
			return{
				activeTab:0,
				tabNavData:[{
		          	title:'未使用(2)'
		        },{
		          	title:'已使用(0)'
		        },{
		          	title:'已过期(8)'
		        }],
		        radio: "1"
			}
		}	
	}
</script>

<style>
	.my-sale-card .van-tabs__nav{
		justify-content: space-around;
		background: #f7f7f7;
	}
	.my-sale-card .van-tab{
		flex: 0 1 auto;
		padding: 0 0.2rem;
		background: #f7f7f7;
	}
	/*卡券*/
	.cateTitle {
	    height: 52px;
	    padding-left: 18px;
	    padding-top: 30px;
	    font-size: 18px;
	    color: #000;
	    font-weight: bold;
	}
  	.ticket {
	    background: url("../../assets/images/img-yhq-ty.png") no-repeat;
	    background-size: 100% 100%;
	    height: 153px;
	    display: flex;
	    flex-direction: row;
	}
	
	.ticket .number {
	    flex-grow: 10;
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    justify-content: center;
	}
	
	.ticket .number .yuan {
	    color: #ea5361;
	    font-size: 30px;
	}
	
	.ticket .number .guize {
	    font-size: 10px;
	    color: #b3b3b3;
	}
	
	.ticket .detail {
	    flex-grow: 12;
	    text-align: center;
	    justify-content: flex-start;
	    display: flex;
	    padding-top: 38px;
	    flex-direction: column;
	}
	
	.ticket .detail .title {
	    font-size: 14px;
	    font-weight: bold;
	    margin-bottom: 24px;
	}
	
	.ticket .detail .time {
	    color: #b3b3b3;
	    font-size: 10px;
	}

</style>
<style scoped>
	.my-sc-list{
		padding-top: 0.3rem;
	}
	.cateTitle{
		height: auto;
		padding-top: 0.3rem;
		padding-bottom: 0.1rem;
		line-height: 1;
	}
</style>