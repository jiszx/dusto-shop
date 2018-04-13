<template>
  <div class="page-service-type">
    <OrderDetailsRow />
    <OrderSource />

    <div class="typeMark">
        <span class="resTotal">共：<span class="">1</span>件商品 
          <span class="">实付：<span class="presentPrice">&yen118.00</span></span>
        </span>
      <p>(含运费&yen<span class="">0.00</span>)</p>
    </div>

    <ul class="operation-wrapper">
        <li class="operation-cell-btn van-hairline--bottom" @click="linkTo('money')">
          <div class="text">
            <p class="title fontsize-28 paddingB10">仅退款</p>
            <p class="gray fontsize-20 lineheight-32">未收到货(包含未签收)，或卖家协商同意的前提下</p>
          </div>
          <div class="gray arrow-icon-wrapper text-right">
            <Icon name="arrow" class="arrow-icon inline-block"></Icon>
          </div>
        </li>
        <li class="operation-cell-btn van-hairline--bottom" @click="linkTo('moneyAndGoods')">
          <div class="text">
            <p class="title fontsize-28 paddingB10">退货退款</p>
            <p class="gray fontsize-20 lineheight-32">已收到货，需要退换已收到的商品</p>
          </div>
          <div class="gray arrow-icon-wrapper text-right">
            <Icon name="arrow" class="arrow-icon inline-block"></Icon>
          </div>
        </li>
        <li class="operation-cell-btn van-hairline--bottom" @click="linkTo('changeGoods')">
          <div class="text">
            <p class="title fontsize-28 paddingB10">换货</p>
            <p class="gray fontsize-20 lineheight-32">已收到货，与卖家协商同意的前提下，需要更换已收到的商品</p>
          </div>
          <div class="gray arrow-icon-wrapper text-right">
            <Icon name="arrow" class="arrow-icon inline-block"></Icon>
          </div>
        </li>
      </ul>
  </div>
</template>

<script>
  import { Icon  } from 'vant';
  import OrderDetailsRow from '@/components/OrderDetailsRow'
  import OrderSource from '@/components/OrderSource'
  export default {
    data(){
      return{}
    },
    components:{
      Icon,
      OrderDetailsRow,
      OrderSource,
    },
    methods:{
      /**
       * 点击操作选项按钮实现连接跳转
       * @param type 类型 money , changeGoods,moneyAndGoods
       */
      linkTo(type){
        if(type==='money'){
          this.$router.push({name:'ApplyForBack',query:{type:'money'}});
        }else if(type==='moneyAndGoods'){
          this.$router.push({name:'ApplyForBack',query:{type:'moneyAndGoods'}});
        }else if(type==='changeGoods'){
          this.$router.push({name:'ExhangeGoods',query:{type:'changeGoods'}});
        }
      },
    },
  };
</script>

<style scoped>
.page-service-type{
  background:#f7f7f7;
  position:fixed;
  top:0;
  bottom:0;
  left:0;
  right:0;
}
.operation-wrapper,.typeMark{
  background:#fff;
}
.operation-wrapper{
  padding: 0 0.3rem;
}
.operation-wrapper>li{
  position: relative;
  padding: 0.24rem 1rem 0.24rem 0;
}
  .arrow-icon-wrapper{
    position: absolute;
    top: 0.4rem;
    right: 0;
    width: 1rem;
    height: 0.3rem;
  }
.arrow-icon{

}
.typeMark{
  text-align:right;
  padding: 0.1rem 0.3rem;
  margin-bottom:0.2rem;
}
.typeMark span,.typeMark p{
  font-size: 0.2rem;
  color:#999;
}
.typeMark p{
  margin-top:-0.05rem;
}
.typeMark span.presentPrice{
  font-size: 0.24rem;
  color:#ea5631;
}
</style>
