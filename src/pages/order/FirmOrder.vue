<template>
	<div class="firm-order">
		<user-address class="firm-order-addr" :isClick="true" @userAddr="userAddr" :orderDetailData="submitingOrderData.address">
			<i class="more-icon"></i>
		</user-address>
		<div class="fo-goods-info">
			<order-details-row :dataList="goodsDataList"></order-details-row>
			<!--积分兑换-->
			<div class="goods-check-integral goods-integral-deduction line-one-px">
				<div class="gci-left">
					<p class="gci-tit">积分兑换 <em class="gci-spec">该商品500积分可兑换</em></p>
					<p class="gci-txt">您有699积分</p>
				</div>
				<Checkbox class="gci-radio"  v-model="radio"></Checkbox>
			</div>
			<!--积分抵扣-->
			<div class="goods-integral-deduction line-one-px" v-if="radio">
				<div>
					<p class="gid-left-tet">您有699积分可抵扣6元</p>
					<p class="gid-left-tet">积分满100可使用</p>
				</div>
				<div class="gid-txt">
					使用
					<Field class="gid-num" type="number" v-model="value" placeholder="户名" ></Field>
					00个 可抵扣：￥1.00
				</div>
			</div>
			<!--优惠抵扣-->
			<div class="goods-sale-deduction line-one-px">
				<span class="gsd-txt">优惠券抵扣</span>
				<a class="seeMore">已减40<i class="more-icon"></i></a>
			</div>
			<div class="goods-sale-deduction line-one-px" @click="shopActive">
				<span class="gsd-txt">商城活动</span>
				<a class="seeMore">满100减20<i class="more-icon"></i></a>
			</div>
			<!--结算-->
			<div class="goods-price-wrap line-one-px">
				<span class="order-from">订单来源：在线商城</span>
				<div class="gp-keyword">
					<span class="gp-key">商品总额</span>
					<span class="gp-val">￥119.00</span>
				</div>
				<div class="gp-keyword">
					<span class="gp-key">积分抵扣</span>
					<span class="gp-val">-￥1.00</span>
				</div>
				<div class="gp-keyword">
					<span class="gp-key">优惠券抵扣</span>
					<span class="gp-val">-￥40.00</span>
				</div>
				<div class="gp-keyword">
					<span class="gp-key">商城活动</span>
					<span class="gp-val">-￥20.00</span>
				</div>
				<div class="gp-keyword">
					<span class="gp-key">+运费</span>
					<span class="gp-val">￥0.00</span>
				</div>
			</div>
			<!--结算2-->
			<div class="goods-all-price-wrap line-one-px">
				<span class="goods-ap-txt">共1件商品 实付：<em>￥118.00</em></span>
				<span class="goods-ap-txt">(含运费￥0.00)</span>
			</div>
		</div>
		<div class="flex-bottom-bar">
			<a class="submit-order-btn" @click="submitOrder">提交订单</a>
		</div>
		<!--弹出-->
		<ActionSheet :showAs="showAs" @hideAs="hideAs" :minHeight="48">
			<div class="as-shop-active">
		        <div class="as-sa-tit">
		          	<p>商城活动</p>
		        </div>
		        <div class="weui-cell line-one-px">
		            <span class="">满100减20</span>
		            <radio name="1" v-model="asShopActive"></radio>
		        </div>
		        <div class="weui-cell line-one-px">
		            <span class="">满2送1</span>
		            <radio name="2" v-model="asShopActive"></radio>
		        </div>
		        <div class="weui-cell line-one-px">
		            <span class="">不参与活动</span>
		            <radio name="3" v-model="asShopActive"></radio>
		        </div>
		        <div class="as-sa-btn" @click="submitChoose">确定</div>
		    </div>
		</ActionSheet>
	</div>
</template>

<script>
	import { Field, Checkbox, Radio } from 'vant';
	import ActionSheet from "components/ActionSheet";
	import UserAddress from "./orderDetail/UserAddress"
	import OrderDetailsRow from "components/OrderDetailsRow"
  import { mapGetters } from 'vuex';
	export default{
		name:"firmOrder",
		components:{
			Field,
      Checkbox,
			UserAddress,
			OrderDetailsRow,
			ActionSheet,
      Radio
		},
		data(){
			return{
				value: 5,
				radio: false,
				showAs: false, // 是否显示弹出
        asShopActive:'1',
        uuid:'',
			}
		},
    computed:{
      ...mapGetters([
        'getSubmitingOrderDataByUuid'
      ]),
      submitingOrderData(){
        return this.getSubmitingOrderDataByUuid(this.uuid)
      },
      goodsDataList(){
        let list = [];
        this.submitingOrderData.goodsData.forEach(item=>{
          list.push({
            productName:item.productData.title,
            productImg:item.color.image,
            price:item.productData.price,
            buyPrice:item.productData.old_price,
            color:item.color.text,
            size:item.size,
            count:item.num,
          })
        });
        return list;
      },
    },
    created(){
      this.uuid = this.$route.query.uuid;
      if(!!!this.uuid){//如果么有uuid则抛出异常
        this.$store.commit('showMsgTips','没有查到该订单，请重新选择！');
      }

    },
		methods:{

			submitOrder(){
				this.$router.push({name:'OrderDetail',query:{type:3}})
			},
			// 地址点击
			userAddr(){
				this.$router.push({name:'MyAddress',query:{uuid:this.uuid}})
			},
			//显示商城活动
			shopActive(){
				this.showAs = true;
			},
			// 隐藏弹出
			hideAs(){
				this.showAs = false;
			},
			// 弹出层 确定
			submitChoose(){
				this.showAs = false;
			}
		}
	}
</script>
<style>
	.firm-order-addr{
		display: flex;
		align-items: center;
	}
	.firm-order-addr .user-addr{
		flex: 1;
	}
	.gid-num .van-field__control{
		text-align: center;
		color: #808080;
		background: #f2f2f2;
	}
	.firm-order .van-radio__label{
		margin-left: 0;
	}
	.firm-order .van-radio .van-icon-checked{
		color: #ea5361;
	}
</style>
<style scoped>
	.firm-order{
		height: 100%;
		padding-bottom: 0.88rem;
		background: #f7f7f7;
	}
	.fo-goods-info{
		margin-top: 0.2rem;
		background: #fff;
	}
	/*商品价格块*/
	.goods-price-wrap{
		padding: 0.18rem 0.3rem 0.28rem 0.3rem;
		font-size: 0.24rem;
		color: #999;
		line-height: 1;
	}
	.goods-price-wrap::after{
		left: 0.24rem;
	}
	.order-from{
		display: block;
		padding-bottom: 0.14rem;
	}
	.gp-keyword{
		padding-top: 0.06rem;
		display: flex;
		justify-content: space-between;
	}
	.gp-val{
		color: #b3b3b3;
	}
	/*积分兑换*/
	.gci-left{
		font-size: 0.24rem;
	}
	.gci-spec{
		color: #ea5361;
	}
	.gci-tit{
		color: #000;
	}
	.gci-txt{
		color: #999;
	}
	/*积分抵扣*/
	.goods-integral-deduction{
		padding-left: 0.24rem;
		padding-right: 0.3rem;
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 0.88rem;
	}
	.gid-left-tet{
		line-height: 1.2;
		font-size: 0.2rem;
		color: #999;
	}
	.gid-txt{
		display: flex;
		color: #808080;
	}
	.gid-num{
		width: 0.68rem;
		height: 0.48rem;
		padding: 0;
		margin: 0 0.12rem;
	}
	/*优惠券抵扣*/
	.goods-sale-deduction{
		padding-left: 0.24rem;
		padding-right: 0.3rem;
		height: 0.76rem;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.gsd-txt{
		color: #4d4d4d;
	}
	.seeMore{
		font-size: 0.24rem;
		color: #808080;
	}
	/*结算*/
	.goods-all-price-wrap{
		padding: 0.16rem 0.3rem 0.2rem 0;
		display: flex;
		flex-direction: column;
		align-items: flex-end;
	}
	.goods-ap-txt{
		font-size: 0.2rem;
		color: #999;
		line-height: 1.2;
	}
	.goods-ap-txt em{
		font-size: 0.24rem;
		color: #ea5361;
	}
	/*提交订单按钮*/
	.flex-bottom-bar{
		position: fixed;
		right: 0;
		bottom: 0;
		left: 0;

		display: flex;
		justify-content: flex-end;
		align-items: center;
		padding-right: 0.3rem;
		height: 0.88rem;
		background: #fff;
	}
	.submit-order-btn{
		display: block;
		width: 1.96rem;

		line-height: 0.6rem;
		text-align: center;
		font-size: 0.3rem;
		color: #fff;
		background: #ea5361;
	}
	/* 商城活动弹出  */
	.as-shop-active{
		position: absolute;
		top: 0;
		right: 0;
		bottom: 0;
		left: 0;
		background: #fff;
	}
	.as-sa-tit{
		line-height: 0.76rem;
		text-align: center;
		color: #000;
		background: #f7f7f7;
	}
	.as-shop-active .weui-cell{
		justify-content: space-between;
		height: 0.76rem;
	}
	.line-one-px::after{
		left: 0.3rem;
		right: 0.3rem;
	}
	.goods-all-price-wrap::after{
		left: 0;
		right: 0;
	}
	.as-sa-btn{
		position: absolute;
		right: 0;
		bottom: 0;
		left: 0;
		line-height: 0.88rem;
		text-align: center;
		font-size: 0.32rem;
		color: #fff;
		background: #ea5361;
	}
</style>
