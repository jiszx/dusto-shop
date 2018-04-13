<template>
	<div class="shop-info">
        <div class="shop-info-left">
          	<div class="shop-info-text">
            	<div class="shop-area">{{itemData.location}}</div>
       		 	<div class="shop-name">{{itemData.shopName}} 
       		 		<img class="black-star-icon" v-if="itemData.star" src="../assets/images/img-star-hb.png"/>
        		</div>
           	 	<div class="shop-addr">{{itemData.address}}</div>
         	 </div>
        </div>
        <div class="shopInfo-right">
          	<small>{{itemData.distance}}</small>
        </div>
    </div>
</template>

<script>
	export default{
		name:"shopItem",
		props:{
			itemData: {
				type: Object
			}
		}
	}
</script>

<style scoped>
	.shop-info {
	    padding-right: 0.6rem;
	    display: flex;
	    justify-content: space-between;
	    line-height: 1;
	}

  	.shopi-info-left {
	    display: flex;
  	}
  	.shop-info-text {
	    padding: 0.48rem 0 0.46rem;
  	}

  	.shop-area {
  		margin-bottom: 0.12rem;
	    font-size: 0.24rem;
	    color: #1a1a1a;
	    
  	}
  	.shop-name {
	    font-size: 0.36rem;
	    color: #1a1a1a;
	    font-weight: bold;
	    margin-bottom: 0.24rem;
  	}
  	.black-star-icon{
	    height: 0.32rem;
	    margin-left: 0.2rem;
  	}
  	.shop-addr {
	    font-size: 0.24rem;
	    color: #b3b3b3;
  	}
  	.shopInfo-right {
	    display: flex;
	    align-items: center;
	    color: #666;
  	}
  	.shopInfo .van-icon {
	  	font-size: 0.28rem;
	    color: #e5e5e5;
  	}
</style>