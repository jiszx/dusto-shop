<template>
	<div class="component-search-btn">
    <!--搜索输入按钮-->
    <router-link class="top-search block" :to="{name:'Search'}">
      <Icon name="search" class="search-btn inline-block vertical-align-middle" />
      <span class="inline-block search-btn-text vertical-align-middle">搜索</span>
    </router-link>
	</div>
</template>

<script>
  import {
    Icon,
  } from 'vant';
	export default {
    data(){
      return{}
    },
    components:{
      Icon,
    },
	}
</script>

<style scoped>
.component-search-btn{
  box-sizing: border-box;
  width: 100%;
  height: 1rem;
  padding: 0.21rem 0.3rem;
  color:#b3b3b3;
}
.top-search{
  line-height: 0.52rem;
  background: #f7f7f7;
  padding-left: 0.3rem;
  color:#b3b3b3;
}
.search-btn{
  font-size: 0.28rem;
  vertical-align: middle;
}
.search-btn-text{
  margin-left: 0.2rem;
}
</style>
