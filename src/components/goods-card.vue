/**
封装一个商品卡片
props:
  type:       商品卡片类型，可选值'card'和'list',对应的是以卡片还是以列表形式展示
  image:      商品图片，
  title:      商品标题
  price:      商品当前价格，
  old_price:  商品打折前价格
  id:         商品ID
  liked:      是否收藏
  show-like-btn: 是否显示收藏按钮
events:
  like-click: 收藏按钮被点击是触发事件like-click
methods:

*/
<template>
	<div class="component-goods-card fontsize-28" :class="[type]">
    <div class="goods-card-image">
      <img v-lazy="image" alt="">
    </div>
    <div class="component-goods-card-content">
      <div class="goods-card-title">
        <p class="ellipsis-s">{{title}}</p>
      </div>
      <div>
        <span class="red inline-block marginR5 fontBold">￥{{price}}</span>
        <span class="gray inline-block line-through old_price fontsize-24" v-if="old_price">￥{{old_price}}</span>
      </div>
      <div class="like-btn-wrapper" v-if="showLikeBtn" @click.prevent="switchLike">
        <div class="like-btn" :class="[liked?'like':'dislike']"></div>
      </div>
    </div>
	</div>
</template>

<script>
	export default {
	  props:{
	    type:{
	      type:String,
        default:'card'
      },
      id:{
        type:Number,
      },
      image:{
        type:String,
        required:true,
      },
      title:{
        type:String,
        required:true,
      },
      price:{
        type:Number,
        required:true,
      },
      old_price:{
        type:Number
      },
      liked:{
	        type:Boolean,
          default:false,
      },
      showLikeBtn:{
        type: Boolean,
        required: false
      },
    },
		data() {
			return {}
		},
    methods:{
      /**
       * 爱心按钮被点击，向上抛一个事件
       */
      switchLike(event){
        event.stopPropagation();
          this.$emit('like-click');
      },
    },
	}
</script>

<style scoped>
.component-goods-card{
  line-height: 1.4;
  box-sizing: border-box;
  position: relative;
  color:#000;
}
.goods-card-title{
  height: 0.8rem;
  color:#4d4d4d;
}
img{
   display: block;
   width: 100%;
 }
.ellipsis-s{
  overflow:hidden;

  text-overflow:ellipsis;

  display:-webkit-box;

  -webkit-box-orient:vertical;

  -webkit-line-clamp:2;
}


  .component-goods-card-content{
    position: relative;
    padding-top: 0.2rem;
  }
  .like-btn-wrapper{
    position: absolute;
    top: -0.62rem;
    right: 0.13rem;
    width: 0.533333rem;
    height: 0.533333rem;
  }
  .like-btn{
    width: 100%;
    height: 100%;
    background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
  }
  .like-btn.like{
    background-image: url(../assets/icon/icon-sc-dj.png);
    animation-name: scale-spring;
    animation-duration: .28s;
    animation-fill-mode: both;
  }
.like-btn.dislike{
  background-image: url(../assets/icon/icon-sc-wdj.png);
}

  /*列表显示*/
  .component-goods-card.list{
    padding-left: 2.4rem;
    height: 2.133333rem;
  }
.component-goods-card.list .goods-card-image{
  position: absolute;
  top: 0;
  left: 0;
  width: 2.133333rem;
  height: 2.133333rem;
}
.component-goods-card.list .component-goods-card-content{
  padding-top: 0;
  height: 2.133333rem;
}
.component-goods-card.list .like-btn-wrapper{
  bottom: 12px;
  right: 0px;
  top: auto !important;
}
.component-goods-card.list .goods-card-title{
  height: 1.4rem;
}
</style>
