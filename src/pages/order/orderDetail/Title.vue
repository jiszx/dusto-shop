<template>
	<div class="order-datail-title">
		<div class="od-title-space" v-if="titleData.type==='space'">
			<div>
				<span class="odt-space-left" v-for="item in titleData.slotLft">{{item}}</span>
			</div>
			<div class="odt-space-right-wrap">
				<span class="odt-space-right" v-for="item in titleData.slotRight">{{item}}</span>
			</div>
		</div>
		<span v-else class="od-title-center">{{titleData.text}}</span>
	</div>
</template>

<script>
	export default{
		name:"datailTitle",
		props:{
			titleData:{
				type: Object
			}
		}
	}
</script>

<style>
	.order-datail-title{
		order:-2;
		background: url(../../../assets/images/img-dd-bj.png) no-repeat;
		background-size: 100% auto;
		padding: 0 0.3rem 0 0.62rem;
		height: 1.4rem;
	}
	/*左右*/
	.od-title-space{
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 100%;
	}
	.odt-space-left,.odt-space-right{
		color: #fff;
		line-height: 1.2;
	}
	.odt-space-left{
		display: block;
		font-size: 0.32rem;
		
	}
	.odt-space-right-wrap{
		display: flex;
		flex-direction: column;
		align-items: flex-end;
	}
	.odt-space-right{
		display: block;
		font-size: 0.2rem;
	}
	/*居中*/
	.od-title-center{
		display: block;
		line-height: 1.4rem;
		text-align: center;

		font-size: 0.32rem;		
		color: #fff;
	}
</style>