<template>
  <div id="app" class="AddAddress">
    <div class="weui-cell">
      <div class="weui-cell__hd">
        <label class="weui-label">收货人:</label>
      </div>
      <div class="weui-cell__bd">
        <input v-model="address.name" type="text" placeholder="填写收货人姓名" class="weui-input">
      </div>
    </div>

    <div class="weui-cell">
      <div class="weui-cell__hd">
        <label class="weui-label">联系方式:</label>
      </div>
      <div class="weui-cell__bd">
        <input v-model="address.phone" type="tel" placeholder="请输入电话号码" class="weui-input">
      </div>
    </div>

    <div class="weui-cell" @click="showSelectArea">
        <div class="weui-cell__hd">
            <label class="weui-label">所在地区</label>
        </div>
        <div class="inline-block fontsize-24">
          <span class=" gray" v-if="!address.area">请选择所在区域</span>
          <span class="fontsize-28" v-else>{{address.area}}</span>
        </div>
    </div>

    <div class="weui-cell">
      <div class="weui-cell__hd">
        <label class="weui-label">详细地址:</label>
      </div>
      <div class="weui-cell__bd">
        <input v-model="address.areaDetail" type="text" placeholder="具体接到或门牌号等" class="weui-input">
      </div>
    </div>

    <div class="weui-cell weui-cell_switch">
        <div class="weui-cell__bd defaultSwitch">设为默认地址
          <span class="remark">注：每次下单会使用改地址</span>
        </div>
        <div class="weui-cell__ft">
            <input class="weui-switch" type="checkbox" v-model="address.isDefault">
        </div>
    </div>

    <div class="addInfo addAdress">
      <p @click="saveAddress">保存收货地址</p>
    </div>
    <Popup v-model="showAreaSelect" position="bottom">
      <Area
        :area-list="areaList"
        @confirm="selectAreaConfirm"
        :loading="true"
        @cancel="showSelectArea"/>
    </Popup>
    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import QuickBar from "components/QuickBar";
  import areaData from 'assets/js/area.js'
  import { Area, Popup } from 'vant';
  import { mapGetters } from 'vuex'
  export default {
    data(){
      return{
        uuid:undefined,
        id:undefined,
        type:"add",
        address:{
          name:"",
          phone:"",
          area:"",
          areaDetail:"",
          isDefault:false
        },
        areaList:areaData,
        showAreaSelect:false,
      }
    },
    computed:{
      ...mapGetters([
        'getAddressById'
      ]),
    },
    created(){
      this.uuid = this.$route.query.uuid;
      this.id = this.$route.query.id;
      this.type = this.$route.query.type;
      let addressData = this.getAddressById(this.id);
      //初始化
      if(this.id&&addressData){
        for(let key in this.address){
          this.address[key] = addressData[key]
        }
      }
    },
  	components: {
  		QuickBar, // 快速导航
      Area,
      Popup
  	},
    methods:{
      /**
       * 检验表单是否填写完整
       */
      validateForm(){
        if(!this.address.name){
          this.$store.dispatch('showMsgTips','请填写用户姓名');
          return false;
        }
        if(!(this.address.phone&&(this.$mUtils.checkType(this.address.phone.trim(),'phone')||this.$mUtils.checkType(this.address.phone.trim(),'tel')))){
          this.$store.dispatch('showMsgTips','请正确填写手机号');
          return false;
        }
        if(!this.address.area){
          this.$store.dispatch('showMsgTips','请选择所在地区');
          return false;
        }
        if(!this.address.areaDetail){
          this.$store.dispatch('showMsgTips','请填写详细地址');
          return false;
        }

        return true;
      },
      /**
       * 点击保存地址
       */
      saveAddress(){
        if(!this.validateForm()){//如果表单验证不通过，则返回
          return;
        }
        //如是修改编辑地址模式
        if(this.type=='edit'){
          this.$store.dispatch('addressUpdateById',{
            id:this.id,
            address:this.address
          });
        }else if(this.type=='add'){
          this.$store.dispatch('addressAdd',this.address);
        }
        this.$router.push({name:"MyAddress",query:{uuid:this.uuid}})
      },
      /**
       * 显示选择地址
       */
      showSelectArea(){
        this.showAreaSelect=!this.showAreaSelect;
      },
      selectAreaConfirm(val){
        let data = '';
        val.forEach(item=>{
          if(item.code!=-1){
            data+=(item.name+' ');
          }
        });
        if(data)
          this.address.area = data;
          this.showSelectArea()
      },
    },
  };
</script>

<style scoped>
  .AddAddress{
    background:#f7f7f7f7;
    position:fixed;
    top:0;
    bottom:;
    left:0;
    right:0;
  }
  .AddAddress .weui-cell{
    background:#fff;
    border-bottom: 0.02px solid #e5e5e5;
    padding: 0.15rem 0.3rem;
  }
  .AddAddress .weui-cell  .weui-select{
    height:0.44rem;
    line-height:0.44rem;
  }
  .AddAddress .weui-cell  .defaultSwitch{
    position:relative;
    margin-top: -0.5rem;
  }
  .AddAddress .weui-cell  .defaultSwitch .remark{
    position:absolute;
    left: 0;
    top: 0.5rem;
    font-size:0.24rem;
    color:#ccc;
  }
  .AddAddress .weui-cell_switch{
    padding: 0.21rem 0.3rem;
  }
  .AddAddress .weui-cell select,input::-webkit-input-placeholder{
    color:#ccc;
    font-size:12px;
  }
  .weui-cell_select .weui-cell__bd:after{
    border-color: #ccc;
  }

  .gray{
    color: #c1c1c1;
  }
</style>
