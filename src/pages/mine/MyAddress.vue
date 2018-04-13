<template>
  <div class="myAddress">

    <Address
      v-for="(item,index) in addressList"
      :key="item.id"
      :id="item.id"
      :name="item.name"
      :phone="item.phone"
      :address="item.address"
      :isDefault="item.isDefault"
      @addressClick="addressClick"
      @clickSetDefault="clickSetDefault"
      @clickEdit="clickEdit"
      @clickDel="clickDel"
    />
    <div class="addInfo addAdress">
      <p @click="goAddAddress"><span class="addIcon"><img class="list-img" alt="" src="../../assets/images/mine/icon-xzshdz.png" /></span>新增收货地址</p>
    </div>
    <quick-bar></quick-bar>
  </div>
</template>

<script>
  import QuickBar from "components/QuickBar";
  import Address from "components/Address";
  import wx from 'weixin-js-sdk'
  import { mapState } from 'vuex';
  export default {
    data() {
      return{
        uuid:undefined,
        items: [{
            id:1,
            name: "陈先生",
            phone:"15885254454",
            area:"浙江省 温州市 瑞安市",
            areaDetail:"塘厦镇 罗凤北工业区 大东鞋业开发部",
            address:"浙江省 温州市 瑞安市 塘厦镇 罗凤北工业区 大东鞋业开发部",
            isDefault:true,
          },
          {
            id:2,
            name: "王女士",
            phone:"189524325421",
            area:"浙江省 温州市 瑞安市",
            areaDetail:"塘厦镇 罗凤北工业区 大东鞋业开发部",
            address:"浙江省 温州市 瑞安市 塘厦镇 罗凤北工业区 大东鞋业人资部",
            isDefault:false,
          }]
      }
    },
    computed:{
      ...mapState({
        addressList:state=>state.mine.address
      })
    },
    created(){
      this.uuid = this.$route.query.uuid;
      // todo 获取地址列表
      if(!this.addressList.length){
        this.$store.dispatch('addressGetData');
      }
      wx.openAddress({
        success: function (res) {
          // 用户成功拉出地址
          console.log(123,res)
        },
        cancel: function (res) {
          // 用户取消拉出地址
          console.log(23,res)
        }
      });
    },
    components: {
      QuickBar, // 快速导航
      Address,
    },
    methods:{
      goAddAddress:function(){
        this.$router.push({path:'/mine/AddAddress',query:{uuid:this.uuid,type:'add'}})
      },
      /**
       * 点击地址
       */
      addressClick(id){
        if(!this.uuid){//如果不是从订单页面跳转过来的，不处理点击事件
          return;
        }
        let index = this.addressList.findIndex(v=>{
          return v.id == id;
        });
        this.$store.dispatch('updateSubmitingOrderAddress',{
          uuid:this.uuid,
          address:this.addressList[index]
        });

        //跳转到确认订单页面
        this.$router.push({name:"FirmOrder",query:{uuid:this.uuid}})
      },
      /**
       * 设置为默认地址
       * @param id
       */
      clickSetDefault(id){
        this.$store.dispatch('addressSetDefault',id);
      },
      /**
       * 点击编辑地址按钮
       */
      clickEdit(id){
        this.$router.push({path:'/mine/AddAddress',query:{id:id,uuid:this.uuid,type:'edit'}})
      },
      /**
       * 点击删除地址按钮
       */
      clickDel(id){
        this.$store.dispatch('addressDelById',id)
      },
    },
  };
</script>

<style>
  .myAddress{
    background:#f7f7f7f7;
    position:fixed;
    top:0;
    bottom:0;
    left:0;
    right:0;
  }
  .myAddress .weui-cells{
    margin-top:0.2rem;
    font-size:16px;
    color:#000;
  }
  .myAddress .weui-cell{
    padding:0.2rem 0.3rem 0 0.3rem;
  }
  .myAddress .address{
    font-size:14px;
    color:#999;
    padding: 0.1rem 0.3rem;
    border-bottom: 1px solid #e5e5e5;
  }
  .weui-cells_checkbox .weui-check:checked+.weui-icon-checked:before {
    color: #ea5361;
  }
  .weui-cells_checkbox  .weui-cell{ padding:0.14rem 0.3rem}

  .defaultAddress{
    position:relative;
    font-size:14px;
    color:#999;
  }
  .radiusControl{
    position:absolute;
    right: 0;
  }
  .edit img,.del img{
    width: 19px;
    vertical-align: sub;
    padding-right: 5px;
  }
  .del{
    padding-left: 17px;
  }

</style>
