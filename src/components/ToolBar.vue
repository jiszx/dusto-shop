<template>
  <div>
    <tabbar v-model="active">
      <tabbar-item v-for="(item,index) in items" :to="item.toURL" :key="item.name">
        <span>{{item.name}}</span>
        <template slot="icon" slot-scope="props">
          <img :src="props.active ? item.active : item.normal"/>
        </template>
      </tabbar-item>
    </tabbar>
  </div>
</template>

<script>
  import {Tabbar, TabbarItem} from 'vant';
  import {mapState} from 'vuex'

  export default {
    name: "ToolBar",
    data() {
      return {
        items: [
          {
            name: "首页",
            toURL: "/",
            normal: require("../assets/icon/icon-home-wdj.png"),
            active: require("../assets/icon/icon-home-dj.png")
          },
          {
            name: "资讯",
            toURL: "/message",
            normal: require("../assets/icon/icon-zixun-wdj.png"),
            active: require("../assets/icon/icon-zixun-dj.png")
          },
          {
            name: "我的",
            toURL: "/mine",
            normal: require("../assets/icon/icon-wode-wdj.png"),
            active: require("../assets/icon/icon-wode-dj.png")
          },
          {
            name: "门店",
            toURL: "/shop",
            normal: require("../assets/icon/icon-mend-wdj.png"),
            active: require("../assets/icon/icon-mend-dj.png")
          },
        ]
      }
    },
    methods: {
      onChnge: function (active) {
        alert(active)
      }
    },
    computed: {
//      ...mapState({
//        active: state => state.home.active
//      }),
      active: {
        get: function () {
          return this.$store.state.home.active
        },
        set: function (val) {
          this.$store.state.home.active = val
        }
      }
    },
    components: {
      Tabbar,
      TabbarItem
    }
  };
</script>

<style>
  .van-tabbar {
    height: 54px;
  }

  .van-tabbar-item__icon img {
    height: 22px;
  }

  .van-tabbar-item__icon {
    margin-top: 5px;
  }

  .van-tabbar-item--active {
    color: #ea5361;
  }

  .van-tabbar-item__text {
    margin-bottom: 7px;
  }
</style>
