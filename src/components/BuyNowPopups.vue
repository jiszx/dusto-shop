/**
封装一个商品购买业务组件
props:
  type: String 购物车显示类型，buy:显示立即购买和加入购物车按钮， confirm：显示确认按钮
  product-data: 要购买商品信息
  activeColorId：当前选中的颜色id

events:
  close:弹窗关闭事件
  open:弹窗打开事件
  add-car:点击添加购物车事件
  buy-now:点击立即购买按钮事件
  confirm-click：点击确认按钮事件
methods:
  close：关闭收起弹窗
  open: 展开弹窗
*/
<template>
    <div class="component-buy-now-popups fontsize-24" :style="{zIndex:unfold?101:9}">
      <div class="buy-now-btn-wrapper text-right van-hairline--top" @click="open"   :style="{zIndex:unfold?101:9}">
        <div class="buy-now-btn add-goods-car fontsize-30" @click.prevent="addCar" v-if="temp_type==='buy'">加入购物车</div>
        <div class="buy-now-btn buy-now fontsize-30" @click.prevent="buy" v-if="temp_type==='buy'">立即购买</div>
        <div class="buy-now-btn buy-now fontsize-30" @click.prevent="confirm" v-if="temp_type==='edit'">确认</div>
        <div class="buy-now-popups-confirm fontsize-32" @click.prevent="confirm" v-if="temp_type==='confirm'">确认</div>
      </div>
      <div class="buy-now-popups-wrapper" :class="{unfold:unfold}">
        <div class="buy-now-popups"  @click.prevent="close">
          <div class="buy-now-popups-inner">
            <div class="buy-now-popups-main" @click.prevent="preventClose">
              <!--购物车产品title-->
              <div class="product-title-wrapper">
                <div class="product-image">
                  <img v-lazy="selectProductImage" alt="">
                </div>
                <div class="product-title">
                  <div class="info-text-wrapper">
                    <p class="title ellipsis fontsize-28">{{productData.title}}</p>
                  </div>
                  <div>
                    <span class="red inline-block marginR5 fontsize-28 fontBold">￥{{productData.price}}</span>
                    <span class="gray inline-block line-through old_price fontsize-24" v-if="productData.old_price">￥{{productData.old_price}}</span>
                  </div>
                  <div v-if="productData.Integral">
                    <p class="red fontsize-24">{{productData.Integral}}积分可兑换</p>
                  </div>
                </div>
              </div>
              <!--选择颜色-->
              <div class="choose-color-wrapper">
                <p class="title fontsize-28">颜色</p>
                <ul class="clearfix">
                  <li
                    v-for="(item,index) in productData.colorType"
                    :key="index"
                    :class="{active:color.id===item.id}"
                    @click="colorTypeSelect(item)">
                    <img :src="item.image" alt="" class="img-block">
                  </li>
                </ul>
              </div>
              <!--选择尺寸-->
              <div class="choose-size-wrapper">
                <div>
                  <p class="title inline-block">您经常选择的尺码：</p>
                  <div class="inline-block">
                    <span class="goods-size-item">37</span>
                    <span class="goods-size-item">38</span>
                  </div>
                </div>
                <div>
                  <p class="title inline-block">选择尺码：</p>
                  <div class="inline-block">
                    <span class="goods-size-item"
                          v-for="iterm in currentColorSizeList"
                          :class="{active:selectSize==iterm.size}"
                          @click="chooseSize(iterm)"
                    >{{iterm.size}}</span>
                  </div>
                </div>
              </div>

              <!--选择数量-->
              <div class="choose-num van-hairline--top fontsize-28" v-if="!hideNum">
                <p class="inline-block title">数量：</p>
                <div class="inline-block pull-right">
                  <Stepper
                    v-model="selectNum"
                    :min="1"
                    :max="maxNum"
                    :default-value="1"
                    disable-input
                  />
                </div>
              </div>

            </div>
          </div>
        </div>
        <div class="buy-now-popups-mask"></div>
      </div>
    </div>
</template>

<script>
    import { Stepper } from 'vant';
    import { getStocksinfo } from 'api/productrs'
    export default{
        props:{
          type:{
            type:String,
            default:'buy',//buy购买，confirm 显示确认按钮 eidt 显示确认按钮
          },
          productData:{
              type:Object,
          },
          activeColorId:{
              type:Number,
          },
          show:{
            type:Boolean,
            require:false,
          },
          hideNum:Boolean,
        },
      data(){
            return{
                temp_type:'buy',
                unfold:false,
                color:{},
                selectSize:37,
                selectNum:1,
            }
      },
      watch:{
        unfold(current,old){
          if(current){
            this.getStocksinfo();
          }
        },
      },
      computed:{
          selectProductImage(){
              var image='';
              this.productData.colorType.forEach(iterm=>{
                  if(iterm.id==this.colorId){
                    image=iterm.image;
                  }
              })
            return image;
          },
        currentColorSizeList(){
            var sizeList = this.color.sizeList;
            //todo 待后台接口完善后再填写逻辑
            return sizeList;
        },
        maxNum(){//可选最大数根据颜色尺码返回
            var max  = 1000;//默认
          var item = this.currentColorSizeList.find(v=>{
            return v.size == this.selectSize;
          })
          if(item){
            max= item.num;
          }
          return max;
        },
        currentData(){
            return {
              productData:this.productData,
              color:this.color,
              size:this.selectSize,
              num:this.selectNum,
            }
        },
      },
      created(){
          this.colorId=this.activeColorId;
          this.unfold = !!this.show;
          this.temp_type = this.type;
          if(this.temp_type==='confirm'){
            this.unfold=true;
          };
          // 默认选中颜色
          if(this.activeColorId!==undefined){
            this.color = this.productData.colorType.find(v=>{
              return v.id = this.activeColorId;
            })
          }else{
            this.color=this.productData.colorType[0];
          }
      },
      components:{
        Stepper
      },
      methods:{
        /**
         * 选择颜色样式
         * @param id
         */
        colorTypeSelect(data){
          this.color=data;
          console.log('当前选择颜色id:',data,this.color);
          //todo 选择样式颜色
        },
        /**
         * 选择尺码
         * @param item
         */
        chooseSize(item){
            this.selectSize=item.size;
        },
        /**
         * 关闭收起弹窗
         */
        close(){
          this.unfold=false;
          if(this.temp_type!='buy'){
            this.temp_type='buy'
          }
          this.$emit('close');
        },
        open(event){
            if(!this.unfold){
              this.unfold=true;
              this.$emit('open');
              event.stopPropagation();
              return;
            }
        },
        preventClose(event){
          event.stopPropagation();
        },

        /**
         * 添加购物车
         */
        addCar(){
          if(this.unfold){
            this.$emit('add-car',this.currentData)
          }else{
            this.unfold=true;
            this.$emit('open');
          }

        },
        /**
         * 立即购买
         */
        buy(){
          if(this.unfold){
            this.$emit('buy-now')
          }else{
            this.unfold=true;
            this.$emit('open');
          }
        },
        /**
         * 点击确认按钮
         */
        confirm(){
          this.$emit('confirm-click',this.currentData)
        },
        /**
         * 获取商品库存
         */
        getStocksinfo(){
          let params = {
            artNo:this.productData.artNo,
            phone:"18200000000",
            cityCode:"331100"
          };
          getStocksinfo(params).then(res=>{
            console.log(res)
          }).catch(err=>{

          })
        },
      },
    }
</script>

<style scoped>
  .component-buy-now-popups{
    position: relative;
  }
  .title{

  }
.buy-now-btn-wrapper{
  box-sizing: border-box;
  position: relative;
  height: 1rem;
  width: 100%;
  padding: 0.2rem 0 0;
  background: #f7f7f7;
  z-index: 9;
}
  .buy-now-btn{
    display: inline-block;
    vertical-align: middle;
    width: 1.96rem;
    height: 0.6rem;
    line-height: 0.6rem;
    color:#fff;
    text-align: center;
    margin-right: 0.3rem;
  }
  .buy-now-popups-confirm{
    width: 100%;
    height: 0.8rem;
    line-height: 0.8rem;
    text-align: center;
    color:#fff;
    background: #ea5361;
  }

.add-goods-car{
  background: #f8b500;
  margin-right: 10px;
}
  .buy-now{
    background: #ea5361;
  }

  .buy-now-popups-wrapper{
    position: fixed;
    bottom: 0;
    left: 0;
    box-sizing: border-box;
    width: 100%;
    height: 100%;
    padding-bottom: 1rem;
    z-index: 100;
  }
  .buy-now-popups-mask{
    position: absolute;
    top:0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0,0,0,.3);
    z-index: 1;
  }
  .buy-now-popups{
    box-sizing: border-box;
    position: relative;
    width: 100%;
    height: 100%;
    z-index: 10;
  }
  .buy-now-popups-inner{
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 66%;
    background: #fff;
  }
  .buy-now-popups-main{
    position: relative;
    box-sizing: border-box;
    min-height: 100%;
    padding: 0 0.3rem 1rem;
  }

  .product-title-wrapper{
    position: relative;
  }
  .product-title{
    padding-left: 2rem;
  }
  .product-image{
    position: absolute;
    top: -0.5rem;
    width: 1.8rem;
    height: 1.8rem;
  }
  .product-image img{
    display: block;
    width: 100%;
    height: 100%;
  }

  .info-text-wrapper{
    padding-top: 0.1rem;
    line-height: 1.4;
  }

  .choose-color-wrapper{
    padding-top: 0.6rem;
  }
  .choose-color-wrapper ul{
    padding-top: 0.2rem;
  }
  .choose-color-wrapper ul li{
    box-sizing: border-box;
    float: left;
    width: 1rem;
    height: 1rem;
    margin-right: 10px;

  }
  .choose-color-wrapper ul li.active{
    border: 1px solid #999;
  }

  .choose-size-wrapper>div{
    padding-top: 0.4rem;
  }
  .choose-size-wrapper>div>.inline-block{
    vertical-align: middle;
  }
  .choose-size-wrapper>div>div.inline-block{
    margin-left: 10px;
  }

  .goods-size-item{
    display: inline-block;
    width: 0.4rem;
    height: 0.4rem;
    line-height:0.4rem;
    text-align: center;
    background: #f2f2f2;
    border-radius: 20%;
    margin-right: 10px;
  }
  .goods-size-item.active{
    background-color: #ea5361;
    color:#fff;
  }

  .choose-num{
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    min-height: 1rem;
    line-height: 0.6rem;
    padding: 0.2rem 0.3rem;
    box-sizing: border-box;
  }

  /*动画*/
  .buy-now-popups-wrapper{
    bottom: -100%;
  }
  .buy-now-popups-wrapper.unfold{
    bottom: 0;
  }
  .buy-now-popups-wrapper .buy-now-popups-mask{
    opacity: 0;
    transition: .4s all;
  }
  .buy-now-popups-wrapper.unfold .buy-now-popups-mask{
    opacity: 1;
  }
  .buy-now-popups-wrapper .buy-now-popups-inner{
    opacity: 0;
    bottom: -100%;
    transition: .3s all;
  }
  .buy-now-popups-wrapper.unfold .buy-now-popups-inner{
    opacity: 1;
    bottom: 0;
  }
</style>
