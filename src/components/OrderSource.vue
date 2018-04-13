<template>
  <div class="shopResource">
    <div class="list-item">
       <p><span>订单来源：</span><span>在线商城</span></p>
    </div>
    <div class="list-item">
       <p><span>商品总额</span><span class="rightSpan">&yen;119.00</span></p>
    </div>
    <div class="list-item">
      <p><span>积分抵扣</span><span class="rightSpan">&#45;&yen;1.00</span></p>
    </div>
    <div class="list-item">
       <p><span>&#43;运费</span><span class="rightSpan">&yen;0.00</span></p>
    </div>
  </div>
</template>

<script>
  export default {
  	data() {
	    return {
	    	 
	    }
  	},
  	components: {
     
  	},
    methods: {
        
    }
  };
</script>

<style>
  
   /*订单来源*/
  .shopResource{
    border-bottom: 0.02rem solid #e5e5e5;
    background:#fff;
  }
  .shopResource{
    border-top: 0.02rem solid #e5e5e5;
  }
  .shopResource .list-item,.shopTotle .list-item,.detailsRefunds .list-item {
      display: -webkit-box;
      display: -webkit-flex;
      display: flex;
      padding: 0 0.3rem;
  }
  .shopResource div.list-item:nth-of-type(1){
    padding-bottom: 0.1rem;
  }
  .shopResource,.shopTotle,.detailsRefunds{
    padding: 0.2rem 0;
  }
  .shopResource div,.detailsRefunds div{
    font-size:0.24rem;
    color:#999;
  } 
  .shopResource .list-item p{
    -webkit-box-flex: 1;
    -webkit-flex: 1;
    flex: 1;
  }
  .shopResource .list-item p .rightSpan{
    float:right;
  }
</style>
