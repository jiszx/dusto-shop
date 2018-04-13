<template>
	<div class="OrderListRow">
    <div @click="orderListClick">
      <!--单个商品时-->
      <div v-if="orderData.productList.length<2" class="border-1px-b">
        <div class="OrderListRow-min" v-for="(item,index) in orderData.productList" :key="index">
          <div class="image-box">
            <img :src="item.productImg" alt="">
          </div>
          <div class="goods-info paddingL20">
            <div class="title fontsize-28 paddingB15">{{item.productName}}</div>
            <div class="gray fontsize-20">
              <p>
                <span class="inline-block paddingR20">颜色：{{item.color}} </span>
                <span>尺码：{{item.size}}码</span>
              </p>
              <p>{{$mUtils.formatDate(item.buyTime)}}</p>
            </div>
          </div>
          <div class="OrderListRow-totle">
            <p class="fontsize-24 ">￥{{item.buyPrice}}</p>
            <p class="fontsize-20 gray line-through">￥{{item.price}}</p>
            <p class="fontsize-20 gray">x {{item.count}}</p>
          </div>
        </div>
      </div>
      <!--多个商品显示-->
      <div class="OrderListRow-min-multiple-wrapper" v-else>
        <div class="OrderListRow-min-multiple-wrapper-inner clearfix ">
          <div :style="{width:1.7*orderData.productList.length+0.1+'rem'}">
            <div class="OrderListRow-min-multiple" v-for="(item,index) in orderData.productList" :key="index">
              <div class="image-box-multiple">
                <img :src="item.productImg" alt="">
              </div>
            </div>
          </div>
        </div>
        <!--向右箭头-->
        <Icon name="arrow" class="gray OrderListRow-min-multiple-arrow"></Icon>
      </div>
      <div class="OrderListRow-all-info inline_box fontsize-20 gray clearfix">
        <div class="inline_2">
          <p class="fontsize-24">订单来源：<span>{{orderData.orderType}}</span></p>
        </div>
        <div class="inline_2 text-right">
          <p>共1件商品 合计：<span class="fontsize-24 black">￥199.00</span></p>
          <p>(含运费￥0.00)</p>
        </div>
      </div>
    </div>
    <div class="OrderListRow-button text-right">
      <Button class="order-button" v-if="showDeleteBtn" @click="DeleteBtnClick">删除订单</Button>
      <Button class="order-button" v-if="showCancleBtn" @click="CancleBtnClick">取消</Button>
      <Button class="order-button" v-if="showTxfhBtn" @click="TxfhBtnClick">提醒发货</Button>
      <Button class="order-button" v-if="showYcshBtn" @click="YcshBtnClick">延长收货</Button>
      <Button class="order-button" v-if="showCkwlBtn" @click="CkwlBtnClick">查看物流</Button>
      <Button class="order-button active" v-if="showPayBtn" @click="clickPay">支付</Button>
      <Button class="order-button active" v-if="showApplySHBtn" @click="clickApplySH">申请售后</Button>
      <Button class="order-button active" v-if="showEvaluateBtn" @click="EvaluateBtnClick">评价</Button>
      <Button class="order-button active" v-if="showQrshBtn" @click="QrshBtnClick">确认收货</Button>
      <Button class="order-button" v-if="showCancleApplyBtn" @click="CancleApplyBtnClick">撤销申请</Button>
      <Button class="order-button active" v-if="showEditApplyBtn" @click="EditApplyClick">修改申请</Button>
      <Button class="order-button active" v-if="showHasReturnBtn" @click="HasReturnClick">我已退货</Button>
    </div>
	</div>
</template>

<script>
  import { Dialog,Button, Icon } from 'vant';
  var noFn = ()=>{};
export default{
  props:{
    orderData:{
      type:Object,
      require:true,
    },
    type:{
      type:Number,
      default:2,
    },
    showPayBtn:{//是否显示支付按钮
      type:Boolean,
      require:false
    },
    showEvaluateBtn:{//是否显示评价按钮
      type:Boolean,
      require:false
    },
    showTxfhBtn:{//是否显示提醒发货按钮
      type:Boolean,
      require:false
    },
    showYcshBtn:{//是否显示延长收货按钮
      type:Boolean,
      require:false
    },
    showCkwlBtn:{//是否显示查看物流按钮
      type:Boolean,
      require:false
    },
    showQrshBtn:{//是否显示确认收货按钮
      type:Boolean,
      require:false
    },
    showCancleBtn:{//是否显示取消按钮
      type:Boolean,
      require:false
    },
    showDeleteBtn:{//是否显示删除按钮
      type:Boolean,
      require:false
    },
    showCancleApplyBtn:{//是否显示撤销申请
      type:Boolean,
      require:false
    },
    showEditApplyBtn:{//是否显示修改申请
      type:Boolean,
      require:false
    },
    showHasReturnBtn:{//是否显示我已退货
      type:Boolean,
      require:false
    },
    showApplySHBtn:{//是否显示申请售后
      type:Boolean,
      require:false
    },
  },
	data(){
	  return{
      imgUrl:require("../assets/images/list-goods/img-3.png"),
    }
  },
  components:{
    Dialog,
    Button,
    Icon
  },
  methods:{
    /**
     * 点击订单跳转
     */
    orderListClick(){
      this.$router.push({name:"OrderDetail",query:{type:this.orderData.type,orderNo:this.orderData.orderNo}});
    },
    clickPay(){
      this.$router.push({name:'OrderDetail',query:{type:3}});
    },
    EvaluateBtnClick(){
      this.$router.push({name:'WriteEvaluate'});
    },
    TxfhBtnClick(){
      Dialog.alert({
        title: '标题',
        message: '提醒发货成功'
      }).then(() => {
        // on close
      });
    },
    YcshBtnClick(){
      Dialog.alert({
        title: '标题',
        message: '已延长收货'
      })
    },
    CkwlBtnClick(){
      this.$router.push({name:'Logistics'});
    },
    QrshBtnClick(){
      Dialog.alert({
        title: '标题',
        message: '是否确认收货'
      })
    },
    CancleBtnClick(){
      Dialog.confirm({
        title: '标题',
        message: '是否确认取消订单'
      }).then(() => {

      }).catch(() => {

      });
    },
    DeleteBtnClick(){
      Dialog.confirm({
        title: '标题',
        message: '是否确认删除该订单'
      }).then(() => {

      }).catch(() => {

      });
    },
    CancleApplyBtnClick(){
      Dialog.confirm({
        title: '标题',
        message: '是否确认撤销申请'
      }).then(() => {

      }).catch(() => {

      });
    },
    EditApplyClick(){
      Dialog.confirm({
        title: '标题',
        message: '是否确认修改申请'
      }).then(() => {

      }).catch(() => {

      });
    },
    HasReturnClick(){
      Dialog.confirm({
        title: '标题',
        message: '是否确认我已退货'
      }).then(() => {

      }).catch(() => {

      });
    },
    clickApplySH(){
      Dialog.confirm({
        title: '标题',
        message: '是否确认申请售后'
      }).then(() => {
        this.$router.push({name:'CustomerService'})
      }).catch(() => {

      });
    },
  },
}
</script>

<style scoped>
  .OrderListRow{
    padding-top: 0.2rem;
  }
.OrderListRow-min{
  padding: 0.2rem 1rem 0.2rem 1.6rem;
  position: relative;
}
  .image-box{
    position: absolute;
    left: 0rem;
    top: 0.2rem;
    width: 1.6rem;
    height: 1.6rem;
  }
  img{
    display: block;
    width: 100%;
    height: 100%;
  }
  .goods-info{
    min-height: 1.6rem;
  }
  .OrderListRow-totle{
    position: absolute;
    right: 0;
    bottom: 0.3rem;
  }
  .OrderListRow-all-info{
    padding: 0.1rem 0 0.2rem;
  }
  .OrderListRow-button{
  }
  .OrderListRow-button>.order-button{
    min-width: 1.48rem;
    height: 0.6rem;
    line-height: 0.6rem;
    background: #fff;
  }
  .OrderListRow-button>.order-button.active{
    color:#ea5361;
    border: 0.02rem solid #ea5361;
  }

  .OrderListRow-min-multiple-wrapper{
    padding-right: 30px;
    position: relative;
  }

  .OrderListRow-min-multiple{
    padding-top: 0.2rem;
    padding-bottom: 0.2rem;
    float: left;
  }
  .image-box-multiple{
    width: 1.6rem;
    height: 1.6rem;
    margin-right: 0.1rem;
  }
  .OrderListRow-min-multiple-arrow{
    position: absolute;
    top: 50%;
    margin-top: -10px;
    right: 10px;
  }
  .OrderListRow-min-multiple-wrapper-inner{
    width: 100%;
    overflow-x: scroll;
    overflow-y: hidden;
  }
</style>
