<template>
	<div class="gl-tab">
		<div class="weui-navbar">
            <div class="weui-navbar__item"
            	:class="active===index?'weui-bar__item_on':''"
            	v-for="(item, index) in sortData" 
            	:key="index" @click="tabFun(index)">{{item}}</div>
            <div class="weui-navbar__item glt-list-cut" @click.sync="toggerList">
            	<i class="gl-tab-icon" :class="listTypeIsCol?'gl-tab-col-icon':'gl-tab-row-icon'"></i>
            </div>
        </div>
	</div>
</template>

<script>
	export default{
		name:"goodsSortTab",
		props:{
			listTypeIsCol:{
				type: Boolean
			}
		},
		data(){
			return {
				typeBool: this.listTypeIsCol,
				active:0, // tab选中的项
				sortData:["综合","销量","新品","价格"]
			}
		},
		methods:{
			toggerList(){
				this.typeBool = !this.typeBool;
				this.$emit("toggerList",this.typeBool)
			},
			tabFun(index){
				this.active = index;
			}
		}
	}
</script>

<style>
	/*tab*/
	.gl-tab{
		position: relative;
		height: 0.76rem;
	}
	.weui-navbar{
		background: #f7f7f7;
	}
	.gl-tab .weui-navbar__item{
		padding: 0.14rem 0;
		font-size: 0.28rem;
	}
	.gl-tab .weui-navbar__item:after{
		top: 0.08rem;
		height: 0.6rem;
		border-right-color: #e5e5e5;
	}
	.glt-list-cut{
		width: 1rem;
		flex: 0 1 auto;
		
		display: flex;
		justify-content: center;
		align-items: center;
		
	}
	.gl-tab-icon{
		display: block;
		width: 0.32rem;
		height: 0.32rem;
		
		background-position: center;
		background-repeat: no-repeat;
		background-size: auto 100%;
	}
	.gl-tab-col-icon{
		background-image: url(../assets/icon/icon-lieb.png);
	}
	.gl-tab-row-icon{
		background-image: url(../assets/icon/icon-fenl.png);
	}

</style>