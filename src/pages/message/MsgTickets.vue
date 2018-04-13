<template>
  <div>
    <div v-for="(item,index) in couponlistData" :key="item.coupType">
      <div class="cateTitle" v-if="item.coupType==='1'">通用</div>
      <div class="ticket" v-if="item.coupType==='1'">
        <div class="number">
          <span class="yuan">{{item.coupAmount}}元</span>
          <span class="guize">{{item.coupCondition}}</span>
        </div>
        <div class="detail">
          <div class="title">{{item.coupDesc}}</div>
          <div class="time">有效期：{{$mUtils.formatDate(item.startDate,'yyyy-MM-dd')}} - {{$mUtils.formatDate(item.endDate,'yyyy-MM-dd')}}</div>
      </div>
    </div>

    </div>

    <div class="cateTitle">线上</div>
    <div class="ticket">
      <div class="number">
        <span class="yuan">10元</span>
        <span class="guize">满99元使用</span>
      </div>
      <div class="detail">
        <div class="title">下沈线大东10元店铺优惠券</div>
        <div class="time">有效期：2018/01/28 - 2018/12/12</div>
      </div>
    </div>

    <div class="cateTitle">线下</div>
    <div class="ticket">
      <div class="number">
        <span class="yuan">10元</span>
        <span class="guize">满99元使用</span>
      </div>
      <div class="detail">
        <div class="title">下沈线大东10元店铺优惠券</div>
        <div class="time">有效期：2018/01/28 - 2018/12/12</div>
      </div>
    </div>

    <div class="divBtn">
      <button class="weui-btn btn-putTicket" @click="putTickets">一键放入卡券包</button>
    </div>
    <quick-bar></quick-bar>
  </div>


</template>

<script>
  import {Dialog} from 'vant';
	import QuickBar from "components/QuickBar";
  import { getCouponlistData } from 'api/promors'
  import { getCouponreceiveData } from 'api/promors'
  export default {
    components: {
      Dialog,
      QuickBar
    },
    data() {
      return {
        formData:{

        },
        formMess:{
          promoCodelist:[
            {promoCode:"X1324567"},
            {promoCode:"X333322"}
          ],
          memberNo:"3333343"
        },
        couponlistData:[
          {
            coupID:"",
            coupType:"",
            coupDesc:"",
            couponName:"",
            parValue:"",
            endDate:"",
            bound:"",
            isDP:"",
            description:"",
            shopName:"",
            coupAmount:"",
            couponNo:"",
            coupImg:"",
            isFullScope:"",
            type:"",
            isPL:"",
            isPP:"",
            shopNo:"",
            startDate:"",
            status:""
          },
        ],
        couponreceiveData:{
          repFlag:"",
          repMsg:""
        },
        formRouteName:'',
        productID:'',
      }
    },
    created(){
      this.getData();
      this.formRouteName = this.$route.query.fromRouteName;
      this.productID = this.$route.query.productID;
    },
    methods: {
      onConfirm() {
      },
      /**
       * 路由跳转判断
       */
      routeBack(){
        if(this.formRouteName&&this.formRouteName=='CommodityDetails'){
          this.$router.push({name:this.formRouteName,query:{productID:this.productID}})
        }
      },
      putTickets() {
        Dialog.alert({
          message: '<p style="font-size: 16px;font-weight:bold;padding:50px 0px;text-align: center">确定领取所有的店铺优惠券?</p>',
        }).then(() => {
          // on confirm
          getCouponreceiveData(this.formMess).then(res=>{
            if(res.response_code=="S000000"){
                this.couponreceiveData = res.response_data;
                if(this.couponreceiveData.repFlag=='0'){
                  this.$store.commit('showMsgTips',this.couponreceiveData.repMsg)
                }else if(this.couponreceiveData.repFlag=='1'){
                  this.$store.commit('showMsgTips',this.couponreceiveData.repMsg)
                }
              }
            //路由跳转到上一页
            this.routeBack();
            }).catch(err=>{
              this.$store.commit('showMsgTips','请求失败，请重试！')
            })

        }).catch((err) => {
          // on cancel
          console.log(err);
        });
      },
      getData(){
        getCouponlistData(this.formData).then(res=>{
          if(res.response_code=="S000000"){
              this.couponlistData = res.response_data;
            }
          }).catch(err=>{
            this.$store.commit('showMsgTips','请求失败，请重试！')
          })
      },
    }
  };
</script>

<style>
  .cateTitle {
    height: 52px;
    padding-left: 18px;
    padding-top: 30px;
    font-size: 18px;
    color: #000;
    font-weight: bold;
  }
  .ticket {
    background: url("../../assets/images/img-yhq-ty.png") no-repeat;
    background-size: 100% 100%;
    height: 153px;
    display: flex;
    flex-direction: row;
  }

  .ticket .number {
    flex-grow: 10;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }

  .ticket .number .yuan {
    color: #ea5361;
    font-size: 24px;
  }

  .ticket .number .guize {
    font-size: 10px;
    color: #b3b3b3;
  }

  .ticket .detail {
    flex-grow: 12;
    text-align: center;
    justify-content: flex-start;
    display: flex;
    padding-top: 38px;
    flex-direction: column;
  }

  .ticket .detail .title {
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 24px;
  }

  .ticket .detail .time {
    color: #b3b3b3;
    font-size: 10px;
  }

  .divBtn {
    margin: 30px 0px;
    padding: 0px 30px;
  }

  .btn-putTicket {
    width: 100%;
    height: 44px;
    box-sizing: border-box;
    background: #ea5361;
    color: #fff;
    font-size: 16px;
  }

  .message {
    font-size: 16px;
    padding: 50px;
    text-align: center;
  }

  .van-dialog__confirm{
    font-size: 18px !important;
    color: #ea5361 !important;
    font-weight: bold !important;
  }
  .van-dialog {
    border-radius: 20px !important;
  }
</style>
