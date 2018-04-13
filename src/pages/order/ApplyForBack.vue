<template>
  <div id="app" class="applyForBack">
    <order-details-row></order-details-row>
    <order-source></order-source>
    <div class="shopTotle">
      <div class="list-item">
          <p><span>共<span>1</span>件商品</span><span>实付：</span><span class="theMoney">&yen;118.00</span></p>
      </div>
      <div class="list-item">
         <p><span>(含运费</span><span class="rightSpan">&yen;0.00</span>)</p>
      </div>
    </div>

    <div class="list-item reasonsRefunds stateGoods">
      <div class="weui-cells">
          <a class="weui-cell weui-cell_access" href="javascript:;">
              <div class="weui-cell__bd">
                  <p>货物状态根据传值显示隐藏</p>
              </div>
              <div class="weui-cell__ft" @click="showActionAS(1)">请选择</div>
          </a>
      </div>
    </div>

    <div class="list-item reasonsRefunds">
      <div class="weui-cells">
          <a class="weui-cell weui-cell_access" href="javascript:;">
              <div class="weui-cell__bd">
                  <p>退款原因</p>
              </div>
              <div class="weui-cell__ft" @click="showActionAS(2)">请选择</div>
          </a>
      </div>
    </div>

    <div class="list-item detailsRefunds">
      <div class="list-item">
         <p><span>退款金额：&yen;</span><span>118.00</span></p>
      </div>
      <div class="list-item">
         <p><span>扣除积分：</span><span>20</span>点</p>
      </div>
      <div class="list-item remark">
         <p><span>(若您的积分余额已不足，不足部分将按照每积分0.05元从货款中扣除。)</span></p>
      </div>
    </div>

    <div class="list-item refunds">
      <div class="weui-cell">
          <div class="weui-cell__hd"><label class="weui-label">退款说明</label></div>
          <div class="weui-cell__bd">
              <input class="weui-input" type="text" placeholder="选填">
          </div>
      </div>
    </div>

    <upload-voucher></upload-voucher>
    
    <div class="division"></div>
    <div class="confirm">
      <p>提交</p>
    </div>

    <ActionSheet :showAs="show" @hideAs="hideAs">
      <div class="theModel" v-for="(item,index) in items" :key="index" v-if="typeId===item.type">
        <div class="addInfo infoTitle">
          <p>{{item.title}}<span @click="hideAs">取消</span></p>
        </div>
        <div class="weui-cell">
            <span class="">{{item.text1}}</span>
            <checkbox v-model="checked" class="checkbox"></checkbox>
        </div>
        <div class="weui-cell">
            <span class="">{{item.text2}}</span>
            <checkbox class="checkbox"></checkbox>
        </div>
        <div class="confirm confirmS">
          <p @click="hideAs">确定</p>
        </div>
      </div>  
    </ActionSheet>
     
    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import { Checkbox } from 'vant';
  import ActionSheet from "components/ActionSheet";
  import OrderDetailsRow from 'components/OrderDetailsRow';
  import OrderSource from 'components/OrderSource';
  import UploadVoucher from 'components/UploadVoucher';
  import QuickBar from "components/QuickBar";
  export default {
    data() {
      return {
         show: false,
         checked: true,
         items:[
           {
              title:"货物状态",
              text1:"未收到货",
              text2:"已收到货",
              type:1
           },
           {
              title:"退款原因",
              text1:"多拍/拍错/不想要",
              text2:"缺货",
              type:2
           }
         ],
         typeId:1
      }
    },
    components: {
      Checkbox,
      ActionSheet,//弹层组件
      OrderDetailsRow,//订单详情列表
      OrderSource,//订单来源
      UploadVoucher,//上传凭证
      QuickBar //快速导航
    },
    props:{
    },
    methods: {
        showActionAS(typeId){
          this.show = true;
          this.typeId = typeId;
        },
        hideAs(){
          this.show = false
        }
    }
  };
</script>

<style scoped>
  .applyForBack,.division{
    background:#f7f7f7;
  }
  .shopTotle,.refunds{
    border-bottom: 0.02rem solid #e5e5e5;
    background:#fff;
  }
   /*商品实付*/
  .shopTotle,.refunds{
     margin-bottom: 0.2rem;
  }
  .shopTotle p{
    flex: 1;
    text-align: right;
  }
  .shopTotle div{
    font-size:0.2rem;
    color: #999;
  }
  .shopTotle div .theMoney{
    font-size:0.24rem;
    color: #ea5361;
  }
  /*货物状态*/
  .stateGoods .weui-cells{
    margin-top:0;
  }
  /*退款原因*/
  .reasonsRefunds .weui-cell__bd{
    font-size:0.28rem;
  }
  .reasonsRefunds .weui-cell__ft,.refunds .weui-input{
    font-size:0.24rem;
    color: #b3b3b3;
  }
  .reasonsRefunds,.weui-cells:after{
    border-bottom: 0.02rem solid #e5e5e5;
  }
  .reasonsRefunds .weui-cells{
    margin-top:0.2rem;
  }
  .weui-cells:before{
    border-top:none;
  }
  /*退款积分扣除*/
    .detailsRefunds div.remark{
      font-size:0.2rem;
      color:#ccc;
    }
  /*退款说明*/
    .refunds .weui-label{
      width:1.3rem;
    }
  /*分割区域*/
  .division{
    height:1.5rem;
  }
  /*上传*/
  .confirm{
    height: 0.88rem;
    line-height: 0.88rem;
    text-align: center;
    background: #ea5361;
    color:#fff;
    font-size:0.32rem;
  }
   /*弹层*/
  .theModel{
    background:#fff;
  }
  .infoTitle{font-size: 0.28rem;color:#000;background:#f7f7f7;}
  .infoTitle p{position:relative;}
  .infoTitle p span{position:absolute;right: 0.3rem;}

  .action-sheet .action-sheet-com{
   
  }
  .applyForBack .action-sheet{
    z-index: 999;
  }
  .theModel .confirmS{
    height: 0.88rem;
    margin-top: 2rem;
  }

  .checkbox{
    flex: 1;
    text-align: right;
  }
  .van-checkbox__control:checked+.van-icon-success {
    border-color: #ea5361;
    background-color: #ea5361;
  }
  .theModel .weui-cell{
    border-bottom: 0.02rem solid #e5e5e5;
  }
</style>
