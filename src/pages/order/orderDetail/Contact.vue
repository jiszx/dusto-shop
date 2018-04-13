<template>
	<div class="contact-btn-bar line-one-px">
    <router-link class="contact-btn line-one-px" :to="{name:'Contact'}">联系客服</router-link>
		<a class="contact-btn" href="tel:4008001234">拨打电话</a>
	</div>
</template>

<script>
</script>

<style scoped>
	/*联系*/
	.contact-btn-bar{
		display: flex;
		background: #fff;
	}
	.contact-btn{
		flex: 1;
		text-align: center;
		line-height: 0.76rem;
		font-size: 0.28rem;
		color: #4d4d4d;
	}
	.contact-btn.line-one-px{
		border-top: none;
		border-right: 1px solid #e5e5e5;
		top: 0;
		left: auto;
		right: 0;
	}
</style>
