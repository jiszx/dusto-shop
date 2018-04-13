<template>
  <div  class="shop-list">
    <search v-model="formData.keyword" placeholder="搜索店铺" show-action background="#fff" @search="search">
      <i slot="action" class="location-icon"></i>
    </search>
    <!--<div class="selectBar">
      <div class="title">四川-成都</div>
      <div class="title">附近5KM</div>
      <div class="title">智能排序</div>
    </div>-->
    <shop-list-tab class="index-shop-slt" @shopListTabCB="shopListTabCB"></shop-list-tab>
    <div class="shopList">
      <div class="shopInfo" v-for="shop in shopList" @click="toShopDetail(shop.shopNo)">
        <div class="left">
          <div class="text">
            <div class="area">{{shop.shopCityAddress}}</div>
            <div class="shopName">
              {{shop.shopName}}
              <img v-if="shop.hasCollection" src="../../assets/images/img-star-hb.png"/>
            </div>
            <div class="addr">{{shop.address}}</div>
          </div>
        </div>
        <div class="right">
          <small>{{shop.distance}}</small>
          <icon name="arrow"/>
        </div>
      </div>
    </div>
    <tool-bar></tool-bar>
  </div>
</template>

<script>
  import {
    Search,
    Icon
  } from 'vant';
  import ShopListTab from "components/ShopListTab"
  import ToolBar from "../../components/ToolBar";
  import {mapState} from "vuex"

  export default {
    data(){
      return {
        value:"",
        formData:{
          keyword:"",
          longitude:"120.677329",
          latitude:"27.848174"
        }

      }
    },
    computed: {
      ...mapState({
        shopList: state => state.shop.shopList
      })
    },
    created(){
      this.$store.dispatch('getShopList',this.formData)
    },
    methods: {
      toShopDetail(shopNo) {
        this.$router.push({name: "shopDetail",query:{shopNo:shopNo}});
      },
      // tab 回掉函数 parm type类型 id选择排序的id
      shopListTabCB(type,id){
      	console.log(type,id)
      },
      /**
       * 点击搜索按钮
       */
      search(){
        this.$store.dispatch('getShopList',this.formData)
      },
    },
    components: {
      Icon,
      ToolBar,
      Search,
      ShopListTab
    },

  };
</script>

<style>
	.shop-list{
		background: #fff;
	}
	/*search*/
	.shop-list .van-search{
		padding: 0.16rem 0 0.16rem 0.3rem;
	}
	.shop-list .van-search__input-wrap{
		border: none;
		border-radius: 0;
		background: #f7f7f7;
	}
	.shop-list .van-search__input{
		background: #f7f7f7;
	}
	/*地址图标表*/
	.shop-list .location-icon{
		display: block;
		width: 0.44rem;
		height: 0.44rem;
		margin: 0 0.3rem;

		background: url(../../assets/images/icon/icon-dt.png) no-repeat center;
		background-size: 100%;
	}
	.index-shop-slt .selectBar{
		margin-top: 0;
	}
  .shopList {
    height: 105px;
    margin-left: 12px;
  }

  .shopInfo {
    padding-right: 15px;
    display: flex;
    flex-direction: row;
    border-bottom: 1px solid #ccc;
    justify-content: space-between;
  }

  .shopList .shopInfo .left {
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .shopList .shopInfo .left .redio {
    width: 28px;
    padding-right: 9px;
  }

  .shopList .shopInfo .left .redio img {
    width: 100%;
  }

  .shopList .shopInfo .left .text {
    display: flex;
    padding-top: 24px;
    flex-direction: column;
  }

  .shopList .shopInfo .left .text .area {
    font-size: 12px;
    color: #1a1a1a;
    margin-bottom: 6px;
  }

  .shopList .shopInfo .left .text .shopName {
    font-size: 18px;
    color: #1a1a1a;
    font-weight: bold;
    margin-bottom: 12px;
  }

  .shopList .shopInfo .left .text .shopName img {
    height: 16px;
    margin-left: 10px;
  }

  .shopList .shopInfo .left .text .addr {
    font-size: 12px;
    color: #b3b3b3;
    margin-bottom: 23px;
  }

  .shopList .shopInfo .right {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    font-size: 14px;
    color: #666666;
  }

  .shop-list .van-icon {
    color: #e5e5e5;
  }
</style>
