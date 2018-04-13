<template>
	<div class="subpage-component-exhibition-card">
    <div class="exhibition-card-header">
      <div class="exhibition-card-header-title inline-block">
        <p class="fontsize-32">{{data.title_en}}</p>
        <p class="fontsize-26">{{data.title}}</p>
      </div>
      <div class="exhibition-card-header-more inline-block pull-right fontsize-24">
        <p @click="seeMore">
          	查看更多
          <Icon name="arrow" />
        </p>
      </div>
    </div>
    <div class="exhibition-card-goods">
      <div class="cover-image" @click="linkToDetail">
        <img v-lazy="data.image" alt="">
      </div>

      <div class="card-goods-list clearfix">
        <div v-for="(item,index) in data.goodsList" :key="index" @click="linkToDetail">
          <GoodsCard
            :image="item.image"
            :title="item.title"
            :price="item.price"
            :old_price="item.old_price"
          />
        </div>
      </div>
    </div>
	</div>
</template>

<script>
  import {
    Icon,
  } from 'vant'
  import GoodsCard from "@/components/goods-card"
	export default {
    props:{
      data:{
        type:Object,
        default:{},
      }
    },
		data() {
			return {}
		},
    components:{
      Icon,
      GoodsCard
    },
    methods:{
      seeMore(){
      	this.$router.push({name:'GoodsList'})
      },
      /**
       * 点击进入商品详情
       */
      linkToDetail(){
        this.$router.push({name:'CommodityDetails'})
      },
    }
	}
</script>

<style scoped>
.subpage-component-exhibition-card{
  box-sizing: border-box;
  padding: 0.2rem 0.24rem 0;
}
  .exhibition-card-header{
    height: 0.8rem;
    border-left: 0.04rem solid #000;
    padding-left: 0.2rem;
  }
  .exhibition-card-header-title{
    color:#666;
    line-height: 1;
  }
  .exhibition-card-header-title>P:first-child{
    color:#000;
    margin-bottom: 6px;
  }
  .exhibition-card-header-more{
    color:#666;
    line-height:0.8rem;
  }
  .exhibition-card-goods{
    padding: 14px 0;
  }
  img{
    display: block;
    width: 100%;
  }

  .card-goods-list{
    margin-top: 14px;
  }
  .card-goods-list>div{
    float: left;
    width: 32%;
  }
  .card-goods-list>div:nth-of-type(3n+2){
    margin: 0 2%;
  }
</style>
