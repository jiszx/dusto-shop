<template>
  <div class="OrderList" :class="{isPaytabs:activeIndex===1}">
    <tabs :active="active" @click="clickTabs">
      <tab v-for="item in items" :title="item.title" :key="item.title">
      </tab>
    </tabs>
    <div>
      <!--代付款-->
      <div class="marginT30 order-list-item" v-if="activeIndex===0||activeIndex===1">
        <CheckboxGroup v-model="chooseOrders">
          <p class="order-item-title fontsize-32 red" v-if="activeIndex===0">待付款</p>
          <div v-for="(data,index) in awaitPay" class="marginB30 order-item-wrapper border-1px-b">
            <div class="order-item-title-checkbox fontsize-32" v-if="activeIndex===1">
              <Checkbox :name="data.orderNo"></Checkbox>
            </div>
            <div class="clearfix order-tips-wrapper">
              <p class="order-tips-text red pull-right">订单24小时自动关闭</p>
            </div>
            <div class=" border-1px-t">
              <OrderListRow showPayBtn showCancleBtn :orderData="data"></OrderListRow>
            </div>
          </div>
        </CheckboxGroup>
      </div>

      <!--待发货-->
      <div class="marginT30 order-list-item" v-if="activeIndex===0||activeIndex===2">
        <p class="order-item-title fontsize-32" v-if="activeIndex===0">待发货</p>
        <div v-for="(data,index) in awaitPay" class="marginB30 order-item-wrapper border-1px-b">
          <div class="clearfix order-tips-wrapper">
            <p class="order-tips-text red pull-right">买家已付款</p>
          </div>
          <div class=" border-1px-t">
            <OrderListRow showTxfhBtn :orderData="data"></OrderListRow>
          </div>
        </div>
      </div>

      <!--待收货-->
      <div class="marginT30 order-list-item" v-if="activeIndex===0||activeIndex===3">
        <p class="order-item-title fontsize-32" v-if="activeIndex===0">待收货</p>
        <div v-for="(data,index) in awaitPay" class="marginB30 order-item-wrapper border-1px-b">
          <div class="clearfix order-tips-wrapper">
            <p class="order-tips-text red pull-right">大东已发货</p>
          </div>
          <div class=" border-1px-t">
            <OrderListRow showCkwlBtn showYcshBtn showQrshBtn :orderData="data"></OrderListRow>
          </div>
        </div>
      </div>

      <!--待评价-->
      <div class="marginT30 order-list-item" v-if="activeIndex===0||activeIndex===4">
        <p class="order-item-title fontsize-32" v-if="activeIndex===0">待评价</p>
        <div v-for="(data,index) in awaitPay" class="marginB30 order-item-wrapper border-1px-b">
          <div class="clearfix order-tips-wrapper">
            <p class="order-tips-text red pull-right">交易成功</p>
          </div>
          <div class=" border-1px-t">
            <OrderListRow showApplySHBtn showEvaluateBtn showDeleteBtn :orderData="data"></OrderListRow>
          </div>
        </div>
      </div>
    </div>
    <div class="pay-btn-wrapper" :class="{active:chooseOrders.length}">
      <div class="inline_box pay-btn-choose-all">
        <div class="inline_2">
          <Checkbox @change="selectAll">全选</Checkbox>
        </div>
        <div class="inline_2 fontsize-24 gray text-right">
          <p><span class="inline-block paddingR30">2件商品</span><span>合计: ￥200</span></p>
        </div>
      </div>
      <div class="pay-btn-item text-right fontsize-30">
        <Button class="order-button active">合并付款</Button>
      </div>
    </div>
    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import { Tab, Tabs, Checkbox, CheckboxGroup ,Button } from 'vant';
  import OrderListRow from '../../components/OrderListRow';
  import QuickBar from "components/QuickBar";
  import mixinOrderType from 'mixins/orderType'
  import { mapState } from 'vuex'
  export default {
    mixins:[mixinOrderType],
    data() {
      return {
        chooseOrders:[],
        active: 0,
        activeIndex:0,
        items: [
            {"title":"全部","id":0},
            {"title":"待付款","id":3},
            {"title":"待发货","id":1},
            {"title":"待收货","id":10},
            {"title":"待评价","id":2},
        ] ,
        formData:{
          memberNo:'25000105079',
        },
        awaitPay:[],
        awaitDelivery:[],
        awaitReceiving:[],
        finish:[],
      }
    },
    computed:{
      ...mapState({
        storeOrderList:state=>state.order.myOrderList
      }),
      currentActiveTabId(){
        return this.items[this.activeIndex].id;
      },
    },
    watch:{
      storeOrderList(val) {
        this.computedMyOrderList(val);
      }
    },
    components: {
      Tab,
      Tabs,
      OrderListRow,
      QuickBar, // 快速导航
      Checkbox,
      CheckboxGroup,
      Button,
    },
    created(){
      let activeId = this.$route.query.type;
      this.active = this.activeIndex = activeId||0;
      this.$store.dispatch('orderGetList',this.formData)
      this.computedMyOrderList(this.storeOrderList);
    },
    methods:{
      clickTabs(val){
        this.activeIndex=val;
      },
      /**
       * 代付款页面点击全选按钮
       */
      selectAll(){

      },
      computedMyOrderList(list){
        //todo 订单分类，分为待付款，代发货，待收货，待评价
        let awaitPay = [],
          awaitDelivery=[],
          awaitReceiving=[],
          finish=[];
        for(let i=0, len=list.length; i<len; i++){
          let type = this._getOrderType(list[i].orderStatus);
          list[i].type = type;
          if(type==3){
            awaitPay.push(list[i]);
          }else if(type==1){
            awaitDelivery.push(list[i]);
          }else if(type==10){
            awaitReceiving.push(list[i]);
          }else{
            finish.push(list[i]);
          }
        }
        this.awaitPay = awaitPay;
        this.awaitDelivery = awaitDelivery;
        this.awaitReceiving = awaitReceiving;
        this.finish = finish;
        this.$forceUpdate();
      },
    },
  };
</script>

<style>
  .OrderList{
    background:#f7f7f7;
  }
  .van-tabs--scrollable .van-tab {
    flex: 0 0 20%;
  }
  .van-checkbox__control:checked+.van-icon-success {
    border-color: #ea5361;
    background-color: #ea5361;
  }
  .order-list-item{
    position: relative;
  }
  .order-tips-wrapper{
    height: 0.76rem;
    line-height: 0.76rem;
  }
  .order-item-title{
    position: absolute;
    top: 0.12rem;
    left: 0.24rem;
  }
  .order-item-title-checkbox{
    position: absolute;
    top: 0.1rem;
    left: 0.24rem;
  }
  .order-item-wrapper{
    padding: 0 0.24rem 0.3rem;
  }

  .isPaytabs{
    padding-bottom: 1.8rem;
  }
  .OrderList{
    position: relative;
  }
  .pay-btn-wrapper{
    position: fixed;
    left: 0;
    width: 100%;
    height: 1.8rem;
    padding: 0 0.24rem;
    opacity: 0;
    bottom: -1.8rem;
    transition: .3s all;

  }
  .pay-btn-wrapper.active{
    opacity: 1;
    bottom: 0;
  }
  .pay-btn-item{
    height: 1rem;
  }
  .order-button{
    min-width: 1.48rem;
    height: 0.6rem;
    line-height: 0.6rem;
    border-radius: 0;
    margin-top: 0.2rem ;
    background: #ea5361;
  }
  .order-button.active{
    color:#fff;
    border: 0.02rem solid #ea5361;
  }
  .pay-btn-choose-all{
    height: 0.8rem;
    line-height: 0.8rem;
  }
</style>
