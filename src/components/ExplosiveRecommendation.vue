<template>
    <div class="weui-panel weui-panel_access centerBPTJ explosiveOuter">
      <div class="weui-panel__hd">
        <a class="weui-cell weui-cell_access center-alloder" href="javascript:void(0);">
          <div class="weui-cell__bd wy-cell">
            <div class="weui-cell__bd weui-cell_primary"><p class="center-list-txt explosive explosiveMyHome">爆品推荐</p></div>
          </div>
          <router-link :to="{name:'GoodsList'}" tag="span" class="weui-cell__ft">查看更多</router-link>
        </a>
      </div>

      <div class="bktjOuter">
      	  <ul>
            <router-link v-for="(item,index) in newProductBurst" :to="{name:'CommodityDetails',query:{prodcutID:item.prodcutID}}" tag="li" :key="index">
              <img :src="item.productImg">
            </router-link>
      	  </ul>
      </div>
    </div>
</template>

<script>
  	import {mapState} from "vuex"
	export default{
		data(){
			return{

			}
		},
    computed:{
      ...mapState({
        newProductBurst:state => state.products.newProductBurst,
      })
    },
	    created(){
	      if(!this.newProductBurst.length){
          this.$store.dispatch('getNewProductBrust')
        }
	    },
	}
</script>

<style>
	.explosiveOuter{
		padding: 0 0.24rem;
	}
	.center-alloder{padding:0;}
	p.center-list-txt{font-size:0.28rem;color:#1a1a1a;}
	span.weui-cell__ft{font-size:0.24rem;color:#666;}
	p.explosive{font-size:0.32rem;text-align: center;padding-left:1.4rem;}

	.bktjOuter{
	   height: 2.46rem;
	   overflow-x: auto;
	   overflow-y: hidden;

	}
	.bktjOuter ul{
		width: 12rem;
		box-sizing: content-box;
	    padding-bottom: 0.2rem;
	    display: -webkit-flex; /* Safari */
    	display: flex;
	}
	.bktjOuter ul li{
	   float: left;
	   margin-right:0.2rem;
	   background-color: #f7f7f7;
	}
	.bktjOuter ul li:last-child{
	   margin-right: 0;
	}
	.bktjOuter ul li img{
	  display: block;
      width: 100%;
      height: 100%;
	}
</style>
