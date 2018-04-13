/**
  封装一个快速导航按钮
*/
<template>
    <div class="component-fast-navigation fontsize-20">
      <div class="fast-nav-btn">
        <span class="fast-nav-btn-icon"></span>
        <div class="fast-nav-btn-text">
          <p>快速</p>
          <p>导航</p>
        </div>
      </div>
    </div>
</template>

<script>
    export default{
        data(){
          return{}
        },
    }
</script>

<style scoped>
.component-fast-navigation{
  box-sizing: border-box;
  display: inline-block;
  width: 1.2rem;
  height: 0.8rem;
  padding-left: 0.1rem;
  border-radius: 0.15rem 0 0 0.15rem;
  background: #4c4c4c;
  position: relative;
}
.fast-nav-btn{
  height: 0.8rem;
  color:#fff;
  box-sizing: border-box;
  padding: 0.1rem 0.1rem;
  line-height: 1.2;
}
  .fast-nav-btn-icon{
    display: inline-block;
    vertical-align: middle;
    width: 14px;
    height: 12px;
    background: url(../assets/icon/icon-ksdh-left.png) no-repeat center center;
    background-size: cover;
  }
  .fast-nav-btn-text{
    display: inline-block;
    vertical-align: middle;
  }
</style>
