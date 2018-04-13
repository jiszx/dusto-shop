<template>
    <div class="page-exchange-goods">
      <OrderDetailsRow />
      <OrderSource />
      <ul class="operation-wrapper">
        <li class="operation-cell-btn van-hairline--bottom" @click="operation('chooseReason')">
          <div class="text">
            <p class="title fontsize-28 paddingB10">换货原因</p>
          </div>
          <div class="gray arrow-icon-wrapper text-right gray fontsize-24">
            <span>请选择</span>
            <Icon name="arrow" class="arrow-icon inline-block"></Icon>
          </div>
        </li>
        <li class="operation-cell-btn van-hairline--bottom" @click="operation('writeReasion')">
          <!--<div class="text">-->
            <!--<p class="title fontsize-28 paddingB10">换货说明 <span class="fontsize-24 gray">选填</span></p>-->
          <!--</div>-->
          <div class="weui-cell paddingL0">
            <div class="weui-cell__hd"><label class="weui-label">换货说明</label></div>
            <div class="weui-cell__bd">
              <input class="weui-input" type="text" placeholder="选填">
            </div>
          </div>
        </li>
      </ul>
      <UploadVoucher />
      <div class="page-exchange-goods-introduce gray fontsize-24 paddingT30 paddingL30 paddingR30">
        <p>换货退款金额：换货申请成功后会再次发送支付页面，请放心支付，该金额将于7个工作日内返还至原账户</p>
        <p class="paddingT20">
          关于换货商品：不影响第二次销售的商品，仅限更换同款相同价格商品，如需申请换货请联系商家处理，具体查看
          <router-link class="blue" to="/">规则内容</router-link>
        </p>
      </div>

      <div class="text-center fontsize-32 confirm-btn">确认</div>

      <div>
        <ActionSheet :showAs="show" @hideAs="hideAs">
          <div class="theModel">
            <div class="addInfo infoTitle">
              <p>退款原因<span @click="hideAs">取消</span></p>
            </div>
            <div
              class="weui-cell exchange-goods-reason"
              v-for="(item,index) in exchangeGoodsReasionList"
              :key="index" @click="selectReason(item.value)"
              :class="{active:exchangeGoodsReasion.includes(item.value)}">
              <span class="">{{item.label}}</span>
              <div class="checkbox" v-show="exchangeGoodsReasion.includes(item.value)">
                <Icon name="success"></Icon>
              </div>
            </div>

            <div class="confirm confirmS text-center fontsize-32 confirm-btn">
              <p @click="hideAs">确定</p>
            </div>

          </div>
        </ActionSheet>
      </div>

      <quick-bar></quick-bar>
    </div>
</template>

<script>
  import { Icon  } from 'vant';
  import UploadVoucher from '@/components/UploadVoucher'
  import OrderDetailsRow from '@/components/OrderDetailsRow'
  import OrderSource from '@/components/OrderSource'
  import ActionSheet from "components/ActionSheet";
  import QuickBar from "components/QuickBar";
    export default {
      data(){
        return{
          show: false,
          exchangeGoodsReasion:[0,1],
          exchangeGoodsReasionList:[{
            value:0,
            label:'七天无理由',
          },{
            value:1,
            label:'颜色发错',
          },{
            value:2,
            label:'码数发错',
          },{
            value:3,
            label:'款式发错',
          },{
            value:4,
            label:'商品破损',
          },{
            value:5,
            label:'质量问题(胶水印，开线，两只鞋子不对称)',
          },],
        }
      },
      components:{
        Icon,
        ActionSheet,//弹层组件
        OrderDetailsRow,
        OrderSource,
        UploadVoucher,
        QuickBar,
      },
      methods:{
        /**
         * 点击操作选项按钮实现连接跳转
         * @param type 类型 money , changeGoods,moneyAndGoods
         */
        operation(type){
          if(type==='chooseReason'){
            //todo somethings
            this.showActionAS();
          }else if(type==='writeReasion'){
            //todo somethings
          }
        },
        showActionAS(){
          this.show = true
        },
        hideAs(){
          this.show = false
        },
        /**
         * 点击选择原因
         * @param val
         */
        selectReason(val){
          if(this.exchangeGoodsReasion.includes(val)){
            this.exchangeGoodsReasion.splice(this.exchangeGoodsReasion.indexOf(val),1)
          }else{
            this.exchangeGoodsReasion.push(val);
          }
        },
      },
    }
</script>

<style scoped>
  .page-exchange-goods{
    padding-bottom: 1rem;
  }
  .operation-wrapper{
    padding: 0 0.3rem;
  }
  .operation-wrapper>li{
    position: relative;
    padding: 0.24rem 1.2rem 0.24rem 0;
  }
  .arrow-icon-wrapper{
    position: absolute;
    top: 0.25rem;
    right: 0;
    width: 1.2rem;
    height: 0.5rem;
  }
  .arrow-icon{

  }

  .confirm-btn{
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 0.8rem;
    line-height: 0.8rem;
    text-align: center;
    color:#fff;
    background: #ea5361;
  }

  /*弹层*/
  .theModel{
    background:#fff;
  }
  .infoTitle{font-size: 0.28rem;color:#000;background:#f7f7f7;}
  .infoTitle p{position:relative;}
  .infoTitle p span{position:absolute;right: 0.3rem;}
  .confirm-btn.confirmS.confirm{
    position: relative;

  }
  .theModel .confirmS{
     height: 1.08rem;
     margin-top: 0.6rem;
   }

  .exchange-goods-reason.active,.checkbox{
    color:#ea5361;
  }
  .checkbox{
    flex: 1;
    text-align: right;
  }
</style>
