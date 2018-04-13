<template>
	<div class="subpage-component-new-goods">
    <div class="new-goods-title fontsize-32">
      <p>新品速递</p>
    </div>
    <div class="new-goods-list" ref="listWrapperDom">
      <ul :style="{width:data.length*(2.4+0.2)+'rem'}">
        <li v-for="(item,index) in data" :key="index" @click="toGoodsList">
          <img :src="item" alt="">
        </li>
      </ul>
    </div>
	</div>
</template>

<script>
	export default {
	  props:{
      data:{
        type:Array,
        default:[],
      }
    },
		data() {
			return {

      }
		},
    mounted(){
    },
    methods:{
    	toGoodsList(){
			this.$router.push({name:'CommodityDetails'})
		}
    }
	}
</script>

<style scoped>
.new-goods-title{
  padding-bottom:0.2rem;
  text-align: center;
}
  .new-goods-list{
    height:2.4rem;
    overflow-x: auto;
    overflow-y: hidden;

  }
.new-goods-list>ul{
  box-sizing: content-box;
  height: 100%;
  padding-bottom: 0.2rem;
}
.new-goods-list>ul>li{
  height: 100%;
  width: 2.4rem;
  float: left;
  background-color: #f7f7f7;
}
.new-goods-list>ul>li+li{
  margin-left: 0.2rem;
}
.new-goods-list>ul>li img{
  display: block;
  width: 100%;
  height: 100%;
}

</style>
