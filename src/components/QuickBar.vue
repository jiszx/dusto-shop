<template>
  <div>
    <div class="quickBar" @click="toggleMask" :style="{zIndex:showMask?200:10}">
      <div class="leftIcon">
        <div><img src="../assets/icon/icon-ksdh-right.png" v-if="showMask"/><img src="../assets/icon/icon-ksdh-left.png"
                                                                                 v-else="showMask"/></div>
        <div class="text">快速导航</div>
      </div>
      <div class="rightNav" v-show="showMask">
        <div class="icons">
          <div class="icon" @click="pushURL('Home')">
            <div><img src="../assets/icon/icon-home-wdj.png"/></div>
            <div>首页</div>
          </div>
          <div class="icon" @click="pushURL('Message')">
            <div><img src="../assets/icon/icon-zixun-wdj.png"/></div>
            <div>资讯</div>
          </div>
          <div class="icon" @click="pushURL('Mine')">
            <div><img src="../assets/icon/icon-wode-wdj.png"/></div>
            <div>我的</div>
          </div>
          <div class="icon" @click="pushURL('ShopList')">
            <div><img src="../assets/icon/icon-mend-wdj.png"/></div>
            <div>门店</div>
          </div>
        </div>
        <div class="icons">
          <div class="icon" @click="pushURL('Search')">
            <div><img src="../assets/icon/icon-ksdh-ss.png"/></div>
            <div>搜索</div>
          </div>
          <!--<div class="icon" @click="pushURL('MyMsg')">-->
            <!--<div><img src="../assets/icon/icon-xx.png"/></div>-->
            <!--<div>消息</div>-->
          <!--</div>-->
          <div class="icon" @click="pushURL('Contact')">
            <div><img src="../assets/icon/icon-kf.png"/></div>
            <div>客服</div>
          </div>
          <div class="icon" @click="pushURL('Cart')">
            <div><img src="../assets/icon/icon-gwc.png"/></div>
            <div>购物车</div>
          </div>

        </div>
      </div>
    </div>
    <div class="maskShow" v-if="showMask" @click="toggleMask" :style="{zIndex:showMask?190:10}"></div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        showMask: false
      }
    },
    components: {},
    methods: {
      toggleMask() {
        this.showMask = !this.showMask;
        this.$nextTick(()=>{
          if (this.showMask) {
            document.body.style.overflow = "hidden";
          } else {
            document.body.style.overflow = "auto";
          }
        })
      },
      pushURL: function (name) {
        this.$router.push({name: name});
      }
    },
  }
</script>
<style scoped>
  .quickBar {
    position: fixed;
    bottom: 200px;
    right: 0;
    /*height: 154px;*/
    display: flex;
    z-index: 20;
    align-items: center;
    flex-direction: row;
  }

  .quickBar .leftIcon {
    height: 40px;
    width: 45px;
    background: #000;
    opacity: 0.7;
    padding-left: 7px;
    border-bottom-left-radius: 6px;
    border-top-left-radius: 6px;
    color: #fff;
    font-size: 12px;
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .quickBar .leftIcon img {
    width: 100%;
  }

  .quickBar .leftIcon text {
    margin-right: 5px;
  }

  .quickBar .rightNav {
    padding: 20px 25px 16px 22px;
    display: flex;
    flex-direction: column;
    background: #FFF;
    border-top-left-radius: 10px;
    border-bottom-left-radius: 10px;
  }

  .quickBar .rightNav .icons {
    display: flex;
    flex-direction: row;
  }

  .icons .icon {
    display: flex;
    width: 60px;
    height: auto;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }

  .icons:first-child {
    margin-bottom: 10px;
  }

  .icons .icon div {
    flex-grow: 1;
    font-size: 11px;
    color: #adb2b8;
  }

  .icons .icon div:first-child {
    padding: 0px 15px;
  }

  .icons .icon div img {
    width: 100%;
  }

  .maskShow {
    width: 100%;
    height: 100%;
    background: #000;
    opacity: 0.3;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 10;
  }
</style>
