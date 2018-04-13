/**
  封装一个商品评论业务组件，用于展示一条评论内容
  props:
    data: Object 评论详情数据

  events:

  methods:

*/
<template>
	<div class="component-comment fontsize-28">
    <div class="comment-user paddingB20">
      <div class="inline-block">
        <span class="user-head-image">
          <img v-lazy="data.userHead" alt="">
        </span>
        <span class="user-name">{{data.username}}</span>
      </div>
      <p class="gray comment-date fontsize-24">
        {{data.commontDate}}
      </p>
    </div>
    <div class="comment-text fontsize-28">
      {{data.comment}}
    </div>
    <div class="comment-image paddingT20" v-if="data.images&&data.images.length">
      <ul>
        <li v-for="(item,index) in data.images" :key="index">
          <img v-lazy="item" alt="">
        </li>
      </ul>
    </div>
    <div class="gray comment-color-type fontsize-10 fontsize-20 paddingT10">
      <p>
        <span>
          <span>颜色： </span>
          <span> {{data.colorType}} </span>
        </span>
        <span>
          <span> 尺码： </span>
          <span>{{data.size}}码</span>
        </span>
      </p>
      <p class="paddingB10 paddingT5">
        <span>
          <span>购买日期： </span>
          <span>{{data.buyDate}}</span>
        </span>
      </p>
    </div>
	</div>
</template>

<script>
	export default {
    props:{
      data:{
          type:Object
      },
    },
		data() {
			return {}
		}
	}
</script>

<style scoped>
.component-comment{
  box-sizing: border-box;
  padding: 0.2rem 0;
}
.user-head-image{
  display: inline-block;
  width: 0.64rem;
  height: 0.64rem;
  border-radius: 50%;
  overflow: hidden;
  margin-right: 0.188889rem;
}
.user-name{
}
.comment-user>div>span{
  display: inline-block;
  vertical-align: middle;
}
.user-head-image img{
  display: block;
  width: 100%;
  height: 100%;
}
  .comment-date{
    float: right;
    line-height: 0.64rem;

  }
.comment-text{

}
.comment-color-type{

}
  .comment-image ul{}
  .comment-image ul li{
    display: inline-block;
    width: 1.2rem;
    height: 1.2rem;
    margin-right: 0.133333rem;
  }
.comment-image ul li img{
  display: block;
  width: 100%;
  height: 100%;
}
  .comment-color-type>p+p{

  }
.comment-color-type>p>span{
  display: inline-block;
  margin-right: 0.3rem;
}
</style>
