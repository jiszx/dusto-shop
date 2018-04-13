<template>
	<div class="cart cart-goods" :class="{'edit-cart':isEdit}">
		<div class="cart-tit">
			<a class="cart-edit-btn" @click="editFun">{{isEdit?'完成':'编辑'}}</a>
		</div>
		<!--列表暂时做一层循环 后面看情况是否要日期循环-->
		<div class="cart-time-list">
			<div class="cart-tl-item line-one-px">
        <!--针对整个订单的赠品-->
        <div class="present-wrapper border-1px-b" v-if="selectedGoods.length">
          <div class="present-list">
            <div v-for="(present,i) in hasSelectGoods.presentList" :key="i">
              <CellSwipe :right-width="65">
                <cart-goods hideNum  @editGoodsFun="editOrderPresentFun(i)" :goodsData="present"></cart-goods>

                <span slot="right">删除</span>
              </CellSwipe>
            </div>
          </div>
          <!--添加赠品按钮-->
          <div class="present-go-select gray _van-red-icon">
            <Cell is-link :to="{name:'PresentList',query:{type:'orderPresent',selectedGoods:selectedGoods}}">
              <span slot="title" class="gray">您有赠品待领取</span>
              <span class="red">去领取</span>
            </Cell>
          </div>
        </div>

				<checkbox-group v-model="selectedGoods">
					<ul class="cart-goods-list">
						<li class="cart-gl-item  line-one-px" v-for="(item,index) in shoppdingCartData">
							<Checkbox :name="item.uuid">
								<cart-goods @editGoodsFun="editGoodsFun(index)" :goodsData="item"></cart-goods>
							</Checkbox>
              <div class="present-wrapper">
                <!--赠品-->
                <div class="present-list">
                  <div v-for="(present,i) in item.presentList" :key="i">
                    <CellSwipe :right-width="65">
                      <cart-goods hideNum @editGoodsFun="editPresentFun(index,i)" :goodsData="present"></cart-goods>

                      <span slot="right">删除</span>
                    </CellSwipe>
                  </div>
                </div>
                <!--添加赠品按钮-->
                <div class="present-go-select gray _van-red-icon">
                  <Cell is-link :to="{name:'PresentList',query:{uuid:item.uuid,type:'goodsPresent',selectedGoods:selectedGoods}}">
                    <span slot="title" class="gray">您有赠品待领取</span>
                    <span class="red">去领取</span>
                  </Cell>
                </div>
              </div>
						</li>
					</ul>
				</checkbox-group>
			</div>
		</div>
		<div class="cart-bottom-bar">
			<div class="edit-show" v-show="isEdit">
				<a class="save-btn"@click="moveToCollect">移入收藏夹</a>
				<a class="remove-btn" @click="removeGoods">删除</a>
			</div>
			<div class="submit-wrap line-one-px">
				<Checkbox :disabled="isEdit" v-model="selectAll" @change="allSelectChange">全选</Checkbox>
				<span class="cart-all-price"><span>{{hasSelectGoods.num}}</span> 件商品 合计 ￥<span>{{hasSelectGoods.price}}</span></span>
			</div>
			<div class="cart-submit-btn-wrap">
				<a class="cart-submit-btn"
					:class="{'edit-submit-btn':isEdit||!hasSelectGoods.num}"
					@click="submitFun">结算 <span v-if="hasSelectGoods.num">({{hasSelectGoods.num}})</span></a>
			</div>
		</div>
    <div class="buy-now-btn-wrapper" v-if="isShowAs">
      <BuyNowPopups
        v-if="shoppdingCartData.length"
        :product-data="buyNowPopupsData.productData"
        :active-color-id="buyNowPopupsData.color.id"
        type="edit"
        :show="isShowAs"
        :hideNum="isEditPresent"
        @close="BuyNowPopupsClose"
        @confirm-click="confirmClick"
      />
    </div>

		<quick-bar></quick-bar>
	</div>
</template>

<script>
import { mapState } from 'vuex'
import { Radio, Checkbox, CheckboxGroup, Cell, CellSwipe } from 'vant';
import CartGoods from './CartGoods';
import ActionSheet from "components/ActionSheet";
import GoodsSpec from "components/GoodsSpec";
import QuickBar from "components/QuickBar";
import BuyNowPopups from '@/components/BuyNowPopups'
export default{
	name: "cart",
	components:{
		Radio,
		Checkbox,
		CheckboxGroup,
		CartGoods,
		ActionSheet,
		GoodsSpec,
		QuickBar,
    BuyNowPopups,
    Cell,
    CellSwipe
	},
	data() {
	    return {
        isEdit: false, // 是否是编辑状态
        isShowAs: false, // 显示隐藏弹出层
        selectedGoods: [],
        selectAll: false,
        currentInd: 0, // 当前修改数据的index
        currentPresentId:0,//当前赠品index
        isEditPresent:false,//是否是编辑赠品（赠品不显示数量，所以要单独处理）
        isEditOrderPresent:false,//是否是编辑订单的赠品（赠品不显示数量，所以要单独处理）
        orderPresentIndex:0,//当前选中商品整单的赠品index
	    }
	},
	computed:{
    ...mapState({
      shoppdingCartData: state => state.cart.shoppingCart,
      hasSelectGoods: state => state.cart.hasSelectGoods
    }),
    /**
     * 根据不同状态
     */
    buyNowPopupsData(){
      if(!this.isEditPresent&&!this.isEditOrderPresent){
        console.log(this.shoppdingCartData[this.currentInd].productData);
        return this.shoppdingCartData[this.currentInd]
      }else if(this.isEditPresent&&!this.isEditOrderPresent){
        return this.shoppdingCartData[this.currentInd].presentList[this.currentPresentId]
      }else if(this.isEditOrderPresent){
        return this.hasSelectGoods.presentList[this.orderPresentIndex]
      }
    },
	},
	created(){
	  if(this.$route.query.selectedGoods){
      this.selectedGoods=this.$route.query.selectedGoods;
    }
    //如果没有默认选中项则先清空一次vuex购物车选中数据
	  if(!this.selectedGoods.length){
      this.$store.dispatch('emptyCartSelect');
    }
	},
	methods:{
	  //清空选中
    emptySelect(){
      this.selectedGoods=[];
      this.selectAll = false;
    },
		// 点击编辑事件
		editFun(){
			this.isEdit = !this.isEdit;
		},
		// 点击结算按钮事件
		submitFun(){
			if(this.isEdit) return;
			//todo 将选中物品加入到待提交订单vuex中
      let goodsData = [];
      this.shoppdingCartData.forEach(item=>{
        if(this.selectedGoods.includes(item.uuid)){
          goodsData.push(item);
        }
      });
      let uuid = 'dusto-order'+(new Date).getTime();
      let submitingObj = {
        uuid:uuid,
        goodsData:goodsData,//商品数据
        address:{},//用户地址
        jifen:0,//积分
        coupon:{},//优惠券
        activity:{}//活动
      }
      this.$store.dispatch("addSubmitingOrder",submitingObj);
      this.$router.push({name:"FirmOrder",query:{uuid:uuid}})
		},
		// 隐藏弹出层
		hideAs(){
			this.isShowAs = false;
		},
		// 编辑
		editGoodsFun(index){
      this.isEditPresent = false;
      this.isEditOrderPresent = false;
			this.isShowAs = true;
			this.currentInd = index // 当前修改的数据
		},
    /**
     * 点击编辑赠品
     */
    editPresentFun(index,presentIndex){
      this.isEditOrderPresent = false;
      this.isEditPresent = true;
      this.isShowAs = true;
      this.currentInd = index // 当前修改的数据
      this.currentPresentId = presentIndex;
    },
    editOrderPresentFun(index){
      this.isEditOrderPresent = true;
      this.isEditPresent = true;
      this.isShowAs = true;
      this.orderPresentIndex=index;
    },
    //点击全选
    allSelectChange(val){
      var temp = [];
      if(val){
        this.shoppdingCartData.forEach(item=>{
          temp.push(item.uuid);
        });
      }
      this.selectedGoods=temp;
    },
  /**
   * 点击移入收藏夹
   */
    moveToCollect(){
      //todo 待完善
      this.isEdit = false;
    },
		// 删除商品
		removeGoods(){
      var uuidList = this.selectedGoods;
      console.log(this.selectedGoods)
      this.selectedGoods=[];
      uuidList.forEach(item=>{
        this.$store.dispatch('deleteShoppingCartGoodsByUuid',item);
      });
			this.isEdit = false;
		},
    /**
     * 编辑购物车点击确定按钮
     */
    confirmClick(data){
      if(!this.isEditPresent){
        this.$store.dispatch('updateShoppingCartByUuid',{
          uuid:this.shoppdingCartData[this.currentInd].uuid,
          ...data
        });
      }else{
        this.$store.dispatch('updatePresentByUuid',{
          productUuid:this.shoppdingCartData[this.currentInd].uuid,
          uuid:this.shoppdingCartData[this.currentInd].presentList[this.currentPresentId].uuid,
          data:data
        });
      }

      this.hideAs();
    },
    BuyNowPopupsClose(){
      this.isShowAs=false;
      this.hideAs();
    },
    /**
     * 更新选中的信息，既有更改后请求接口拉取待提交订单相关信息
     */
    updateHasSelectInfo(){
      //todo
    },

	}
}
</script>

<style>
@import url("../../assets/css/list.css");
/*checkbox*/


.cart{
	height: 100%;
	padding-bottom: 1.8rem;
	overflow: auto;
}
.edit-cart{
	padding-bottom: 2.4rem;
}
/*顶部编辑*/
.cart-tit{
	display: flex;
	justify-content: flex-end;
	align-items: center;
	height: 0.88rem;
	padding: 0 0.3rem;
}
.cart-edit-btn{
	font-size: 0.3rem;
	color: #1a1a1a;
}

/*时间列表类*/
.cart-tl-item{
	padding-left: 0.24rem;
}
.cart-tl-tit{
	display: flex;
	align-items: center;

	height: 0.76rem;
}
.cart-tl-tit::after{
	left: 0.6rem;
}
/*商品类*/


/*底部*/
.cart-bottom-bar{
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;

	background: #f7f7f7;
}
.edit-show{
	display: flex;
	justify-content: flex-end;
}
.save-btn,.remove-btn{
	line-height: 0.6rem;
	text-align: center;
	width: 25%;

	font-size: 0.3rem;
	color: #fff;
}
.save-btn{
	background: #f8b500;
}
.remove-btn{
	background: #ea5361;
}
.submit-wrap{
	padding: 0.24rem 0.3rem 0.36rem 0.24rem;
	display: flex;
	justify-content: space-between;
	align-items: center;
	line-height: 1;
}
.submit-wrap::after{
	top: 0;
	bottom: auto;
}
.cart .submit-wrap .van-icon{
	font-size: 0.36rem;
}
.cart .van-radio__input{
	height: 0.36rem;
}
.cart-all-price{
	font-size: 0.24rem;
	color: #808080;
}
.cart-submit-btn-wrap{
	display: flex;
	justify-content: flex-end;
	padding-right: 0.3rem;
	padding-bottom: 0.2rem;
}
.cart-submit-btn{
	width: 2rem;
	line-height: 0.6rem;

	text-align: center;
	font-size: 0.3rem;
	color: #fff;
	background: #ea5361;
}
.edit-submit-btn{
	background: #C1C1C7;
}

.buy-now-btn-wrapper{
  position: fixed;
  width: 100%;
  max-width: 750px;
  height: 1rem;
  left: 0;
  bottom: 0;
  z-index: 100;
}
  .present-list{
    padding-left: 30px;
  }
  .cart-goods-list .present-go-select{
    padding-left: 30px;
  }
</style>
