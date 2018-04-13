<template>
	<div class="page-customer-service">
    <!--请等待商家处理-->
    <div class="marginT30 order-list-item">
      <p class="order-item-title red fontsize-32">请等待商家处理</p>
      <div v-for="(data,index) in customerServiceList" class="marginB30 order-item-wrapper border-1px-b">
        <div class="clearfix order-tips-wrapper">
          <p class="order-tips-text red pull-right">还剩2天23小时59分</p>
        </div>
        <div class=" border-1px-t">
          <OrderListRow  showCancleApplyBtn showEditApplyBtn :orderData="data"></OrderListRow>
        </div>
      </div>
    </div>

    <!--请等待商家处理-->
    <div class="marginT30 order-list-item">
      <p class="order-item-title red fontsize-32">商家已拒绝</p>
      <div v-for="(data,index) in customerServiceList" class="marginB30 order-item-wrapper border-1px-b">
        <div class="clearfix order-tips-wrapper">
          <p class="order-tips-text red pull-right">还剩2天23小时59分</p>
        </div>
        <div class=" border-1px-t">
          <OrderListRow showCancleApplyBtn showEditApplyBtn :orderData="data"></OrderListRow>
        </div>
      </div>
    </div>

    <!--请等待商家处理-->
    <div class="marginT30 order-list-item">
      <p class="order-item-title red fontsize-32">商家审核通过</p>
      <div v-for="(data,index) in customerServiceList" class="marginB30 order-item-wrapper border-1px-b">
        <div class="clearfix order-tips-wrapper">
          <p class="order-tips-text red pull-right">还剩2天23小时59分</p>
        </div>
        <div class=" border-1px-t">
          <OrderListRow showHasReturnBtn :orderData="data"></OrderListRow>
        </div>
      </div>
    </div>

    <!--请等待商家处理-->
    <div class="marginT30 order-list-item">
      <p class="order-item-title fontsize-32">商家已拒绝</p>
      <div v-for="(data,index) in customerServiceList" class="marginB30 order-item-wrapper border-1px-b">
        <div class="clearfix order-tips-wrapper">
          <p class="order-tips-text red pull-right">退款申请关闭</p>
        </div>
        <div class=" border-1px-t">
          <OrderListRow showDeleteBtn :orderData="data"></OrderListRow>
        </div>
      </div>
    </div>

    <!--请等待商家处理-->
    <div class="marginT30 order-list-item">
      <p class="order-item-title fontsize-32">已完成</p>
      <div v-for="(data,index) in customerServiceList" class="marginB30 order-item-wrapper border-1px-b">
        <div class="clearfix order-tips-wrapper">
          <p class="order-tips-text red pull-right"></p>
        </div>
        <div class=" border-1px-t">
          <OrderListRow showDeleteBtn :orderData="data"></OrderListRow>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import OrderListRow from '../../components/OrderListRow';
  import mixinOrderType from 'mixins/orderType'
  import { mapState } from 'vuex'
export default{
  mixins:[mixinOrderType],
  data(){
    return {
      items: [
        {"title":"请等待商家处理","id":0},
        {"title":"商家已拒绝","id":3},
        {"title":"商家审核通过","id":1},
        {"title":"商家已拒绝","id":10},
        {"title":"已完成","id":2}
      ] ,
    }
  },
  computed:{
    ...mapState({
      customerServiceList:state=>state.order.customerServiceList
    }),
  },
  created(){
    this.$store.dispatch('getCustomerService');
  },
  components: {
    OrderListRow,
  },
}
</script>

<style scoped>
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
