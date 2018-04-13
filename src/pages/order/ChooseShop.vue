<template>
	<div class="choose-shop">
		<search v-model="value" show-action>
	      	<i slot="action" class="location-icon"></i>
	   	</search>
	   	<shop-list-tab @shopListTabCB="shopListTabCB"></shop-list-tab>
	    <ul class="choose-shop-list">
			<li class="choose-shop-item line-one-px" v-for="shop in shopList" @click="toShopDetail(shop.id)">
				<radio name="2" v-model="radio">
					<shop-item :itemData="shop"></shop-item>
				</radio>
			</li>
		</ul>
		<router-link tag="a" :to="{name:'OrderDetail',params:{type:11}}" class="choose-shop-submit">确认门店</router-link>
		<quick-bar></quick-bar>
	</div>
</template>

<script>
  import { Search, Radio} from 'vant';
  import ShopListTab from "components/ShopListTab"
  import QuickBar from "components/QuickBar";
  import ShopItem from "components/ShopItem"
  import {mapState} from "vuex"

  export default {
  	components: {
      	Search,
      	QuickBar, // 快速导航
      	Radio,
      	ShopListTab, // tab
      	ShopItem,
    },
    data(){
      return {
        value:"",
        radio: 2
      }
    },
    methods: {
      	toShopDetail(id) {
       	 	this.$router.push({name: "shopDetail", id: id});
      	},
      	// tab 回掉函数 parm type类型 id选择排序的id
      	shopListTabCB(type,id){
      		console.log(type,id)
      	}
    },
    
    computed: {
      ...mapState({
        shopList: state => state.shop.shopList
      })
    }
  };
</script>

<style>
	.choose-shop{
		padding-bottom: 0.88rem;
	}
	/*地址图标表*/
	.location-icon{
		display: block;
		width: 0.44rem;
		height: 0.44rem;
		margin: 0 0.3rem;
		
		background: url(../../assets/images/icon-dt.png) no-repeat center;
		background-size: 100%;
	}
	/*列表*/
	.choose-shop-item .van-radio{
		display: flex;
		align-items: center;
	}
	.choose-shop-item .van-radio__label{
		flex: 1;
	}
  	.choose-shop-list{
  		padding-left: 0.24rem;
  	}
  	/*按钮*/
  	.choose-shop-submit{
  		position: fixed;
  		bottom: 0;
  		left: 0;
  		right: 0;
  		
  		line-height: 0.88rem;
  		text-align: center;
  		color: #ea5361;
  		font-size: 0.32rem;
  		background: #f7f7f7;
  	}
</style>
