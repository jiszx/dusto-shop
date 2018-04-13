<template>
	<div>
	    <ul class="orderDetailsRow">
	      <li class="list-item line-one-px" v-for="(item,index) in dataList" :key="index">
	      	<div class="userImg"><img alt="pic" :src="item.productImg" /></div>
	        <div class="goods-info">
	          <div class="goods-info-txt">
	            <span class="goods-txt">{{item.productName}}</span>
	          </div>

            <div class="goods-spec goods-spec-price">
              <span class="presentPrice">&yen<span class="">{{item.buyPrice}}</span></span>
            </div>

	          <div class="goods-spec  goods-spec-spec">
	          	<span class="goods-spec-item">颜色:{{item.color}}</span>
  		        <span class="goods-spec-item">尺码:{{item.size}}码</span>
  		        <span class="goods-spec-item rightSpan old-price"><span class="originalPrice">&yen{{item.price}}</span></span>
	          </div>
	          <div class="goods-spec">
	            <span class="goods-spec-item">{{item.buyTime}}</span>
	            <span class="goods-spec-item">{{item.buyTime}}</span>
	            <span class="goods-spec-item rightSpan">&#215<span>{{item.count}}</span></span>
	          </div>
	        </div>
	      </li>
	    </ul>
	</div>
</template>

<script>
	export default{
	  props:['dataList'],
		components:{

		},
		data() {
		    return {
		    	checked: true,
		    	items: [
		          {
		          	checked: true,
		          	imgUrl:require("../assets/images/list-goods/img-1.png"),
		            name: "绑带尖嘴高跟鞋",
		            presentPrice: 99.00,
		            color:"灰色",
		            size: 28,
		            date:"2018-01-02",
		            time:"12:45:38",
		            originalPrice: 239.00,
		            resource:"在线商城",
		            resTotal: 1
		          }
		        ]
		    }
		},
		created(){
		},
		methods:{

		}
	}
</script>

<style scoped>
  ul.orderDetailsRow li{
    background: #fff;
  }
  .line-one-px::after{
  	left: 0.24rem;
  }
  .list-item {
    display: flex;
    padding: 0.1rem 0.3rem 0.1rem 0.21rem;
  }

  .list-img {
      width: 1.6rem;
      height: 1.6rem;
  }
  .userImg img{
    width: 1.6rem;
    height: 1.6rem;
  }

  .goods-info {
    padding: 0.16rem 0 0.24rem 0.24rem;
    -webkit-box-flex: 1;
    -webkit-flex: 1;
    flex: 1;
    display: -webkit-box;
    display: -webkit-flex;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -webkit-flex-direction: column;
    flex-direction: column;
    -webkit-box-pack: justify;
    -webkit-justify-content: space-between;
    justify-content: space-between;
  }
  .goods-info-txt {
      display: -webkit-box;
      display: -webkit-flex;
      display: flex;
  }
  .goods-txt {
      height: 0.46rem;
      font-size: 0.28rem;
      color: #000;
      line-height: 0.36rem;
      overflow: hidden;
      -webkit-box-flex: 1;
      -webkit-flex: 1;
      flex: 1;
  }
  .goods-spec-price{
    text-align:right;
    margin-top: -0.4rem;
  }
  .goods-spec-spec{
    margin-bottom: -5px;
  }
  .goods-spec-item {
      color: #999;
      font-size: 0.2rem;
  }
  .goods-spec-item + .goods-spec-item {
      margin-left: 0.2rem;
  }

  .goods-spec .rightSpan{
  	float: right;
  }
  .rightSpan .originalPrice{
    text-decoration:line-through;
  }
</style>
